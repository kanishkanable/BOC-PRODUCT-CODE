$(document).ready(function () {
	
	var IOP=window.location.origin+'/'+LoadFrmXML("PG001");

		SessionStChk();
		if (document.getElementById("SubmitSuccess").value=="InValid")
			{			
			window.location= IOP;	
			return;
			}
			
	 var loggedinuser = getUrlParam("username");
	 $(".NewTab").on("click", function () {
			$('.TXTFIELDSCLEAR').find('input:text').each(

	                function () {
	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
	                }

	            );
			$('#lstLeft').empty();
			$('#lstRight').empty();
			$("#Table2_wrapper").hide();
		});
	 
	 
	 $(".EditTab").on("click", function () {
			$('.TXTFIELDSCLEAR').find('input:text').each(

	                function () {
	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
	                }

	            );
			$('#EditAddTblNameLeft').empty();
			$('#EditAddTblNameRight').empty();
			//$("#Table2_wrapper").hide();
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
	           // WhereConditiondrop();
	        }
	    });
	    $("#right").bind("click", function () {
	        var options = $("[id*=lstLeft] option:selected");
	        for (var i = 0; i < options.length; i++) {
	            var opt = $(options[i]).clone();
	            $(options[i]).remove();
	            $("[id*=lstRight]").append(opt);
	            //WhereConditiondrop();
	        }
	    });

	    
	    
	    
	    $("#Editup").on("click", function () {
	        listbox_move('EditAddTblNameRight', 'up');
	        //alert("ok");
	    });
	    $("#Editdown").on("click", function () {
	        listbox_move('EditAddTblNameRight', 'down');
	        //alert("ok");
	    });
	    //end
	    $("#EditLeft").bind("click", function () {
	        var options = $("[id*=EditAddTblNameRight] option:selected");
	        for (var i = 0; i < options.length; i++) {
	            var opt = $(options[i]).clone();
	            $(options[i]).remove();
	            $("[id*=EditAddTblNameLeft]").append(opt);
	           // WhereConditiondrop();
	        }
	    });
	    $("#EditRight").bind("click", function () {
	        var options = $("[id*=EditAddTblNameLeft] option:selected");
	        for (var i = 0; i < options.length; i++) {
	            var opt = $(options[i]).clone();
	            $(options[i]).remove();
	            $("[id*=EditAddTblNameRight]").append(opt);
	            //WhereConditiondrop();
	        }
	    });

	    

	  $('#NewFormIns').click(function () {
			 $(".NewMndtry").addClass("ISDBfields");
			 $(".NewMndtry").addClass("Mndtry");
			 //$(".ISDBMndtryedit").removeClass("ISDBfields");
			 $(".EditMndtry").removeClass("Mndtry");
			  $("#NewFormURL").addClass("ISDBfields");
			 
			 getValue('AddtionalTables','lstRight');
			 var AddTbls = document.getElementById('AddtionalTables').value;
			 
			  var chkmndtry = ChkMandatoryFields("Mndtry");


		        if (chkmndtry == "No") {
		            return;
		        }
		        var param = 'Entry';

		        //getListValToDB('lstRight','FromLBVal');
		        var FORMXML = submitdata("ISDBfields");
		        var spname='Sam_sInsFormHdr';
		        //var spname='Sam_sFormHdr';
		        var param3='';
		        var param4='';
		        var param5='';
		        $.ajax({

		            url: "/ThemePro_LSW/UI_GetData",
		            data: { param1: FORMXML, param2: param , param3: param3 , param4: param4 , param5: param5 ,spname:spname},
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
						
						if($(data).text()=='Success'){
						alertify.alert(LoadFrmXML("V0108"));
						$('.ClearTxt').empty();
						//alertify.alert(data.replace(/,/g, "\r\n"));
						
						 $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
						
						}
						else if($(data).text()=='Failure'){
							 alertify.alert(LoadFrmXML("V0153"));
							$('.ClearTxt').empty();
							//alertify.alert(data.replace(/,/g, "\r\n"));
							
							 $('.TXTFIELDSCLEAR').find('input:text').each(

			        	                function () {
			        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
			        	                }

			        	            );
						}
//window.location.reload();
		             
		            },
		            error: function OnError_submit(xmlRequest) {
					alertify.alert(LoadFrmXML("V0075"));
		            }

		        });
		        
		        
	  });
	  
	  
	  $('#Update').click(function () {
			 $(".NewMndtry").removeClass("ISDBfields");
			 $("#NewFormURL").removeClass("ISDBfields");
			 $(".NewMndtry").removeClass("Mndtry");
			 $(".EditMndtry").addClass("ISDBfields");
			 $(".EditMndtry").addClass("Mndtry");
			 $("#EditFormURL").addClass("ISDBfields");
			// $(".ISDBMndtryedit").addClass("ISDBfields");
			 
			 getValue('EditAddtionalTables','EditAddTblNameRight');
		        var AddTbls = document.getElementById('EditAddtionalTables').value;
		       
			  var chkmndtry = ChkMandatoryFields("Mndtry");


		        if (chkmndtry == "No") {
		            return;
		        }
		        var param = 'Update';
		         var FORMXML = submitdata("ISDBfields");
		        var spname='Sam_sInsFormHdr';
		        var param3='';
		        var param4='';
		        var param5='';
		      $.ajax({

		            url: "/ThemePro_LSW/UI_GetData",
		            data: { param1: FORMXML, param2: param , param3: param3 , param4: param4 , param5: param5 ,spname:spname},
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
						
						if($(data).text()=='Success'){
						alertify.alert(LoadFrmXML("V0123"));
					  	$('.ClearTxt').empty();
						 $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
						
						}
						

		             
		            },
		            error: function OnError_submit(xmlRequest) {
					alertify.alert(LoadFrmXML("V0075"));
		            }

		        });
		        
		        
	  });
	  $('#Deletebtn').click(function () {
			 $(".NewMndtry").removeClass("ISDBfields");
			 $("#NewFormURL").removeClass("ISDBfields");
			 $(".NewMndtry").removeClass("Mndtry");
			 $(".EditMndtry").addClass("ISDBfields");
			 $(".EditMndtry").addClass("Mndtry");
			 $("#EditFormURL").addClass("ISDBfields");
			 
			 getValue('EditAddtionalTables','EditAddTblNameRight');
		        var AddTbls = document.getElementById('EditAddtionalTables').value;
		     
			  var chkmndtry = ChkMandatoryFields("Mndtry");


		        if (chkmndtry == "No") {
		            return;
		        }
		        var param = 'Delete';
		          var FORMXML = submitdata("ISDBfields");
		        var spname='Sam_sInsFormHdr';
		        var param3='';
		        var param4='';
		        var param5='';
		      $.ajax({

		            url: "/ThemePro_LSW/UI_GetData",
		            data: { param1: FORMXML, param2: param , param3: param3 , param4: param4 , param5: param5 ,spname:spname},
		            async: false,
		           // dataType: "json",
		            type: 'POST',
		            success: function OnSuccess_submit(data) {
						
						if($(data).text()=='Success'){
						alertify.alert(LoadFrmXML("V0124"));
						$('.ClearTxt').empty();
						 $('.TXTFIELDSCLEAR').find('input:text').each(

		        	                function () {
		        	                    $('input:text[id=' + $(this).attr('id') + ']').val('');
		        	                }

		        	            );
						}
						

		             
		            },
		            error: function OnError_submit(xmlRequest) {
					alertify.alert(LoadFrmXML("V0075"));
		            }

		        });
		        
		        
	  });
});



