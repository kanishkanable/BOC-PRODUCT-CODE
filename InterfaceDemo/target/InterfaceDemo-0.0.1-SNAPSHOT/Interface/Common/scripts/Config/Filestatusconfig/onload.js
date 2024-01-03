$(document).ready(function () {
	
	 $('.find3').hide();
	 $('.find6').hide();

	 
	 
	 $(".NewDetails").show();
	 
	 $(".AssignDetails").hide();
	 
	    $(".NewTab").on("click", function () {
 $('.find6').hide();

	        //$(".rearrhide").show();
	        $(".AssignDetails").hide();
	        $(".NewDetails").show();
	       // $('#New_MenuName').removeClass('ISDBMndtry');
	        $('.TXTFIELDSCLEAR').find('input:text').each(

	                function () {
	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
	                }
	            );
	    });
	    $(".EditTab").on("click", function () {
$("#Table2_wrapper").hide();
	        $(".NewDetails").hide();
	        $(".AssignDetails").show();	     
	        $('.TXTFIELDSCLEAR').find('input:text').each(

	                function () {
	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
	                }

	            );

	    });
	 
	 
    //start
    $("#up").on("click", function () {
        listbox_move('lstRight', 'up');
        //alert("ok");
    });
    $("#down").on("click", function () {
        listbox_move('lstRight', 'down');
        //alert("ok");
    });
    //end
    $("#left").bind("click", function () {
        var options = $("[id*=lstRight] option:selected");
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=lstLeft]").append(opt);
            WhereConditiondrop();
        }
    });
    $("#right").bind("click", function () {
        var options = $("[id*=lstLeft] option:selected");
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=lstRight]").append(opt);
            WhereConditiondrop();
        }
    });


    $('#RearrSubmit').click(function () {
   	
     
        //listbox_selectall('RemenuName', true);
        getValue();
        //var XmlTxt = getValue();
        var FromLBVal = document.getElementById('FromLBVal').value;
        var chkmndtry = ChkMandatoryFields("Mndtry");


        if (chkmndtry == "No") {
            return;
        }
        var xml = submitdata("ISDBfields");
        

        var param='New';
        var spname='sam_sfileconfig';
          // alert(XmlTxt);
           $.ajax({
               url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
               data: { xml: xml,Param:param,spname:spname },
               async: false,
              // dataType: "json",
               type: 'POST',
               success: function OnSuccess_submit(data) {
                   if (data == 'Success') {

                	//alert('data saved');
              	
                	   alertify.alert(LoadFrmXML("V0108"));
                 
                   }
                   
                   else if (data == 'Update') {

                   	//alert('data saved');
                 	
                   	   alertify.alert(LoadFrmXML("V0123"));
                    
                      }
              
                   document.getElementById('WFID').value = '';
               
	                document.getElementById('WFName').value = '';
                   document.getElementById('ReviewName').value = '';
                   document.getElementById('FunctionName').value = '';
                   document.getElementById('TableName').value = '';
                
                   document.getElementById('FromLBVal').value = '';
                   document.getElementById('Icon').value = '';
                   document.getElementById('WrConditionClm').value = '';
               	$('#lstRight').empty();
              	$('#lstLeft').empty();
	$('.find6').hide();
               },
           
               
       });
    	
        
       
    });
    
    
   


    
});
function theme()
{
	
    
	 var param1=$('#WFName').val();
	   	var param2="";
	   	var param3="";
	   	var param4="";
	   	var param5="";
	var spname='SAM_sreviewflowsse';
	 $.ajax({
	        url: "/ThemePro_LSW/UI_GetData",
	        data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
	        async: false,
	     
	        type: 'POST',
	        success: function OnSuccess_submit(data)
	        {
	        	 var str_xml = data;
	 	            var parser_xml = new DOMParser();
	 	            var oXml = parser_xml.parseFromString(str_xml, "text/xml");
	 	            var obj_elementcol = oXml.documentElement;
	 	           var item = obj_elementcol.getElementsByTagName('ReviewName')[0].textContent;
	         if(item=='')
	        	 
	        	 {
	        	 $('.find3').hide();
	        	 $('.find6').hide();
	        	 }
	         else
	        	 {
	        	 
	        	 $('.find3').show();
	        	 $('.find6').show();
	        	 }
	     
	        }
	 });
}
function FetchTblColName()
{//$('#lstRight').empty();
	$('#lstLeft').empty();

	//var QueueName=$('#QueueName').val();

	 
	    var param1=$('#WFID').val();
	   	var param2=$('#WFName').val();
	   	var param3=$('#TableName').val();
	   	var param4=$('#ReviewName').val();
	   	var param5=$('#FunctionName').val();
 	var spname='Sam_sFileStatusConfigfetch';
 	 $.ajax({
 	        url: "/ThemePro_LSW/UI_GetData",
 	        data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
 	        async: false,
 	     
 	        type: 'POST',
 	        success: function OnSuccess_submit(data)
 	        {
 	        	 var str_xml = data;
 	            var parser_xml = new DOMParser();
 	            var oXml = parser_xml.parseFromString(str_xml, "text/xml");
 	            var obj_elementcol = oXml.documentElement;

 	            for (var i = 0; i < obj_elementcol.getElementsByTagName('a').length; i++) {

 	                var option = document.createElement('option');

 	                  var item = obj_elementcol.getElementsByTagName('a')[i].textContent;
 	   //
 	                 option.value =$.trim(item);
 	                option.innerHTML = item;   
 	               
 	               
 	                    document.getElementById("lstLeft").appendChild(option);
 	                  }

 	           
 	        	
 	        }             
 	             });
 	var options= $($("#lstRight").find('option')).clone()
    $("#WrConditionClm").append(options);
   var x=$("#WrConditionClm option:selected").val()
   $("#HWrConditionClm").val(x)
   // WhereConditiondrop();
	}

