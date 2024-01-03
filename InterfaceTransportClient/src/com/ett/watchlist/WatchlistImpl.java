package com.ett.watchlist;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boc.watchlist.WatchlistResponse;
import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;
import com.ett.util.QueryConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.tiplus2.apps.ti.service.common.GWROFACPassFailFlag;
import com.misys.tiplus2.apps.ti.service.common.GatewayContextType;
import com.misys.tiplus2.apps.ti.service.messages.GWRIWLCType;
import com.misys.tiplus2.apps.ti.service.messages.GWRIWLCType.FailedFields;
import com.misys.tiplus2.apps.ti.service.messages.GWRIWLCType.FailedFields.FailedField;
import com.misys.tiplus2.apps.ti.service.messages.ObjectFactory;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;

public class WatchlistImpl implements WatchlistInterface{
	private static final Logger LOG = LoggerFactory.getLogger(WatchlistImpl.class);
	
	public static Object thisclass=null; 
	
	@Override
	public String doWatchlistCheck(String tiRequestXml) throws Exception {
		Loggers.general().info(LOG, "doWatchlistCheck");
		System.out.println("Wathclis Tirequest --->" + tiRequestXml);
		String tirespnse = null;
		try {
		String pkey = null;
		String tiResponseXML = null;
		
		WatchlistTIRequestModel aWatchlistTIRequestModel = new WatchlistTIRequestModel();

		pkey = BOCCommonUtil.getNextSeqNo(Constants.WATCHLIST_SEQUENCE);
		Loggers.general().info(LOG, "Inserting Starts call");
		System.out.println("Inserting Starts");
		BOCCommonUtil.insertTIReqToLogTable(tiRequestXml, Constants.WATCHLIST_STAGING_TABLE, Constants.STATUS_RECEIVED,
				pkey);

		aWatchlistTIRequestModel = setTIReqModelFromTIReqXML(tiRequestXml);
		WatchlistTIResModel aWatchlistTIResModel = new WatchlistTIResModel();
		Loggers.general().info(LOG, "Starting call");
		System.out.println("Starting End call");
		aWatchlistTIResModel = callAPIEndPoint(aWatchlistTIRequestModel);
		tiResponseXML = getTIResponseXMLfromBankReq(aWatchlistTIResModel, aWatchlistTIRequestModel);
		//tiResponseXML=tiResponseXML.replace("\r\n","");
		System.out.println("Tireponse xml"+tiResponseXML);
		tirespnse=ApiserverInsert(aWatchlistTIRequestModel,tiResponseXML);
		System.out.println("Tiresponse------>"+tirespnse);
		tirespnse=BOCCommonUtil.constructTIResponseXML(tiRequestXml,Constants.STATUS_SUCCEEDED);
		System.out.println("tirespnse--->"+tirespnse);
		
		/*below line uncommented  by N-able
    	//	tirespnse=CommonUtil.processEJBMessage(tiResponseXML); */
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
		return tirespnse;
	}
	
	private String  ApiserverInsert(WatchlistTIRequestModel aWatchlistTIRequestModel,String tiresponse)
	{
		String result=Constants.STATUS_SUCCEEDED;
		Connection aConnection = null;
		PreparedStatement pst = null;
		
		String Query=QueryConstants.InsertWatchlistAPI;
		Loggers.general().info(LOG, "Befor changing Query"+Query);
		
		try {
			Query=Query.replace("$Tirequest", tiresponse);
			String MasterRef=aWatchlistTIRequestModel.getMasterReference();
			System.out.println("MasterRef"+MasterRef);
			Query=Query.replace("$MasterRefrence", MasterRef);
			System.out.println("Insert Query-------->"+Query);
			aConnection = CommonUtil.DBConnection();
			
			System.out.println("Watchlist DatabaseConnection "+ aConnection);
			
			pst = aConnection.prepareStatement(Query);
			pst.executeUpdate();
			System.out.println("Successfully executed watchlist update ");
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in insert wl--->"+e);
			result="Failed";
			return result;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return result;
	}

	private String getTIResponseXMLfromBankReq(WatchlistTIResModel aWatchlistTIResModel,
			WatchlistTIRequestModel aWatchlistTIRequestModel) {
		System.out.println("Tirequest Formation");
		String tiResponseXML = null;
		RequestHeader requestHeader = new RequestHeader();
		System.out.println("Getting jaxtiContext");
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		System.out.println("Getting jaxtiContext Done");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		String Tiresponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
				"<ns4:ServiceRequest xmlns:ns2=\"urn:messages.service.ti.apps.tiplus2.misys.com\" xmlns:ns3=\"urn:common.service.ti.apps.tiplus2.misys.com\" xmlns:ns4=\"urn:control.services.tiplus2.misys.com\">\r\n" + 
				"<ns4:RequestHeader>\r\n" + 
				"<ns4:Service>TI</ns4:Service>\r\n" + 
				"<ns4:Operation>TFWLCRSP</ns4:Operation>\r\n" + 
				"<ns4:Credentials>\r\n" + 
				"<ns4:Name>SUPERVISOR</ns4:Name>\r\n" + 
				"</ns4:Credentials>\r\n" + 
				"<ns4:CorrelationId>${CorrelationID}</ns4:CorrelationId>\r\n" + 
				"</ns4:RequestHeader>\r\n" + 
				"<ns2:TFWLCRSP>\r\n" + 
				"<ns2:Context>\r\n" + 
				"<ns3:Branch>${Behalf_of_Branch}</ns3:Branch>\r\n" + 
				"<ns3:Product>${Product}</ns3:Product>\r\n" + 
				"<ns3:Event>${Event}</ns3:Event>\r\n" + 
				"<ns3:OurReference>${MasterReference}</ns3:OurReference>\r\n" + 
				"<ns3:BehalfOfBranch>${Behalf_of_Branch}</ns3:BehalfOfBranch>\r\n" + 
				"<ns3:EventReference>${EventReferance}</ns3:EventReference>\r\n" + 
				"</ns2:Context>\r\n" ;
				//"<ns2:EmbeddedItemss>\r\n";
	/*	try {
			if (aWatchlistTIResModel.getWatchlistTIResponseModel().size() > 0) {
			for (WatchlistTIResponseModel aWatchlistTIResponseModel : aWatchlistTIResModel
					.getWatchlistTIResponseModel()) {
				System.out.println("aWatchlistTIResponseModel.getID()"+aWatchlistTIResponseModel.getID());
				String id=aWatchlistTIResponseModel.getID();
				id=id.replace("&", " ");
				Tiresponse=Tiresponse+"<ns2:EmbeddedItems>\r\n"+ 
						"<ns2:ID>"+id+"</ns2:ID>\r\n";
				
				String MatchPercentage=aWatchlistTIResponseModel.getMatchPercentage();
				MatchPercentage=MatchPercentage.replace("&", " ");
				System.out.println("MatchPercentage-->"+MatchPercentage); 
				Tiresponse=Tiresponse+"<ns2:Description>"+MatchPercentage+"</ns2:Description>\r\n"
						+"</ns2:EmbeddedItems>\r\n";
			
			}}
			else {
				Tiresponse=Tiresponse+ "<ns2:EmbeddedItems>\r\n"+ 
						"<ns2:ID>dju</ns2:ID>\r\n"
						+"<ns2:Description>hjk</ns2:Description>\r\n"
						+"</ns2:EmbeddedItems>\r\n";
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in tirequest Framing");
		}*/
		Tiresponse=Tiresponse
				//"</ns2:EmbeddedItemss>\r\n"+""
				+ "<ns2:MasterRef>${MasterReference}</ns2:MasterRef>\r\n" + 
				"<ns2:EventReference>${EventReferance}</ns2:EventReference>\r\n" + 
				"<ns2:Status>F</ns2:Status>\r\n" + 
			"<ns2:FailedFields>\r\n" ;
				
				
		
		try {
			System.out.println("Tirespnose modification");
			System.out.println("Tiresponse -->"+Tiresponse);
			String CorrelationId= CommonUtil.generateCorrelationId();
			System.out.println("Correlation id"+CorrelationId);
			Tiresponse=Tiresponse.replace("${CorrelationID}", CorrelationId);
			System.out.println(Tiresponse);
			String BehalfBrch=aWatchlistTIRequestModel.getInputBranch();
			System.out.println("BehalfBrch"+BehalfBrch);
			Tiresponse=Tiresponse.replace("${Behalf_of_Branch}",BehalfBrch);
			System.out.println(Tiresponse);
			String MasterRef=aWatchlistTIRequestModel.getMasterReference();
			System.out.println("MasterRef"+MasterRef);
			Tiresponse=Tiresponse.replace("${MasterReference}",MasterRef);
			System.out.println(Tiresponse);
			String product=aWatchlistTIRequestModel.getProduct();
			System.out.println("product"+product);
			Tiresponse=Tiresponse.replace("${Product}",product);
			System.out.println(Tiresponse);
			String Event=aWatchlistTIRequestModel.getEventreference();		//=aWatchlistTIRequestModel.getEvent();
			String Event1=Event.substring(0, 3);
			System.out.println("Event"+Event1);
			Tiresponse=Tiresponse.replace("${Event}",Event1);
			System.out.println(Tiresponse);
			String EventRef=aWatchlistTIRequestModel.getEventreference();
			System.out.println("EventRef"+EventRef);
			Tiresponse=Tiresponse.replace("${EventReferance}",EventRef);
			System.out.println(Tiresponse);
			System.out.println("Tiresponse -->"+Tiresponse);
			if (aWatchlistTIResModel.getWatchlistTIResponseModel().size() > 0) {
			System.out.println("Entered IF");
				for (WatchlistTIResponseModel aWatchlistTIResponseModel : aWatchlistTIResModel
						.getWatchlistTIResponseModel()) {
					
					String id=aWatchlistTIResponseModel.getID();
					id=id.replace("&", " ");
					
					System.out.println("aWatchlistTIResponseModel.getCode()"+aWatchlistTIResponseModel.getCode());
					String Code=aWatchlistTIResponseModel.getCode();
					Code=Code.replace("&", " ");
					System.out.println("Code-->"+Code);
					String MatchPercentage1=aWatchlistTIResponseModel.getMatchPercentage();
					MatchPercentage1=MatchPercentage1.replace("&", " ");
					System.out.println("MatchPercentage-->"+MatchPercentage1); 
					String name=aWatchlistTIResponseModel.getCode();
					name=name.replace("&", " ");
					Tiresponse=Tiresponse+"<ns2:FailedField>\r\n"+ 
							"<ns2:Code>"+id+"</ns2:Code>\r\n";
					String Reason=aWatchlistTIResponseModel.getReason();
					Reason=Reason.replace("&", " ");
					System.out.println("Reason-->"+Reason);
					System.out.println("aWatchlistTIResponseModel.getReason()"+aWatchlistTIResponseModel.getReason());
					Tiresponse=Tiresponse+"<ns2:Reason>"+Reason+"\r\n"+
							"name: "+name+"\r\n"+"MatchPercentage: "+MatchPercentage1+"</ns2:Reason>\r\n"
							+"</ns2:FailedField>\r\n";	

					
					System.out.println("Left for");
				}
				
			}else {
				
				  Tiresponse=Tiresponse+ "<ns2:FailedField>\r\n"+ "<ns2:Code>No Match Found</ns2:Code>\r\n"
				  +"<ns2:Reason>No Match Found</ns2:Reason>\r\n" +"</ns2:FailedField>\r\n";
				
				
			}
			System.out.println("With Out Footer "+Tiresponse);
			Tiresponse=Tiresponse+"</ns2:FailedFields>\r\n" ;
			
			if (aWatchlistTIResModel.getWatchlistTIResponseModel().size() > 0) {
				Tiresponse=Tiresponse+"<ns2:GeneralNarrative>";
				String Construct = "";
				for (WatchlistTIResponseModel aWatchlistTIResponseModel : aWatchlistTIResModel
						.getWatchlistTIResponseModel()) {
					String id=aWatchlistTIResponseModel.getID();
					id=id.replace("&", " ");
					String MatchPercentage=aWatchlistTIResponseModel.getMatchPercentage();
					MatchPercentage=MatchPercentage.replace("&", " ");
					System.out.println("MatchPercentage-->"+MatchPercentage); 
					String name=aWatchlistTIResponseModel.getCode();
					name=name.replace("&", " ");
					System.out.println("Code-->"+name);
					String Queryvalue=aWatchlistTIResponseModel.getReason();
					Queryvalue=Queryvalue.replace("&", " ");
					System.out.println("Reason-->"+Queryvalue);
					Construct=Construct+"\r\nID: "+id+"";
					/*Construct=Construct+"MatchPercentage: "+MatchPercentage+"\r\n";
					Construct=Construct+"name: "+name+"\r\n";
					Construct=Construct+"Queryvalue: "+Queryvalue+"\r\n";*/
					//Construct=Construct+"\r\n";
					
					System.out.println("Construct"+Construct);
					
					
					
					
				}
				if(Construct.length()>148)
				Construct=Construct.substring(0, 147);
				Tiresponse=Tiresponse+Construct+"\r\n</ns2:GeneralNarrative>\r\n";
				
				
				 
			}
			
			Tiresponse=Tiresponse+"</ns2:TFWLCRSP>\r\n" + 
					"</ns4:ServiceRequest>";
			System.out.println("With footer "+Tiresponse);
			Tiresponse=Tiresponse.replaceAll("'","''");
			return Tiresponse;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in tirequest Framing");
		}
		try {
			System.out.println("Setting Header request");
			requestHeader = BOCCommonUtil.getRequestHeader(Constants.SUPERVISOR, CommonUtil.generateCorrelationId(),
					Constants.SERVICE_REQUEST, Constants.TFWLCRSP);
			System.out.println("Setting Header request done");
			ObjectFactory of = new ObjectFactory();
			System.out.println("ObjectFactory done");
			GatewayContextType gwycon = new GatewayContextType();
			System.out.println("Object creation done");
			
			// N-Able Pvt LTD / 20-02-2023
			// setBranch parameter error fixed with using "ObjectFactory".
			 
//			gwycon.setBranch(aWatchlistTIRequestModel.getInputBranch());
			gwycon.setBranch(of.createAccountDetailsResponseTypeBranch(aWatchlistTIRequestModel.getInputBranch()));		
			System.out.println("aWatchlistTIRequestModel.getInputBranch()" + aWatchlistTIRequestModel.getInputBranch());
			
			gwycon.setProduct(aWatchlistTIRequestModel.getProduct());
			System.out.println("aWatchlistTIRequestModel.getProduct()" + aWatchlistTIRequestModel.getProduct());
			gwycon.setEvent(aWatchlistTIRequestModel.getEvent());
			System.out.println("aWatchlistTIRequestModel.getEvent()" + aWatchlistTIRequestModel.getEvent());
			
			// N-Able Pvt LTD / 20-02-2023
			// setBranch parameter error fixed with using "ObjectFactory".
			
//			gwycon.setOurReference(aWatchlistTIRequestModel.getMasterReference());
			gwycon.setOurReference(of.createGWRELCTypeLicenseDetailsLicenseDetailOurReference(aWatchlistTIRequestModel.getMasterReference()));
			System.out.println("aWatchlistTIRequestModel.getMasterReference()" + aWatchlistTIRequestModel.getMasterReference());
			
			// N-Able Pvt LTD / 20-02-2023
			// setBranch parameter error fixed with using "ObjectFactory".
			
//			gwycon.setBehalfOfBranch(aWatchlistTIRequestModel.getBehalfOfBranch());
			gwycon.setBehalfOfBranch(of.createGWR700GTTypeBehalfOfBranch(aWatchlistTIRequestModel.getBehalfOfBranch()));
			System.out.println("aWatchlistTIRequestModel.getBehalfOfBranch()" + aWatchlistTIRequestModel.getBehalfOfBranch());
			gwycon.setEventReference(aWatchlistTIRequestModel.getEventreference());
			System.out.println("aWatchlistTIRequestModel.getEventreference()" + aWatchlistTIRequestModel.getEventreference());

			GWRIWLCType gwy = new GWRIWLCType();
			System.out.println("Set gwycon to gwy");
			gwy.setContext(gwycon);
			System.out.println("Set gwycon to gwy done");
			
			// N-Able Pvt LTD / 20-02-2023
			// setBranch parameter error fixed with using "ObjectFactory".
			
//			gwy.setMasterRef(aWatchlistTIRequestModel.getMasterReference());
			gwy.setMasterRef(of.createGatewayEventRTypeMasterRef(aWatchlistTIRequestModel.getMasterReference()));
			System.out.println("aWatchlistTIRequestModel.getMasterReference()" + aWatchlistTIRequestModel.getMasterReference());
			gwy.setEventReference(aWatchlistTIRequestModel.getEventreference());
			String status = "P";
			if (aWatchlistTIResModel.getWatchlistTIResponseModel().size() > 0) {
				FailedFields fields = new FailedFields();
				for (WatchlistTIResponseModel aWatchlistTIResponseModel : aWatchlistTIResModel
						.getWatchlistTIResponseModel()) {
					System.out.println("Entered For");
					FailedField afail = new FailedField();
					afail.setCode(aWatchlistTIResponseModel.getCode());
					System.out.println("aWatchlistTIResponseModel.getCode()"+aWatchlistTIResponseModel.getCode());
					afail.setReason(aWatchlistTIResponseModel.getReason());
					System.out.println("aWatchlistTIResponseModel.getReason()"+aWatchlistTIResponseModel.getReason());

					fields.getFailedField().add(afail);
					System.out.println("Left for");
				}
				gwy.setFailedFields(fields);
				status = "F";
			}
			System.out.println("Status--->"+status);
			gwy.setStatus(GWROFACPassFailFlag.fromValue(status));
			System.out.println("GWROFACPassFailFlag.fromValue(status)"+GWROFACPassFailFlag.fromValue(status));
			ServiceRequest sRequest = new ServiceRequest();
			System.out.println("requestHeader"+requestHeader);
			sRequest.setRequestHeader(requestHeader);
			JAXBElement<GWRIWLCType> watchl = of.createTFWLCRSP(gwy);
			List<JAXBElement<?>> sReqList = sRequest.getRequest();
			sReqList.add(watchl);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			System.out.println("<--Marshaller-->");
			jaxbMarshaller.marshal(sRequest, outStream);
			System.out.println("After the marshaller");
			System.out.println("Outstream"+outStream);
			tiResponseXML = outStream.toString();
			//tiResponseXML=tiResponseXML.replaceAll("'","''");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception In the tirequest --->" + e);
		}
		System.out.println("Tirequest Xml------>" + tiResponseXML);
		tiResponseXML=tiResponseXML.replaceAll("'","''");
		return tiResponseXML;
	}

	@SuppressWarnings("null")
	private WatchlistTIResModel callAPIEndPoint(WatchlistTIRequestModel aWatchlistTIRequestModel) {

		String bankResponseXML = null;
		List<String> bankReqXML = new LinkedList<>();
		String URL = BOCCommonUtil.retreiveFromProperties(Constants.WATCHLIST_URL);
		Loggers.general().info(LOG, "Framing");
		System.out.println("Framing");
		Loggers.general().info(LOG, "");
		try {
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getPortOfLoading())) {
				Loggers.general().info(LOG, "Port of Loading" + aWatchlistTIRequestModel.getPortOfLoading());
				bankReqXML.add(generatePortLoadReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getCountryOfOrigin())) {
				Loggers.general().info(LOG, "CountryOfOrigin" + aWatchlistTIRequestModel.getCountryOfOrigin());
				bankReqXML.add(generateCountryOrgReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getPlaceOfReceipt())) {
				Loggers.general().info(LOG, "PlaceOfReceipt" + aWatchlistTIRequestModel.getPlaceOfReceipt());
				bankReqXML.add(generatePlaceReceiptReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getPortOfDischarge())) {
				Loggers.general().info(LOG, "PortOfDischarge" + aWatchlistTIRequestModel.getPortOfDischarge());
				bankReqXML.add(generatePortDischReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getPlaceofDischarge())) {
				Loggers.general().info(LOG, "PlaceofDischarge" + aWatchlistTIRequestModel.getPlaceofDischarge());
				bankReqXML.add(generatePlaceDelivReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getVessel())) {
				Loggers.general().info(LOG, "Vessel" + aWatchlistTIRequestModel.getVessel());
				bankReqXML.add(generateVesselDetReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getShippingLine())) {
				Loggers.general().info(LOG, "ShippingLine" + aWatchlistTIRequestModel.getShippingLine());
				bankReqXML.add(generateShippingLineReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getCustomerName())) {
				Loggers.general().info(LOG, "CustomerName" + aWatchlistTIRequestModel.getCustomerName());
				bankReqXML.add(generateCustDetailReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getCounterPtyName())) {
				Loggers.general().info(LOG, "CounterPtyName" + aWatchlistTIRequestModel.getCounterPtyName());
				bankReqXML.add(generateCounterPtyReq(aWatchlistTIRequestModel));
			}
			if (BOCCommonUtil.isValidString(aWatchlistTIRequestModel.getCounterpartyBankName())) {
				Loggers.general().info(LOG,
						"CounterpartyBankName" + aWatchlistTIRequestModel.getCounterpartyBankName());
				bankReqXML.add(generateCounterPtyBankReq(aWatchlistTIRequestModel));
			}
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
		}

		System.out.println("Call ponit Started");
		Loggers.general().info(LOG, "Call ponit Started");
		WatchlistTIResModel aWatchlistTIResModel = new WatchlistTIResModel();

		List<WatchlistTIResponseModel> aWatchlistTIResponseModel = new LinkedList<WatchlistTIResponseModel>();
		System.out.println("Call ponit Started");
		Loggers.general().info(LOG, "Call ponit Started");
		Loggers.general().info(LOG, "Bank Request" + bankReqXML);
		// String BankRequrl=URLEncoder.encode(bankReqXML,"UTF-8");
		System.out.println("Bank Request      " + bankReqXML);

		System.out.println("Bank Request size     " + bankReqXML.size());

		for (int i = 0; i < bankReqXML.size(); i++) {

			try {
				HttpClient httpClient = HttpClientBuilder.create().build();
				// String BankRequrl=URLEncoder.encode(bankReqXML.get(i),"utf-8");
				String EncodeReq;
				EncodeReq = bankReqXML.get(i).replace(" ", "%20");
				System.out.println("EncodeReq--->" + EncodeReq);
				URL url = new URL(URL + EncodeReq);
				// String BankRequrl=URLEncoder.encode(bankReqXML.get(i),"utf-8");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				System.out.println("test1");
				System.out.println("Bank Request" + url);
				con.setRequestMethod("POST");
				//con.setRequestProperty("Content-Type", "application/json; utf-8");
				//con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);
				System.out.println("test");
				int Response_code = (con.getResponseCode());
				System.out.println("Response---" + Response_code);
				// HttpPost post = new HttpPost(url);
				// post.setHeader("Content-type", "application/json");
				// OutputStream os=con.getOutputStream();
				// HttpResponse response = httpClient.execute((HttpUriRequest) con);
				// System.out.println("Response---"+response);
				System.out.println("test2");
				if (Response_code == 200) {
					try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
						StringBuilder response1 = new StringBuilder();
						String responseLine = null;
						while ((responseLine = br.readLine()) != null) {
							response1.append(responseLine.trim());
						}
						System.out.println(response1.toString());
						bankResponseXML = response1.toString();
						Loggers.general().info(LOG, "Bank Response " + bankResponseXML);
						System.out.println("Bank Response " + bankResponseXML);
						aWatchlistTIResponseModel = setTIResModelFromBankXML(bankResponseXML,
								aWatchlistTIResponseModel);
					}
				}
				System.out.println("test3");
				// HttpEntity entity = response.getEntity();
				// bankResponseXML = EntityUtils.toString(entity);
				// Loggers.general().info(LOG, "Bank Response " + bankResponseXML);
				// System.out.println("Bank Response " + bankResponseXML);
				// aWatchlistTIResponseModel = setTIResModelFromBankXML(bankResponseXML,
				// aWatchlistTIResponseModel);

			} catch (Exception e) {
				Loggers.general().info(LOG, "Exception" + e);
				e.printStackTrace();
				System.out.println("Exception In the Watchlist-------->" + e);
			}

		}
		aWatchlistTIResModel.setWatchlistTIResponseModel(aWatchlistTIResponseModel);
		return aWatchlistTIResModel;
	}

	private String generateCustDetailReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */

		// List<String> bankRequestXML = null;
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getCustomerName() + "&id=&resaddr="
					+ aWatchlistTIRequestModel.getCustomerAddressLine2()
					+ aWatchlistTIRequestModel.getCustomerAddressLine3()
					+ aWatchlistTIRequestModel.getCustomerAddressLine4()
					+ aWatchlistTIRequestModel.getCustomerAddressLine5() + "&entitytype=\"P\"&src=\"tf\"";

			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generateCounterPtyReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {

		/*
		 * , List<String> bankResXML
		 */

		// List<String> bankRequestXML = null;
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getCounterPtyName() + "&id=&resaddr="
					+ aWatchlistTIRequestModel.getCounterpartyAddressLine2()
					+ aWatchlistTIRequestModel.getCounterpartyAddressLine3()
					+ aWatchlistTIRequestModel.getCounterpartyAddressLine4()
					+ aWatchlistTIRequestModel.getCounterpartyAddressLine5() + "&entitytype=\"P\"&src=\"tf\"";

			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generateCounterPtyBankReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		// List<String> bankRequestXML = null;
		String bak = null;
		try {
			Loggers.general().info(LOG, "CounterpartyBankName");
			System.out.println("CounterpartyBankName");
			bak = "?name=" + aWatchlistTIRequestModel.getCounterpartyBankName() + "&id="+aWatchlistTIRequestModel.getSwiftID()+"&resaddr="
					+ aWatchlistTIRequestModel.getCounterpartyBankAddressLine2()
					+ aWatchlistTIRequestModel.getCounterpartyBankAddressLine3()
					+ aWatchlistTIRequestModel.getCounterpartyBankAddressLine4()
					+ aWatchlistTIRequestModel.getCounterpartyBankAddressLine5() + "&entitytype=\"bank\"&src=\"tf\"";

			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private List<WatchlistTIResponseModel> setTIResModelFromBankXML(String bankResponseXML,
			List<WatchlistTIResponseModel> aWatchlistTIResponseModel) {
		System.out.println("Watchlis Respone Builder");
		System.out.println("Bank Response--->" + bankResponseXML);
		ObjectMapper om = new ObjectMapper();
		WatchlistResponse aWatchlistResponse = new WatchlistResponse();
		try {

			aWatchlistResponse = om.readValue(bankResponseXML, WatchlistResponse.class);
			System.out.println("Total--->" + aWatchlistResponse.getWlMatch().getMatchedResults().getTotal());
			int a = aWatchlistResponse.getWlMatch().getMatchedResults().getTotal();
			for (int i = 0; i < a; i++) {
				System.out.println(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i)
						.getFields().get(0).getName());
				WatchlistTIResponseModel aWTR = new WatchlistTIResponseModel();
				aWTR.setCode(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i).getFields()
						.get(0).getName());
				aWTR.setReason(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i)
						.getFields().get(0).getValue());
				System.out.println("Score Value");
				System.out.println(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i)
						.getFields().get(0).getScore());
				aWTR.setMatchPercentage(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i)
						.getFields().get(0).getScore());
				aWTR.setID(aWatchlistResponse.getWlMatch().getMatchedResults().getMatchedRecords().get(i)
						.getId());
				aWatchlistTIResponseModel.add(aWTR);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Watchlis Respone Builder E-->" + e);
		}

		return aWatchlistTIResponseModel;

	}

	private String generatePortLoadReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
