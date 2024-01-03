$(document).ready(function () {

	 var IOP=window.location.origin+'/jw';
	 
	  var Rdpage = LoadFrmXML("PG001");
		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= Rdpage;	
			return;
			}
			
					 /*var Resultval=FormRights();
	  if(Resultval!='Yes')
	 {
	 return;
	 }*/
	
    var id = getUrlParam("id");
	    var AppID = getUrlParam("AppID");
	    var activityID = getUrlParam("activityID");
			
			

	    
	    var Username = GetCurrentUser();
	    var MenuID = getUrlParam("MenuID");
	    
		GetCustomPageHdrStrip("File Forward","(Select Workflow & then ID to view file history)",getUrlParam("RefID"));
		
	    var IOP=window.location.origin+'/jw';
	    document.getElementById('LAPP_ID').value = id;
	    document.getElementById('Username').value = Username;
	    document.getElementById('MenuName').value = MenuID;
	    
			var Username = GetCurrentUser();
	document.getElementById("LoggedInUser").value = Username;
	
  //$("#GetFileHistData").trigger("click");
  
});


function REPTHISTPAGEDATA(Val)

{
	
	var OP = UI_getdata($("#WFIDHist").val(),"","","","","Sam_sRGetHistPageFlds");
		
		$(".trremove").remove();
		//document.getElementById('Table2').style.display=""
		
		$("#GetFileHistData").trigger("click");
		
	     $("#HistFields").append(OP.replace("</HTML></a></Resultset>","").replace("<Resultset><a><HTML>",""));
	
}

$(document).on('click', '#lookup', function () {
	
	var IOP=window.location.origin;

	//var processId= $(this).closest('tr').children('td:eq(4)').text();
	var RedirectURL="";
	
	var processId= $(this).closest('tr').find('td:eq('+$($('th:contains("ProcessID")')[0]).index()+')').text();
	var activityID= $(this).closest('tr').find('td:eq('+$($('th:contains("ActivityID")')[0]).index()+')').text();
	
	var URL= "/ThemePro_LSW/QueueForwardJobs";
	//UI_getdata(processId,'','','','','Sam_sGetHistViewUrl');
	
	///
	
	//URL = URL.replace(/<Resultset><a><Result>/g, "");	
	//URL = URL.replace('</Result></a></Resultset>','');
	
	// $("#SubmitSuccess").val("Hist");
	 // RedirectURL = IOP+URL.split("|")[0]+"?activityId="+activityID+"&processId="+processId+"&View=Hist";
	 
	 //RedirectURL = IOP+LoadFrmXML("PG010")+"?activityId="+activityID+"&processId="+processId +"&View=Hist";
	 
	 RedirectURL = IOP+URL+"?activityID="+activityID+"&id="+processId;
	 
	 WintabsCtrl(RedirectURL,'File Forward',$("#ApplicntNo").val(),'yes');
		/*
//	 var activityId = $(this).closest('tr').children('td:eq(3)').text();
	 
	
 //   var RedirectURL = IOP+"/web/userview/Repco50L/UPTO50L//Inbox?j_username=admin&j_password=admin&_mode=assignment&activityId=" + activityID;
	 //"http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formComplete.jsp?activityId=" + activityId + "&redirect=http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formSuccess.jsp";
	  // var RedirectURL = IOP+"ThemeproLO/Upto50L/Pages/U50L_AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
	  
	    WintabsCtrl(RedirectURL,'History View','','yes');
	  
	 /* if(activityID.indexOf('HomeLoan')>-1)
	  {
		  
		  $("#SubmitSuccess").val("Hist");
		  RedirectURL = IOP+"ThemeproLO/Loan_Sanction/Pages/AllForms/AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		  
		    WintabsCtrl(RedirectURL,'History View',$("#ApplicantId").val(),'yes');
		  
	  }
	   else if(activityID.indexOf('CustomerCreation')>-1)
	  {
		  $("#SubmitSuccess").val("Hist");
		 // RedirectURL = IOP+"ThemeproLO/Customer_Creation/Pages/LCC_CustomerCreation.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		  RedirectURL = IOP+"ThemeproLO/Customer_Creation/Pages/AllForms/AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		    WintabsCtrl(RedirectURL,'History View',$("#ApplicantId").val(),'yes');
		  
	  }
	  
	  
	   else if(activityID.indexOf('UPTO50L')>-1)
	  {
		  
		  $("#SubmitSuccess").val("Hist");
		  RedirectURL = IOP+"ThemeproLO/Upto50L/Pages/AllForms/AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		  
		    WintabsCtrl(RedirectURL,'History View',$("#ApplicantId").val(),'yes');
		  
	  }*/
	  
});


