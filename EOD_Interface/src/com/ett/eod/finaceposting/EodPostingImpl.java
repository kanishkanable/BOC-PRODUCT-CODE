package com.ett.eod.finaceposting;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import com.ett.eod.util.BOCCommonUtil;
import com.ett.eod.util.CommonUtil;
import com.ett.eod.util.Constant;
import com.ett.eod.util.QueryConstant;

public class EodPostingImpl {
	public static Logger logger = Logger.getLogger(EodPostingImpl.class);

	public String EODbatchposting() {
		String pkey = null;
		BOCCommonUtil.DeleteTabelEntries("eodpostingtable");
		EodPostingInsert();
		EodNPAPostingInsert();
		backupEodPostingTable();
		EodAmtToLKRForGL();
		EodBankRequestModel aEodBankRequestModel = new EodBankRequestModel();
		pkey = BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE);
		try {
			logger.info("Inside the Try");
			aEodBankRequestModel = setEODFinacePostingreq();
			setoutputfile(aEodBankRequestModel, pkey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Success";
	}

	private void EodAmtToLKRForGL() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		aConnection = CommonUtil.DBConnection();
		logger.info("update Amt and ccy for GL");
		try {
			String query=QueryConstant.UPDATEEODPOSTING;
			query=query.replaceAll("LocalCurrency",BOCCommonUtil.retreiveFromProperties(Constant.LOCAL_CURRENCY) );
			//pst = aConnection.prepareStatement(query);
			//logger.info("Update Query--------------->" + QueryConstant.UPDATEEODPOSTING);
			query=query.replaceAll("LocalBranch",BOCCommonUtil.retreiveFromProperties(Constant.ZONEID) );
            pst = aConnection.prepareStatement(query);
            logger.info("Update Query--------------->" + query);
			pst.execute();
			aConnection.close();

		} catch (Exception e) {
			logger.info("Exception while updating the data----->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		
	}

	private void backupEodPostingTable() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		aConnection = CommonUtil.DBConnection();
		logger.info("Backup Insert Query starts");
		try {
			pst = aConnection.prepareStatement(QueryConstant.INSERTINTOEODPOSTING_BKP);
			logger.info("Insert Query--------------->" + QueryConstant.INSERTINTOEODPOSTING_BKP);
			pst.execute();
			aConnection.close();

		} catch (Exception e) {
			logger.info("Exception while inserting the data----->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
	
	}

	public void EodPostingInsert() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		aConnection = CommonUtil.DBConnection();
		logger.info("Insert Query starts");
		try {
			pst = aConnection.prepareStatement(QueryConstant.INSERTINTOEODPOSTING);
			logger.info("Insert Query--------------->" + QueryConstant.INSERTINTOEODPOSTING);
			pst.execute();
			aConnection.close();

		} catch (Exception e) {
			logger.info("Exception while inserting the data----->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
	}
	
	public void EodNPAPostingInsert() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		aConnection = CommonUtil.DBConnection();
		logger.info("Insert Query starts");
		try {
			pst = aConnection.prepareStatement(QueryConstant.INSERTINTONPAEOD);
			logger.info("Insert Query--------------->" + QueryConstant.INSERTINTONPAEOD);
			pst.execute();
			aConnection.close();

		} catch (Exception e) {
			logger.info("Exception while inserting the data----->" + e);
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
			//pst = aConnection.prepareStatement(QueryConstant.RETRIVEVALUEFROMEODPOSTING);
			
			// N-Able Pvt LTD / 20-02-2023
			// BOCS validation added with Seychelles implementation.
			// Same BOCM quiery used as per the given instructions by BOC.
			
			if(BOCCommonUtil.retreiveFromProperties(Constant.ZONEID).equals("BOCM") || BOCCommonUtil.retreiveFromProperties(Constant.ZONEID).equals("BOCS")) {
	            pst = aConnection.prepareStatement(QueryConstant.RETRIVEVALUEFROMEODPOSTING_BOCM);
	            logger.info("Select Query ---->" + QueryConstant.RETRIVEVALUEFROMEODPOSTING_BOCM);

	        }else {
	        pst = aConnection.prepareStatement(QueryConstant.RETRIVEVALUEFROMEODPOSTING);
	        logger.info("Select Query ---->" + QueryConstant.RETRIVEVALUEFROMEODPOSTING);
	        }
			
			rst = pst.executeQuery();
			while (rst.next()) {
				try {
				EodBankReqModel aEodBankReqModel = new EodBankReqModel();
				// 1
				aEodBankReqModel.setPOSTINGBRANCH(rst.getString("POSTING_BRN"));
				logger.info("POSTING_BRN  " + rst.getString("POSTING_BRN"));

				aEodBankReqModel.setDEBITCREDITFLAG(rst.getString("DebitCreditFlag"));
				logger.info("DebitCreditFlag  " + rst.getString("DebitCreditFlag"));
				// 2
				aEodBankReqModel
						.setDebitCreditCode(BOCCommonUtil.DebitCreditTranslate(aEodBankReqModel.getDEBITCREDITFLAG()));

				String valuedate;
				String Formatdate;
				valuedate = rst.getString("ValueDate");
				Formatdate=CommonUtil.GetDateFormat(valuedate);
				logger.info("ValueDate  " + rst.getString("ValueDate"));
				logger.info("Formatdate  " + Formatdate);
				
				// aEodBankReqModel.setVALUEDATE(rst.getString("ValueDate"));
				// Date ValueDate=CommonUtil.StringtoDate(valuedate);
				XMLGregorianCalendar VDATE;
				VDATE = CommonUtil.SimpleDatetoGregorianDate("yyyyMMdd", valuedate);

				aEodBankReqModel.setVALUEDATE(VDATE);
				logger.info("ValueDate Format " + aEodBankReqModel.getVALUEDATE());
				// 3
				aEodBankReqModel.setEffectiveDate(Formatdate);
				logger.info("EffectiveDate----->" + aEodBankReqModel.getEffectiveDate());
				// 4
				aEodBankReqModel
						.setAccountType(BOCCommonUtil.AccountTypeTranslate(rst.getString("ACCOUNTTYPE").trim()));
				logger.info("Account Type---->" + rst.getString("ACCOUNTTYPE"));
				// 5
				aEodBankReqModel.setBACKOFFICEACCOUNTNO(rst.getString("BackOfficeAccountNo"));
				logger.info("BackOfficeAccountNo  " + rst.getString("BackOfficeAccountNo"));
				// 6
				aEodBankReqModel.setPOSTINGAMOUNT(new BigDecimal(rst.getString("PostingAmount")));
				logger.info("PostingAmount  " + rst.getString("PostingAmount"));
				// 7
				aEodBankReqModel.setPOSTINGCCY(rst.getString("PostingCcy"));
				logger.info("PostingCcy  " + rst.getString("PostingCcy"));
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
				logger.info("Exchange Rate-----> " + aEodBankReqModel.getEXCHANGERATE());

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
				logger.info("MASTERREFERENCE  " + aEodBankReqModel.getMASTERREFERENCE());
				aEodBankReqModel
						.setAlphabeticData1(rst.getString("MASTERREFERENCE").concat(Constant.HIFEN).concat("EOD000"));
				logger.info("Alpha1--->" + aEodBankReqModel.getAlphabeticData1());
				aEodBankReqModel.setAlphabeticData2(CommonUtil.getSysDate(Constant.POSTING_DATE_FORMAT_WITH_TIME));
				logger.info("Alpha2---->" + aEodBankReqModel.getAlphabeticData2());
				aEodBankReqModel.setAlphabeticData3(Constant.SPACE);
				aEodBankReqModel.setTransactionTime(CommonUtil.getSysDate(Constant.POSTING_TIME_FORMAT));
				logger.info("Transaction Time---> " + aEodBankReqModel.getTransactionTime());

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

				aEodBankReqModel.setProcessingDate(CommonUtil.GetJulianDate());

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
				 * logger.info("PostingNarrative1  " + rst.getString("PostingNarrative1"));
				 * 
				 * aEodBankRequestModel.setFilename("EODFinacePosting" +
				 * rst.getString("ValueDate")); logger.info("EODFinacePosting  " +
				 * rst.getString("ValueDate"));
				 */
				EodBankReqList.add(aEodBankReqModel);
				aEodBankRequestModel.setEodBankReqModel(EodBankReqList);

				/*
				 * if (rst.getString("PostingCcy").equalsIgnoreCase(Constant.LOCAL_CURRENCY)) {
				 * logger.info("inside main if");
				 * aEodBankReqModel.setEXCHANGERATE(BigDecimal.ZERO);
				 * logger.info("EXCHANGERATE  "); } else { logger.info("inside main else");
				 * logger.info("Setting value"); String Exr; Exr =
				 * rst.getString("ExchangeRate"); logger.info("Setting value" + Exr); if
				 * (BOCCommonUtil.isValidString(Exr)) { logger.info("inside valid if");
				 * aEodBankReqModel.setEXCHANGERATE(BigDecimal.ZERO);
				 * logger.info("EXCHANGERATE  " + BigDecimal.ZERO); } else {
				 * logger.info("inside valid else"); aEodBankReqModel.setEXCHANGERATE(new
				 * BigDecimal(rst.getString("ExchangeRate"))); logger.info("ExchangeRate  " +
				 * rst.getString("ExchangeRate")); }
				 * 
				 * }
				 */
				}catch(Exception e) {
					System.out.println("Exception in master--->"+rst.getString("MASTERREFERENCE"));
				}
			}
			/*
			 * aEodBankRequestModel.setFilename("EOD" +
			 * StringUtils.leftPad(BOCCommonUtil.getNextSeqNo(Constant.EOD_BATCH_SEQUENCE),
			 * 7, '0'));
			 */
		} catch (Exception e) {
			logger.info("Exception in setting up the value");
			logger.info("Exception in setting up the value--->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return aEodBankRequestModel;
	}

	private static void setoutputfile(EodBankRequestModel aEodBankRequestModel, String pkey) {
		// File file= new File("E:\\test\\Batchposting.txt");
		logger.info("File reader");
		logger.info("Buffer reader");
		logger.info("Test Fix");
		File file = new File((BOCCommonUtil.retreiveFromProperties(Constant.POSTING_OUTPUT_PATH))
				+ (Constant.EOD_PRIFIX));
		logger.info("File reader");
		// FileWriter fr = null;
		BufferedWriter br = null;
		StringBuilder sb = new StringBuilder();
		logger.info("Buffer reader  test");
		String dataWithNewLine = System.getProperty("line.separator");
		// fr = new FileWriter(file);
		// br = new BufferedWriter(new FileWriter(file, false));
		logger.info("Buffer reader");
		try {
			logger.info("File Path--->" + BOCCommonUtil.retreiveFromProperties(Constant.POSTING_OUTPUT_PATH));
			br = new BufferedWriter(new FileWriter(file, false));

			for (EodBankReqModel br1 : aEodBankRequestModel.getEodBankReqModel()) {
				
				try {
				sb.append(StringUtils.leftPad(br1.getPOSTINGBRANCH().trim(), 5, Constant.ZERO));
				logger.info("POSTINGBRANCH" + br1.getPOSTINGBRANCH());
				sb.append(br1.getDebitCreditCode());
				sb.append(br1.getEffectiveDate());
				sb.append(br1.getAccountType().trim());
				int account,len;
				account = br1.getBACKOFFICEACCOUNTNO().indexOf("-");
				String bo_accountno = br1.getBACKOFFICEACCOUNTNO();
				System.out.println("BOAccountnumberindex  ...." + account);
				if (account > 0) {
					bo_accountno = br1.getBACKOFFICEACCOUNTNO().substring(0, account);
					len=bo_accountno.length();
					System.out.println(len);
					if(len>8)
					{
					bo_accountno = bo_accountno.substring(1);
					}
				}
				System.out.println("BOAccountnumber  ...." + bo_accountno);
				sb.append(StringUtils.leftPad(bo_accountno, 12, Constant.ZERO));
				// sb.append(br1.getBACKOFFICEACCOUNTNO());
				logger.info("BACKOFFICEACCOUNTNO" + br1.getBACKOFFICEACCOUNTNO());

				int tranAmt = br1.getPOSTINGAMOUNT().toString().indexOf(".");
				String tranAmtVal = br1.getPOSTINGAMOUNT().toString().replace(".", "");
				if (tranAmt > 0)
					tranAmtVal = tranAmtVal.substring(0, tranAmt+2);

				sb.append((StringUtils.leftPad(tranAmtVal, 17, Constant.ZERO)).substring(0, 17));
				// sb.append(StringUtils.rightPad(br1.getPOSTINGAMOUNT().toString(), 20,
				// Constant.SPACE));
				logger.info("getPOSTINGAMOUNT" + tranAmtVal);

				int lceAmt = br1.getLCEAmount().toString().indexOf(".");
				String lceAmtVal = br1.getLCEAmount().toString().replace(".", "");
				System.out.println("lceAmtVal--->"+br1.getLCEAmount().toString());
				System.out.println("lceAmt--->"+lceAmt);
				if (lceAmt > 0)
					lceAmtVal = lceAmtVal.substring(0, lceAmt+2);

				logger.info("lceAmtVal-->" + lceAmtVal);
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
				String s2 ="0";
				if(fx.toString().contains(".")) {
					s2= fx.toString().split("\\.")[1];
				}
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
				 * Constant.SPACE)); logger.info("getPOSTINGCCY" + br1.getPOSTINGCCY());
				 * sb.append(StringUtils.rightPad(br1.getEXCHANGERATE().toString(), 23,
				 * Constant.SPACE)); logger.info("getEXCHANGERATE" + br1.getEXCHANGERATE());
				 * sb.append(br1.getDEBITCREDITFLAG()); logger.info("getDEBITCREDITFLAG" +
				 * br1.getDEBITCREDITFLAG()); sb.append(br1.getVALUEDATE());
				 * logger.info("getVALUEDATE" + br1.getVALUEDATE());
				 * sb.append(StringUtils.rightPad(br1.getPOSTINGNARRATIVE1(), 40,
				 * Constant.SPACE)); logger.info("getPOSTINGNARRATIVE1" +
				 * br1.getPOSTINGNARRATIVE1()); sb.append(br1.getMASTERREFERENCE());
				 * logger.info("getMASTERREFERENCE" + br1.getMASTERREFERENCE());
				 */
				sb.append(dataWithNewLine);
				} catch (Exception e) {
					logger.info("Exception in writing the file for the masterRef--->"+br1.getMASTERREFERENCE());
					e.printStackTrace();
				}
			}
			br.write(sb.toString());
			br.flush();
		} catch (Exception e) {
			logger.info("Exception in writing the file--->");
			e.printStackTrace();
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
