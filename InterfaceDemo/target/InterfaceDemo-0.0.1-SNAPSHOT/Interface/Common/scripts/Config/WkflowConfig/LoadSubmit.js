/**
 * 
 */
$(document).ready(function(){
	
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


$(".NewTab").on("click", function () {
	 window.location.reload();
$('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
        $(".NewDetails").show();
        $(".EditDetails").hide();
});

$(".EditTab").on("click", function () {
$('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
        $(".NewDetails").hide();
        $(".EditDetails").show();
        
       // $("#BTNMasAprvLmt").trigger("click");
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
       
*//*
    $("#BTNMasAprvLmt").trigger("click");
    
    $(document).on('click', '#Table2 tbody tr', function () {

    	var usrpk_id = $(this).closest('tr').find('td:eq(0)').text(); 
    	var userid = $(this).closest('tr').find('td:eq(1)').text(); 
    	var designation = $(this).closest('tr').find('td:eq(2)').text(); 
    	var departmentid = $(this).closest('tr').find('td:eq(3)').text();
    	
    	//usermap_pkid
    	
    	document.getElementById("usermap_pkid").value= usrpk_id;
    	document.getElementById("EditUserid").value = userid;
    	document.getElementById("EditDesgn").value = designation;
    	document.getElementById("EditDeptid").value = departmentid;   

    });
    
    */
    
    
    
    $('#save').click(function () {
    	
    	 $(".Newdetls").addClass("Mndtry");
 $(".Editdetls").removeClass("Mndtry");
 
 var chkmndtry = ChkMandatoryFields("Mndtry");


     if (chkmndtry == "No") {
         return;
     }
     var x= GridMndryChkFrWF("Table2");
    if(x=="No")
    	 {
    	window.alert('Please Fill all  alies names')
    	 return;
    	 }
     var gridxml = Gridsubmitdata("Table2");
        AssignGridXmltoField("workflow", gridxml);
        var XmlTxt = submitdata("DBfields");
        //   var AssertGridXML = Gridsubmitdata("Table3", "gridDBfields", "GridPartnersCount")
      ///  AssignGridXmltoField("LSW_PropertyExstLon", AssertGridXML);
   
     var param='New';
     var SPName ='sam_workflowtable';

    $.ajax({
            url: "ThemeproLO/UI_formdatains_ConfigScreens",
            data: { xml: XmlTxt,param:param,spname:SPName },
            async: false,
     
            type: 'POST',
            success: function OnSuccess_submit(data) {
                if (data == 'Success') {

                	
                	
                	window.alert("Data Saved");
                    $('.TXTFIELDSCLEAR').find('input:text').each(

        	                function () {
        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
        	                }

        	            );
                    window.location.reload();
                 
                }
                else if(data=='Exists')
                	{
                	window.alert("Values are already Exists");
                	}
               
            },
            error: function OnError_submit(xmlRequest) {
            	window.alert("Submission Failed");

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
		     
		     
		     
		     var AssertGridXML = Gridsubmitdata("Table2");
			    AssignGridXmltoField("WFtblProp", AssertGridXML);
		        var XmlTxt = submitdata("DBfields");
		     var param='Update';
		     var SPName ='Sam_sworkflowtable';
		
		        $.ajax({
		            url: "ThemeproLO/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		          
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		               
		                	
		                	
		                	window.alert("Updated Successfully");
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                   window.location.reload();
		                }
		                
	               
		            },
		            error: function OnError_submit(xmlRequest) {
		            	window.alert("Submission Failed");

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
		     
		     
 //GridMndryChkFrWF("Table2");
		     
		     var AssertGridXML = Gridsubmitdata("Table2");
			    AssignGridXmltoField("WFtblProp", AssertGridXML);
		        var XmlTxt = submitdata("DBfields");
		     var param='Delete';
		     var SPName ='Sam_sworkflowtable';
		   
		        $.ajax({
		            url: "ThemeproLO/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		          
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		               
		                	
		                	
		                	window.alert("Deleted Successfully");
		                    
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    window.location.reload();
		                }
		               
		            },
		            error: function OnError_submit(xmlRequest) {
		            	window.alert("Submission Failed");

		            }
		    });
		    });

});


