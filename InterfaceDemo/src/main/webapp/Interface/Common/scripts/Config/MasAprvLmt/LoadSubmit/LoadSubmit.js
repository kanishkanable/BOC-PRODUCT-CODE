
$(document).ready(function(){
	
	var IOP=window.location.origin+'/'+LoadFrmXML("PG001");
/*
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
*/
	 $("#Newenddtmndtry").hide();
	 $("#Editenddtmndtry").hide();
	 
	 
	 
	 var loggedinuser = GetCurrentUserFName();
	 document.getElementById('APLT_DtModified').value = get2dateTime();

	     if (document.getElementById('APLT_DtCreated').value == "" || document.getElementById('APLT_DtCreated').value == "null") {
	         document.getElementById('APLT_DtCreated').value = get2dateTime();
	     }

	     if (document.getElementById('APLT_CreatedBy').value == "" || document.getElementById('APLT_CreatedBy').value == "null") {
	         document.getElementById('APLT_CreatedBy').value = loggedinuser;
	     }
	     document.getElementById('APLT_ModifiedBy').value = loggedinuser;
	 
	
	$(".NewTab").on("click", function () {
		$('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
		
        $(".NewDetails").show();
        $(".EditDetails").hide();
        $(".AddZero").val('0');
        $("#Newenddtmndtry").hide();
      
	});
	
	$(".EditTab").on("click", function () {
		$('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
		
        $(".NewDetails").hide();
        $(".EditDetails").show();
        $(".AddZero").val('0');
        $("#Editenddtmndtry").hide();
        $("#BTNMasAprvLmt").trigger("click");
	});
	
	
	 $(".DateFields").cdatepicker({
	        showOn: "button",
	        buttonImage: "/ThemePro_LSW/ThemeproLO/Common/Images/calendar.png",
	        buttonImageOnly: true,
	        changeMonth: true,
	        changeYear: true,
	        dateFormat: "dd-mm-yy",
	        yearRange: "c-10:c+10"
	    });
	       

	    $('#save').click(function () {
	    	
	    	 $(".Newdetls").addClass("Mndtry");
			 $(".Editdetls").removeClass("Mndtry");
			 
			 var chkmndtry = ChkMandatoryFields("Mndtry");


		     if (chkmndtry == "No") {
		         return;
		     }
		        var XmlTxt = submitdata("DBfields");
		     var param='New';
		     var SPName ='Sam_sMasAprvLmt';
		       // alert(XmlTxt);
		        $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		                   // $("#Tab3").trigger("click");
		                	
		                	  window.alert(LoadFrmXML("V0108"));
		                
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    $(".AddZero").val('0');
		                    document.getElementById("NewAPLT_Flag").value='';
		              
		                    
		                 
		                }
		                else if(data=='Exists')
		                	{
		                	//alertify.alert("Values are already Exists");
								window.alert(LoadFrmXML("V0121"));
		                	}
		               
		            },
		            error: function OnError_submit(xmlRequest) {
		            	  window.alert(LoadFrmXML("V0075"));

		            }
		    });
		    });
	    
	    $('#Update').click(function () {
			 
			 $(".Newdetls").removeClass("Mndtry");
			 $(".Editdetls").addClass("Mndtry");
	    	
			 var chkmndtry = ChkMandatoryFields("Mndtry");


		     if (chkmndtry == "No") {
		         return;
		     }
		        var XmlTxt = submitdata("DBfields");
		     var param='Update';
		     var SPName ='Sam_sMasAprvLmt';
		       // alert(XmlTxt);
		        $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		                   // $("#Tab3").trigger("click");
		                	
		                	
		                	
								window.alert(LoadFrmXML("V0123"));
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    $(".AddZero").val('0');
		                    document.getElementById("APLT_Flag").value='';
		                    $("#BTNMasAprvLmt").trigger("click");
		                }
		                
	               
		            },
		            error: function OnError_submit(xmlRequest) {
		            	  window.alert(LoadFrmXML("V0075"));

		            }
		    });
		    });
		 
		 $('#Delete').click(function () {
		       
			 $(".Newdetls").removeClass("Mndtry");
			 $(".Editdetls").addClass("Mndtry");
			 var chkmndtry = ChkMandatoryFields("Mndtry");


		     if (chkmndtry == "No") {
		         return;
		     }
		        var XmlTxt = submitdata("DBfields");
		     var param='Delete';
		     var SPName ='Sam_sMasAprvLmt';
		       // alert(XmlTxt);
		        $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		                   // $("#Tab3").trigger("click");
		                	
		                	
		                	//alertify.alert("Deleted Successfully");
								window.alert(LoadFrmXML("V0124"));
		                    
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    $(".AddZero").val('0');
		                    document.getElementById("APLT_Flag").value='';
		                    $("#BTNMasAprvLmt").trigger("click");
		                }
		               
		            },
		            error: function OnError_submit(xmlRequest) {
		            	  window.alert(LoadFrmXML("V0075"));

		            }
		    });
		    });

});

function mndtry(enddt,flag,spanid)
{
	
	if(flag=='Active')
		{
		spanid.show();
		enddt.addClass("Mndtry");
		
		}
	else{
		
		spanid.hide();
		enddt.removeClass("Mndtry");
	}


}



function CompareDateFields(startDate, endDate) {

    var end = endDate.id;
    var startDate = startDate.value;
    //document.getElementById("#" +startDate).value;
    var endDate = endDate.value;
    //document.getElementById("#" + endDate).value;
    var one_day = 1000 * 60 * 60 * 24;
    var x = startDate.split("-");
    var y = endDate.split("-");
    var date1 = new Date(x[2], (x[1] - 1), x[0]);

    var date2 = new Date(y[2], (y[1] - 1), y[0])
    Diff = Math.ceil((date2.getTime() - date1.getTime()) / (one_day));
    if (Diff < 0) {

    	//alertify.alert("End date is greater than Start Date");
			window.alert(LoadFrmXML("V0130"));

        return document.getElementById(end).value = "";
    }


}