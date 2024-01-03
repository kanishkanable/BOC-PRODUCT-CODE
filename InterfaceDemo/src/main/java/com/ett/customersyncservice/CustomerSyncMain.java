package com.ett.customersyncservice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

public class CustomerSyncMain {
	public static void main(String args[]) {

		String requestXML = "";
		String responseXML = "";
		String customerID = "0015189137";
		BufferedReader buf = null;
		File file = new File("F:\\Office_Works\\Request.xml");
		try {
			InputStream is = new FileInputStream(file);
			System.out.println("Read the file successfully");
			buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}

			String fileAsString = sb.toString();
			System.out.println("Request XML from TI : " + fileAsString);
			requestXML = fileAsString;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		responseXML = getBankResponse(requestXML, customerID);

		System.out.println(responseXML);

	}

	public static String getBankResponse(String xml, String customerID) {
		CustomerSyncBankResponse aCustomerSyncBankResponse = new CustomerSyncBankResponse();
		com.boc.customerinquiryresponse.IFX responseParsed = null;
		InputStream inStream = new ByteArrayInputStream(xml.getBytes());
		InputStream is1 = new ByteArrayInputStream(xml.getBytes());
		try {

			JAXBContext jaxbContext1 = JAXBContext.newInstance("com.boc.custInq");
			Unmarshaller jaxbUnmarshaller1 = jaxbContext1.createUnmarshaller();
			com.boc.customerinquiryresponse.IFX resqParsed = (com.boc.customerinquiryresponse.IFX) jaxbUnmarshaller1
					.unmarshal(is1);
			System.out.println("Completed");
			System.out.println(resqParsed.getSignonRs().getComputerKey());

			JAXBContext jaxbContext = JAXBContext.newInstance("com.boc.custInq");
			System.out.println("Created Context");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			responseParsed = (com.boc.customerinquiryresponse.IFX) jaxbUnmarshaller.unmarshal(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		aCustomerSyncBankResponse.setBankResponse(responseParsed.toString());

		aCustomerSyncBankResponse
				.setCustomerType(responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustType());
		aCustomerSyncBankResponse
				.setShortName(responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getShortName());
		aCustomerSyncBankResponse.setDefaultBranch(
				responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getBranchId());
		aCustomerSyncBankResponse
				.setSalutation(responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getSalutation());

		if (responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddrCode()
				.equalsIgnoreCase("PRIMARY")) {

			aCustomerSyncBankResponse.setFullName(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
					.getCustProfBasic().getCustAddr().getFullName());
			aCustomerSyncBankResponse.setNameAndAddress(responseParsed.getCIFSvcRs().getCustProfBasicInqRs()
					.getCustProfBasic().getCustAddr().getFullName() + "\n"
					+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr1()
					+ "\n"
					+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr2()
					+ "\n"
					+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr3()
					+ "\n"
					+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr4()
					+ "\n"
					+ responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic().getCustAddr().getAddr5());
			aCustomerSyncBankResponse.setZipCode(responseParsed.getCIFSvcRs().getCustProfBasicInqRs().getCustProfBasic()
					.getCustAddr().getPostalCode());

		}

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

		String responseXML = getTIRequestXML(aCustomerSyncBankResponse, customerID);

		return responseXML;
	}

	private static String getTIRequestXML(CustomerSyncBankResponse aCustomerSyncBankResponse, String custID) {

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
			/* commented by nable and add to take the value from table
			 aCustomer.setSourceBankingBusiness("TIZONE1"); 
			 */
			aCustomer.setSourceBankingBusiness(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			aCustomer.setMnemonic(custID);
			aCustomer.setCustomerType(custID);
			aCustomer.setCustomerType(BOCCommonUtil.getCustomerType(aCustomerSyncBankResponse.getCustomerType()));
			aCustomer.setFullName(aCustomerSyncBankResponse.getFullName());
			aCustomer.setShortName(aCustomerSyncBankResponse.getShortName());
			aCustomer.setMailToBranch(aCustomerSyncBankResponse.getDefaultBranch());
			AddressDetail aAddressDetail = new AddressDetail();
			aAddressDetail.setAddressType("P");
			aAddressDetail.setSalutation(aCustomerSyncBankResponse.getSalutation());
			aAddressDetail.setNameAndAddress(aCustomerSyncBankResponse.getNameAndAddress());
			aAddressDetail.setZipCode(
					of.createSTRCustomerAddressDetailsAddressDetailZipCode(aCustomerSyncBankResponse.getZipCode()));
			aAddressDetail.setPhone(
					of.createSTRCustomerAddressDetailsAddressDetailPhone(aCustomerSyncBankResponse.getPhone()));
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
}
