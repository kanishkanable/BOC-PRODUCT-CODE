package com.ett.customersyncservice;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

import com.boc.customerinquiryrequest.IFX;
import com.boc.customerinquiryrequest.IFX.CIFSvcRq;
import com.boc.customerinquiryrequest.IFX.CIFSvcRq.CustProfBasicInqRq;
import com.boc.customerinquiryrequest.IFX.EnvironmentInfo;
import com.boc.customerinquiryrequest.IFX.SignonRq;
import com.boc.customerinquiryrequest.IFX.SignonRq.ClientApp;
import com.boc.customerinquiryrequest.IFX.SignonRq.SignonPswd;
import com.boc.customerinquiryrequest.IFX.SignonRq.SignonPswd.CustId;
import com.boc.customerinquiryrequest.IFX.SignonRq.SignonPswd.CustPswd;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.apps.ti.service.common.EnigmaBoolean;
import com.misys.tiplus2.apps.ti.service.common.GWRMaintenanceType;
import com.misys.tiplus2.apps.ti.service.messages.Customer;
import com.misys.tiplus2.apps.ti.service.messages.ObjectFactory;
import com.misys.tiplus2.apps.ti.service.messages.STRCustomer.AddressDetails;
import com.misys.tiplus2.apps.ti.service.messages.STRCustomer.AddressDetails.AddressDetail;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;

public class CustomerSync {

	public String getCustomerDetails(CustomerModel aCustomerModel) {
		System.out.println("Inside getCustomerDetails method");
		String status = "";
		CustomerSyncBankResponse aCustomerSyncBankResponse = new CustomerSyncBankResponse();
		try {
			String bankReqXML = getBankRequest(aCustomerModel.getCustomerID());
			System.out.println("Bank Request " + bankReqXML);
			aCustomerSyncBankResponse = callAPIEndPoint(bankReqXML);
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+aCustomerSyncBankResponse.getBankResponse() );
			
			if (aCustomerSyncBankResponse.getBankResponse() == null
					|| aCustomerSyncBankResponse.getBankResponse().trim().isEmpty()) {
				System.out.println("Inside getCustomerDetails method-->bankresponse if statement");
				return status = "FAILED";
			} else {
				String tiRequest = getTIRequestXML(aCustomerSyncBankResponse, aCustomerModel.getCustomerID());
				String tiResponse = CommonUtil.processEJBMessage(tiRequest);
				String pkey = BOCCommonUtil.getNextSeqNo(Constants.CUSTOMER_SEQUENCE);
				BOCCommonUtil.insertIntoCustomerlogTable(pkey, aCustomerModel.getCustomerID(), bankReqXML,
						aCustomerSyncBankResponse.getBankResponse(), tiRequest, tiResponse);
				System.out.println("tiResponse" + tiResponse);
				if (!CommonUtil.NullStringCheck(tiResponse)) {
					if (tiResponse.contains("SUCCEEDED"))
						status = "SUCCEEDED";
					else
						status = "FAILED";
				} else {
					status = "FAILED";
				}

				System.out.println("status of full name-->" + status);
			}
		} catch (Exception e) {
			status = "FAILED";
			e.printStackTrace();
		}

