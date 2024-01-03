if (!window.location.origin) {
  window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
}

function SessionStChk()
{
	 var CHK="";
	 var IOP=window.location.origin+"/jw"
	var callback = {
		    success : function(response){
		        // response.username
		        if(response.username != "roleAnonymous"){
		            console.log("Username is " + response.username);
					 // document.getElementById("TXTUsername").value=response.username;
		          // alert("Username is " + response.username);
		           // CHK="Valid";
			
				    
		        }else{
					
					
		            console.log("User is anonymous");
		            document.getElementById("SubmitSuccess").value="InValid";
		          // alertify.alert(LoadFrmXML("V0061"));
					window.alert(LoadFrmXML("V0061"));
					window.close();
		         }
				 
		    }
		};
		AssignmentManager.getCurrentUsername(IOP, callback);	
		
}






     function get2dateTime() {
            var today ="";
            $.ajax({
                url: "/SBIReimbursement/UI_fetchformdata_get2dateTime",
            data: {  },
            async: false,
           // dataType: "json",
            type: 'POST',
            success: function (xml1) {
               
		var Date = xml1.substring(0,10)

                today= Date.split("-")[1] + "-" + Date.split("-")[2] + "-" + Date.split("-")[0];

            },

            error: function (xml1) {

              

            }

            });
             
            return today;

         

        }
	
function Get2DateBusn(){

var output=UI_IntrData('','','','','','SAM_sGet2DateBusn','INTR1');
var BusnDate= $(output).find("Result").text();

return BusnDate;
}

	
function FormRights()
{
	
	
		if(window.frameElement === null)
	{
		  $('body').empty();
		$("<div><img src=\"ThemeproLO/Common/Images/AD.png\"></div>").appendTo('body');
		return;
	}
		
		var formid=$('form').attr('id');
	if(formid != "AllForms")
	{
// Form Rights Check Start

	var html = "";

	var op= UI_getdata(GetCurrentUser(),formid,'','','','SAM_SGetFormRights');
		
	html = $(op).find("a").html();
	   
	   if(html != 'Yes')
	   {
		   $('body').empty();
		  // $('body').append(html);
		   $(html).appendTo('body');
		   return 'No'
	   }
	   else{
		   
		   return 'Yes'
	   }
	} 
// Form Rights Check End
	
	
}

function GetCurrentUser()
{
	 var CHK="";
	 var CUsername="";
	 var params="";
	// var IOP=window.location.origin+"/jw"

	  var url = LoadFrmXML("PG006");
	 
	 $.ajax({
	        url:         url,
		    async: false,
	        dataType:    'jsonp',
			data:        params,
	        processData: false,
	        success:     function(data) {
                    CUsername = data.username;
                }
	    });
	 
		return CUsername;
}


function GetCurrentUserFName()
{
	 var CHK="";
	 var CUsername="";
	 var params="";
	 var op ="";
	// var IOP=window.location.origin+"/jw"

	  var url = LoadFrmXML("PG006");
	 
	 $.ajax({
	        url:         url,
		    async: false,
	        dataType:    'jsonp',
			data:        params,
	        processData: false,
	        success:     function(data) {
                    CUsername = data.username;
					op = UI_getdata(CUsername,"","","","","sam_sGetUserName");
                }
	    });
	 
		return $(op).find("FirstName").text();
}


function SessionLoginChk()
{
	 var CHK="";
	 var IOP=window.location.origin+"/jw"
	var callback = {
		    success : function(response){
		        // response.username
		        if(response.username != "roleAnonymous"){
		            console.log("Username is " + response.username);
					document.getElementById("SubmitSuccess").value="Valid";
		          // alert("Username is " + response.username);
		           // CHK="Valid";
		        }else{
		            console.log("User is anonymous");
		            document.getElementById("SubmitSuccess").value="InValid";
					// window.close();
		           // alertify.alert(LoadFrmXML("V0061"));
		         }
		    }
		};
		AssignmentManager.getCurrentUsername(IOP, callback);	
		
}

function SessionLogin()
{
	
	var IOP=window.location.origin+LoadFrmXML("PG011");
	var IOP1=window.location.origin;
	// var MainPGURL=LoadFrmXML("PG001");
	var SChk="";
	
	 $.ajax({
	        url:         IOP,
		    async: false,
	        dataType:    'jsonp',
			data:        '',
	        processData: false,
	        success:     function(data) {
                    SChk = "Success"
                }
	    });
// window.alert(SChk);
	
	
	// AssignmentManager.logout(IOP);
	
	 var CHK="";
	 var IOP=window.location.origin+"/jw"
	var MainPGURL=window.location.origin+LoadFrmXML("PG004");
	 
	var username=$("#TXTusername").val();
	var pwd=$("#TXTpwd").val();
	
	$.ajax({
            type: "POST",
            url: MainPGURL,
			async:false,
            data: {
                username: username,
                password: pwd
            },
			dataType: "json",
            success: function(res) {
                // console.log("username (" + res.username + ") is " +
				// ((res.isAdmin !== undefined && res.isAdmin ===
				// "true")?"admin":"not an admin"));
            document.getElementById("SubmitSuccess").value="Valid";
			 
			},            
			error:function(response){
				document.getElementById("SubmitSuccess").value="";
			// alertify.alert("Incorrect username or password");
			// alertify.alert(LoadFrmXML("V0125"));
			window.alert(LoadFrmXML("V0125"));
	}
        });
	
	/*
	 * var callback = {
	 * 
	 * success : function(response){
	 * 
	 * //response.username && response.isAdmin
	 * 
	 * if(response.username != "roleAnonymous"){
	 * 
	 * document.getElementById("SubmitSuccess").value="Valid";
	 * 
	 * 
	 * }else{
	 * 
	 * alert("Fail to login user!"); } }, error:function(response){
	 * alert("Incorrect username or password"); } };
	 * 
	 * AssignmentManager.login(IOP, username, pwd, callback);
	 */
// AssignmentManager.loginWithHash(IOP, username, pwdHash, callback);
		
}

function AppliLogout() 
{
	var IOP=window.location.origin+"/jw"
	var IOP1=window.location.origin;
	var MainPGURL=LoadFrmXML("PG001");
	
	AssignmentManager.logout(IOP);	
	window.close();
	
}

/*
 * function AppliClose() { var IOP=window.location.origin+"/jw" var
 * IOP1=window.location.origin; var MainPGURL=LoadFrmXML("PG001");
 * 
 * //AssignmentManager.logout(IOP); window.close(); }
 */

function Profile()
{
	
	 SessionStChk();
	 if (document.getElementById("SubmitSuccess").value=="InValid")
	 {	
	 return;
	 }
	
	
	var IOP=window.location.origin;
	var MainPGURL=LoadFrmXML("PG003");
		
	JPopup.create("ProfilePopup", "Profile Setup");
	// JPopup.show("ProfilePopup", IOP+MainPGURL, "");
	JPopup.show("ProfilePopup",MainPGURL,"","","","","Get");
	
	
}

/*
 * function submitdata(FieldsClassName) { var x =
 * document.getElementsByClassName(FieldsClassName); var y =
 * document.getElementsByTagName("select"); var name = ""; var fieldid = ""; var
 * value = ""; var type = ""; var a = parseInt(x.length) + parseInt(y.length);
 * 
 * //var formid = document.getElementsByTagName("form").item('0').name;
 * 
 * var formxml = "<Form><Details>"; for (i = 0; i < x.length; i++) { name =
 * document.getElementsByClassName(FieldsClassName).item(i).name; fieldid =
 * document.getElementsByClassName(FieldsClassName).item(i).id; // value =
 * document.getElementsByClassName(FieldsClassName).item(i).value; if ($('#' +
 * fieldid).hasClass("IsCURCommaFields")) { value =
 * document.getElementsByClassName(FieldsClassName).item(i).value.replace(/,/g,
 * ""); } else { if ($('#' + fieldid).hasClass("ImgIdnt")) {
 * if(document.getElementsByClassName(FieldsClassName).item(i).src.indexOf("/Attachments/")!=-1) {
 * value = document.getElementsByClassName(FieldsClassName).item(i).src; value =
 * "/Attachments/" + value.split("/Attachments/")[1]; } else{ value = ""; } }
 * else {
 * 
 * value =
 * document.getElementsByClassName(FieldsClassName).item(i).value.replace(/\&/g,
 * "and"); } }
 * 
 * //type = document.getElementsByTagName("input").item(i).type; //if(fieldid
 * !="" && type!="submit") //{ //if(fieldid !="" && type!="radio") //{ if(value !=
 * "") { var formxml = formxml + "<" + name + ">" + value + "</" + name + ">"; }
 * //} //} } /*for(j=0;j<y.length;j++) { name =
 * document.getElementsByTagName("select").item(j).name; fieldid =
 * document.getElementsByTagName("select").item(j).id; value =
 * document.getElementsByTagName("select").item(j).value; type =
 * document.getElementsByTagName("input").item(j).type; if(fieldid !="" &&
 * type!="submit") { if(fieldid !="" && type!="radio") {
 * 
 * var formxml=formxml+"<"+name+">"+ value +"</"+name+">"; } } } var formxml =
 * formxml + "</Details></Form>"; return formxml; }
 */
