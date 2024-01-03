$(document).on('click', '#Save', function(){
       // $(document).on('click', '#Table1 tbody tr', function () {
		   
		   var data1="";
		   if($("input:checked" ).length == 0)
	{
	//alertify.alert("Please Select a File");
	alertify.alert(LoadFrmXML("V0110"));
	$("input:checked" ).prop( "checked", false );
	return false;
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

//        if (document.getElementById("AssignRights").value == "Everyone" && document.getElementById("AssignForAllSubLevels").value != "Yes") {

//            alert('Choose "Yes", In Assign For All SubLevels');
//            return;

//        }



      
		
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
       var param = 'Entry';
        var setrightsid=document.getElementById('SetRightsID').value;
        var FORMXML = submitdata("ISDBfields");
       // var levelcount = document.getElementById('Menulevel').value;
      //  alert(FORMXML);
var spname='sam_sFormRights';
		


$.ajax({

            url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param ,spname:spname},
            async: false,
           // dataType: "json",
            type: 'POST',
            success: function OnSuccess_submit(data) {
				
				
				alertify.alert(LoadFrmXML("V0106"));
				//alertify.alert(data.replace(/,/g, "\r\n"));
				
					$("#Table3_wrapper").hide();
				$("#Rgtsgridview").trigger("click");
				

                 /* $('.TXTFIELDSCLEAR').find('input:text').each(

            function () {
                $('input:text[id=' + $(this).attr('id') + ']').val('');
                $('#Menulevel').val('');
                Clearfields();
               
            }

        );*/

            },
            error: function OnError_submit(xmlRequest) {
			alertify.alert(LoadFrmXML("V0075"));
            }

        });

		
}

$(document).on('click', '#RightsDelete', function () {
	var Wfname=document.getElementById('name').value;
	var pid=document.getElementById('packageId').value
		//var MenuId= $('#MenuID').val(); //$(this).closest('tr').find('td')[1].innerHTML;
		var RightsID=$(this).closest('tr').find('td')[1].innerHTML+'|'+$(this).closest('tr').find('td')[2].innerHTML+'|'+$(this).closest('tr').find('td')[3].innerHTML;
		var spname='Sam_sFormRightsDelete';
		//alert(Level+'|'+Type+'|'+RightsID);

		alertify.confirm("Are you sure?", function (e) {
		        if (e) 
				{	
				    
		           var Deletedata= UI_getdata(Wfname,pid,RightsID,"","",spname);
					//$(this).closest('tr').remove();
					$("#Rgtsgridview").trigger("click");
		        } 
				else 
				{
		            return;
		        }
		    });
		    
	
});

function FetchAllData(){
	$("#Rgtsgridview").trigger("click")
}



function fillAddTblNames(param1,param2,param3,param4,param5,spname,fieldid)
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





