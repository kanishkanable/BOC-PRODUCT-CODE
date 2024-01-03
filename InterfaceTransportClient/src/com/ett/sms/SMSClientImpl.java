package com.ett.sms;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.document.production.common.exception.DocumentProcessException;
import com.misys.tiplus2.foundations.lang.logging.Loggers;

public class SMSClientImpl {

	public String processSMS(String TiRequestXML, String service) throws DocumentProcessException {

		System.out.println("Entering into processSMS....");
		SMSRequestModel aSMSRequestModel = new SMSRequestModel();
		String bankResponse = null;
		String tiResponseXML = null;

		String pkey = BOCCommonUtil.getNextSeqNo(Constants.SMS_SEQUENCE);
		BOCCommonUtil.insertTIReqToLogTable(TiRequestXML, Constants.SMS_STAGING_TABLE, Constants.STATUS_RECEIVED, pkey);

		aSMSRequestModel = setRequestModelFromTIReqXML(TiRequestXML, service);
		BOCCommonUtil.updateReferenceinLog(aSMSRequestModel.getMasterReference(), aSMSRequestModel.getEventReference(),
				Constants.SMS_STAGING_TABLE, pkey);
		String[] mobileNumberArray = setMobileNumberinArray(aSMSRequestModel);
		String ZoneID = BOCCommonUtil.retreiveFromProperties(Constants.ZONEID);
		System.out.println("The ZoneID is " + ZoneID);

		// N-Able Pvt LTD / 20-02-2023
		// BOCS validation added with Seychelles implementation.
		// Same BOCM functionality used as per the given instructions by BOC.

		if (ZoneID.equalsIgnoreCase("BOCD") || ZoneID.equalsIgnoreCase("BOCA") || ZoneID.equalsIgnoreCase("BOCM")
				|| ZoneID.equalsIgnoreCase("BOCS"))
			bankResponse = sendSMS(aSMSRequestModel, mobileNumberArray);
		else
			bankResponse = writeSMS(aSMSRequestModel, mobileNumberArray);
		BOCCommonUtil.updateTIResToLogTable(bankResponse, Constants.SMS_STAGING_TABLE, Constants.STATUS_SUCCEEDED,
				pkey);
		tiResponseXML = BOCCommonUtil.constructTIResponseXML(TiRequestXML, Constants.STATUS_SUCCEEDED);
		return tiResponseXML;

	}

