$(document).ready(function(){
	
	var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

		/*SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= IOP;	
			return;
			}
			
					 var Resultval=FormRights();
	  if(Resultval!='Yes')
	 {
	 return;
	 }*/
	 $(".DateFields").cdatepicker({
	        showOn: "button",
	        buttonImage: "ThemeproLO/Common/Images/calendar.png",
	        buttonImageOnly: true,
	        changeMonth: true,
	        changeYear: true,
	        dateFormat: "dd-mm-yy",
	        yearRange: "c-10:c+10"
	    });
	 $("#enddtspan").hide();
	 $("#Editenddtspan").hide();
	 var loggedinuser = GetCurrentUserFName();
	 document.getElementById('LRCN_DtModified').value = get2dateTime();

     if (document.getElementById('LRCN_DtCreated').value == "" || document.getElementById('LRCN_DtCreated').value == "null") {
         document.getElementById('LRCN_DtCreated').value = get2dateTime();
     }

     if (document.getElementById('LRCN_CreatedBy').value == "" || document.getElementById('LRCN_CreatedBy').value == "null") {
         document.getElementById('LRCN_CreatedBy').value = loggedinuser;
     }
     document.getElementById('LRCN_ModifiedBy').value = loggedinuser;
	
	$(".NewTab").on("click", function () {

        $(".NewDetails").show();
        $(".EditDetails").hide();
        $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }
                
               
            );
        $(".Newval").val('0');
        $("#LRCN_StartDt").val('');
        $("#LRCN_EndDt").val('');
        $("#LRCN_Flag").val('');
        $("#enddtspan").hide();
	});
	
	$(".EditTab").on("click", function () {

        $(".NewDetails").hide();
        $(".EditDetails").show();
        $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }
                
               
            );
        $(".Editval").val('0');
        $("#EditLRCN_StartDt").val('');
        $("#EditLRCN_EndDt").val('');
        $("#EditLRCN_Flag").val('');
        $("#Editenddtspan").hide();
      $("#BTNRoi").trigger("click");
	});
	/*
	
	 $(".DateFields").cdatepicker({
	        showOn: "button",
	        buttonImage: "ThemeproLO/Common/Images/calendar.png",
	        buttonImageOnly: true,
	        changeMonth: true,
	        changeYear: true,
	        dateFormat: "dd-mm-yy",
	        yearRange: "c-10:c+10"
	    });
	       
*/
	  // populatedropdowns();
	    //FormDataFromDB("LSW_tMasAprvLmt", "LRCN_", "LRCN", "");
	   
	
	 $('#save').click(function () {
		 $(".Newdetls").addClass("Mndtry");
		 $(".Newdetls").addClass("DBfields");

		 $(".Editdetls").removeClass("Mndtry");
		 $(".Editval").removeClass("Mndtry");
		 $(".Editval").removeClass("DBfields");
		 var chkmndtry = ChkMandatoryFields("Mndtry");


	     if (chkmndtry == "No") {
	       return;
	     }
	        var XmlTxt = submitdata("DBfields");
	     var param='New';
	      //  alert(XmlTxt);
	     var spname='LSW_sMasLonRoiConfig'
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_ConfigScreens",
	            data: { xml: XmlTxt,param:param,spname:spname},
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
	                    $(".Newval").val('0');
	                    $("#LRCN_StartDt").val('');
	                    $("#LRCN_EndDt").val('');
	                    $("#LRCN_Flag").val('');
	                    $("#enddtspan").hide();
	                 
	                }
	                else if(data=='Exists')
	                	{
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
		 $("#LRCN_PurpCode").removeClass("Mndtry");
		 $(".Newdetls").removeClass("DBfields");
		 $(".Editval").addClass("DBfields");
		 $(".Newval").removeClass("Mndtry");
		 $(".Editdetls").addClass("Mndtry");
		 var chkmndtry = ChkMandatoryFields("Mndtry");


	     if (chkmndtry == "No") {
	       return;
	     }
	        var XmlTxt = submitdata("DBfields");
	     var param='Update';
	      //  alert(XmlTxt);
	     var spname='LSW_sMasLonRoiConfig'
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_ConfigScreens",
	            data: { xml: XmlTxt,param:param,spname:spname},
	            async: false,
	           // dataType: "json",
	            type: 'POST',
	            success: function OnSuccess_submit(data) {
	                if (data == 'Success') {

	                   // $("#Tab3").trigger("click");
	                	
	                	
	                	//alertify.alert("Updated Successfully");
							window.alert(LoadFrmXML("V0123"));
	                  /*  $('.TXTFIELDSCLEAR').find('input:text').each(

	                            function () {
	                                $('input:text[id=' + $(this).attr('id') + ']').val('');
	                            }
	                            
	                           
	                        );*/
	                    $('.Editval').val('0');
						 $('.ISDatefield').val('');
						 
	                    
	                    $("#BTNRoi").trigger("click");
	                }
	               else{
				   	window.alert(data);
				   }
	               
	            },
	            error: function OnError_submit(xmlRequest) {
	            	  window.alert(LoadFrmXML("V0075"));

	            }
	    });
	    });
	 $('#Delete').click(function () {
		 $(".Newdetls").removeClass("Mndtry");
		 $(".Newval").removeClass("Mndtry");
		 $(".Editdetls").addClass("Mndtry");
		  var GridClkChk=$('#GridClkChk').val();
	     if (GridClkChk == "") {
			 	//window.alert(LoadFrmXML("V0124"));
				window.alert(LoadFrmXML("V0171"));
	       return;
	     } 
		 var chkmndtry = ChkMandatoryFields("Mndtry");
		 

	     if (chkmndtry == "No") {
	       return;
	     } 
	    
	        var XmlTxt = submitdata("DBfields");
	     var param='Delete';
	      //  alert(XmlTxt);
	     var spname='LSW_sMasLonRoiConfig'
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_ConfigScreens",
	            data: { xml: XmlTxt,param:param,spname:spname},
	            async: false,
	           // dataType: "json",
	            type: 'POST',
	            success: function OnSuccess_submit(data) {
	                if (data == 'Success') {

	                   // $("#Tab3").trigger("click");
	                	
	                	
	                	
							window.alert(LoadFrmXML("V0124"));
	                    $('.TXTFIELDSCLEAR').find('input:text').each(

	                            function () {
	                                $('input:text[id=' + $(this).attr('id') + ']').val('');
	                            }
	                            
	                           
	                        );   $('.Editval').val('0');
	                        $("#EditLRCN_StartDt").val('');
	                        $("#EditLRCN_EndDt").val('');
	                        $("#EditLRCN_Flag").val('');
	                        $("#Editenddtspan").hide();
		                    $("#BTNRoi").trigger("click");
	                   
	                }
					else{
				   	window.alert(data);
				   }
	              
	            },
	            error: function OnError_submit(xmlRequest) {
	            	  window.alert(LoadFrmXML("V0075"));

	            }
	    });
	    });
});
function MaxAmtChk(MaxVal,MinVal,clrval)
{
var min=parseFloat(document.getElementById(MinVal).value.replace(/,/g, ""));
var max=parseFloat(document.getElementById(MaxVal).value.replace(/,/g, ""));

if(max!=0)
{
if(min>max){
		window.alert(LoadFrmXML("V0132"));
	//alertify.alert("Minimum loan amount should less than the Maximum Loan amount")
	
document.getElementById(clrval).value=0;

}
}
}
function MaxTermChk(MaxVal,MinVal,clrval)
{
var min=parseFloat(document.getElementById(MinVal).value.replace(/,/g, ""));
var max=parseFloat(document.getElementById(MaxVal).value.replace(/,/g, ""));

if(max!=0)
{
if(min>max){
		window.alert(LoadFrmXML("V0152"));
	//alertify.alert("Minimum loan amount should less than the Maximum Loan amount")
	
document.getElementById(clrval).value=0;

}
}
}

