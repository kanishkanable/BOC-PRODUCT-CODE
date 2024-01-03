package com.ett.forwardcontracts;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//import org.apache.commons.httpclient.util.HttpURLConnection;

import com.boc.forwardcontract.FxPlus;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceRequest;
import com.misys.tiplus2.apps.ti.service.messages.BulkServiceResponse;
import com.misys.tiplus2.apps.ti.service.messages.FXOptionsSearchResponseType;
import com.misys.tiplus2.apps.ti.service.messages.FXOptionsSearchResponseType.FXOptionsResults;
import com.misys.tiplus2.apps.ti.service.messages.FXOptionsSearchResponseType.FXOptionsResults.FXOptionsResult;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceResponse;

public class ForwardContractsImpl {

	public String getForwardContracts(String tiRequestXML) {

		ForwardContractBankResponse aForwardContractBankResponse = new ForwardContractBankResponse();

		String pkey = BOCCommonUtil.getNextSeqNo(Constants.FORWARD_CONTRACT_SEQUENCE);

		BOCCommonUtil.insertTIReqToLogTable(tiRequestXML, Constants.FORWARD_CONTRACT_TABLE, Constants.STATUS_RECEIVED,
				pkey);

		String dealReferenceNumber = CommonUtil.getTagValue(Constants.DEALREFERENCE, tiRequestXML);

		aForwardContractBankResponse = getBankResponse(dealReferenceNumber);
		String tiResponseXML = setTIResponse(aForwardContractBankResponse, tiRequestXML);

		return tiResponseXML;
	}

