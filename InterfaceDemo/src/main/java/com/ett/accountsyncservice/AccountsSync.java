package com.ett.accountsyncservice;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

import com.boc.accountdetailsrequest.IFX;
import com.boc.accountdetailsrequest.IFX.BankSvcRq;
import com.boc.accountdetailsrequest.IFX.EnvironmentInfo;
import com.boc.accountdetailsrequest.IFX.SignonRq;
import com.boc.accountdetailsrequest.IFX.BankSvcRq.AcctInqRq;
import com.boc.accountdetailsrequest.IFX.BankSvcRq.AcctInqRq.CustId;
import com.boc.accountdetailsrequest.IFX.BankSvcRq.AcctInqRq.DepAcctId;
import com.boc.accountdetailsrequest.IFX.SignonRq.ClientApp;
import com.boc.accountdetailsrequest.IFX.SignonRq.ClientDt;
import com.boc.accountdetailsrequest.IFX.SignonRq.SignonPswd;
import com.boc.accountdetailsrequest.IFX.SignonRq.SignonPswd.CustPswd;
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

public class AccountsSync {

	public String accountID;
	public String accountType;

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountDetails() {
		System.out.println("Inside get AccountDetails method");
		AccountsSyncBankRequestModel aAccountsSyncBankRequestModel = new AccountsSyncBankRequestModel();
		AccountsSyncBankResponseModel aAccountsSyncBankResponseModel = new AccountsSyncBankResponseModel();
		aAccountsSyncBankRequestModel = setBankRequestModel();
		aAccountsSyncBankResponseModel = getAccountDetailfromCore(aAccountsSyncBankRequestModel);
		aAccountsSyncBankResponseModel = getAccountCustomerfromCore(aAccountsSyncBankRequestModel,
				aAccountsSyncBankResponseModel);
		String tiRequest = getTIRequestXML(aAccountsSyncBankResponseModel);
		String tiResponse = CommonUtil.processEJBMessage(tiRequest);
		String pkey = BOCCommonUtil.getNextSeqNo(Constants.ACCOUNT_SEQUENCE);
		BOCCommonUtil.insertIntoAccountlogTable(pkey, getAccountID(), tiRequest, tiResponse);
		System.out.println("TiResponse-->"+tiResponse);
		String status=aAccountsSyncBankResponseModel.getBackOfficeAccount();
		System.out.println("status from account bank response"+status);
		return status;
	}

