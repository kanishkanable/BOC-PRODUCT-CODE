
function CompareDateFields(startDate, endDate) {

    var end = endDate.id;
    var startDate = startDate.value;
    //document.getElementById("#" +startDate).value;
    var endDate = endDate.value;
    //document.getElementById("#" + endDate).value;
    var one_day = 1000 * 60 * 60 * 24;
    var x = startDate.split("-");
    var y = endDate.split("-");
    var date1 = new Date(x[2], (x[1] - 1), x[0]);

    var date2 = new Date(y[2], (y[1] - 1), y[0])
    Diff = Math.ceil((date2.getTime() - date1.getTime()) / (one_day));
    if (Diff < 0) {

    	//alertify.alert("End date is greater than Start Date");
			window.alert(LoadFrmXML("V0130"));

        return document.getElementById(end).value = "";
    }


}

$(document).on('click', '#Save', function(){
       // $(document).on('click', '#Table1 tbody tr', function () {
		   
		   var data1="";
		   if($("input:checked" ).length == 0)
	{
	//alertify.alert("Please Select a File");
		window.alert(LoadFrmXML("V0110"));
	//$("input:checked" ).prop( "checked", false );
	return;
    }
	
	

	
	

	for(i=0; i<$("input:checked" ).length;i++)
	{
		
	for(var j=1;j<$("#Table1").find($('input:checkbox[name=selectchk]:checked')[i]).closest('tr').find('td').length;j++)
    {
		data1 += $($("#Table1").find($('input:checkbox[name=selectchk]:checked')[i]).closest('tr').find('td'))[j].innerHTML+",";
	}
		
	
	}
	
	ChkValues(data1);
	
	
    
	
	/*
                var aData = popTableChk.fnGetData(this);
                                
                for (var i = 0; i < aData.length; i++) {

                    if (typeof curcontl[i] != "undefined") {
						if(aData[i] != null)
						{
                        //     Getjsondate                       curcontl[i].innerText = aData[i]
                        if (aData[i].toString().substring(0, 5) == "/Date") {
                            $(curcontl[i]).val(Getjsondate(aData[i]))
                        }
                        else {
                            $(curcontl[i]).val(aData[i]);

                            if ($(curcontl[i]).attr("id") == "ActivityID") {
                                //#region If you want Trigger Event Reg Here
                                $("#ActivityID").trigger('change');
                                //#endregion
                            }
							
							 if ($(curcontl[i]).attr("id") == "BCDT_ApplID")
								 {
                                //#region If you want Trigger Event Reg Here
                            	ViewAppDtl();
								
                                //#endregion
                            }
                            
                            if ($(curcontl[i]).attr("id") == "TableName") {
                                //#region If you want Trigger Event Reg Here
                                $("#TableName").trigger('change');
                                //#endregion
                            }
                            if ($(curcontl[i]).attr("id") == "editMenuFunction")
                            {
                                //#region If you want Trigger Event Reg Here
                                $("#editMenuFunction").trigger('change');
                                
                                //#endregion
                            }
                            
                            if ($(curcontl[i]).attr("id") == "QueueName") {
                                //#region If you want Trigger Event Reg Here
                                $("#QueueName").trigger('change');
                                $("#TableName").trigger('change');
                                //#endregion
                            }
                            
                            if ($(curcontl[i]).attr("id") == "WFName") {
                                //#region If you want Trigger Event Reg Here
                                $("WFName").trigger('change');
                                
                                //#endregion
                            }
							

							
							

                        }
}
                    }
                }
                if (typeof enablecontrol != "undefined") {
                    for (var i = 0; i < aData.length; i++) {

                        if (typeof enablecontrol[i] != "undefined") {




                            if (aData[i] == "Group with others" || aData[i] == "GROUP") {

                                $("." + enablecontrol[i]).show();
                                for (var ii = 0; ii < $("." + enablecontrol[i]).length; ii++) {
                                    var trid = $("." + enablecontrol[i])[ii].id;
                                    $("#" + trid).find("input[type=text]").val("");
                                }


                            }
                            else {
                                $("." + enablecontrol[i]).hide();

                                $("." + enablecontrol[i]).addClass("hidden-input");
                                for (var ii = 0; ii < $("." + enablecontrol[i]).length; ii++) {
                                    var trid = $("." + enablecontrol[i])[ii].id;
                                    $("#" + trid).find("input[type=text]").val("");
                                }
                            }
                        }
                    }
                }
*/
                $("#popup").dialog("close");

            });