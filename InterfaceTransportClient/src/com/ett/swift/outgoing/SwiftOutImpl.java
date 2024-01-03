package com.ett.swift.outgoing;

import java.util.LinkedList;
import java.util.List;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.foundations.lang.logging.Loggers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwiftOutImpl {

	private static final Logger LOG = LoggerFactory.getLogger(SwiftOutImpl.class);

//	public void processSWIFTOut(String swiftOut, List<String> formattedSwiftOut)
	public String processSWIFTOut(String swiftOut, List<String> formattedSwiftOut)

	{

		Loggers.general().info(LOG, "Entered into SWIFTOUT Process");

		String response = "<?xml version=\"1.0\" standalone=\"yes\"?>\r\n"
				+ "<ServiceResponse xmlns='urn:control.services.tiplus2.misys.com'>\r\n" + " <ResponseHeader>\r\n"
				+ " <Service>SWIFT</Service>\r\n" + " <Operation>SwiftOut</Operation>\r\n"
				+ " <Status>SUCCEEDED</Status>\r\n" + " <CorrelationId>{CorrelationID}</CorrelationId>\r\n"
				+ " </ResponseHeader>\r\n" + "</ServiceResponse>";
		SwiftOutRequestModel aSwiftOutRequestModel = new SwiftOutRequestModel();
		String swiftStatus = Constants.STATUS_SENT;
		String pkey = BOCCommonUtil.getNextSeqNo(Constants.SWIFTOUT_SEQUENCE);
		boolean transmitFlag = true;
		String swiftMessageResponse = "";

		try {

			/*
			 * BOCCommonUtil.insertTIReqToLogTable(swiftOut,
			 * Constants.SWIFTOUT_STAGING_TABLE, Constants.STATUS_SUCCEEDED, pkey);
			 */

			Loggers.general().info(LOG, "Extracting correlationId");
			String correlationId = CommonUtil.getTagValue(Constants.CORRELATIONID, swiftOut);

			Loggers.general().info(LOG, "correlationId" + correlationId);

			Loggers.general().info(LOG, "Extracting Reference");
			aSwiftOutRequestModel = getReferencefromDB(correlationId);

			Loggers.general().info(LOG, "Extracting Reference end");

			boolean postingAvailable = BOCCommonUtil.getpostings(aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference());

			BOCCommonUtil.insertTIReqToLogTable(swiftOut, Constants.SWIFTOUT_STAGING_TABLE, Constants.STATUS_SUCCEEDED,
					pkey);

			if (BOCCommonUtil.rtgsTransaction(aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference()))
				formattedSwiftOut = getRTGSSwift(formattedSwiftOut);
			// String
			// postingcheck=BOCCommonUtil.retreiveFromProperties(Constants.PostingCheck);
			System.out.println("Posting Check Before");
			if ((BOCCommonUtil.retreiveFromProperties(Constants.PostingCheck)).equalsIgnoreCase("YES")) {
				System.out.println("Posting Check");
				if (postingAvailable) {
					swiftStatus = Constants.STATUS_HOLD;
					Loggers.general().info(LOG, "Posting Availabel");
				}
			}
			if (swiftStatus.equalsIgnoreCase(Constants.STATUS_SENT)) {
				for (int i = 0; i < formattedSwiftOut.size(); i++) {
					/*
					 * BOCCommonUtil.sendMessagetoMQ(formattedSwiftOut.get(i),
					 * BOCCommonUtil.retreiveFromProperties(Constants.SWIFT_OUT_QUEUE),
					 * BOCCommonUtil.retreiveFromProperties(Constants.SWIFTQUEUE_CONNECTION_FACTORY)
					 * );
					 */
					// added u0001 and u0003
					if ((BOCCommonUtil.retreiveFromProperties(Constants.SwiftTestBic)).equalsIgnoreCase("YES")) {
						swiftOut = updateTestBics(formattedSwiftOut.get(i));
					} else {
						System.out.println("Swift Without Test BIC");
						swiftOut = formattedSwiftOut.get(i);
						// System.out.println("Swift "+swiftOut);
					}
					if ((BOCCommonUtil.retreiveFromProperties(Constants.SwiftMqEncode)).equalsIgnoreCase("YES")) {
						// BOCCommonUtil.writeMQMessage("\u0001" + swiftOut + "\u0003");

						// by Arun TLS
						System.out.println("Swift Without Test BIC====" + swiftOut);
						transmitFlag = BOCCommonUtil.pushMessage("\u0001" + swiftOut + "\u0003");
					} else {
						System.out.println("Swift Without Test BIC==else==" + swiftOut);

						transmitFlag = BOCCommonUtil.pushMessage(swiftOut);
						// BOCCommonUtil.writeMQMessage(swiftOut);
					}


					String swiftPkey = BOCCommonUtil.getNextSeqNo(Constants.SWIFTPKEY_SEQUENCE);
					if (swiftStatus.equalsIgnoreCase(Constants.STATUS_SENT)) {
						swiftMessageResponse = BOCCommonUtil.swiftResponse;
						if (transmitFlag) {
							swiftStatus = Constants.STATUS_SUCCEEDED;
						} else {
							swiftStatus = Constants.STATUS_FAILED;
						}
					}

					BOCCommonUtil.inserttoSwiftOutTable(swiftPkey, pkey, aSwiftOutRequestModel.getMasterReference(),
							aSwiftOutRequestModel.getEventReference(), swiftOut, swiftStatus, swiftMessageResponse);
					Loggers.general().info(LOG, "SwifOut Table Insert");
				}
				Loggers.general().info(LOG,
						"QueueName" + BOCCommonUtil.retreiveFromProperties(Constants.SWIFT_OUT_QUEUE));
				Loggers.general().info(LOG, "Connection Factory"
						+ BOCCommonUtil.retreiveFromProperties(Constants.SWIFTQUEUE_CONNECTION_FACTORY));
			} else {
				for (int i = 0; i < formattedSwiftOut.size(); i++) {

					Loggers.general().info(LOG, "inserttoSwiftOutTable---->  " + i);
					// added by mani
					Loggers.general().info(LOG, "Swiftoutsize---->  " + formattedSwiftOut.size());
					// added by mani

					if ((BOCCommonUtil.retreiveFromProperties(Constants.SwiftTestBic)).equalsIgnoreCase("YES")) {

						System.out.println("Entered");
						swiftOut = updateTestBics(formattedSwiftOut.get(i));
					} else {
						System.out.println("Swift Without Test BIC");
						swiftOut = formattedSwiftOut.get(i);
						// System.out.println("Swift "+swiftOut);
					}
					

					String swiftPkey = BOCCommonUtil.getNextSeqNo(Constants.SWIFTPKEY_SEQUENCE);
					if (swiftStatus.equalsIgnoreCase(Constants.STATUS_SENT)) {
						swiftMessageResponse = BOCCommonUtil.swiftResponse;
						if (transmitFlag) {
							swiftStatus = Constants.STATUS_SUCCEEDED;
						} else {
							swiftStatus = Constants.STATUS_FAILED;
						}
					}

					BOCCommonUtil.inserttoSwiftOutTable(swiftPkey, pkey, aSwiftOutRequestModel.getMasterReference(),
							aSwiftOutRequestModel.getEventReference(), swiftOut, swiftStatus, swiftMessageResponse);
					Loggers.general().info(LOG, "SwifOut Table Insert");
				}

			}
			response = response.replace("{CorrelationID}", correlationId);
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.general().info(LOG, "Exception--->" + e);
		} finally {

		}
		return response;

	}

	private String updateTestBics(String swiftOut) {

		String xml = swiftOut;
		try {

			// System.out.println("Entered into test bic update try " + swiftOut);
			StringBuilder sb = new StringBuilder(xml);
			sb.setCharAt(xml.indexOf("{1:") + 13, '0');
			sb.setCharAt(xml.indexOf("{2:") + 14, '0');
			xml = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Entered into test bic update try " + xml);
		return xml;
	}

	// New Method
	private List<String> getRTGSSwift(List<String> formattedSwiftOut) {

		List<String> rtgsSwift = new LinkedList<String>();
		String formattedSwiftOutstr = null;
		String swiftOldBIC = null;
		int pos = 0;
		String swiftNewBIC = null;
		for (int i = 0; i < formattedSwiftOut.size(); i++) {
			if (formattedSwiftOut.get(i).contains("2:I103")) {
				// formattedSwiftOutstr=formattedSwiftOut.get(i).replace("{3:", "{3:{103:LKB}");
				rtgsSwift.add(formattedSwiftOut.get(i).replace("{3:", "{3:{103:LKB}"));

			} else if (formattedSwiftOut.get(i).contains("2:I202")) {
				// Commented the below on 27-09-2023 NTAC 8430
				//rtgsSwift.add(formattedSwiftOut.get(i).replace("{3:", "{3:{202:LKB}"));
				rtgsSwift.add(formattedSwiftOut.get(i).replace("{3:", "{3:{103:LKB}"));
			} else {
				rtgsSwift.add(formattedSwiftOut.get(i));
			}
		}

		return rtgsSwift;
	}

	private SwiftOutRequestModel getReferencefromDB(String correlationId) {

		Loggers.general().info(LOG, "Inside getReferencefromDB");

		SwiftOutRequestModel aSwiftOutRequestModel = new SwiftOutRequestModel();

		String result = BOCCommonUtil.getMasterEventRef(correlationId);

		aSwiftOutRequestModel.setMasterReference(result.split("\\,")[0]);
		aSwiftOutRequestModel.setEventReference(result.split("\\,")[1]);

		return aSwiftOutRequestModel;
	}

	public static void main(String[] args) {
		String swiftNewBIC = "{2:I202CBCELKL0XXXXN}";
		/*
		 * int pos=swiftNewBIC.indexOf("{2:");
		 * swiftNewBIC=swiftNewBIC.substring(pos+7,pos+19);
		 */
		// swiftNewBIC=swiftNewBIC.substring(0,7)+"0"+swiftNewBIC.substring(7,swiftNewBIC.length());
		System.out.println("swiftNewBIC-->" + swiftNewBIC.contains("{2:I202"));
	}
}
