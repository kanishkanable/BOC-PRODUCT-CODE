package com.ett.fxrates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ett.util.Constants;

public class FXmainMethod {

	public static void main(String args[]) {

		File folder = new File("D:\\Rates\\");
		File[] listOfFiles = folder.listFiles();
		FXRatesImpl aFXRatesImpl = new FXRatesImpl();
		FXRateTIRequestModel aFXRateTIRequestModel = new FXRateTIRequestModel();
		FXRateRequestModel aFXRateRequestModel = new FXRateRequestModel();
		List<FXRateReqModel> FxR = new LinkedList<>();
		FXRateReqModel aFxRateReqModel = new FXRateReqModel();

		InputStream is = null;
		BufferedReader buf = null;
		try {

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String fileName = listOfFiles[i].getName();

					System.out.println("File Name --> " + fileName);
					is = new FileInputStream(listOfFiles[i]);
					buf = new BufferedReader(new InputStreamReader(is));
					String line = buf.readLine();
					while (line != null) {
						aFxRateReqModel.setCurrencyCode(StringUtils.leftPad(line.substring(4, 6),3,Constants.ZERO));
						System.out.println("Currency " + aFxRateReqModel.getCurrencyCode());
						System.out.println(line.substring(7, 17));
						aFxRateReqModel.setBookExchangeRate(new BigDecimal(line.substring(7, 17)));
						
						System.out.println(line.substring(240, 250));
						aFxRateReqModel.setApplTransferBuyRate(new BigDecimal(line.substring(240, 249)));
						aFxRateReqModel.setMarginImportTransaction(new BigDecimal(line.substring(320, 331)));

						System.out.println(
								aFxRateReqModel.getCurrencyCode() + "  " + aFxRateReqModel.getBookExchangeRate() + "  "
										+ aFxRateReqModel.getApplTransferBuyRate() + "  "
										+ aFxRateReqModel.getMarginImportTransaction());
						
						line = buf.readLine();
						FxR.add(aFxRateReqModel);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		aFXRateRequestModel.setFxRateReqModel(FxR);

		aFXRateTIRequestModel = aFXRatesImpl.setTIFxRates(aFXRateRequestModel);
		aFXRatesImpl.getTIRequestXML(aFXRateTIRequestModel);

	}

}
