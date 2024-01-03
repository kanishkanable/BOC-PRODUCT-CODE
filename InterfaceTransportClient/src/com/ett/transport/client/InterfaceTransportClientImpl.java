package com.ett.transport.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.misys.tiplus2.document.production.common.exception.DocumentProcessException;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.boc.themebridge.adapter.impl.ExtractsGatewayConsumption;
import com.ett.backofficebatch.BackOfficeBatchImpl;
import com.ett.backofficeverify.BackOfficeVerifyImpl;
import com.ett.forwardcontracts.ForwardContractsImpl;
import com.ett.sms.SMSClientImpl;
import com.ett.swift.outgoing.SwiftOutImpl;
import com.ett.usdclearing.USDClearingImpl;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.watchlist.WatchlistImpl;
import com.misys.tiplus2.messaging.MessagingException;
import com.misys.tiplus2.messaging.controls.out.TransportClient;

public class InterfaceTransportClientImpl implements TransportClient {

	private static final Logger LOG = LoggerFactory.getLogger(InterfaceTransportClientImpl.class);

	@Override
	public String invoke(String arg0, String arg1, String arg2) throws MessagingException {

		Loggers.general().info(LOG, "Entering into Transport client");

		String service = arg0 + arg1;
		String xml = arg2;
		String tiResponseXML = null;

		Loggers.general().info(LOG, "Service is + " + service);

		Loggers.general().info(LOG, "XML from TI is " + xml);

		if (service.equalsIgnoreCase("BackOfficeVerify")) {
			System.out.println("Entering BackOfficeVerify interfaceClient ");
			if ((CommonUtil.getTagValue("TargetSystem", xml).equalsIgnoreCase("InternalLimits"))) {

				tiResponseXML = BOCCommonUtil.constructTIResponseXML(xml);

			} else {
				BackOfficeVerifyImpl aBackOfficeVerifyImpl = new BackOfficeVerifyImpl();

				tiResponseXML = aBackOfficeVerifyImpl.doBalanceCheck(xml);
			}
		}

		if (service.equalsIgnoreCase("BackOfficeBatch")) {
			System.out.println("Entering BackOfficeBatch interfaceClient ");
			BackOfficeBatchImpl aBackOfficeBatchImpl = new BackOfficeBatchImpl();

			tiResponseXML = aBackOfficeBatchImpl.createPostings(xml);
			// added by mani
			System.out.println("BackOfficeBatchImpl completed....");
			// added by mani

		}

		if (service.equalsIgnoreCase("SWIFTSwiftOut")) {
			Loggers.general().info(LOG, "Entering Swiftout interfaceClient");
			System.out.println("Entering SWIFTSwiftOut interfaceClient ");
			List<String> formattedSwiftOut = BOCCommonUtil.getFormattedSwiftOut(xml);
			// added by mani
			//System.out.println("Swiftout format completed...."+formattedSwiftOut);
			// added by mani

			SwiftOutImpl aSwiftOutImpl = new SwiftOutImpl();

			tiResponseXML = aSwiftOutImpl.processSWIFTOut(xml, formattedSwiftOut);
//			aSwiftOutImpl.processSWIFTOut(xml, formattedSwiftOut);
			// added by mani
			System.out.println("Swiftout message sent succesfully....");
			// added by mani

		}

		if (service.equalsIgnoreCase("BusinessSearch")) {
			Loggers.general().info(LOG, "Entering BusinessSearch interfaceClient");
			System.out.println("Entering BusinessSearch interfaceClient ");
			ForwardContractsImpl aForwardContractsImpl = new ForwardContractsImpl();

			tiResponseXML = aForwardContractsImpl.getForwardContracts(xml);
		}
//		if (service.equalsIgnoreCase("SMSGatewayOut")) 
		if (service.contains("GATEWAYSMS")) {
			Loggers.general().info(LOG, "Entering SMSGatewayMessageOut interfaceClient");
			System.out.println("Entering SMSGatewayMessageOut interfaceClient ");
			SMSClientImpl smsclient = new SMSClientImpl();
			try {
				tiResponseXML = smsclient.processSMS(xml, service);
			} catch (DocumentProcessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (service.contains("GATEWAYWL")) {
			Loggers.general().info(LOG, "Entering WatchListGatewayOut interfaceClient");
			System.out.println("Entering WatchListGatewayOut interfaceClient ");

			WatchlistImpl aWatchlistImpl = new WatchlistImpl();

			try {
				tiResponseXML=aWatchlistImpl.doWatchlistCheck(xml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (service.equalsIgnoreCase("GATEWAYExtracts")) {
			ExtractsGatewayConsumption extractsGatewayConsumptionImpl = new ExtractsGatewayConsumption();

			try {
				tiResponseXML = extractsGatewayConsumptionImpl.process(xml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (service.contains("GATEWAYPaymode")) {	
			System.out.println("Entering USD Clearing");

			USDClearingImpl aUSDClearingImpl = new USDClearingImpl();
			tiResponseXML = aUSDClearingImpl.sendUSDClearing(xml);

			System.out.println("USD Clearing completed....");

		}

		Loggers.general().info(LOG, "Response sent to TI --> \n" + tiResponseXML);

		return tiResponseXML;
	}

}
