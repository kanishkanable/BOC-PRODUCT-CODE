package com.ett.backofficebatch;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

import com.ett.limitupdate.LimitImpl;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.ett.util.QueryConstants;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceRequest;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceResponse;
import com.misys.tiplus2.apps.ti.service.messages.Posting;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceResponse;
import com.misys.tiplus2.services.control.StatusEnum;

public class BackOfficeBatchImpl {
	private String productCode = "";
	private String eventCode = "";

	public String createPostings(String tiRequestXML) {

		String pkey = null;
		String tiResponseXML = null;

		BackOfficeBatchTIRequestModel aBackOfficeBatchTIRequestModel = new BackOfficeBatchTIRequestModel();
		BackOfficeBatchBankRequestModel aBackOfficeBatchBankRequestModel = new BackOfficeBatchBankRequestModel();

		pkey = BOCCommonUtil.getNextSeqNo(Constants.BACKOFFICE_BATCH_SEQUENCE);

		BOCCommonUtil.insertTIReqToLogTable(tiRequestXML, Constants.BACKOFFICE_BATCH_STAGING_TABLE,
				Constants.STATUS_RECEIVED, pkey);

		aBackOfficeBatchTIRequestModel = setTIReqModelFromTIReqXML(tiRequestXML);

		// added by mani
		System.out.println("setTIReqModelFromTIReqXML....");
		// added by mani

		aBackOfficeBatchBankRequestModel = setBankReqModelFromTIReqModel(aBackOfficeBatchTIRequestModel);
		// added by mani
		System.out.println("setBankReqModelFromTIReqModel....");
		// added by mani
		setOutputtoFile(aBackOfficeBatchBankRequestModel, pkey);
		// added by mani
		System.out.println("setOutputtoFile....");
		// added by mani

		// newlyadded
//		aBackOfficeBatchTIRequestModel = setTIReqModelFromTIReqXML(tiRequestXML);
		BOCCommonUtil.updateReferenceinLog(
				aBackOfficeBatchTIRequestModel.getBackOfficeBatchTIReqModel().get(0).getMasterReference(),
				aBackOfficeBatchTIRequestModel.getBackOfficeBatchTIReqModel().get(0).getEventReference(),
				Constants.BACKOFFICE_BATCH_STAGING_TABLE, pkey);
		// newlyadded
		tiResponseXML = constructTIResponseXML(tiRequestXML);
		BOCCommonUtil.updateTIResToLogTable(tiResponseXML, Constants.BACKOFFICE_BATCH_STAGING_TABLE,
				StatusEnum.SUCCEEDED.toString(), pkey);
		return tiResponseXML;

	}

