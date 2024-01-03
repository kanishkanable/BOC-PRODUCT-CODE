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

        //$(".rearrhide").show();
     
        $(".ShowNewQueue").show();
        $(".EditDetails").hide();
        $('.ClearTxt').empty();
       // $('#New_MenuName').removeClass('ISDBMndtry');
        $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
       
       

    });
    $(".Edit").on("click", function () {
   $(".wfnmlkup").hide();
        $("#Rearrange").hide();
        $(".EditDetails").show();
        $(".ShowNewQueue").hide();
        $('.ClearTxt').empty();
		$("#EditWrConditionClmI").empty()
				$("#EditWrConditionClmII").empty()
				$("#EditLookupI").empty()
				$("#EditLookupII").empty()
				$("#EditTabshwclm").empty()
      /*  $('#EditClmNameRight').empty();
    	$('#EditClmName').empty();
    	$('#EditClmNameLeft').empty();*/
        $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );

    });
  



    //start
    $("#up").on("click", function () {
        listbox_move('ReQueueName', 'up');
        //alert("ok");
    });
    $("#down").on("click", function () {
        listbox_move('ReQueueName', 'down');
        //alert("ok");
    });
    //end
    //start
    $("#Qup").on("click", function () {
        listbox_move('ClmNameRight', 'up');
        //alert("ok");
    });
    $("#Qdown").on("click", function () {
        listbox_move('ClmNameRight', 'down');
        //alert("ok");
    });
    //end
    $("#Qleft").bind("click", function () {
        var options = $("[id*=ClmNameRight] option:selected");
		
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
			
			
            $("[id*=ClmNameLeft]").append(opt);
			//$("[id*=WrConditionClmI]").append($('#ClmNameRight option'))
			$("#WrConditionClmI").empty()
			$("#WrConditionClmII").empty()
			var $options = $("#ClmNameRight > option").clone();
    $("[id*=WrConditionClmI]").append($options);
	$("#LookupI").empty()
			$("#LookupII").empty()
			$("#Tabshwclm").empty();
    $("[id*=LookupI]").append($options);
    $("[id*=Tabshwclm]").append($options);
			$("#WrConditionClmI option[value='" + $("#HWrConditionClmI").val()+ "']").attr("selected", "true");
	$("#WrConditionClmII option[value='" + $("#HWrConditionClmII").val()+ "']").attr("selected", "true");
    $("#LookupI option[value='" + $("#HLookupI").val()+ "']").attr("selected", "true");  
$("#LookupII option[value='" + $("#HLookupII").val()+ "']").attr("selected", "true"); 
$("#Tabshwclm option[value='" + $("#HTabshwclm").val()+ "']").attr("selected", "true"); 

        }
		//getListValToDB('ClmNameRight','ColumnNames');
    });
    $("#Qright").bind("click", function () {
        var options = $("[id*=ClmNameLeft] option:selected");
        for (var i = 0; i < options.length; i++) {
        	if($('#ClmNameRight option').length<8){
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=ClmNameRight]").append(opt);
			
        	}
        	else
        		{
        		//alertify.alert("Only Eight columns are Allowed")
					alertify.alert(LoadFrmXML("V0131"));
        		}
				
				$("#WrConditionClmI").empty();
				$("#WrConditionClmII").empty();
				$("#LookupI").empty();
				$("#LookupII").empty();
				$("#Tabshwclm").empty();
			var $options = $("#ClmNameRight > option").clone();
    $("[id*=WrConditionClmI]").append($options);
	$("[id*=LookupI]").append($options);
	$("[id*=Tabshwclm]").append($options);
			$("#WrConditionClmI option[value='" + $("#HWrConditionClmI").val()+ "']").attr("selected", "true");
	$("#WrConditionClmII option[value='" + $("#HWrConditionClmII").val()+ "']").attr("selected", "true");
    $("#LookupI option[value='" + $("#HLookupI").val()+ "']").attr("selected", "true");  
$("#LookupII option[value='" + $("#HLookupII").val()+ "']").attr("selected", "true"); 
$("#Tabshwclm option[value='" + $("#HTabshwclm").val()+ "']").attr("selected", "true"); 
        }
		//getListValToDB('ClmNameRight','ColumnNames');
    });

    $("#EditQup").on("click", function () {
        listbox_move('EditClmNameRight', 'up');
        //alert("ok");
    });
    $("#EditQdown").on("click", function () {
        listbox_move('EditClmNameRight', 'down');
        //alert("ok");
    });
    //end
    $("#EditQleft").bind("click", function () {
        var options = $("[id*=EditClmNameRight] option:selected");
        for (var i = 0; i < options.length; i++) {
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=EditClmNameLeft]").append(opt);
           $("#EditWrConditionClmI").empty();
				$("#EditWrConditionClmII").empty();
				$("#EditLookupI").empty();
				$("#EditLookupII").empty();
				
				$("#EditTabshwclm").empty();
			var $options = $("#EditClmNameRight > option").clone();
    $("[id*=EditWrConditionClmI]").append($options);
	$("[id*=EditLookupI]").append($options);
	$("[id*=EditTabshwclm]").append($options);
	$("#EditWrConditionClmI option[value='" + $("#HEditWrConditionClmI").val()+ "']").attr("selected", "true");
	$("#EditWrConditionClmII option[value='" + $("#HEditWrConditionClmII").val()+ "']").attr("selected", "true");
    $("#EditLookupI option[value='" + $("#HEditLookupI").val()+ "']").attr("selected", "true");  
$("#EditLookupII option[value='" + $("#HEditLookupII").val()+ "']").attr("selected", "true"); 
$("#EditTabshwclm option[value='" + $("#HEditTabshwclm").val()+ "']").attr("selected", "true"); 
        }
    });
    $("#EditQright").bind("click", function () {
        var options = $("[id*=EditClmNameLeft] option:selected");
        for (var i = 0; i < options.length; i++) {
        	if($('#EditClmNameRight option').length<8){
            var opt = $(options[i]).clone();
            $(options[i]).remove();
            $("[id*=EditClmNameRight]").append(opt);
        	}
        	else
    		{
        		//alertify.alert("Only Eight columns are Allowed")
					alertify.alert(LoadFrmXML("V0131"));
    		}
			$("#EditWrConditionClmI").empty()
				$("#EditWrConditionClmII").empty()
				$("#EditLookupI").empty()
				$("#EditLookupII").empty()
				$("#EditTabshwclm").empty();
			var $options = $("#EditClmNameRight > option").clone();
    $("[id*=EditWrConditionClmI]").append($options);
	$("[id*=EditLookupI]").append($options);
	$("[id*=EditTabshwclm]").append($options);
		$("#EditWrConditionClmI option[value='" + $("#HEditWrConditionClmI").val()+ "']").attr("selected", "true");
	$("#EditWrConditionClmII option[value='" + $("#HEditWrConditionClmII").val()+ "']").attr("selected", "true");
    $("#EditLookupI option[value='" + $("#HEditLookupI").val()+ "']").attr("selected", "true");  
$("#EditLookupII option[value='" + $("#HEditLookupII").val()+ "']").attr("selected", "true"); 
$("#EditTabshwclm option[value='" + $("#HEditTabshwclm").val()+ "']").attr("selected", "true"); 

        }
    });



    $('#save').click(function () {
    	
        

            // document.getElementById('Chksave').value = "save";
            var chkmndtry = ChkMandatoryFields("ISDBMndtry");
            if (chkmndtry == "No") {
                return;
            }
            getListValToDB('ClmNameRight','ColumnNames');
            var param="New";
            var FORMXML = submitdata("ISDBfields");
            var spname="SAM_sINSHistConfig";
//alert(FORMXML);
       $.ajax({
            	
            	
                url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
                data: { xml: FORMXML,param:param,spname:spname},
                async:false,
           type:"Post",
                success : function OnSuccess_submit(Result) {
                	
                    if (Result == 'Success') {
                       
                      alertify.alert(LoadFrmXML("V0108"));
                       // window.location.reload();
                        $('.ClearTxt').empty();
                    	  	$("#WrConditionClmI").empty()
				$("#WrConditionClmII").empty()
				$("#LookupI").empty()
				$("#LookupII").empty()
                    	$("#Tabshwclm").empty()
                        $('.TXTFIELDSCLEAR').find('input:text').each(

                    function () {
                        $('input:text[id=' + $(this).attr('id') + ']').val('');
                    }

                );
            
                    }
                    else {
                      
                       alertify.alert(LoadFrmXML("V0074"));        
                       // window.location.reload();
                        $('.ClearTxt').empty();
                    	$("#WrConditionClmI").empty()
				$("#WrConditionClmII").empty()
				$("#LookupI").empty()
				$("#LookupII").empty()
                    $("#Tabshwclm").empty()	
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
    $('#UpdateBtn').click(function () {

        
        
        
        var chkmndtry = ChkMandatoryFields("ISDBMndtryedit");
        if (chkmndtry == "No") {
            return;
        }
        getListValToDB('EditClmNameRight','EditColumnNames');
        
        var FORMXML = submitdata("ISDBfields");

        var param = "Update";
        var spname='SAM_sINSHistConfig';
//alert(FORMXML);
       $.ajax({

            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param ,spname:spname},
            async:false,
            type: 'POST',
            success: function OnSuccess_submit(data) {
                if (data == 'Success') {
                	 alertify.alert(LoadFrmXML("V0108"));
                	$('.ClearTxt').empty();
                	$("#EditWrConditionClmI").empty()
				$("#EditWrConditionClmII").empty()
				$("#EditLookupI").empty()
				$("#EditLookupII").empty()
                $("#EditTabshwclm").empty()	
                    $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
                   
                    
                    //$("#Tab2").trigger("click");

                }
                else {
                	 alertify.alert(LoadFrmXML("V0085"));
                    $('.ClearTxt').empty();
					$("#EditWrConditionClmI").empty();
				$("#EditWrConditionClmII").empty();
				$("#EditLookupI").empty();
				$("#EditLookupII").empty();
				$("#EditTabshwclm").empty();	
                    $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
                    

                    
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
                //GridDateChangeBack_m("TablerescADD", "ISDatefields", "2");
            	 alertify.alert(LoadFrmXML("V0075"));

            }
        });
    });


    $('#DeleteBtn').click(function () {
    	var chkmndtry = ChkMandatoryFields("ISDBMndtryedit");
        if (chkmndtry == "No") {
            return;
        }

        var FORMXML = submitdata("ISDBfields");

        var param = "Delete";
        var spname="SAM_sINSHistConfig";
        $.ajax({

            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param,spname:spname},
            async:false,
            type: 'POST',
            success: function OnSuccess_submit(data) {
                if (data == 'Success') {
                	 alertify.alert(LoadFrmXML("V0109"));
                	$('.ClearTxt').empty();
                   // $("#Tab2").trigger("click");
				   $("#EditWrConditionClmI").empty();
				$("#EditWrConditionClmII").empty();
				$("#EditLookupI").empty();
				$("#EditLookupII").empty();
				$("#EditTabshwclm").empty()	;
                    $('.TXTFIELDSCLEAR').find('input:text').each(

                function () {
                    $('input:text[id=' + $(this).attr('id') + ']').val('');
                }

            );
                    
                    
                }
                else {
                	 alertify.alert(LoadFrmXML("V0085"));

                }
            },
            error: function OnError_submit(xmlRequest) {
            	 alertify.alert(LoadFrmXML("V0075"));

            }
        });
    });
    
});

function clrtxtWF()
{

document.getElementById('QueueName').value = '';

}

