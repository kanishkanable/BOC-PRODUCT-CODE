$(document).ready(function () {

 var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= IOP;	
			return;
			}
			
    readonly();

    $(".NewTab").on("click", function () {

    	
    	Newclearscreen();
        $(".ShowNewMenu").show();
        $(".ShowEdit").hide();
      

    });
    
    
    $(".Edit").on("click", function () {
    	 $(".ShowNewMenu").hide();
         $(".ShowEdit").show();
       
         editclearscreen();
    });
    
   


    $('#save').click(function () { 
	
	
	if ($('#lstLeft').has('option').length>=1)
	{
	$('#lstLeft').removeClass("NewMndtry");
	}

          var chkmndtry = ChkMandatoryFields("NewMndtry");


           if (chkmndtry == "No") {
                return;
            }
           var attch = document.getElementById("list").value;
       	var dropdn = document.getElementById("Mndtry").value;
        if(attch != "" || dropdn!="" )
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0128"));
	    	return
        }
    	getListValToDB('lstLeft','FromLBVal');
       var x= getListValToDB('ClmNameRight','ColumnNames');
       if (x == "No") {
    		alertify.alert(LoadFrmXML("V0159"));
           return;
       }
            var FORMXML = submitdata("ISDBfields");
            var param = "New";
            var spname = "SAM_sINSAttchConfig";
      $.ajax({
            	
                url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
                data: { xml: FORMXML, param: param, spname: spname },
                async:false,
           type:"Post",
                success : function OnSuccess_submit(Result) {
                	
                	//alert(Result);
                    if (Result == 'Success') {
                       
                         alertify.alert(LoadFrmXML("V0108"));
                        Newclearscreen();
                    
                    }
                    else {
                        // DateChangeBack("ISDatefield");
                        alertify.alert(LoadFrmXML("V0074"));          
                    }
                },
                error: function OnError_submit(xmlRequest) {
                    //DateChangeBack("ISDatefield");
                    alertify.alert(LoadFrmXML("V0075"));

                }
            });                
    
    });
    
    
    
    
    
    $('#Update').click(function () { 
        var chkmndtry = ChkMandatoryFields("EditMndtry");


     if (chkmndtry == "No") {
          return;
        }
     var attch = document.getElementById("Editlist").value;
 	var dropdn = document.getElementById("EditMndtry").value;
     if(attch != "" || dropdn!="" )
	    {
    	
		 alertify.alert(LoadFrmXML("V0128"));
	    	return
     }
    document.getElementById("FromLBVal").value = "";
    
	getListValToDB('EditlstLeft','EditFromLBVal');
	var value=getListValToDB('EditClmNameRight','EditColumnNames');
	if (value == "No") {
		alertify.alert(LoadFrmXML("V0159"));
		//alert("Pls select prcsID")
        return;
      }
        var FORMXML = submitdata("ISDBfields");
        var param = "Update";
        var spname = "SAM_sINSAttchConfig";
        
        $.ajax({
        	
            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param, spname: spname },
            async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
                if (Result == 'Success') {
                   
                	alertify.alert(LoadFrmXML("V0123"));
					//alertify.alert(LoadFrmXML("V0128"));
                    editclearscreen();
                }
                else {
                    // DateChangeBack("ISDatefield");
					alertify.alert(LoadFrmXML("V0121"));
                	//alertify.alert("Already Exsist");           
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
            	
				alertify.alert(LoadFrmXML("V0075"));

            }
        });                         

});
    
    
    $('#Deletebtn').click(function () { 
        var chkmndtry = ChkMandatoryFields("EditMndtry");


     if (chkmndtry == "No") {
          return;
        }
    document.getElementById("FromLBVal").value = "";
   var wfname = $("#Editname").val();
	var formname = $("#EditFormID").val();
	
 
	
	
	alertify.confirm("This will Delete the Entire  "+"'"+wfname+"'" + " "+"'"+formname+"'" +"  "+"Data" , function (e) {
    if (e) {
	
	
	getListValToDB('EditlstLeft','EditFromLBVal');
		
        var FORMXML = submitdata("ISDBfields");
        var param = "Delete";
        var spname = "SAM_sINSAttchConfig";
        
        $.ajax({
        	
            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param, spname: spname },
            async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
                if (Result == 'Success') {
                   
                	
						alertify.alert(LoadFrmXML("V0124"));
                    editclearscreen();
                }
                else {
                    // DateChangeBack("ISDatefield");
                	//alertify.alert("Please Choose a file");  
				alertify.alert(LoadFrmXML("V0117"));					
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
				alertify.alert(LoadFrmXML("V0075"));
            	

            }
        });  

		}
		else{
		return;
		}
		});

});
    
    $("#Qup").on("click", function () {
        listbox_move('EditlstLeft', 'up');
        //alert("ok");
    });
    $("#Qdown").on("click", function () {
        listbox_move('EditlstLeft', 'down');
        //alert("ok");
    });
    $("#up").on("click", function () {
        listbox_move('lstLeft', 'up');
        //alert("ok");
    });
    $("#down").on("click", function () {
        listbox_move('lstLeft', 'down');
        //alert("ok");
    });
    $("#Attchup").on("click", function () {
        listbox_move('ClmNameRight', 'up');
        //alert("ok");
    });
    $("#Attchdown").on("click", function () {
        listbox_move('ClmNameRight', 'down');
        //alert("ok");
    });
    $("#EditQup").on("click", function () {
        listbox_move('EditClmNameRight', 'up');
        //alert("ok");
    });
    $("#EditQdown").on("click", function () {
        listbox_move('EditClmNameRight', 'down');
        //alert("ok");
    });
    
    $("#Qleft").bind("click", function () {
        var options = $("[id*=ClmNameRight] option:selected");
		
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
			
			
            $("[id*=ClmNameLeft]").append(opt);
			//$("[id*=WrConditionClmI]").append($('#ClmNameRight option'))
			$("#WrConditionClm").empty()
			
			var $options = $("#ClmNameRight > option").clone();
    $("[id*=WrConditionClm]").append($options);
	
			

			$("#WrConditionClm option[value='" + $("#HWrConditionClm").val()+ "']").attr("selected", "true");
	     }
		//getListValToDB('ClmNameRight','ColumnNames');
    });
    $("#Qright").bind("click", function () {
        var options = $("[id*=ClmNameLeft] option:selected");
        for (var i = 0; i < options.length; i++) {
        	if($('#ClmNameRight option').length<4){
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=ClmNameRight]").append(opt);
			
        	}
        	else
        		{
        		//alertify.alert("Only Eight columns are Allowed")
					alertify.alert(LoadFrmXML("V0160"));
        		}
				
				$("#WrConditionClm").empty()
				
			var $options = $("#ClmNameRight > option").clone();
    $("[id*=WrConditionClm]").append($options);
			$("#WrConditionClm option[value='" + $("#HWrConditionClm").val()+ "']").attr("selected", "true");
	       }
		//getListValToDB('ClmNameRight','ColumnNames');
    });
       

    
    
    
    $("#EditQleft").bind("click", function () {
        var options = $("[id*=EditClmNameRight] option:selected");
		
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
			
			
            $("[id*=EditClmNameLeft]").append(opt);
			//$("[id*=WrConditionClmI]").append($('#ClmNameRight option'))
			$("#EditWrConditionClm").empty()
			
			var $options = $("#EditClmNameRight > option").clone();
    $("[id*=EditWrConditionClm]").append($options);
	
			
  
			$("#EditWrConditionClm option[value='" + $("#HEditWrConditionClm").val()+ "']").attr("selected", "true");
	     }
		//getListValToDB('ClmNameRight','ColumnNames');
    });
    $("#EditQright").bind("click", function () {
        var options = $("[id*=EditClmNameLeft] option:selected");
        for (var i = 0; i < options.length; i++) {
        	if($('#EditClmNameRight option').length<4){
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=EditClmNameRight]").append(opt);
			
        	}
        	else
        		{
        		//alertify.alert("Only Eight columns are Allowed")
					window.alert(LoadFrmXML("V0160"));
        		}
				
				$("#EditWrConditionClm").empty()
				
			var $options = $("#EditClmNameRight > option").clone();
    $("[id*=EditWrConditionClm]").append($options);
			$("#EditWrConditionClm option[value='" + $("#HEditWrConditionClm").val()+ "']").attr("selected", "true");
	       }
		//getListValToDB('ClmNameRight','ColumnNames');
    });
});
/***************** New Page******************/
function addlistval(){
	
	var attch = document.getElementById("list").value;
	var dropdn = document.getElementById("Mndtry").value;
	var FormID=document.getElementById("FormID").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("lstLeft");
	 var option = document.createElement("OPTION");
	   
	    
	    if(attch == "" || dropdn=="" || FormID=="")
	    {
	    	//alertify.alert('Fill Attachment type and Mandatory condition');
			alertify.alert(LoadFrmXML("V0128"));
        }
	    else if (jQuery("#lstLeft").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=lstLeft]").append(option);
     	   document.getElementById("list").value="";
  	       document.getElementById("Mndtry").value="";
        }
        
        else
        {
        	for (var i = 0; i < listBox.options.length; i++) 
        	{
        		if (listBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			
					alertify.alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=lstLeft]").append(option); 
        			document.getElementById("list").value="";
        		    document.getElementById("Mndtry").value="";
        		    return;  
        	}     
        

	
/*
	var attch = document.getElementById("list").value;
	var dropdn = document.getElementById("Mndtry").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("<%= lstLeft.ClientID%>");
	 var option = document.createElement("OPTION");
	    option.innerHTML = res;
	    option.value = res;
	    $("[id*=lstLeft]").append(option);
	    
	    document.getElementById("list").value="";
	    document.getElementById("Mndtry").value="";
	*/
	
	 
	
}
function Editlistval()
{
	var list = document.getElementById('lstLeft');
	 
	    var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
				alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	var spldata= attchval.split('|');
	     
	     	var data1= $(spldata)[0];
	     	var data2= $(spldata)[1];
	     
	     	document.getElementById("list").value=data1;
	     	document.getElementById("Mndtry").value=data2;
	list.remove(list.selectedIndex);

	} 
	 
}