//List<String> bankResXML
		Loggers.general().info(LOG, "generatePortLoadReq");
		System.out.println("generatePortLoadReq");
		String bak = "";
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getPortOfLoading()
					+ "&id=&resaddr=&entitytype=\"port\"&src=\"tf\"";

			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);

		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generateCountryOrgReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		Loggers.general().info(LOG, "generateCountryOrgReq");
		System.out.println("generateCountryOrgReq");
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getCountryOfOrigin()
					+ "&id=&resaddr=&entitytype=\"country\"&src=\"tf\"";
			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generatePlaceReceiptReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		Loggers.general().info(LOG, "generatePlaceReceiptReq");
		System.out.println("generatePlaceReceiptReq");
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getPlaceOfReceipt()
					+ "&id=&resaddr=&entitytype=\"city\"&src=\"tf\"";
			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generatePortDischReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		Loggers.general().info(LOG, "generatePortDischReq");
		System.out.println("generatePortDischReq");
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getPortOfDischarge()
					+ "&id=&resaddr=&entitytype=\"port\"&src=\"tf\"";
			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generatePlaceDelivReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		Loggers.general().info(LOG, "generatePlaceDelivReq");
		System.out.println("generatePlaceDelivReq");
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getPlaceofDischarge()
					+ "&id=&resaddr=&entitytype=\"city\"&src=\"tf\"";
			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}

	private String generateVesselDetReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
		/*
		 * , List<String> bankResXML
		 */
		Loggers.general().info(LOG, "generateVesselDetReq");
		System.out.println("generateVesselDetReq");
		String bak = null;
		try {
			bak = "?name=" + aWatchlistTIRequestModel.getVessel() + "&id=" + aWatchlistTIRequestModel.getOthers()
					+ "&resaddr=&entitytype=\"vessel\"&src=\"tf\"";
			Loggers.general().info(LOG, "bak" + bak);
			System.out.println("bak" + bak);
			// bankResXML.add(bak);
		} catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
		}

		return bak;
	}
		private String generateShippingLineReq(WatchlistTIRequestModel aWatchlistTIRequestModel) {
			/*
			 * , List<String> bankResXML
			 */
			Loggers.general().info(LOG, "generateShippingLineReq");
			System.out.println("generateShippingLineReq");
			String bak = null;
			try {
				bak = "?name=" + aWatchlistTIRequestModel.getShippingLine() + "&id=" + aWatchlistTIRequestModel.getContainerNumber()
						+ "&resaddr=&entitytype=\"vessel\"&src=\"tf\"";
				Loggers.general().info(LOG, "bak" + bak);
				System.out.println("bak" + bak);
				// bankResXML.add(bak);
			} catch (Exception e) {
				Loggers.general().info(LOG, "Exception" + e);
				System.out.println("Exception" + e);
				e.printStackTrace();
			}

			return bak;
		}
	private WatchlistTIRequestModel setTIReqModelFromTIReqXML(String tiRequestXml) {

		WatchlistTIRequestModel aWatchlistTIRequestModel = new WatchlistTIRequestModel();
		String master_ref=null;
		try {
			System.out.println("WL request framing ");
			master_ref=CommonUtil.getTagValue("Master_Reference", tiRequestXml);
			try {
			master_ref=CheckMaster(master_ref);
			}catch (Exception e) {
			Loggers.general().info(LOG, "Exception" + e);
			System.out.println("Exception" + e);
			e.printStackTrace();
			}
			aWatchlistTIRequestModel.setMasterReference(master_ref);
			//aWatchlistTIRequestModel.setMasterReference(CommonUtil.getTagValue("Master_Reference", tiRequestXml));
			Loggers.general().info(LOG, "Master_Reference" + aWatchlistTIRequestModel.getMasterReference());
			aWatchlistTIRequestModel.setEventreference(CommonUtil.getTagValue("Event_Reference", tiRequestXml));
			aWatchlistTIRequestModel.setBehalfOfBranch(CommonUtil.getTagValue("Behalf_of_Branch", tiRequestXml));
			aWatchlistTIRequestModel.setVessel(CommonUtil.getTagValue("Vessel", tiRequestXml));
			Loggers.general().info(LOG, "Vessel" + aWatchlistTIRequestModel.getVessel());
			aWatchlistTIRequestModel.setPortOfLoading(CommonUtil.getTagValue("Port_of_Loading", tiRequestXml));
			Loggers.general().info(LOG, "Port_of_Loading" + aWatchlistTIRequestModel.getPortOfLoading());
			aWatchlistTIRequestModel.setPortOfDischarge(CommonUtil.getTagValue("Port_of_Discharge", tiRequestXml));
			Loggers.general().info(LOG, "Port_of_Discharge" + aWatchlistTIRequestModel.getPortOfDischarge());
			aWatchlistTIRequestModel.setShippingLine(CommonUtil.getTagValue("Shipping_Line", tiRequestXml));
			Loggers.general().info(LOG, "Shipping_Line" + aWatchlistTIRequestModel.getShippingLine());
			aWatchlistTIRequestModel.setContainerNumber(CommonUtil.getTagValue("Container_Number", tiRequestXml));
			Loggers.general().info(LOG, "Container_Number" + aWatchlistTIRequestModel.getContainerNumber());
			aWatchlistTIRequestModel.setPlaceOfReceipt(CommonUtil.getTagValue("Place_of_Receipt", tiRequestXml));
			Loggers.general().info(LOG, "Place_of_Receipt" + aWatchlistTIRequestModel.getPlaceOfReceipt());
			aWatchlistTIRequestModel.setPlaceofDischarge(CommonUtil.getTagValue("Place_of_Discharge", tiRequestXml));
			Loggers.general().info(LOG, "Place_of_Discharge" + aWatchlistTIRequestModel.getPlaceofDischarge());
			aWatchlistTIRequestModel.setOthers(CommonUtil.getTagValue("Others", tiRequestXml));
			Loggers.general().info(LOG, "Others" + aWatchlistTIRequestModel.getOthers());
			aWatchlistTIRequestModel.setCountryOfOrigin(CommonUtil.getTagValue("Country_of_Origin", tiRequestXml));
			Loggers.general().info(LOG, "Country_of_Origin" + aWatchlistTIRequestModel.getCountryOfOrigin());
			aWatchlistTIRequestModel.setCustomerName(CommonUtil.getTagValue("CustomerName", tiRequestXml));
			Loggers.general().info(LOG, "CustomerName" + aWatchlistTIRequestModel.getCustomerName());
			aWatchlistTIRequestModel.setCounterPtyName(CommonUtil.getTagValue("CounterpartyName", tiRequestXml));
			Loggers.general().info(LOG, "CounterpartyName" + aWatchlistTIRequestModel.getCounterPtyName());
			aWatchlistTIRequestModel.setInputBranch(CommonUtil.getTagValue("InputBranch", tiRequestXml));
			aWatchlistTIRequestModel.setProduct(CommonUtil.getTagValue("ProductCode", tiRequestXml));
			Loggers.general().info(LOG, "ProductCode" + aWatchlistTIRequestModel.getProduct());
			aWatchlistTIRequestModel.setSwiftID(CommonUtil.getTagValue("SwiftID", tiRequestXml));
			aWatchlistTIRequestModel
					.setCounterpartyBankName(CommonUtil.getTagValue("CounterpartyBankName", tiRequestXml));
			Loggers.general().info(LOG, "CounterpartyBankName" + aWatchlistTIRequestModel.getCounterpartyBankName());
			aWatchlistTIRequestModel
					.setCustomerAddressLine2(CommonUtil.getTagValue("CustomerAddressLine2", tiRequestXml));
			Loggers.general().info(LOG, "CustomerAddressLine2" + aWatchlistTIRequestModel.getCustomerAddressLine2());
			aWatchlistTIRequestModel
					.setCustomerAddressLine3(CommonUtil.getTagValue("CustomerAddressLine3", tiRequestXml));
			Loggers.general().info(LOG, "CustomerAddressLine3" + aWatchlistTIRequestModel.getCustomerAddressLine3());
			aWatchlistTIRequestModel
					.setCustomerAddressLine4(CommonUtil.getTagValue("CustomerAddressLine4", tiRequestXml));
			Loggers.general().info(LOG, "CustomerAddressLine4" + aWatchlistTIRequestModel.getCustomerAddressLine4());
			aWatchlistTIRequestModel
					.setCustomerAddressLine5(CommonUtil.getTagValue("CustomerAddressLine5", tiRequestXml));
			Loggers.general().info(LOG, "CustomerAddressLine5" + aWatchlistTIRequestModel.getCustomerAddressLine5());
			aWatchlistTIRequestModel
					.setCounterpartyAddressLine2(CommonUtil.getTagValue("CounterpartyAddressLine2", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyAddressLine2" + aWatchlistTIRequestModel.getCounterpartyAddressLine2());
			aWatchlistTIRequestModel
					.setCounterpartyAddressLine3(CommonUtil.getTagValue("CounterpartyAddressLine3", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyAddressLine3" + aWatchlistTIRequestModel.getCounterpartyAddressLine3());
			aWatchlistTIRequestModel
					.setCounterpartyAddressLine4(CommonUtil.getTagValue("CounterpartyAddressLine4", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyAddressLine4" + aWatchlistTIRequestModel.getCounterpartyAddressLine4());
			aWatchlistTIRequestModel
					.setCounterpartyAddressLine5(CommonUtil.getTagValue("CounterpartyAddressLine5", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyAddressLine5" + aWatchlistTIRequestModel.getCounterpartyAddressLine5());
			aWatchlistTIRequestModel.setCounterpartyBankAddressLine2(
					CommonUtil.getTagValue("CounterpartyBankAddressLine2", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyBankAddressLine2" + aWatchlistTIRequestModel.getCounterpartyBankAddressLine2());
			aWatchlistTIRequestModel.setCounterpartyBankAddressLine3(
					CommonUtil.getTagValue("CounterpartyBankAddressLine3", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyBankAddressLine3" + aWatchlistTIRequestModel.getCounterpartyBankAddressLine3());
			aWatchlistTIRequestModel.setCounterpartyBankAddressLine4(
					CommonUtil.getTagValue("CounterpartyBankAddressLine4", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyBankAddressLine4" + aWatchlistTIRequestModel.getCounterpartyBankAddressLine4());
			aWatchlistTIRequestModel.setCounterpartyBankAddressLine5(
					CommonUtil.getTagValue("CounterpartyBankAddressLine5", tiRequestXml));
			Loggers.general().info(LOG,
					"CounterpartyBankAddressLine5" + aWatchlistTIRequestModel.getCounterpartyBankAddressLine5());
			Loggers.general().info(LOG, "WL Framing completed");
			System.out.println("WL Framing completed");

		} catch (Exception e) {
			System.out.println("Exception in watchlist request making--->" + e);
			Loggers.general().info(LOG, "WL Framing completed" + e);
			e.printStackTrace();

		}
		return aWatchlistTIRequestModel;
	}

	private String CheckMaster(String master_ref) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		PreparedStatement pst1 = null;
		ResultSet rst1 = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_MASTER_REF);
			pst.setString(1, master_ref.trim());
			rst = pst.executeQuery();
			if (!rst.next())
			{
				pst1 = aConnection.prepareStatement(QueryConstants.GET_ORIGINAL_REF);
				pst1.setString(1, master_ref.trim());
				rst1= pst1.executeQuery();
				if (rst1.next())
				{
					master_ref=rst1.getString(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
			CommonUtil.CloseConnection(aConnection, pst1, rst1);
		}

		return master_ref;
	
	}

}