		return status;

	}

	private String getTIRequestXML(CustomerSyncBankResponse aCustomerSyncBankResponse, String custID) {

		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String tiRequest = null;
		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.CUSTOMER);
			ObjectFactory of = new ObjectFactory();
			Customer aCustomer = new Customer();
			aCustomer.setMaintType(GWRMaintenanceType.F);
			aCustomer.setMaintainedInBackOffice(EnigmaBoolean.N);
			aCustomer.setSourceBankingBusiness(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			aCustomer.setMnemonic(custID);
			aCustomer.setCustomerNumber(custID);
			aCustomer.setCustomerType(BOCCommonUtil.getCustomerType(aCustomerSyncBankResponse.getCustomerType()));
			aCustomer.setFullName(aCustomerSyncBankResponse.getFullName());
			aCustomer.setShortName(
					(StringUtils.rightPad(aCustomerSyncBankResponse.getShortName().trim(), 15, Constants.SPACE))
							.substring(0, 15));
			aCustomer.setMailToBranch(
					StringUtils.leftPad(aCustomerSyncBankResponse.getDefaultBranch(), 4, Constants.ZERO));
			AddressDetail aAddressDetail = new AddressDetail();
			aAddressDetail.setAddressType("P");
			aAddressDetail.setSalutation(aCustomerSyncBankResponse.getSalutation());
			aAddressDetail.setNameAndAddress(aCustomerSyncBankResponse.getNameAndAddress());

			if (!CommonUtil.NullStringCheck(aCustomerSyncBankResponse.getZipCode()))
				aAddressDetail.setZipCode(
						of.createSTRCustomerAddressDetailsAddressDetailZipCode(aCustomerSyncBankResponse.getZipCode()));

			if (!CommonUtil.NullStringCheck(aCustomerSyncBankResponse.getPhone()))
				aAddressDetail.setPhone(
						of.createSTRCustomerAddressDetailsAddressDetailPhone(aCustomerSyncBankResponse.getPhone()));

			if (!CommonUtil.NullStringCheck(aCustomerSyncBankResponse.getEmail()))
				aAddressDetail.setEmail(
						of.createSTRCustomerAddressDetailsAddressDetailEmail(aCustomerSyncBankResponse.getEmail()));

			List<AddressDetail> adl = new LinkedList<>();
			adl.add(aAddressDetail);
			AddressDetails aAddressDetails = new AddressDetails();
			aAddressDetails.setAddressDetail(adl);
			aCustomer.setAddressDetails(aAddressDetails);
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<Customer> custDetails = of.createCustomer(aCustomer);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(custDetails);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sRequest, outStream);
			tiRequest = outStream.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tiRequest;

	}

	private CustomerSyncBankResponse callAPIEndPoint(String bankReqXML) {
		String bankResponseXML = null;
		CustomerSyncBankResponse aCustomerSyncBankResponse = new CustomerSyncBankResponse();

		try {
			bankResponseXML = BOCCommonUtil.callCoreBankingWebService(bankReqXML);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Bank Response " + bankResponseXML);

		com.boc.customerinquiryresponse.IFX responseParsed = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.boc.customerinquiryresponse");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			responseParsed = (com.boc.customerinquiryresponse.IFX) jaxbUnmarshaller
					.unmarshal(new StringReader(bankResponseXML));
		} catch (Exception e) {
			e.printStackTrace();
		}
//Code added for null pointer exception
		aCustomerSyncBankResponse.setBankResponse(responseParsed.toString());
		byte statuscoderesponse = responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getStatus().getStatusCode();
		System.out.println("statuscoderesponse " + statuscoderesponse);
		String severityresponse = responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getStatus().getSeverity();
		System.out.println("severityresponse " + severityresponse);
		 
		aCustomerSyncBankResponse.setStatus(""+statuscoderesponse);
		
		System.out.println("Bank Status Code"+aCustomerSyncBankResponse.getStatus());
		
		if (aCustomerSyncBankResponse.getStatus().equalsIgnoreCase("100")|| severityresponse.equalsIgnoreCase("Error")) {
			System.out.println("Inside Exception code ");
			aCustomerSyncBankResponse.setBankResponse("");
			return aCustomerSyncBankResponse;
		} else {
			aCustomerSyncBankResponse.setCustomerType(
					responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustType());
			aCustomerSyncBankResponse.setShortName(
					responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getShortName());
			aCustomerSyncBankResponse.setDefaultBranch(
					responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getBranchId());
			aCustomerSyncBankResponse.setSalutation(
					responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getSalutation());

			if (responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddrCode()
					.equalsIgnoreCase("PRIMARY")) {

				aCustomerSyncBankResponse.setFullName(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
						.getCustProfBasic().getCustAddr().getFullName());
				aCustomerSyncBankResponse.setNameAndAddress(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
						.getCustProfBasic().getCustAddr().getFullName()
						+ "\n"
						+ responseParsed
								.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr1()
						+ "\n"
						+ responseParsed
								.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr2()
						+ "\n"
						+ responseParsed
								.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr3()
						+ "\n"
						+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr()
								.getAddr4()
						+ "\n" + responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr()
								.getAddr5());
				aCustomerSyncBankResponse.setZipCode(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
						.getCustProfBasic().getCustAddr().getPostalCode());

			}
			aCustomerSyncBankResponse.setPhone("");
			for (int i = 0; i < responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getPhoneNum()
					.size(); i++) {
				if (responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getPhoneNum().get(i)
						.getPhoneType().equalsIgnoreCase("CellPhone")) {

					aCustomerSyncBankResponse.setPhone(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
							.getCustProfBasic().getPhoneNum().get(i).getPhone());

				}
			}
			aCustomerSyncBankResponse
					.setEmail(responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getEmailAddr());

			return aCustomerSyncBankResponse;
		}
	}

	private String getBankRequest(String custID) {

		StringWriter result = null;
		String bankRequestXML = null;
		String correlationID = CommonUtil.generateCorrelationId();
		IFX obj = new IFX();
		SignonRq aSignonRq = new SignonRq();

		SignonPswd aSignonPswd = new SignonPswd();
		aSignonPswd.setSignonRole("CSR");

		CustId aCustId = new CustId();
		aCustId.setCustLoginId(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_USERNAME));
		aCustId.setSPName("FiservICBS");
		aSignonPswd.setCustId(aCustId);

		CustPswd aCustPswd = new CustPswd();
		aCustPswd.setCryptType("NONE");
		aCustPswd.setPswd(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_PASSWORD));
		aSignonPswd.setCustPswd(aCustPswd);

		aSignonPswd.setGenSessKey((byte) 0);
		aSignonRq.setSignonPswd(aSignonPswd);

		ClientApp aClientApp = new ClientApp();
		aClientApp.setClientAppKey("BOCSRVRHECGNSYSQCUWRJDHKRFDBNTGN");
		aClientApp.setName("TFIN");
		aClientApp.setOrg("Fiserv");
		aClientApp.setVersion((float) 1.0);
		aSignonRq.setClientApp(aClientApp);

		aSignonRq.setComputerId("SGPVMAPRDEV03");
		aSignonRq.setInstitutionCode("BOCSR");
		obj.setSignonRq(aSignonRq);

		EnvironmentInfo aEnvironmentInfo = new EnvironmentInfo();
		aEnvironmentInfo.setEnvironmentName("default");

		obj.setEnvironmentInfo(aEnvironmentInfo);

		CIFSvcRq aCIFSvcRq = new CIFSvcRq();
		aCIFSvcRq.setRqUID(correlationID);
		aCIFSvcRq.setSPName("FiservICBS");
		CustProfBasicInqRq aCustProfBasicInqRq = new CustProfBasicInqRq();
		aCustProfBasicInqRq.setRqUID(correlationID);
		com.boc.customerinquiryrequest.IFX.CIFSvcRq.CustProfBasicInqRq.CustId aCustId1 = new com.boc.customerinquiryrequest.IFX.CIFSvcRq.CustProfBasicInqRq.CustId();

		aCustId1.setSPName("FiservICBS");
		aCustId1.setCustPermId(StringUtils.leftPad(custID, 10, Constants.ZERO));
		aCustProfBasicInqRq.setCustId(aCustId1);

		aCIFSvcRq.setCustProfBasicInqRq(aCustProfBasicInqRq);

		obj.setCIFSvcRq(aCIFSvcRq);

		try {

			result = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(IFX.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(obj, result);

			System.out.println("XML to Bank" + result.toString());

			bankRequestXML = result.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return bankRequestXML;

	}

}
