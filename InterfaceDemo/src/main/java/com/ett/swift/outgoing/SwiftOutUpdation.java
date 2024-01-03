package com.ett.swift.outgoing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ett.util.BOCCommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.foundations.lang.logging.Loggers;

public class SwiftOutUpdation {
	private static final Logger LOG = LoggerFactory.getLogger(SwiftOutUpdation.class);

	public void swiftoutUpdate() {

		List<SwiftOutRequestModel> aSwiftOutRequestModel = new ArrayList<>();

		aSwiftOutRequestModel = BOCCommonUtil.getHoldSwifts();

		Loggers.general().info(LOG, "aSwiftOutRequestModel Size-->" + aSwiftOutRequestModel.size());

		for (int i = 0; i < aSwiftOutRequestModel.size(); i++) {
			System.out.println("i value-->" + i);
			Loggers.general().info(LOG, "data--> " + aSwiftOutRequestModel.get(i).getMasterReference());
			checkandUpdateSwift(aSwiftOutRequestModel.get(i));

		}

	}

	private void checkandUpdateSwift(SwiftOutRequestModel aSwiftOutRequestModel) {

		String swiftStatus = "";
		boolean transmitFlag = true;
		String swiftMessageResponse = "";

		try {
			Loggers.general().info(LOG, "MasterReference " + aSwiftOutRequestModel.getMasterReference());
			Loggers.general().info(LOG, "Event reference " + aSwiftOutRequestModel.getEventReference());
			swiftStatus = BOCCommonUtil.checkPostingStatus(aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference());

			Loggers.general().info(LOG, "swiftStatus " + swiftStatus);
			if (BOCCommonUtil.isValidString(swiftStatus)) {
				if (swiftStatus.equalsIgnoreCase(Constants.STATUS_SUCCEEDED)) {
					List<String> formattedSwiftOut = BOCCommonUtil.getSwiftOutMessage(
							aSwiftOutRequestModel.getMasterReference(), aSwiftOutRequestModel.getEventReference());

					for (int i = 0; i < formattedSwiftOut.size(); i++)
						// BOCCommonUtil.writeMQMessage(formattedSwiftOut.get(i));
						// by Arun for TLS
						transmitFlag = BOCCommonUtil.pushMessage(formattedSwiftOut.get(i));

					/*
					 * BOCCommonUtil .sendMessagetoMQ( formattedSwiftOut.get(i), BOCCommonUtil
					 * .retreiveFromProperties(Constants.SWIFT_OUT_QUEUE), BOCCommonUtil
					 * .retreiveFromProperties(Constants.SWIFTQUEUE_CONNECTION_FACTORY));
					 */
					swiftMessageResponse = BOCCommonUtil.swiftResponse;
					if (transmitFlag) {
						swiftStatus = Constants.STATUS_SUCCEEDED;
					} else {
						swiftStatus = Constants.STATUS_FAILED;
					}
					BOCCommonUtil.updateSwiftOutStatus(aSwiftOutRequestModel.getMasterReference(),
							aSwiftOutRequestModel.getEventReference(), swiftStatus, swiftMessageResponse);

				}

				else if (swiftStatus.equalsIgnoreCase(Constants.STATUS_FAILED)) {

					BOCCommonUtil.updateSwiftOutStatus(aSwiftOutRequestModel.getMasterReference(),
							aSwiftOutRequestModel.getEventReference(), Constants.STATUS_FAILED, swiftMessageResponse);
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

}