function submitdata(FieldsClassName) {
    var x = document.getElementsByClassName(FieldsClassName);
    var y = document.getElementsByTagName("select");
    var name = "";
    var fieldid = "";
    var value = "";
    var type = "";
    var a = parseInt(x.length) + parseInt(y.length);

    // var formid = document.getElementsByTagName("form").item('0').name;

    var formxml = "<Form><Details>";
    for (i = 0; i < x.length; i++) {
        name = document.getElementsByClassName(FieldsClassName).item(i).name;
        fieldid = document.getElementsByClassName(FieldsClassName).item(i).id;
        if ($('#' + fieldid).hasClass("IsCURCommaFields")) {
            value = document.getElementsByClassName(FieldsClassName).item(i).value.replace(/,/g, "");

        }
        else {
            value = document.getElementsByClassName(FieldsClassName).item(i).value.replace(/\&/g, "and");
        }

        if(value != "")
        {
        	
        if ($('#' + fieldid).hasClass("MTXTAR"))
        	
        {
        var len =$('#' + fieldid).val().split('\n').length;
        for(j=1;j<=len;j++){
        	Val=$('#' + fieldid).val().split('\n')[j-1];
        	formxml = formxml + "<" + name + j + ">" + Val + "</" + name+ j + ">";
        }
 
        }
        else
        {
                formxml = formxml + "<" + name + ">" + value + "</" + name + ">";
        }
        }
      
    }
     
    formxml = formxml + "</Details></Form>";
    return formxml;

}
  function FielddatafrmDB(param1,param2,param3,param4,param5,spname)
  {
	  $.ajax({
	      // url: "Upto50L/CS_FetchFormFieldData",
	      url: "/SBIReimbursement/CS_FetchFormFieldData",	   
	        data: { param1: param1, param2: param2, param3: param3, param4: param4, param5: param5,spname: spname },
	      async: false,
	     // dataType: "json",
	      type: 'POST',
	      success: function (xml){

              fetchdropdown('',param4,param5,xml);

		   },

	      error: OnError1

	  });
 
  }
  
  
  function fetchdropdown(index,code,id,xval)
{
// alert('hi');

// xval = xval.documentElement.innerHTML;

// var str_xml = '<Select>'+xval+'</Select>';
	  var str_xml = xval;
// var str_xml=xval;
var parser_xml = new DOMParser();
var oXml = parser_xml.parseFromString(str_xml, "text/xml");
var obj_elementcol = oXml.documentElement;


var refno = '';
var type = '';
var dropstr = "";
var select=document.getElementById(id);
var ind;
var selectedvalue=document.getElementById(id).value;
Remove(id);
	 var option = document.createElement('option');
        option.text = option.value = '--Select--';
		option.index=0;
        select.add(option, i);
		if(selectedvalue=="")
		{
	ind = 0;
		}
	for (i = 0; i < obj_elementcol.getElementsByTagName(code).length; i++){
	// var item = xml.childNodes(0).childNodes(i).text;
	var item = obj_elementcol.getElementsByTagName(code)[i].textContent;
	// var optn = document.createElement("option");
        // document.getElementById(id).options.add(optn);
        // optn.text = '--Select--';
        // optn.value = '--Select--';
if(item==selectedvalue){
ind = i+1;
 
 }

  var exists = false;
	for (x = 0 ; x <= select.options.length - 1 ; x++ ){
		if (select.options[x].value == item) {
		select.options[x].selected = true;
		exists = true;
		break;
		}
	}
	if(!exists) {
	
	var option = document.createElement('option');
		option.setAttribute("value",item);
		var textnode = document.createTextNode(item);
        option.appendChild(textnode);
		document.getElementById(id).appendChild(option);
/*
 * var option = document.createElement('option'); option.text = option.value =
 * item; option.index=i+1; select.add(option, i);
 */
		}
    
   	}
  select.selectedIndex=ind;
}

 function Remove(id)
  {
    var DropDownList=document.getElementById(id);
  
    for(i=DropDownList.length-1; i>=0; i--) 
    {
       // if(DropDownList.options[i].selected)
       // {
           DropDownList.remove(i);         
       // }
    }
  }
  
  function migrateformdata(Fromfields,Tofields,Fromtable,Totable,ProcessID)
  {

      $.ajax({
          // url: "Upto50L/CS_Migrformdata",
          url: "/SBIReimbursement/CS_Migrformdata",
          data: { Fromfields: Fromfields, Tofields: Tofields, Fromtable: Fromtable, Totable: Totable, ProcessID: ProcessID },
          async: false,
         // dataType: "json",
          type: 'POST',
          success: function (xml) {
          // formdataonload_populate();
          },
          error: OnError2

      });
  }
  	function OnError2(xmlRequest) {
           // alertify.alert(LoadFrmXML("V0067"));
			window.alert(LoadFrmXML("V0067"));
			
        }

        function OnError1(xmlRequest) {
            // alertify.alert(LoadFrmXML("V0068"));
			 window.alert(LoadFrmXML("V0068"));

        }


        function FormSubmitButtons() {
 
       var View = $("#SubmitSuccess").val();
	   
        	var ProcessID = getUrlParam("processId");
            var ActivityID = getUrlParam("activityId");
        	
        	if(View!="Hist")
        	{
            $.ajax({
                // url: "Upto50L/UI_formdatains",
                url: "/SBIReimbursement/UI_fetchformButtonData",
                data: { ProcessDefID: ProcessID, ActivityDefID: ActivityID },
                async: false,
               // dataType: "json",
                type: 'POST',
                success: OnSuccess_submitBtn,

                error: OnError_submitBtn

            });
        	}
        }


        function OnSuccess_submitBtn(data) {

           // alertify.alert(data);
          // var val = $(data).find('BTNData').text();
         // alertify.alert(val);
            var Count = $(data).find('BTNData').length;
            var ResultBTNTable = "";
            var BTNnames = "";
            var BTNID = "";
            var $input = "";    
                      
           var parser_xml = new DOMParser();
var oXml = parser_xml.parseFromString(data, "text/xml");
var obj_elementcol = oXml.documentElement;

ResultBTNTable = "<table border=\"0\">";
ResultBTNTable += "<tr>";
for (i = 0; i < Count; i++) {

     BTNnames = obj_elementcol.getElementsByTagName('BTNData')[i].textContent;
     BTNID = BTNnames.replace(/ /g, '');


    ResultBTNTable += "<td>" + "<input type=\"button\" id=\""+ BTNID + "\" value=\"" +BTNnames+ "\" class=\"ForassignmentComplete buttonPRS\"/></td>";
      

}
ResultBTNTable += "</tr>";
ResultBTNTable += "</table>";

$input = $(ResultBTNTable);

$input.appendTo($("#SubmitBTNs"));

// alertify.alert(ResultBTNTable)

        }

        function OnError_submitBtn() {

           // alertify.alert(LoadFrmXML("V0069"));
			window.alert(LoadFrmXML("V0069"));

        }

        function FormDataToDB(tablenameins) {

            var CHKresult = "";

            var tablenameins1 = "";
            var xml = submitdata("DBfields");
            var activityid = getUrlParam("activityId");
            var activityname = GetActivityName();

            $.ajax({

                url: "/SBIReimbursement/UI_formdatains",
                data: { xml: xml, tablenameins: tablenameins, tablenameins1: tablenameins1, activityid: activityid, activityname: activityname },
                async: false,
                // dataType: "json",
                type: 'POST',
                success: function OnSuccess_submit(data) {
                    if (data == 'Success') {
                        // alert("Data Inserted");
                        document.getElementById("SubmitSuccess").value = "Yes";
                        CHKresult = "Yes";
                       
                    }
                    else {
                       // alert("Insertion Failed");
                        document.getElementById("SubmitSuccess").value = "No";
                        CHKresult = "No";
                       
                    }
                },
                error: function OnError_submit(xmlRequest) {
                   // alert("Submission Failed");
                    document.getElementById("SubmitSuccess").value = "No";
                    CHKresult = "No";
                   
                }
            });

            return CHKresult;

        }
        
 
        
     function UI_getdata(param1,param2,param3,param4,param5,spname)
       
       {
    	   
    	   var OP="";
    	  
    	   $.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: param1, param2: param2, param3: param3, param4: param4, param5: param5,spname: spname },
               async: false,
               // dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   	OP=xml1.responseText;
					// ajaxindicatorstop();
            },
			
            error: function (xml1)
            {
				// alertify.alert(LoadFrmXML("V0126"));
					window.alert(LoadFrmXML("V0126"));
				OP="Fail";
				// ajaxindicatorstop();
            }
                                  
               
    	   });     
		  // ajaxindicatorstop();
    	   return OP;
    	   
    	   }
       
       
	   function UI_IntrData(param1,param2,param3,param4,param5,spname,DBSrc)
       
       {
    	   
    	   var OP="";
    	  
    	   $.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: param1, param2: param2, param3: param3, param4: param4, param5: param5,spname: spname,DBSrc:DBSrc},
               async: false,
               // dataType: "json",
               type: 'POST',
               success: function OnSuccess_submit(xml1) {
            	   	
					if(	$(xml1).find("a").text() != "")
					{
					OP=xml1;
					}
					else
					{
					OP="Success"	
					}
					// ajaxindicatorstop();
            },
            error: function (xml1)
            {
				// alertify.alert(LoadFrmXML("V0126"));
					window.alert(LoadFrmXML("V0126"));
				OP="Fail";
				// ajaxindicatorstop();
            }
                                  
               
    	   });     
		  // ajaxindicatorstop();
    	   return OP;
    	   
    	   }
	   
        /*
		 * function OnSuccess_submit(data) { if (data == 'Success') {
		 * alert("Data Inserted");
		 * document.getElementById("SubmitSuccess").value = "Yes"; return "Yes"; }
		 * else { alert("Insertion Failed");
		 * document.getElementById("SubmitSuccess").value = "No"; return "No"; } }
		 * 
		 * function OnError_submit(xmlRequest) { alert("Submission Failed");
		 * document.getElementById("SubmitSuccess").value = "No"; return "No"; }
		 */
        
        function FormDataFromDB(SPName, FieldPrx, FieldClass, GridTableID) {

            // var loggedinuser = GetCurrentUserFName();
			
            var ProcessID = getUrlParam("processId");
            var activityId = getUrlParam("activityId");
            var View = getUrlParam("View");

            // var securityname123 = "#currentUser.firstName#";
            // alertify.alert(securityname123);
                       
            // var ProcessActvID = ProcessID +"|"+activityId;
            // var TableName = TableName + "|" + View;
            var ProcessActvID = ProcessID;
            var TableName = "";
            $.ajax({
                // url: "Upto50L/UI_fetchformdata",
                url: "/SBIReimbursement/UI_fetchformdata",
                data: { SPName:SPName,id: ProcessActvID, TableName: TableName },
                async: false,
               // dataType: "json",
                type: 'POST',
                success: function (xml1) {

                    var n = document.getElementsByClassName(FieldClass);
                    for (i = 0; i < n.length; i++) {
                        name = document.getElementsByClassName(FieldClass).item(i).name;
                        var val = $(xml1).find(name).text();
			if (val == ".00"){
			val = "0.00";
			}
                        if (val != "") {
                            document.getElementById(name).value = val;
	
	
				/*if($("#"+ name).is( "select" ))
				{
				var optn="<option  value='"+val+"'>" + val + "</option>"
				if($("#"+name+" option:contains("+val+")").length==0)
				{
				$("#"+ name).append(optn)
				$("#"+name+" option:contains("+val+")").attr("selected","selected")
				}
				
				}*/
	
				// IsCURCommaFields
				var x = $("#" + name).hasClass("IsCURCommaFields");
                     if (x == true) {
						if(val=="")
						{
							val= "0";
						}
                        $("#" + name).val(CURCommaSep(val.replace(/,/g,"")));
                    }
                    else {
						// For Attached Photo identity Start
					var y = $("#" + name).hasClass("ImgIdnt");
                    if (y == true) {
					
					 var IOP=window.location.origin+LoadFrmXML("RS006");
					
					val = IOP+val;
					
					
					 document.getElementById(name).src = val;
                    }
						// For Attached Photo identity End
					else
					{				
                        document.getElementById(name).value = val;
						}
                    }
                            
                        }
                    }
                    var loggedinuser = $("#UserName").val();
                    document.getElementById(FieldPrx + 'PRCSID').value = ProcessID;
					document.getElementById(FieldPrx + 'ACTVID').value=activityId;
                    document.getElementById(FieldPrx + 'DTMODIFIED').value = get2dateTime();

                    if (document.getElementById(FieldPrx + 'DTCREATED').value == "" || document.getElementById(FieldPrx +'DTCREATED').value == "null") {
                    document.getElementById(FieldPrx + 'DTCREATED').value = get2dateTime();
                    }

                    if (document.getElementById(FieldPrx + 'CREATEDBY').value == "" || document.getElementById(FieldPrx + 'CREATEDBY').value == "null") {
                        document.getElementById(FieldPrx + 'CREATEDBY').value = loggedinuser;
                    }
                    document.getElementById(FieldPrx + 'MODIFIEDBY').value = loggedinuser;

                    // Onloadgrid(GridFields, GridTableID);
		
                },

                error: OnError_ONLOAD

            });

				
			SetGridViewmodeVal();
			
			// For Attached Photo identity Start
		/*
		 * if($(document).find( "input[id*='PrcsID']" ).length!=0) { var
		 * prodata= $(document).find( "input[id*='PrcsID']"
		 * ).attr('id').split("_")[0];
		 * 
		 * PhotoIdnty(prodata+"_"); }
		 */
			
			// For Attached Photo identity End

        }

        function OnError_ONLOAD() {

           // alertify.alert(LoadFrmXML("V0070"));
			  window.alert(LoadFrmXML("V0070"));
        }


		
		
        function get2date() {
            var today ="";
            $.ajax({
                url: "/SBIReimbursement/UI_fetchformdata_get2date",
            data: {  },
            async: false,
           // dataType: "json",
            type: 'POST',
            success: function (xml1) {
               
                today= xml1;

            },

            error: function (xml1) {

              

            }

            });
             
            return today;

          /*
			 * var today = new Date(); var dd = today.getDate(); var mm =
			 * today.getMonth() + 1; var yyyy = today.getFullYear();
			 * 
			 * if (dd < 10) { dd = '0' + dd }
			 * 
			 * if (mm < 10) { mm = '0' + mm }
			 * 
			 * return today = dd + '-' + mm + '-' + yyyy;
			 */

        }


		
		function ChangePrcsActvXmlVal(GridXML,PRFX)
{
	
	  var loggedinuser = GetCurrentUser();
            var ProcessID = getUrlParam("processId");
            var activityId = getUrlParam("activityId");
			var todydt = get2date();
			
	 doc = $.parseXML(GridXML);	
	
	
	for(i=0;i<=$(doc).find('row').length;i++)
	{
		
		$($(doc).find('row')[i]).find(PRFX+'PrcsID').text(ProcessID);
		$($(doc).find('row')[i]).find(PRFX+'ActivityID').text(activityId);
		$($(doc).find('row')[i]).find(PRFX+'ModifiedBy').text(loggedinuser);
		$($(doc).find('row')[i]).find(PRFX+'DTMODIFIED').text(todydt);
								
		
	}
	
	return (new XMLSerializer()).serializeToString(doc);;
	
}




        function ActivityForms() {
        	var ProcessID = getUrlParam("processId");
            var activityId = getUrlParam("activityId");
            $.ajax({
                // url: "Upto50L/UI_formdatains",
                url: "/SBIReimbursement/UI_fetchActivityform",
                data: { ProcessDefID: ProcessID, ActivityDefID: activityId },
                async: false,
              // dataType: "json",
                type: 'POST',
                success: OnSuccess_submitActFrms,

                error: OnError_submitActFrms

            });

        }


        function OnSuccess_submitActFrms(data) {

            // alertify.alert(data);
            // var val = $(data).find('BTNData').text();
            // alertify.alert(val);
            var Count = $(data).find('FormData').length;

            var Formnames = "";
            var FormID = "";
            var $input = "";

            var parser_xml = new DOMParser();
            var oXml = parser_xml.parseFromString(data, "text/xml");
            var obj_elementcol = oXml.documentElement;

            // ResultBTNTable = "<table border=\"0\">";
            // ResultBTNTable += "<tr>";
            for (i = 0; i < Count; i++) {

                Formnames = obj_elementcol.getElementsByTagName('FormData')[i].textContent;
                FormID = Formnames.split('-')[1].replace(/ /g, '');
				
                document.getElementById(FormID).style.display = "";

				if(Formnames.split('-')[0].indexOf('IA') != -1)
				{
				// $("#"+$($("article")[i]).attr('id')).find('iframe').contents().find('body').append('<div
				// id="over" style="position: absolute;top:0;left:0;width:
				// 100%;height:100%;z-index:2;opacity:0.4;filter: alpha(opacity
				// = 50)"></div>');
				$($(".wrapper ul").find('#'+Formnames.split('-')[1])).attr("name", Formnames.split('-')[0])
				}
            }
           // ResultBTNTable += "</tr>";
            // ResultBTNTable += "</table>";

           // $input = $(ResultBTNTable);

           // $input.appendTo($("#SubmitBTNs"));

            // alertify.alert(ResultBTNTable)

        }

        function OnError_submitActFrms(data) {

            alertify.alert(LoadFrmXML("V0071") + data);

        }


        function BranchApprovalLimitCheck() {


            var loggedinuser = GetCurrentUser();
            var Level = "";
            var PrID = "";
            var BrID = "";
            var LoanAmount = "";
            var activityid = getUrlParam("activityId");
            var activityname = GetActivityName();

            if (activityname == "BranchManager") {
                Level = "Branch";               
            }
            else {
                Level = "HO";
            }
            BrID = $('#iframe3').contents().find('#LPRN_BrID').val();
            PrID = $('#iframe3').contents().find('#LPRN_PrID').val();
            LoanAmount = $('#iframe3').contents().find('#iframe5').contents().find('#LPRN_Amt').val();
            
            // var securityname123 = "#currentUser.firstName#";
            // alertify.alert(securityname123);

            // var param1 = "ProcessID=" + ProcessID + "&TableName=" +
			// TableName;

            $.ajax({
                // url: "Upto50L/UI_fetchformdata",
                url: "/SBIReimbursement/UI_ApprovalLimitChk",
                data: { loggedinuser: loggedinuser, PrID: PrID, BrID: BrID, LoanAmount: LoanAmount, Level: Level },
                async: false,
                // dataType: "json",
                type: 'POST',
                success: function (xml1) {

                    var val = $(xml1).find("LimitCheck").text();

                    document.getElementById("ApprovalLimitCheck").value = val;

                },

                error: OnError_ONApprovalCheck

            });


        }

        function OnError_ONApprovalCheck() {

            document.getElementById("ApprovalLimitCheck").value = "Fail";
            // alert("Approval Limit Check Failed");

        }

    /*    function ChkMandatoryFields(FieldsClassName) {
            var x = document.getElementsByClassName(FieldsClassName);
            var y = document.getElementsByTagName("select");
            var name = "";
            var fieldid = "";
            var value = "";
            var type = "";
            var a = parseInt(x.length) + parseInt(y.length);

            // var formid =
			// document.getElementsByTagName("form").item('0').name;
			// var formid = document.getElementsByTagName("form")[0].name;

         // var formxml = "<Form><Details>";
            for (i = 0; i < x.length; i++) {
                name = document.getElementsByClassName(FieldsClassName).item(i).name;
                fieldid = document.getElementsByClassName(FieldsClassName).item(i).id;
                value = document.getElementsByClassName(FieldsClassName).item(i).value;

                if (value == "" || value == "--Select--")
                {      

				var Textfld= $("#"+fieldid).closest('td').prev().find('font').justtext();
				
						if($(document).find("#SubmitSuccess").val()!=undefined)
					{
					// alertify.alert("Fill the mandatory field
					// '"+Textfld+"'|"+fieldid);
					alertify.alert("Fill the mandatory field '"+Textfld+"'|"+fieldid);
					// window.alert("Fill the mandatory field");
                    document.getElementById('SubmitSuccess').value = "Mandatory";
					 return 'No';
					}
					else
					{
					// alertify.alert("Fill the mandatory field
					// '"+Textfld+"'|"+fieldid);
					alertify.alert("Fill the mandatory field '"+Textfld+"'|"+fieldid);
					// window.alert("Fill the mandatory field");
                    document.getElementById(fieldid).focus();
                    return 'No';
					}
                    document.getElementById(fieldid).focus();
                    return ;

                }
                else
                {
				if($(document).find("#SubmitSuccess").val()!=undefined)
					{
                    document.getElementById('SubmitSuccess').value = "";
                }
				
				}

                             
            }
          
         if($(document).find("#SubmitSuccess").val()==undefined)
					{
					 return 'Yes';
				
					}
            // return formxml;

        }
*/

