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
	 }**/

   // readonly();

    $(".NewTab").on("click", function () {

    	
    	Newclearscreen();
        $(".ShowNewMenu").show();
        $(".ShowEdit").hide();
      

    });
    
   // editclearscreen();
    $("#BTN_Usermap").trigger("click");
	
   
    
   


   /* $('#Save').click(function () {
		
		$(".Newdetls").addClass("Mndtry");
		 $(".Editdetls").removeClass("Mndtry");
        
            var chkmndtry = ChkMandatoryFields("Mndtry");


            if (chkmndtry == "No") {
                return;
           }

            var FORMXML = submitdata("ISDBfields");           
            var param = "New";
            var spname = "SAM_sUsrWsDeptRewv";


            $.ajax({
            	//UI_formdatains_NewMenuEntry
            	
                //url: "ThemeproLO/UserdeptMapping",
                url: "ThemeproLO/UI_formdatains_ConfigScreens",
                data: { xml: FORMXML, param:param, spname: spname },
                async:false,
                type:"Post",
                success : function OnSuccess_submit(Result) {
                	
                	//alert(Result);
                    if (Result == 'Success') {
                       
                        alert(LoadFrmXML("V0106"));
                        Newclearscreen();
                    
                    }
                    else {
                        // DateChangeBack("ISDatefield"); 
                        alert(LoadFrmXML("V0074"));           
                    }
                },
                error: function OnError_submit(xmlRequest) {
                    //DateChangeBack("ISDatefield");
                  alert(LoadFrmXML("V0075"));

                }
            });                         
    
    });
    
    
    
    
    */
    
    $('#Update').click(function () {
		
		 $(".Newdetls").removeClass("Mndtry");
		 $(".Editdetls").addClass("Mndtry");

    	var chkmndtry = ChkMandatoryFields("Mndtry");


        if (chkmndtry == "No") {
             return;
        }

         var FORMXML = submitdata("ISDBfields");      
         var param = "Update";
         var spname = "SAM_sUsrWsDeptRewv";


         $.ajax({
         	//UI_formdatains_NewMenuEntry
         	
        	 url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
             data: { xml: FORMXML, param:param, spname: spname },
             async:false,
             type:"Post",
             success : function OnSuccess_submit(Result) {
             	
             	//alert(Result);
                 if (Result == 'Success') {
                    
                   
                	 alert(LoadFrmXML("V0123"));
                     
                     editclearscreen();
                     $("#BTN_Usermap").trigger("click");
                 
                 }
                 else {
                     // DateChangeBack("ISDatefield");
						//alertify.alert("DepartmentId already Exsist ");     
                	 alert(LoadFrmXML("V0133"));					 
                 }
             },
             error: function OnError_submit(xmlRequest) {
                 //DateChangeBack("ISDatefield");
            	 alert(LoadFrmXML("V0075"));

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

         var FORMXML = submitdata("ISDBfields");         
         var param = "Delete";
         var spname = "SAM_sUsrWsDeptRewv";


         $.ajax({
         	//UI_formdatains_NewMenuEntry
         	
        	 url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
             data: { xml: FORMXML, param:param, spname: spname },
             async:false,
             type:"Post",
             success : function OnSuccess_submit(Result) {
             	
             	//alert(Result);
                 if (Result == 'Success') {
                    
                    
                	 alert(LoadFrmXML("V0124"));
                     
                     editclearscreen();
                     $("#BTN_Usermap").trigger("click");
                 
                 }
                 else {
                     // DateChangeBack("ISDatefield");
                	 alert(LoadFrmXML("V0134"));
                             
                 }
             },
             error: function OnError_submit(xmlRequest) {
                 //DateChangeBack("ISDatefield");
                  alert(LoadFrmXML("V0075"));

             }
         });
         
    });         
});

/*function Assigndeptid(){
	
	
	var s = document.getElementById("Userid").value; 
	document.getElementById("hdnuserid").value = s;
	
}
function EditAssigndeptid(){
	
	
	var s = document.getElementById("EditUserid").value; 
	
	document.getElementById("hdnuserid").value = "";
	
	document.getElementById("hdnuserid").value = s;
	
	//$("#BTN_Usermap").trigger("click");
	
}*/

function Newclearscreen()
{
	document.getElementById("Userid").value = "";
	document.getElementById("Desgn").value = "";
	document.getElementById("Deptid").value = "";
}
function editclearscreen()
{
	document.getElementById("EditPKID").value = "";
	 document.getElementById("EditUserid").value = "";
	 document.getElementById("EditDesgn").value = "";
	 document.getElementById("EditDeptid").value = "";
	}
 
 function ChkOption()
 {
	 if($("#Tab1").hasClass('active'))
	 {
		 return 'New';
	 }
	 else
	 {
		 return 'Edit';
	 }
	 
 }

 
 $(document).on('click', '#Save', function(){
     // $(document).on('click', '#Table1 tbody tr', function () {
		   
		   var data1="";
		   if($("input:checked" ).length == 0)
	{
	//alert("Please Select a File");
	alert(LoadFrmXML("V0110"));
	$("input:checked" ).prop( "checked", false );
	return
  }
	
	

	
	

	for(i=0; i<$("input:checked" ).length;i++)
	{
		
	for(var j=1;j<$("#Table1").find($('input:checkbox[name=selectchk]:checked')[i]).closest('tr').find('td').length;j++)
  {
		data1 += $($("#Table1").find($('input:checkbox[name=selectchk]:checked')[i]).closest('tr').find('td'))[j].innerHTML+",";
	}
		
		data1 +="|";
	
	}
	
	ChkValues(data1);
	
	
  
	
	
              $("#popup").dialog("close");

          });

 
 function ChkValues(data1)
 {

    
         var chkmndtry = ChkMandatoryFields("DBMndtry");


         if (chkmndtry == "No") {
             return;
         }

         //Check Grid MAndatory Fields
         // END Check Grid MAndatory Fields

//         if (document.getElementById("AssignRights").value == "Everyone" && document.getElementById("AssignForAllSubLevels").value != "Yes") {

//             alert('Choose "Yes", In Assign For All SubLevels');
//             return;

//         }



       
 		
 		var id="";

 for(var i=0;i<data1.split('|').length;i++)
 {
 	
 	if(data1.split('|')[i].split(',')[0]==undefined)
 	{
 		
 		var error='';
 		
 	}
 	else{
 		 id += data1.split('|')[i].split(',')[0]+"|";
 	}
 	
 	
 }


         // DateChange("ISDatefield");

 		

         //    var AddrXML = Gridsubmitdata_m("TableTimest", "ISGridDBfields", "9")
         //    AssignGridXmltoField("SAL_Detls", AddrXML);
 		$("#SetRightsID").val(id.slice(0,-1));
 //$("#SetRightsID").val(data1);
 
        var param = 'New';
         var setrightsid=document.getElementById('SetRightsID').value;
         var FORMXML = submitdata("ISDBfields");
        // var levelcount = document.getElementById('Menulevel').value;
       //  alert(FORMXML);
 var spname='SAM_sUsrWsDeptRewv';
 		


 $.ajax({

             url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
             data: { xml: FORMXML, param: param ,spname:spname},
             async: false,
          
             type: 'POST',
             success: function OnSuccess_submit(data) {
 				
 				
            	 alert(LoadFrmXML("V0106"));
 				
 				//  editclearscreen();
                  $("#BTN_Usermap").trigger("click");
 				

             },
             error: function OnError_submit(xmlRequest) {
            	 alert(LoadFrmXML("V0075"));
             }

         });
         
         

 		
 }
 
 
 
 
 $(document).on('click', '#RightsDelete', function () {
		 
	var param='Delete';
	var UsrId=$(this).closest('tr').find('td')[1].innerHTML;
	var desgn=$(this).closest('tr').find('td')[2].innerHTML;
	var dept=$(this).closest('tr').find('td')[3].innerHTML;
	 $("#SetRightsID").val(UsrId+'|'+desgn+'|'+dept);
	 var FORMXML = submitdata("ISDBfields");
	var spname='SAM_sUsrWsDeptRewv';


	alertify.confirm("Are you sure?", function (e) {
	        if (e) 
			{	
			    
	        	 $.ajax({

	                 url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
	                 data: { xml: FORMXML, param: param ,spname:spname},
	                 async: false,
	              
	                 type: 'POST',
	                 success: function OnSuccess_submit(data) {
	     				
	     				
	     				alert(LoadFrmXML("V0124"));
	     				
	     				//  editclearscreen();
	                      $("#BTN_Usermap").trigger("click");
	     				

	                 },
	                 error: function OnError_submit(xmlRequest) {
	                	 alert(LoadFrmXML("V0075"));
	                 }

	             });
			      } 
			else 
			{
	            return;
	        }
	    });

	 });
	 
	 