package com.ett.eod.npaposting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ett.eod.finaceposting.EodBankReqModel;
import com.ett.eod.finaceposting.EodBankRequestModel;
import com.ett.eod.finaceposting.EodPostingImpl;
import com.ett.eod.util.*;

public class NPAPostingImpl {

	public static Logger logger = Logger.getLogger(NPAPostingImpl.class);

	public String EODbatchposting() {
		String pkey = null;
		BOCCommonUtil.DeleteTabelEntries("EODNPAPOSTINGTABLE");
		EodNPAPostingInsert();
		EodBankRequestModel aEodBankRequestModel = new EodBankRequestModel();
		pkey = BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE);
		try {
			System.out.println("Inside the Try");
			aEodBankRequestModel = setEODFinacePostingreq();
			setoutputfile(aEodBankRequestModel, pkey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Success";
	}

	public void EodNPAPostingInsert() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		aConnection = CommonUtil.DBConnection();
		System.out.println("Insert Query starts");
		try {
			pst = aConnection.prepareStatement(QueryConstant.INSERTINTONPAEOD);
			System.out.println("Insert Query--------------->" + QueryConstant.INSERTINTONPAEOD);
			pst.execute();
			aConnection.close();

		} catch (Exception e) {
			System.out.println("Exception while inserting the data----->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
	}
	
	

	private EodBankRequestModel setEODFinacePostingreq() throws SQLException {
		EodBankRequestModel aEodBankRequestModel = new EodBankRequestModel();
		List<EodBankReqModel> EodBankReqList = new LinkedList<EodBankReqModel>();
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		// String key97 = null;
		// String Date=null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.RETRIVEVALUEFROMNPAEODPOSTING);
			System.out.println("Select Query ---->" + QueryConstant.RETRIVEVALUEFROMNPAEODPOSTING);
			rst = pst.executeQuery();
			while (rst.next()) {
				try {
				EodBankReqModel aEodBankReqModel = new EodBankReqModel();
				// 1
				aEodBankReqModel.setPOSTINGBRANCH(rst.getString("POSTING_BRN"));
				System.out.println("POSTING_BRN  " + rst.getString("POSTING_BRN"));

				aEodBankReqModel.setDEBITCREDITFLAG(rst.getString("DebitCreditFlag"));
				System.out.println("DebitCreditFlag  " + rst.getString("DebitCreditFlag"));
				// 2
				aEodBankReqModel
						.setDebitCreditCode(BOCCommonUtil.DebitCreditTranslate(aEodBankReqModel.getDEBITCREDITFLAG()));

				String valuedate;
				String Formatdate;
				valuedate = rst.getString("ValueDate");
				Formatdate=CommonUtil.GetDateFormat(valuedate);
				System.out.println("ValueDate  " + rst.getString("ValueDate"));
				System.out.println("Formatdate  " + Formatdate);
				
				// aEodBankReqModel.setVALUEDATE(rst.getString("ValueDate"));
				// Date ValueDate=CommonUtil.StringtoDate(valuedate);
				XMLGregorianCalendar VDATE;
				VDATE = CommonUtil.SimpleDatetoGregorianDate("yyyyMMdd", valuedate);

				aEodBankReqModel.setVALUEDATE(VDATE);
				System.out.println("ValueDate Format " + aEodBankReqModel.getVALUEDATE());
				// 3
				aEodBankReqModel.setEffectiveDate(Formatdate);
				System.out.println("EffectiveDate----->" + aEodBankReqModel.getEffectiveDate());
				// 4
				aEodBankReqModel
						.setAccountType(BOCCommonUtil.AccountTypeTranslate(rst.getString("ACCOUNTTYPE").trim()));
				System.out.println("Account Type---->" + rst.getString("ACCOUNTTYPE"));
				// 5
				aEodBankReqModel.setBACKOFFICEACCOUNTNO(rst.getString("BackOfficeAccountNo"));
				System.out.println("BackOfficeAccountNo  " + rst.getString("BackOfficeAccountNo"));
				// 6
				aEodBankReqModel.setPOSTINGAMOUNT(new BigDecimal(rst.getString("PostingAmount")));
				System.out.println("PostingAmount  " + rst.getString("PostingAmount"));
				// 7
				aEodBankReqModel.setPOSTINGCCY(rst.getString("PostingCcy"));
				System.out.println("PostingCcy  " + rst.getString("PostingCcy"));
				String eventKeyVal = null;
				aEodBankReqModel.setAuthorizerUser("SUPERVISOR");
				BigDecimal exchRate = BigDecimal.ZERO;
				eventKeyVal = Constant.ZERO;
				if (aEodBankReqModel.getPOSTINGCCY()
						.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constant.LOCAL_CURRENCY)))
				// changes done end
				{
					aEodBankReqModel.setForeignCurrencyCode("0");
					aEodBankReqModel.setEXCHANGERATE(BigDecimal.ZERO);
				} else {
					aEodBankReqModel
							.setForeignCurrencyCode(BOCCommonUtil.getCurrencyCode(aEodBankReqModel.getPOSTINGCCY()));

					aEodBankReqModel.setEXCHANGERATE(BOCCommonUtil.getSpotRate(aEodBankReqModel.getPOSTINGCCY(),
							BOCCommonUtil.retreiveFromProperties(Constant.ZONEID)));

				}
				System.out.println("Exchange Rate-----> " + aEodBankReqModel.getEXCHANGERATE());

				if (!(aEodBankReqModel.getForeignCurrencyCode().equalsIgnoreCase("0"))) {
					aEodBankReqModel.setLCEAmount(CommonUtil.convertNormalAmount(
							aEodBankReqModel.getEXCHANGERATE().multiply(aEodBankReqModel.getPOSTINGAMOUNT()),
							aEodBankReqModel.getForeignCurrencyCode()));

					System.out.println("aBackOfficeBatchBankReqModel.getTransactionAmount()"
							+ aEodBankReqModel.getPOSTINGAMOUNT());

				} else {
					aEodBankReqModel.setLCEAmount(BigDecimal.ZERO);

					System.out.println("aBackOfficeBatchBankReqModel.setLCEAmount(BigDecimal.ZERO)..");

				}

				aEodBankReqModel.setMASTERREFERENCE(StringUtils.leftPad(rst.getString("MASTERREFERENCE"), 16, Constant.ZERO).substring(7, 16));
				System.out.println("MASTERREFERENCE  " + aEodBankReqModel.getMASTERREFERENCE());
				aEodBankReqModel
						.setAlphabeticData1(rst.getString("MASTERREFERENCE").concat(Constant.HIFEN).concat("EOD000"));
				System.out.println("Alpha1--->" + aEodBankReqModel.getAlphabeticData1());
				aEodBankReqModel.setAlphabeticData2(CommonUtil.getSysDate(Constant.POSTING_DATE_FORMAT_WITH_TIME));
				System.out.println("Alpha2---->" + aEodBankReqModel.getAlphabeticData2());
				aEodBankReqModel.setAlphabeticData3(Constant.SPACE);
				aEodBankReqModel.setTransactionTime(CommonUtil.getSysDate(Constant.POSTING_TIME_FORMAT));
				System.out.println("Transaction Time---> " + aEodBankReqModel.getTransactionTime());

				aEodBankReqModel.setMadeBy(Constant.SPACE);

				System.out.println("aBackOfficeBatchBankReqModel.setMadeBy..");

				aEodBankReqModel.setApprovedBy(Constant.SPACE);

				System.out.println("aBackOfficeBatchBankReqModel.setApprovedBy..");

				aEodBankReqModel.setAdditionalFCYInfo(Constant.ZERO);

				System.out.println("aBackOfficeBatchBankReqModel.setAdditionalFCYInfo..");

				aEodBankReqModel.setOriginationInfo(Constant.ZERO);

				System.out.println("aBackOfficeBatchBankReqModel.setOriginationInfo..");

				aEodBankReqModel.setForeignCurrencyOverride(Constant.SPACE);

				System.out.println("aBackOfficeBatchBankReqModel.setForeignCurrencyOverride..");

				aEodBankReqModel.setApplicationTranCode(Constant.SPACE);

				System.out.println("aBackOfficeBatchBankReqModel.setApplicationTranCode..");

				aEodBankReqModel.setAuthorisedUserID(aEodBankReqModel.getAuthorizerUser());

				System.out.println("aBackOfficeBatchBankReqModel.setAuthorisedUserID..");

				aEodBankReqModel.setDealerNumber(Constant.SPACE);

				System.out.println("aBackOfficeBatchBankReqModel.setDealerNumber..");

				aEodBankReqModel.setProcessingDate(Formatdate);

				System.out.println("aBackOfficeBatchBankReqModel.setProcessingDate..");

				/*
				 * aEodBankRequestModel.setFilename("EOD" +
				 * StringUtils.leftPad(BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE),
				 * 7, '0'));
				 */

				// Date=(rst.getString("ValueDate"));
				// Date date1=rst.getDate("ValueDate");

				/*
				 * aEodBankReqModel.setPOSTINGNARRATIVE1(rst.getString("PostingNarrative1"));
				 * System.out.println("PostingNarrative1  " + rst.getString("PostingNarrative1"));
				 * 
				 * aEodBankRequestModel.setFilename("EODFinacePosting" +
				 * rst.getString("ValueDate")); System.out.println("EODFinacePosting  " +
				 * rst.getString("ValueDate"));
				 */
				EodBankReqList.add(aEodBankReqModel);
				aEodBankRequestModel.setEodBankReqModel(EodBankReqList);

				/*
				 * if (rst.getString("PostingCcy").equalsIgnoreCase(Constant.LOCAL_CURRENCY)) {
				 * System.out.println("inside main if");
				 * aEodBankReqModel.setEXCHANGERATE(BigDecimal.ZERO);
				 * System.out.println("EXCHANGERATE  "); } else { System.out.println("inside main else");
				 * System.out.println("Setting value"); String Exr; Exr =
				 * rst.getString("ExchangeRate"); System.out.println("Setting value" + Exr); if
				 * (BOCCommonUtil.isValidString(Exr)) { System.out.println("inside valid if");
				 * aEodBankReqModel.setEXCHANGERATE(BigDecimal.ZERO);
				 * System.out.println("EXCHANGERATE  " + BigDecimal.ZERO); } else {
				 * System.out.println("inside valid else"); aEodBankReqModel.setEXCHANGERATE(new
				 * BigDecimal(rst.getString("ExchangeRate"))); System.out.println("ExchangeRate  " +
				 * rst.getString("ExchangeRate")); }
				 * 
				 * }
				 */
				} catch (Exception e) {
					System.out.println("Exception in setting up the value");
					System.out.println("Exception in setting up the value--->" + e);
				} 
			}
			/*
			 * aEodBankRequestModel.setFilename("EOD" +
			 * StringUtils.leftPad(BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE),
			 * 7, '0'));
			 */
		} catch (Exception e) {
			System.out.println("Exception in setting up the value");
			System.out.println("Exception in setting up the value--->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return aEodBankRequestModel;
	}

	private static void setoutputfile(EodBankRequestModel aEodBankRequestModel, String pkey) {
		// File file= new File("E:\\test\\Batchposting.txt");
		System.out.println("File reader");
		System.out.println("Buffer reader");
		System.out.println("Test Fix");
		File file = new File((BOCCommonUtil.retreiveFromProperties(Constant.POSTING_OUTPUT_PATH))
				+ (Constant.EOD_NPA_PRIFIX+StringUtils.leftPad(BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE), 7, '0')));
		System.out.println("File reader");
		// FileWriter fr = null;
		BufferedWriter br = null;
		StringBuilder sb = new StringBuilder();
		System.out.println("Buffer reader  test");
		String dataWithNewLine = System.getProperty("line.separator");
		// fr = new FileWriter(file);
		// br = new BufferedWriter(new FileWriter(file, false));
		System.out.println("Buffer reader");
		try {
			System.out.println("File Path--->" + BOCCommonUtil.retreiveFromProperties(Constant.POSTING_OUTPUT_PATH));
			br = new BufferedWriter(new FileWriter(file, false));

			for (EodBankReqModel br1 : aEodBankRequestModel.getEodBankReqModel()) {
				try {
				sb.append(StringUtils.leftPad(br1.getPOSTINGBRANCH().trim(), 5, Constant.ZERO));
				System.out.println("POSTINGBRANCH" + br1.getPOSTINGBRANCH());
				sb.append(br1.getDebitCreditCode());
				sb.append(br1.getEffectiveDate());
				sb.append(br1.getAccountType().trim());
				int account;
				account = br1.getBACKOFFICEACCOUNTNO().indexOf("-");
				String bo_accountno = br1.getBACKOFFICEACCOUNTNO();
				System.out.println("BOAccountnumberindex  ...." + account);
				if (account > 0) {
					bo_accountno = br1.getBACKOFFICEACCOUNTNO().substring(0, account);
					bo_accountno = bo_accountno.substring(1);
				}
				System.out.println("BOAccountnumber  ...." + bo_accountno);
				sb.append(StringUtils.leftPad(bo_accountno, 12, Constant.ZERO));
				// sb.append(br1.getBACKOFFICEACCOUNTNO());
				System.out.println("BACKOFFICEACCOUNTNO" + br1.getBACKOFFICEACCOUNTNO());

				int tranAmt = br1.getPOSTINGAMOUNT().toString().indexOf(".");
				String tranAmtVal = br1.getPOSTINGAMOUNT().toString();
				if (tranAmt > 0)
					tranAmtVal = tranAmtVal.substring(0, tranAmt);

				sb.append((StringUtils.leftPad(tranAmtVal, 17, Constant.ZERO)).substring(0, 17));
				// sb.append(StringUtils.rightPad(br1.getPOSTINGAMOUNT().toString(), 20,
				// Constant.SPACE));
				System.out.println("getPOSTINGAMOUNT" + tranAmtVal);

				int lceAmt = br1.getLCEAmount().toString().indexOf(".");
				String lceAmtVal = br1.getLCEAmount().toString();
				if (lceAmt > 0)
					lceAmtVal = lceAmtVal.substring(0, lceAmt);

				sb.append((StringUtils.leftPad(lceAmtVal, 17, Constant.ZERO)).substring(0, 17));
				sb.append(StringUtils.rightPad(br1.getForeignCurrencyCode(), 3, Constant.ZERO));

				/*
				 * int frgnRte = br1.getEXCHANGERATE().toString().indexOf("."); String
				 * frgnRteVal = br1.getEXCHANGERATE().toString(); if (frgnRte > 0) frgnRteVal =
				 * frgnRteVal.substring(0, frgnRte);
				 * 
				 * sb.append((StringUtils.leftPad(frgnRteVal, 15, Constant.ZERO)).substring(0,
				 * 15));
				 */
				String fx = br1.getEXCHANGERATE().toString();
				String s1 = fx.toString().split("\\.")[0];
				String s2 = fx.toString().split("\\.")[1];
				sb.append(StringUtils.leftPad(s1, 8, Constant.ZERO).substring(0, 8));
				sb.append(StringUtils.rightPad(s2, 7, Constant.ZERO).substring(0, 7));
				sb.append(StringUtils.leftPad(br1.getMASTERREFERENCE(), 9, Constant.ZERO));
				sb.append(StringUtils.rightPad(br1.getAlphabeticData1(), 40, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getAlphabeticData2(), 40, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getAlphabeticData3(), 40, Constant.SPACE));
				sb.append(br1.getTransactionTime());
				sb.append(StringUtils.rightPad(Constant.SPACE, 10));
				sb.append(StringUtils.rightPad(br1.getMadeBy(), 3, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getApprovedBy(), 3, Constant.SPACE));
				sb.append(StringUtils.leftPad(br1.getAdditionalFCYInfo(), 1, Constant.ZERO));
				sb.append(StringUtils.leftPad(br1.getOriginationInfo(), 1, Constant.ZERO));
				sb.append(StringUtils.rightPad(br1.getForeignCurrencyOverride(), 1, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getApplicationTranCode(), 2, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getAuthorisedUserID(), 10, Constant.SPACE));
				sb.append(StringUtils.rightPad(br1.getDealerNumber(), 10, Constant.SPACE));
				sb.append(br1.getProcessingDate());
				/*
				 * sb.append(StringUtils.rightPad(br1.getPOSTINGCCY().toString(), 3,
				 * Constant.SPACE)); System.out.println("getPOSTINGCCY" + br1.getPOSTINGCCY());
				 * sb.append(StringUtils.rightPad(br1.getEXCHANGERATE().toString(), 23,
				 * Constant.SPACE)); System.out.println("getEXCHANGERATE" + br1.getEXCHANGERATE());
				 * sb.append(br1.getDEBITCREDITFLAG()); System.out.println("getDEBITCREDITFLAG" +
				 * br1.getDEBITCREDITFLAG()); sb.append(br1.getVALUEDATE());
				 * System.out.println("getVALUEDATE" + br1.getVALUEDATE());
				 * sb.append(StringUtils.rightPad(br1.getPOSTINGNARRATIVE1(), 40,
				 * Constant.SPACE)); System.out.println("getPOSTINGNARRATIVE1" +
				 * br1.getPOSTINGNARRATIVE1()); sb.append(br1.getMASTERREFERENCE());
				 * System.out.println("getMASTERREFERENCE" + br1.getMASTERREFERENCE());
				 */
				sb.append(dataWithNewLine);
				} catch (Exception e) {
					System.out.println("Exception in writing the file for the masterRef--->"+br1.getMASTERREFERENCE());
				} 
			}
			br.write(sb.toString());
			br.flush();
		} catch (Exception e) {
			System.out.println("Exception in writing the file");
		} finally {
			try {
				br.close();
				// fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * public static void main(String[] args) { //setoutputfile(); }
	 */


}
