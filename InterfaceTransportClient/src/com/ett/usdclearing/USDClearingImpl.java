package com.ett.usdclearing;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import com.boc.usdclearing.Field;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.ett.util.QueryConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.tiplus2.services.control.StatusEnum;

public class USDClearingImpl {

	public String sendUSDClearing(String tiRequestXML) {

		USDClearingBankRequest aUSDClearingBankRequest = new USDClearingBankRequest();

		String pkey = BOCCommonUtil.getNextSeqNo(Constants.USD_CLEARING_SEQUENCE);
		BOCCommonUtil.insertTIReqToLogTable(tiRequestXML, Constants.USD_CLEARING_TABLE, Constants.STATUS_RECEIVED,
				pkey);

		aUSDClearingBankRequest = getValuesfromTI(tiRequestXML);

		BOCCommonUtil.updateReferenceinLog(aUSDClearingBankRequest.getMasterReference(),
				aUSDClearingBankRequest.getEventReference(), Constants.USD_CLEARING_TABLE, pkey);

		String bankRequest = getBankRequest(aUSDClearingBankRequest);

		System.out.println("update bankRequest======>" + bankRequest);
		String tiResponseXML = sendRequesttoBank(bankRequest);
		BOCCommonUtil.updateTIResToLogTable(tiResponseXML, Constants.USD_CLEARING_TABLE,
				StatusEnum.SUCCEEDED.toString(), pkey);

		tiResponseXML = BOCCommonUtil.constructTIResponseXML(tiRequestXML, Constants.STATUS_SUCCEEDED);
		return tiResponseXML;

	}

