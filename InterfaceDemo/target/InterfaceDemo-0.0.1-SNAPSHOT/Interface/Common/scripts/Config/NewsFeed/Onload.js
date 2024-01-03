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
    FetchNewsFeed()
   


    $('#save').click(function () { 
	
	
	

        
           var attch = document.getElementById("NewsFeedArea").value;
		   
		    var iconname = document.getElementById("NewsFeedIcon").value;
		   
		   
     //  	var dropdn = document.getElementById("Mndtry").value;
        if(attch != "")
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alert(LoadFrmXML("V0128"));
	    	return
        }
    	getListValToDB('NewsFeed','FromLBVal');
      
    	
    	 var chkmndtry = $('#FromLBVal').val();


         if (chkmndtry == "") {
      	   alert(LoadFrmXML("V0078"));
      	   
              return;
          }
    	
            var FORMXML = submitdata("ISDBfields");
            var param = "New";
            var param3 =iconname;
            var param4 = "";
            var param5 = "";
            //alert(FORMXML)
           var spname = "Sam_sInsNewsFeed";
      $.ajax({
            	
                url: "ThemeproLO/UI_GetData",
                data: { param1: FORMXML, param2: param, param3: param3,param4: param4,param5: param5,spname: spname },
                async:false,
           type:"Post",
                success : function OnSuccess_submit(Result) {
                	
                	//alert(Result);
                	if($(Result).text()=='Success'){
                       
                         alert(LoadFrmXML("V0108"));
                        Newclearscreen();
                        FetchNewsFeed()
                    }
                    else {
                        // DateChangeBack("ISDatefield");
                        alert(LoadFrmXML("V0074")); 
                        Newclearscreen();
                    }
                },
                error: function OnError_submit(xmlRequest) {
                    //DateChangeBack("ISDatefield");
                    alert(LoadFrmXML("V0075"));

                }
            });                
    
    });
    
    
    
    
    
  /*  $('#Update').click(function () { 
       
     var attch = document.getElementById("EditNewsFeedArea").value;
 //	var dropdn = document.getElementById("EditMndtry").value;
     if(attch != "" )
	    {
    	
		 alert(LoadFrmXML("V0128"));
	    	return
     }
    document.getElementById("FromLBVal").value = "";
    
	getListValToDB('EditNewsFeed','EditFromLBVal');
	
	 var chkmndtry = $('#EditFromLBVal').val();


     if (chkmndtry == "") {
  	   alert(LoadFrmXML("V0078"));
  	   
          return;
      }
	
	    var FORMXML = submitdata("ISDBfields");
        var param = "Update";
      var spname = "Sam_sNewsFeed";
        var param3 = "";
        var param4 = "";
        var param5 = "";
        $.ajax({
        	
        	 url: "ThemeproLO/UI_GetData",
             data: { param1: FORMXML, param2: param, param3: param3,param4: param4,param5: param5,spname: spname },
             async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
                if ($(Result).text() == 'Success') {
                   
                	alert(LoadFrmXML("V0123"));
					//alert(LoadFrmXML("V0128"));
                    editclearscreen();
                    FetchNewsFeed();
                }
                else {
                    // DateChangeBack("ISDatefield");
					alert(LoadFrmXML("V0162"));
					editclearscreen();
                	//alert("Already Exsist");           
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
            	
				alert(LoadFrmXML("V0075"));

            }
        });                         

});
    */
    
    $('#Deletebtn').click(function () { 
       
    document.getElementById("FromLBVal").value = "";
  
 
	
	
	
	
	getListValToDB('NewsFeed','FromLBVal');
	 var chkmndtry = $('#FromLBVal').val();


     if (chkmndtry == "") {
  	   alert(LoadFrmXML("V0078"));
  	   
          return;
      }
	  
	  
	  alertify.confirm("This will Delete the Entire News Feed" , function (e) {
    if (e) {
	
	 var FORMXML = submitdata("ISDBfields");
     var param = "Delete";
   var spname = "Sam_sInsNewsFeed";
     var param3 = "";
     var param4 = "";
     var param5 = "";
     $.ajax({
     	
     	 url: "ThemeproLO/UI_GetData",
          data: { param1: FORMXML, param2: param, param3: param3,param4: param4,param5: param5,spname: spname },
         
            async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
            	if ($(Result).text() == 'Success') {
                   
                	
						alert(LoadFrmXML("V0124"));
                    Newclearscreen();
                    
                }
                else {
                    // DateChangeBack("ISDatefield");
                	//alert("Please Choose a file");  
				alertify.alert(LoadFrmXML("V0117"));					
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
				alert(LoadFrmXML("V0075"));
            	

            }
        });  

		}
		else{
		return;
		}
		});

});
    
    $("#Qup").on("click", function () {
        listbox_move('EditNewsFeed', 'up');
        //alert("ok");
    });
    $("#Qdown").on("click", function () {
        listbox_move('EditNewsFeed', 'down');
        //alert("ok");
    });
    $("#up").on("click", function () {
        listbox_move('NewsFeed', 'up');
        //alert("ok");
    });
    $("#down").on("click", function () {
        listbox_move('NewsFeed', 'down');
        //alert("ok");
    });
   
   
  
});








function Newclearscreen()
{
	document.getElementById("NewsFeedArea").value = "";
	

	$('#NewsFeed').empty();
	
}
function editclearscreen()
{
	document.getElementById("EditNewsFeedArea").value = "";
	//document.getElementById("EditNewsFeed").value = "";
	
	$('#EditNewsFeed').empty();
	//$('#EditClmNameRight').empty();
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
    url: "ThemeproLO/UI_FetchHistTblClmNames",
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
            if(item.split("_")[1]=="PrcsID"){
            	document.getElementById(fieldid2).appendChild(option);
            	}
          //var PrcsID=item.split('_')[1]
            else{
                document.getElementById(fieldid1).appendChild(option);
            }
        }
    }
})
}
 




function listbox_move(listID, direction) {

    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;

    if (-1 == selIndex) {
    	//alert("Please select an option to move.");
		alert(LoadFrmXML("V0129"));
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
    url: "ThemeproLO/UI_FetchHistTblClmNames",
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
		 url: "ThemeproLO/UI_GetData",
       // url: "ThemeproLO/UI_FetchInsertedTblColName",
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
