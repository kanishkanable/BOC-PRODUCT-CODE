package com.ett.fxrates;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.misys.tiplus2.apps.ti.service.common.EnigmaBoolean;
import com.misys.tiplus2.apps.ti.service.common.GWRMaintenanceType;
import com.misys.tiplus2.apps.ti.service.messages.ObjectFactory;
import com.misys.tiplus2.apps.ti.service.messages.STRCurrencySpotRate;
import com.misys.tiplus2.apps.ti.service.messages.STRFXRate;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;

public class FXRatesImpl {

	public void FXRateUpdate() throws InterruptedException {

		FXRateRequestModel aFXRateRequestModel = new FXRateRequestModel();
		FXRateTIRequestModel aFXRateTIRequestModel = new FXRateTIRequestModel();
		if (BOCCommonUtil.isFilesPresent(Constants.FXRATE_FILEPATH)) {
			aFXRateRequestModel = getFXRates();
			aFXRateTIRequestModel = setTIFxRates(aFXRateRequestModel);
			aFXRateTIRequestModel = getTIRequestXML(aFXRateTIRequestModel);
			pushtoTI(aFXRateTIRequestModel);
		}
		TimeUnit.SECONDS.sleep(5);

	}

	private void pushtoTI(FXRateTIRequestModel aFXRateTIRequestModel) {

		for (FXRateTIReqModel aFXR : aFXRateTIRequestModel.getFXRateTIReqModel()) {

			String ttResponse = CommonUtil.processEJBMessage(aFXR.getTiTTRequestXML());
			String impResponse = CommonUtil.processEJBMessage(aFXR.getTiIMPRequestXML());
			String spotRateResponse = CommonUtil.processEJBMessage(aFXR.getTiSpotRateXML());
			String pkey = BOCCommonUtil.getNextSeqNo(Constants.FX_SEQUENCE);
			BOCCommonUtil.inserttoFXLog(pkey, aFXR.getTiTTRequestXML(), ttResponse, aFXR.getTiIMPRequestXML(),
					impResponse, aFXR.getTiSpotRateXML(), spotRateResponse);

		}

	}

	public FXRateTIRequestModel getTIRequestXML(FXRateTIRequestModel aFXRateTIRequestModel) {

		List<FXRateTIReqModel> afxTIRate = new LinkedList<>();

		for (FXRateTIReqModel aFXR : aFXRateTIRequestModel.getFXRateTIReqModel()) {
			aFXR.setTiTTRequestXML(getTTRequestXML(aFXR));
			aFXR.setTiIMPRequestXML(getIMPRequestXML(aFXR));
			aFXR.setTiSpotRateXML(getSpotRateRequestXML(aFXR));

			System.out.println("setTiTTRequestXML -->" + aFXR.getTiTTRequestXML());
			System.out.println("setTiIMPRequestXML -->" + aFXR.getTiIMPRequestXML());
			System.out.println("setTiSpotRateXML -->" + aFXR.getTiSpotRateXML());
			afxTIRate.add(aFXR);
            aFXR=new FXRateTIReqModel();
		}
		aFXRateTIRequestModel.setFXRateTIReqModel(afxTIRate);

		return aFXRateTIRequestModel;

	}

