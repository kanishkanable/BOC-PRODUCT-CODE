$(document).ready(function () {

	 var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= IOP;	
			return;
			}
			
					 var Resultval=FormRights();
	  if(Resultval!='Yes')
	 {
	 return;
	 }
	
    var id = getUrlParam("id");
	    var AppID = getUrlParam("AppID");
	    var activityID = getUrlParam("activityID");
	    
	    var Username = GetCurrentUser();
	    var MenuID = getUrlParam("MenuID");
	    
	    var IOP=window.location.origin+'/jw';
	    document.getElementById('LAPP_ID').value = id;
	    document.getElementById('Username').value = Username;
	    document.getElementById('MenuName').value = MenuID;
	    
		 $.ajax({

		        url: "ThemeproLO/UI_formdata_GetHistoryQueuePageHTML",
		        data: { Username: Username, MenuName: MenuID },
		        async: false,
		       // dataType: "json",
		        type: 'POST',
		        success: function OnSuccess_submit(data) {
		          
		                //alert(data);
		                $("#HistoryPage").append(data);
		                //iframeurl();              
		          
		        },
		        error: function OnError_submit(xmlRequest) {


		        	window.alert(LoadFrmXML("V0075"));

		        }
		    });
  $("#assignmentComplete").trigger("click");
});


$(document).on('click', '#lookup', function () {
	
	var IOP=window.location.origin;

	//var processId= $(this).closest('tr').children('td:eq(4)').text();
	var RedirectURL="";
	
	var processId= $(this).closest('tr').find('td:eq('+$($('th:contains("ProcessID")')[0]).index()+')').text();
	var activityID= $(this).closest('tr').find('td:eq('+$($('th:contains("ActivityID")')[0]).index()+')').text();
	
	
	
//	 var activityId = $(this).closest('tr').children('td:eq(3)').text();
	 
	
 //   var RedirectURL = IOP+"/web/userview/Repco50L/UPTO50L//Inbox?j_username=admin&j_password=admin&_mode=assignment&activityId=" + activityID;
	 //"http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formComplete.jsp?activityId=" + activityId + "&redirect=http://103.230.85.234:8585/jw/TestApplication/JSPIMP/CommonAssignment/formSuccess.jsp";
	  // var RedirectURL = IOP+"ThemeproLO/Upto50L/Pages/U50L_AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
	  
	  if(activityID.indexOf('HomeLoan')>-1)
	  {
		  
		  RedirectURL = IOP+"ThemeproLO/Loan_Sanction/Pages/AllForms/AllForms.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		  
	  }
	   else if(activityID.indexOf('CustomerCreation')>-1)
	  {
		  
		  RedirectURL = IOP+"ThemeproLO/Customer_Creation/Pages/LCC_CustomerCreation.jsp?activityId="+activityID+"&processId="+processId+"&View=Hist";
		  
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
	   window.open(RedirectURL, '_blank','toolbar=no,resizable=yes,location=0');
});


