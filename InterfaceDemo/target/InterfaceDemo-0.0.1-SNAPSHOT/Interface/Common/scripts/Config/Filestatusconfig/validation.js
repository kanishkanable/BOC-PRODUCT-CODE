function WhereConditiondrop()
{  var x = document.getElementById("lstRight");
var listval = "<Data>";
for (var i = 0; i < x.options.length; i++) {
   // listval = listval + "<Data>";
    listval = listval + "<value>";
    listval = listval + x.options[i].value;
    listval = listval + "</value>";
  //  listval = listval + "</Data>";
}
listval = listval + "</Data>";
//$('#FromLBVal').val(listval);
	 var str_xml = listval;
     var parser_xml = new DOMParser();
     var oXml = parser_xml.parseFromString(str_xml, "text/xml");
     var obj_elementcol = oXml.documentElement;
$('#WrConditionClm').empty();
     for (var i = 0; i < obj_elementcol.getElementsByTagName('value').length; i++) {

         var option = document.createElement('option');

           var item = obj_elementcol.getElementsByTagName('value')[i].textContent;
//
          option.value =$.trim(item);
         option.innerHTML = item;   
        
        
             document.getElementById("WrConditionClm").appendChild(option);
             $("#WrConditionClm option[value='" + $("#HWrConditionClm").val()+ "']").attr("selected", "true");
             
           }
   
	}

function clearQueue()
{
	//document.getElementById('QueueName').value='';
	document.getElementById('TableName').value='';
	//document.getElementById('lstRight').value='';
	$('#lstRight').empty();
	$('#lstLeft').empty();
	$('#WrConditionClm').empty();
	}
function clr()
{
	$('#lstRight').empty();
	$('#WrConditionClm').empty();
}


function SelectedDropVal(id){
	//var val=$("#H"id).val();
	var x=$("#"+id+" option:selected").val()
	 $("#H"+id).val(x)

	}





//function transactiontype(responseText) {

//var billtype = document.getElementById('INVE_TYPE').value;
//if (billtype == 'VCN') {
//  document.getElementById('INVE_TRANS_TYPE').value = 'Cr'
//}
//else if (billtype == 'VIN' || billtype == 'VDN') {
//  document.getElementById('INVE_TRANS_TYPE').value = 'Dr'
//}
//else {
//  document.getElementById('INVE_TRANS_TYPE').value = ''
//}
//}


function hideshow() {

if (document.getElementById("Menulevel").value == "0") {
  $(".level0").hide();
 
}
else if (document.getElementById("Menulevel").value == "1") {
  $(".levelhide").hide();
  $(".levelshow2").show();
 
}
else {
  $(".level0").show();

}

}



function selecthide() {
if (document.getElementById("Menulevel").value == "Select") {
$(".level0").hide();

}
}



function hideshowDel() {

if (document.getElementById("DelMenulevel").value == "0") {
  $(".level0").hide();

}
else if (document.getElementById("DelMenulevel").value == "1") {
  $(".levelhide").hide();
  $(".levelshow2").show();

}
else {
  $(".level0").show();

}

}
function selecthideDel() {
if (document.getElementById("DelMenulevel").value == "Select") {
  $(".level0").hide();

}
}

//function everyone() {
//if (document.getElementById("Menulevel").value == "0") {
//
//

//}
//else {

// alert('Everyone for only Level0')
//}

//}
function Clearfields() {



if (document.getElementById("Menulevel").value == "Select") {
  $('#menulookup').hide();
  $('#Rightslookup').hide();


}
else {

  $('#menulookup').show();
  $('#Rightslookup').show();

}
  $.each($('#content').find($('input[type="text"]')), function () {
      $(this).val("");
  });


$('#Level').val('');
$('#AssignForAllSubLevels').val('');
$('.ClrTXT').val('');


}


function userlookup() {
if (document.getElementById("DelLevel").value == "Select") {
  $('#RightslookupDel').hide();



}
else {

  $('#RightslookupDel').show();


}


}

function ClearfieldsDel() {



if (document.getElementById("DelMenulevel").value == "Select") {
  $('#menulookupDel').hide();
 


}
else {

  $('#menulookupDel').show();
  

}
$.each($('#Delcontent').find($('input[type="text"]')), function () {
  $(this).val("");
});


$('#DelLevel').val('');

$('.ClrTXTDel').val('');


}