function ChkMandatoryFields(FieldsClassName) 
        {
            var x = document.getElementsByClassName(FieldsClassName);
            var y = document.getElementsByTagName("select");
            var name = "";
            var fieldid = "";
            var value = "";
            var type = "";
            var Textfld="";
            var count=0;
            var a = parseInt(x.length) + parseInt(y.length);
            document.getElementById('SubmitSuccess').value = "";
              for (i = 0; i < x.length; i++) {
                name = document.getElementsByClassName(FieldsClassName).item(i).name;
                fieldid =document.getElementsByClassName(FieldsClassName).item(i).id;
                value = document.getElementsByClassName(FieldsClassName).item(i).value;

                if (value == "" ||value == "0"||value == "--Select--")
                {      

var Textfld= Textfld+$("#"+fieldid).closest('td').prev().find('font').justtext()+',';
 count=1;
                }
              }
              if(count==1)
            
  {
            
 document.getElementById('SubmitSuccess').value = "Mandatory";
            
if($(document).find("#SubmitSuccess").val()!=undefined)
{
            
Textfld = Textfld.slice(0, -1);
alertify.alert("Fill the mandatory fields '"+Textfld+"'|"+fieldid);
                document.getElementById('SubmitSuccess').value = "Mandatory";
 return 'No';
}
            
  }
              else
            
  {
 if($(document).find("#SubmitSuccess").val()==undefined)
{
 return 'Yes';
}
            
  }
        }

        function ForAttachment(GetProcessID, FieldName)
        {

            var ProcessID = document.getElementById(GetProcessID).value;

            var URL = "http://192.168.168.147:1011/home/index?ProcessID=" + ProcessID + "&FieldName=" + FieldName;

            window.open(URL, "Lookup", "toolbar=0,location=0,menubar=0,scrollbars=no,resizable=no,titlebar=0,width=400,height=350",true);


        }

      /*
		 * function submitdata() { var x =
		 * document.getElementsByTagName("input"); var y =
		 * document.getElementsByTagName("select"); var name = ""; var fieldid =
		 * ""; var value = ""; var type = ""; var a = parseInt(x.length) +
		 * parseInt(y.length);
		 * 
		 * var formid = document.getElementsByTagName("form").item('0').name
		 * 
		 * var formxml = "<Form><Details>"; for (i = 0; i < x.length; i++) {
		 * name = document.getElementsByTagName("input").item(i).name; fieldid =
		 * document.getElementsByTagName("input").item(i).id; value =
		 * document.getElementsByTagName("input").item(i).value;
		 * 
		 * type = document.getElementsByTagName("input").item(i).type; if
		 * (fieldid != "" && type != "submit") { if (fieldid != "" && type !=
		 * "radio") {
		 * 
		 * var formxml = formxml + "<" + name + ">" + value + "</" + name +
		 * ">"; } } } for (j = 0; j < y.length; j++) { name =
		 * document.getElementsByTagName("select").item(j).name; fieldid =
		 * document.getElementsByTagName("select").item(j).id; value =
		 * document.getElementsByTagName("select").item(j).value; type =
		 * document.getElementsByTagName("input").item(j).type; if (fieldid != "" &&
		 * type != "submit") { if (fieldid != "" && type != "radio") {
		 * 
		 * var formxml = formxml + "<" + name + ">" + value + "</" + name +
		 * ">"; } } } var formxml = formxml + "</Details></Form>"; return
		 * formxml; }
		 */
        
        function GridMndtry(FieldsClassName) {
            var x = document.getElementsByClassName(FieldsClassName.split("_")[0]);
            var y = document.getElementsByTagName("select");
			var divid=FieldsClassName.split("_")[1]+"GridPop";
            var name = "";
            var fieldid = "";
            var value = "";
            var type = "";
			var Textfld="";
            var a = parseInt(x.length) + parseInt(y.length);

           // var formid =
			// document.getElementsByTagName("form").item('0').name;
		
         // var formxml = "<Form><Details>";
            for (i = 0; i < x.length; i++) {
               // name =
				// divid.getElementsByClassName(FieldsClassName.split("_")[0]).item(i).name;
               // fieldid =
				// divid.getElementsByClassName(FieldsClassName.split("_")[0]).item(i).id;
				
              // value =
				// divid.getElementsByClassName(FieldsClassName.split("_")[0]).item(i).value;
			  
			name =   $($(document).find("#"+divid).find('.'+FieldsClassName.split("_")[0])[i]).attr('name');
		    fieldid =  $($(document).find("#"+divid).find('.'+FieldsClassName.split("_")[0])[i]).attr('id');
			value = $($(document).find("#"+divid).find('.'+FieldsClassName.split("_")[0])[i]).val();
			
			
			
 Textfld= $("#"+fieldid).closest('td').prev().find('font').justtext();
			
if(Textfld == "")
{
	
	Textfld = $("#"+fieldid).closest('td').find('font').justtext();
	
}	
			if (value == "" || value == "--Select--")
                {
                   // alertify.alert("Fill the mandatory fields")
				   		// alertify.alert(LoadFrmXML("V0150")+"
						// '"+Textfld+"'|"+fieldid);
						// window.alert(LoadFrmXML("V0150")+"
						// '"+Textfld+"'|"+fieldid);
						alert(LoadFrmXML("V0150"));
                  // document.getElementById(fieldid).focus();
                    return 'No';

                }
                else if(value == 0 && $("#"+fieldid).hasClass('GridCurrMndtry'))
                {
				
					alert(LoadFrmXML("V0150"));
                  // document.getElementById(fieldid).focus();
                    return 'No';
                    // document.getElementById('SubmitSuccess').value = "";
                    
                }

                             
            }
            return 'Yes';
         
            // return formxml;

        } 
        


  function GridTblMndtry(TableID) {
	

var formname = window.frameElement.getAttribute("Name");
var gridname= $($('#'+TableID+'_wrapper').parent('div').find('.form-section-title')[0]).find('span').justtext().replace(/[*]/g, "").trim();	
	  
	  if($('#'+TableID).hasClass('gridtblmndtry')==true)
{	
if($("#"+TableID).find('tbody tr').length==0 || $("#"+TableID).find('tbody tr').find('.dataTables_empty').text()=="No data available in table")
{
	document.getElementById('SubmitSuccess').value = "GridMandatory";
	// var formname = $('#'+TableID).parents().find('form').attr('id');
	// alertify.alert('Add a Single Row in the Grid "'+gridname+'" in
	// "'+formname+'" Form');
	window.alert(LoadFrmXML("V0165")+' "'+gridname+'" in "'+formname+'" Tab');
	return 'GridMandatory';
	
}
else
{	
	document.getElementById('SubmitSuccess').value ="";
	// return 'Yes';
}
 
}
if($('#'+TableID+'_wrapper').siblings('.form-section-title').find('option:selected').val()=='')
{
	document.getElementById('SubmitSuccess').value = "GridMandatory";
	window.alert(LoadFrmXML("V0168")+' "'+gridname+'" in "'+formname+'" Tab');
	return 'GridMandatory';
	
}


if($('#'+TableID+'_wrapper').siblings('.form-section-title').find('option:selected').val()=='NO')
{
if($("#"+TableID).find('tbody tr').length!=0)
{
	if($("#"+TableID).find('tbody tr').find('.dataTables_empty').text()!="No data available in table")
	{
	document.getElementById('SubmitSuccess').value = "GridMandatory";
	// var formname = $('#'+TableID).parents().find('form').attr('id');
	
	// alertify.alert('Add a Single Row in the Grid "'+gridname+'" in
	// "'+formname+'" Form');
	window.alert(LoadFrmXML("V0166")+' "'+gridname+'" in "'+formname+'" Tab');
	return 'GridMandatory';
	}
}

else
{	
	document.getElementById('SubmitSuccess').value ="";
	// return 'Yes';
}

}


}    