	private String setTIResponse(ForwardContractBankResponse aForwardContractBankResponse, String tiRequestXML) {

		List<String> error = new LinkedList<>();
		List<String> warning = new LinkedList<>();
		ByteArrayOutputStream outStream = null;
		StringBuilder sb = new StringBuilder();

		InputStream inStream = new ByteArrayInputStream(tiRequestXML.getBytes());
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		Unmarshaller unmarshaller;
		try {
			unmarshaller = context.createUnmarshaller();

			ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
			com.misys.tiplus2.apps.ti.service.messages.ObjectFactory of = new com.misys.tiplus2.apps.ti.service.messages.ObjectFactory();
			ServiceResponse serviceResponse = new ServiceResponse();
			serviceResponse.setResponseHeader(BOCCommonUtil.getHeader(
					serviceRequest.getRequestHeader().getCorrelationId(),
					serviceRequest.getRequestHeader().getOperation(), serviceRequest.getRequestHeader().getService(),
					sb.toString(), Constants.STATUS_SUCCEESS, error, warning));
			List<JAXBElement<?>> batchRequestList = serviceRequest.getRequest();
			@SuppressWarnings("unchecked")
			JAXBElement<BulkServiceRequest> bsr = (JAXBElement<BulkServiceRequest>) batchRequestList.get(0);
			List<ServiceRequest> bsrlist = bsr.getValue().getServiceRequest();
			List<JAXBElement<?>> sres = serviceResponse.getResponse();
			BulkServiceResponse localResponse = new BulkServiceResponse();
			List<ServiceResponse> batchResponse = localResponse.getServiceResponse();
			for (ServiceRequest sr : bsrlist) {
				ServiceResponse bulkServiceResponse = new ServiceResponse();
				bulkServiceResponse.setResponseHeader(BOCCommonUtil.getHeader(sr.getRequestHeader().getCorrelationId(),
						sr.getRequestHeader().getOperation(), sr.getRequestHeader().getService(), sb.toString(),
						Constants.STATUS_SUCCEESS, error, warning));
				batchResponse.add(bulkServiceResponse);
			}
			sres.add(of.createBatchResponse(localResponse));
			JAXBContext jaxbContext = BOCCommonUtil.getJAXBTIContext();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			outStream = new ByteArrayOutputStream();
			FXOptionsSearchResponseType fxop = new FXOptionsSearchResponseType();
			FXOptionsResult fxr = new FXOptionsResult();
			FXOptionsResults fxrs = new FXOptionsResults();
			fxop.getFXOptionsResults().getFXOptionsResult().get(1).getAvailablePurchaseAmount();
			fxr.setFXContractReference(CommonUtil.getJAXBString(aForwardContractBankResponse.getDealRef()));
			fxr.setCustomer(CommonUtil.getJAXBString(aForwardContractBankResponse.getCustID()));
			fxr.setAvailablePurchaseAmount(
					CommonUtil.getJAXBLong(aForwardContractBankResponse.getAvailableAmount().longValue()));
			fxr.setAvailablePurchaseCurrency(CommonUtil.getJAXBString(aForwardContractBankResponse.getAvailCurrency()));
			fxr.setValidFromDate(CommonUtil.getJAXBGregCalendar(
					CommonUtil.SimpleDatetoGregorianDate("yyyy-MM-dd", aForwardContractBankResponse.getStartDate())));
			fxr.setValidFromDate(CommonUtil.getJAXBGregCalendar(
					CommonUtil.SimpleDatetoGregorianDate("yyyy-MM-dd", aForwardContractBankResponse.getExpiryDate())));
			fxrs.getFXOptionsResult().add(0, fxr);
			fxop.setFXOptionsResults(fxrs);

			sres.add(of.createFXOptionsSearchResponse(fxop));
			jaxbMarshaller.marshal(sres, outStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outStream.toString();
	}

	private ForwardContractBankResponse getBankResponse(String dealReferenceNumber) {

		ForwardContractBankResponse aForwardContractBankResponse = new ForwardContractBankResponse();
		String jsonResponse = null;

		
		  try { 
			  URL urlForGetRequest = new URL(BOCCommonUtil.retreiveFromProperties(Constants.FORWARD_CONTRACT_URL)+dealReferenceNumber);
//			  URL urlForGetRequest = new URL(BOCCommonUtil.retreiveFromProperties(Constants.FORWARD_CONTRACT_URL));
		  String readLine = null; 
		  HttpURLConnection conection = (HttpURLConnection)
		  urlForGetRequest.openConnection(); 
		  conection.setRequestMethod("GET"); int
		  responseCode = conection.getResponseCode(); 
		  if (responseCode ==
		  HttpURLConnection.HTTP_OK) { BufferedReader in = new BufferedReader(new
		  InputStreamReader(conection.getInputStream())); 
		  StringBuffer response = new
		  StringBuffer();
		  while ((readLine = in.readLine()) != null)
		  {
		  response.append(readLine); }
		  in.close(); 
		  jsonResponse = response.toString();
		  } else { System.out.println("GET NOT WORKED"); 
		  } } 
		  catch (Exception e) {
		  e.printStackTrace(); 
		  }
		 
		 
		/*BufferedReader buf = null;

		File file = new File("F:\\Office_Works\\Request.xml");
		try {
			InputStream is = new FileInputStream(file);
			System.out.println("Read tyhe file successfully");
			buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}

			String fileAsString = sb.toString();
			System.out.println("Request XML from TI : " + fileAsString);
			jsonResponse = fileAsString;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/

		ObjectMapper mapper = new ObjectMapper();
		try {

			FxPlus obj = mapper.readValue(jsonResponse, FxPlus.class);
			aForwardContractBankResponse.setDealRef(obj.getSystemInfo().getDealNumber().toString());
			aForwardContractBankResponse.setCustID(obj.getSystemInfo().getCpartyMnemonic());
			aForwardContractBankResponse.setBaseCurrency(obj.getTradedCcy());
			aForwardContractBankResponse.setStartDate(obj.getMainLeg().getOptionStartDate());
			aForwardContractBankResponse.setExpiryDate(obj.getMainLeg().getOptionEndDate());
			aForwardContractBankResponse.setExchangeRate(obj.getMainLeg().getSystemInfo().getCustomerRate());
			aForwardContractBankResponse.setAvailableAmount(null);
			aForwardContractBankResponse.setAvailCurrency(null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return aForwardContractBankResponse;
	}

}