	private String getSpotRateRequestXML(FXRateTIReqModel aFXR) {
		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String tiRequestXML = null;
		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.SPOTRATE);
			ObjectFactory of = new ObjectFactory();
			STRCurrencySpotRate fxr = new STRCurrencySpotRate();
			fxr.setMaintType(GWRMaintenanceType.F);
			fxr.setMaintainedInBackOffice(EnigmaBoolean.N);
			fxr.setBankingEntity(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			fxr.setCurrency(aFXR.getCurrency());
			fxr.setSpotRate(aFXR.getMidRate());
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<STRCurrencySpotRate> fxRate = of.createCurrencySpotRate(fxr);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(fxRate);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sRequest, outStream);
			tiRequestXML = outStream.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tiRequestXML;
	}

	private String getIMPRequestXML(FXRateTIReqModel aFXR) {
		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String tiRequestXML = null;
		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.FXRATE);
			ObjectFactory of = new ObjectFactory();
			STRFXRate fxr = new STRFXRate();
			fxr.setMaintType(GWRMaintenanceType.F);
			fxr.setMaintainedInBackOffice(EnigmaBoolean.N);
			fxr.setFxRateCode("FXI");
			fxr.setBankingEntity(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			fxr.setCurrency(aFXR.getCurrency());
			/*
			 * fxr.setBuyRateSpecific(GWRExchangeRateBy.fromValue(aFXR.getBcImpBuy().
			 * toString()));
			 * fxr.setSellRateSpecific(GWRExchangeRateBy.fromValue(aFXR.getBcImpSell().
			 * toString()));
			 */
			fxr.setBuyExchangeRate(aFXR.getBcImpBuy());
			fxr.setSellExchangeRate(aFXR.getBcImpSell());
			fxr.setBaseCurrency(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY));
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<STRFXRate> fxRate = of.createFXRate(fxr);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(fxRate);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sRequest, outStream);
			tiRequestXML = outStream.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tiRequestXML;
	}

	private String getTTRequestXML(FXRateTIReqModel aFXR) {
		RequestHeader requestHeader = new RequestHeader();
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String tiRequestXML = null;
		try {

			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.FXRATE);
			ObjectFactory of = new ObjectFactory();
			STRFXRate fxr = new STRFXRate();
			fxr.setMaintType(GWRMaintenanceType.F);
			fxr.setMaintainedInBackOffice(EnigmaBoolean.N);
			fxr.setFxRateCode("FTI");
			fxr.setBankingEntity(BOCCommonUtil.retreiveFromProperties(Constants.ZONEID));
			fxr.setCurrency(aFXR.getCurrency());
			/*
			 * fxr.setBuyRateSpecific(GWRExchangeRateBy.fromValue(aFXR.getTtBuy().toString()
			 * ));
			 * fxr.setSellRateSpecific(GWRExchangeRateBy.fromValue(aFXR.getTtSell().toString
			 * ()));
			 */
			fxr.setBuyExchangeRate(aFXR.getTtBuy());
			fxr.setSellExchangeRate(aFXR.getTtSell());
			fxr.setBaseCurrency(BOCCommonUtil.retreiveFromProperties(Constants.LOCAL_CURRENCY));
			ServiceRequest sRequest = new ServiceRequest();
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<STRFXRate> fxRate = of.createFXRate(fxr);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(fxRate);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sRequest, outStream);
			tiRequestXML = outStream.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tiRequestXML;
	}

	public FXRateTIRequestModel setTIFxRates(FXRateRequestModel aFXRateRequestModel) {

		FXRateTIRequestModel aFXRateTIRequestModel = new FXRateTIRequestModel();
		List<FXRateTIReqModel> fxTIRate = new LinkedList<>();

		for (FXRateReqModel fxr : aFXRateRequestModel.getFxRateReqModel()) {
			try {
				FXRateTIReqModel aFXRateTIReqModel = new FXRateTIReqModel();
				aFXRateTIReqModel.setCurrency(BOCCommonUtil.getCurrency(fxr.getCurrencyCode()));
				aFXRateTIReqModel.setMidRate(fxr.getBookExchangeRate());
				aFXRateTIReqModel.setTtBuy(fxr.getBookExchangeRate().subtract(fxr.getApplTransferBuyRate()));
				aFXRateTIReqModel.setTtSell(fxr.getBookExchangeRate().add(fxr.getApplTransferBuyRate()));
				aFXRateTIReqModel.setBcImpBuy(fxr.getBookExchangeRate().subtract(fxr.getApplTransferBuyRate()));
				aFXRateTIReqModel.setBcImpSell(fxr.getBookExchangeRate().add(fxr.getMarginImportTransaction()));
				fxTIRate.add(aFXRateTIReqModel);
	
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		aFXRateTIRequestModel.setFXRateTIReqModel(fxTIRate);

		return aFXRateTIRequestModel;
	}

	private FXRateRequestModel getFXRates() {

		FXRateRequestModel aFXRateRequestModel = new FXRateRequestModel();
		List<FXRateReqModel> FxR = new LinkedList<>();
		FXRateReqModel aFxRateReqModel = new FXRateReqModel();
           
		File file = new File(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH));
		File[] listOfFiles = file.listFiles();
		InputStream is = null;
		BufferedReader buf = null;
		String Status="Success";
		try {

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String fileName = listOfFiles[i].getName();

					System.out.println("File Name --> " + fileName);
					is = new FileInputStream(listOfFiles[i]);
					buf = new BufferedReader(new InputStreamReader(is));
					String line = buf.readLine();
					while (line != null) {
						try {
							aFxRateReqModel
									.setCurrencyCode(StringUtils.leftPad(line.substring(4, 6).trim(), 3, Constants.ZERO));
							System.out.println("Currency " + aFxRateReqModel.getCurrencyCode());
							System.out.println(line.substring(6, 18));
							aFxRateReqModel.setBookExchangeRate(new BigDecimal(line.substring(6, 18).trim()));

							System.out.println(line.substring(246, 258));
							aFxRateReqModel.setApplTransferBuyRate(new BigDecimal(line.substring(246, 258).trim()));
							aFxRateReqModel.setMarginImportTransaction(new BigDecimal(line.substring(331, 343).trim()));

							System.out.println(
									aFxRateReqModel.getCurrencyCode() + "  " + aFxRateReqModel.getBookExchangeRate()
											+ "  " + aFxRateReqModel.getApplTransferBuyRate() + "  "
											+ aFxRateReqModel.getMarginImportTransaction());

							line = buf.readLine();
							FxR.add(aFxRateReqModel);
							aFxRateReqModel = new FXRateReqModel();
					
						} catch (Exception e) {
							Status="Error";
							System.out.println("Exception while reading -->" + e.getStackTrace());
							e.printStackTrace();
							File sourceFile = new File(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) + fileName);
		                	 fileName=fileName.substring(0, fileName.length()-4)+ getCurrentTime()+".txt";
		                	 File destFile = new File(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) +BOCCommonUtil.retreiveFromProperties(Constants.FILEPATH_ERROR)+ fileName);
		                	 if (sourceFile.renameTo(destFile)) {
		                		    System.out.println("Directory renamed successfully");
		                		} else {
		                		    System.out.println("Failed to rename directory");
							Files.move(
									Paths.get(
											BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) + fileName),
									Paths.get(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH)
											+ BOCCommonUtil.retreiveFromProperties(Constants.FILEPATH_ERROR)
											+ fileName));
							break;

						}
					}
					}
                 if(Status!="Error") {
                	 File sourceFile = new File(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) + fileName);
                	 fileName=fileName.substring(0, fileName.length()-4)+ getCurrentTime()+".txt";
                	 File destFile = new File(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) + fileName);
                	 if (sourceFile.renameTo(destFile)) {
                		    System.out.println("Directory renamed successfully");
                		} else {
                		    System.out.println("Failed to rename directory");
                		}
					Files.move(Paths.get(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH) + fileName),
							Paths.get(BOCCommonUtil.retreiveFromProperties(Constants.FXRATE_FILEPATH)
									+ BOCCommonUtil.retreiveFromProperties(Constants.FILEPATH_PROCESSED) + fileName));
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

		return aFXRateRequestModel;
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        String strDate = sdf.format(cal.getTime());
        System.out.println(strDate);
        return strDate;
}
}