function GridRowChkval(tableid)
{
	
	if($($('#'+tableid+'_wrapper').parent('div').find('.form-section-title')[0]).find('select').val()!="" || $($('#'+tableid+'_wrapper').parent('div').find('.form-section-title')[0]).find('select').val()!=undefined)
	{
		if($("#"+tableid).find('tbody tr').length!=0)
{
	if($("#"+tableid).find('tbody tr').find('.dataTables_empty').text()!="No data available in table")
	{
		$($('#'+tableid+'_wrapper').parent('div').find('.form-section-title')[0]).find('select').val('Yes');
	}
}
else
{
	$($('#'+tableid+'_wrapper').parent('div').find('.form-section-title')[0]).find('select').val('');
	
}
		
	}
}







   /*
	 * function ChkMandatory(FieldsClassName) { var x =
	 * document.getElementsByClassName(FieldsClassName); var y =
	 * document.getElementsByTagName("select"); var name = ""; var fieldid = "";
	 * var value = ""; var type = ""; var a = parseInt(x.length) +
	 * parseInt(y.length); // var formid =
	 * document.getElementsByTagName("form").item('0').name; // var formxml = "<Form><Details>";
	 * for (i = 0; i < x.length; i++) { name =
	 * document.getElementsByClassName(FieldsClassName).item(i).name; fieldid =
	 * document.getElementsByClassName(FieldsClassName).item(i).id; value =
	 * document.getElementsByClassName(FieldsClassName).item(i).value;
	 * 
	 * if (value == "" || value == "--Select--") { alert("Fill the mandatory
	 * fields") document.getElementById(fieldid).focus(); return 'No'; } else {
	 * //document.getElementById('SubmitSuccess').value = ""; } } return 'Yes';
	 * 
	 * //return formxml; }
	 */
        
		function loadScript(src)
    	{
    	  var s,
    	      r,
    	      t;
    	  r = false;
    	  s = document.createElement('script');
    	  s.type = 'text/javascript';
    	  s.src = src;
    	  s.onload = s.onreadystatechange = function() {
    	    // console.log( this.readyState ); //uncomment this line to see
			// which ready states are called.
    	    if ( !r && (!this.readyState || this.readyState == 'complete') )
    	    {
    	      r = true;
    	      callback();
    	    }
    	  };
    	  t = document.getElementsByTagName('script')[24];
    	  t.parentNode.insertBefore(s, t);
    	}
        
		


function attch_Rdt(prfx)
{

		
			var IOP=window.location.origin;
			/**
			 * var Prfx = prfx; var processId = getUrlParam("processId");
			 * 
			 * var formname = $(document).find('form').attr('name');
			 */
	
	var Prfx = prfx;
	var processId = getUrlParam("processId");
	var ActivityId = getUrlParam("activityId");
	var username=$("#UserName").val();
	var tabid=$(window.top.jQuery("#assignmentExternalForm").context).find('.active').attr('id')
	 var formid = $(document.body).find('form').attr('id');
			 /***************************************************************
				 * For Modification Start
				 */

			// for tab start
			 var View="";
			 
			 if($(window.parent.document).find("form").find("#SubmitSuccess").val()=="")
			 {
			 View=$(document).find("form").find("#SubmitSuccess").val()
			 }
			 else
			 {
			 View=$(window.parent.document).find("form").find("#SubmitSuccess").val()
			 }
			
			// for tab end
			 alertify.confirm("Choose Anyone following option?|attachview|Modify|View", function (e) {
				if (e) 
				{ 	 RedirectURL = IOP+"/SBIReimbursement/ViewAttach?Prfx="+Prfx+ "&processId=" + processId+ "&username=" + username+ "&ActivityId=" + ActivityId+ "&formid=" + formid+ "&tabid=" + tabid+ "&View=" + View;
				var type="";
					WintabsCtrl(RedirectURL,'View Attachment','','yes');
					
			     }
			  
				else
				{
					RedirectURL = IOP+"/SBIReimbursement/Attachment?Prfx="+Prfx+ "&processId=" + processId;

					// WintabsCtrl(RedirectURL,'Attachment('+formname+')','','no');
					// window.open(RedirectURL,
					// '_blank','toolbar=no,resizable=yes,location=0');
			JPopup.create("GridPopupShow", "","","");
			// JPopup.show("GridPopupShow",RedirectURL,"","","683","544","Post");
			JPopup.show("GridPopupShow",RedirectURL,"","","","390","Post");
				
					 
				}
			    });
				
			 /***************************************************************
				 * For Modification end
				 */
		/**
		 * * RedirectURL =
		 * IOP+"/SBIReimbursement/Common/Pages/Attachment/Attachment.jsp?Prfx="+Prfx+
		 * "&processId=" + processId;
		 * 
		 * //WintabsCtrl(RedirectURL,'Attachment('+formname+')','','no');
		 * //window.open(RedirectURL,
		 * '_blank','toolbar=no,resizable=yes,location=0');
		 * JPopup.create("GridPopupShow", "","","");
		 * //JPopup.show("GridPopupShow",RedirectURL,"","","683","544","Post");
		 * JPopup.show("GridPopupShow",RedirectURL,"","","","390","Post");
		 * 
		 */	
		}




function UsrAcceptPrcs(activityID)
{
	
	var IOP=window.location.origin+"/jw"
	
	var AccptURL = IOP+"/web/json/workflow/assignment/accept/"+activityID;
	
	var OP="";
	
	OP=UI_getdata(activityID,"","","","","SAM_sChkActvAcct");
	if(OP != 'Accepted')
	{
	ajaxindicatorstart("Loading.. Please Wait")
	
	$.ajax({
                 type: 'POST',
                 url: AccptURL,
                // data: params,
                 dataType : "text",
				 async:false,
                 xhrFields: {
                     withCredentials: true
                 },
                 success: function(data) {
                     OP="Accepted";
                 },
                 error: function(data) {
                     try {
                         // do nothing for now
                        // if (callback.error) {
                          // callback.error.call(thisWindow, data);
						  	// alertify.alert(LoadFrmXML("V0127"));
								window.alert(LoadFrmXML("V0127"));
						  // alertify.alert('Error: File not Accepted');
                        // }
                     }
                     catch (e) {}
                 }
               });
	ajaxindicatorstop();
	}
	return OP;
	
}