function ReorderFields(actionType, listBoxCtrl) {
if (actionType == 'up') {
  ListBoxMoveUp(listBoxCtrl);
}
else if (actionType == 'down') {
  ListBoxMoveDown(listBoxCtrl);
}
}

function ListBoxMoveDown(ctrl) {
var selectedDx = $("#" + ctrl).find("option:selected");
var next = selectedDx.last().next();

selectedDx.each(function () {
  $(this).insertAfter(next);
});
}

function ListBoxMoveUp(ctrl) {
var selectedDx = $("#" + ctrl).find("option:selected");
var prev = selectedDx.first().prev();

selectedDx.each(function () {
  $(this).insertBefore(prev);
});
}
$(document).on('click', '#INVE_CHECK', function () {
if (this.checked) {

  $(this).val('Yes');
  
}
else {
$(this).val('No');
  
}



});

function clrtext() {

$('#AssignRights').val('');


}

function clrtextDel() {

$('#RightsWFName').val('');
$('#RightsReviewName').val('');
$('#RightsType').val('');
$('#Rights').val('');

}

$(document).on('click', '#Save', function(){
 // $(document).on('click', '#Table1 tbody tr', function () {
	   
	   var data1="";
	   if($("input:checked" ).length == 0)
{
//alertify.alert("Please Select a File");
alertify.alert(LoadFrmXML("V0110"));
//$("input:checked" ).prop( "checked", false );
return;
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


$(document).on('click', '#QueRightsDelete', function () {



if($("#Table1").parents().find('.block').find('article :visible').find('.AssignDetails').html() != undefined)
{
var WFName =  $('#WFID').val(); //$(this).closest('tr').find('td')[1].innerHTML;
var QId = $('#Queue-ID').val();     //$(this).closest('tr').find('td')[2].innerHTML;
var RghtType = $(this).closest('tr').find('td')[1].innerHTML;
var Rights = $(this).closest('tr').find('td')[2].innerHTML;
var spname='Sam_squeuerightdelt';

/*
if (confirm("Are you Sure??") == true) 
{
var Deletedata= UI_getdata(WFName,QId,RghtType,Rights,"",spname);
$(this).closest('tr').remove();
$("#AsgnQuegrid").trigger("click");

}*/

alertify.confirm("Are you sure?", function (e) {
  if (e) 
	{	
	    
		var Deletedata= UI_getdata(WFName,QId,RghtType,Rights,"",spname);
		//$(this).closest('tr').remove();
		$("#AsgnQuegrid").trigger("click");
  } 
	else 
	{
      return;
  }
});



}


else
{

var WFName =  $('#WFID').val(); //$(this).closest('tr').find('td')[1].innerHTML;
var QId = $('#Queue-ID').val();     //$(this).closest('tr').find('td')[2].innerHTML;
var RghtType = $(this).closest('tr').find('td')[1].innerHTML;
var Rights = $(this).closest('tr').find('td')[2].innerHTML;
var spname='Sam_squeuerightdelt';


alertify.confirm("Are you sure?", function (e) {
  if (e) 
	{	
	    
		var Deletedata= UI_getdata(WFName,QId,RghtType,Rights,"",spname);
		//$(this).closest('tr').remove();
		$("#AsgnQuegrid").trigger("click");
  } 
	else 
	{
      return;
  }
});


}


});			



$(document).on('click', '#QueRightslevelDelete', function () {



if($("#Table1").parents().find('.block').find('article :visible').find('.AssignDetails').html() != undefined)
{

var param1 =  $('#RightsWFName').val(); //$(this).closest('tr').find('td')[1].innerHTML;
var param2 = $('#RightsReviewName').val();     //$(this).closest('tr').find('td')[2].innerHTML;
var param3 = $(this).closest('tr').find('td')[1].innerHTML;
var param4 = $(this).closest('tr').find('td')[2].innerHTML;
var param5='';
var spname=' Sam_sFileRightsDel';

/*
if (confirm("Are you Sure??") == true) 
{

var Deletedata= UI_getdata(WFName,QId,RghtType,Rights,"",spname);

$(this).closest('tr').remove();
$("#QueRgtsviewlevel").trigger("click");

}*/
alertify.confirm("Are you sure?", function (e) {
  if (e) 
	{	
	    
		var Deletedata= UI_getdata(param1,param2,param3,param4,"",spname);
		//$(this).closest('tr').remove();
		$("#QueRgtsviewlevel").trigger("click");
  } 
	else 
	{
      return;
  }
});


}


else
{

var WFName =  $('#WFID').val(); //$(this).closest('tr').find('td')[1].innerHTML;
var QId = $('#Queue-ID').val();     //$(this).closest('tr').find('td')[2].innerHTML;
var RghtType = $(this).closest('tr').find('td')[1].innerHTML;
var Rights = $(this).closest('tr').find('td')[2].innerHTML;
var spname='Sam_squeuerightdelt';


alertify.confirm("Are you sure?", function (e) {
  if (e) 
	{	
	    
		var Deletedata= UI_getdata(WFName,QId,RghtType,Rights,"",spname);
		//$(this).closest('tr').remove();
		$("#QueRgtsviewlevel").trigger("click");
  } 
	else 
	{
      return;
  }
});


}


});

function Queuegridview()
{
$("#Table2_wrapper").hide();
$("#QueRgtsviewlevel").trigger("click");
}


function ChkValues(data1)
{

   
        var chkmndtry = ChkMandatoryFields("RightsMndtry");


        if (chkmndtry == "No") {
            return;
        }

        //Check Grid MAndatory Fields
        // END Check Grid MAndatory Fields

//        if (document.getElementById("AssignRights").value == "Everyone" && document.getElementById("AssignForAllSubLevels").value != "Yes") {

//            alert('Choose "Yes", In Assign For All SubLevels');
//            return;

//        }



        var Stage = "ENTRY"
		
		var id="";

for(var i=0;i<data1.split('|').length;i++)
{
	
	if(data1.split('|')[i].split(',')[1]==undefined)
	{
		
		var error='';
		
	}
	else{
		 id += data1.split('|')[i].split(',')[1]+"|";
	}
	
	
}


        // DateChange("ISDatefield");

		

		$("#Rights").val(id.slice(0,-1));
         
        var setrightsid=document.getElementById('Rights').value;
	 
        //var FORMXML = submitdata("ISDBfields");
	 
		 //var Code ='Assign';
		 
		// var param3:'';
		 
       

		 var param1=$('#RightsWFName').val();
		   	var param2=$('#RightsReviewName').val();
		   	var param3= submitdata("ISDBfields");
		   	var param4="Assign";
		   	var param5="";
		var spname='SAM_sFileConfigRights';
		 $.ajax({
		        url: "/ThemePro_LSW/UI_GetData",
		        data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
		        async: false,
           // dataType: "json",
            type: 'POST',
            success: function OnSuccess_submit(data) {
				
				
				
				alertify.alert(LoadFrmXML("V0106"));

				//$("#Table3_wrapper").hide();
			
				$("#QueRgtsviewlevel").trigger("click");
				

                 /* $('.TXTFIELDSCLEAR').find('input:text').each(

            function () {
                $('input:text[id=' + $(this).attr('id') + ']').val('');
                $('#Menulevel').val('');
                Clearfields();
               
            }

        );*/
		
				//document.getElementById('RightsWFName').value=''
				//document.getElementById('RightsReviewName').value=''
		 var levelselect = document.getElementById('RightsType').value

                    if (levelselect != '') {

                        document.getElementById('RightsType').value = ''

                    }
					 
                else {
                    //alertify.alert(data);
					alertify.alert(LoadFrmXML("V0106"));

                }

            },
            error: function OnError_submit(xmlRequest) {
			alertify.alert(LoadFrmXML("V0075"));

            }

        });

		



}


/*function clr()
{
	$('#lstRight').empty();
	$('#WrConditionClm').empty();
	//document.getElementById('Icon').value='';
}*/
function clrscr()
{
	$('#lstRight').empty();
	$('#WrConditionClm').empty();
	$('#lstLeft').empty();
	document.getElementById('TableName').value='';
	document.getElementById('Icon').value='';
	document.getElementById('FunctionName').value='';

}



















