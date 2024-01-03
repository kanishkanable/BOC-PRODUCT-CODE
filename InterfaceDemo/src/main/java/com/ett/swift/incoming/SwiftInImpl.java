package com.ett.swift.incoming;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.jms.ConnectionFactory;
//import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
//import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

//import javax.jms.QueueConnection;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
//import com.ibm.mq.MQQueue;
import com.ibm.mq.jms.MQQueue;
import com.misys.tiplus2.apps.ti.service.common.EnigmaBoolean;
import com.misys.tiplus2.apps.ti.service.messages.GatewaySwiftIn;
import com.misys.tiplus2.apps.ti.service.messages.ObjectFactory;
//import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;
//import com.ibm.jms.JMSBytesMessage;
//import com.ibm.jms.JMSTextMessage;
//import com.ibm.msg.client.wmq.WMQConstants;

public class SwiftInImpl {
	// private static final Logger LOG = LoggerFactory.getLogger(SwiftInImpl.class);
	public static String listenerMsgWaitTime = "5";

	public void readSwiftInMessage() {
		try {
//			BOCCommonUtil.getMQConnection();
			processMqSwiftIncomingMsg();

//			 System.out.println("------Swiftin Starts------");
////			QueueConnection con = CommonUtil.getMQConnectionFactory("jms/SwiftQueueConnectionFactory");
//			QueueConnection con =BOCCommonUtil.getMQConnection(inputMessage) ;
//			Message msg=null;
//			MessageConsumer consumer = null;
//			System.out.println("------Swiftin Queue Connection Factory------");
//			boolean swiftRunMaster = true;
//			con.start();
//			System.out.println("------Swiftin Connection Starts------");
//			QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//			Queue que = CommonUtil.getMQConnection("IN.MQ.SWIFT.TF");
//			System.out.println("------Swiftin Connection------");
//			/*QueueReceiver receiver = ses.createReceiver(que);
//			SwiftInListener listener = new SwiftInListener();*/
//			/*receiver.setMessageListener(listener);*/
//			
//			while (swiftRunMaster) {
//				 System.out.println("------Entering While------");
//			consumer = ses.createConsumer(que);
//			msg = consumer.receive(Long.parseLong(listenerMsgWaitTime));
//		    System.out.println("------Swiftin------");
//			if(msg!=null){
//			//onMessage(msg);
//			TextMessage m = (TextMessage) msg;
//			SwiftInImpl aSwiftInImpl = new SwiftInImpl();
//			aSwiftInImpl.processSwiftInMessage(m.getText());
//			}	
//			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void processSwiftInMessage(String swiftIn) {
		System.out.println("Entered the Swiftin Process");
		String pkey = BOCCommonUtil.getNextSeqNo(Constants.SWIFTIN_SEQUENCE);
		BOCCommonUtil.insertTIReqToLogTable(swiftIn, Constants.SWIFTIN_STAGING_TABLE, Constants.STATUS_RECEIVED, pkey);
		String swiftMessageType = BOCCommonUtil.getSwiftMsgType(swiftIn);
		System.out.println("MESSAGE TYPE" + swiftMessageType);
		String tiRequestXML = generateTIRequestXML(swiftIn);
		System.out.println("tiRequestXML" + tiRequestXML);
		if ((BOCCommonUtil.retreiveFromProperties(Constants.SwiftTestBic)).equalsIgnoreCase("YES")) {

			System.out.println("Entered IF");
			tiRequestXML = updateTestBics(tiRequestXML);
		}
		String tiResponseXML = CommonUtil.processEJBMessage(tiRequestXML);
		System.out.println("tiResponseXML" + tiResponseXML);
		BOCCommonUtil.updateSwiftInData(pkey, swiftMessageType, tiRequestXML, tiResponseXML);

	}

	private String updateTestBics(String tiRequestXML) {
		String xml = tiRequestXML;
		try {

			if (xml.contains("BCEYLKL0AXXX"))
				xml = xml.replace("BCEYLKL0AXXX", "BCEYLKLXAXXX");

			if (xml.contains("BCEYMVM0AXXX"))
				xml = xml.replace("BCEYMVM0AXXX", "BCEYMVMVAXXX");
			if (xml.contains("BCEYLKL0A131"))
				xml = xml.replace("BCEYLKL0A131", "BCEYLKLXA131");
			if (xml.contains("BCEYIN50AXXX"))
				xml = xml.replace("BCEYIN50AXXX", "BCEYIN5MAXXX");
			if (xml.contains("BCEYSCS0AXXX"))
				xml = xml.replace("BCEYSCS0AXXX", "BCEYSCSCAXXX");
			if (xml.contains("CITILKL0AXXX"))
				xml = xml.replace("CITILKL0AXXX", "CITILKLXAXXX");
			if (xml.contains("SEYBLKL0AXXX"))
				xml = xml.replace("SEYBLKL0AXXX", "SEYBLKLXAXXX");
			if (xml.contains("SCBLLKL0AXXX"))
				xml = xml.replace("SCBLLKL0AXXX", "SCBLLKLXAXXX");
			if (xml.contains("BCEYGB20AXXX"))
				xml = xml.replace("BCEYGB20AXXX", "BCEYGB2LAAXXX");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	private String generateTIRequestXML(String swiftInMessage) {

		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String formattedRequestCRLF = null;

		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.SWIFT_IN);
			ObjectFactory of = new ObjectFactory();
			GatewaySwiftIn swiftIn = new GatewaySwiftIn();
			JAXBElement<EnigmaBoolean> acknowledgeJAXB = of.createGatewaySwiftInAcknowledged(EnigmaBoolean.TRUE);
			swiftIn.setAcknowledged(acknowledgeJAXB);
			swiftIn.setMessage(swiftInMessage);
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<GatewaySwiftIn> swiftInJAXB = of.createSwiftIn(swiftIn);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(swiftInJAXB);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sRequest, outStream);
			String tiRequest = outStream.toString();
			String headertiRequest = CommonUtil.stringReplaceCommonUtil(tiRequest, "\u0001", "<![CDATA[");
			String footertiRequest = CommonUtil.stringReplaceCommonUtil(headertiRequest, "\u0003", "]]>");
			String formattedRequest = CommonUtil.stringReplaceCommonUtil(footertiRequest, "&#xD;", "");
			formattedRequestCRLF = CommonUtil.stringReplaceCommonUtil(formattedRequest, "\\r\\n", "\\\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return formattedRequestCRLF;
	}

	public static void processMqSwiftIncomingMsg() throws Exception {

		System.out.println("******** SwiftIncomingSyncListener *******");
		// ExtMQMessageManager mqMessageManagerObj = new ExtMQMessageManager();

		try {
//			LockFileListenerUtil.createListnerLockFile();
			System.out.println("Entering into Swiftincoming try block------");
			String swInMqJndiName = BOCCommonUtil.retreiveFromProperties("SwiftInMQJndiName");
			String swInQueueName = BOCCommonUtil.retreiveFromProperties("SWIFTInputqueue");

			System.out.println("SwiftJNDIName---" + swInMqJndiName);
			System.out.println("SWIFTInputqueue---" + swInQueueName);

			pullMqMessage(swInMqJndiName, swInQueueName); // TI.INCOMING

			System.out.println("pullMqMessage completed---");
			// logger.debug("SwiftIn MQ Message -->" + mqQueueMessage);

		} catch (Exception e) {
			System.out.println("SwiftIn Listener Exception! " + e.getMessage());
			e.printStackTrace();

		}
	}

	public static String pullMqMessage(String jndiName, String queueName) {

		Connection conn = null;
		Session session = null;
		String queueMessage = "";
		MessageConsumer consumer = null;

		try {
			System.out.println("Entering into PullMessage---");
			Queue responseMQueue;
			conn = getMQJNDIConnection(jndiName);
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			responseMQueue = session.createQueue("queue:///" + queueName);
			System.out.println("responseMQueue session created---");
			// While writing MQ Queue using JMS must declare client
			((MQQueue) responseMQueue).setTargetClient(1); // MQSTR Format
			consumer = session.createConsumer(responseMQueue);
			System.out.println("createConsumer session created---");
			conn.start();
			Message msg = consumer.receive(Long.parseLong("5"));
			System.out.println("createConsumer session created---");

			TextMessage m = (TextMessage) msg;

			// TextMessage m = (TextMessage) msg;

			// JMSBytesMessage aJMSBytesMessage = (JMSBytesMessage) msg;

			if (m != null) {

				// int TEXT_LENGTH = new Long(aJMSBytesMessage.getBodyLength()).intValue();
				// byte[] textBytes = new byte[TEXT_LENGTH];
				// aJMSBytesMessage.readBytes(textBytes, TEXT_LENGTH);
				// String codePage =
				// aJMSBytesMessage.getStringProperty(WMQConstants.JMS_IBM_CHARACTER_SET);
				// queueMessage = new String(textBytes, codePage);
				queueMessage = m.getText();
				System.out.println("Swift Queue Message ===" + queueMessage);
				SwiftInImpl aSwiftInImpl = new SwiftInImpl();
				System.out.println("SwiftIncomingmessageprocessstarted-------");
				aSwiftInImpl.processSwiftInMessage(queueMessage);
				if ((queueMessage != null) && (!queueMessage.isEmpty())) {
				}

			}

		} catch (JMSException jmsex) {
			System.out.println("MQ incoming JMSException -->" + jmsex.getMessage());
			jmsex.printStackTrace();

		} catch (Exception ex) {
			System.out.println("MQ incoming Exception -->" + ex.getMessage());
			ex.printStackTrace();

		} finally {
			surrenderMQ(conn, consumer, session);
		}
		// logger.info("return Q message >--> " + queueMessage);
		return queueMessage;
	}

	public static Connection getMQJNDIConnection(String jndiName) {

		Connection conn = null;
		try {
			InitialContext init = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) init.lookup(jndiName);
			conn = connectionFactory.createConnection();

		} catch (NamingException e) {
			System.out.println("MQ JNDI Connection NamingException e! " + e.getMessage());
			e.printStackTrace();

		} catch (JMSException e) {
			System.out.println("MQ JNDI Connection JMSException e! " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("MQ JNDI connection Exception e-->" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public static void surrenderMQ(javax.jms.Connection conn, MessageConsumer consumer, Session session) {

		try {
			if (consumer != null)
				consumer.close();
		} catch (JMSException e) {
			System.out.println("Close JMS consumer Failed! " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (session != null)
				session.close();
		} catch (JMSException e) {
			System.out.println("Close JMS session Failed! " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (JMSException e) {
			System.out.println("Close JMS connection Failed! " + e.getMessage());
			e.printStackTrace();
		}

	}

}
