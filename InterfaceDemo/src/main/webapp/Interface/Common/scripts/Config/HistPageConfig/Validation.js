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
	});
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
$('#'+ToID).val(listval);
	}

function listbox_move(listID, direction) {

    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;

    if (-1 == selIndex) {
    
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


function ForEditRightClmBlur(){
$("#EditWrConditionClmI").empty()
				$("#EditWrConditionClmII").empty()
				$("#EditLookupI").empty()
				$("#EditLookupII").empty()
				$("#EditTabshwclm").empty()	;
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


function SelectedDropVal(id){
//var val=$("#H"id).val();
var x=$("#"+id+" option:selected").val()
 $("#H"+id).val(x)

}