function Deletelistval()
{
	var selectbox = document.getElementById('lstLeft');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
			alertify.alert(LoadFrmXML("V0129"));
	    	//alertify.alert("Please select an option to move.");
	        return;
	    }
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
	if(selectbox.options[i].selected)
		
	selectbox.remove(i);
	
	}
	 
 
}

/***************** Edit Page******************/

function Editaddlistval(){
	
	var attch = document.getElementById("Editlist").value;
	var dropdn = document.getElementById("EditMndtry").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("EditlstLeft");
	 var option = document.createElement("OPTION");
	   
	    
	  if(attch == "" || dropdn=="" )
	    {
		  //alertify.alert('Fill Attachment type and Mandatory condition');
		  alertify.alert(LoadFrmXML("V0128"));
      }
	    else if (jQuery("#EditlstLeft").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=EditlstLeft]").append(option);
     	   document.getElementById("Editlist").value="";
  	       document.getElementById("EditMndtry").value="";
        }
        
        else
        {
        	for (var i = 0; i < listBox.options.length; i++) 
        	{
        		if (listBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			//alertify.alert('Already here'); 
					alertify.alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=EditlstLeft]").append(option); 
        			document.getElementById("Editlist").value="";
        		    document.getElementById("EditMndtry").value="";
        		    return;  
        	}  
}
function Editpgeditlistval()
{
	var list = document.getElementById('EditlstLeft');
	 var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
			alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	var spldata= attchval.split('|');
	     
	     	var data1= $(spldata)[0];
	     	var data2= $(spldata)[1];
	     
	     	document.getElementById("Editlist").value=data1;
	     	document.getElementById("EditMndtry").value=data2;
	list.remove(list.selectedIndex);

	} 
}

