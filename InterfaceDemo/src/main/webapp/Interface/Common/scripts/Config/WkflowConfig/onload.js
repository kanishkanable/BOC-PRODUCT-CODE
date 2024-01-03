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
		$('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
		  window.location.reload();
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
		   var y=  ChkUniqueParam("TableName","UniqueParam");
		    //var ChkUniqueParam=ChkUniqueParam("TableName","UniqueParam");
		    if(y=="No"){
		    	
					window.alert(LoadFrmXML("V0135"));
              	document.getElementById("UniqueParam").value="";
              	return;
		    }
		    
		  /* var x=  GridMndryChkFrWF("Table2");
		  if(x=="No")
		    	 {
					 	window.alert(LoadFrmXML("V0136"));
		   
		    	 return;
		     }*/
		 
		     var gridxml = Gridsubmitdata("Table2");
		        AssignGridXmltoField("workflow", gridxml);
		        var XmlTxt = submitdata("DBfields");
		        //   var AssertGridXML = Gridsubmitdata("Table3", "gridDBfields", "GridPartnersCount")
			      ///  AssignGridXmltoField("LSW_PropertyExstLon", AssertGridXML);
			   
		     var param='New';
		     var SPName ='Sam_sworkflowtable';
		
	   $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		     
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		                	
		                	
		                  
							window.alert(LoadFrmXML("V0108"));   
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    window.location.reload();
		                 
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
			 $(".Editdetls").addClass("Mndtry");
	    	
			 var chkmndtry = ChkMandatoryFields("Mndtry");


		     if (chkmndtry == "No") {
		         return;
		     }
	    
		   var y=  ChkUniqueParam("EditTableName","EditUniqueParameter");
		    //var ChkUniqueParam=ChkUniqueParam("TableName","UniqueParam");
		    if(y=="No"){
		    	
					window.alert(LoadFrmXML("V0135"));
           	document.getElementById("UniqueParam").value="";
           	return;
		    }
		     
		    var x=  GridMndryChkFrWF("Table2");
			  if(x=="No")
			    	 {
						 	window.alert(LoadFrmXML("V0136"));
			  
			    	 return;
			     }
		     var AssertGridXML = Gridsubmitdata("Table2");
			    AssignGridXmltoField("WFtblProp", AssertGridXML);
		        var XmlTxt = submitdata("DBfields");
		     var param='Update';
		     var SPName ='Sam_sworkflowtable';
		
		        $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		          
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {
		               	                			                			                   
								window.alert(LoadFrmXML("V0123"));
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                   window.location.reload();
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
		     
		     
//GridMndryChkFrWF("Table2");
		     
		     var AssertGridXML = Gridsubmitdata("Table2");
			    AssignGridXmltoField("WFtblProp", AssertGridXML);
		        var XmlTxt = submitdata("DBfields");
		     var param='Delete';
		     var SPName ='Sam_sworkflowtable';
		   
		        $.ajax({
		            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
		            data: { xml: XmlTxt,param:param,spname:SPName },
		            async: false,
		          
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
		                if (data == 'Success') {

		               
		                	
		                	
		                    //window.alert("Deleted Successfully");
								window.alert(LoadFrmXML("V0124"));
		                    
		                    $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
		                    window.location.reload();
		                }
		               
		            },
		            error: function OnError_submit(xmlRequest) {
		              window.alert(LoadFrmXML("V0075")); 

		            }
		    });
		    });

});



