$(document).ready(function () {

	 var IOP=window.location.origin;
	 
	  var Rdpage = LoadFrmXML("PG001");
		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= Rdpage;	
			return;
			}
			
					 var Resultval=FormRights();
	  if(Resultval!='Yes')
	 {
	 return;
	 }
	
      var id = getUrlParam("id");
	    var AppID = getUrlParam("AppID");
	    var activityID = "FormEdit";
		var processId = getUrlParam("processId");	
		var FormID = getUrlParam("FormID");	
			
	var URL= UI_getdata(processId,'','','','','Sam_sGetHistViewUrl');
	URL = URL.replace("<Resultset><a><Result>", "");	
	URL = URL.replace('</Result></a></Resultset>','');
	
	$("#SubmitSuccess").val("Hist");
	RedirectURL = IOP+ URL.split("|")[0] +"?activityId="+activityID+"&processId="+processId +"&View=FormEdit&FormID="+FormID;
	    document.getElementById('assignmentExternalForm').src=RedirectURL;
	    var Username = GetCurrentUser();
	    var MenuID = getUrlParam("MenuID");
	    
	    var IOP=window.location.origin+'/jw';
	    //document.getElementById('LAPP_ID').value = id;
	    document.getElementById('Username').value = Username;
	    document.getElementById('MenuName').value = MenuID;
	    
			var Username = GetCurrentUser();
	document.getElementById("LoggedInUser").value = Username;
	
  //$("#GetFormEditData").trigger("click");
  

  
  
    $(document).on('click', '#ReevaluateLkp', function () {
	  
	  
	  var IOP=window.location.origin+LoadFrmXML("PG007");

	//var processId= $(this).closest('tr').children('td:eq(4)').text();
	var RedirectURL="";
	
	var processId= $(this).closest('tr').find('td:eq('+$($('th:contains("ProcessID")')[0]).index()+')').text();
	var activityID= $(this).closest('tr').find('td:eq('+$($('th:contains("ActivityID")')[0]).index()+')').text();
	  
	      alertify.confirm(LoadFrmXML("V0155"), function (e) {
	if (e) 
	{
	  var Result=UI_getdata(processId,activityID,"","","","Sam_sWFReevaluate");
	  
	  if($(Result).find("result").text() == 'Success');			 
	  {
	  $.ajax({
                 type: 'POST',
                 url: IOP+activityID,
                // data: params,
                 dataType : "text",
				 async:false,
                 xhrFields: {
                     withCredentials: true
                 },
                 success: function(data) {
                    // OP="Accepted";
					 window.success("Activity Re-evaluated");
                 },
                 error: function(data) {
                     try {
                         // do nothing for now
                        // if (callback.error) {
                          //   callback.error.call(thisWindow, data);
						  	//alertify.alert(LoadFrmXML("V0127"));
								//window.alert(LoadFrmXML("V0127"));
						  //alertify.alert('Error: File not Accepted');
                        // }
                     }
                     catch (e) {}
                 }
               })
	  }  
	  }
	   else
	{
		return;
	}
	});  
	  
  });
  
    $(document).on('click', '#TerminateLkp', function () {
	  
	  
	  var IOP=window.location.origin+LoadFrmXML("PG008");

	//var processId= $(this).closest('tr').children('td:eq(4)').text();
	var RedirectURL="";
	
	var processId= $(this).closest('tr').find('td:eq('+$($('th:contains("ProcessID")')[0]).index()+')').text();
	var activityID= $(this).closest('tr').find('td:eq('+$($('th:contains("ActivityID")')[0]).index()+')').text();
	  
	  
	    alertify.confirm(LoadFrmXML("V0156"), function (e) {
	if (e) 
	{
	  var Result=UI_getdata(processId,activityID,"","","","Sam_sWFTerminate");
	    if($(Result).find("result").text() == 'Success');			 
	  {
		  $("#GetFormEditData").trigger("click");
		  window.success("Activity Terminated");
	  }
	}
	 else
	{
		return;
	}
	});   
	  
  });
  
});


function REPTHISTPAGEDATA()

{
	
	var OP = UI_getdata($("#WFIDHist").val(),"","","","CBR","Sam_sRGetHistPageFlds");
		
		$(".trremove").remove();
		//document.getElementById('Table2').style.display=""
		
		$("#GetFormEditData").trigger("click");
		
	     $("#HistFields").append(OP.replace("</HTML></a></Resultset>","").replace("<Resultset><a><HTML>",""));
	
}

$(document).on('click', '#lookup', function () {
	
	var processId=$("#PrcsID").val();
	
	if(processId==''){
	window.alert("Choose Branch and ID");
	return;
	}
	
	var IOP=window.location.origin;

	var RedirectURL="";
	
	//var processId= $(this).closest('tr').find('td:eq('+$($('th:contains("ProcessID")')[0]).index()+')').text();
	//var activityID= $(this).closest('tr').find('td:eq('+$($('th:contains("ActivityID")')[0]).index()+')').text();
	var activityID='';
	var URL= UI_getdata(processId,'','','','','Sam_sGetHistViewUrl');
	URL = URL.replace("<Resultset><a><Result>", "");	
	URL = URL.replace('</Result></a></Resultset>','');
	
	$("#SubmitSuccess").val("Hist");
	RedirectURL = IOP+"ThemeproLO/Common/Pages/Config/FormEditPage.jsp?activityId="+activityID+"&processId="+processId +"&View=Hist";
	WintabsCtrl(RedirectURL,'Form Edit',$('input[name="'+URL.split("|")[1]+'"]').val(),'yes');
	
//	 var activityId = $(this).closest('tr').children('td:eq(3)').text();
	 
	
 //   var RedirectURL = IOP+"/web/userview/Repco50L/UPTO50L//Inbox?j_username=admin&j_password=admin&_mode=assignment&activityId=" + activityID;
	 //"http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formComplete.jsp?activityId=" + activityId + "&redirect=http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formSuccess.jsp";
	  // var RedirectURL = IOP+"ThemeproLO/Upto50L/Pages/U50L_AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
	  
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
		  
	  }
	  
    /*
    $.ajax({
    url: RedirectURL,
    type: "GET",
    async: false,
    dataType: "html"
    }).done(function (data) {

    var $dom = $(document.createElement("html"));
    $dom[0].innerHTML = data;

    var $fieldset = $dom.find("body");

    //    document.getElementById("mainPage").innerHTML = $fieldset[0].innerHTML;

    //  document.getElementById("StartU50L").target = "_blank";

    }).fail(function (jqXHR, textStatus, errorThrown) {
    $("#mainPage").html("Error!! File is not loaded");
    });
    */

    //  window.top.location.href = RedirectURL;
	  // window.open(RedirectURL, '_blank','toolbar=no,resizable=yes,location=0');
});