function WintabsCtrl(RedirectURL,Text,Wchk,ScrollYN)
{
	
	
if(Wchk != '')
{
	Wchk = ' ('+ Wchk +')'
	
}
var win = window.top.jQuery("#assignmentExternalForm").context
	
	var LiCount = $(win).find('section.wrapper').find('ul.tabs li').length;
	
	LiCount = parseInt(LiCount)-1;
	
 LiCount = parseInt($($(win).find('section.wrapper').find('ul.tabs li')[LiCount]).attr('id').split('b')[1]) + 1;
		
$(win).find('section.wrapper').find('ul.tabs').append('<li name="'+RedirectURL.split('=')[1]+'~'+$('ul.sidebar-menu').find('li.header').text()+'" class="'+RedirectURL.split('=')[1]+'" id="Tab'+LiCount+'"><a href="#tab'+LiCount+'">'+Text+Wchk+'<span class="closetab" onclick="wintabclose(this)">X</span></a></li>')

$(win).find('section.block').append('<article name="Tab'+LiCount+'" id="tab'+LiCount+'"><p><iframe id="PageIframe'+LiCount+'" width="100%" style="overflow-x: hidden;overflow-y: hidden" src="" frameborder="0"></iframe> </p></article>');
 
 
 $(win).find('section.wrapper').find('article').find('#PageIframe'+LiCount).attr('src',RedirectURL);
  $(win).find('section.wrapper').find('article').find('#PageIframe'+LiCount).attr('scrolling',ScrollYN);
 
 
var defaultfrmheight = $(win).find('section.wrapper').find('article').find('#PageIframe').height();

$(win).find('section.wrapper').find('article').find('#PageIframe'+LiCount).height(defaultfrmheight+'px');

			// $(window.parent.document).find('#Tab'+LiCount).find('a').text('Review');

  $(win).find('ul.tabs li').removeClass('active');
        $(win).find('#Tab'+LiCount).addClass('active')
        $(win).find('.block article').hide();
        var activeTab = $(win).find('#Tab'+LiCount).find('a').attr('href');
            // $(activeTab).show();
  $(win).find('#Tab'+LiCount).trigger('click');
	
	
}	

function wintabclose(Evnt)
{
		
	// var trgID = $(Evnt).closest('li').siblings().attr('id');
	var trgID =  $(Evnt).closest('li').prev().attr('id');
		var Liid = $(Evnt).closest('li').attr('id');// $(Evnt).closest('li');
	var artid = $('section').find('#tab'+$(Evnt).closest('li').attr('id').split('b')[1]).attr('id');
	
	$('#'+Liid).remove();
	$('#'+artid).remove();
	
	if($(Evnt).closest('li').attr('class').split(' ')[1]== "active")
		// if($(Evnt).closest('li').attr('class')== "active")
	{
	$('#'+trgID).trigger('click');
	}
	

	// $('section').find('#tab'+$(Evnt).closest('li').attr('id').split('b')[1]);
	
}          


function GetActivityName()
{

 var processId = getUrlParam("processId");
 
 var activityid = getUrlParam("activityId");
 
 var spname ='SAM_sGetActivityName';
 
 var op = UI_getdata(processId,activityid,"","","",spname);
 
var actvityname = $(op).find("ActivityName").text();

return actvityname;

}

function GetPackageID()
{

var processId = getUrlParam("processId");


var spname ='SAM_sGetPackageid';


var op = UI_getdata(processId,"","","","",spname);

var packageid = $(op).find("PackageId").text();

return packageid;

}


function ChkAttchMndtry(Prfx)
{

var formname= $(document.body).find('form').attr('id');
	var prcsid="";

if($('#'+Prfx+'_OldPrcsID').length!=0)
{
			var op= UI_getdata($('#'+Prfx+'_PrcsID').val(),$('#'+Prfx+'_OldPrcsID').val(),"","","","LSW_sChkCusDetlsAttchMod");



	if($(op).find('Result').text() == "OLD-YES")
	{
	
	prcsid = $('#'+Prfx+'_OldPrcsID').val();
	
	}
	else
	{
		prcsid = $('#'+Prfx+'_PrcsID').val();		
		
	}
}
else{
prcsid = $('#'+Prfx+'_PrcsID').val();	
}


// var prcsid= document.getElementById(Prfx+'_PrcsID').value
// var formname=formname;
var formprfx= Prfx;

var spname ='SAM_sGetChkAttchMndtry'

var AttchMndtry= UI_getdata(formname,prcsid,"","","",spname);

var AttchMndtryVal = $(AttchMndtry).find("Result").text();


if(AttchMndtryVal=='AttchY')
{
return AttchMndtryVal;
}
else
{
document.getElementById('SubmitSuccess').value="AttchMandatory";
return AttchMndtryVal;

}
}


function PhotoIdnty(prodata)
{

var a='';
var ImgClsCnt='';

if(parent.document != null)
{
a= prodata;
ImgClsCnt=$(parent.document).find(".ImgIdnt").length;
$(parent.document).find(".ImgIdnt").attr('src', '');
$(parent.document).find(".TxtIdnt").val('');
// var b= $("#mainurldata").val();
}
else{

a= prodata;
ImgClsCnt=$(document).find(".ImgIdnt").length;
$(document).find(".ImgIdnt").attr('src', '');
$(document).find(".TxtIdnt").val('');

}

if(a != undefined && ImgClsCnt !=0)
{

var spldata= a.split('|');
var len=spldata.length;


var ArryCnt=[];

// var mainhidn = $("#mainurldata").val();

// var spldata2="";

for(var i=0; i<=len; i++)
{
	if($(spldata)[i]==""||$(spldata)[i]==undefined)
	{
		var b;
	}
	else
	{	
		var data1= $(spldata)[i].split('~')[0];
		
		if(data1.indexOf("Photo-Identity")==0)
		{
		
		
		ArryCnt += [i]+",";
		
		
		}

		
}
}

if(ArryCnt.length != 0)
{

for(var j=0;j<ArryCnt.split(",").length;j++)
		{
		
		if(ArryCnt.split(",")[j]=="")
		{
			var b;
		
		}
		else{
		var ArryVarVal=ArryCnt.split(",")[j]
		
		var ArrayVal= a.split('|')[ArryVarVal];
		
			var data2=  ArrayVal.split('~')[1];
		var data3=  ArrayVal.split('~')[2];
			var IOP=window.location.origin+LoadFrmXML("RS006");
		
		if(parent.document != null)
		{
			if(j+1 <=ImgClsCnt)
			{
		$(parent.document).find(".ImgIdnt")[j].src=IOP+data3.replace(/ /g, '%20');
		$(parent.document).find(".ImgIdnt")[j].val=data3
		$($(parent.document).find(".TxtIdnt")[j]).val(data2);
			}
		}
		else
		{
			if(j+1 <=ImgClsCnt)
			{
			
		$(document).find(".ImgIdnt")[j].src=IOP+data3.replace(/ /g, '%20');
			$(document).find(".ImgIdnt")[j].val=data3
		$($(document).find(".TxtIdnt")[j]).val(data2);
			}
		
		}
		}
		
		}
}

}
}

function GridMandtryAstrk(tbl)
	{
		
		var tableid = $(tbl).parents('div').find('table').attr('id');
		
		if(tbl.value=='YES')
		{
			
			 $("#"+tableid).addClass('gridtblmndtry');
			 
			$(tbl).closest('span').append('<font class="asterik" color="red">*</font>')

			 
			 
		}
		else{
			 $("#"+tableid).removeClass('gridtblmndtry');
			  $(tbl).closest('span').find('font').remove();

		}
	}