function Editdeletelistval()
{
	var selectbox = document.getElementById('EditlstLeft');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
			alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
		if(selectbox.options[i].selected)
		
			selectbox.remove(i);
	
	}
    
    
}

/*****************************************/



function Newclearscreen()
{
	document.getElementById("name").value = "";
	document.getElementById("packageId").value = "";
	document.getElementById("FormID").value = "";
	document.getElementById("list").value = "";
	document.getElementById("Mndtry").value = "";
	document.getElementById("FromLBVal").value = "";
	document.getElementById("TableName").value = "";
	document.getElementById("WrConditionClm").value = "";
	document.getElementById("HWrConditionClm").value = "";
	document.getElementById("ColumnNames").value = "";

	$('#ClmNameLeft').empty();
	$('#ClmNameRight').empty();
	$('#lstLeft > option').remove();
}
function editclearscreen()
{
	document.getElementById("Editname").value = "";
	document.getElementById("EditpackageId").value = "";
	document.getElementById("EditFormID").value = "";
	document.getElementById("Editlist").value = "";
	document.getElementById("EditMndtry").value = "";
	document.getElementById("EditFromLBVal").value = "";
	$('#EditlstLeft > option').remove();
	
	document.getElementById("EditTableName").value = "";
	document.getElementById("EditWrConditionClm").value = "";
	document.getElementById("HEditWrConditionClm").value = "";
	document.getElementById("EditColumnNames").value = "";

	$('#EditClmNameLeft').empty();
	$('#EditClmNameRight').empty();
	}