function FetchAddTbl()
{//$('#lstRight').empty();
	$('#lstLeft').empty();

	//var QueueName=$('#QueueName').val();

	 
	    var param1=$('#NewWFName').val();
	   	var param2=$('#NewMaintableName').val();
		var param3='';
		var param4='';
		var param5='';
	   	//var param3=$('#TableName').val();
	   	//var param4=$('#ReviewName').val();
	   	//var param5=$('#FunctionName').val();
 	var spname='Sam_sFetchFormConfigTblName';
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
 	//var options= $($("#lstRight").find('option')).clone()
   // $("#WrConditionClm").append(options);
   //var x=$("#WrConditionClm option:selected").val()
  // $("#HWrConditionClm").val(x)
   // WhereConditiondrop();
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
}
function getValue(Txt,Lst) {
    var x = document.getElementById(Lst);
    var listval = "";
    for (var i = 0; i < x.options.length; i++) {
        listval = listval + "<Data>";
        listval = listval + "<value>";
        listval = listval + x.options[i].value;
        listval = listval + "</value>";
        listval = listval + "</Data>";
    }
    $('#'+Txt).val(listval);
}
function ClrTxt()
{
	document.getElementById('NewFormID').value = '';
	document.getElementById('NewFormName').value = '';
	document.getElementById('NewMaintableName').value = '';
	
	$('#lstLeft').empty();
	$('#lstRight').empty();

}

function ClrList()
{
	
	$('#lstRight').empty();

}
function Clrval()
{
	
	
	document.getElementById('EditFormName').value = '';
	document.getElementById('EditMaintableName').value = '';
	document.getElementById('EditFormURL').value = '';
	$('#EditAddTblNameLeft').empty();
	$('#EditAddTblNameRight').empty();

}