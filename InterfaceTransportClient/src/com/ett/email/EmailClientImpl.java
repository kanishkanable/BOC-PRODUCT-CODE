package com.ett.email;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.document.production.common.DocumentControlModule;
import com.misys.tiplus2.document.production.common.bean.DocumentProductionDocumentDetail;
import com.misys.tiplus2.document.production.common.bean.DocumentProductionJobDetail;
import com.misys.tiplus2.document.production.common.exception.DocumentProcessException;
import com.misys.tiplus2.foundations.lang.logging.Loggers;

public class EmailClientImpl implements DocumentControlModule {

	private static final Logger LOG = LoggerFactory.getLogger(EmailClientImpl.class);

	public void process(DocumentProductionJobDetail job) throws DocumentProcessException {
		Loggers.general().info(LOG, "Entering EmailClientImpl");

		List<DocumentProductionDocumentDetail> documents = job.getDocuments();
		System.out.println("Documents  -->" + documents);
		LinkedHashMap<String, String> metaMap = new LinkedHashMap<String, String>();
		EmailRequestModel aEmailRequestModel = new EmailRequestModel();
		EmailResponseModel aEmailResponseModel = new EmailResponseModel();
		System.out.println("Entering to metadata");
		metaMap = BOCCommonUtil.getMetaMap(job.getMetadata(), Constants.EMAIL);
		if (metaMap != null) {

			String pkey = BOCCommonUtil.getNextSeqNo(Constants.EMAIL_SEQUENCE);
			BOCCommonUtil.insertTIReqToLogTable(documents.toString(), Constants.EMAIL_STAGING_TABLE,
					Constants.STATUS_RECEIVED, pkey);
			aEmailRequestModel = setRequestModelFromTIReqXML(metaMap);
			String[] toEmailArray = setMailIDinArray(aEmailRequestModel);
			String mailReleaseStatus = sendEmail(aEmailRequestModel, toEmailArray, documents);
			aEmailResponseModel = getResponseMesaage(toEmailArray, mailReleaseStatus, documents, aEmailRequestModel);
			if (!mailReleaseStatus.equalsIgnoreCase("SUCCEEDED"))
				mailReleaseStatus = Constants.STATUS_FAILED;

			BOCCommonUtil.updateReferenceinLog(aEmailRequestModel.getMasterReference(),
					aEmailRequestModel.getEventReference(), Constants.EMAIL_STAGING_TABLE, pkey);

			BOCCommonUtil.updateTIResToLogTable(aEmailResponseModel.getBankRequest(), Constants.EMAIL_STAGING_TABLE,
					mailReleaseStatus, pkey);

		}

	}

	private EmailResponseModel getResponseMesaage(String[] toEmailArray, String mailReleaseStatus,
			List<DocumentProductionDocumentDetail> documents, EmailRequestModel aEmailRequestModel) {

		System.out.println(mailReleaseStatus);

		EmailResponseModel aEmailResponseModel = new EmailResponseModel();
		String toMail = Arrays.toString((Object[]) toEmailArray);
		String tirequest = " ";

		if (!mailReleaseStatus.equalsIgnoreCase("SUCCEEDED")) {
			String[] msglist = mailReleaseStatus.split("\\|");
			mailReleaseStatus = "FAILED";
			aEmailResponseModel.setErrorMessage(msglist[1]);
		}
		for (DocumentProductionDocumentDetail documentDetail : documents)
			tirequest = String.valueOf(tirequest) + "\nDmsId : " + documentDetail.getDocumentID() + "\nDocumentName : "
					+ documentDetail.getDocumentName() + "TO Address : " + toMail + "\nCC Address : " + "\nCustomer: "
					+ aEmailRequestModel.getPrincipalparty() + "\nErrormsg : " + aEmailResponseModel.getErrorMessage();
		aEmailResponseModel.setBankRequest(tirequest);

		return aEmailResponseModel;
	}

	private String[] setMailIDinArray(EmailRequestModel aEmailTIReqModel) {

		String toEmailArray[] = null;
		String partymailId[] = null;
		String multimailId[] = null;
		List toEmailList=new ArrayList();
		if (!CommonUtil.NullStringCheck(aEmailTIReqModel.getPartymailTo())) {
			partymailId = aEmailTIReqModel.getPartymailTo().split("\\;");
		
		}
		
		if (!CommonUtil.NullStringCheck(aEmailTIReqModel.getMultimailID())) {
			multimailId = aEmailTIReqModel.getMultimailID().split("\\;");
		}
		if(partymailId!=null && !partymailId.equals(null) && partymailId.length>0 ) {
		toEmailList=new ArrayList(Arrays.asList(partymailId));
		}
		if(toEmailList.size()>0 ) {
			if(multimailId!=null && !multimailId.equals(null) && multimailId.length>0 ) {
				toEmailList.addAll(Arrays.asList(multimailId));
			}
		}
		else
		{
			if(multimailId!=null && !multimailId.equals(null) && multimailId.length>0 ) {
				toEmailList=new ArrayList(Arrays.asList(multimailId));
				}
		}
		if(toEmailList.size()>0 ) {
		toEmailArray = new String[toEmailList.size()];
		toEmailArray  = (String[]) toEmailList.toArray(toEmailArray);
		}
		return toEmailArray;
	}
	

