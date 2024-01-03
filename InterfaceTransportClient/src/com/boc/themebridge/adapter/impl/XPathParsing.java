package com.boc.themebridge.adapter.impl;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathParsing {
	private final static Logger logger = Logger.getLogger(XPathParsing.class
			.getName());

	public static String getValue(String requestXML, String xpath)
			throws SAXException, IOException, XPathExpressionException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db;
		String attributeValue = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(
					new StringReader(requestXML)));
			XPathFactory factory = XPathFactory.newInstance();
			javax.xml.xpath.XPath oXPath = factory.newXPath();
			attributeValue = oXPath.compile(xpath).evaluate(doc);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			logger.info("error xml " + requestXML);
			logger.debug("ParserConfigurationException:" + e.getMessage());
			return null;
		}
		return attributeValue;
	}
}