function PageIdval()
{
var hdnAppId ='';
var AppId = $(window.parent.document).find('#BCDT_CusID').val();
var CoAppId='';
var GurAppId = '';



if($(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr').find('.dataTables_empty').text()!="No data available in table")
{
var CoApplen = $(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr').length;


for(i=0;i<CoApplen;i++)
{

CoAppId +=  $($(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr')[i]).find('td')[1].innerHTML;

CoAppId +=',';

}
}

if($(window.parent.document).find("#iframe3").contents().find('#Table2 tbody tr').find('.dataTables_empty').text()!="No data available in table")
{
var GurApplen = $(window.parent.document).find("#iframe3").contents().find('#Table2 tbody tr').length;


for(i=0;i<GurApplen;i++)
{

GurAppId +=  $($(window.parent.document).find("#iframe3").contents().find('#Table2 tbody tr')[i]).find('td')[1].innerHTML;

GurAppId +=',';

}
}
hdnAppId +=AppId+ ',' + CoAppId+ GurAppId;

return hdnAppId;
}





function integer_to_roman(num) {
if (typeof num !== 'number') 
return false; 

var digits = String(+num).split(""),
key = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",
"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
"","I","II","III","IV","V","VI","VII","VIII","IX"],
roman_num = "",
i = 3;
while (i--)
roman_num = (key[+digits.pop() + (i * 10)] || "") + roman_num;
return Array(+digits.join("") + 1).join("M") + roman_num;
}


function romantointeger( num ) {
	var vals = { 'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000 };
	 num = num.toUpperCase().replace( /[^IVXLCDM]/g, '' ).replace( /VV/g, 'X' ).replace( /LL/g, 'C' ).replace( /DD/g, 'M' );
	 var bits = [], i = 0, j = 0, k, l, n = num.length, last = 9999, rep = 0, sum = 0, valid = 1;
	 for( ; i < n; i++ ) {
	  if( !( bits[j] = vals[ num.charAt(i) ] ) ) {
	   valid = 0; break;
	  }
	  if(j > 0) {
	   k = bits[j]; l = bits[j - 1];
	   if( k===l * 5 || k===l * 10 ) {
	    bits[--j] = k - l;
	   }
	  }
	  j++;
	 }
	 if( valid ) {
	  for( i = 0; i < j; i++ ) {
	   k = bits[i];
	   sum += k;
	   if( (last < k) ||
	    (rep > 1 && last == k) ||
	    (last == k && k != 1 && k != 10 && k != 100 && k != 1000) ||
	    last == k * 4 || last == k * 9 || last * 4 == k * 9 || last * 5 == k * 9 ) {
	    valid = 0; break;
	   }
	   rep = (last == k) ? rep + 1 : 0;
	   last = k;
	  }
	 }
	 return valid ? sum : 'Enter a proper Roman number please.';
}


function FetchROIFrmMas(PrcsIDF,LonAmtF,TermF,SchmF,IncmsrcF)
{
	
		var PrcsID=$("#"+PrcsIDF).val();
	var LonAmt=$("#"+LonAmtF).val().replace(/,/g, "");
	var Term=$("#"+TermF).val();
	var Schm="";
	var Incmsrc="";
	if(SchmF!="")
	{
	Schm=$("#"+SchmF).val();
	}
	if(IncmsrcF!="")
	{
	Incmsrc=$("#"+IncmsrcF).val();
	}
	
	var xml = UI_getdata(PrcsID,LonAmt,Term,Schm,Incmsrc,"LSW_sGetROI");
	
	return $(xml).find("ROI").text();
	
}

function emi(param1,param2,param3,paramout)
{
	
	var TotLon =$("#"+param1).val().replace(/,/g, "");
	var ROI=$("#"+param2).val();
	var Terms=$("#"+param3).val();
	var monthlyInterestRatio = (ROI/100)/12;
	
	var top = Math.pow((1+monthlyInterestRatio),Terms);
	var bottom = top -1;
	var sp = top / bottom;
	var emival = ((TotLon * monthlyInterestRatio) * sp);

if (isNaN(emival)||(emival==Infinity)) {
	emival = '0';
	}

var fnlemi = parseFloat(emival).toFixed(0);


$("#"+paramout).val(CURCommaSep(fnlemi));

}

function GridValChk(tableid,gridclmns)
{
	
	
	var trcount= $("#"+tableid).find('tbody tr').length;
	var chkval="";
	var Gridname=$($('#'+tableid+'_wrapper').parent('div').find('.form-section-title')[0]).text();
	
	
if($('#'+tableid).find('tbody tr').find('.dataTables_empty').text()!="No data available in table")
{	
	for(i=0;i<gridclmns.split("|").length;i++)
	{
		
	if(gridclmns.split("|")[i]!="")
	{
	var columnindex = $("#"+tableid).find('thead').find('tr:nth-child(2)').find('th:contains("'+gridclmns.split("|")[i]+'")').index();

	for(j=0;j<trcount;j++)
	{	
	chkval= $($("#"+tableid).find('tbody').find('tr').find("td:eq("+columnindex+")")[j]).text();
	if(chkval=="")
	{
		$("#SubmitSuccess").val("CV");
		// alertify.alert('Enter/Review the "'+Gridname+'" Grid before
		// Submission');
		window.alert('Enter/Review the "'+Gridname+'" Grid before Submission');
		return "No";
	}
	 	 
	}

	}
	}
}
}

/*
 * document.onmousedown=disableclick; status="Right Click Disabled"; function
 * disableclick(event) { if(event.button==2) {
 * 
 * alertify.error(status); return false; } }
 */
 /*
*/

/*
 * function attch_cmnts(prfx) {
 * 
 * var IOP=window.location.origin;
 * 
 * var Prfx = prfx; var processId = getUrlParam("processId"); var ActivityId =
 * getUrlParam("activityId"); var username=$("#UserName").val(); var
 * tabid=$(window.top.jQuery("#assignmentExternalForm").context).find('.active').attr('id')
 * var formid = $(document.body).find('form').attr('id');
 * 
 * 
 * alertify.confirm("Choose Anyone following option?|comments|New Tab|PopUp",
 * function (e) { if (e) { RedirectURL =
 * IOP+"/SBIReimbursement/Common/Pages/Comments/Comments.jsp?Prfx="+Prfx+
 * "&processId=" + processId+ "&username=" + username+ "&ActivityId=" +
 * ActivityId+ "&formid=" + formid; JPopup.create("GridPopupShow", "","","");
 * JPopup.show("GridPopupShow",RedirectURL,"","","","390","Post"); }
 * 
 * else {
 * 
 * 
 * RedirectURL =
 * IOP+"/SBIReimbursement/Common/Pages/Comments/Comments.jsp?Prfx="+Prfx+
 * "&processId=" + processId+ "&username=" + username+ "&ActivityId=" +
 * ActivityId+ "&formid=" + formid+ "&tabid=" + tabid; var type="TimeLine"
 * WintabsCtrl(RedirectURL,'Comments View('+type+')','','yes'); } });
 * 
 * 
 * //$.alert('Hi'); //window.open(RedirectURL,
 * '_blank','toolbar=no,resizable=yes,location=0'); }
 */

function attch_cmnts(prfx)
{

	var IOP=window.location.origin;
	
	var Prfx = prfx;
	var processId = getUrlParam("processId");
	var ActivityId = getUrlParam("activityId");
	var username=$("#UserName").val();
	var tabid=$(window.top.jQuery("#assignmentExternalForm").context).find('.active').attr('id')
	 var formid = $(document.body).find('form').attr('id');
	 
	 
	// for tab start
	 var View="";
	 
	 if($(window.parent.document).find("form").find("#SubmitSuccess").val()=="")
	 {
	 View=$(document).find("form").find("#SubmitSuccess").val()
	 }
	 else
	 {
	 View=$(window.parent.document).find("form").find("#SubmitSuccess").val()
	 }
	
	// for tab end
	
		  
 alertify.confirm("Choose Anyone following option?|comments|New Tab|PopUp", function (e) {
	if (e) 
	{ 	
            	 RedirectURL = IOP+"/SBIReimbursement/Comments?Prfx="+Prfx+ "&processId=" + processId+ "&username=" + username+ "&ActivityId=" + ActivityId+ "&formid=" + formid;
				JPopup.create("GridPopupShow", "","","");
			  JPopup.show("GridPopupShow",RedirectURL,"","","","390","Get");
     }
  
	else
	{
	
	
		  RedirectURL = IOP+"/SBIReimbursement/Comments?Prfx="+Prfx+ "&processId=" + processId+ "&username=" + username+ "&ActivityId=" + ActivityId+ "&formid=" + formid+ "&tabid=" + tabid+ "&View=" + View;
			var type="TimeLine"
	WintabsCtrl(RedirectURL,'Comments View('+type+')','','yes');
	}
    });
		  
	 
// $.alert('Hi');
	// window.open(RedirectURL, '_blank','toolbar=no,resizable=yes,location=0');
	
 
}

function emptygridchk(tableid)
{
if($('#'+tableid+' tbody tr').length==0)
{
var emptyrow='<tr align="center" class="odd"><td class="dataTables_empty">No data available</td></tr>'
$('#'+tableid+' tbody').append(emptyrow)
}
}

function emptyrwdelte(tableid)
{
 var x = $('#'+tableid+' tbody tr').hasClass('odd');
if(x==true)
{
$('#'+tableid+' tbody').find('.odd').closest('tr').remove();
}

}




function DeleteAttchDirData(Prfx)
{
	
	// var delattchdata=$("#"+Prfx+"delAttch").val();
	 var username=GetCurrentUserFName();
	 var formid=$(document.body).find('form').attr('id');	
	var PrcsId = getUrlParam("processId");
	var activityid = getUrlParam("activityId");
	
	
	var spname='Sam_sDCCMFnlMnpl';
	
	var op= UI_getdata(PrcsId,formid,"","","",spname);
	
var domain1= LoadFrmXML("RS001");
var usrpwd1= LoadFrmXML("RS002");

var domain2= LoadFrmXML("RS004");
var usrpwd2= LoadFrmXML("RS005");
	
	var delattchdata=$(op).find("Result").text();
	var Insattchdata=$("#"+Prfx+"delAttch").val();
		if(delattchdata != '')
		{
		
	 $.ajax({
	        url:"/SBIReimbursement/deletefile?Insattchdata="+Insattchdata+"&delattchdata="+delattchdata+"&domain1="+domain1+"&usrpwd1="+usrpwd1+"&domain2="+domain2+"&usrpwd2="+usrpwd2,
	        // data: fd,
	        contentType: false,
	        processData: false,
			async:false,
	        type: 'POST',
	        success: function(data){
				
	         // alert(data);
	        
			},
	        failure:function(data)
	        {
					// alertify.alert(LoadFrmXML("V0115"));
						window.alert(LoadFrmXML("V0115"));
	        	
	        	
	        }
	    });
		}


		
}



function DCCMInstnc(flgval)
{
var formid=$(document.body).find('form').attr('id');	
var PrcsId = getUrlParam("processId");
var activityid = getUrlParam("activityId");

//var xml = UI_getdata(PrcsId,activityid,formid,flgval,"","Sam_sDCCMInstnc");

}




function AttchDmsIns(Data,FlagVal,prodata){

if (prodata!=''){
var formid=$(window.parent.document).find('form').attr('id');
var PrcsId = $(window.parent.document).find('form').find("#"+prodata+"PrcsID").val();
var activityid = $(window.parent.document).find('form').find("#"+prodata+"ActivityID").val();
var CreatedBy=$(window.parent.document).find('form').find("#"+prodata+"CreatedBy").val();
var ModifiedBy=$(window.parent.document).find('form').find("#"+prodata+"ModifiedBy").val()
// alert(Data+','+FlagVal+','+formid+','+PrcsId+','+activityid+','+CreatedBy+','+ModifiedBy)
var xml = UI_getdata(Data,FlagVal,formid+','+PrcsId+','+activityid,CreatedBy,"","sam_sAttchDms");

}
else if (prodata==''){

var formid=$(window.document).find('form').attr('id')
var prodata =$(window.document).find('form').find("[id*='PrcsID']").attr('id').split('_')[0]
var PrcsId = $(window.document).find('form').find("#"+prodata+"_PrcsID").val();
var activityid = $(window.document).find('form').find("#"+prodata+"_ActivityID").val();
var CreatedBy=$(window.document).find('form').find("#"+prodata+"_CreatedBy").val();
var ModifiedBy=$(window.document).find('form').find("#"+prodata+"_ModifiedBy").val()
// alert(Data+','+FlagVal+','+formid+','+PrcsId+','+activityid+','+CreatedBy+','+ModifiedBy)
var xml = UI_getdata(Data,FlagVal,formid+','+PrcsId+','+activityid,CreatedBy,"","sam_sAttchDms");

}

}

function AttachDMSFetch(prodata){
var formid=$(window.parent.document).find('form').attr('id');
var PrcsId = getUrlParam("processId");
var OldPrcsID = getUrlParam("OLDprocessId");
var activityid = $(window.parent.document).find('form').find("#"+prodata+"ActivityID").val();
var xml = UI_getdata(formid,PrcsId,activityid,OldPrcsID,"","Sam_sOnloadDms");
// alert($(xml).text());
BindAttchViewTable($(xml).text());
}



function Ratio(param1,param2,paramout)
{
var value1=parseFloat(document.getElementById(param1).value.replace(/,/g, ""));
var value2=parseFloat(document.getElementById(param2).value.replace(/,/g, ""));
 

    
 var result =(parseFloat(value1) / parseFloat(value2))*100;




 if (isNaN(result)||(result==Infinity)) {
 result = '0';
    }
 
 var outval = parseFloat(result).toFixed(2);
 // $('#'+param3).val(CURCommaSep(value3));
 $('#'+paramout).val(outval);
   


}

function ChkBlankFrames()
{
	
	 var IframeCount = $('article').find('iframe').length;
		
var BlankFrames = "";
		
	 if (IframeCount > 0) {
            for (i = 0; i < IframeCount; i++) {
				
				var $input = $('article').find('iframe')[i];
				var IFrameName = $($('article').find('iframe')[i]).attr('name');
                var IFrameID = $input.id;
				var ArticleName = $($('article')[i]).attr('name');
				
				if($('form').attr('id')=="AllForms")
{
	if($('#'+ArticleName).attr('name')!="IA")
	{
			if($('#'+ArticleName).attr('style')=="display:none"|| $('#'+ArticleName).attr('style')=="display: none;")
	{
		var blk='';
	}
	else{
	
		if($('#' + IFrameID).contents().find('body').text() == "")
				{
    
	BlankFrames = BlankFrames + IFrameName + ', ';
	
				}
	
	}
	
	
	}
}
else{
	
	if($('#'+ArticleName).attr('name')!="IA")
	{
	
		if($('#' + IFrameID).contents().find('body').text() == "")
				{
				
				if(IFrameID!="iframe0")
				{
				
				
    
	BlankFrames = BlankFrames + IFrameName + ', ';
	}
				}
	
	
	
	}
}		
			}
}


if(BlankFrames!="")
{
swal({
                        title: LoadFrmXML("V0154"),
                        text: BlankFrames,
                        type: "warning",
                        showCancelButton: true,
                        showConfirmButton: false,
                        cancelButtonColor: "#1569C7",
                        cancelButtonText: "OK",
                        closeOnConfirm: false
                    });
}
return BlankFrames;
}

function GetCustomPageHdrStrip(Content1,Content2,ID)
{
	
	var op= UI_getdata(Content1,Content2,ID,'','','SAM_sGETCstmPgHdrStrip');
		
	op= op.replace(/<Resultset><a><HTML>/g, "");	
	op= op.replace('</HTML></a></Resultset>',''); 
	$('#PoplCustomHdrStrip').append(op);
	
}

function GetFormURL(FormId)
{
	var PrcsID=getUrlParam("processId");
	
	var op= UI_getdata(PrcsID,FormId,'','','','SAM_sGETFormURL');
	
	// op= op.replace(/<Resultset><a>/g, "");
	// op= op.replace('</a></Resultset>','');
	
	var Formurl = $(op).find("FormURL").text();
	
	var Formname = $(op).find("FormName").text();
	
	var outputresult = Formurl+','+ Formname;
	// op= op.find('Result').text();
	
	return outputresult;
	
}

function SPLGridPopup()
{
	document.getElementById("popupSPL").style.display="";
  $("#popupSPL").dialog();
  $("#popupSPL").dialog("option", "width", 805);
  $("#popupSPL").dialog("option", "closeOnEscape", true);
  $("#popupSPL").dialog("option", "position", { my: "right bottom", at: "left top", of: $(this) });
	document.getElementById("SPLFetchGridBtn").style.display="";
	   $("#popupSPL").attr("tabindex",0).focus();
	    $("#popupSPL").attr("tabindex","");
	
}


function popupcnfrmalertfyclose(id)
{
	// $(this).closest('section').remove();
var idval=$(id).attr('id')
$('.'+idval).closest('section').remove();
}
	
function HelpOptn()
{
	var URLHELP=LoadFrmXML("PG012");
	
	
	WintabsCtrl(URLHELP,'Help','','yes');
	
}	


function GetPin(idval)
{
if(idval!="")
{

	var PinDetls= UI_getdata(idval,'','','','','SAM_sGETPinDetl');
		
if($(PinDetls).find("statename").text()=="")
{
	alert("Please contact IT team. This Pin code is not present in database")
}

	
	return PinDetls;
}
}
function GetIfsc(idval)
{
	
	if(idval!="")
	{
	 if(idval.length <11 || idval.length >11 )
	 {
						
  	 window.alert(LoadFrmXML("V0157"));
     return false;
	 }
	 else
	 {
	var IfscDetls= UI_getdata(idval,'','','','','SAM_sGETIfscDetl');

if($(IfscDetls).find("BANK").text()=="")
{
	alert("Please contact IT team. This Ifsc code is not present in database")
}	

	return IfscDetls;
	 }
	}
}

function MobileChk(idval)
{
	var formid= $(document).find('form').attr('id')
	if(formid=='CustomerCreation')
	{
	if($(idval).val()!=''||$($(idval).val()).length!=10)
	{
	var MblDtls=UI_getdata($(idval).val(),$("#CUCR_CusID").val(),'','','','LSW_sKYCMblChk');
	if($(MblDtls).find("Result").text()!='No')
	{	
	alert("Mobile Number Already Exists for the Customer  "+$(MblDtls).find("Result").text())
	}
	}
	}
	else
	{
		
	if($(idval).val()!=''||$($(idval).val()).length!=10)
	{
	var MblDtls=UI_getdata($(idval).val(),'','','','','LSW_sKYCMblChk');
	if($(MblDtls).find("Result").text()!='No')
	{	
	alert("Mobile Number Already Exists for the Customer  "+$(MblDtls).find("Result").text())
	}
	}	
	}
}

function SndEmailConfig(PrcsID)
{


 var OPXML = UI_getdata(PrcsID,"","Vendor","Group","","SAM_sGetVendorMailDetl");
				
		var To=$(OPXML).find("Email").text().slice(0,-1);
		var Title=$(OPXML).find("Subject").text()
		var MsgContent=$(OPXML).find("Content").text()
	
		var fromMail = $(OPXML).find("SendMail").text()
		var fromPwd =$(OPXML).find("Password").text()
		var Prophost =  $(OPXML).find("HostName").text()+'|'+$(OPXML).find("PortNo").text()
		 var rt='Proceed';

		 
	if(To!='')
		{	 
		 
		$.ajax({
	        url:"/SBIReimbursement/SendMail",
	        type: 'POST',
	        async:false,
	        data: {To:To,Title:Title,MsgContent:MsgContent,fromMail:fromMail,fromPwd:fromPwd,Prophost:Prophost},
	        success: function(stm){	
			
			if(stm=='Success')
			{
	        	// alert(stm);
				rt=stm;
				
			}
			else
			{
			alert(stm);
			return;
			}
	        	
	        },
	        });
}
return rt

}



function CibilRpt(id,prfx,Screid,ApplNo,Prdcde,PrdctNme,SbPrdctNme,LnAmt)
{



ChkMandatoryFields("Mndtry");
 if (document.getElementById('SubmitSuccess').value == "Mandatory")
 {
	 
	// alert("Fill the Mandatory Fields")
	 return
 }

if(id!="")
{
if(Screid!="")	
{

	
	
	swal({
                title: "", 
                text: "Click on 'OK' to generate cibil else Click on 'Cancel'" ,
                type: "warning", 
                showCancelButton: true, 
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Ok!",
                animation: "slide-from-top", 
                closeOnConfirm: false
            },
			
			
	
	function () {
			
var op= UI_getdata(id,ApplNo,$(document).find('form').find("#"+prfx+"_PrcsID").val(),Prdcde,"Chk","Sam_sCibilDataIns")

if($(op).find("Result").text()=='Yes')	
{		
			

   var root= LoadFrmXML("RS001");
   var Usrpwd= LoadFrmXML("RS002");
   var PrcsID=getUrlParam("processId");
   var FormName="LoanApplication";
   var AttchName="CIBIL REPORT";
   var AttchDescrptn="CIBILRPT_"+id;
   var CntntURL="http://14.192.17.66:8080/CibilWebService/rest/Score/post";
   var op= UI_getdata("CBLVRNO","","","","","Sam_sGetCOMSeqID")

   var Version=$(op).find("VR").text()
   var spname="LSW_SGetCibilData";
	var prodata = prfx+"_";
	var param1=id;
	var param2='';
	var param3='';
	var param4='';
	var param5='';

	
	$.ajax({
		// url: "Upto50L/UI_fetchformdata",
		url : "/SBIReimbursement/JerseyClientPost",
		data : {
			param1 : param1,
			param2 : param2,
			param3 : param3,
			param4 : param4,
			param5 : param5,
			root : root,
			Usrpwd : Usrpwd,
			PrcsID : PrcsID,
			FormName : FormName,
			AttchName : AttchName,
			Version : Version,
			AttchDescrptn : AttchDescrptn,
			spname : spname,
			CntntURL : CntntURL
		},
		async : false,
		// dataType: "json",
		type : 'POST',
        success: function (xml1) {

           // var val = $(xml1).find("LimitCheck").text();
var op= UI_getdata(id,ApplNo,$(document).find('form').find("#"+prfx+"_PrcsID").val(),Prdcde,xml1,"Sam_sCibilDataLogIns")
  
if(xml1.split('|')[1]=="PDF" && xml1.split('|')[0]!="")
{
xml1=xml1.replace(/\//g, '\\');
var formid=FormName;
var PrcsId = $(document).find('form').find("#"+prfx+"_PrcsID").val();
var activityid = $(document).find('form').find("#"+prfx+"_ActivityID").val();
var CreatedBy=$(document).find('form').find("#"+prfx+"_CreatedBy").val();
var ModifiedBy=$(document).find('form').find("#"+prfx+"_ModifiedBy").val()
// alert(Data+','+FlagVal+','+formid+','+PrcsId+','+activityid+','+CreatedBy+','+ModifiedBy)
var xml = UI_getdata(xml1.split('|')[0]+'|',"upload",formid+','+PrcsId+','+activityid,CreatedBy,"","sam_sAttchDms");
$('#'+Screid).val(xml1.split('|')[2])

var op= UI_getdata(id,ApplNo,$(document).find('form').find("#"+prfx+"_PrcsID").val(),Prdcde,"Ins","Sam_sCibilDataIns")
 swal("CIBIL GENERATION", "Success!", "success");
}
		else
{
// alert(xml1.split('|')[0])
$('#'+Screid).val('')
  swal("CIBIL GENERATION", "FAILED"+xml1.split('|')[0]+"!", "error");

}		

// $("#LNAP_ApplName").val($(xml1).find("APDT_FullName").text());
            
        },

        error: function (xml1)
{


}


    });
}
	else{
	alert($(op).find("Result").text());
}
	
				
});

var CUSVAL= UI_getdata(id,"","","","","Sam_sGetCusVal")

$(CUSVAL).find("Name").text()

                var divctnt ='<div class="TxtCntnt"><table style="text-align:left;">'
				divctnt +='<tr><td>Customer Name</td><td>:  '+$(CUSVAL).find("Name").text()+'</td></tr>'
				divctnt +='<tr><td>Customer ID</td><td>:   '+id+'</td></tr>'
				divctnt +='<tr><td>Age</td><td>:   '+$(CUSVAL).find("Age").text()+'</td></tr>'
				divctnt +='<tr><td>PAN ID</td><td>:   '+$(CUSVAL).find("Id").text()+'</td></tr>'
				divctnt +='<tr><td>Loan Amount</td><td>:   '+LnAmt+'</td></tr>'
				divctnt +='<tr><td>Product Name</td><td>:   '+PrdctNme+'</td></tr>'
				divctnt +='<tr><td>Sub Product</td><td>: '+SbPrdctNme+'</td></tr>'
				divctnt +='</tbody></table></div>'
				$('.showSweetAlert').find('fieldset').prev().append(divctnt)

}
	}
	else
	{
	
	alert("Please select the Applicant");
	}
}





function WrkFlwTrmntChk()
{

var ActvtyTrmChk =UI_getdata(getUrlParam("processId"),getUrlParam("activityId"),'','','',"LSW_sWFActvTermChck");

var Result=$(ActvtyTrmChk).find('Result').text();


return Result;

}



function pancardvalidation(idval,valchk,frstnme,mdlnme,cusid)
{

    var value= $(idval).val()
	var pancardList = [];
	pancardList.push(value);
	var reqobj ={};
	var xmlhttp = new XMLHttpRequest();
	// reqobj.pancardList=[];
	reqobj.pancardList=pancardList;

	if($(valchk).val()=="")
	{
	
	$.ajax({
        type: "POST",
        dataType: 'json',
        url: "/NSDLWebApp/rest/nsdl/getpanverification",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(reqobj),
        success: function (msg) {              
            // var responseobj = JSON.parse(msg);
        	
        	if(msg.interrorMsg=='')
        		{
        	if (msg.panCardList[0].status=='E')
        	{
            alert("Entered PAN is valid and existing and belongs to - "+msg.outputstring.split('^')[6]+' '+msg.panCardList[0].firstName+' '+msg.outputstring.split('^')[5]+' '+msg.panCardList[0].lastName);    
			$(valchk).val('Valid');
			
			// $(tlt).val(msg.outputstring.split('^')[6]);
			$(frstnme).val(msg.outputstring.split('^')[6]);
			$(mdlnme).val(msg.panCardList[0].firstName+' '+msg.outputstring.split('^')[5]+' '+msg.panCardList[0].lastName);
			// $(lstnme).val(msg.outputstring.split('^')[4]);
			
			 UI_getdata(value+'|'+$(valchk).val(),$(cusid).val(),msg.panCardList[0].firstName,msg.outputstring.split('^')[5],msg.panCardList[0].lastName+'|'+msg.outputstring.split('^')[6],"Sam_sPanLogIns");
			}
			
			else{
			
			alert('Entered Pan Number is Invalid')
			// $(idval).val('');
			// $(tlt).val('');
			$(frstnme).val(msg.outputstring.split('^')[6]);
			$(mdlnme).val(msg.panCardList[0].firstName+' '+msg.outputstring.split('^')[5]+' '+msg.panCardList[0].lastName);
			// $(lstnme).val('');
			$(valchk).val('Invalid');
			
			  UI_getdata(value+'|'+$(valchk).val(),$(cusid).val(),msg.panCardList[0].firstName,msg.outputstring.split('^')[5],msg.panCardList[0].lastName+'|'+msg.outputstring.split('^')[6],"Sam_sPanLogIns");

			}
        	}
			else
			{
			alert('Entered Pan Number is Invalid')
			// $(idval).val('');
			// $(tlt).val('');
			$(frstnme).val('');
			// $(mdlnme).val('');
			// $(lstnme).val('');
			$(valchk).val('Invalid');
			
			  UI_getdata(value+'|'+$(valchk).val(),$(cusid).val(),msg.panCardList[0].firstName,msg.outputstring.split('^')[5],msg.panCardList[0].lastName+'|'+msg.outputstring.split('^')[6],"Sam_sPanLogIns");

			}
        },
        error: function (e) {

            alert(e);
        }
    });
}
else
{
 alert("Already Verified ("+$(valchk).val()+")");
}
}

// Recovery Email Start

function RcvyEmail(UserId,To,Content)
{


 var OPXML = UI_getdata(UserId,Content,"","","","SAM_sGetRecvyMailDetl");
				
		var To=To;
		var Title=$(OPXML).find("Title").text();
		var MsgContent= $(OPXML).find("Content").text();
	
		var fromMail = $(OPXML).find("SendMail").text()
		var fromPwd =$(OPXML).find("Password").text()
		var Prophost =  $(OPXML).find("HostName").text()+'|'+$(OPXML).find("PortNo").text()
		 var rt='Proceed';

		 
	if(To!='')
		{	 
		 
		$.ajax({
	        url:"/SBIReimbursement/UpSendMail",
	        type: 'POST',
	        async:false,
	        data: {To:To,Title:Title,MsgContent:MsgContent,fromMail:fromMail,fromPwd:fromPwd,Prophost:Prophost},
	        success: function(stm){	
			
			if(stm=='Success')
			{
	        	// alert(stm);
				rt=stm;
				
			}
			else
			{
			alert(stm);
			return;
			}
	        	
	        },
	        });
}
return rt

}
// Recovery Email End






// field Document Upload Start
function FldUpldHndlr(id)
{

var Val=$(id).val()
if(Val=="Upload")
{
if($(id).closest('td').find('input[type="file"]').val()!="")
{
    var domain1= LoadFrmXML("RS001");
    var usrpwd1= LoadFrmXML("RS002");
    var PrcsID=getUrlParam("processId");
    var FormName= $(id).closest('form').attr('id');
	if(FormName=="" || FormName==undefined)
	{
		FormName=$(id).closest('table').parent('div').next('form').attr('id')
	}
	
	
    var names="";
    var descrptns="";
	var op= UI_getdata("DOCVRNO","","","","","Sam_sGetCOMSeqID")
	var flsize = "";
 var fd = new FormData();
   var vrsnno= "";
	var prodata = "";
var CountAttch=1;
	
	 for(var c=0;c<CountAttch;c++)
	 {
      file_data = $(id).closest('td').find('input[type="file"]')[0].files; // for
																			// multiple
																			// files
	     for(var i = 0;i<file_data.length;i++){
			var op= UI_getdata("DOCVRNO","","","","","Sam_sGetCOMSeqID")
	         fd.append("file_"+c, file_data[i]);
	         names += $(id).closest('td').find('input[type="file"]')[0].files[0].name.split('.')[0]+',';
			 flsize += parseFloat($(id).closest('td').find('input[type="file"]')[0].files[0].size/1024).toFixed(2)+',';
			 vrsnno += $(op).find("VR").text()+',';
			 if($($('input[type="file"]')[c]).closest('tr').find("#comments").val()=="")
			 {
				 $($('input[type="file"]')[c]).closest('tr').find("#comments").val("No Description");
			 }
	         descrptns += 'FieldDocument'+',';
	     }
	 }

 ajaxindicatorstart("Uploading.. Please wait");
	    $.ajax({
	        url:"/SBIReimbursement/FileUploadHandler?names="+names+"&PrcsID="+PrcsID+"&FormName="+FormName+"&descrptns="+descrptns+"&flsize="+flsize+"&vrsnno="+vrsnno+"&domain1="+domain1+"&usrpwd1="+usrpwd1,
	        data: fd,
			async:false,
	        contentType: false,
	        processData: false,
	        type: 'POST',
	        success: function(data){
			
			if(data=="Fail")
	        		{
						 ajaxindicatorstop();
	        		alert(LoadFrmXML("V0119"));
	        		}
			else{
				AttchDmsIns(data,'upload',prodata);
				$(id).val('View');
				$(id).closest('td').find('input[type="hidden"]').val(data.split('~')[2])
				$(id).closest('td').find('input[type="file"]').attr('disabled',true)
				$(id).closest('td').find('input[type="file"]').val('');
				
					ajaxindicatorstop();
					alert(LoadFrmXML("V0118"));
					
				}	
					 ajaxindicatorstop(); 
	        },
	        failure:function(data)
	        {
	     		  ajaxindicatorstop();
					alert(LoadFrmXML("V0119"));
	        	
	        }
	    });
		
		  ajaxindicatorstop();
		  }
		  else{
		  
		  alert('select the file to upload');
		  }
}
		 
if(Val=="View")
{
	var IOP=LoadFrmXML("RS006");
	var url= $(id).closest('td').find('input[type="hidden"]').val();
	var type="DOC";
    RedirectURL = IOP+url.replace(/\\/g,"/");
	WintabsCtrl(RedirectURL,'Attach View('+type+')','','yes');
}	
	  
}		  


function FldDocDlte(id)
{

if($(id).closest('td').find('input[type="hidden"]').val()!="")
{
var strconfirm = confirm("Are you sure you want to delete?");
if (strconfirm == true) 
{
	
var Prfx=$(id).closest('td').find('input[type="hidden"]').attr('id').split('_')[0];

var op= UI_getdata(getUrlParam("processId"),Prfx,$(id).closest('form').attr('id'),$(id).closest('td').find('input[type="hidden"]').attr('id'),$('#'+Prfx+'_ModifiedBy').val()+'|'+$(id).closest('td').find('input[type="hidden"]').val(),"Sam_sDltFldAttch")

$(id).closest('td').find('input[type="hidden"]').val('');
$(id).closest('td').find('input[type="button"]').val('Upload');
$(id).closest('td').find('input[type="file"]').val('');
$(id).closest('td').find('input[type="file"]').attr('disabled',false)
}

}


}

// Only for Repco
// Start
function RepcoPageIdval()
{
var hdnAppId ='';
var AppId = $(window.parent.document).find('#BCDT_CusID').val();
var CoAppId='';
var GurAppId = '';



if($(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr').find('.dataTables_empty').text()!="No data available in table")
{
var CoApplen = $(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr').length;


for(i=0;i<CoApplen;i++)
{

CoAppId +=  $($(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr')[i]).find('td')[1].innerHTML;

CoAppId +=',';

}
}


hdnAppId +=AppId+ ',' + CoAppId;

return hdnAppId;
}


function RepcoGridPageIdval()
{
var hdnAppId ='';
var AppId = $(window.parent.document).find('#BCDT_CusID').val();
var CoAppId='';
var GurAppId = '';



if($(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr').find('.dataTables_empty').text()!="No data available in table")
{
var CoApplen = $(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr').length;


for(i=0;i<CoApplen;i++)
{

CoAppId +=  $($(window.parent.document).find("#iframe1").contents().find('#Table2 tbody tr')[i]).find('td')[1].innerHTML;

CoAppId +=',';

}
}

if($(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr').find('.dataTables_empty').text()!="No data available in table")
{
var GurApplen = $(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr').length;


for(i=0;i<GurApplen;i++)
{

GurAppId +=  $($(window.parent.document).find("#iframe2").contents().find('#Table2 tbody tr')[i]).find('td')[1].innerHTML;

GurAppId +=',';

}
}
hdnAppId +=AppId+ ',' + CoAppId+ GurAppId;

return hdnAppId;
}

// Repco End



function FldUpldVal(id)
{
if($(id).val()=="")
{
// $(id).closest('td').find('input[type="hidden"]').val('');
$(id).closest('td').find('input[type="button"]').val('View');
}
else
{
AttchDmsIns($(id).closest('td').find('input[type="hidden"]').val(),'delete',"");
$(id).closest('td').find('input[type="hidden"]').val('');
$(id).closest('td').find('input[type="button"]').val('Upload');
}


}
// Field document upload end





function wflowsubmit(){
	var Module=getUrlParam("Module");
    var Username=$("#UserName").val();
     
    var activityid = getUrlParam("activityId");
    var op= UI_getdata(Module,"APPROVAL",Username,'Submit',activityid,'RIMB_sGetProcActvId');
     
    URL = "/SBIReimbursement/SRMB_FReimbHome";
     
    window.location.href= window.location.origin + URL;

}
function GetUserDetl(){
	 
	
	$.ajax({

        url: "/SBIReimbursement/UserSessionDetl",
        data: { },
        async: false,
        type: 'POST',
        success: function OnSuccess_submit(data) {
            $("#UserName").val($(data).find('USERNAME').text());
            $("#UserRole").val($(data).find('USERROLE').text());
        },
        error: function OnError_submit(xmlRequest) {
           
        }
    });
}




function DataXmltoFiled(Prefix,Xml){
	$(document).find('.'+Prefix).each(function(){
		var id=$(this).attr("id");
		var Val=$(Xml).find(id).text();
		if (Val == ".00"){
			Val = "0.00";
		}
		if(Val!="" && Val !="null"){
			$(this).val(Val);
		}
	});
	
}


function ValidateBnkBIC(BICid , Bankid){
	var BnkBIC = $("#" + BICid).val();
	if (BnkBIC != ""){
		if (len = BnkBIC.length == 8)
		{
			BnkBIC = BnkBIC + 'XXX';
		}
		if (len = BnkBIC.length == 12)
		{
			if(BnkBIC.charAt(8)=='A')
			{
			BnkBIC = BnkBIC.substring(0, 8) + BnkBIC.substring(9, 12);
			}
		}
		var op= UI_getdata(BnkBIC,"","","","",'RIMB_SGETBNKBICVALD');
		Valid = parseInt($(op).find("VLD").html());
		if(isNaN(Valid)){
			Valid = 0;
		}
		if(Valid == 0){
			alert("Invalid BIC");
			$("#" + BICid).val("");
			if (Bankid != "" ){
				BANK = $(op).find("BANK").html();
				$("#" + Bankid).val("");
			}
			return false;
		}
		else{
			if (Bankid != "" ){
				BANK = $(op).find("BANK").html();
				$("#" + Bankid).val(BANK);
			}
	
		}

	} 
	$("#" + BICid).val(BnkBIC);
	return true;
}