	private String getTIRequestXML(AccountsSyncBankResponseModel aAccountsSyncBankResponseModel) {

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
			aAccount.setBackOfficeAccount(aAccountsSyncBankResponseModel.getBackOfficeAccount().replaceFirst("^0+(?!$)", ""));
			aAccount.setBranch(StringUtils.leftPad(aAccountsSyncBankResponseModel.getBranchID().toString(), 4, Constants.ZERO));
			
			GWRCustomer acust = new GWRCustomer();
			acust.setSourceBankingBusiness(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			acust.setMnemonic(aAccountsSyncBankResponseModel.getCustID().replaceFirst("^0+(?!$)", ""));
			aAccount.setCustomer(acust);
			aAccount.setCurrency(aAccountsSyncBankResponseModel.getCurrency());
			aAccount.setAccountType(BOCCommonUtil.ToTIACCTypeTranslate(aAccountsSyncBankResponseModel.getAccType()));
			aAccount.setExternalAccount(aAccountsSyncBankResponseModel.getBackOfficeAccount().replaceFirst("^0+(?!$)", ""));
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

	private AccountsSyncBankRequestModel setBankRequestModel() {

		AccountsSyncBankRequestModel aAccountsSyncBankRequestModel = new AccountsSyncBankRequestModel();
		System.out.println("AccNumb in bankreq"+getAccountID());
		System.out.println("ACCType in bankreq"+getAccountType());
		aAccountsSyncBankRequestModel.setAcctId(getAccountID());
		aAccountsSyncBankRequestModel.setAcctType(getAccountType());
		aAccountsSyncBankRequestModel.setDay(CommonUtil.getSysDate("dd"));
		aAccountsSyncBankRequestModel.setMonth(CommonUtil.getSysDate("MM"));
		aAccountsSyncBankRequestModel.setYear(CommonUtil.getSysDate("yyyy"));

		return aAccountsSyncBankRequestModel;

	}

	private AccountsSyncBankResponseModel getAccountCustomerfromCore(
			AccountsSyncBankRequestModel aAccountsSyncBankRequestModel,
			AccountsSyncBankResponseModel aAccountsSyncBankResponseModel) {

		String bankReqXML = generateAccountCustomerRequest(aAccountsSyncBankRequestModel);
		String bankResponseXML = null;

		com.boc.accountcustomerresponse.IFX responseParsed = null;
		try {
			bankResponseXML = BOCCommonUtil.callCoreBankingWebService(bankReqXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private AccountsSyncBankResponseModel getAccountDetailfromCore(
			AccountsSyncBankRequestModel aAccountsSyncBankRequestModel) {

		String bankReqXML = generateAccountDetailsRequest(aAccountsSyncBankRequestModel);
		String bankResponseXML = null;
		AccountsSyncBankResponseModel aAccountsSyncBankResponseModel = new AccountsSyncBankResponseModel();
		com.boc.accountdetailsresponse.IFX responseParsed = null;
		try {
			bankResponseXML = BOCCommonUtil.callCoreBankingWebService(bankReqXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		System.out.println("Inside account model -->"+aAccountsSyncBankResponseModel
				.getAccType());
		aAccountsSyncBankResponseModel
				.setBackOfficeAccount(responseParsed.getBankSvcRs().getAcctInqRs().getDepAcctId().getAcctId());
		aAccountsSyncBankResponseModel.setBranchID(responseParsed.getBankSvcRs().getAcctInqRs().getBranchId());
		aAccountsSyncBankResponseModel
				.setCurrency(responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(1).getCurAmt().getCurCode());

		return aAccountsSyncBankResponseModel;

	}

	private String generateAccountCustomerRequest(AccountsSyncBankRequestModel aAccountsSyncBankRequestModel) {

		String reqID = CommonUtil.generateCorrelationId();
		StringWriter result = null;
		String bankRequestXML = null;
		com.boc.accountcustomerrequest.IFX obj = new com.boc.accountcustomerrequest.IFX();

		com.boc.accountcustomerrequest.IFX.MaintSvcRq aMaint = new com.boc.accountcustomerrequest.IFX.MaintSvcRq();

		aMaint.setRqUID(reqID);
		aMaint.setSPName("FiservICBS");

		com.boc.accountcustomerrequest.IFX.MaintSvcRq.AcctCustInqRq accInq = new com.boc.accountcustomerrequest.IFX.MaintSvcRq.AcctCustInqRq();
		accInq.setRqUID(reqID);
		accInq.setSPName("FiservICBS");

		com.boc.accountcustomerrequest.IFX.MaintSvcRq.AcctCustInqRq.BankAcctId depAccId = new com.boc.accountcustomerrequest.IFX.MaintSvcRq.AcctCustInqRq.BankAcctId();
		depAccId.setAcctId(aAccountsSyncBankRequestModel.getAcctId());
		depAccId.setAcctType(aAccountsSyncBankRequestModel.getAcctType());

		accInq.setBankAcctId(depAccId);
		aMaint.setAcctCustInqRq(accInq);

		obj.setMaintSvcRq(aMaint);

		com.boc.accountcustomerrequest.IFX.EnvironmentInfo env = new com.boc.accountcustomerrequest.IFX.EnvironmentInfo();
		env.setEnvironmentName("default");
		obj.setEnvironmentInfo(env);

		com.boc.accountcustomerrequest.IFX.SignonRq sgnRq = new com.boc.accountcustomerrequest.IFX.SignonRq();
		sgnRq.setComputerId("SGPVMAPRDEV03");
		sgnRq.setInstitutionCode("BOCSR");
		com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd sgnpswd = new com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd();
		sgnpswd.setSignonRole("CSR");
		sgnpswd.setGenSessKey((byte) 0);

		com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd.CustId custid = new com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd.CustId();
		custid.setCustLoginId(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_USERNAME));
		custid.setSPName("FiservICBS");
		sgnpswd.setCustId(custid);

		com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd.CustPswd cuspwd = new com.boc.accountcustomerrequest.IFX.SignonRq.SignonPswd.CustPswd();
		cuspwd.setCryptType("NONE");
		cuspwd.setPswd(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_PASSWORD));
		sgnpswd.setCustPswd(cuspwd);
		sgnRq.setSignonPswd(sgnpswd);

		com.boc.accountcustomerrequest.IFX.SignonRq.ClientDt cltDt = new com.boc.accountcustomerrequest.IFX.SignonRq.ClientDt();
		cltDt.setDay(aAccountsSyncBankRequestModel.getDay());
		cltDt.setMonth(aAccountsSyncBankRequestModel.getMonth());
		cltDt.setYear(aAccountsSyncBankRequestModel.getYear());
		sgnRq.setClientDt(cltDt);

		com.boc.accountcustomerrequest.IFX.SignonRq.ClientApp clapp = new com.boc.accountcustomerrequest.IFX.SignonRq.ClientApp();
		clapp.setClientAppKey("BOCSRVRHECGNSYSQCUWRJDHKRFDBNTGN");
		clapp.setVersion((float) 1.0);
		clapp.setName("TFIN");
		clapp.setOrg("Fiserv");
		sgnRq.setClientApp(clapp);
		obj.setSignonRq(sgnRq);

		try {

			result = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(com.boc.accountcustomerrequest.IFX.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(obj, result);

			System.out.println("XML to Bank" + result.toString());

			bankRequestXML = result.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return bankRequestXML;

	}

	private String generateAccountDetailsRequest(AccountsSyncBankRequestModel aAccountsSyncBankRequestModel) {

		String reqID = CommonUtil.generateCorrelationId();
		StringWriter result = null;
		String bankRequestXML = null;
		IFX obj = new IFX();
		BankSvcRq bankSvc = new BankSvcRq();
		bankSvc.setRqUID(reqID);
		bankSvc.setSPName("FiservICBS");

		AcctInqRq accInq = new AcctInqRq();
		accInq.setIncBal((byte) 1);
		accInq.setIncExtBal((byte) 1);
		accInq.setRqUID(reqID);

		CustId cusId = new CustId();
		cusId.setSPName("FiservICBS");
		accInq.setCustId(cusId);

		DepAcctId depAccId = new DepAcctId();
		depAccId.setAcctId(aAccountsSyncBankRequestModel.getAcctId());
		depAccId.setAcctType(aAccountsSyncBankRequestModel.getAcctType());
		accInq.setDepAcctId(depAccId);
		bankSvc.setAcctInqRq(accInq);
		obj.setBankSvcRq(bankSvc);

		EnvironmentInfo env = new EnvironmentInfo();
		env.setEnvironmentName("default");
		obj.setEnvironmentInfo(env);

		SignonRq sgnRq = new SignonRq();
		sgnRq.setComputerId("SGPVMAPRDEV03");
		sgnRq.setInstitutionCode("BOCSR");
		SignonPswd sgnpswd = new SignonPswd();
		sgnpswd.setSignonRole("CSR");
		sgnpswd.setGenSessKey((byte) 0);

		com.boc.accountdetailsrequest.IFX.SignonRq.SignonPswd.CustId custid = new com.boc.accountdetailsrequest.IFX.SignonRq.SignonPswd.CustId();
		custid.setCustLoginId(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_USERNAME));
		custid.setSPName("FiservICBS");
		sgnpswd.setCustId(custid);

		CustPswd cuspwd = new CustPswd();
		cuspwd.setCryptType("NONE");
		cuspwd.setPswd(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_PASSWORD));
		sgnpswd.setCustPswd(cuspwd);
		sgnRq.setSignonPswd(sgnpswd);

		ClientDt cltDt = new ClientDt();
		cltDt.setDay(aAccountsSyncBankRequestModel.getDay());
		cltDt.setMonth(aAccountsSyncBankRequestModel.getMonth());
		cltDt.setYear(aAccountsSyncBankRequestModel.getYear());
		sgnRq.setClientDt(cltDt);

		ClientApp clapp = new ClientApp();
		clapp.setClientAppKey("BOCSRVRHECGNSYSQCUWRJDHKRFDBNTGN");
		clapp.setVersion((float) 1.0);
		clapp.setName("TFIN");
		clapp.setOrg("Fiserv");
		sgnRq.setClientApp(clapp);
		obj.setSignonRq(sgnRq);

		try {

			result = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(IFX.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

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