	private String sendRequesttoBank(String bankRequest) {

		String bankResponse = null;

		try {

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(BOCCommonUtil.retreiveFromProperties(Constants.USD_CLEARING_URL));
			StringEntity params = new StringEntity(bankRequest);
			post.setEntity(params);
			post.setHeader("Content-type", "application/json");
			//post.setHeader("apiKey", "asdasdasdasdasdasdasd123asdasdas");
			post.setHeader("apiKey",BOCCommonUtil.retreiveFromProperties("apiKey"));
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			bankResponse = EntityUtils.toString(entity);
			System.out.println("USD CLEARING Bank Response -->" + bankResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankResponse;
	}

	private String getBankRequest(USDClearingBankRequest aUSDClearingBankRequest) {

		String bankRequest = null;
		System.out.println("Entered into get bank request ");

//		ObjectMapper om = new ObjectMapper();
		try {
//			Field field = new Field();

//			field.setReference(aUSDClearingBankRequest.getCustDesc());
//			field.setDestBnk(aUSDClearingBankRequest.getDestBank());
//			field.setDestBnkNm(aUSDClearingBankRequest.getDestBankName().trim());
//			field.setDestBrchNm(aUSDClearingBankRequest.getDestBranchName());
//			field.setDestAcc(aUSDClearingBankRequest.getDestAcc());
//			field.setDestBrch(aUSDClearingBankRequest.getDestBranch());
//			field.setDestName(aUSDClearingBankRequest.getDestName());
//			field.setDestAdd1(aUSDClearingBankRequest.getDestAddress1());
//			field.setDestAdd2(aUSDClearingBankRequest.getDestAddress2());
//			field.setDestAdd3(aUSDClearingBankRequest.getDestAddress3());
//			field.setTxnAmount(aUSDClearingBankRequest.getTranAmount());
//			field.setFee(aUSDClearingBankRequest.getFee());
//			field.setTxnAmtCurr(aUSDClearingBankRequest.getTranAmtCurr());
//			field.setTxnCurr(aUSDClearingBankRequest.getTranCurr());
//			field.setOrgAcc(aUSDClearingBankRequest.getOrgAcc());
//			field.setOrgAccType(aUSDClearingBankRequest.getOrgAccType());
//			field.setOrgAccCus(aUSDClearingBankRequest.getOrgAccCus());
//			field.setOrgAccBrch(aUSDClearingBankRequest.getOrgAccBranch());
//			field.setOrgAccCurr(aUSDClearingBankRequest.getOrgAccCurr());
//			field.setOrgBrch(aUSDClearingBankRequest.getOrgBranch());
//			field.setCustDesc(aUSDClearingBankRequest.getCustDesc());
//			field.setPurCode(aUSDClearingBankRequest.getPurposeCode());
//			field.setClrType(aUSDClearingBankRequest.getClrType());
//			field.setTxnType(aUSDClearingBankRequest.getTxnType());
//			field.setOrgApp(aUSDClearingBankRequest.getOrgApp());
//			field.setValDate(aUSDClearingBankRequest.getValdate());
			
			
		
//			field.setReference(aUSDClearingBankRequest.getCustDesc().trim());
//			System.out.println("==1==");
//			field.setDestBnk(aUSDClearingBankRequest.getDestBank().trim());
//			System.out.println("==2==");
//			
//			field.setDestBnkNm(aUSDClearingBankRequest.getDestBankName().trim());
//			System.out.println("==3==");
//			
//			field.setDestBrchNm(aUSDClearingBankRequest.getDestBranchName());
//			System.out.println("==4==");
//			
//			
//			field.setDestAcc(aUSDClearingBankRequest.getDestAcc().trim());
//			System.out.println("==5==");
//			
//			
//			field.setDestBrch(aUSDClearingBankRequest.getDestBranch().trim());
//			System.out.println("==6==");
//			
//			
//			field.setDestName(aUSDClearingBankRequest.getDestName().trim());
//			System.out.println("==7==");
//			
//			field.setTxnAmount(aUSDClearingBankRequest.getTranAmount());
//			System.out.println("==8==");
//			
//			field.setFee(aUSDClearingBankRequest.getFee());
//			System.out.println("==9==");
//			
//			field.setTxnAmtCurr(aUSDClearingBankRequest.getTranAmtCurr());
//			System.out.println("==10==");
//			
//			field.setTxnCurr(aUSDClearingBankRequest.getTranCurr());
//			System.out.println("==11==");
//			
//			field.setOrgAcc(aUSDClearingBankRequest.getOrgAcc().trim());
//			System.out.println("==12==");
//			
//			field.setOrgAccType(aUSDClearingBankRequest.getOrgAccType());
//			System.out.println("==13==");
//			
//			field.setOrgAccCus(aUSDClearingBankRequest.getOrgAccCus().trim());
//			System.out.println("==14==");
//			
//			field.setOrgAccBrch(aUSDClearingBankRequest.getOrgAccBranch().trim());
//			System.out.println("==15==");
//			
//			field.setOrgAccCurr(aUSDClearingBankRequest.getOrgAccCurr());
//			System.out.println("==16==");
//			
//			field.setOrgBrch(aUSDClearingBankRequest.getOrgBranch());
//			System.out.println("==17==");
//			
//			
//			field.setCustDesc(aUSDClearingBankRequest.getCustDesc().trim());
//			System.out.println("==18==");
//			
//			field.setPurCode(aUSDClearingBankRequest.getPurposeCode());
//			System.out.println("==19==");
//			
//			
//			field.setClrType(aUSDClearingBankRequest.getClrType());
//			System.out.println("==20==");
//			
//			field.setTxnType(aUSDClearingBankRequest.getTxnType());
//			System.out.println("==21==");
//			
//			field.setOrgApp(aUSDClearingBankRequest.getOrgApp().trim());
//			System.out.println("==22==");
//			
//			field.setValDate(aUSDClearingBankRequest.getValdate());
//			System.out.println("==23==");
//			
//			field.setDestAdd1(aUSDClearingBankRequest.getDestAddress1().trim());
//			System.out.println("==24==");
//			
//			field.setDestAdd2(aUSDClearingBankRequest.getDestAddress2().trim());
//			System.out.println("==25==");
//			
//			field.setDestAdd3(aUSDClearingBankRequest.getDestAddress3().trim());
//			System.out.println("==26==");
//			
//			field.setOrgAccName(aUSDClearingBankRequest.getOrgAccName());
//			System.out.println("==27==");
//			
//			field.setOrgAccAdd1(aUSDClearingBankRequest.getOrgAccAdd1());
//			System.out.println("==28==");
//			
//			field.setOrgAccAdd2(aUSDClearingBankRequest.getOrgAccAdd2());
//			System.out.println("==29==");
//			
//			field.setOrgAccAdd3(aUSDClearingBankRequest.getOrgAccAdd3());
//			System.out.println("==30==");
//			
//			field.setSubPurposeCode(aUSDClearingBankRequest.getSubPurposeCode());
//			System.out.println("==31==");
//			bankRequest = om.writeValueAsString(field);
			
			
			
			
			
			
			
			
			String openbracket = "{";

			String Reference = " \"Reference\": ";
			String Reference1 = aUSDClearingBankRequest.getCustDesc().trim();
			String setReference = "\"" + Reference1 + "\"" + ",";

			String DestBnk = " \"DestBnk\": ";
			String DestBnk1 = aUSDClearingBankRequest.getDestBank().trim();
			String setDestBnk = "\"" + DestBnk1 + "\"" + ",";

			String DestBnkNm = " \"DestBnkNm\": ";
			String DestBnkNm1 = aUSDClearingBankRequest.getDestBankName().trim();
			String setDestBnkNm = "\"" + DestBnkNm1 + "\"" + ",";

			String DestBrchNm = " \"DestBrchNm\": ";
			String DestBrchNm1 = aUSDClearingBankRequest.getDestBranchName();
			String setDestBrchNm = "\"" + DestBrchNm1 + "\"" + ",";

			String DestAcc = " \"DestAcc\": ";
			String DestAcc1 = aUSDClearingBankRequest.getDestAcc().trim();
			String setDestAcc = "\"" + DestAcc1 + "\"" + ",";

			String DestBrch = " \"DestBrch\": ";
			String DestBrch1 = aUSDClearingBankRequest.getDestBranch().trim();
			String setDestBrch = "\"" + DestBrch1 + "\"" + ",";

			String DestName = " \"DestName\": ";
			String DestName1 = aUSDClearingBankRequest.getDestName().trim();
			String setDestName = "\"" + DestName1 + "\"" + ",";

			String TxnAmount = " \"TxnAmount\": ";
			String TxnAmount1 = aUSDClearingBankRequest.getTranAmount();
			String setTxnAmount = "\"" + TxnAmount1 + "\"" + ",";

			String Fee = " \"Fee\": ";
			String Fee1 = aUSDClearingBankRequest.getFee();
			String setFee = "\"" + Fee1 + "\"" + ",";

			String TxnAmtCurr = " \"TxnAmtCurr\": ";
			String TxnAmtCurr1 = aUSDClearingBankRequest.getTranAmtCurr();
			String setTxnAmtCurr = "\"" + TxnAmtCurr1 + "\"" + ",";

			String TxnCurr = " \"TxnCurr\": ";
			String TxnCurr1 = aUSDClearingBankRequest.getTranCurr();
			String setTxnCurr = "\"" + TxnCurr1 + "\"" + ",";

			String OrgAcc = " \"OrgAcc\": ";
			String OrgAcc1 = aUSDClearingBankRequest.getOrgAcc().trim();
			String setOrgAcc = "\"" + OrgAcc1 + "\"" + ",";

			String OrgAccType = " \"OrgAccType\": ";
			String OrgAccType1 = aUSDClearingBankRequest.getOrgAccType();
			String setOrgAccType = "\"" + OrgAccType1 + "\"" + ",";

			String OrgAccCus = " \"OrgAccCus\": ";
			String OrgAccCus1 = aUSDClearingBankRequest.getOrgAccCus().trim();
			String setOrgAccCus = "\"" + OrgAccCus1 + "\"" + ",";

			String OrgAccBrch = " \"OrgAccBrch\": ";
			String OrgAccBrch1 = aUSDClearingBankRequest.getOrgAccBranch().trim();
			String setOrgAccBrch = "\"" + OrgAccBrch1 + "\"" + ",";

			String OrgAccCurr = " \"OrgAccCurr\": ";
			String OrgAccCurr1 = aUSDClearingBankRequest.getOrgAccCurr();
			String setOrgAccCurr = "\"" + OrgAccCurr1 + "\"" + ",";

			String OrgBrch = " \"OrgBrch\": ";
			String OrgBrch1 = aUSDClearingBankRequest.getOrgBranch();
			String setOrgBrch = "\"" + OrgBrch1 + "\"" + ",";

			String CustDesc = " \"CustDesc\": ";
			String CustDesc1 = aUSDClearingBankRequest.getCustDesc().trim();
			String setCustDesc = "\"" + CustDesc1 + "\"" + ",";

			String PurCode = " \"PurCode\": ";
			String PurCode1 = aUSDClearingBankRequest.getPurposeCode();
			String setPurCode = "\"" + PurCode1 + "\"" + ",";

			String ClrType = " \"ClrType\": ";
			String ClrType1 = aUSDClearingBankRequest.getClrType();
			String setClrType = "\"" + ClrType1 + "\"" + ",";

			String TxnType = " \"TxnType\": ";
			String TxnType1 = aUSDClearingBankRequest.getTxnType();
			String setTxnType = "\"" + TxnType1 + "\"" + ",";

			String OrgApp = " \"OrgApp\": ";
			String OrgApp1 = aUSDClearingBankRequest.getOrgApp().trim();
			String setOrgApp = "\"" + OrgApp1 + "\"" + ",";

			String ValDate = " \"ValDate\": ";
			String ValDate1 = aUSDClearingBankRequest.getValdate();
			String setValDate = "\"" + ValDate1 + "\"" + ",";

			String DestAdd1 = " \"DestAdd1\": ";
			String DestAdd11 = aUSDClearingBankRequest.getDestAddress1().trim();
			String setDestAdd1 = "\"" + DestAdd11 + "\"" + ",";

			String DestAdd2 = " \"DestAdd2\": ";
			String DestAdd21 = aUSDClearingBankRequest.getDestAddress2().trim();
			String setDestAdd2 = "\"" + DestAdd21 + "\"" + ",";

			String DestAdd3 = " \"DestAdd3\": ";
			String DestAdd31 = aUSDClearingBankRequest.getDestAddress3().trim();
			String setDestAdd3 = "\"" + DestAdd31 + "\"" + ",";

			String OrgAccName = " \"OrgAccName\": ";
			String OrgAccName1 = aUSDClearingBankRequest.getOrgAccName();
			String setOrgAccName = "\"" + OrgAccName1 + "\"" + ",";

			String OrgAccAdd1 = " \"OrgAccAdd1\": ";
			String OrgAccAdd11 = aUSDClearingBankRequest.getOrgAccAdd1();
			String setOrgAccAdd1 = "\"" + OrgAccAdd11 + "\"" + ",";

			String OrgAccAdd2 = " \"OrgAccAdd2\": ";
			String OrgAccAdd21 = aUSDClearingBankRequest.getOrgAccAdd2();
			String setOrgAccAdd2 = "\"" + OrgAccAdd21 + "\"" + ",";

			String OrgAccAdd3 = " \"OrgAccAdd3\": ";
			String OrgAccAdd31 = aUSDClearingBankRequest.getOrgAccAdd3();
			String setOrgAccAdd3 = "\"" + OrgAccAdd31 + "\"" + ",";

			String SubPurposeCode = " \"SubPurCode\": ";
			String SubPurposeCode1 = aUSDClearingBankRequest.getSubPurposeCode();
			String setSubPurposeCode = "\"" + SubPurposeCode1 + "\"";

			String closebracket = "}";
			bankRequest = openbracket + Reference + setReference + DestBnk + setDestBnk + DestBnkNm + setDestBnkNm +

					DestBrchNm + setDestBrchNm + DestAcc + setDestAcc + DestBrch + setDestBrch + DestName + setDestName
					+ TxnAmount + setTxnAmount +

					Fee + setFee + TxnAmtCurr + setTxnAmtCurr + TxnCurr + setTxnCurr + OrgAcc + setOrgAcc + OrgAccType
					+ setOrgAccType + OrgAccCus + setOrgAccCus +

					OrgAccBrch + setOrgAccBrch + OrgAccCurr + setOrgAccCurr + OrgBrch + setOrgBrch + CustDesc
					+ setCustDesc +

					PurCode + setPurCode + ClrType + setClrType + TxnType + setTxnType + OrgApp + setOrgApp +

					ValDate + setValDate + DestAdd1 + setDestAdd1 + DestAdd2 + setDestAdd2 + DestAdd3 + setDestAdd3 +

					OrgAccName + setOrgAccName +OrgAccAdd1+setOrgAccAdd1+ OrgAccAdd2 + setOrgAccAdd2 + OrgAccAdd3 + setOrgAccAdd3
					+ SubPurposeCode + setSubPurposeCode +

					closebracket;
			
			
				
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Bank Request===->" + e.getMessage());
		}

		System.out.println("USD Clearing Bank Request is -->" + bankRequest);
		return bankRequest;
	}

	private USDClearingBankRequest getValuesfromTI(String tiRequestXML) {

		USDClearingBankRequest aUSDClearingBankRequest = new USDClearingBankRequest();

		try {

			System.out.println("Entered getValuesfromTI Method ");

			aUSDClearingBankRequest.setMasterReference(CommonUtil.getTagValue("Master_Refernce", tiRequestXML));
			aUSDClearingBankRequest.setEventReference(CommonUtil.getTagValue("Event_refernce", tiRequestXML));
			aUSDClearingBankRequest.setDestBank(CommonUtil.getTagValue("DestinationBank", tiRequestXML));
			
			aUSDClearingBankRequest.setDestBankName(CommonUtil.getTagValue("DestinationBankName", tiRequestXML));

			aUSDClearingBankRequest.setDestBranchName(".");
			aUSDClearingBankRequest.setDestAcc(CommonUtil.getTagValue("Destinationaccount", tiRequestXML));
			// String Destinationaccount = CommonUtil.getTagValue("Destinationaccount",
			// tiRequestXML);
			aUSDClearingBankRequest.setDestBranch("0");
			aUSDClearingBankRequest.setDestName(CommonUtil.getTagValue("DestinationName", tiRequestXML));

			String val = CommonUtil.getTagValue("TransactionAmount", tiRequestXML);
			String transAmt = val.substring(0, val.indexOf(' ')).replace(",", "");
			// String tranCurr = val.substring(val.indexOf(' ') + 1);
			// System.out.println("tranCurr=====>" + tranCurr);
			aUSDClearingBankRequest.setTranAmount(transAmt);
			aUSDClearingBankRequest.setFee("0.00");
			aUSDClearingBankRequest.setTranAmtCurr(transAmt);

			if (CommonUtil.getTagValue("ClrType", tiRequestXML).equalsIgnoreCase("USDClearing")) {
				aUSDClearingBankRequest.setTranCurr("USD");
			} else {
				aUSDClearingBankRequest.setTranCurr("LKR");
			}
			

			aUSDClearingBankRequest.setOrgAcc(CommonUtil.getTagValue("OriginatingAccount", tiRequestXML));

			String OriginatingAccount = aUSDClearingBankRequest.getOrgAcc();
			System.out.println("OriginatingAccount=====>" + OriginatingAccount);
			
			String NewAccountType="";
			String AccountType= BOCCommonUtil.executeQuery(QueryConstants.ORG_ACTYPE + OriginatingAccount + "'");
			System.out.println("OriginatingAccount AccountType===1==>" + AccountType);
			if (AccountType.equalsIgnoreCase("SA")) {
				NewAccountType = "SV";
				aUSDClearingBankRequest	.setOrgAccType(NewAccountType);
			} else if (AccountType.equalsIgnoreCase("CA")) {
				NewAccountType = "DD";
				aUSDClearingBankRequest	.setOrgAccType(NewAccountType);
			} else {
				NewAccountType = "GL";
				aUSDClearingBankRequest	.setOrgAccType(NewAccountType);
			}
			System.out.println("OriginatingAccount NewAccountType===2==>" + NewAccountType);
			aUSDClearingBankRequest
					.setOrgAccCus(BOCCommonUtil.executeQuery(QueryConstants.ORG_CUS_MNM + OriginatingAccount + "'"));
			String Cust_acct_brch = BOCCommonUtil.executeQuery(QueryConstants.Cust_acct_brch + OriginatingAccount + "'");
			if (!Cust_acct_brch.equalsIgnoreCase("")) {
				String OrgAccBranchValue = Cust_acct_brch.substring(1);
				System.out.println("OrgAccBranchValue==1===" + OrgAccBranchValue);
				aUSDClearingBankRequest.setOrgAccBranch(OrgAccBranchValue);
			}
			aUSDClearingBankRequest
					.setOrgAccCurr(BOCCommonUtil.executeQuery(QueryConstants.ORG_CURRENCY + OriginatingAccount + "'"));
			String orgBranch = CommonUtil.getTagValue("OrgBranch", tiRequestXML);
			if (!orgBranch.equalsIgnoreCase("")) {
				String orgBrachValue = orgBranch.substring(1);
				System.out.println("orgBrachValue===" + orgBrachValue);
				aUSDClearingBankRequest.setOrgBranch(orgBrachValue);
				System.out.println("");
			}

			aUSDClearingBankRequest.setCustDesc(
					aUSDClearingBankRequest.getMasterReference() + aUSDClearingBankRequest.getEventReference());
			System.out.println("Get reference number===" + aUSDClearingBankRequest.getCustDesc());
			String purpose = CommonUtil.getTagValue("PurposeCode", tiRequestXML);
			if (!purpose.equalsIgnoreCase("")) {
				System.out.println("purpose====" + purpose);
				String purposeCode = purpose.substring(0, 4);
				aUSDClearingBankRequest.setPurposeCode(purposeCode);
				System.out.println("");
			}
			System.out.println("purpose====" + aUSDClearingBankRequest.getPurposeCode());

			if (CommonUtil.getTagValue("ClrType", tiRequestXML).equalsIgnoreCase("USDClearing")) {
				aUSDClearingBankRequest.setClrType("U");

				System.out.println("ClrType=1===");
			} else if (CommonUtil.getTagValue("ClrType", tiRequestXML).equalsIgnoreCase("SLIPS")) {
				aUSDClearingBankRequest.setClrType("S");
			} else if (CommonUtil.getTagValue("ClrType", tiRequestXML).equalsIgnoreCase("CEFTS")) {
				aUSDClearingBankRequest.setClrType("C");
				System.out.println("ClrType==2==");
			}
			aUSDClearingBankRequest.setTxnType(CommonUtil.getTagValue("TranType", tiRequestXML));
			System.out.println("setTxnType=3==");
//			aUSDClearingBankRequest.setRtnCode("00");

			aUSDClearingBankRequest.setOrgApp("TF");
			System.out.println("setOrgApp=4==");
			aUSDClearingBankRequest.setValdate(CommonUtil.getTagValue("ValDate", tiRequestXML));
			System.out.println("setValdate=5==");
			String orgCustName = aUSDClearingBankRequest.getOrgAccCus();
			System.out.println("OrgaAccName=6==");
			aUSDClearingBankRequest
					.setOrgAccName(BOCCommonUtil.executeQuery(QueryConstants.ORGCUS_NAME + orgCustName + "'"));

			System.out.println("orgCustName=7==");
			aUSDClearingBankRequest
					.setOrgAccAdd1(BOCCommonUtil.executeQuery(QueryConstants.DESTADD1 + orgCustName + "'"));
			System.out.println("orgCustName=8==" + aUSDClearingBankRequest.getOrgAccAdd1());
			aUSDClearingBankRequest
					.setOrgAccAdd2(BOCCommonUtil.executeQuery(QueryConstants.DESTADD2 + orgCustName + "'"));
			System.out.println("orgCustName=9==" + aUSDClearingBankRequest.getOrgAccAdd2());
			aUSDClearingBankRequest
					.setOrgAccAdd3(BOCCommonUtil.executeQuery(QueryConstants.DESTADD3 + orgCustName + "'"));
			System.out.println("orgCustName=10==" + aUSDClearingBankRequest.getOrgAccAdd3());
			aUSDClearingBankRequest.setDestAddress1("SRI LANKA");
			aUSDClearingBankRequest.setDestAddress2(".");
			aUSDClearingBankRequest.setDestAddress3(".");
			System.out.println("purpose=11==" + purpose);

			aUSDClearingBankRequest.setSubPurposeCode(purpose);
			System.out.println("Completed getValuesfromTI Method");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception in aUSDClearingBankRequest==>" + e.getMessage());
		}

		return aUSDClearingBankRequest;
	}

}
