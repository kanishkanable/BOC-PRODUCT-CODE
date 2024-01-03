package com.ett.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;

public class ServiceProcessorUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceProcessorUtil.class);

	private RequestHeader requestHeader;
	private String inputMessage;
	public Document document;
	private String reqServiceOperation;
	private String reqService;

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getInputMessage() {
		return inputMessage;
	}

	public void setInputMessage(String inputMessage) {
		this.inputMessage = inputMessage;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getReqServiceOperation() {
		return reqServiceOperation;
	}

	public void setReqServiceOperation(String reqServiceOperation) {
		this.reqServiceOperation = reqServiceOperation;
	}

	public String getReqService() {
		return reqService;
	}

	public void setReqService(String reqService) {
		this.reqService = reqService;
	}

	/**
	 * Constructor ServiceProcessorUtil
	 * 
	 * process the input xml
	 * 
	 * @param inputXml : input from TI
	 */

	public ServiceProcessorUtil(String inputXml)
			throws ParserConfigurationException, SAXException, IOException, JAXBException {

		Loggers.general().info(LOG, " Input XML in Adaptee ***** \n \n " + inputXml + "\n\n");

		inputMessage = inputXml;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new ByteArrayInputStream(inputMessage.getBytes()));
		InputStream inStream = new ByteArrayInputStream(inputXml.getBytes());
		JAXBContext context = JAXBContext.newInstance(ServiceRequest.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
		requestHeader = serviceRequest.getRequestHeader();
		reqService = requestHeader.getService();
		reqServiceOperation = requestHeader.getService() + requestHeader.getOperation();

		String operation = requestHeader.getOperation();

		Loggers.general().debug(LOG, "operation -> " + operation);
		reqServiceOperation = requestHeader.getService() + operation;
		Loggers.general().info(LOG, "ServiceOperation -> " + reqServiceOperation);

		Loggers.general().info(LOG, "Account number " + requestHeader.getCreationDate());

		inStream.close();
	}

	public void initialize(String inputXml)
			throws ParserConfigurationException, SAXException, IOException, JAXBException {

		inputMessage = inputXml;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new ByteArrayInputStream(inputMessage.getBytes()));
		InputStream inStream = new ByteArrayInputStream(inputXml.getBytes());
		JAXBContext context = JAXBContext.newInstance(ServiceRequest.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
		requestHeader = serviceRequest.getRequestHeader();
		reqService = requestHeader.getService();
		reqServiceOperation = requestHeader.getService() + requestHeader.getOperation();
		Loggers.general().debug(LOG, "ServiceOperation -> " + reqServiceOperation);
		inStream.close();

	}

}