	private String constructTIResponseXML(String tiRequestXML) {

		List<String> error = new LinkedList<>();
		List<String> warning = new LinkedList<>();
		ByteArrayOutputStream outStream = null;

		StringBuilder sb = new StringBuilder();

		InputStream inStream = new ByteArrayInputStream(tiRequestXML.getBytes());
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		Unmarshaller unmarshaller;
		try {
			unmarshaller = context.createUnmarshaller();

			ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
			com.misys.tiplus2.apps.ti.service.messages.ObjectFactory of = new com.misys.tiplus2.apps.ti.service.messages.ObjectFactory();
			ServiceResponse serviceResponse = new ServiceResponse();
			serviceResponse.setResponseHeader(BOCCommonUtil.getHeader(
					serviceRequest.getRequestHeader().getCorrelationId(),
					serviceRequest.getRequestHeader().getOperation(), serviceRequest.getRequestHeader().getService(),
					sb.toString(), Constants.STATUS_SUCCEESS, error, warning));
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
						sr.getRequestHeader().getOperation(), sr.getRequestHeader().getService(), sb.toString(),
						Constants.STATUS_SUCCEESS, error, warning));
				batchResponse.add(bulkServiceResponse);
			}
			sres.add(of.createBatchResponse(localResponse));
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

	/*
	 * private void setOutputtoFile(BackOfficeBatchBankRequestModel
	 * aBackOfficeBatchBankRequestModel, String pkey) {
	 * 
	 * File file = new
	 * File(BOCCommonUtil.retreiveFromProperties(Constants.POSTING_OUTPUT_PATH) +
	 * aBackOfficeBatchBankRequestModel.getFileName());
	 * 
	 * //added by mani System.out.println("SET POSTING_OUTPUT_PATH  ...."); //added
	 * by mani int account;
	 * 
	 * 
	 * 
	 * Writer br = null; String dataWithNewLine =
	 * System.getProperty("line.separator"); try {
	 * 
	 * br = new BufferedWriter(new FileWriter(file, false));
	 * 
	 * 
	 * for (BackOfficeBatchBankReqModel brl :
	 * aBackOfficeBatchBankRequestModel.getBackOfficeBatchBankReqModel()) {
	 * 
	 * sb.append(Constants.SPACE);
	 * sb.append(StringUtils.rightPad(brl.getBranchNumber(), 4));
	 * sb.append(brl.getDebitCreditCode());
	 * 
	 * System.out.println("effectivedatewriteinfile---"+brl.getEffectiveDate());
	 * sb.append(brl.getEffectiveDate());
	 * 
	 * // String effecdate = brl.getEffectiveDate().substring(0,
	 * brl.getEffectiveDate().length() - 2); //
	 * System.out.println("effectivedatewriteinfile---"+effecdate); //
	 * sb.append(effecdate);
	 * 
	 * sb.append(brl.getAccountType());
	 * System.out.println("accountypewriteinfile...."+brl.getAccountType());
	 * account=brl.getAccountNumber().indexOf("-");
	 * System.out.println("BOAccountnumberindex  ...."+account); String
	 * bo_accountno=brl.getAccountNumber().substring(0,account);
	 * System.out.println("BOAccountnumber  ...."+bo_accountno);
	 * sb.append(StringUtils.rightPad(bo_accountno,12, Constants.SPACE)); //
	 * sb.append(StringUtils.rightPad(brl.getAccountNumber().endsWith(),12,
	 * Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getTransactionAmount().toString(), 20,
	 * Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getLCEAmount().toString(), 20,
	 * Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getForeignCurrencyCode(), 3,
	 * Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getForeignCurrencyRate().toString(), 23,
	 * Constants.SPACE)); sb.append(brl.getReferenceNumber());
	 * sb.append(StringUtils.rightPad(brl.getAlphabeticData1(), 40,
	 * Constants.SPACE)); sb.append(StringUtils.rightPad(brl.getAlphabeticData2(),
	 * 40, Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getAlphabeticData3(), 40,
	 * Constants.SPACE)); sb.append(brl.getTransactionTime());
	 * sb.append(StringUtils.rightPad(brl.getCIFKey(), 10, Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getMadeBy(), 3, Constants.SPACE));
	 * sb.append(StringUtils.rightPad(brl.getApprovedBy(), 3, Constants.SPACE));
	 * sb.append(brl.getAdditionalFCYInfo()); sb.append(brl.getOriginationInfo());
	 * sb.append(brl.getForeignCurrencyOverride());
	 * sb.append(StringUtils.rightPad(brl.getApplicationTranCode(), 2,
	 * Constants.SPACE)); sb.append(StringUtils.rightPad(brl.getAuthorisedUserID(),
	 * 10, Constants.SPACE)); sb.append(StringUtils.rightPad(brl.getDealerNumber(),
	 * 10, Constants.SPACE)); sb.append(brl.getProcessingDate()); String postingKey
	 * = BOCCommonUtil.getNextSeqNo(Constants.POSTING_SEQUENCE);
	 * BOCCommonUtil.insertPostingEntrytoTable(brl, pkey,postingKey);
	 * 
	 * sb.append(dataWithNewLine); br.flush();
	 * 
	 * //added by mani System.out.println(" POSTING FILE ADDED  ...."); //added by
	 * mani
	 * 
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } finally { try { br.close();
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 */

	private void setOutputtoFile(BackOfficeBatchBankRequestModel aBackOfficeBatchBankRequestModel, String pkey) {

		File file = new File(BOCCommonUtil.retreiveFromProperties(Constants.POSTING_OUTPUT_PATH)
				+ aBackOfficeBatchBankRequestModel.getFileName());
		String fileName = aBackOfficeBatchBankRequestModel.getFileName();
		// added by mani
		System.out.println("SET POSTING_OUTPUT_PATH  ....");
		// added by mani
		int account;

		BufferedWriter br = null;
		// String line = null;
		StringBuilder sb = new StringBuilder();

		String dataWithNewLine = System.getProperty("line.separator");
		try {

			br = new BufferedWriter(new FileWriter(file, false));

			for (BackOfficeBatchBankReqModel brl : aBackOfficeBatchBankRequestModel.getBackOfficeBatchBankReqModel()) {

				// sb.append(Constants.SPACE);
				sb.append(StringUtils.leftPad(brl.getBranchNumber(), 5, Constants.ZERO));
				sb.append(brl.getDebitCreditCode());

				System.out.println("effectivedatewriteinfile---" + brl.getEffectiveDate());
				sb.append(brl.getEffectiveDate());

//				String effecdate = brl.getEffectiveDate().substring(0, brl.getEffectiveDate().length() - 2);
//		        System.out.println("effectivedatewriteinfile---"+effecdate);
//		        sb.append(effecdate);

				sb.append(brl.getAccountType());
				System.out.println("accountypewriteinfile...." + brl.getAccountType());
				account = brl.getAccountNumber().indexOf("-");
				String bo_accountno = brl.getAccountNumber();
				System.out.println("BOAccountnumberindex  ...." + account);
				if (account > 0)
					bo_accountno = brl.getAccountNumber().substring(0, account);
				System.out.println("BOAccountnumber  ...." + bo_accountno);
				sb.append(StringUtils.leftPad(bo_accountno, 12, Constants.ZERO));
//				sb.append(StringUtils.rightPad(brl.getAccountNumber().endsWith(),12, Constants.SPACE));
				int tranAmt = brl.getTransactionAmount().toString().indexOf(".");
				String tranAmtVal = brl.getTransactionAmount().toString();
				if (tranAmt > 0)
					tranAmtVal = tranAmtVal.substring(0, tranAmt);

				sb.append((StringUtils.leftPad(tranAmtVal, 17, Constants.ZERO)).substring(0, 17));
				int lceAmt = brl.getLCEAmount().toString().indexOf(".");
				String lceAmtVal = brl.getLCEAmount().toString();
				if (lceAmt > 0)
					lceAmtVal = lceAmtVal.substring(0, lceAmt);

				sb.append((StringUtils.leftPad(lceAmtVal, 17, Constants.ZERO)).substring(0, 17));
				sb.append(StringUtils.rightPad(brl.getForeignCurrencyCode(), 3, Constants.ZERO));
				System.out.println("Posting File FX Fix");
				/* FX Code fix 23/04/21 Done by rajwin */
				/*
				 * int frgnRte = brl.getForeignCurrencyRate().toString().indexOf(".");
				 * System.out.println("frgnRte--->" + brl.getForeignCurrencyRate()); String
				 * frgnRteVal = brl.getForeignCurrencyRate().toString(); if (frgnRte > 0)
				 * frgnRteVal = frgnRteVal.substring(0, frgnRte);
				 * 
				 * 
				 * sb.append((StringUtils.leftPad(frgnRteVal, 8, Constants.ZERO)).substring(0,
				 * // 15));
				 */
				String fx = brl.getForeignCurrencyRate().toString();
				String s1 = fx.toString().split("\\.")[0];
				// String s2 = fx.toString().split("\\.")[1];
				String s2 = "0";
				if (fx.toString().contains(".")) {
					s2 = fx.toString().split("\\.")[1];
				}
				sb.append(StringUtils.leftPad(s1, 8, "0").substring(0, 8));
				sb.append(StringUtils.rightPad(s2, 7, "0").substring(0, 7));
				sb.append(StringUtils.leftPad(brl.getReferenceNumber(), 9, Constants.ZERO));
				sb.append(StringUtils.rightPad(brl.getAlphabeticData1(), 40, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getAlphabeticData2(), 40, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getAlphabeticData3(), 40, Constants.SPACE));
				sb.append(brl.getTransactionTime());

				if (brl.getCIFKey() != null) {
					if (brl.getAccountType().trim().equals("GL")) {
						sb.append(StringUtils.rightPad(Constants.SPACE, 10));
					} else if (brl.getCIFKey().length() < 10) {
						sb.append(StringUtils.rightPad(brl.getCIFKey(), 10, Constants.SPACE));
					} else {
						sb.append(StringUtils.rightPad(brl.getCIFKey().substring(0, 10), 10, Constants.SPACE));
					}

				} else {
					sb.append(StringUtils.rightPad(Constants.SPACE, 10));
				}
				sb.append(StringUtils.rightPad(brl.getMadeBy(), 3, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getApprovedBy(), 3, Constants.SPACE));
				sb.append(StringUtils.leftPad(brl.getAdditionalFCYInfo(), 1, Constants.ZERO));
				sb.append(StringUtils.leftPad(brl.getOriginationInfo(), 1, Constants.ZERO));
				sb.append(StringUtils.rightPad(brl.getForeignCurrencyOverride(), 1, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getApplicationTranCode(), 2, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getAuthorisedUserID(), 10, Constants.SPACE));
				sb.append(StringUtils.rightPad(brl.getDealerNumber(), 10, Constants.SPACE));
				sb.append(brl.getProcessingDate());

				BOCCommonUtil.insertPostingEntrytoTable(brl, pkey,fileName);

				sb.append(dataWithNewLine);
				// br.flush();

				// added by mani
				System.out.println(" POSTING FILE ADDED  ....");
				// added by mani

			}
			// extractFileData.append(sb.toString() + "\r\n");
			br.write(sb.toString());
			br.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private BackOfficeBatchBankRequestModel setBankReqModelFromTIReqModel(

			BackOfficeBatchTIRequestModel aBackOfficeBatchTIRequestModel) {
		// added by mani
		System.out.println("Entering into setBankReqModelFromTIReqModel....");
		// added by mani
		String refNo = null;
		BackOfficeBatchBankRequestModel aBackOfficeBatchBankRequestModel = new BackOfficeBatchBankRequestModel();
		List<BackOfficeBatchBankReqModel> BackOfficeBatchBankReqList = new LinkedList<BackOfficeBatchBankReqModel>();

		for (BackOfficeBatchTIReqModel bl : aBackOfficeBatchTIRequestModel.getBackOfficeBatchTIReqModel()) {
			System.out.println("Posting Code" + bl.getPostingCode());
			if ((!bl.getPostingCode().startsWith("SP0") || bl.getPostingCode().isEmpty()
					|| "".equalsIgnoreCase(bl.getPostingCode()) || bl.getPostingCode() == null)
					&& !bl.getPostingCode().equalsIgnoreCase("SP101")) {
				// added by mani
				System.out.println("Entering into forloop BackOfficeBatchTIReqModel....");
				// added by mani
				BackOfficeBatchBankReqModel aBackOfficeBatchBankReqModel = new BackOfficeBatchBankReqModel();
				aBackOfficeBatchBankReqModel.setBranchNumber(bl.getPostingBranch());
				// added by mani
				System.out.println("bl.getPostingBranch()" + bl.getPostingBranch());
				// added by mani
				aBackOfficeBatchBankReqModel
						.setDebitCreditCode(BOCCommonUtil.DebitCreditTranslate(bl.getDebitCreditFlag()));
				// added by mani
				System.out.println("bl.getDebitCreditFlag()" + bl.getDebitCreditFlag());
				// added by mani

				// added by rajwin
				aBackOfficeBatchBankReqModel
						.setPriotoFuturdatecheck(CommonUtil.GregorianDatetoSimpleDate("yyyyMMdd", bl.getValueDate()));
				System.out.println(
						"b1.setPriotoFuturdatecheck()  " + aBackOfficeBatchBankReqModel.getPriotoFuturdatecheck());
				String ValDate;
				ValDate = CommonUtil.getValueDate(aBackOfficeBatchBankReqModel.getPriotoFuturdatecheck());
				aBackOfficeBatchBankReqModel.setEffectiveDate(ValDate);
				System.out.println("getEffectiveDate" + aBackOfficeBatchBankReqModel.getEffectiveDate());
				// added by rajwin
				// added by mani
				System.out.println("bl.getValueDate()" + bl.getValueDate());
				// added by mani
				aBackOfficeBatchBankReqModel.setAccountType(BOCCommonUtil.AccountTypeTranslate(bl.getAccountType()));
				// added by mani
				System.out.println("bl.getAccountType()" + bl.getAccountType());
				// added by mani
				aBackOfficeBatchBankReqModel.setAccountNumber(bl.getBackOfficeAccountNo());
				// added by mani
				System.out.println("bl.getBackOfficeAccountNo()" + bl.getBackOfficeAccountNo());
				// added by mani
				/*
				 * aBackOfficeBatchBankReqModel
				 * .setTransactionAmount(CommonUtil.convertTIAmount(bl.getPostingAmount(),
				 * bl.getPostingCcy()));
				 */
				// added for removing decimal
				if (bl.getPostingCcy().equals("JPY")) {
					aBackOfficeBatchBankReqModel
							.setTransactionAmount(bl.getPostingAmount().multiply(new BigDecimal(100)));
				} else {
					aBackOfficeBatchBankReqModel.setTransactionAmount(bl.getPostingAmount());
				}

				System.out.println("bl.getPostingAmount()" + bl.getPostingAmount());

				// added by mani
				System.out.println("bl.getPostingCcy()" + bl.getPostingCcy());
				// added by mani

				aBackOfficeBatchBankReqModel.setForeignCurrencyCode(bl.getForeignCurrencyCode());
				// added by mani
				System.out.println("bl.getForeignCurrencyCode()" + bl.getForeignCurrencyCode());
				// added by mani

				if (bl.getForeignCurrencyRate().equals(BigDecimal.ZERO)) {
					aBackOfficeBatchBankReqModel.setForeignCurrencyRate(
							CommonUtil.convertNormalAmount(bl.getForeignCurrencyRate(), bl.getForeignCurrencyCode())
									.setScale(5, BigDecimal.ROUND_CEILING));
				} else {
					aBackOfficeBatchBankReqModel.setForeignCurrencyRate(
							CommonUtil.convertNormalAmount(bl.getForeignCurrencyRate(), bl.getForeignCurrencyCode())
									.setScale(7, BigDecimal.ROUND_CEILING));
				}
				// added by mani
				System.out
						.println("bl.getForeignCurrencyRate()" + aBackOfficeBatchBankReqModel.getForeignCurrencyRate());
				// added by mani
				if (!(bl.getForeignCurrencyCode().trim().equalsIgnoreCase("0")
						|| bl.getForeignCurrencyCode().trim().equalsIgnoreCase("000"))) {

					/*
					 * aBackOfficeBatchBankReqModel.setLCEAmount(CommonUtil.convertNormalAmount(
					 * bl.getForeignCurrencyRate().multiply(aBackOfficeBatchBankReqModel.
					 * getTransactionAmount()), bl.getForeignCurrencyCode())); // added by mani
					 * System.out.println("aBackOfficeBatchBankReqModel.getTransactionAmount()" +
					 * aBackOfficeBatchBankReqModel.getTransactionAmount()); // added by mani
					 */
					aBackOfficeBatchBankReqModel.setLCEAmount(bl.getLcyAmount());

				} else {
					aBackOfficeBatchBankReqModel.setLCEAmount(BigDecimal.ZERO);
					// added by mani
					System.out.println("aBackOfficeBatchBankReqModel.setLCEAmount(BigDecimal.ZERO)..");
					// added by mani
				}
				// migrated refrence correction rajwin 030821
				int len;
				len = bl.getMasterReference().length();
				if (len > 14) {
					aBackOfficeBatchBankReqModel.setReferenceNumber(bl.getMasterReference().substring(7, 16));
				} else {
					refNo = bl.getMasterReference();
					if (refNo.length() > 9) {
						refNo = refNo.substring(refNo.length() - 9, refNo.length());
					}
					aBackOfficeBatchBankReqModel.setReferenceNumber(refNo);
				}
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setReferenceNumber..");
				// added by mani,vishnu for concat - with ms,ev ref
				aBackOfficeBatchBankReqModel.setAlphabeticData1(
						bl.getMasterReference().concat(Constants.HIFEN).concat(bl.getEventReference()));
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setAlphabeticData1..");
				// added by mani,vishnu for change of date as per sample file
				aBackOfficeBatchBankReqModel
						.setAlphabeticData2(CommonUtil.getSysDate(Constants.POSTING_DATE_FORMAT_WITH_TIME));
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setAlphabeticData2..");
				// added by mani
				aBackOfficeBatchBankReqModel.setAlphabeticData3(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setAlphabeticData3..");
				// added by mani
				aBackOfficeBatchBankReqModel.setTransactionTime(CommonUtil.getSysDate(Constants.POSTING_TIME_FORMAT));
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setTransactionTime..");
				// added by mani
				aBackOfficeBatchBankReqModel.setCIFKey(bl.getCustomerMnemonic());
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setCIFKey..");
				// added by mani
				aBackOfficeBatchBankReqModel.setMadeBy(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setMadeBy..");
				// added by mani
				aBackOfficeBatchBankReqModel.setApprovedBy(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setApprovedBy..");
				// added by mani
				aBackOfficeBatchBankReqModel.setAdditionalFCYInfo(Constants.ZERO);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setAdditionalFCYInfo..");
				// added by mani
				aBackOfficeBatchBankReqModel.setOriginationInfo(Constants.ZERO);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setOriginationInfo..");
				// added by mani
				aBackOfficeBatchBankReqModel.setForeignCurrencyOverride(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setForeignCurrencyOverride..");
				// added by mani
				aBackOfficeBatchBankReqModel.setApplicationTranCode(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setApplicationTranCode..");
				// added by mani
				aBackOfficeBatchBankReqModel.setAuthorisedUserID(bl.getAuthorizerUser());
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setAuthorisedUserID..");
				// added by mani
				aBackOfficeBatchBankReqModel.setDealerNumber(Constants.SPACE);
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setDealerNumber..");
				// added by mani
				aBackOfficeBatchBankReqModel.setProcessingDate(CommonUtil.GetJulianDate());
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setProcessingDate..");
				// added by mani
				aBackOfficeBatchBankRequestModel
						.setFileName(BOCCommonUtil.retreiveFromProperties(Constants.POSTING_FILE_PREFIX) + StringUtils
								.leftPad(BOCCommonUtil.getNextSeqNo(Constants.POSTINGFILE_SEQUENCE), 7, '0'));
				// added by mani
				System.out.println("aBackOfficeBatchBankReqModel.setFileName..");
				// added by mani
				BackOfficeBatchBankReqList.add(aBackOfficeBatchBankReqModel);

				// added by mani
				System.out.println("BackOfficeBatchBankReqList added....");
				// added by mani
			} else if (bl.getAccountType().contains("DL")) {
				try {
					LimitImpl aLimitImpl = new LimitImpl();
					System.out.println("Entering into limit update with customerID -->" + bl.getCustomerMnemonic());
					aLimitImpl.doLimitUpdateCore(bl.getCustomerMnemonic());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		aBackOfficeBatchBankRequestModel.setBackOfficeBatchBankReqModel(BackOfficeBatchBankReqList);

		return aBackOfficeBatchBankRequestModel;
	}

	private BackOfficeBatchTIRequestModel setTIReqModelFromTIReqXML(String tiRequestXML) {

		BackOfficeBatchTIRequestModel aBackOfficeBatchTIRequestModel = new BackOfficeBatchTIRequestModel();
		List<BackOfficeBatchTIReqModel> BackOfficeBatchTIReqList = new LinkedList<BackOfficeBatchTIReqModel>();

		try {

			List<ServiceRequest> bsrList = BOCCommonUtil.extractPostingEntries(tiRequestXML, Constants.BO_BATCH);
			Posting posting = null;
			BigDecimal lcyAmount = BigDecimal.ZERO;
			BigDecimal lcyBalAmt = BigDecimal.ZERO;
			BigDecimal maxDBcheck = BigDecimal.ZERO;
			BigDecimal maxCRcheck = BigDecimal.ZERO;
			boolean isValidNonRevolvingProduct = false;
			// changes starts here
			int counter = 1;
			List<YXDetail> yxDetailList = new LinkedList<YXDetail>();
			BigDecimal batchTotal = new BigDecimal(0);
			int i=0;
			String changeDBPos=" ";
			String changeCRPos=" ";
			String changeDBWithoutLCYPos=" ";
			String changeCRWithoutLCYPos=" ";
			String changePos=" ";
			String masterRef=" ";
			String eventRef=" ";
			String ChangeDbOrCR=" ";
			for (ServiceRequest sr : bsrList) {
				List<JAXBElement<?>> serviceList = sr.getRequest();
				
				JAXBElement<?> postingJAXB = (JAXBElement<?>) serviceList.get(0);
				if (postingJAXB.getValue().toString().contains("Posting")) {
					posting = (Posting) postingJAXB.getValue();
					boolean glCheck = checkIFGL(posting.getSPSKMnemonic());
					if (posting.getAccountType() != null && posting.getAccountType().equals("YX")) {
						YXDetail aYXDetail1 = new YXDetail();
						aYXDetail1.setId(counter + "");
						aYXDetail1.setCcy(posting.getPostingCcy());
						aYXDetail1.setAmount(new BigDecimal(posting.getPostingAmount()));
						aYXDetail1.setDebitCredit(posting.getDebitCreditFlag());
						aYXDetail1.setLcyAmount(getLCYAMT(posting.getPostingNarrative1()));
						counter = counter + 1;
						yxDetailList.add(aYXDetail1);
					}
					if (posting.getDebitCreditFlag().equals("D")) {
						
						batchTotal = batchTotal.subtract(getLCYAMT(posting.getPostingNarrative1()));
						System.out.println("checkPost-->"+checkPosting(posting.getAccountType(),BOCCommonUtil.getReferencefromKey(posting.getMasterKey()),posting.getEventReference()));
						if(checkPosting(posting.getAccountType(),posting.getMasterReference(),posting.getEventReference())) {
							if(getLCYAMT(posting.getPostingNarrative1()).compareTo(maxDBcheck)==1) {
								maxDBcheck=getLCYAMT(posting.getPostingNarrative1());
								changeDBPos=String.valueOf(i);
								masterRef=BOCCommonUtil.getReferencefromKey(posting.getMasterKey());
								eventRef=posting.getEventReference();
								if(!(glCheck||posting.getPostingCcy().equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY)))) {
									changeDBWithoutLCYPos=String.valueOf(i);
								}
							}
							
						}
						
					} else {
						
						batchTotal = batchTotal.add(getLCYAMT(posting.getPostingNarrative1()));
						if(checkPosting(posting.getAccountType(),BOCCommonUtil.getReferencefromKey(posting.getMasterKey()),posting.getEventReference())) {
							if(getLCYAMT(posting.getPostingNarrative1()).compareTo(maxCRcheck)==1) {
								maxCRcheck=getLCYAMT(posting.getPostingNarrative1());
								changeCRPos=String.valueOf(i);
								masterRef=BOCCommonUtil.getReferencefromKey(posting.getMasterKey());
								eventRef=posting.getEventReference();
								if(!(glCheck||posting.getPostingCcy().equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY)))) {
									changeCRWithoutLCYPos=String.valueOf(i);
								}
							}
							
						}
						
					}
				}
				i++;
			}
			System.out.println("Batch Total : " + batchTotal.toString());
			System.out.println("changeCRPos->"+changeCRPos +" maxCRcheck-->"+maxCRcheck +" changeCRWithoutLCYPos-->"+changeCRWithoutLCYPos);
			System.out.println("changeDBPos->"+changeDBPos +" maxDBcheck-->"+maxDBcheck +" changeDBWithoutLCYPos-->"+changeDBWithoutLCYPos);
			if (batchTotal.compareTo(new BigDecimal(0)) != 0) {
				getProdEvent(masterRef, posting.getEventReference());
				if(!(productCode.equals("CPC")||productCode.equals("CPB")||productCode.equals("CPH"))) {
				String getExcessAmt="select trim(NARR_P1),DR_CR_FLG from posting where key97 in (select key97 from relitem where event_key=(select bev.key97 from master mas,baseevent bev where mas.key97=bev.master_key and mas.master_ref=? and BEV.REFNO_PFIX||LPAD(BEV.REFNO_SERL,3,0)=?)) and POSTAMT is not null and AMOUNT=0 order by 1";
				Connection aConnection = null;
				PreparedStatement pst = null;
				ResultSet rst = null;
				BigDecimal lcy = BigDecimal.ZERO;
				try {
					aConnection = CommonUtil.DBConnection();
					pst = aConnection.prepareStatement(getExcessAmt);
					pst.setString(1, masterRef);
					pst.setString(2, eventRef);
					System.out.println("Query-->" + getExcessAmt +" master-->"+masterRef+" event-->"+eventRef);
					rst = pst.executeQuery();
					while (rst.next()) {
						
						lcyBalAmt=getLCYAMT(rst.getString(1));
						lcyBalAmt=lcyBalAmt.abs();
						
						ChangeDbOrCR=rst.getString(2);
							if(rst.getString(1).contains("-")) {
								if(ChangeDbOrCR.equals("D") ) {
									ChangeDbOrCR="C";
									
								}else {
									ChangeDbOrCR="D";
									
								}
								}
							if(ChangeDbOrCR.equals("C") ) {
								lcy=lcy.add(lcyBalAmt);
							}else {
								lcy=lcy.subtract(lcyBalAmt);
							}
							lcyBalAmt=lcy.abs();
					}
					if(lcy.compareTo(BigDecimal.ZERO)==1) {
						ChangeDbOrCR="C";
					}else
						ChangeDbOrCR="D";
					if(ChangeDbOrCR.equals("D")) {
						if(changeDBWithoutLCYPos.equals(" ")) {
						changePos=changeDBWithoutLCYPos;
						}else {
						changePos=changeCRWithoutLCYPos;
						lcyBalAmt=lcyBalAmt.multiply(BigDecimal.valueOf(-1));
						}
						}else {
							if(!changeCRWithoutLCYPos.equals(" ")) {
								changePos=changeCRWithoutLCYPos;
							}else {
								changePos=changeDBWithoutLCYPos;
								lcyBalAmt=lcyBalAmt.multiply(BigDecimal.valueOf(-1));
								}
							}

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					CommonUtil.CloseConnection(aConnection, pst, rst);
				}
			}else {
				lcyBalAmt=batchTotal.abs();
				if(batchTotal.compareTo(BigDecimal.ZERO)==1) {
					if(changeDBPos.equals(changeDBWithoutLCYPos)) {
						changePos=changeDBPos;
						}else {
						changePos=changeCRWithoutLCYPos;
						lcyBalAmt=lcyBalAmt.multiply(BigDecimal.valueOf(-1));
						}
				}else {
					if(changeCRPos.equals(changeCRWithoutLCYPos)) {
						changePos=changeCRPos;
					}else {
						changePos=changeDBWithoutLCYPos;
						lcyBalAmt=lcyBalAmt.multiply(BigDecimal.valueOf(-1));
						}
				}
				
				
			}
			}
			System.out.println("ChangeDbOrCR-->"+ ChangeDbOrCR);
			System.out.println("lcyBalAmt-->"+lcyBalAmt);
			System.out.println("changePos-->"+changePos);
			// changes ends here
			i=0;
			for (ServiceRequest sr : bsrList) {

				try {

					List<JAXBElement<?>> serviceList = sr.getRequest();

					JAXBElement<?> postingJAXB = (JAXBElement<?>) serviceList.get(0);
					if (postingJAXB.getValue().toString().contains("Posting")) {
						posting = (Posting) postingJAXB.getValue();
						/* Adding Non Revolving Limit amount in External application Start */

						masterRef = BOCCommonUtil.getReferencefromKey(posting.getMasterKey());

						try {
							
							if (posting.getDebitCreditFlag().equalsIgnoreCase("D")) {
								System.out.println("Product Code: "+ productCode+"\n"+"EventCode: "+eventCode);
								//if (productCode == null || productCode.isEmpty() || productCode.equals(null)) {
									getProdEvent(masterRef, posting.getEventReference());
									isValidNonRevolvingProduct = NonRevolvingLimitImpl.validProductEvent(productCode,
											eventCode);
								//}
                                 System.out.println("isvalid non revolving product"+isValidNonRevolvingProduct);
								if (isValidNonRevolvingProduct) {
									NonRevolvingLimitImpl.addNonRevolvingLimitAmount(masterRef,
											posting.getEventReference(), posting.getCustomerMnemonic(),
											posting.getAccountType(), posting.getPostingAmount(),
											posting.getPostingCcy(),productCode,eventCode);
								}
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						/* Adding Non Revolving Limit amount in External application End */
						BackOfficeBatchTIReqModel aBackOfficeBatchTIReqModel = new BackOfficeBatchTIReqModel();

						System.out.println("posting.getAccountType()" + posting.getAccountType());
						System.out.println("posting.getBackOfficeAccountNo()" + posting.getBackOfficeAccountNo());
						System.out.println("posting.getPostingAmount()" + posting.getPostingAmount());
						System.out.println("posting.getPostingCcy()" + posting.getPostingCcy());
						System.out.println("posting.getDebitCreditFlag()" + posting.getDebitCreditFlag());

						aBackOfficeBatchTIReqModel.setPostingNarrative(posting.getPostingNarrative1());
						aBackOfficeBatchTIReqModel.setPostingNarrative2(posting.getPostingNarrative2());
						aBackOfficeBatchTIReqModel.setDebitCreditFlag(posting.getDebitCreditFlag());
						aBackOfficeBatchTIReqModel.setValueDate(posting.getValueDate());
						aBackOfficeBatchTIReqModel.setAccountType(posting.getAccountType());

						aBackOfficeBatchTIReqModel
								.setMasterReference(BOCCommonUtil.getReferencefromKey(posting.getMasterKey()));
						aBackOfficeBatchTIReqModel.setEventReference(posting.getEventReference());
						aBackOfficeBatchTIReqModel.setCustomerMnemonic(posting.getCustomerMnemonic());
						aBackOfficeBatchTIReqModel.setPostingAmount(new BigDecimal(posting.getPostingAmount()));
						aBackOfficeBatchTIReqModel.setPostingCcy(posting.getPostingCcy());
						// aBackOfficeBatchTIReqModel=setPostingRate(aBackOfficeBatchTIReqModel,posting);

						BigDecimal exchRate = BigDecimal.ZERO;
						String eventKeyVal = null;
						if (posting.getEventKey() == null) {
							aBackOfficeBatchTIReqModel.setAuthorizerUser("SUPERVISOR");

							eventKeyVal = Constants.ZERO;

						} else {
							aBackOfficeBatchTIReqModel
									.setAuthorizerUser(BOCCommonUtil.getAuthotizerID(posting.getEventKey().toString()));
							eventKeyVal = posting.getEventKey().toString();
						}
						if (!BOCCommonUtil.CustomerAccountCheck(posting.getAccountType())) {
							if (!posting.getSettlementAccountUsed().isEmpty()
									&& !"N".equalsIgnoreCase(posting.getSettlementAccountUsed())
									&& posting.getSettlementAccountUsed() != "N") {
								System.out.println("Posting code if");

								aBackOfficeBatchTIReqModel.setPostingCode(" ");
							} else {
								try {
									aBackOfficeBatchTIReqModel.setPostingCode(posting.getSPSKMnemonic().toString());
								} catch (Exception e) {
									aBackOfficeBatchTIReqModel.setPostingCode(" ");
									System.out.println("getSPSKMnemonic--->" + e.getMessage());
								}
							}
							aBackOfficeBatchTIReqModel
									.setPostingBranch(posting.getBackOfficeAccountNo().split("\\-")[1]);
							aBackOfficeBatchTIReqModel
									.setBackOfficeAccountNo(posting.getBackOfficeAccountNo().split("\\-")[0]);
						} else {
							aBackOfficeBatchTIReqModel.setPostingCode(" ");
							System.out.println(QueryConstants.Cust_acct_brch + posting.getBackOfficeAccountNo() + "'");
							aBackOfficeBatchTIReqModel.setPostingBranch(BOCCommonUtil.executeQuery(
									QueryConstants.Cust_acct_brch + posting.getBackOfficeAccountNo() + "'"));

							aBackOfficeBatchTIReqModel.setBackOfficeAccountNo(posting.getBackOfficeAccountNo());
						}
						// added by mani
						System.out.println("posting.getEventKey()" + posting.getEventKey());
						// added by mani
						// changes done added BOCCommonUtil.retreiveFromProperties to retrive value from
						// property
						if (posting.getPostingCcy()
								.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY)))
						// changes done end

						{
							aBackOfficeBatchTIReqModel.setForeignCurrencyCode("0");
							aBackOfficeBatchTIReqModel.setForeignCurrencyRate(BigDecimal.ZERO);
								if(String.valueOf(i).equals(changePos)) {
								System.out.println("SetPostAmt-->"+new BigDecimal(posting.getPostingAmount()).add(lcyBalAmt));
								/*
								 * aBackOfficeBatchTIReqModel.setPostingAmount(new
								 * BigDecimal(posting.getPostingAmount()).add(lcyBalAmt));
								 */}
						} else {
							boolean glCheck = checkIFGL(posting.getSPSKMnemonic());
							System.out.println("checkIFGL(posting.getSPSKMnemonic())--->" + glCheck);
							if (glCheck) {
								System.out.println("inside checkIFGL");
								exchRate = BOCCommonUtil.getSpotRate(posting.getPostingCcy(),
										BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
								System.out.println("exchRate-->" + exchRate);
								aBackOfficeBatchTIReqModel.setPostingAmount(
										getLCYAMT(posting.getPostingNarrative1()));
								aBackOfficeBatchTIReqModel
										.setPostingCcy(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY));

								aBackOfficeBatchTIReqModel.setForeignCurrencyCode("0");
								aBackOfficeBatchTIReqModel.setForeignCurrencyRate(BigDecimal.ZERO);
							} else {
								aBackOfficeBatchTIReqModel
										.setForeignCurrencyCode(BOCCommonUtil.getCurrencyCode(posting.getPostingCcy()));
								exchRate = BOCCommonUtil.getFXRate(eventKeyVal);
								if ((exchRate.compareTo(BigDecimal.ZERO)) == 0) {
									
									aBackOfficeBatchTIReqModel
											.setForeignCurrencyRate(BOCCommonUtil.getSpotRate(posting.getPostingCcy(),
													BOCCommonUtil.retreiveFromProperties(Constants.ZONEID)));
									System.out.println("Inside IF exchRate--> " + exchRate);
									lcyAmount = getLCYAMT(posting.getPostingNarrative1());
									System.out.println("batchTotal--->"+batchTotal);
									
										
									
									if (batchTotal.compareTo(new BigDecimal(0)) == 0) {
										aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount);
									} 
									
									else {
										aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount);
										if(String.valueOf(i).equals(changePos)) {
											aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount.add(lcyBalAmt));
											System.out.println("lcyAmount.add(lcyBalAmt)-->"+lcyAmount.add(lcyBalAmt));
										}
										
									}
								
								} else {
									
									System.out.println("Inside Else exchRate--> " + exchRate);
									System.out.println("batchTotal--->"+batchTotal);
									aBackOfficeBatchTIReqModel.setForeignCurrencyRate(exchRate);
									lcyAmount = getLCYAMT(posting.getPostingNarrative1());
									
									if (batchTotal.compareTo(new BigDecimal(0)) == 0) {
										aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount);
									} else {
										aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount);
										if(String.valueOf(i).equals(changePos)) {
											aBackOfficeBatchTIReqModel.setLcyAmount(lcyAmount.add(lcyBalAmt));
											System.out.println("lcyAmount.add(lcyBalAmt)-->"+lcyAmount.add(lcyBalAmt));
										}
										
									}
								
								}
							}
						}

						BackOfficeBatchTIReqList.add(aBackOfficeBatchTIReqModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// added by mani
				System.out.println("BackOfficeBatchTIReqList added");
				// added by mani
				i++;
			}
			BackOfficeBatchTIReqList = setPostingRate(BackOfficeBatchTIReqList);
			BackOfficeBatchTIReqList = sortListbyDB_CR(BackOfficeBatchTIReqList);
			aBackOfficeBatchTIRequestModel.setBackOfficeBatchTIReqModel(BackOfficeBatchTIReqList);
			// added by mani
			System.out.println("setBackOfficeBatchTIReqModel...");
			// added by mani

		} catch (Exception e) {
			e.printStackTrace();
		}
		return aBackOfficeBatchTIRequestModel;

	}

	private boolean checkPosting(String accountType, String masterReference, String eventReference) {
		String getMasEve="select mas.REFNO_PFIX,bev.REFNO_PFIX from master mas,baseevent bev where mas.key97=bev.master_key and mas.master_ref=? and BEV.REFNO_PFIX||LPAD(BEV.REFNO_SERL,3,0)=?";
		String isAvailable="select * from ETT_BOC_POST_CHECK where PRODUCT_TYPE=? and EVENT_TYPE=? and ACCOUNT_TYPE like '%"+accountType+"%'";
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		PreparedStatement pst1 = null;
		ResultSet rst1 = null;
		boolean postcheck=false;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(getMasEve);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			if (rst.next()) {
				pst1=aConnection.prepareStatement(isAvailable);
				System.out.println("Product-->"+rst.getString(1)+" Ëvent-->"+rst.getString(2));
				pst1.setString(1, rst.getString(1));
				pst1.setString(2, rst.getString(2));
				rst1 = pst1.executeQuery();
				if (rst1.next()) {
					postcheck=true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return postcheck;
	}

	private List<BackOfficeBatchTIReqModel> sortListbyDB_CR(List<BackOfficeBatchTIReqModel> backOfficeBatchTIReqList) {
		List<BackOfficeBatchTIReqModel> sortedBackOfficeBatchTIReqList = new LinkedList<BackOfficeBatchTIReqModel>();
		for (BackOfficeBatchTIReqModel sr : backOfficeBatchTIReqList) {
			if (sr.getDebitCreditFlag().equals("C")) {
				sortedBackOfficeBatchTIReqList.add(0, sr);
			} else {
				sortedBackOfficeBatchTIReqList.add(sr);
			}

		}
		return sortedBackOfficeBatchTIReqList;
	}

	private boolean checkIFGL(String spskMnemonic) {
		boolean glCheck = false;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.GL_CHECK_QUERY);
			pst.setString(1, spskMnemonic);
			System.out.println("Query-->" + QueryConstants.GL_CHECK_QUERY + "--->" + spskMnemonic);
			rst = pst.executeQuery();
			if (rst.next()) {
				glCheck = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return glCheck;
	}

	private static BigDecimal getLCYAMT(String narrative) {
		BigDecimal lcyamt = BigDecimal.ZERO;

		try {
			if (narrative.substring(0, 3).equals("DBE")) {
				String lcyAmtstr = narrative.substring(4, narrative.length() - 3);
				lcyAmtstr = lcyAmtstr.replace(",", "");
				lcyAmtstr = lcyAmtstr.replace(".", "");
				lcyamt = lcyamt.add(new BigDecimal(lcyAmtstr));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return lcyamt;
	}

	private List<BackOfficeBatchTIReqModel> setPostingRate(List<BackOfficeBatchTIReqModel> backOfficeBatchTIReqList) {
		BigDecimal lcyAmt = BigDecimal.ZERO;
		BigDecimal posCompAmt = BigDecimal.ZERO;
		BigDecimal postingAmt = BigDecimal.ZERO;
		String postingAmtCCY = null;
		BigDecimal balAmt = BigDecimal.ZERO;
		BigDecimal exchRate = BigDecimal.ZERO;
		BigDecimal spotRate = BigDecimal.ZERO;
		List<Integer> lcypos = new ArrayList<Integer>();
		String narrative = null;
		String narrative2 = null;
		String debitFlag = null;
		String zoneID = BOCCommonUtil.retreiveFromProperties(Constants.ZONEID);
		for (int i = 0; i < backOfficeBatchTIReqList.size(); i++) {

			narrative = backOfficeBatchTIReqList.get(i).getPostingNarrative();
			narrative2 = backOfficeBatchTIReqList.get(i).getPostingNarrative2();
			debitFlag = backOfficeBatchTIReqList.get(i).getDebitCreditFlag();
			try {
				System.out.println("inside setPostingRate--->" + narrative);
				System.out.println("narrative2--->" + narrative2);
				if (narrative != null && !(narrative.isEmpty())) {
//				  if(narrative.substring(0,4).equals("AMT:") && narrative.length()>4) { 
//					  String lcyAmtstr=narrative.substring(4); 
//					  lcyAmtstr=lcyAmtstr.replace(",", "");
//					  lcyAmtstr=lcyAmtstr.replace(".", "");
//					  lcyAmt=lcyAmt.add(new BigDecimal(lcyAmtstr));
//					  String lcyAmtstr2=narrative2.substring(4); 
//					  lcyAmtstr2=lcyAmtstr2.replace(",", "");
//					  lcyAmtstr2=lcyAmtstr2.replace(".", "");
//					 
//					  posCompAmt=posCompAmt.add(new BigDecimal(lcyAmtstr2));
//				  }else 

					if (narrative.substring(0, 3).equals("LCY")) {

//					  if(postingAmtCCY!=null) {
//						  System.out.println("postingAmtCCY in LCY--->"+postingAmtCCY);
//					  if(postingAmtCCY.equals(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY))) {
//						  postingAmtCCY=backOfficeBatchTIReqList.get(i).getPostingCcy(); 
//					  }
//					  }else {
//						  postingAmtCCY=backOfficeBatchTIReqList.get(i).getPostingCcy(); 
//					  }

//					  if(narrative.length()>4&& narrative2!=null) {
//						  if(!(narrative2.equals("RATE:1.0")||narrative2.equals("RATE:"))){
//						  String lcyAmtstr=narrative.substring(4,narrative.length()-3);
//						  lcyAmtstr=lcyAmtstr.replace(",", "");
//						  lcyAmtstr=lcyAmtstr.replace(".", "");
//						  String lcyCCY=narrative.substring(narrative.length()-3,narrative.length());
//						  if(lcyCCY.equals(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY)) ) {
//							  lcyAmt=lcyAmt.add(new BigDecimal(lcyAmtstr));
//						  }else {
//							  BigDecimal spotRate1=BOCCommonUtil.getSpotRate(lcyCCY, zoneID);
//							  System.out.println("spotRate1--->"+spotRate1);
//							  BigDecimal spLcyAmt=new BigDecimal (lcyAmtstr).multiply(spotRate1);
//							  System.out.println("spLcyAmt--->"+spLcyAmt);
//							  lcyAmt=lcyAmt.add(spLcyAmt);
//						  }
//						  }
//					  }

						if (lcypos.size() > 0) {
							if (backOfficeBatchTIReqList.get(lcypos.get(0)).getDebitCreditFlag()
									.equals(backOfficeBatchTIReqList.get(i).getDebitCreditFlag())) {
								postingAmt = postingAmt.add(backOfficeBatchTIReqList.get(i).getPostingAmount());

							} else {
								postingAmt = postingAmt.subtract(backOfficeBatchTIReqList.get(i).getPostingAmount())
										.abs();

							}
							System.out.println("postingAmt--->" + postingAmt);
						} else {
							postingAmt = postingAmt.add(backOfficeBatchTIReqList.get(i).getPostingAmount());
							postingAmtCCY = backOfficeBatchTIReqList.get(i).getPostingCcy();
						}

						if (debitFlag != null && debitFlag.equalsIgnoreCase("D")) {

						} else {

						}
						lcypos.add(i);
					}
				}
			} catch (Exception e) {

				System.out.println("Exception in setPostingRate--->" + e.getMessage());
				e.printStackTrace();
			}

		}
		if (postingAmt.compareTo(posCompAmt) != 0) {
			balAmt = postingAmt.subtract(posCompAmt).abs();
			System.out.println("balamt--->" + balAmt);
			System.out.println("posCompAmt--->" + posCompAmt);
			System.out.println("postingAmt--->" + postingAmt);
			System.out.println("postingAmtCCY--->" + postingAmtCCY);
			spotRate = BOCCommonUtil.getSpotRate(postingAmtCCY, BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			System.out.println("spotRate--->" + spotRate);
			balAmt = balAmt.multiply(spotRate);
			System.out.println("balamt--->" + balAmt);
			lcyAmt = lcyAmt.add(balAmt);
		}

		if (postingAmt.compareTo(BigDecimal.ZERO) == 1 && lcypos.size() > 0) {
			/* exchRate=lcyAmt.divide(postingAmt, 7, RoundingMode.FLOOR); */
			System.out.println("lcyAmt-->" + lcyAmt);
			System.out.println("postingAmt--->" + postingAmt);
			exchRate = lcyAmt.divide(postingAmt, 18, RoundingMode.UP);
			System.out.println("exchRate--->" + exchRate);
			if ((exchRate.compareTo(BigDecimal.ZERO)) == 1) {
				for (int i = 0; i < lcypos.size(); i++) {
					System.out.println("Existing exchRate--->"
							+ backOfficeBatchTIReqList.get(Integer.valueOf(lcypos.get(i))).getForeignCurrencyRate());
					backOfficeBatchTIReqList.get(Integer.valueOf(lcypos.get(i))).setForeignCurrencyRate(exchRate);
				}
			}
		}

		return backOfficeBatchTIReqList;
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
				productCode = rst.getString("mas_code");
				eventCode = rst.getString("event_code");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

	}

	public static void main(String[] args) {
		String narrative = "DBE -182700000LKR";
		BigDecimal a = new BigDecimal("-122222");
		BigDecimal b = new BigDecimal("222222");
		System.out.println(getLCYAMT(narrative));
		System.out.println(a.add(b));
	}

	/*
	 * private BackOfficeBatchTIReqModel setPostingRate(BackOfficeBatchTIReqModel
	 * aBackOfficeBatchTIReqModel, Posting posting) { String narrative=null;
	 * narrative=posting.getPostingNarrative1(); try {
	 * System.out.println("inside setPostingAMTCCY");
	 * if(narrative!=null&&!(narrative.isEmpty())){ if(narrative.substring(0,
	 * 4).equals("LCY:") && narrative.length()>4) { String
	 * lcyAmt=narrative.substring(4); lcyAmt=lcyAmt.replace(",", "");
	 * lcyAmt=lcyAmt.replace(".", "");
	 * aBackOfficeBatchTIReqModel=setFixedFX(aBackOfficeBatchTIReqModel,posting,
	 * lcyAmt); } else {
	 * aBackOfficeBatchTIReqModel=setDefaultFX(aBackOfficeBatchTIReqModel,posting);
	 * } } else {
	 * aBackOfficeBatchTIReqModel=setDefaultFX(aBackOfficeBatchTIReqModel,posting);
	 * } }catch(Exception e) {
	 * System.out.println("Error in setPostingAMTCCY-->"+e.getMessage()); } return
	 * aBackOfficeBatchTIReqModel; }
	 * 
	 * private BackOfficeBatchTIReqModel setFixedFX(BackOfficeBatchTIReqModel
	 * aBackOfficeBatchTIReqModel, Posting posting, String lcyAmt) {
	 * 
	 * BigDecimal exchRate = BigDecimal.ZERO;
	 * 
	 * BigDecimal a=new BigDecimal(lcyAmt); BigDecimal b=new
	 * BigDecimal(posting.getPostingAmount()); exchRate=a.divide(b, 4,
	 * RoundingMode.FLOOR);
	 * 
	 * aBackOfficeBatchTIReqModel
	 * .setForeignCurrencyCode(BOCCommonUtil.getCurrencyCode(posting.getPostingCcy()
	 * )); if ((exchRate.compareTo(BigDecimal.ZERO)) == 0) {
	 * aBackOfficeBatchTIReqModel
	 * .setForeignCurrencyRate(BOCCommonUtil.getSpotRate(posting.getPostingCcy(),
	 * BOCCommonUtil.retreiveFromProperties(Constants.ZONEID))); } else {
	 * aBackOfficeBatchTIReqModel.setForeignCurrencyRate(exchRate); }
	 * 
	 * return aBackOfficeBatchTIReqModel; }
	 * 
	 * private BackOfficeBatchTIReqModel setDefaultFX(BackOfficeBatchTIReqModel
	 * aBackOfficeBatchTIReqModel, Posting posting) { BigDecimal exchRate =
	 * BigDecimal.ZERO; String eventKeyVal = null; if (posting.getEventKey() ==
	 * null) { eventKeyVal = Constants.ZERO; } else { eventKeyVal =
	 * posting.getEventKey().toString(); } if (posting.getPostingCcy()
	 * .equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.
	 * LOCAL_CURRENCY))) // changes done end
	 * 
	 * { aBackOfficeBatchTIReqModel.setForeignCurrencyCode("0");
	 * aBackOfficeBatchTIReqModel.setForeignCurrencyRate(BigDecimal.ZERO); } else {
	 * aBackOfficeBatchTIReqModel
	 * .setForeignCurrencyCode(BOCCommonUtil.getCurrencyCode(posting.getPostingCcy()
	 * )); exchRate = BOCCommonUtil.getFXRate(eventKeyVal); if
	 * ((exchRate.compareTo(BigDecimal.ZERO)) == 0) { aBackOfficeBatchTIReqModel
	 * .setForeignCurrencyRate(BOCCommonUtil.getSpotRate(posting.getPostingCcy(),
	 * BOCCommonUtil.retreiveFromProperties(Constants.ZONEID))); } else {
	 * aBackOfficeBatchTIReqModel.setForeignCurrencyRate(exchRate); }
	 * 
	 * } return aBackOfficeBatchTIReqModel; }
	 */

}
