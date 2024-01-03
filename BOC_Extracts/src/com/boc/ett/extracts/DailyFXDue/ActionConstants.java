package com.boc.ett.extracts.DailyFXDue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface ActionConstants {

	String ENTERING_METHOD = "Entering Method";
	String EXITING_METHOD = "Exiting Method";

	public static final String FXCI = "SELECT * FROM FX_CONFIRM_IMPORTS";

	public static final String FXUI = "SELECT * FROM FX_UNCONFIRM_IMPORTS";

	public static final String FXCE = "SELECT * FROM FX_CONFIRM_EXPORTS";

	public static final String FXUE = "SELECT * FROM FX_UNCONFIRM_EXPORTS";

	public static final Map<String, String> SCENARIO = Collections.unmodifiableMap(new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("FXCI", "FX Confirmed Requirement - Imports");
			put("FXUI", "FX UnConfirmed Requirement - Imports");
			put("FXCE", "FX Confirmed Requirement - Exports");
			put("FXUE", "FX UnConfirmed Requirement - Exports");

		}
	});
}
