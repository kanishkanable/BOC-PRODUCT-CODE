package com.ett.accountsyncservice;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.apps.ti.service.common.EnigmaBoolean;
import com.misys.tiplus2.apps.ti.service.common.GWRCustomer;
import com.misys.tiplus2.apps.ti.service.common.GWRMaintenanceType;
import com.misys.tiplus2.apps.ti.service.messages.ObjectFactory;
import com.misys.tiplus2.apps.ti.service.messages.STRAccount;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;

public class AccountMainClass {

	public static void main(String[] args) {

		String requestXML = "";
		String responseXML = "";
		String requestXML2 = null;
		BufferedReader buf = null;
		File file = new File("F:\\Office_Works\\Request2.xml");
		File file1 = new File("F:\\Office_Works\\Request1.xml");
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
		try {
			InputStream is = new FileInputStream(file1);
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
			requestXML2 = fileAsString;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AccountsSyncBankResponseModel aAccountsSyncBankResponseModel = new AccountsSyncBankResponseModel();
		aAccountsSyncBankResponseModel = getAccountDetailfromCore(requestXML);
		aAccountsSyncBankResponseModel = getAccountCustomerfromCore(requestXML2, aAccountsSyncBankResponseModel);
		responseXML = getTIRequestXML(aAccountsSyncBankResponseModel);

		System.out.println(responseXML);

	}

	private static AccountsSyncBankResponseModel getAccountDetailfromCore(String bankResponseXML) {

		AccountsSyncBankResponseModel aAccountsSyncBankResponseModel = new AccountsSyncBankResponseModel();
		com.boc.accountdetailsresponse.IFX responseParsed = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.boc.accountdetailsresponse");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			responseParsed = (com.boc.accountdetailsresponse.IFX) jaxbUnmarshaller
					.unmarshal(new StringReader(bankResponseXML));
		} catch (Exception e) {
			e.printStackTrace();
		}

		aAccountsSyncBankResponseModel
				.setAccType(responseParsed.getBankSvcRs().getAcctInqRs().getDepAcctId().getAcctType());
		aAccountsSyncBankResponseModel
				.setBackOfficeAccount(responseParsed.getBankSvcRs().getAcctInqRs().getDepAcctId().getAcctId());
		aAccountsSyncBankResponseModel.setBranchID(responseParsed.getBankSvcRs().getAcctInqRs().getBranchId());
		aAccountsSyncBankResponseModel
				.setCurrency(responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(1).getCurAmt().getCurCode());

		return aAccountsSyncBankResponseModel;

	}

	private static AccountsSyncBankResponseModel getAccountCustomerfromCore(String bankResponseXML,
			AccountsSyncBankResponseModel aAccountsSyncBankResponseModel) {

		com.boc.accountcustomerresponse.IFX responseParsed = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.boc.accountcustomerresponse");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			responseParsed = (com.boc.accountcustomerresponse.IFX) jaxbUnmarshaller
					.unmarshal(new StringReader(bankResponseXML));
		} catch (Exception e) {
			e.printStackTrace();
		}

		aAccountsSyncBankResponseModel
				.setCustID(responseParsed.getMaintSvcRs().getAcctCustInqRs().getAcctRelation().getCustPermId());

		return aAccountsSyncBankResponseModel;

	}

	private static String getTIRequestXML(AccountsSyncBankResponseModel aAccountsSyncBankResponseModel) {

		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String tiRequest = null;
		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.ACCOUNT);
			ObjectFactory of = new ObjectFactory();
			STRAccount aAccount = new STRAccount();
			aAccount.setMaintType(GWRMaintenanceType.F);
			aAccount.setMaintainedInBackOffice(EnigmaBoolean.N);
			aAccount.setBackOfficeAccount(aAccountsSyncBankResponseModel.getBackOfficeAccount());
			aAccount.setBranch(aAccountsSyncBankResponseModel.getBranchID());
			GWRCustomer acust = new GWRCustomer();
			/* commented by nable and add to take the value from table
			 acust.setSourceBankingBusiness("TIZONE1");
			 */
			acust.setSourceBankingBusiness(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			acust.setMnemonic(aAccountsSyncBankResponseModel.getCustID());
			aAccount.setCustomer(acust);
			aAccount.setCurrency(aAccountsSyncBankResponseModel.getCurrency());
			aAccount.setAccountType(aAccountsSyncBankResponseModel.getAccType());
			aAccount.setExternalAccount(aAccountsSyncBankResponseModel.getBackOfficeAccount());
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<STRAccount> accDetails = of.createAccount(aAccount);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(accDetails);
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