function getListValToDB(FromID,ToID)
{
	

var x = document.getElementById(FromID);
var listval = "";
for (var i = 0; i < x.options.length; i++) {
    listval = listval + "<Data>";
    listval = listval + "<value>";

  listval = listval + x.options[i].value;
 


    listval = listval + "</value>";
    listval = listval + "</Data>";
}
document.getElementById(ToID).value = listval;
var option="";
if(FromID=="ClmNameRight" && ToID=="ColumnNames" || FromID=="EditClmNameRight" && ToID=="EditColumnNames"){
	for (var i = 0; i < x.options.length; i++) {
	  var str = x.options[i].value;
	  var res = str.split("_")[1];
	  var Matchvalue='PrcsID';
	
	  
		if (res=="PrcsID"){
			  option=option+res;
		}

	}
	if(option==""){
		
		return "No";
	}
}
}
 

function fillHistTblClmNames(param1,param2,param3,param4,param5,spname,fieldid1,fieldid2)
{
$('#'+fieldid1).empty();

$('#'+fieldid2).empty();
$.ajax({
    url: "/ThemePro_LSW/UI_FetchHistTblClmNames",
    data: {param1:param1,param2:param2,param3:param3, param4:param4, param5:param5,spname:spname },
    async: false,
    //dataType: "json",
    type: 'POST',
    success: function (data) {

     
        var str_xml = data;
        var parser_xml = new DOMParser();
        var oXml = parser_xml.parseFromString(str_xml, "text/xml");
        var obj_elementcol = oXml.documentElement;

        for (var i = 0; i < obj_elementcol.getElementsByTagName('row').length; i++) {

            var option = document.createElement('option');

              var item = obj_elementcol.getElementsByTagName('row')[i].textContent;

             option.value =$.trim(item);
            option.innerHTML = item;   
                     //var PrcsID=item.split('_')[1]
           
                document.getElementById(fieldid1).appendChild(option);
            
        }
    }
})
}
 




function listbox_move(listID, direction) {

    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;

    if (-1 == selIndex) {
    	//alertify.alert("Please select an option to move.");
		alertify.alert(LoadFrmXML("V0129"));
        return;
    }

    var increment = -1;
    if (direction == 'up')
        increment = -1;
    else
        increment = 1;

    if ((selIndex + increment) < 0 ||
        (selIndex + increment) > (listbox.options.length - 1)) {
        return;
    }

    var selValue = listbox.options[selIndex].value;
    var selText = listbox.options[selIndex].text;
    listbox.options[selIndex].value = listbox.options[selIndex + increment].value
    listbox.options[selIndex].text = listbox.options[selIndex + increment].text

    listbox.options[selIndex + increment].value = selValue;
    listbox.options[selIndex + increment].text = selText;

    listbox.selectedIndex = selIndex + increment;
}
/*
function fillHistTblClmNames(param1,param2,param3,param4,param5,spname,fieldid)
{
	$('#'+fieldid).empty();
	$.ajax({
    url: "/ThemePro_LSW/UI_FetchHistTblClmNames",
    data: {param1:param1,param2:param2,param3:param3, param4:param4, param5:param5,spname:spname },
    async: false,
    //dataType: "json",
    type: 'POST',
    success: function (data) {

     
        var str_xml = data;
        var parser_xml = new DOMParser();
        var oXml = parser_xml.parseFromString(str_xml, "text/xml");
        var obj_elementcol = oXml.documentElement;

        for (var i = 0; i < obj_elementcol.getElementsByTagName('row').length; i++) {

            var option = document.createElement('option');

              var item = obj_elementcol.getElementsByTagName('row')[i].textContent;

             option.value =$.trim(item);
            option.innerHTML = item;   
           
           
                document.getElementById(fieldid).appendChild(option);
        }
    }
	})
}
*/
function SelectedDropVal(id){
	//var val=$("#H"id).val();
	var x=$("#"+id+" option:selected").val()
	 $("#H"+id).val(x)

	}

function FetchInsTblColName()
{
	var WFName=$('#Editname').val();
	var QueueName=$('#EditpackageId').val();
	var FormId=$('#EditFormID').val();
	var TableName=$('#EditTableName').val();
	//var param4='';
	var param5='';
	spname='Sam_sAttchPageInsDETLClmName'
	$.ajax({
		 url: "/ThemePro_LSW/UI_GetData",
       // url: "/ThemePro_LSW/UI_FetchInsertedTblColName",
        data: { param1:WFName,param2:QueueName,param3:FormId,param4:TableName,param5:param5,spname:spname },
        async: false,
        //dataType: "json",
        type: 'POST',
        success: function (data) {
        	 var str_xml = $(data).find('option');
			  $("#EditClmNameRight").empty();
			   $("#EditWrConditionClm").empty();
        	 $("#EditClmNameRight").append(str_xml);
			   var options= $($("#EditClmNameRight").find('option')).clone()
    $("#EditWrConditionClm").append(options);
   var x=$("#EditWrConditionClm option:selected").val()
   $("#HEditWrConditionClm").val(x)

        }
    });
	}
