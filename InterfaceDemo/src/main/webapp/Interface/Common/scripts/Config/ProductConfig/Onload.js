$(document).ready(function () {
var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

SessionStChk();
if (document.getElementById("SubmitSuccess").value=="InValid")
{	
window.location= IOP;	
return;
}
 /*var Resultval=FormRights();
  if(Resultval=='Yes')
 {
 return;
 }*/

readonly();

 if (document.getElementById('EntryTYPE').value == "Product") {
        $("#Product").trigger("click");
       
 $('.EditDetails').hide();
 $('.ShowNewScheme').hide();
 $('.ShowNewPurpose').hide();
 $('.NewDetails').show();
  $('.ShowNewProduct').show();
    }
   
 
 $(".NewTab").on("click", function () {
	  $('.TXTFIELDSCLEAR').find('input:text').each(

              function () {
                  $('input:text[id=' + $(this).attr('id') + ']').val('');
              }

          );
 var Entrytype= document.getElementById('EntryTYPE').value;
 document.getElementById('TabVal').value='New';

 if(Entrytype=='Product'){
  
 $('.EditDetails').hide();
 $('.ShowNewScheme').hide();
 $('.ShowNewPurpose').hide();
 $('.NewDetails').show();
  $('.ShowNewProduct').show();

 }
 if(Entrytype=='Purpose'){
  
 $('.EditDetails').hide();
 $('.ShowNewScheme').hide();
 $('.ShowNewProduct').hide();
 $('.NewDetails').show();
 $('.ShowNewPurpose').show();
 }
 if(Entrytype=='Scheme'){
 
 $('.ShowNewPurpose').hide();
 $('.EditDetails').hide();
 	 $('.ShowNewProduct').hide();
 	 $('.NewDetails').show();
  $('.ShowNewScheme').show();
 }
 });
 $(".EditTab").on("click", function () {
	  $('.TXTFIELDSCLEAR').find('input:text').each(

              function () {
                  $('input:text[id=' + $(this).attr('id') + ']').val('');
              }

          );
 var Entrytype= document.getElementById('EntryTYPE').value;
 document.getElementById('TabVal').value='Edit';
 if(Entrytype=='Product'){


 $('.NewDetails').hide();
 $('.ShowEditPurpose').hide();
 $('.ShowEditScheme').hide();
 $('.EditDetails').show(); 
 $('.ShowEditProduct').show();
 
 }
 if(Entrytype=='Purpose'){

 $('.ShowEditProduct').hide();
 $('.NewDetails').hide();
 $('.ShowEditScheme').hide();
 $('.EditDetails').show(); 
 $('.ShowEditPurpose').show();
 }
 if(Entrytype=='Scheme'){
 
 $('.ShowEditPurpose').hide();
 $('.ShowEditProduct').hide();
 $('.NewDetails').hide();
 $('.EditDetails').show();
 $('.ShowEditScheme').show();
 
 }
 });
 $("#Product").on("click", function () {
	 $('.TXTFIELDSCLEAR').find('input:text').each(

             function () {
                 $('input:text[id=' + $(this).attr('id') + ']').val('');
             }

         );
 document.getElementById('EntryTYPE').value = "Product";
 var TabVal= document.getElementById('TabVal').value;
// alertify.alert(TabVal);
 if(TabVal=='New'){
 $('.EditDetails').hide(); 
 $('.ShowNewScheme').hide();
 $('.ShowNewPurpose').hide();
 $('.NewDetails').show();
 $('.ShowNewProduct').show();
 }
 if(TabVal=='Edit'){
 $('.EditDetails').show(); 
 $('.ShowEditScheme').hide();
 $('.ShowEditPurpose').hide();
 $('.NewDetails').hide();
 $('.ShowEditProduct').show();
 }

 });
 $("#Purpose").on("click", function () {
	 $('.TXTFIELDSCLEAR').find('input:text').each(

             function () {
                 $('input:text[id=' + $(this).attr('id') + ']').val('');
             }

         );
 document.getElementById('EntryTYPE').value = "Purpose";
 var TabVal= document.getElementById('TabVal').value;
// alertify.alert(TabVal);
 if(TabVal=='New'){
 $('.EditDetails').hide();
 $('.ShowNewScheme').hide();
 $('.ShowNewProduct').hide();
 $('.NewDetails').show();
 $('.ShowNewPurpose').show();
 }
 if(TabVal=='Edit'){
 $('.EditDetails').show();
 $('.ShowEditScheme').hide();
 $('.ShowEditProduct').hide();
 $('.NewDetails').hide();
 $('.ShowEditPurpose').show();
 }
 
 });
 $("#Scheme").on("click", function () {
	 $('.TXTFIELDSCLEAR').find('input:text').each(

             function () {
                 $('input:text[id=' + $(this).attr('id') + ']').val('');
             }

         );
 document.getElementById('EntryTYPE').value = "Scheme";
 var TabVal= document.getElementById('TabVal').value;
// alertify.alert(TabVal);
 if(TabVal=='New'){
 $('.EditDetails').hide();
 $('.NewDetails').show();
 $('.ShowNewPurpose').hide();
 $('.ShowNewProduct').hide();
  $('.ShowNewScheme').show();
 }
 if(TabVal=='Edit'){
 $('.EditDetails').show();
 $('.NewDetails').hide();
 $('.ShowEditPurpose').hide();
 $('.ShowEditProduct').hide();
  $('.ShowEditScheme').show();
 }

 });

$("#prodsave").on("click", function () {
	var code = document.getElementById("PROD_Code").value;
	var name = document.getElementById("PROD_Name").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Product';
   	var param2='New';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0108"));
               
            document.getElementById("PROD_Code").value='';
          document.getElementById("PROD_Name").value='';
         
                
               }
               else if($(data).text()=='Exists')
               	{
               	//alertify.alert("Values are already Exists");
window.alert(LoadFrmXML("V0121"));
document.getElementById("PROD_Code").value='';
document.getElementById("PROD_Name").value='';
    
               	}
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

});
$("#purpsave").on("click", function () {

	var code = document.getElementById("PURP_Code").value;
	var name = document.getElementById("PURP_Name").value;
	var OprnId = document.getElementById("PURP_AF_OPRN_ID").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="" || OprnId=="" )
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }

var param1='Purpose';
   	var param2='New';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0108"));
               
               document.getElementById("PURP_Code").value='';
          document.getElementById("PURP_Name").value='';
          document.getElementById("PURP_AF_OPRN_ID").value='';       
                
               }
               else if($(data).text()=='Exists')
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
$("#schmsave").on("click", function () {
	
	var code = document.getElementById("SCHM_Code").value;
	var name = document.getElementById("SCHM_Name").value;
	//var purpcode = document.getElementById("SCHM_PurpCode").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Scheme';
   	var param2='New';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0108"));
               
              document.getElementById("SCHM_Code").value='';
            	 document.getElementById("SCHM_Name").value='';
            	 document.getElementById("SCHM_PurpCode").value='';
                 
                
               }
               else if($(data).text()=='Exists')
               	{
               	//alertify.alert("Values are already Exists");
window.alert(LoadFrmXML("V0121"));
document.getElementById("SCHM_Code").value='';
	 document.getElementById("SCHM_Name").value='';
	 document.getElementById("SCHM_PurpCode").value='';
   
               	}
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

});

