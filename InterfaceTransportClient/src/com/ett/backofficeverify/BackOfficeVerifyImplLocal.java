package com.ett.backofficeverify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boc.accountbalancerequest.IFX;
import com.boc.accountbalancerequest.IFX.BankSvcRq;
import com.boc.accountbalancerequest.IFX.EnvironmentInfo;
import com.boc.accountbalancerequest.IFX.SignonRq;
import com.boc.accountbalancerequest.IFX.BankSvcRq.AcctInqRq;
import com.boc.accountbalancerequest.IFX.BankSvcRq.AcctInqRq.CustId;
import com.boc.accountbalancerequest.IFX.BankSvcRq.AcctInqRq.DepAcctId;
import com.boc.accountbalancerequest.IFX.SignonRq.ClientApp;
import com.boc.accountbalancerequest.IFX.SignonRq.ClientDt;
import com.boc.accountbalancerequest.IFX.SignonRq.SignonPswd;
import com.boc.accountbalancerequest.IFX.SignonRq.SignonPswd.CustPswd;
import com.ett.util.*;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceRequest;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceResponse;
import com.misys.tiplus2.apps.ti.service.messages.Posting;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceResponse;
import com.misys.tiplus2.services.control.StatusEnum;

public class BackOfficeVerifyImplLocal {

	private static final Logger LOG = LoggerFactory.getLogger(BackOfficeVerifyImpl.class);
	private String masterCode = "";
	private String eventCode = "";

	public String doBalanceCheck(String tiRequestXML) {
		Loggers.general().info(LOG, "Entered into BackOffice Verify Process");

		String pkey = null;
		String tiResponseXML = null;

		BackOfficeVerifyTIRequestModel aBackOfficeVerifyTIRequestModel = new BackOfficeVerifyTIRequestModel();
		BackOfficeVerifyTIResponseModel aBackOfficeVerifyTIResponseModel = new BackOfficeVerifyTIResponseModel();
		BackOfficeVerifyBankRequestModel aBackOfficeVerifyBankRequestModel = new BackOfficeVerifyBankRequestModel();
		BackOfficeVerifyBankResponseModel aBackOfficeVerifyBankResponseModel = new BackOfficeVerifyBankResponseModel();

		//pkey = BOCCommonUtil.getNextSeqNo(Constants.BALANCE_CHECK_SEQUENCE);

		System.out.println("getNextSeqNo key97...." + pkey);

		System.out.println("getNextSeqNo key97...." + tiRequestXML);
		tiRequestXML = tiRequestXML.replace("@", "");
		tiRequestXML = stripNonValidXMLCharacters(tiRequestXML);
		System.out.println("Replace...." + tiRequestXML);

	
		 // BOCCommonUtil.insertTIReqToLogTable(tiRequestXML,
		 // Constants.BALANCE_CHECK_STAGING_TABLE, Constants.STATUS_RECEIVED, pkey);
		 
		aBackOfficeVerifyTIRequestModel = setTIReqModelFromTIReqXML(tiRequestXML);

		System.out.println("Exited from  setTIReqModelFromTIReqXML....");

		
//		  BOCCommonUtil.updateReferenceinLog(
//		  aBackOfficeVerifyTIRequestModel.getBackOfficeVerifyTIReqModel().get(0).
//		 getMasterReference(),
//		  aBackOfficeVerifyTIRequestModel.getBackOfficeVerifyTIReqModel().get(0).
//		  getEventReference(), Constants.BALANCE_CHECK_STAGING_TABLE, pkey);
//		  System.out.println("Exited from  updateReferenceinLog....");
//		 
		aBackOfficeVerifyBankRequestModel = setBankReqModelFromTIReqModel(aBackOfficeVerifyTIRequestModel);

		System.out.println("Exited from  BackOfficeVerifyBankRequestModel....");

		aBackOfficeVerifyBankResponseModel = callAPIEndPoint(aBackOfficeVerifyBankRequestModel);

		System.out.println("Exited into   callAPIEndPoint....");

		aBackOfficeVerifyTIResponseModel = setTIResModelFromBankResModel(aBackOfficeVerifyBankResponseModel,
				aBackOfficeVerifyBankRequestModel,
				aBackOfficeVerifyTIRequestModel.getBackOfficeVerifyTIReqModel().get(0).getMasterReference(),
				aBackOfficeVerifyTIRequestModel.getBackOfficeVerifyTIReqModel().get(0).getEventReference());
		tiResponseXML = constructTIResponseXML(aBackOfficeVerifyTIResponseModel, tiRequestXML);
		System.out.println(tiResponseXML);
		Loggers.general().info(LOG, "TI Response XML \n" + tiResponseXML);
		
//	  BOCCommonUtil.updateTIResToLogTable(tiResponseXML,
//	  Constants.BALANCE_CHECK_STAGING_TABLE, StatusEnum.SUCCEEDED.toString(),
//	  pkey);
	 
		return tiResponseXML;

	}