function AddFlagBasedMntry(enddt,flag,spanid){
	var flag= document.getElementById(flag).value;
	if(flag=='InActive')
	{
	$("#"+spanid).show();
	$("#"+enddt).addClass("Mndtry");

	}
	else{

	$("#"+spanid).hide();
	$("#"+enddt).removeClass("Mndtry");
	}


}

function CompareDateFields(startDt, endDt) {

   // var endDate = endDate.id;
    //var startDate = startDate.value;
    var startDate =$("#"+startDt).val();
   // var endDate = endDate.value;
    var endDate=$("#"+endDt).val()
    var one_day = 1000 * 60 * 60 * 24;
    var x = startDate.split("-");
    var y = endDate.split("-");
    var date1 = new Date(x[2], (x[1] - 1), x[0]);

    var date2 = new Date(y[2], (y[1] - 1), y[0])
    Diff = Math.ceil((date2.getTime() - date1.getTime()) / (one_day));
    if (Diff < 0) {
    	$("#"+endDt).val('');
    	//alertify.alert("End date is greater than Start Date");
			window.alert(LoadFrmXML("V0130"));
			
        return //document.getElementById(endDate).value = "";
    }


}
function TxtClr(){
    $(".Newval").val('0');
	                    $("#LRCN_StartDt").val('');
	                    $("#LRCN_EndDt").val('');
	                    $("#LRCN_Flag").val('');
	                    $("#enddtspan").hide();
						 $(".Editval").val('0');
        $("#EditLRCN_StartDt").val('');
        $("#EditLRCN_EndDt").val('');
        $("#EditLRCN_Flag").val('');
        $("#Editenddtspan").hide();
}