$("#ProdUpdateBtn").on("click", function () {
	var code = document.getElementById("EditPROD_Code").value;
	var name = document.getElementById("EditPROD_Name").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	
	var param1='Product';
   	var param2='Update';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0123"));
               
               	 document.getElementById("EditPROD_Code").value='';
                 document.getElementById("EditPROD_Name").value='';
                 document.getElementById("PKID").value='';
                     
                 
                
               }
               else if($(data).text()=='Exists')
               	{
               	//alertify.alert("Values are already Exists");
window.alert(LoadFrmXML("V0121"));
document.getElementById("EditPROD_Code").value='';
document.getElementById("EditPROD_Name").value='';
    
               	}
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

});

$("#ProdDeleteBtn").on("click", function () {
	var code = document.getElementById("EditPROD_Code").value;
	var name = document.getElementById("EditPROD_Name").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Product';
   	var param2='Delete';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0124"));
               
               	 document.getElementById("EditPROD_Code").value='';
                 document.getElementById("EditPROD_Name").value='';
                 document.getElementById("PKID").value=''; 
                 
                
               }
               
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

});

$("#PurpUpdateBtn").on("click", function () {
	
	var code = document.getElementById("EditPURP_Code").value;
	var name = document.getElementById("EditPURP_Name").value;
	var OprnId = document.getElementById("EditPURP_AF_OPRN_ID").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="" || OprnId=="" )
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	var param1='Purpose';
   	var param2='Update';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0123"));
               
               	 document.getElementById("EditPURP_Code").value='';
                 document.getElementById("EditPURP_Name").value='';
                 document.getElementById("EditPURP_AF_OPRN_ID").value='';     
                 document.getElementById("PKID").value='';
                
               }
               else if($(data).text()=='Exists')
               	{
               	//alertify.alert("Values are already Exists");
window.alert(LoadFrmXML("V0121"));
document.getElementById("EditPURP_Code").value='';
document.getElementById("EditPURP_Name").value='';
document.getElementById("EditPURP_AF_OPRN_ID").value='';
       
               	}
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

}); 
$("#PurpDeleteBtn").on("click", function () {
	
	var code = document.getElementById("EditPURP_Code").value;
	var name = document.getElementById("EditPURP_Name").value;
    var OprnId = document.getElementById("EditPURP_AF_OPRN_ID").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
    if(code == "" || name=="" || OprnId=="" )
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Purpose';
   	var param2='Delete';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0124"));
               
               	 document.getElementById("EditPURP_Code").value='';
                 document.getElementById("EditPURP_Name").value='';
                 document.getElementById("EditPURP_AF_OPRN_ID").value='';
                 document.getElementById("PKID").value='';      
                 
                
               }
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

}); 
$("#SchmUpdateBtn").on("click", function () {
	
	var code = document.getElementById("EditSCHM_Code").value;
	var name = document.getElementById("EditSCHM_Name").value;
	//var purpcode = document.getElementById("EditSCHM_PurpCode").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Scheme';
   	var param2='Update';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0123"));
               
               	document.getElementById("EditSCHM_Code").value='';
              	 document.getElementById("EditSCHM_Name").value='';
              	 document.getElementById("EditSCHM_PurpCode").value='';
              	 document.getElementById("PKID").value=''; 
                 
                
               }
               else if($(data).text()=='Exists')
               	{
               	//alertify.alert("Values are already Exists");
window.alert(LoadFrmXML("V0121"));
document.getElementById("EditSCHM_Code").value='';
	 document.getElementById("EditSCHM_Name").value='';
	 document.getElementById("EditSCHM_PurpCode").value='';
   
               	}
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

}); 
$("#SchmDeleteBtn").on("click", function () {
	var code = document.getElementById("EditSCHM_Code").value;
	var name = document.getElementById("EditSCHM_Name").value;
	//var purpcode = document.getElementById("EditSCHM_PurpCode").value;
    //  	var dropdn = document.getElementById("Mndtry").value;
       if(code == "" || name=="")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0150"));
	    	return
       }
	
	var param1='Scheme';
   	var param2='Delete';
   	var param3=submitdata("ISDBfields");
   	var param4="";
   	var param5="";
   	var spname='LSW_sInsProdPurpScheme';
     
       $.ajax({
    	   url: "ThemeproLO/UI_GetData",
           data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
           async: false,
          // dataType: "json",
           type: 'POST',
           success: function OnSuccess_submit(data) {
               if ($(data).text()== 'Success') {

                  // $("#Tab3").trigger("click");
               	
               	  window.alert(LoadFrmXML("V0124"));
               
               	document.getElementById("EditSCHM_Code").value='';
           	 document.getElementById("EditSCHM_Name").value='';
           	 document.getElementById("EditSCHM_PurpCode").value='';
           	 document.getElementById("PKID").value='';
               
                 
                
               }
               
              
           },
           error: function OnError_submit(xmlRequest) {
           	  window.alert(LoadFrmXML("V0075"));

           }
   });

}); 

});