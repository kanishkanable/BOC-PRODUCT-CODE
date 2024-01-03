package com.ett.swift.outgoing;

import java.util.LinkedList;
import java.util.List;

import com.ett.util.BOCCommonUtil;
import com.ett.util.Constants;

public class SwiftOutUpdation {

	public void swiftoutUpdate() {

		List<SwiftOutRequestModel> aSwiftOutRequestModel = new LinkedList<>();

		aSwiftOutRequestModel = BOCCommonUtil.getHoldSwifts();

		for (int i = 0; i < aSwiftOutRequestModel.size(); i++) {
			checkandUpdateSwift(aSwiftOutRequestModel.get(i));
		}

	}

	private void checkandUpdateSwift(SwiftOutRequestModel aSwiftOutRequestModel) {

		String swiftStatus = Constants.STATUS_HOLD;

		swiftStatus = BOCCommonUtil.checkPostingStatus(
				aSwiftOutRequestModel.getMasterReference(),
				aSwiftOutRequestModel.getEventReference());
		if (swiftStatus.equalsIgnoreCase(Constants.STATUS_SUCCEEDED)) {
			List<String> formattedSwiftOut = BOCCommonUtil.getSwiftOutMessage(
					aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference());
			for (int i = 0; i < formattedSwiftOut.size(); i++)
				BOCCommonUtil
						.sendMessagetoMQ(
								formattedSwiftOut.get(i),
								BOCCommonUtil
										.retreiveFromProperties(Constants.SWIFT_OUT_QUEUE),
								BOCCommonUtil
										.retreiveFromProperties(Constants.SWIFTQUEUE_CONNECTION_FACTORY));
			BOCCommonUtil.updateSwiftOutStatus(
					aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference(),
					Constants.STATUS_SUCCEEDED);

		}

		else if (swiftStatus.equalsIgnoreCase(Constants.STATUS_FAILED)) {

			BOCCommonUtil.updateSwiftOutStatus(
					aSwiftOutRequestModel.getMasterReference(),
					aSwiftOutRequestModel.getEventReference(),
					Constants.STATUS_FAILED);
		}

	}

}
