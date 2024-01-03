$(document).ready(function () {
	
var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= IOP;	
			return;
			}
			
		/** var Resultval=FormRights();
	  if(Resultval!='Yes')
	 {
	 return;
	 }
		**/	
 var loggedinuser = getUrlParam("username");
 
 document.getElementById("LoggedInUser").value=loggedinuser;
 $(".DateFields").cdatepicker({
        showOn: "button",
        buttonImage: "ThemeproLO/Common/Images/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd-mm-yy",
        yearRange: "c-10:c+10"
    });
	
	
	});
	
	
	
	function ChkValues(data1)
{
       
        //listbox_selectall('RemenuName', true);
       // getValue();
        //var XmlTxt = getValue();
       // var FromLBVal = document.getElementById('FromLBValue').value;
	 var chkmndtry = ChkMandatoryFields("Mndtry");


     if (chkmndtry == "No") {
         return;
     }
	 
	 var id="";

	for(var i=0;i<data1.split(',').length;i++)
	{
		
		if(data1.split(',')[i]==undefined)
		{
			
			var error='';
			
		}
		else{
			 id += data1.split(',')[i]+"|";
		}
		
		
	}
	 $("#Designation").val(id.slice(0,-2));
	 
        var XmlTxt = submitdata("DBfields");
     
       // alert(XmlTxt);
        $.ajax({
            url: "ThemeproLO/UI_formdatains_DesignateUser",
            data: { xml: XmlTxt },
            async: false,
           // dataType: "json",
            type: 'POST',
            success: function OnSuccess_submit(data) {
                if (data == 'Success') {

                   // $("#Tab3").trigger("click");
                   
                   window.alert(LoadFrmXML("V0106"));
                    document.getElementById('Designation').value = '';
                    document.getElementById('StartDate').value = '';
                    document.getElementById('EndDate').value = '';
                 
                }
               
            },
            error: function OnError_submit(xmlRequest) {
                window.alert(LoadFrmXML("V0075"));

            }
    });

	
}

 /*
 $('#save').click(function () {
       
        //listbox_selectall('RemenuName', true);
       // getValue();
        //var XmlTxt = getValue();
       // var FromLBVal = document.getElementById('FromLBValue').value;
	 var chkmndtry = ChkMandatoryFields("Mndtry");


     if (chkmndtry == "No") {
         return;
     }
        var XmlTxt = submitdata("DBfields");
     
       // alert(XmlTxt);
        $.ajax({
            url: "ThemeproLO/UI_formdatains_DesignateUser",
            data: { xml: XmlTxt },
            async: false,
           // dataType: "json",
            type: 'POST',
            success: function OnSuccess_submit(data) {
                if (data == 'Success') {

                   // $("#Tab3").trigger("click");
                   
                	alertify.alert("Data Saved");
                    document.getElementById('Designation').value = '';
                    document.getElementById('StartDate').value = '';
                    document.getElementById('EndDate').value = '';
                 
                }
               
            },
            error: function OnError_submit(xmlRequest) {
            	alertify.alert("Submission Failed");

            }
    });
    });*/
