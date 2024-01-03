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
	
	 $(".NewTab").on("click", function () {

	       
	      //  $("#Rearrange").hide();
	        $(".ShowNewQueue").show();
	        $(".EditDetails").hide();
	      
	        $('.TXTFIELDSCLEAR').find('input:text').each(

	                function () {
	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
	                }

	            );
	       
	       

	    });
	 
	 $('#Genlkup').click(function () {
		 $('#PKID').val();
	 })
	 
	 $('#save').click(function () {
		 $(".Newdetls").addClass("Mndtry");
		 $(".Editdetls").removeClass("Mndtry");
		 
		 var chkmndtry = ChkMandatoryFields("Mndtry");


	     if (chkmndtry == "No") {
	         return;
	     }
	        var XmlTxt = submitdata("DBfields");
	     var param='New';
	       // alert(XmlTxt);
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_General",
	            data: { xml: XmlTxt,Param:param },
	            async: false,
	           // dataType: "json",
	            type: 'POST',
	            success: function OnSuccess_submit(data) {
	                if (data == 'Success') {

	                   // $("#Tab3").trigger("click");
	                	
	                	
							
							window.alert(LoadFrmXML("V0108"));
	                    document.getElementById('code').value = '';
	                    document.getElementById('shrtdescr').value = '';
	                    document.getElementById('descr').value = '';
						document.getElementById('ExtraSrc').value = '';
	                 
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
	        var XmlTxt = submitdata("DBfields");
	     var param='Update';
	       // alert(XmlTxt);
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_General",
	            data: { xml: XmlTxt,Param:param },
	            async: false,
	           // dataType: "json",
	            type: 'POST',
	            success: function OnSuccess_submit(data) {
	                if (data == 'Success') {

	                   // $("#Tab3").trigger("click");
	                	
	                	
	                  
							window.alert(LoadFrmXML("V0123"));
	                    document.getElementById('Editcode').value = '';
	                    document.getElementById('Editshrtdescr').value = '';
	                    document.getElementById('Editdescr').value = '';
						document.getElementById('EditExtraSrc').value = '';
	                 
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
	 
	 $('#Delete').click(function () {
	       
		 $(".Newdetls").removeClass("Mndtry");
		 $(".Editdetls").addClass("Mndtry");
		 
		 var chkmndtry = ChkMandatoryFields("Mndtry");


	     if (chkmndtry == "No") {
	         return;
	     }
	        var XmlTxt = submitdata("DBfields");
	     var param='Delete';
	       // alert(XmlTxt);
	        $.ajax({
	            url: "ThemeproLO/UI_formdatains_General",
	            data: { xml: XmlTxt,Param:param },
	            async: false,
	           // dataType: "json",
	            type: 'POST',
	            success: function OnSuccess_submit(data) {
	                if (data == 'Success') {

	                   // $("#Tab3").trigger("click");
	                	
	                	
	                    
							window.alert(LoadFrmXML("V0124"));
	                    document.getElementById('Editcode').value = '';
	                    document.getElementById('Editshrtdescr').value = '';
	                    document.getElementById('Editdescr').value = '';
	                 document.getElementById('EditExtraSrc').value = '';
	                }
	               
	            },
	            error: function OnError_submit(xmlRequest) {
						window.alert(LoadFrmXML("V0075"));
	              

	            }
	    });
	    });
});