	private String constructTIResponseXML(BackOfficeVerifyTIResponseModel aBackOfficeVerifyTIResponseModel,
			String tiRequestXML) {

		List<String> error = new LinkedList<>();
		List<String> warning = new LinkedList<>();
		ByteArrayOutputStream outStream = null;
		String status = null;

		for (BackOfficeVerifyTIResModel aBackOfficeVerifyTIResModel : aBackOfficeVerifyTIResponseModel
				.getBackOfficeVerifyTIResModel()) {

			if (aBackOfficeVerifyTIResModel.getStatus().equalsIgnoreCase(Constants.STATUS_FAILED)) {
				if (aBackOfficeVerifyTIResModel.getSeverity().equalsIgnoreCase(Constants.ERROR)) {
					error.add(aBackOfficeVerifyTIResModel.getDetails());
				} else if (aBackOfficeVerifyTIResModel.getSeverity().equalsIgnoreCase(Constants.WARNING)) {
					warning.add(aBackOfficeVerifyTIResModel.getDetails());
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		if (error.isEmpty() && warning.isEmpty()) {
			status = Constants.STATUS_SUCCEESS;
		} else {
			status = Constants.STATUS_FAILED;
		}
		InputStream inStream = null;
		try {
			inStream = new ByteArrayInputStream(tiRequestXML.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		Unmarshaller unmarshaller;
		try {
			unmarshaller = context.createUnmarshaller();
			ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
			com.misys.tiplus2.apps.ti.service.messages.ObjectFactory of = new com.misys.tiplus2.apps.ti.service.messages.ObjectFactory();
			ServiceResponse serviceResponse = new ServiceResponse();
			serviceResponse
					.setResponseHeader(BOCCommonUtil.getHeader(serviceRequest.getRequestHeader().getCorrelationId(),
							serviceRequest.getRequestHeader().getOperation(),
							serviceRequest.getRequestHeader().getService(), sb.toString(), status, error, warning));
			List<JAXBElement<?>> batchRequestList = serviceRequest.getRequest();
			@SuppressWarnings("unchecked")
			JAXBElement<BulkServiceRequest> bsr = (JAXBElement<BulkServiceRequest>) batchRequestList.get(0);
			List<ServiceRequest> bsrlist = bsr.getValue().getServiceRequest();
			List<JAXBElement<?>> sres = serviceResponse.getResponse();
			BulkServiceResponse localResponse = new BulkServiceResponse();
			List<ServiceResponse> batchResponse = localResponse.getServiceResponse();
			for (ServiceRequest sr : bsrlist) {
				ServiceResponse bulkServiceResponse = new ServiceResponse();
				bulkServiceResponse.setResponseHeader(BOCCommonUtil.getHeader(sr.getRequestHeader().getCorrelationId(),
						sr.getRequestHeader().getOperation(), sr.getRequestHeader().getService(), sb.toString(), status,
						error, warning));
				batchResponse.add(bulkServiceResponse);
			}
			sres.add(of.createVerifyResponse(localResponse));
			JAXBContext jaxbContext = BOCCommonUtil.getJAXBTIContext();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			outStream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(serviceResponse, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outStream.toString();
	}

	private BackOfficeVerifyTIResponseModel setTIResModelFromBankResModel(
			BackOfficeVerifyBankResponseModel aBackOfficeVerifyBankResponseModel,
			BackOfficeVerifyBankRequestModel aBackOfficeVerifyBankRequestModel, String MasterRef, String EventRef) {
		System.out.println("Entered into  setTIResModelFromBankResModel--> ");
		Loggers.general().info(LOG, "Entered into setTIResModelFromBankResModel ");
		BackOfficeVerifyTIResponseModel aBackOfficeVerifyTIResponseModel = new BackOfficeVerifyTIResponseModel();
		List<BackOfficeVerifyTIResModel> BackOfficeVerifyTIResList = new LinkedList<BackOfficeVerifyTIResModel>();
		boolean AccStatus = false;
		boolean isBalAvail = false;
		String zone = BOCCommonUtil.retreiveFromProperties(Constants.ZONEID);
		int i = 0;

		getProdEvent(MasterRef, EventRef);
		System.out.println("master code:" + masterCode + " " + "event code:" + eventCode);
		if (!CommonUtil.NullStringCheck(masterCode) && !CommonUtil.NullStringCheck(eventCode)) {
			if ((masterCode.equalsIgnoreCase("ILC") && eventCode.equalsIgnoreCase("POC"))
					|| (masterCode.equalsIgnoreCase("IGT") && eventCode.equalsIgnoreCase("POC"))
					|| (masterCode.equalsIgnoreCase("IDC") && eventCode.equalsIgnoreCase("PAY"))
					|| (masterCode.equalsIgnoreCase("FSA") && eventCode.equalsIgnoreCase("CRE"))
					|| (masterCode.equalsIgnoreCase("FIC") && eventCode.equalsIgnoreCase("CRE"))
					|| (masterCode.equalsIgnoreCase("FIL") && eventCode.equalsIgnoreCase("CRE"))) {
				System.out.println("inside the product and event condition");
				aBackOfficeVerifyBankRequestModel = getCorrectAmtforUnnetted(aBackOfficeVerifyBankRequestModel);
			}
		}
		for (BackOfficeVerifyBankResModel aBackOfficeVerifyBankResModel : aBackOfficeVerifyBankResponseModel
				.getBackOfficeVerifyBankResModel()) {

			BackOfficeVerifyTIResModel aBackOfficeVerifyTIResModel = new BackOfficeVerifyTIResModel();
			List<BackOfficeVerifyBankReqModel> aBackOfficeVerifyBankReqModel = aBackOfficeVerifyBankRequestModel
					.getBackOfficeVerifyBankReqModel();

			Loggers.general().info(LOG, "Amounts to compare " + aBackOfficeVerifyBankReqModel.get(i).getPostAmount()
					+ "\n" + aBackOfficeVerifyBankResModel.getAvailBalanceAmt());

			System.out.println("Amounts to compare " + aBackOfficeVerifyBankReqModel.get(i).getPostAmount() + "\n"
					+ aBackOfficeVerifyBankResModel.getAvailBalanceAmt());

			if (aBackOfficeVerifyBankResModel.getAccountStatus().equalsIgnoreCase(Constants.OPEN_ACCOUNT)) {
				AccStatus = true;
				System.out.println("CrdFlag Check");
				Loggers.general().info(LOG, "Entered into Credit Posting check ");
				Loggers.general().info(LOG, "Credit Flag " + aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag());
				System.out.println("Credit Flag " + aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag());
				String CrFlag = aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag();
				System.out.println("Crflag" + CrFlag);

				if (aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag().equals("C")) {
					isBalAvail = true;
					System.out.println("CrdFlag True");
				}
			}
			System.out.println(aBackOfficeVerifyBankReqModel.get(i).getPostAmount().abs()
					.compareTo(aBackOfficeVerifyBankResModel.getAvailBalanceAmt()));
			if (AccStatus && aBackOfficeVerifyBankReqModel.get(i).getPostAmount().abs()
					.compareTo(aBackOfficeVerifyBankResModel.getAvailBalanceAmt()) <= 0) {
				isBalAvail = true;
			}
			String Acctyp = aBackOfficeVerifyBankReqModel.get(i).getAcctType();
			if (AccStatus && isBalAvail) {
				System.out.println("--------------1");
				aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_SUCCEEDED);
			} else if (AccStatus && !isBalAvail) {
				Loggers.general().info(LOG, "Acc Type " + aBackOfficeVerifyBankReqModel.get(i).getAcctType());
				Loggers.general().info(LOG, "Post CCy " + aBackOfficeVerifyBankReqModel.get(i).getPostCcy());
				System.out.println("Acc Type " + aBackOfficeVerifyBankReqModel.get(i).getAcctType());
				System.out.println("Post CCy " + aBackOfficeVerifyBankReqModel.get(i).getPostCcy());
				if (aBackOfficeVerifyBankReqModel.get(i).getAcctType()
						.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.CURRENT_ACCOUNT))
						&& aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
								.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY))) {
					if (aBackOfficeVerifyBankReqModel.get(i).getPostAmount().compareTo(BigDecimal.ZERO) > 0) {
						aBackOfficeVerifyTIResModel.setSeverity(Constants.WARNING);
						aBackOfficeVerifyTIResModel.setDetails("Balance is not available in Account Number "
								+ aBackOfficeVerifyBankReqModel.get(i).getAcctId());
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_FAILED);
					} else {
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_SUCCEEDED);
					}
					
					// N-Able Pvt LTD / 20-02-2023
					// BOCS validation added with Seychelles implementation.
					// Same BOCM quiery used as per the given instructions by BOC.
					
				} else if ((zone.equals("BOCM") && aBackOfficeVerifyBankReqModel.get(i).getAcctType()
						.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.CURRENT_ACCOUNT))&&(aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
                                .equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY))||aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
                                .equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties("USD")))) || 
						(zone.equals("BOCS") && aBackOfficeVerifyBankReqModel.get(i).getAcctType()
                						.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.CURRENT_ACCOUNT))&&(aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
                                                .equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY))||aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
                                                .equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties("USD"))))) {
					System.out.println("Zone 3 Balence Check");
					if (aBackOfficeVerifyBankReqModel.get(i).getPostAmount().compareTo(BigDecimal.ZERO) > 0) {
						aBackOfficeVerifyTIResModel.setSeverity(Constants.WARNING);
						aBackOfficeVerifyTIResModel.setDetails("Balance is not available in Account Number "
								+ aBackOfficeVerifyBankReqModel.get(i).getAcctId());
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_FAILED);
					} else {
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_SUCCEEDED);
					}

				} else if (zone.equals("BOCA") && aBackOfficeVerifyBankReqModel.get(i).getAcctType()
						.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.CURRENT_ACCOUNT))) {

					System.out.println("Zone 2 Balence Check");
					if (aBackOfficeVerifyBankReqModel.get(i).getPostAmount().compareTo(BigDecimal.ZERO) > 0) {
						aBackOfficeVerifyTIResModel.setSeverity(Constants.WARNING);
						aBackOfficeVerifyTIResModel.setDetails("Balance is not available in Account Number "
								+ aBackOfficeVerifyBankReqModel.get(i).getAcctId());
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_FAILED);
					} else {
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_SUCCEEDED);
					}

				} else {
					System.out.println("Debit Amount "
							+ aBackOfficeVerifyBankReqModel.get(i).getPostAmount().compareTo(BigDecimal.ZERO));
					if (aBackOfficeVerifyBankReqModel.get(i).getPostAmount().compareTo(BigDecimal.ZERO) > 0) {
						System.out.println("Else  Balence Check");
						aBackOfficeVerifyTIResModel.setSeverity(Constants.ERROR);
						aBackOfficeVerifyTIResModel.setDetails("Balance is not available in Account Number "
								+ aBackOfficeVerifyBankReqModel.get(i).getAcctId());
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_FAILED);
					} else {
						aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_SUCCEEDED);
					}
				}
			} else {
				aBackOfficeVerifyTIResModel.setSeverity(Constants.ERROR);
				aBackOfficeVerifyTIResModel
						.setDetails("The Account Status is " + aBackOfficeVerifyBankResModel.getAccountStatus());
				aBackOfficeVerifyTIResModel.setStatus(Constants.STATUS_FAILED);
			}
			BackOfficeVerifyTIResList.add(aBackOfficeVerifyTIResModel);
			i++;
			AccStatus = false;
			isBalAvail = false;
		}

		aBackOfficeVerifyTIResponseModel.setBackOfficeVerifyTIResModel(BackOfficeVerifyTIResList);

		return aBackOfficeVerifyTIResponseModel;
	}

	private void getProdEvent(String masterRef, String eventRef) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.GET_MAS_EVE_CODE);
			pst.setString(1, masterRef);
			pst.setString(2, eventRef);

			rst = pst.executeQuery();
			if (rst.next()) {
				masterCode = rst.getString("mas_code");
				eventCode = rst.getString("event_code");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public BackOfficeVerifyBankRequestModel getCorrectAmtforUnnetted(
			BackOfficeVerifyBankRequestModel aBackOfficeVerifyBankRequestModel) {
		List<BackOfficeVerifyBankReqModel> aBackOfficeVerifyBankReqModel = aBackOfficeVerifyBankRequestModel
				.getBackOfficeVerifyBankReqModel();
		List<BackOfficeVerifyBankReqModel> backOfficeVerifyBankList = new LinkedList<BackOfficeVerifyBankReqModel>();
		System.out.println("entered into getamount" + aBackOfficeVerifyBankReqModel.size());
		try {
			for (int i = 0; i < aBackOfficeVerifyBankReqModel.size(); i++) {
				System.out.println("i value" + i);
				BigDecimal totAmt = BigDecimal.ZERO;
				boolean sameAmountFlag = false;
				BackOfficeVerifyBankReqModel bankRequestModel = new BackOfficeVerifyBankReqModel();
				for (int j = 0; j < aBackOfficeVerifyBankReqModel.size(); j++) {
					System.out.println("j value" + j);
					System.out.println("get i value" + aBackOfficeVerifyBankReqModel.get(i).getAcctId());
					System.out.println("get j value" + aBackOfficeVerifyBankReqModel.get(j).getAcctId());
					System.out.println("amount i:" + aBackOfficeVerifyBankReqModel.get(i).getPostAmount());
					System.out.println("amount j:" + aBackOfficeVerifyBankReqModel.get(j).getPostAmount());
					System.out.println("debit/cr i flag:" + aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag());
					System.out.println("credit/dr j flag:" + aBackOfficeVerifyBankReqModel.get(j).getDebitCreditFlag());
					if (aBackOfficeVerifyBankReqModel.get(i).getAcctId()
							.equals(aBackOfficeVerifyBankReqModel.get(j).getAcctId()) && i != j) {
						System.out.println("inside first if");
						if (aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag()
								.equals(aBackOfficeVerifyBankReqModel.get(j).getDebitCreditFlag())) {
							System.out.println("inside second if");
							if(totAmt.compareTo(BigDecimal.ZERO) == 0) {
								totAmt = aBackOfficeVerifyBankReqModel.get(i).getPostAmount()
								.add(aBackOfficeVerifyBankReqModel.get(j).getPostAmount());
								}else {
								totAmt = totAmt.add(aBackOfficeVerifyBankReqModel.get(j).getPostAmount());
								}
							System.out.println("total amount:" + totAmt);
						} else {
							System.out.println("inside else");

							if (aBackOfficeVerifyBankReqModel.get(i).getPostAmount()
									.compareTo(aBackOfficeVerifyBankReqModel.get(j).getPostAmount()) != 0) {
						
								if(totAmt.compareTo(BigDecimal.ZERO) == 0) {
								totAmt = aBackOfficeVerifyBankReqModel.get(i).getPostAmount()
								.subtract(aBackOfficeVerifyBankReqModel.get(j).getPostAmount());
								}
								else {
								totAmt =totAmt.subtract(aBackOfficeVerifyBankReqModel.get(j).getPostAmount());
								}
								
							} else {
								System.out.println("inside the outer else");
								totAmt = BigDecimal.ZERO;
								//sameAmountFlag = true;
							}
						if(totAmt.compareTo(BigDecimal.ZERO) == 0) {
							sameAmountFlag = true;
							}
							System.out.println("total amount:" + totAmt);
						}

					}

				}
				System.out.println(" final flag:" +sameAmountFlag );
				System.out.println(" final total amount:" + totAmt);
				System.out.println("totAmt " + totAmt.compareTo(BigDecimal.ZERO));
				if (totAmt.compareTo(BigDecimal.ZERO) != 0) {
					bankRequestModel.setPostAmount(totAmt);
				} else if (sameAmountFlag && totAmt.compareTo(BigDecimal.ZERO) == 0) {
					bankRequestModel.setPostAmount(totAmt);
				} else {
					bankRequestModel.setPostAmount(aBackOfficeVerifyBankReqModel.get(i).getPostAmount());
				}
				bankRequestModel.setAcctId(aBackOfficeVerifyBankReqModel.get(i).getAcctId());
				bankRequestModel.setAcctType(aBackOfficeVerifyBankReqModel.get(i).getAcctType());
				bankRequestModel.setPostCcy(aBackOfficeVerifyBankReqModel.get(i).getPostCcy());
				bankRequestModel.setDay(aBackOfficeVerifyBankReqModel.get(i).getDay());
				bankRequestModel.setMonth(aBackOfficeVerifyBankReqModel.get(i).getMonth());
				bankRequestModel.setYear(aBackOfficeVerifyBankReqModel.get(i).getYear());
				bankRequestModel.setRqUID(aBackOfficeVerifyBankReqModel.get(i).getRqUID());
				bankRequestModel.setDebitCreditFlag(aBackOfficeVerifyBankReqModel.get(i).getDebitCreditFlag());
				backOfficeVerifyBankList.add(bankRequestModel);
				/*
				 * if (totAmt.compareTo(BigDecimal.ZERO) != 0) {
				 * bankRequestModel.setAcctId(aBackOfficeVerifyBankReqModel.get(i).getAcctId());
				 * bankRequestModel.setAcctType(aBackOfficeVerifyBankReqModel.get(i).getAcctType
				 * ());
				 * bankRequestModel.setPostCcy(aBackOfficeVerifyBankReqModel.get(i).getPostCcy()
				 * ); bankRequestModel.setDay(aBackOfficeVerifyBankReqModel.get(i).getDay());
				 * bankRequestModel.setMonth(aBackOfficeVerifyBankReqModel.get(i).getMonth());
				 * bankRequestModel.setYear(aBackOfficeVerifyBankReqModel.get(i).getYear());
				 * bankRequestModel.setRqUID(aBackOfficeVerifyBankReqModel.get(i).getRqUID());
				 * bankRequestModel.setDebitCreditFlag(aBackOfficeVerifyBankReqModel.get(i).
				 * getDebitCreditFlag()); bankRequestModel.setPostAmount(totAmt);
				 * backOfficeVerifyBankList.add(bankRequestModel); }
				 * 
				 * System.out.println("done with get amount");
				 */

			}
			System.out.println("bankreqmodel" + backOfficeVerifyBankList.size());
			aBackOfficeVerifyBankRequestModel.setBackOfficeVerifyBankReqModel(backOfficeVerifyBankList);

		} catch (Exception e) {
			Loggers.general().error(LOG, e.getMessage());
			;
			e.printStackTrace();
		}
		System.out.println("returning from getamount");

		return aBackOfficeVerifyBankRequestModel;
	}

	public String stripNonValidXMLCharacters(String in) {
		StringBuffer out = new StringBuffer(); // Used to hold the output.
		char current; // Used to reference the current character.

		if (in == null || ("".equals(in)))
			return ""; // vacancy test.GET
		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	private BackOfficeVerifyBankResponseModel callAPIEndPoint(
			BackOfficeVerifyBankRequestModel aBackOfficeVerifyBankRequestModel) {
		System.out.println("Entered into   callAPIEndPoint....");

		String bankRequestXML = null;
		String bankResponseXML = null;

		BackOfficeVerifyBankResponseModel aBackOfficeVerifyBankResponseModel = new BackOfficeVerifyBankResponseModel();
		List<BackOfficeVerifyBankResModel> BackOfficeVerifyBankResList = new LinkedList<BackOfficeVerifyBankResModel>();

		for (BackOfficeVerifyBankReqModel br : aBackOfficeVerifyBankRequestModel.getBackOfficeVerifyBankReqModel()) {

			BackOfficeVerifyBankResModel aBackOfficeVerifyBankResModel = new BackOfficeVerifyBankResModel();

			if (!CommonUtil.NullStringCheck(br.getAcctType())) {
				bankRequestXML = generateBalanceCheckRequest(br);
				Loggers.general().info(LOG, "Bank Request XML --> " + bankRequestXML);
				System.out.println("Bank Request XML --> " + bankRequestXML);

				try {
					//bankResponseXML = BOCCommonUtil.callCoreBankingWebService(bankRequestXML);
					bankResponseXML = ReadFileUtility.ConvertXmltoString(
							"D:\\BOC-Workspace\\InterfaceTransportClient\\src\\com\\ett\\backofficeverify\\BankResponse.xml");
					Loggers.general().info(LOG, "Bank Response XML --> " + bankResponseXML);
					System.out.println("Bank Response XML --> " + bankResponseXML);
				} catch (Exception e) {
					e.printStackTrace();
				}

				aBackOfficeVerifyBankResModel = parseBankResponseXML(bankResponseXML);
				BackOfficeVerifyBankResList.add(aBackOfficeVerifyBankResModel);
			}
		}

		aBackOfficeVerifyBankResponseModel.setBackOfficeVerifyBankResModel(BackOfficeVerifyBankResList);

		return aBackOfficeVerifyBankResponseModel;
	}

	private BackOfficeVerifyBankRequestModel setBankReqModelFromTIReqModel(
			BackOfficeVerifyTIRequestModel aBackOfficeVerifyTIRequestModel) {

		System.out.println("Entered into BackOfficeVerifyBankRequestModel ....");

		BackOfficeVerifyBankRequestModel aBackOfficeVerifyBankRequestModel = new BackOfficeVerifyBankRequestModel();
		List<BackOfficeVerifyBankReqModel> BackOfficeVerifyBankList = new LinkedList<BackOfficeVerifyBankReqModel>();

		for (BackOfficeVerifyTIReqModel bl : aBackOfficeVerifyTIRequestModel.getBackOfficeVerifyTIReqModel()) {
			boolean isCustomerAccount = BOCCommonUtil.CustomerAccountCheck(bl.getAccountType());

			if (isCustomerAccount) {
				BackOfficeVerifyBankReqModel aBackOfficeVerifyBankReqModel = new BackOfficeVerifyBankReqModel();
				aBackOfficeVerifyBankReqModel.setAcctId(bl.getBackOfficeAccountNo());
				aBackOfficeVerifyBankReqModel.setAcctType(BOCCommonUtil.AccountTypeTranslate(bl.getAccountType()));
				aBackOfficeVerifyBankReqModel.setPostAmount(bl.getPostingAmount());
				aBackOfficeVerifyBankReqModel.setPostCcy(bl.getPostingCcy());
				aBackOfficeVerifyBankReqModel.setDay(CommonUtil.getSysDate("dd"));
				aBackOfficeVerifyBankReqModel.setMonth(CommonUtil.getSysDate("MM"));
				aBackOfficeVerifyBankReqModel.setYear(CommonUtil.getSysDate("yyyy"));
				aBackOfficeVerifyBankReqModel.setRqUID(CommonUtil.generateCorrelationId());
				aBackOfficeVerifyBankReqModel.setDebitCreditFlag(bl.getDebitCreditFlag());
				BackOfficeVerifyBankList.add(aBackOfficeVerifyBankReqModel);

			}

		}

		aBackOfficeVerifyBankRequestModel.setBackOfficeVerifyBankReqModel(BackOfficeVerifyBankList);

		return aBackOfficeVerifyBankRequestModel;

	}

	private BackOfficeVerifyTIRequestModel setTIReqModelFromTIReqXML(String tiRequestXML) {

		System.out.println("Entered into setTIReqModelFromTIReqXML....");

		BackOfficeVerifyTIRequestModel aBackOfficeVerifyTIRequestModel = new BackOfficeVerifyTIRequestModel();
		List<BackOfficeVerifyTIReqModel> BackOfficeVerifyTIList = new LinkedList<BackOfficeVerifyTIReqModel>();

		try {
			System.out.println("Entered into tryblock-->setTIReqModelFromTIReqXML....");

			List<ServiceRequest> bsrList = BOCCommonUtil.extractPostingEntries(tiRequestXML, Constants.BO_VERIFY);
			System.out.println("Exited into extractPostingEntries for balanceverify....");
			Posting posting = null;
			for (ServiceRequest sr : bsrList) {
				System.out.println("Entered into forloop for BackOfficeVerifyTIRequestModel....");
				try {
					List<JAXBElement<?>> serviceList = sr.getRequest();

					JAXBElement<?> postingJAXB = (JAXBElement<?>) serviceList.get(0);
					if (postingJAXB.getValue().toString().contains("Posting")) {
						posting = (Posting) postingJAXB.getValue();

						BackOfficeVerifyTIReqModel aBackOfficeVerifyTIReqModel = new BackOfficeVerifyTIReqModel();

						Loggers.general().info(LOG, "posting.getAccountType()" + posting.getAccountType());
						Loggers.general().info(LOG,
								"posting.getBackOfficeAccountNo()" + posting.getBackOfficeAccountNo());
						Loggers.general().info(LOG, "posting.getPostingAmount()" + posting.getPostingAmount());
						Loggers.general().info(LOG, "posting.getPostingCcy()" + posting.getPostingCcy());
						aBackOfficeVerifyTIReqModel.setAccountType(posting.getAccountType());
						aBackOfficeVerifyTIReqModel.setBackOfficeAccountNo(posting.getBackOfficeAccountNo());
						aBackOfficeVerifyTIReqModel.setPostingAmount(CommonUtil
								.convertTIAmount(new BigDecimal(posting.getPostingAmount()), posting.getPostingCcy()));
						aBackOfficeVerifyTIReqModel.setPostingCcy(posting.getPostingCcy());
						aBackOfficeVerifyTIReqModel.setMasterReference(posting.getMasterReference());
						aBackOfficeVerifyTIReqModel.setEventReference(posting.getEventReference());
						aBackOfficeVerifyTIReqModel.setDebitCreditFlag(posting.getDebitCreditFlag());
						BackOfficeVerifyTIList.add(aBackOfficeVerifyTIReqModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			aBackOfficeVerifyTIRequestModel.setBackOfficeVerifyTIReqModel(BackOfficeVerifyTIList);
			System.out.println("Exited into forloop for BackOfficeVerifyTIRequestModel....");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return aBackOfficeVerifyTIRequestModel;

	}

	private String generateBalanceCheckRequest(BackOfficeVerifyBankReqModel aBackOfficeVerifyBankReqModel) {

		System.out.println("Entered into generateBalanceCheckRequest....");

		StringWriter result = null;
		String bankRequestXML = null;
		IFX obj = new IFX();
		BankSvcRq bankSvc = new BankSvcRq();
		bankSvc.setRqUID(aBackOfficeVerifyBankReqModel.getRqUID());
		bankSvc.setSPName(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_SPNAME));

		AcctInqRq accInq = new AcctInqRq();
		accInq.setIncBal((byte) 1);
		accInq.setIncExtBal((byte) 1);
		accInq.setRqUID(aBackOfficeVerifyBankReqModel.getRqUID());

		CustId cusId = new CustId();
		cusId.setSPName(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_SPNAME));
		accInq.setCustId(cusId);

		DepAcctId depAccId = new DepAcctId();
		depAccId.setAcctId(aBackOfficeVerifyBankReqModel.getAcctId());
		depAccId.setAcctType(aBackOfficeVerifyBankReqModel.getAcctType());
		accInq.setDepAcctId(depAccId);
		bankSvc.setAcctInqRq(accInq);
		obj.setBankSvcRq(bankSvc);

		EnvironmentInfo env = new EnvironmentInfo();
		env.setEnvironmentName(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_ENVNAME));
		obj.setEnvironmentInfo(env);

		SignonRq sgnRq = new SignonRq();
		sgnRq.setComputerId(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_COMPUTERID));
		sgnRq.setInstitutionCode(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_INSTITUTECODE));
		SignonPswd sgnpswd = new SignonPswd();
		sgnpswd.setSignonRole(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_SIGNONROLE));
		sgnpswd.setGenSessKey((byte) 0);

		com.boc.accountbalancerequest.IFX.SignonRq.SignonPswd.CustId custid = new com.boc.accountbalancerequest.IFX.SignonRq.SignonPswd.CustId();
		custid.setCustLoginId(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_USERNAME));
		custid.setSPName(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_SPNAME));
		sgnpswd.setCustId(custid);

		CustPswd cuspwd = new CustPswd();
		cuspwd.setCryptType("NONE");
		cuspwd.setPswd(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_PASSWORD));
		sgnpswd.setCustPswd(cuspwd);
		sgnRq.setSignonPswd(sgnpswd);

		ClientDt cltDt = new ClientDt();
		cltDt.setDay(aBackOfficeVerifyBankReqModel.getDay());
		cltDt.setMonth(aBackOfficeVerifyBankReqModel.getMonth());
		cltDt.setYear(aBackOfficeVerifyBankReqModel.getYear());
		sgnRq.setClientDt(cltDt);

		ClientApp clapp = new ClientApp();
		clapp.setClientAppKey(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_CLIENTAPP_KEY));
		clapp.setVersion((float) 1.0);
		clapp.setName(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_NAME));
		clapp.setOrg(BOCCommonUtil.retreiveFromProperties(Constants.COREBANKING_ORG));
		sgnRq.setClientApp(clapp);
		obj.setSignonRq(sgnRq);

		try {

			result = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(IFX.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(obj, result);

			Loggers.general().info(LOG, "XML to Bank" + result.toString());

			bankRequestXML = result.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return bankRequestXML;

	}

	private BackOfficeVerifyBankResModel parseBankResponseXML(String bankResponseXML) {

		BackOfficeVerifyBankResModel aBackOfficeVerifyBankResModel = new BackOfficeVerifyBankResModel();
		com.boc.accountbalanceresponse.IFX responseParsed = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.boc.accountbalanceresponse");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			responseParsed = (com.boc.accountbalanceresponse.IFX) jaxbUnmarshaller
					.unmarshal(new StringReader(bankResponseXML));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().size(); i++) {
			if (responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(i).getBalType()
					.equalsIgnoreCase("Avail")) {
				aBackOfficeVerifyBankResModel.setAvailBalanceAmt(new BigDecimal(
						responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(i).getCurAmt().getAmt()));
				aBackOfficeVerifyBankResModel.setAvailBalanceCcy(
						responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(i).getCurAmt().getCurCode());
				Loggers.general().info(LOG, "Parsed Values  "
						+ responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(i).getCurAmt().getAmt() + "\n"
						+ responseParsed.getBankSvcRs().getAcctInqRs().getAcctBal().get(i).getCurAmt().getCurCode());
			}
		}

		aBackOfficeVerifyBankResModel.setAccountStatus(responseParsed.getBankSvcRs().getAcctInqRs().getAcctStatCode());

		return aBackOfficeVerifyBankResModel;
	}

	public static void main(String[] args) {
		BackOfficeVerifyImplLocal b = new BackOfficeVerifyImplLocal();
		String tirequest = ReadFileUtility.ConvertXmltoString(
				"D:\\BOC-Workspace\\InterfaceTransportClient\\src\\com\\ett\\backofficeverify\\FtiReqXml.xml");
		b.doBalanceCheck(tirequest);
	}
}