	private String writeSMS(SMSRequestModel aSMSRequestModel, String[] mobileNumberArray) {
		System.out.println("Entering into writeSMS....");
		File file = new File(BOCCommonUtil.retreiveFromProperties(Constants.POSTING_OUTPUT_PATH)
				+ aSMSRequestModel.getMasterReference() + aSMSRequestModel.getEventReference() + ".dat");

		FileWriter fr = null;
		BufferedWriter br = null;
		String dataWithNewLine = System.getProperty("line.separator");
		String bankRequest = null;

		try {
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			for (int i = 0; i < mobileNumberArray.length; i++) {
				br.write(StringUtils.rightPad(mobileNumberArray[i], 20, Constants.SPACE));
				br.write(BOCCommonUtil.retreiveFromProperties("LOCALSMS"));
				br.write(BOCCommonUtil.retreiveFromProperties("SMSTYPE"));
				br.write(StringUtils.rightPad(aSMSRequestModel.getBodyContent(), 160, Constants.SPACE));
				br.write(StringUtils.rightPad(CommonUtil.getSysDate("yyyy-MM-dd"), 10, Constants.SPACE));
				br.write(CommonUtil.getSysDate("yyyy-MM-dd-HH.mm.ss.SSSSSS"));
				br.write(Constants.SPACE);
				br.write(CommonUtil.getSysDate("yyyy-MM-dd-HH.mm.ss.SSSSSS"));
				br.write(StringUtils.rightPad(Constants.SPACE, 5));
				br.write(StringUtils.rightPad(aSMSRequestModel.getMasterReference(), 20, Constants.SPACE));
				br.write(dataWithNewLine);
				bankRequest = bankRequest + StringUtils.rightPad(mobileNumberArray[i], 20, Constants.SPACE)
						+ BOCCommonUtil.retreiveFromProperties("LOCALSMS")
						+ (BOCCommonUtil.retreiveFromProperties("SMSTYPE"))
						+ (StringUtils.rightPad(aSMSRequestModel.getBodyContent(), 160, Constants.SPACE))
						+ (StringUtils.rightPad(CommonUtil.getSysDate("yyyy-MM-dd"), 10, Constants.SPACE))
						+ (CommonUtil.getSysDate("yyyy-MM-dd-HH.mm.ss.SSSSSS")) + (Constants.SPACE)
						+ (CommonUtil.getSysDate("yyyy-MM-dd-HH.mm.ss.SSSSSS"))
						+ (StringUtils.rightPad(Constants.SPACE, 5))
						+ (StringUtils.rightPad(aSMSRequestModel.getMasterReference(), 20, Constants.SPACE)) + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bankRequest;
	}

	private String sendSMS(SMSRequestModel aSMSRequestModel, String[] mobileNumberArray) {

		System.out.println("Entering into sendSMS....");
		Socket socket = null;
		String message = null;
		DataOutputStream out = null;
		String bankRequest = null;
		String status = null;
		String zone = BOCCommonUtil.retreiveFromProperties(Constants.ZONEID);
		for (int i = 0; i < mobileNumberArray.length; i++) {
			// N-Able Pvt LTD / 20-02-2023
			// BOCS validation added with Seychelles implementation.
			// Same BOCM functionality used as per the given instructions by BOC.
			// Please double check this change with BOC

			if (zone.equals("BOCM") || zone.equals("BOCS")) {

				try {
  /*Modified  by N-able*/
					String zoneService = "";
					if (zone.equals("BOCM")) {
						zoneService = "maleservice";
					}
					if (zone.equals("BOCS")) {
						zoneService = "seycservice";
					}
					
					String BankRequrl = "http://172.21.12.210:8080/OTPSMS/app/" + zoneService + "/sms/?Mno="
							+ mobileNumberArray[i].trim() + "&Msg=" + aSMSRequestModel.getBodyContent() + "&Org=TF";
					System.out.println("BankRequrl-->" + BankRequrl);
					BankRequrl = BankRequrl.replace(" ", "%20");
					BankRequrl = BankRequrl.replace("\n", "\r\n");
					System.out.println("BankRequrl-->" + BankRequrl);
					HttpClient httpClient = HttpClientBuilder.create().build();
					URL url = new URL(BankRequrl);
					// String
					// BankRequrl=URLEncoder.encode(bankReqXML.get(i),"utf-8");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					System.out.println("test1");
					System.out.println("Bank Request" + url);
					con.setRequestMethod("GET");
					// con.setRequestProperty("Content-Type","application/json; utf-8");
					// con.setRequestProperty("Accept", "application/json");
					con.setDoOutput(true);
					System.out.println("test");
					int Response_code = (con.getResponseCode());
					System.out.println("Response---" + Response_code);
					System.out.println("test2");
					if (Response_code == 200) {
						status = Constants.STATUS_SUCCEESS;
						message = "TFSTF" + mobileNumberArray[i].trim() + "|" + aSMSRequestModel.getBodyContent()
								+ "\n";
						bankRequest = bankRequest + message + " Status:" + status + "/n";
					}
					System.out.println("test3");
				} catch (Exception e) {
					status = Constants.STATUS_FAILED;
					message = "TFSTF" + mobileNumberArray[i].trim() + "|" + aSMSRequestModel.getBodyContent() + "\n";
					bankRequest = bankRequest + message + " Status:" + status + "/n";
					e.printStackTrace();
					System.out.println("Exception In the SMS-------->" + e);
				}
			} else {

				try {
					System.out.println(
							"HostName and port " + aSMSRequestModel.getHostName() + aSMSRequestModel.getPortNumber());
					socket = new Socket(aSMSRequestModel.getHostName(),
							Integer.parseInt(aSMSRequestModel.getPortNumber()));
					System.out.println("Connected to server");
					out = new DataOutputStream(socket.getOutputStream());
					System.out.println("Getting OutputStream to server");
					message = "TFSTF" + mobileNumberArray[i].trim() + "|" + aSMSRequestModel.getBodyContent() + "\n";
					System.out.println("Message to be sent to server" + message);
					out.writeUTF(message);
					System.out.println("Write data to server");
					new DataInputStream(new BufferedInputStream(socket.getInputStream()));
					/*
					 * System.out.println("Read input stream to server"); line = in.readUTF();
					 * System.out.println("Response from SMS server -->" + line); String
					 * outputArray[] = line.split("\\,"); if (outputArray
					 * [1].substring(3).equalsIgnoreCase(Constants.ZERO)) { status =
					 * Constants.STATUS_FAILED; } else {
					 */
					status = Constants.STATUS_SUCCEESS;
					// }
					bankRequest = bankRequest + message + " Status:" + status + "/n";
				} catch (Exception e) {
					e.printStackTrace();
					bankRequest = bankRequest + "Status:" + Constants.STATUS_FAILED + "/n";
				} finally {
					if (out != null) {
						try {
							out.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (socket != null) {
						try {
							socket.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		System.out.println("Bank Response " + bankRequest);

		return bankRequest;
	}

	private String[] setMobileNumberinArray(SMSRequestModel aSMSRequestModel) {

		System.out.println("Entering into setMobileNumberinArray....");

		String mobileNumberArray[] = null;

		if (!CommonUtil.NullStringCheck(aSMSRequestModel.getMultiSMSID())) {
			System.out.println("Entered in 1st method set number");
			mobileNumberArray = aSMSRequestModel.getMultiSMSID().split("\\;");
		} else {
			System.out.println("Entered in 2nd method set number");
			mobileNumberArray = aSMSRequestModel.getMobileNumber().split("\\;");
		}
		mobileNumberArray = validateMobileNumber(mobileNumberArray);
		return mobileNumberArray;
	}

	private String[] validateMobileNumber(String[] mobileNumberArray) {
		String mobileNO = null;
		for (int i = 0; i < mobileNumberArray.length; i++) {
			mobileNO = mobileNumberArray[i].trim();
			if (!(mobileNO.startsWith("94") && mobileNO.length() == 11)) {
				if (mobileNO.startsWith("0") && mobileNO.length() == 10) {
					mobileNO = "94" + mobileNO.substring(1);
				} else {
					if (mobileNO.length() == 9) {
						mobileNO = "94" + mobileNO;
					}
				}
			}
			mobileNumberArray[i] = mobileNO;
		}
		return mobileNumberArray;
	}

	private SMSRequestModel setRequestModelFromTIReqXML(String TiRequestXML, String service) {
		System.out.println("Entering into setRequestModelFromTIReqXML....");
		SMSRequestModel aSMSRequestModel = new SMSRequestModel();

		if (service.contains("SMS01")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String lcAmt = CommonUtil.getTagValue("LcAmount", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace LcAmount", lcAmt);

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS02")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);

			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace DocumentValue",
					CommonUtil.getTagValue("DocumentValue", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS03")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);

			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));
			smsContent = smsContent.replace("-replace LCValue", CommonUtil.getTagValue("LCValue", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS04")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);

			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));
			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS05")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);

			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace CourierNumber",
					CommonUtil.getTagValue("CourierNumber", TiRequestXML));
			smsContent = smsContent.replace("-replace DocumentValue",
					CommonUtil.getTagValue("DocumentValue", TiRequestXML));
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS06")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));
			smsContent = smsContent.replace("-replace IssDate", CommonUtil.getTagValue("IssDate", TiRequestXML));
			smsContent = smsContent.replace("-replace ExpDate", CommonUtil.getTagValue("ExpDate", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS07")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));
			smsContent = smsContent.replace("-replace CourierNumber",
					CommonUtil.getTagValue("CourierNumber", TiRequestXML));
			smsContent = smsContent.replace("-replace DocumentValue",
					CommonUtil.getTagValue("DocumentValue", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS08")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace IssBankRef", CommonUtil.getTagValue("IssBankRef", TiRequestXML));
			smsContent = smsContent.replace("-replace IssDate", CommonUtil.getTagValue("IssDate", TiRequestXML));
			smsContent = smsContent.replace("-replace ExpDate", CommonUtil.getTagValue("ExpDate", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS09")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			smsContent = smsContent.replace("-replace DocumentValue",
					CommonUtil.getTagValue("DocumentValue", TiRequestXML));
			/*
			 * smsContent = smsContent.replace("-replace Drawee",
			 * CommonUtil.getTagValue("Drawee", TiRequestXML)); smsContent =
			 * smsContent.replace("-replace Drawer", CommonUtil.getTagValue("Drawer",
			 * TiRequestXML));
			 */

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		} else if (service.contains("SMS10")) {

			String Masterref = CommonUtil.getTagValue("MasterRef", TiRequestXML);
			String smsContent = CommonUtil.getTagValue("SMSContent", TiRequestXML);
			smsContent = smsContent.replace("-replace MasterRef", Masterref);
			/*
			 * smsContent = smsContent.replace("-replace DocumentsSentToBank",
			 * CommonUtil.getTagValue("DocumentsSentToBank", TiRequestXML));
			 */
			smsContent = smsContent.replace("-replace CourierNumber",
					CommonUtil.getTagValue("CourierNumber", TiRequestXML));
			smsContent = smsContent.replace("-replace DocumentValue",
					CommonUtil.getTagValue("DocumentValue", TiRequestXML));

			aSMSRequestModel.setMasterReference(Masterref);
			aSMSRequestModel.setBodyContent(smsContent);
			aSMSRequestModel.setEventReference(CommonUtil.getTagValue("EventRef", TiRequestXML));
			aSMSRequestModel.setProdsubtype(CommonUtil.getTagValue("Product", TiRequestXML));
			aSMSRequestModel.setPrincipalparty(CommonUtil.getTagValue("Receiver", TiRequestXML));

		}

		System.out.println("SMS Service " + service);

		aSMSRequestModel.setMobileNumber(
				BOCCommonUtil.getMobileEmail(aSMSRequestModel.getPrincipalparty(), Constants.MOBILENUMBER_COLUMN));

		aSMSRequestModel.setMultiSMSID(BOCCommonUtil.getMultiplevalues(aSMSRequestModel.getPrincipalparty(),
				aSMSRequestModel.getProdsubtype(), Constants.MOBILENUMBER));

		aSMSRequestModel.setHostName(BOCCommonUtil.retreiveFromProperties(Constants.SMS_HOST));
		aSMSRequestModel.setPortNumber(BOCCommonUtil.retreiveFromProperties(Constants.SMS_PORT));

		return aSMSRequestModel;
	}

}