	private EmailRequestModel setRequestModelFromTIReqXML(LinkedHashMap<String, String> metaMap) {

		EmailRequestModel aEmailTIReqModel = new EmailRequestModel();
		aEmailTIReqModel.setMasterReference(metaMap.get("MASTERREFERENECE"));
		aEmailTIReqModel.setEventReference(metaMap.get("EVENTREFERENCE"));
		aEmailTIReqModel.setPrincipalparty(metaMap.get("PRINCIPALPARTY"));
		aEmailTIReqModel
				.setPartymailTo(metaMap.get("PARTYMAILTO"));
		aEmailTIReqModel.setProdsubtype(metaMap.get("PRODSUBTYPE"));
		aEmailTIReqModel.setSubject(metaMap.get("SUBJECT"));
		aEmailTIReqModel.setBodyContent(metaMap.get("TEXT"));
		System.out.println("EmailPrincipalParty  ...." + aEmailTIReqModel.getPrincipalparty());
		System.out.println("EmailProdsubtype...." + aEmailTIReqModel.getProdsubtype());
		aEmailTIReqModel.setMultimailID(BOCCommonUtil.getMultiplevalues(aEmailTIReqModel.getPrincipalparty(),
				aEmailTIReqModel.getProdsubtype(), Constants.MAIL));

		aEmailTIReqModel.setHostName(BOCCommonUtil.retreiveFromProperties(Constants.SMTP_HOST));
		aEmailTIReqModel.setPortNumber(BOCCommonUtil.retreiveFromProperties(Constants.SMTP_PORT));
		aEmailTIReqModel.setUserName(BOCCommonUtil.retreiveFromProperties(Constants.EMAIL_USER));
		aEmailTIReqModel.setPassword(BOCCommonUtil.retreiveFromProperties(Constants.EMAIL_PASSWORD));
		aEmailTIReqModel.setEmailLabelName(BOCCommonUtil.retreiveFromProperties(Constants.EMAIL_LABEL_NAME));
		return aEmailTIReqModel;
	}
	public static String fetchEmailAddress(String productType,String customerCIF) {

		 Connection aConnection = null;

		 PreparedStatement pst = null;

		 ResultSet rst = null;

		 String emailAddress = null;

		 try {

		 aConnection = CommonUtil.DBConnection();

		 pst = aConnection.prepareStatement("select details from cusspecins where notetype='259'and CUSTOMER='"+customerCIF+"' and busarea=(SELECT CASE WHEN '"+productType+"' IN ('IGT','EGT') THEN '1'  WHEN '"+productType+"' IN ('ELC','ODC','FEL','FOC','CPHI','CPBI','CPCI','TRF','ELC','ODC','FEL','FOC','CPHI','CPBI','CPCI','TRF') THEN '2'  ELSE '3' END))");

		 rst = pst.executeQuery();

		 while (rst.next()) {

		 emailAddress = rst.getString("details");

		 }

		 } catch (Exception e) {

		 e.printStackTrace();

		 } finally {

		 CommonUtil.CloseConnection(aConnection, pst, rst);

		 }

		 return emailAddress;

		 }
	private String sendEmail(EmailRequestModel aEmailTIReqModel, String[] to,
			List<DocumentProductionDocumentDetail> documents) {
		String mailReleaseStatus = null;
		String SMTPHost = aEmailTIReqModel.getHostName();
		String SMTPPort = aEmailTIReqModel.getPortNumber();
		final String EmailUser = aEmailTIReqModel.getUserName();
		final String EmailPassword = aEmailTIReqModel.getPassword();
		String[] AlertEmail = to;
		try {

			Properties props = System.getProperties();
			props.put("mail.smtp.host", SMTPHost);
			props.put("mail.smtp.port", SMTPPort);
			//props.put("mail.transport.protocol", "smtp");
			//props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EmailUser, EmailPassword);
				}
			});
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom((Address) new InternetAddress(EmailUser,aEmailTIReqModel.getEmailLabelName()));
			
			InternetAddress[] toAddress = null;
			if (AlertEmail != null) {
				toAddress = new InternetAddress[AlertEmail.length];
				for (int i = 0; i < AlertEmail.length; i++) {
					if (BOCCommonUtil.isValidEmailAddress(AlertEmail[i]))
						toAddress[i] = new InternetAddress(AlertEmail[i]);
					mimeMessage.addRecipient(Message.RecipientType.TO, (Address) toAddress[i]);
				}
			}

			mimeMessage.setSubject(aEmailTIReqModel.getSubject());
			mimeMessage.setSentDate(new Date());
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(aEmailTIReqModel.getBodyContent(), "text/plain");
			mimeMultipart.addBodyPart((BodyPart) mimeBodyPart);
			for (DocumentProductionDocumentDetail dpd : documents) {
				System.out.println("Document ID -->" + dpd.getDocumentID());
				byte[] attachmentData = BOCCommonUtil.getDocumentFromDbd(dpd.getDocumentID());
				if (attachmentData != null) {
					String attachmentFileName = String.valueOf(aEmailTIReqModel.getMasterReference()) + ".pdf";
					MimeBodyPart attachFiles = new MimeBodyPart();
					attachFiles.setFileName(attachmentFileName);
					attachFiles.setContent(attachmentData, "application/pdf");
					mimeMultipart.addBodyPart((BodyPart) attachFiles);
				}
			}
			mimeMessage.setContent((Multipart) mimeMultipart);
			Transport.send((Message) mimeMessage);
			mailReleaseStatus = "SUCCEEDED";
		} catch (Exception e) {

			mailReleaseStatus = "FAILED|Mail sending failed due to : " + e.getMessage();
			e.printStackTrace();
		}
		return mailReleaseStatus;
	}
	/*public static void main(String args[])
	{
		String arr[]=null;
		List toEmailList=new ArrayList();
		if( arr!=null && !arr.equals(null) && arr.length>0)
		{
			System.out.println("entered");
		}
		if(toEmailList.size()>0 ) {
			
			System.out.println("entered into list");
			}
	}*/
}