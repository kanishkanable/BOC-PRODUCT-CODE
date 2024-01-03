package com.ett.limitupdate;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ett.util.BOCCommonUtil;
import com.ett.util.Constants;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;

public class LimitImpl {

	public void doLimitUpdateCore(String customerID) {

		LimitTIRequestModel aLimitTIRequestModel = new LimitTIRequestModel();

		aLimitTIRequestModel = setValuesfromRequest(customerID);

		ProgramParameter[] bankRequest = getBankRequest(aLimitTIRequestModel);

		callAS400(bankRequest);

	}

	private ProgramParameter[] getBankRequest(LimitTIRequestModel aLimitTIRequestModel) {

		ProgramParameter[] parmList = new ProgramParameter[12];

		try {

			AS400Text ParamCIF = new AS400Text(10);
			AS400Text ParamFACID = new AS400Text(5);
			AS400Text ParamCURCODE = new AS400Text(3);
			AS400Text ParamEXPDT = new AS400Text(8);
			AS400Text ParamALIMF = new AS400Text(7);
			AS400Text ParamOBALF = new AS400Text(7);
			AS400Text ParamABALF = new AS400Text(7);
			AS400Text ParamALIML = new AS400Text(7);
			AS400Text ParamOBALL = new AS400Text(7);
			AS400Text ParamABALL = new AS400Text(7);
			AS400Text ParamREVFLAG = new AS400Text(1);
			AS400Text ParamCANFL = new AS400Text(1);

			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getCustomerNo(), 10, Constants.ZERO));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getFacilityID(), 5, Constants.ZERO));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getCurrCode(), 3, Constants.ZERO));
			System.out.println(aLimitTIRequestModel.getExpDate());
			System.out.println(
					StringUtils.leftPad(aLimitTIRequestModel.getApprovedLineAmt(), 7, Constants.ZERO).substring(0, 7));
			System.out.println(
					StringUtils.leftPad(aLimitTIRequestModel.getOutstandingBal(), 7, Constants.ZERO).substring(0, 7));
			System.out.println(
					StringUtils.leftPad(aLimitTIRequestModel.getAvailLineAmt(), 7, Constants.ZERO).substring(0, 7));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getApprovedLineAmtLCY(), 7, Constants.ZERO)
					.substring(0, 7));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getOutstandingBalLCY(), 7, Constants.ZERO)
					.substring(0, 7));
			System.out.println(
					StringUtils.leftPad(aLimitTIRequestModel.getAvailLineAmt(), 7, Constants.ZERO).substring(0, 7));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getRevolvingFlag(), 1, Constants.ZERO));
			System.out.println(StringUtils.leftPad(aLimitTIRequestModel.getCancelFlag(), 1, Constants.ZERO));
			parmList[0] = new ProgramParameter(
					ParamCIF.toBytes(StringUtils.leftPad(aLimitTIRequestModel.getCustomerNo(), 10, Constants.ZERO)),
					10);
			parmList[1] = new ProgramParameter(
					ParamFACID.toBytes(StringUtils.leftPad(aLimitTIRequestModel.getFacilityID(), 5, Constants.ZERO)),
					5);
			parmList[2] = new ProgramParameter(
					ParamCURCODE.toBytes(StringUtils.leftPad(aLimitTIRequestModel.getCurrCode(), 3, Constants.ZERO)),
					3);
			parmList[3] = new ProgramParameter(ParamEXPDT.toBytes(aLimitTIRequestModel.getExpDate()), 8);
			parmList[4] = new ProgramParameter(ParamALIMF.toBytes(
					StringUtils.leftPad(aLimitTIRequestModel.getApprovedLineAmt(), 7, Constants.ZERO).substring(0, 7)),
					7);
			parmList[5] = new ProgramParameter(ParamOBALF.toBytes(
					StringUtils.leftPad(aLimitTIRequestModel.getOutstandingBal(), 7, Constants.ZERO).substring(0, 7)),
					7);
			parmList[6] = new ProgramParameter(ParamABALF.toBytes(
					StringUtils.leftPad(aLimitTIRequestModel.getAvailLineAmt(), 7, Constants.ZERO).substring(0, 7)), 7);
			parmList[7] = new ProgramParameter(
					ParamALIML.toBytes(StringUtils
							.leftPad(aLimitTIRequestModel.getApprovedLineAmtLCY(), 7, Constants.ZERO).substring(0, 7)),
					7);
			parmList[8] = new ProgramParameter(
					ParamOBALL.toBytes(StringUtils
							.leftPad(aLimitTIRequestModel.getOutstandingBalLCY(), 7, Constants.ZERO).substring(0, 7)),
					7);
			parmList[9] = new ProgramParameter(ParamABALL.toBytes(
					StringUtils.leftPad(aLimitTIRequestModel.getAvailLineAmt(), 7, Constants.ZERO).substring(0, 7)), 7);
			parmList[10] = new ProgramParameter(ParamREVFLAG
					.toBytes(StringUtils.leftPad(aLimitTIRequestModel.getRevolvingFlag(), 1, Constants.ZERO)), 1);
			parmList[11] = new ProgramParameter(
					ParamCANFL.toBytes(StringUtils.leftPad(aLimitTIRequestModel.getCancelFlag(), 1, Constants.ZERO)),
					1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return parmList;
	}

	private String callAS400(ProgramParameter[] bankRequest) {
		String responseMessage = null;

		String hostName = BOCCommonUtil.retreiveFromProperties(Constants.LIMIT_HOST);
		String userID = BOCCommonUtil.retreiveFromProperties(Constants.LIMIT_USER);
		String passCode = BOCCommonUtil.retreiveFromProperties(Constants.LIMIT_PASS);
		String progName = BOCCommonUtil.retreiveFromProperties(Constants.LIMIT_PROGRAM);
		AS400 equSystem = null;
		ProgramCall program = null;

		try {

			equSystem = new AS400(hostName, userID, passCode);
			System.out.println("<------Before Program Call");
			program = new ProgramCall(equSystem);
			System.out.println("After Program Call------>");
			System.out.println("programName is " + progName);
			ProgramParameter[] paramList = bankRequest;

			// Set Program Name and Parameter List
			program.setProgram(progName, paramList);
			System.out.println("Before Run method");
			// Run
			System.out.println("Program Initiated");

			// Run the program.
			if (!program.run()) {

				System.out.println("Program Not Executed");
				/**
				 * If the AS/400 is not run then look at the message list to find out why it
				 * didn't run.
				 */
				AS400Message[] messageList = program.getMessageList();
				for (AS400Message message : messageList) {
					System.out.println(message.getID() + " - " + message.getText());
				}
			} else {
				/**
				 * Else the program is successfull. Process the output, which contains the
				 * returned data.
				 */
				System.out.println("Program Called");

//    outputData = parmList[1].getOutputData();
//    String output = new String(outputData, "IBM285").trim();
//
//    System.out.println("Output is " + output);
			}

		} catch (Exception e) {
			System.out.println("Program " + program.getProgram() + " issued an exception!");
			e.printStackTrace();
		}
		// Cloing the connection
		equSystem.disconnectAllServices();

		return responseMessage;
	}

	private LimitTIRequestModel setValuesfromRequest(String customerID) {
		LimitTIRequestModel aLimitTIRequestModel = new LimitTIRequestModel();

		List<String> limits = new LinkedList<String>();
		try {

			aLimitTIRequestModel.setCustomerNo(customerID);
			limits = BOCCommonUtil.getLimitVal(customerID);
			String Currency = limits.get(2);
			String outAmt = limits.get(1);
			String lineAmt = limits.get(0);
			String expDate = limits.get(3);
			aLimitTIRequestModel.setFacilityID("OV" + Currency);
			aLimitTIRequestModel.setCurrCode(Currency);
			aLimitTIRequestModel.setExpDate(expDate);
			aLimitTIRequestModel.setApprovedLineAmt(lineAmt);
			aLimitTIRequestModel.setOutstandingBal(outAmt);
			aLimitTIRequestModel.setAvailLineAmt(outAmt);
			aLimitTIRequestModel.setRevolvingFlag("0");
			aLimitTIRequestModel.setCancelFlag("N");

			if (!(Currency.equalsIgnoreCase(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY)))) {
				BigDecimal spotRate = BOCCommonUtil.getSpotRate(Currency,
						BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));

				aLimitTIRequestModel.setApprovedLineAmtLCY((spotRate.multiply(new BigDecimal(lineAmt))).toString());
				aLimitTIRequestModel.setOutstandingBalLCY((spotRate.multiply(new BigDecimal(outAmt))).toString());
			} else {
				aLimitTIRequestModel.setApprovedLineAmtLCY(lineAmt);
				aLimitTIRequestModel.setOutstandingBalLCY(outAmt);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return aLimitTIRequestModel;
	}

}