function getValue() {
    var x = document.getElementById("lstRight");
    var listval = "";
    for (var i = 0; i < x.options.length; i++) {
        listval = listval + "<Data>";
        listval = listval + "<value>";
        listval = listval + x.options[i].value;
        listval = listval + "</value>";
        listval = listval + "</Data>";
    }
    $('#FromLBVal').val(listval);
}
function listbox_move(listID, direction) {

    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;

    if (-1 == selIndex) {
		alertify.alert(LoadFrmXML("V0129"));
        //alertify.alert("Please select an option to move.");
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



/*function FetchInsTblColName()
{
	var WFName=$('#WFName').val();
	var QueueName=$('#QueueName').val();
	var TableName=$('#TableName').val();
	$.ajax({
        url: "/ThemePro_LSW/UI_FetchInsertedTblColName",
        data: { WFName:WFName,QueueName:QueueName,TableName:TableName },
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
    //
                  option.value =$.trim(item);
                 option.innerHTML = item;   
                
                
                     document.getElementById("lstRight").appendChild(option);
                   }
           

        }
    });
	}
*/
function ClearFields()
{
	document.getElementById('WFID').value = '';
    
   // document.getElementById('WFName').value = '';
   document.getElementById('ReviewName').value = '';
   document.getElementById('FunctionName').value = '';
   document.getElementById('TableName').value = '';

   document.getElementById('FromLBVal').value = '';

   document.getElementById('WrConditionClm').value = '';
	$('#lstRight').empty();
	$('#lstLeft').empty();

	}
function clrtxtWF()
{

	$('#lstRight').empty();
	$('#WrConditionClm').empty();

}


function fetchdata()
	{
	      
	 	    var param1=$('#WFID').val();
	 	   	var param2=$('#WFName').val();
	 	   	var param3=$('#TableName').val();
	 	   	var param4=$('#ReviewName').val();
	 	   	var param5=$('#FunctionName').val();
	 	   	var spname='Sam_sFilestatusfetch';
	 	   	 $.ajax({
	 	   	        url: "/ThemePro_LSW/UI_GetData",
	 	   	        data: { param1: param1,param2:param2,param3:param3,param4:param4,param5:param5,spname:spname },
	 	   	        async: false,
	 	   	     
	 	   	        type: 'POST',
	 	   	        success: function OnSuccess_submit(data)
	 	   	        {
	 	   	        var str_xml = $(data).find('option');
	 	        	 $("#lstRight").append(str_xml);
	 	        	var options= $($("#lstRight").find('option')).clone()
	 	           $("#WrConditionClm").append(options);
	 	          var x=$("#WrConditionClm option:selected").val()
	 	          $("#HWrConditionClm").val(x)
	 	   	         /*   var parser_xml = new DOMParser();
	 	   	            var oXml = parser_xml.parseFromString(str_xml, "text/xml");
	 	   	            var obj_elementcol = oXml.documentElement;

	 	   	            for (var i = 0; i < obj_elementcol.getElementsByTagName('a').length; i++) {

	 	   	                var option = document.createElement('option');

	 	   	                  var item = obj_elementcol.getElementsByTagName('a')[i].textContent;
	 	   	
	 	   	                 option.value =$.trim(item);
	 	   	                option.innerHTML = item;   
	 	   	               
	 	   	               
	 	   	                    document.getElementById("lstRight").appendChild(option);
	 	   	                  }

	 	   	           */
	 	   	        	
	 	   	        }            
	 	        
	 	        
	 	        
	 	        
	 	        
	 	        
	 	        
	 	             });

}


function ClearFields()
{
	document.getElementById('WFID').value = '';
    
   // document.getElementById('WFName').value = '';
   document.getElementById('ReviewName').value = '';
   document.getElementById('FunctionName').value = '';
   document.getElementById('TableName').value = '';

   document.getElementById('FromLBVal').value = '';

   document.getElementById('Icon').value = '';
   document.getElementById('WrConditionClm').value = '';
	$('#lstRight').empty();
	$('#lstLeft').empty();

	}
