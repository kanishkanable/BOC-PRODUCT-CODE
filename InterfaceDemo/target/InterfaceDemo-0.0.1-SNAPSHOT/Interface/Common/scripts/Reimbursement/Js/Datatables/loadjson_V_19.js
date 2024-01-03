var BTN = "."
function Fncallwebservicefullalldata(fnname, par, control1, hdncolumn) {
  
    if (par.cusid !="")
    {
    var popTable;
    $.ajax({
        type: "POST",
        url: "/Models/Utilities/WebService2.asmx/" + fnname,
        data: JSON.stringify(par, null, 3),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
           
            var json = [];
            var dtData11 = {};
            var count = 0;
            var myDatacol = JSON.parse(result.d);

            var entry;
            var name;
            entry = myDatacol[0];

            for (name in entry) {
                if (entry.hasOwnProperty(name)) {
                    dtData11[count] = name;
                    count++;

                }

            }

            $.each(myDatacol, function (event) {


                switch (count) {
                    case 1:
                        json.push([this[dtData11[0]]]);
                        break;
                    case 2:
                        json.push([this[dtData11[0]], this[dtData11[1]]]);
                        break;
                    case 3:
                        json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]]]);
                        break;

                }
            });

            //            var dtcolumn = [];

            //            for (var i = 0; i < count; i++) {
            //                var obj = {};
            //                obj["sTitle"] = dtData1[i];
            //                dtcolumn.push(obj);
            //            }
            $('#example').dataTable().fnClearTable();
            for (hash in json) {
                $('#example').dataTable().fnAddData([BTN, json[hash][0], json[hash][1], json[hash][2]]);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            //alertify.alert(err.Message);
			 alert(err.Message);
        }
    });

}
//else
//{

//}
}

/* Getting Customer Loan Details*/
function FncallwebserviceTDS(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
               // alertify.alert(LoadFrmXML("V0072"))
				  alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popup").dialog("open");
                handlejson(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
           // alertify.alert(err.Message);
			alert(err.Message);
        }
    });
}
function FncallwebserviceGetCusLndetls(fnname, par, control1, hdncolumn) {

    if (par.cusid != "") {
        var popTable;
        $.ajax({
            type: "POST",
            url: "/Models/Utilities/WebService2.asmx/" + fnname,
            data: JSON.stringify(par, null, 3),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                var json = [];
                var dtData11 = {};
                var count = 0;
                var myDatacol = JSON.parse(result.d);

                var entry;
                var name;
                entry = myDatacol[0];

                for (name in entry) {
                    if (entry.hasOwnProperty(name)) {
                        dtData11[count] = name;
                        count++;

                    }

                }
                
              
                $.each(myDatacol, function (event) {


                    switch (count) {
                        case 1:
                            json.push([this[dtData11[0]]]);
                            break;
                        case 2:
                            json.push([this[dtData11[0]], this[dtData11[1]]]);
                            break;
                        case 3:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]]]);
                            break;
                        case 4:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]]]);
                            break;
                        case 5:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]]]);
                            break;
                        case 6:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]] ,this[dtData11[5]]]);
                            break;
                        case 7:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]]]);
                            break;
                        case 8:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]]]);
                            break;
                        case 9:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]]]);
                            break;
                        case 10:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]]]);
                            break;
                        case 11:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]]]);
                            break;

                    }
                });

                $('#grid_finalposition').dataTable().fnClearTable();
                for (hash in json) {
                    $('#grid_finalposition').dataTable().fnAddData([BTN, json[hash][0], json[hash][1], json[hash][2], json[hash][3], json[hash][4], json[hash][5], json[hash][6], json[hash][7], json[hash][8], json[hash][9], json[hash][10], json[hash][11]]);
                }
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
               // alertify.alert(err.Message);
				 alert(err.Message);
            }
        });

    }
 
}


function FncallwebserviceforLSO(fnname, par, assingcontrl) {


    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 2),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                //alertify.alert(LoadFrmXML("V0072"))
				 alert(LoadFrmXML("V0072"))

            }
            else {
                var da = JSON.parse(result.d);
                $(assingcontrl).val(da[0]['CurrentVal']);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
           // alertify.alert(err.Message);
			 alert(err.Message);
        }
    });
}

function Fncallwebserviceforqdeserch(fnname, par, assingcontrl) {


    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 2),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                //alertify.alert(LoadFrmXML("V0072"))
				alert(LoadFrmXML("V0072"))

            }
            else {
                $('.cls_AcctDetails').hide();
                $('#div3').show();
                $('.divid').show();
                $(".div1").show()
                $(".divid1").hide()
                $(".divid2").hide()
                $(".divid3").hide()
                $(".divid4").hide()
                var da = JSON.parse(result.d);

                $("#TXT_BrId").val(da[0]['TXT_BrId']);
                $("#TXT_QDEId").val(da[0]['TXT_QDEId']);
                $("#TXT_PrdId").val(da[0]['TXT_PrdId']);
                $("#TXT_CusId").val(da[0]['TXT_CusId']);
                $("#TXT_CusName").val(da[0]['TXT_CusName']);
                $("#TXT_Type").val(da[0]['TXT_Type']);
                $("#TXT_QdeStage").val(da[0]['TXT_QdeStage']);
                $("#TXT_BnkAcctNo").val(da[0]['P_Acctid']);
                $("#TXT_Modpay").val(da[0]['P_ModOfPay']);
                $("#TXT_BnkName").val(da[0]['P_Bankname']);
                $("#TXT_MICRCode").val(da[0]['P_Micrcde']);
                $("#TXT_Brname").val(da[0]['P_Brname']);
                $("#TXT_IFSCCode").val(da[0]['P_ifsccde']);


                if (typeof da[1] != "undefined" && typeof da[1] != undefined) {
                    $('.cls_AcctDetails').show();
                    var JsonObj = [];
                    $.each(da, function (i, data) {
                        var arrobj = [];
                        if (i != '0') {

                            arrobj.push(data["TXT_QDEId"]);
                            arrobj.push(data["P_ModOfPay"]);
                            arrobj.push(data["P_Acctid"]);
                            arrobj.push(data["P_Bankname"]);
                            arrobj.push(data["P_Brname"]);
                            arrobj.push(data["P_Micrcde"]);
                            arrobj.push(data["P_ifsccde"]);

                            arrobj.push(data["TXT_QdeStage"]);
                            JsonObj.push(arrobj);
                        }

                    });
                    oTable_GridFdDetls.fnClearTable();
                    oTable_GridFdDetls.fnAddData(JsonObj);
                }






            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
           // alertify.alert(err.Message);
			  alert(err.Message);
        }
    });
}
function Fncallwebservicegetothdetl(fnname, par, control1, hdncolumn) {

    if (par.cusid != "") {
        var popTable;
        $.ajax({
            type: "POST",
            url: "/Models/Utilities/WebService2.asmx/" + fnname,
            data: JSON.stringify(par, null, 3),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                var json = [];
                var dtData11 = {};
                var count = 0;
                var myDatacol = JSON.parse(result.d);

                var entry;
                var name;
                entry = myDatacol[0];

                for (name in entry) {
                    if (entry.hasOwnProperty(name)) {
                        dtData11[count] = name;
                        count++;

                    }

                }

                $.each(myDatacol, function (event) {


                    switch (count) {
                        case 1:
                            json.push([this[dtData11[0]]]);
                            break;
                        case 2:
                            json.push([this[dtData11[0]], this[dtData11[1]]]);
                            break;
                        case 3:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]]]);
                            break;
                        case 4:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]]]);
                            break;
                        case 5:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]]]);
                            break;
                        case 6:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]]]);
                            break;
                        case 7:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]]]);
                            break;
                        case 8:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]]]);
                            break;
                        case 9:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]]]);
                            break;
                        case 10:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]]]);
                            break;
                        case 11:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]]]);
                            break;
                        case 12:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]], this[dtData11[11]]]);
                            break;
                        case 13:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]], this[dtData11[11]], this[dtData11[12]]]);
                            break;
                        case 14:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]], this[dtData11[11]], this[dtData11[12]], this[dtData11[13]]]);
                            break;
                        case 15:
                            json.push([this[dtData11[0]], this[dtData11[1]], this[dtData11[2]], this[dtData11[3]], this[dtData11[4]], this[dtData11[5]], this[dtData11[6]], this[dtData11[7]], this[dtData11[8]], this[dtData11[9]], this[dtData11[10]], this[dtData11[11]], this[dtData11[12]], this[dtData11[13]], this[dtData11[14]]]);
                            break;
                    }
                });
                $('#Otherbanktable').dataTable().fnClearTable();
                for (hash in json) {
                    $('#Otherbanktable').dataTable().fnAddData([BTN, json[hash][0], json[hash][1], json[hash][2], json[hash][3], json[hash][4], json[hash][5]]);
                }
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
               // alertify.alert(err.Message);
				 alert(err.Message);
            }
        });

    }
//    else {
//        alertify.alert("Enter valid customer Id")
//    }
}

function Getjsondate(jsondate) {
    var dateString = jsondate.substr(6);
    var currentTime = new Date(parseInt(dateString));
    var month = currentTime.getMonth() + 1;
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();
    var date = day + "-" + month + "-" + year;
    return date;
}




function Fncallwebservicgetcusdetl(fnname, par, assingcontrl) {


    //    $("#popup").dialog("open");
    if (par.cusid != "") {
        $.ajax({
            type: "POST",
            url: "/models/Utilities/WebService2.asmx/" + fnname,
            //        data: par,
            data: JSON.stringify(par, null, 2),
            //data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {

                if (result.d == "[]") {
                   // alertify.alert(LoadFrmXML("V0072"))
					  alert(LoadFrmXML("V0072"))

                }
                else {
                    var da = JSON.parse(result.d);
                    //$("#" + assingcontrl[0].id).val(da[0]["Cusid"])
                    //$("#" + assingcontrl[1].id).val(da[0]["FullName"])
                    $("#" + assingcontrl[2].id).val(da[0]["custype"])
                    $("#" + assingcontrl[3].id).val(da[0]["Permanent Address"])
                    $("#" + assingcontrl[4].id).val(da[0]["Business Address"])
                    $("#" + assingcontrl[5].id).val(da[0]["Correspondence  Address"])
                    $("#" + assingcontrl[6].id).val(Getjsondate(da[0]["DOB"]))
                    $("#" + assingcontrl[7].id).val(da[0]["Phone No"])
                    $("#" + assingcontrl[8].id).val(da[0]["Email"])
                    $("#" + assingcontrl[9].id).val(da[0]["age"])
                    $("#" + assingcontrl[10].id).val(da[0]["Occupation"])
                    $("#" + assingcontrl[11].id).val(da[0]["Gender"])
                    $("#" + assingcontrl[12].id).val(da[0]["Maritalstatus"])
                    $("#" + assingcontrl[13].id).val(da[0]["Education"])
                    $("#" + assingcontrl[14].id).val(da[0]["idno"])
                }
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                //alertify.alert(err.Message);
				 alert(err.Message);
            }
        });
    }
}

function Fncallwebservicepincode(fnname, par) {


    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 2),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
               // alertify.alert(LoadFrmXML("V0072"))
				 alert(LoadFrmXML("V0072"))

            }
            else {
                var da = JSON.parse(result.d);
                //$("#" + assingcontrl[0].id).val(da[0]["Cusid"])
                //$("#" + assingcontrl[1].id).val(da[0]["FullName"])
                if (da[0]["result1"] == "NA") {
                }
                else {
                    $("#statecode").val(da[0]["result1"])
                }

            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            //alertify.alert(err.Message);
			alert(err.Message);
        }
    });
    
}

function Fncallwebservicegetclass(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;


    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
              //  alertify.alert(LoadFrmXML("V0072"))
				alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popup").dialog("open");
                handlejson(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
           // alertify.alert(err.Message);
			            alert(err.Message);
        }
    });
}

function Fncallwebserviceforcusid(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;


    //    $("#popup").dialog("open");
    if (typeof par['Param'] != "undefined") {
        $("#ValidationMessage").html("");
        $.ajax({
            type: "POST",
            url: "/models/Utilities/WebService2.asmx/" + fnname,
            //        data: par,
            data: JSON.stringify(par, null, 4),
            //data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {

                if (result.d == "[]") {
                    alert(LoadFrmXML("V0072"))

                }
                else {
                    $("#popup").dialog("open");
                    handlejson(result, control1, hdncolumn, enablecontrol);
                }
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    }
    else {

        $("#ValidationMessage").html("Please Select Customer Type");
    }
}
//#region Load Popup Without Ajax Call with hidden field
function Fncallwebservice_WHdn(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popupBranch").dialog("open");
                handlejson_Whdn(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}
function Fncallwebservice_WHdn_FWelBranch(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popupACCTBRID").dialog("open");
                handlejson_Whdn_FWelBranch(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}

function Fncallwebservice_WHdn_ForBrancID(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popupBranch").dialog("open");
                handlejson_Whdn_FBrID(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}

function Fncallwebservice_WHdn_FPrdID(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popupPRD").dialog("open");
                handlejson_Whdn_FPrdID(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}
function Fncallwebservice_WHdn_FAcctID(fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");

    $.ajax({
        type: "POST",
        url: "/models/Utilities/WebService2.asmx/" + fnname,
        //        data: par,
        data: JSON.stringify(par, null, 4),
        //data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            if (result.d == "[]") {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popupAcctID").dialog("open");
                handlejson_Whdn_FAcctID(result, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}


//function Fncallwebservice_WHdn(Hdn_data,fnname, par, control1, hdncolumn, enablecontrol) {
//    var popTable;
//    if ($('#' + Hdn_data) == "[]") {
//        alertify.alert(LoadFrmXML("V0072"))

//    }
//    else {
//        $("#popupBranch").dialog("open");
//        
//        if(sessionStorage.getItem('SessionMICRCODE') != '1')
//        {
//            handlejson_Whdn($('#' + Hdn_data).val(), control1, hdncolumn, enablecontrol);
//            sessionStorage.setItem('SessionMICRCODE', '1');
//        }
//    }


//    //    $("#popup").dialog("open");


//}
//#endregion
function Fncallwebservice(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

//    $("#popup").dialog("open");

   // var
    ajaxindicatorstart("Loading.. Please wait");
    $.ajax({
        url: "/SBIReimbursement/FncallWebserviceGridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
		  
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
                $("#popup").dialog();
                $("#popup").dialog("option", "width", 805);
                $("#popup").dialog("option", "closeOnEscape", true);
                $("#popup").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejson(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
			ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}

function FncallwebserviceLrgDataSPL(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

//    $("#popup").dialog("open");

var spname = par.spname
var Param = par.Param
var brid = par.brid
var MnuId=par.MnuId

   // var
   // ajaxindicatorstart("Loading.. Please wait");
    /*$.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popup").dialog();
                $("#popup").dialog("option", "width", 805);
                $("#popup").dialog("option", "closeOnEscape", true);
                $("#popup").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejson(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
			ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });*/
	
		//var op=UI_getdata(Param,brid,MnuId,"","",spname);
		
	////
	//return;
		$.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: Param, param2: brid, param3: MnuId, param4: "", param5: "",spname: spname },
             //  async: false,
               //dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   var	op=xml1.responseText;
					
			if (op != "")
			{				
var result = op.replace('<Resultset><a>','').replace('</a></Resultset>','').replace('<XMLLRGResult>','<Resultset>').replace('</XMLLRGResult>','</Resultset>').replace(/asetLRGDAta/g,'a')
				
	
	 result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
					if(json == "")
			{
					alert(LoadFrmXML("V0072"));
					return
			}
			
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
               // $("#popupSPL").dialog();
               // $("#popupSPL").dialog("option", "width", 805);
              //  $("#popupSPL").dialog("option", "closeOnEscape", true);
               // $("#popupSPL").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejsonSPLPopup(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            }
	ajaxindicatorstop();
					
			}		//ajaxindicatorstop();
			else
			{
			alert(LoadFrmXML("V0072"));
            	ajaxindicatorstop();
			}
			},
			
            error: function (xml1)
            {
				//alertify.alert(LoadFrmXML("V0126"));
					alert(LoadFrmXML("V0126"));
				OP="Fail";
				//ajaxindicatorstop();
            }
                                  
               
    	   });
		
		
		
	////

}

///


function FncallwebserviceLrgData(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

//    $("#popup").dialog("open");

var spname = par.spname
var Param = par.Param
var brid = par.brid
var MnuId=par.MnuId
var DBSRC=par.DBSrc

   // var
   // ajaxindicatorstart("Loading.. Please wait");
    /*$.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popup").dialog();
                $("#popup").dialog("option", "width", 805);
                $("#popup").dialog("option", "closeOnEscape", true);
                $("#popup").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejson(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
			ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });*/
	
		//var op=UI_getdata(Param,brid,MnuId,"","",spname);
		
	////
	
		$.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: Param, param2: brid, param3: MnuId, param4: "", param5: "",spname: spname,DBSrc:DBSRC },
             //  async: false,
               //dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   var	op=xml1.responseText;
					
			if (op != "")
			{				
var result = op.replace('<Resultset><a>','').replace('</a></Resultset>','').replace('<XMLLRGResult>','<Resultset>').replace('</XMLLRGResult>','</Resultset>').replace(/asetLRGDAta/g,'a')
				
	
	 result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        				
			if(json == "")
			{
					alert(LoadFrmXML("V0072"));
					return
			}
			
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
                $("#popup").dialog();
                $("#popup").dialog("option", "width", 805);
                $("#popup").dialog("option", "closeOnEscape", true);
                $("#popup").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejson(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            }
	ajaxindicatorstop();
					
			}		//ajaxindicatorstop();
			else
			{
			alert(LoadFrmXML("V0072"));
            	ajaxindicatorstop();
			}
			},
			
            error: function (xml1)
            {
				//alertify.alert(LoadFrmXML("V0126"));
					alert(LoadFrmXML("V0126"));
				OP="Fail";
				//ajaxindicatorstop();
            }
                                  
               
    	   });
		
		
		
	////

}

//QueueLlrgdata start

function FncallQueueLrgData(cntrl, fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    var spname = par.spname
var Param = par.Param
var brid = par.brid
var MnuId=par.MnuId
  

  
  		$.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: Param, param2: brid, param3: MnuId, param4: "", param5: "",spname: spname },
               async: false,
               //dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   var	op=xml1.responseText;
					
			if (op != "")
			{				
var result = op.replace('<Resultset><a>','').replace('</a></Resultset>','').replace('<XMLLRGResult>','<Resultset>').replace('</XMLLRGResult>','</Resultset>').replace(/asetLRGDAta/g,'a')
				
	
	        result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
					if(json == "")
			{
					//alert(LoadFrmXML("V0072"));
					  $('#Table1').empty();
					  	$("#Table1_info").text('Showing 0 to 0 of 0 entries');
						$("#Table1_paginate").remove();
						$("#Table1_wrapper").find(".dataTables_scroll").find(".dataTables_scrollHead").remove();
// For Side Tree Inbox/Queue Count Start			
var QmainMenuID = $(window.parent.document).find('ul.tabs li.active').attr('name').split('~')[0];
var QSubMenuClass = $(cntrl).attr('name');
var QIcon="";

if($($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()).length == 2)
{
	QIcon= $($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()[0]).prop('outerHTML');
}
else if($($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()).length == 3)
{
		QIcon= $($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()[1]).prop('outerHTML');
}
else
{
QIcon = $(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').text();	
}

var QSubMenuTXT = QIcon + QSubMenuClass.replace(/_/g,' ') + ' (0)';

$("#"+$(cntrl).find('span').attr('id')).text('0');


$(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').text('');
$(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').append(QSubMenuTXT)
// For Side Tree Inbox/Queue Count End			
									

					return
			}
			
			
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
               // $("#popupSPL").dialog();
               // $("#popupSPL").dialog("option", "width", 805);
              //  $("#popupSPL").dialog("option", "closeOnEscape", true);
               // $("#popupSPL").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               //handlejsonSPLPopup(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
          //  handlejsonModPageGrid(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),fnname,control1, hdncolumn, cntrl);
	handlejsonQueue(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, cntrl);
			}
	ajaxindicatorstop();
					
			}		//ajaxindicatorstop();
			else
			{
			alert(LoadFrmXML("V0072"));
            	ajaxindicatorstop();
			}
			},
			
            error: function (xml1)
            {
				//alertify.alert(LoadFrmXML("V0126"));
					alert(LoadFrmXML("V0126"));
				OP="Fail";
				//ajaxindicatorstop();
            }
                                  
               
    	   });
	   
	   
}

//QueueLlrgdata end


//PgMdGrdLrgdata start

function FncallModPageGridLrgData(cntrl, fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");
  //ajaxindicatorstart("Loading.. Please wait");
   
   var spname = par.spname
var Param = par.Param
var brid = par.brid
var MnuId=par.MnuId
  

  
  		$.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: Param, param2: brid, param3: MnuId, param4: "", param5: "",spname: spname },
               async: false,
               //dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   var	op=xml1.responseText;
					
			if (op != "")
			{				
var result = op.replace('<Resultset><a>','').replace('</a></Resultset>','').replace('<XMLLRGResult>','<Resultset>').replace('</XMLLRGResult>','</Resultset>').replace(/asetLRGDAta/g,'a')
				
	
	        result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
					if(json == "")
			{
					alert(LoadFrmXML("V0072"));
					  $('#'+fnname).empty();
					  	$("#"+fnname+"_info").text('Showing 0 to 0 of 0 entries');
						$("#"+fnname+"_paginate").remove();
						$("#"+fnname+"_wrapper").find(".dataTables_scroll").find(".dataTables_scrollHead").remove();
					return
			}
			
			
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
               // $("#popupSPL").dialog();
               // $("#popupSPL").dialog("option", "width", 805);
              //  $("#popupSPL").dialog("option", "closeOnEscape", true);
               // $("#popupSPL").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               //handlejsonSPLPopup(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            handlejsonModPageGrid(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),fnname,control1, hdncolumn, cntrl);
			}
	ajaxindicatorstop();
					
			}		//ajaxindicatorstop();
			else
			{
			alert(LoadFrmXML("V0072"));
            	ajaxindicatorstop();
			}
			},
			
            error: function (xml1)
            {
				//alertify.alert(LoadFrmXML("V0126"));
					alert(LoadFrmXML("V0126"));
				OP="Fail";
				//ajaxindicatorstop();
            }
                                  
               
    	   });
		
  
       //   

}

//PgMdGrdLrgdata end




function FncallwebserviceLrgNrmPgGrid(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

	   var spname = par.spname
var Param = par.Param
var brid = par.brid
var MnuId=par.MnuId
	
if(par.Mode!=undefined)
{
hdncolumn = par.Mode;
}

	//    $("#popup").dialog("open");

   // var
  	$.ajax({

               url: "/SBIReimbursement/UI_GetData",
               data: { param1: Param, param2: brid, param3: MnuId, param4: "", param5: "",spname: spname },
               async: false,
               //dataType: "json",
			    dataType: "text",
               type: 'POST',
               complete: function OnSuccess_submit(xml1) {
            	   var	op=xml1.responseText;
					
			if (op != "")
			{				
var result = op.replace('<Resultset><a>','').replace('</a></Resultset>','').replace('<XMLLRGResult>','<Resultset>').replace('</XMLLRGResult>','</Resultset>').replace(/asetLRGDAta/g,'a')
				
	
	        result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
					if(json == "")
			{
					alert(LoadFrmXML("V0072"));
					  $('#'+fnname).empty();
					  	$("#"+fnname+"_info").text('Showing 0 to 0 of 0 entries');
						$("#"+fnname+"_paginate").remove();
						$("#"+fnname+"_wrapper").find(".dataTables_scroll").find(".dataTables_scrollHead").remove();
					return
			}
			
			
        	//return;
            if (JSON.stringify(json.a) === '{}') {
               	alert(LoadFrmXML("V0072"));

            }
            else {
               // $("#popupSPL").dialog();
               // $("#popupSPL").dialog("option", "width", 805);
              //  $("#popupSPL").dialog("option", "closeOnEscape", true);
               // $("#popupSPL").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               //handlejsonSPLPopup(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, enablecontrol);
            handlejsonGrid(JSON.parse((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")),fnname, control1, hdncolumn, enablecontrol);
			}
	ajaxindicatorstop();
					
			}		//ajaxindicatorstop();
			else
			{
			alert(LoadFrmXML("V0072"));
            	ajaxindicatorstop();
			}
			},
			
            error: function (xml1)
            {
				//alertify.alert(LoadFrmXML("V0126"));
					alert(LoadFrmXML("V0126"));
				OP="Fail";
				//ajaxindicatorstop();
            }
                                  
               
    	   });
  
  
  
  /*
    $.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	
        	//alertify.alert(result);
           //return;
           result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                //$("#popup").dialog();
               // $("#popup").dialog("option", "width", 805);
               // $("#popup").dialog("option", "closeOnEscape", true);
                //$("#popup").dialog("option", "position", { my: "left top", at: "left bottom", of: $(cntrl) });
				
				
                handlejsonGrid(JSON.parse((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")),fnname, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });*/
}











///

function FncallwebserviceGrid(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

		if(par.Mode!=undefined)
		{
		hdncolumn = par.Mode;
		}

	//    $("#popup").dialog("open");

   // var
  
    $.ajax({
        url: "/InterfaceDemo/FncallWebserviceGridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	
        	//alertify.alert(result);
           //return;
           result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                //$("#popup").dialog();
               // $("#popup").dialog("option", "width", 805);
               // $("#popup").dialog("option", "closeOnEscape", true);
                //$("#popup").dialog("option", "position", { my: "left top", at: "left bottom", of: $(cntrl) });
				
				
                handlejsonGrid(JSON.parse((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")),fnname, control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}

///////////QUEUE

function FncallQueue(cntrl, fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");
  //ajaxindicatorstart("Loading.. Please wait");
    $.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	//ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                //$("#popup").dialog();
               // $("#popup").dialog("option", "width", 805);
               // $("#popup").dialog("option", "closeOnEscape", true);
                //$("#popup").dialog("option", "position", { my: "left top", at: "left bottom", of: $(cntrl) });
            	handlejsonQueue(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),control1, hdncolumn, cntrl);
            }
        },
        error: function (xhr, status, error) {
			//ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err);
        }
    });
}
// Queue End

function FncallModPageGrid(cntrl, fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");
  //ajaxindicatorstart("Loading.. Please wait");
    $.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	//ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                //$("#popup").dialog();
               // $("#popup").dialog("option", "width", 805);
               // $("#popup").dialog("option", "closeOnEscape", true);
                //$("#popup").dialog("option", "position", { my: "left top", at: "left bottom", of: $(cntrl) });
            	handlejsonModPageGrid(JSON.parse(((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")).replace(/__/g, " ")),fnname,control1, hdncolumn, cntrl);
            }
        },
        error: function (xhr, status, error) {
			//ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err);
        }
    });
}


function FncallwebserviceChk(cntrl,fnname, par, control1,hdncolumn,enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

//    $("#popup").dialog("open");

   // var
    ajaxindicatorstart("Loading.. Please wait");
    $.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                $("#popup").dialog();
                $("#popup").dialog("option", "width", 805);
                $("#popup").dialog("option", "closeOnEscape", true);
                $("#popup").dialog("option", "position", { my: "right bottom", at: "left top", of: $(cntrl) });
               handlejsonChk(JSON.parse((JSON.stringify(json.a).replace(/~`/g, "/>")).replace(/`/g, "<")), control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
			ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}

function FncallPageGrid(cntrl, fnname, par, control1, hdncolumn, enablecontrol) {
    var popTable;
    sessionStorage.setItem('SessionMICRCODE', '0');

    //    $("#popup").dialog("open");
  ajaxindicatorstart("Loading.. Please wait");
    $.ajax({
        url: "/SBIReimbursement/GridView",
        type: "POST",
        async: false,
        //        data: par,
        data: par,
        //data: "{}",
      //  contentType: "application/json; charset=utf-8",
        //contentType: "application/json; charset=utf-8",
        //dataType: "json",
        success: function (result) {
        	ajaxindicatorstop();
        	//alertify.alert(result);
           //return;
            result = result.replace(/>null</g,"><");
        	var json = $.xml2json(result.replace(/&/g,"and"));
        	
        	//alertify.alert(json);
        	
        	//return;
            if (JSON.stringify(json.a) === '{}') {
                alert(LoadFrmXML("V0072"))

            }
            else {
                //$("#popup").dialog();
               // $("#popup").dialog("option", "width", 805);
               // $("#popup").dialog("option", "closeOnEscape", true);
                //$("#popup").dialog("option", "position", { my: "left top", at: "left bottom", of: $(cntrl) });
            	handlejsonPageGrid(JSON.parse(((((JSON.stringify(json.a).replace(/`~~`/g, "</")).replace(/~`/g, "/>")).replace(/`~/g, ">")).replace(/`/g, "<")).replace(/__/g, " ")),fnname,control1, hdncolumn, enablecontrol);
            }
        },
        error: function (xhr, status, error) {
			ajaxindicatorstop();
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
}


//////////////

var dtData1 = {};
var myData;
var popTable;
var popTableModPageGrid;
var popTablePageGrid;
var popTableChk;

function handlejsonQueue(result, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
    //to re-init datatable


   // myData = JSON.parse(result.d);

    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
    }

    });
    if (!(typeof popTable === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

        //popTable.fnDestroy();
		if(popTable[0].id == "Table1")
		{
        popTable.fnDestroy();
		}
        popTable = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
        //Remove all the DOM elements
        $('#Table1').empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
 //added by manimala... <<start>> for date sorting
        if (dtData1[i].indexOf('Date') !== -1)  {obj["sType"] =  "date"; }
      //added by manimala... <<end>>
        dtcolumn.push(obj);
    }

 if(myData[0].Actions == "")
	{
    	dtData = [];
	}

	
		var cc = parseInt(count)-3;
	
	var hideClm = [];
	
	var hideClm1='[';
	
	for(c=parseInt(cc);c<count;c++)
	{
		if(c==parseInt(count)-1)
		hideClm1=hideClm1+c.toString();
	else
		hideClm1=hideClm1+c.toString()+',';
		
	}
		
	hideClm1=hideClm1+']';
	hideClm = hideClm1;

	
    if (typeof popTable === "undefined" || popTable === null) {
        popTable = $('#Table1').dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 7,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "270",
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
			 "aoColumnDefs": [ { "sClass": "dpass", "aTargets": jQuery.parseJSON(hideClm)} ],
            "fnDrawCallback": function (oSettings) {
            	
            	

            }
            });


            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

			// For Queue Count Start
			
			//$("#"+$("ul").find('.active').attr('id')).find('span').attr('id').text();
			//$("#"+$("#"+($("ul").find('.active').attr('id'))).find('span').attr('id')).text($('#Table1_info').text().split(" ")[5])
			$("#"+$(enablecontrol).find('span').attr('id')).text($('#Table1_info').text().split(" ")[5])
			
// For Side Tree Inbox/Queue Count Start			
var QmainMenuID = $(window.parent.document).find('ul.tabs li.active').attr('name').split('~')[0];
var QSubMenuClass = $(enablecontrol).attr('name');
var QIcon="";

if($($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()).length == 2)
{
	QIcon= $($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()[0]).prop('outerHTML');
}
else if($($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()).length == 3)
{
		QIcon= $($(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').contents()[1]).prop('outerHTML');
}
else
{
QIcon = $(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').text();	
}

var QSubMenuTXT = QIcon + QSubMenuClass.replace(/_/g,' ') + ' (' + $('#Table1_info').text().split(" ")[5] +')';

$(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').text('');
$(window.parent.document).find('.'+QmainMenuID).find('.'+QSubMenuClass).find('a').append(QSubMenuTXT)
// For Side Tree Inbox/Queue Count End			
			
			//  For Queue Count End
			
			
			
        //$(".dataTables_scrollHead").remove();
  /*          $("#Table1 tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTable.fnGetData(this);
                                
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

                            if ($(curcontl[i]).attr("id") == "mode") {
                                //#region If you want Trigger Event Reg Here
                                $("#ActivityID").trigger('change');
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
                
               // $("#popup").dialog("close");

            });
            
*/


        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

        
       
        var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="Add" style="width:50px"  class="search_init" /></td>';

            footervalue = '<tfoot><tr>' + footervalue + '</tr><tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $("#Table1").append(footervalue);
            //  $('#Table1').dataTable()
            //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#Table1').dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#Table1').dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

}


//
function handlejsonPageGrid(result, TableID, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
    //to re-init datatable


   // myData = JSON.parse(result.d);
//
    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
		case 36:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]]]);
            break;
		case 37:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]]]);
            break;
		case 38:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]]]);
            break;
		case 39:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]]]);
            break;
		case 40:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]]]);
            break;
		case 41:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]]]);
            break;
		case 42:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]]]);
            break;
		case 43:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]]]);
            break;
		case 44:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]]]);
            break;
		case 45:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]]]);
            break;
		case 46:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]]]);
            break;
    }

    });
    if (!(typeof popTablePageGrid === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

		if(popTablePageGrid !="undefined")
		{

		if(popTablePageGrid[0].id == TableID || popTablePageGrid[0].id == Table2 )
		{
        popTablePageGrid.fnDestroy();
		}
        popTablePageGrid = null;
        // $('#' + TableID).dataTable( {"bDestroy": true	} );
      //  popTable.fnDestroy();
        //Remove all the DOM elements
        $('#' + TableID).empty();
		}
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
	
    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
        dtcolumn.push(obj);
    }



    if (typeof popTablePageGrid === "undefined" || popTablePageGrid === null) {
        popTablePageGrid = $('#' + TableID).dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 7,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "270",
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
            "fnDrawCallback": function (oSettings) {
            	
          /*var a= $("#"+TableID).parents('div').closest('.dataTables_scroll').find('.dataTables_scrollHeadInner').find('thead');
                
                $("#"+TableID).parents('div').closest('.dataTables_scroll').find('.dataTables_scrollHeadInner').find('thead')
                 
                 $("#"+TableID).closest('table').find('thead').remove();
                 $("#"+TableID).prepend(a);
                 $("#"+TableID).parents('div').closest('.dataTables_scroll').find('.dataTables_scrollHead').remove();
				  
				  var GridNodeName =$("#"+TableID).attr('name');
                  var LabelValue = LoadGridLablName(GridNodeName);
                  

                  if($("#"+TableID).find('thead tr').length >= 2)
                {
                  LabelValue="";
                 $("#"+TableID).find('thead').append(LabelValue);
                }
                  
                  else
                  {
                  $("#"+TableID).find('thead').prepend(LabelValue);
                  $("#"+TableID).find('thead').find('tr:nth-child(2)').css('display','none');
                  }*/
                  
                 

            }
            });


            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

        //$(".dataTables_scrollHead").remove();
           $('#' + TableID + " tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTablePageGrid.fnGetData(this);
                                
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
							if ($(curcontl[i]).attr("id") == "CUSM_OldPrcsID") {
                                //#region If you want Trigger Event Reg Here
                               CusUpdation();
                                
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "RCBD_ID") {
                                //#region If you want Trigger Event Reg Here
                               GetCusDetls();
                                
                                //#endregion
                            }
							
								//Added by Manimala --Start
							if ($(curcontl[i]).attr("id") == "EditLRCN_PrCode") {
                               
								 $("#GridClkChk").val('Clk');
                                
                            }
							//end
							
						
							
							

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

              //  $("#popup").dialog("close");

            });
            



        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#' + TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

        
       
        var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="Add" style="width:50px"  class="search_init" /></td>';

            footervalue = '<tfoot><tr>' + footervalue + '</tr></tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $('#' + TableID).append(footervalue);
            //  $('#' + TableID).dataTable()
            //        $('#' + TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTablePageGrid.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#' + TableID).dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#' + TableID).dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

}


//




function handlejson(result, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
	
    //to re-init datatable


   // myData = JSON.parse(result.d);
//
    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
		case 36:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]]]);
            break;
		case 37:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]]]);
            break;
		case 38:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]]]);
            break;
		case 39:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]]]);
            break;
		case 40:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]]]);
            break;
		case 41:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]]]);
            break;
		case 42:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]]]);
            break;
		case 43:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]]]);
            break;
		case 44:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]]]);
            break;
		case 45:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]]]);
            break;
		case 46:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]]]);
            break;
    }

    });
	
	if(popTableChk !=null)
	{
		
		if(popTableChk[0].id == "Table1")
		{
        popTableChk.fnDestroy();
		}
        popTableChk = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTable.fnDestroy();
        //Remove all the DOM elements
        $('#Table1').empty();
		
	}
  
	
	if(popTable !=null)
	{
	
	if (!(typeof popTable === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

		if(popTable[0].id == "Table1")
		{
        popTable.fnDestroy();
		}
        popTable = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTable.fnDestroy();
        //Remove all the DOM elements
        $('#Table1').empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
	}

    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
        dtcolumn.push(obj);
    }



    if (typeof popTable === "undefined" || popTable === null) {
        popTable = $('#Table1').dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 6,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "210",
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
            "fnDrawCallback": function (oSettings) {

            }
            });


            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

        //$(".dataTables_scrollHead").remove();
            $("#Table1 tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTable.fnGetData(this);
                                
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
				if ($(curcontl[i]).attr("id") == "COR_SNDR_BIC")
                            {
                                //#region If you want Trigger Event Reg Here
                                $("#COR_SNDR_BIC").trigger('change');
                                
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
                                $("#WFName").trigger('change');
                                
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CUSM_OldPrcsID") {
                                //#region If you want Trigger Event Reg Here
                               CusUpdation();
                                
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "LNAP_Product") {
                                //#region If you want Trigger Event Reg Here
                             // PrdFundDsbl();
							  ProdFundDsbl();
							  chkMonths();
							  chkProperty();
							  clrfiledsprod();
							  //LonAmtVal();
                                
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "LNAP_PrgName") {
                                //#region If you want Trigger Event Reg Here
							  LonAmtVal();
							  LonTenorVal();
                              //clrfiledsprog();
                                //#endregion
                            }
							
							
							if ($(curcontl[i]).attr("id") == "LNAP_PrCode") {
							//#region If you want Trigger Event Reg Here
							ClrPrdDetl();
							//#endregion
							}
							
							if ($(curcontl[i]).attr("id") == "LNAP_SubProduct") {
                                //#region If you want Trigger Event Reg Here
                             PrpseDsbl();
							 LonAmtVal();
                            //clrfiledssubprod();
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "FNAV_FldInvstgnId") {
                                //#region If you want Trigger Event Reg Here
                            FldDsbl('FI');
                                
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "FNAV_RiskCntnmtUnitId") {
                                //#region If you want Trigger Event Reg Here
                            FldDsbl('RCU');
                                
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "FNAV_LegalId") {
                                //#region If you want Trigger Event Reg Here
                            FldDsbl('LGL');
                                
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "FNAV_TchnclEvaltnId") {
                                //#region If you want Trigger Event Reg Here
                            FldDsbl('VAL');
                                
                                //#endregion
                            }
							
							if ($(curcontl[i]).attr("id") == "FNAV_CAId") {
                                //#region If you want Trigger Event Reg Here
                            FldDsbl('CA');
                                
                                //#endregion
                            }
				

if ($(curcontl[i]).attr("id") == "RightsReviewName") {
								//#region If you want Trigger Event Reg Here
								$("#QueRgtsviewlevel").trigger("click");   
								//#endregion
								}
			
							if ($(curcontl[i]).attr("id") == "CUSA_Country") {
                                //#region If you want Trigger Event Reg Here
                             BsCntryDsbl();
                                //#endregion
                            }
								if ($(curcontl[i]).attr("id") == "CUSA_RCountry") {
                                //#region If you want Trigger Event Reg Here
                             BsResCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CIDT_Country") {
                                //#region If you want Trigger Event Reg Here
                            PrstEmplBsCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CIDT_PCountry") {
                                //#region If you want Trigger Event Reg Here
                             PrvsBsResCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "EditFormID") {
								//#region If you want Trigger Event Reg Here
								$("#EditTblclmName").trigger("click");   
									//#endregion
								}
if ($(curcontl[i]).attr("id") == "FromLBQVal") {
    //#region If you want Trigger Event Reg Here
   // $("#WFName").trigger('change');
	DispQIconRIght();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "TableName") {
	$("#BTNworks").trigger("click");
	//document.getElementById('UniqueParam').value="";

}
if ($(curcontl[i]).attr("id") == "EditUniqueParameter") {
    $("#EditBTNworks").trigger('click');

}
if ($(curcontl[i]).attr("id") == "HistTableName") {
 // fillHistTblClmNames();
	 $("#HistTblclmName").trigger('click');

}

if ($(curcontl[i]).attr("id") == "EditHistTableName") {
	 // fillHistTblClmNames();
		 $("#EditHistTblclmName").trigger('click');

	}
//edited by manimala...
if ($(curcontl[i]).attr("id") == "Desgn") {
    //#region If you want Trigger Event Reg Here
 $("#BTN_Usermap").trigger("click");
    
    //#endregion
}
if ($(curcontl[i]).attr("id") == "Userid") {
    //#region If you want Trigger Event Reg Here
 $("#BTN_Usermap").trigger("click");
    
    //#endregion
}
if ($(curcontl[i]).attr("id") == "EditLRCN_PurpCode") {
	
	$("#BTNRoi").trigger("click");

	}
if ($(curcontl[i]).attr("id") == "MenuName") {
	
Usersgridview();

}
if ($(curcontl[i]).attr("id") == "MenuLevelOne") {
	
Usersgridview1();

}
if ($(curcontl[i]).attr("id") == "MenuLevelTwo") {
	
Usersgridview2();

}
if ($(curcontl[i]).attr("id") == "Queue-ID") {
		
		Queuegridview();


}





if ($(curcontl[i]).attr("id") == "packageId") {
	$("#Rgtsgridview").trigger("click");

}
if ($(curcontl[i]).attr("id") == "FormName") {
	$("#Rgtsgridview").trigger("click");

}


if ($(curcontl[i]).attr("id") == "EditLRCN_PrCode") {
   
 $("#BTNRoi").trigger("click");
    
   
}

if ($(curcontl[i]).attr("id") == "EditLRCN_PurpCode") {
	
	$("#BTNRoi").trigger("click");

}

if ($(curcontl[i]).attr("id") == "EditLRCN_SchmCode") {
    //#region If you want Trigger Event Reg Here
 $("#BTNRoi").trigger("click");
    
    //#endregion
}

if ($(curcontl[i]).attr("id") == "WFIDHist") {
    //#region If you want Trigger Event Reg Here
 REPTHISTPAGEDATA()
    
    //#endregion
}

if ($(curcontl[i]).attr("id") == "PrcsID") {
    //#region If you want Trigger Event Reg Here
  $("#GetFileHistData").trigger("click");
    //#endregion
}
if ($(curcontl[i]).attr("id") == "PropPrcsID") {
    //#region If you want Trigger Event Reg Here
  AttachDMSFetch();
    //#endregion
}
if ($(curcontl[i]).attr("id") == "CustPrcsID") {
    //#region If you want Trigger Event Reg Here
  AttachDMSFetch();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "NewMaintableName") {
	FetchAddTbl();
	ClrList();
}

if ($(curcontl[i]).attr("id") == "NewWFName") {
	ClrTxt();
}

if ($(curcontl[i]).attr("id") == "EditMaintableName") {
	 // fillHistTblClmNames();
		 $("#EditAddTblName").trigger('click');

	}
	
	 
if ($(curcontl[i]).attr("id") == "HEditTabshwclm") {
    //#region If you want Trigger Event Reg Here
 //$("#BTNRoi").trigger("click");
  ForEditRightClmBlur();  
    //#endregion
}


if ($(curcontl[i]).attr("id") == "EditTableName") {
    
 $("#EditTableName").trigger("change");
    
}


if ($(curcontl[i]).attr("id") == "EVSM_SchmCode") {
    //#region If you want Trigger Event Reg Here
  GetROIEMI();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "wkfwid") {
    //#region If you want Trigger Event Reg Here
  Getfilestatusflddata();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "ReviewName") {


	clrscr();
	FetchTblColName();
	fetchdata();
	}

  if ($(curcontl[i]).attr("id") == "OFIP_AcctID") {
    $("#OFIP_AcctID").trigger("change");
    
} 


  //loan proppsal scheme end
  
  if ($(curcontl[i]).attr("id") == "LACD_RepaymntPrd") {
   
   
  emi('LACD_LonAmt','LACD_ROI','LACD_RepaymntPrd','LACD_EMI');
    
}
      
	  
	  
  if ($(curcontl[i]).attr("id") == "LDCR_OldPrcsID")
  {
                                //#region If you want Trigger Event Reg Here
     LedUpdation();
                                
                                //#endregion
     }
	  if ($(curcontl[i]).attr("id") == "CUCR_OldPrcsID") 
	  {
                                //#region If you want Trigger Event Reg Here
       CusUpdation();
                                
                                //#endregion
       }
 if ($(curcontl[i]).attr("id") == "LNAP_CusName")
								 {
                                //#region If you want Trigger Event Reg Here
                            	ViewAppDtl();
								
                                //#endregion
                            }	   
	  if ($(curcontl[i]).attr("id") == "ELDT_ApplId")
								 {
                               
                            	GetOccpdtl()

                            }
if ($(curcontl[i]).attr("id") == "BNKD_ApplId")
								 {
                               
                            	GetApplntval();

                            }		


if ($(curcontl[i]).attr("id") == "DBDT_AcctNo")
								 {
                               
						 	LonTOFund();
							PFCltnChk();

                            }

  if ($(curcontl[i]).attr("id") == "LNAP_LonCpyNo") {
   
   
   RefrnceLonCpy();
    
}

	  if ($(curcontl[i]).attr("id") == "CusCBLIdPropNo") {
   
   
   CibilAttchVw();
    
}

 if ($(curcontl[i]).attr("id") == "FGDG_GrpName") {
   GrpDetlDsbl()
    
} 			

				
if ($(curcontl[i]).attr("id") == "DADL_FacltyCde") {
   
   GetDsbDetls()
    
} 			

if ($(curcontl[i]).attr("id") == "FDSM_SecCode") {
   
   GetMrtgSecDetls()
    
} 						
if ($(curcontl[i]).attr("id") == "FDSH_SecCode") {
   
   GetHypSecDetls()
    
} 	
if ($(curcontl[i]).attr("id") == "FDSP_SecCode") {
   
   GetPldgSecDetls()
    
} 	
	
if ($(curcontl[i]).attr("id") == "FDSG_SecCode") {
   
   GetGrntSecDetls()
    
} 	
if ($(curcontl[i]).attr("id") == "FDSE_SecCode") {
   
   GetEscrSecDetls()
    
} 	
	
if ($(curcontl[i]).attr("id") == "FDSL_SecCode") {
   
   GetLicSecDetls()
    
}
if ($(curcontl[i]).attr("id") == "FDPC_SecCode") {
   
   GetPtntSecDetls()
    
}
if ($(curcontl[i]).attr("id") == "FDMS_SecCode") {
   
   GetshpSecDetls()
    
}
if ($(curcontl[i]).attr("id") == "CCCC_BrID") {
   
   GetCustCrDetls()
    
}

if ($(curcontl[i]).attr("id") == "GPDL_GrpName") {
	
	//$("#BTNGrpPromoDetl").trigger("click");
	 getGrpData();

	}
	if ($(curcontl[i]).attr("id") == "CCCC_ConstitnCd") {
	
	//$("#BTNGrpPromoDetl").trigger("click");
	setRegandCINMnd();

	}
	
	//Security Constitution Start
	if ($(curcontl[i]).attr("id") == "FDSM_SecConsCode") {
	
	setRegandCINMnd('FDSM_SecConsCode','FDSM_RegNo','FDSM_SecProvCIN','FDSM_SecProvAadhar');

	}
	
	if ($(curcontl[i]).attr("id") == "FDSH_SecConsCode") {
	
	setAdhrCINMnd('FDSH_SecConsCode','FDSH_SecProvCIN','FDSH_SecProvAadhar');

	}
	if ($(curcontl[i]).attr("id") == "FDSP_SecConsCode") {
	
	setAdhrCINMnd('FDSP_SecConsCode','FDSP_SecProvCIN','FDSP_SecProvAadhar');

	}
	if ($(curcontl[i]).attr("id") == "FDSG_SecConsCode") {
	
	setAdhrCINMnd('FDSG_SecConsCode','FDSG_SecProvCIN','FDSG_SecProvAadhar');

	}
	if ($(curcontl[i]).attr("id") == "FDSE_SecConsCode") {
	
	setAdhrCINMnd('FDSE_SecConsCode','FDSE_SecProvCIN','FDSE_SecProvAadhar');

	}
	
	if ($(curcontl[i]).attr("id") == "FDMS_SecConsCode") {
	
	setRegandCINMnd('FDMS_SecConsCode','FDMS_RegNo','FDMS_SecProvCIN','FDMS_SecProvAadhar');

	}
	
	//CCAD_ConstitnCd
	if ($(curcontl[i]).attr("id") == "CCAD_ConstitnCd") {
	
	setAdhrCINMnd('CCAD_ConstitnCd','CCAD_CIN','CCAD_Aadhar');

	}
	//Security Constitution End
	
	
	if ($(curcontl[i]).attr("id") == "PRSD_SecCode") {
	
	//$("#BTNGrpPromoDetl").trigger("click");
	GetSecDetls();

	}
	
	
	
//CFSL
							if ($(curcontl[i]).attr("id") == "CCUU_CAMNo") {
								
	                               LoadData('CAM');   
	                            }
								
								if ($(curcontl[i]).attr("id") == "CCUU_SancLetterNo") {
								
	                               LoadData('SAN');   
	                            }
							
							if ($(curcontl[i]).attr("id") == "RMEX_CamNo") {
								
	                               LoadData();   
	                            }
							if ($(curcontl[i]).attr("id") == "CFDE_SancLetterNo") {
								
	                               LoadData();   
	                            }

							if ($(curcontl[i]).attr("id") == "FDSDGH_CAMNo") {
								
	                               GetTrggr();   
	                            }
								
							if ($(curcontl[i]).attr("id") == "RMED_DocType") {
								
	                              ManageDropChange('Load'); 
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

                $("#popup").dialog("close");

            });
            



        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

        
       
        var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="Add" style="width:50px"  class="search_init" /></td>';

            footervalue = '<tfoot><tr>' + footervalue + '</tr></tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $("#Table1").append(footervalue);
            //  $('#Table1').dataTable()
            //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#Table1').dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#Table1').dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

}



///

function handlejsonSPLPopup(result, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
	
    //to re-init datatable


   // myData = JSON.parse(result.d);
//
    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
		case 36:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]]]);
            break;
		case 37:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]]]);
            break;
		case 38:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]]]);
            break;
		case 39:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]]]);
            break;
		case 40:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]]]);
            break;
		case 41:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]]]);
            break;
		case 42:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]]]);
            break;
		case 43:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]]]);
            break;
		case 44:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]]]);
            break;
		case 45:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]]]);
            break;
		case 46:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]]]);
            break;
    }

    });
	
	if(popTableChk !=null)
	{
		
		if(popTableChk[0].id == "TableSPL")
		{
        popTableChk.fnDestroy();
		}
        popTableChk = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTable.fnDestroy();
        //Remove all the DOM elements
        $('#TableSPL').empty();
		
	}
  
	
	if(popTable !=null)
	{
	
	if (!(typeof popTable === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

		if(popTable[0].id == "TableSPL")
		{
        popTable.fnDestroy();
		}
        popTable = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTable.fnDestroy();
        //Remove all the DOM elements
        $('#TableSPL').empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
	}

    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
        dtcolumn.push(obj);
    }



    if (typeof popTable === "undefined" || popTable === null) {
        popTable = $('#TableSPL').dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 6,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "210",
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
            "fnDrawCallback": function (oSettings) {

            }
            });


            $(document).on('click', '#TableSPL tbody th', function () {

                alertify.alert('1');

            });

        //$(".dataTables_scrollHead").remove();
            $("#TableSPL tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTable.fnGetData(this);
                                
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
                                $("#WFName").trigger('change');
                                
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CUSM_OldPrcsID") {
                                //#region If you want Trigger Event Reg Here
                               CusUpdation();
                                
                                //#endregion
                            }
				

if ($(curcontl[i]).attr("id") == "RightsReviewName") {
								//#region If you want Trigger Event Reg Here
								$("#QueRgtsviewlevel").trigger("click");   
								//#endregion
								}
			
							if ($(curcontl[i]).attr("id") == "CUSA_Country") {
                                //#region If you want Trigger Event Reg Here
                             BsCntryDsbl();
                                //#endregion
                            }
								if ($(curcontl[i]).attr("id") == "CUSA_RCountry") {
                                //#region If you want Trigger Event Reg Here
                             BsResCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CIDT_Country") {
                                //#region If you want Trigger Event Reg Here
                            PrstEmplBsCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "CIDT_PCountry") {
                                //#region If you want Trigger Event Reg Here
                             PrvsBsResCntryDsbl();
                                //#endregion
                            }
							if ($(curcontl[i]).attr("id") == "EditFormID") {
								//#region If you want Trigger Event Reg Here
								$("#EditTblclmName").trigger("click");   
									//#endregion
								}
if ($(curcontl[i]).attr("id") == "FromLBQVal") {
    //#region If you want Trigger Event Reg Here
   // $("#WFName").trigger('change');
	DispQIconRIght();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "TableName") {
	$("#BTNworks").trigger("click");
	//document.getElementById('UniqueParam').value="";

}
if ($(curcontl[i]).attr("id") == "EditUniqueParameter") {
    $("#EditBTNworks").trigger('click');

}
if ($(curcontl[i]).attr("id") == "HistTableName") {
 // fillHistTblClmNames();
	 $("#HistTblclmName").trigger('click');

}

if ($(curcontl[i]).attr("id") == "EditHistTableName") {
	 // fillHistTblClmNames();
		 $("#EditHistTblclmName").trigger('click');

	}
//edited by manimala...

if ($(curcontl[i]).attr("id") == "Desgn") {
    //#region If you want Trigger Event Reg Here
 $("#BTN_Usermap").trigger("click");
    
    //#endregion
}
if ($(curcontl[i]).attr("id") == "Userid") {
    //#region If you want Trigger Event Reg Here
 $("#BTN_Usermap").trigger("click");
    
    //#endregion
}


if ($(curcontl[i]).attr("id") == "EditLRCN_PurpCode") {
	
	$("#BTNRoi").trigger("click");

	}
if ($(curcontl[i]).attr("id") == "MenuName") {
	
Usersgridview();

}
if ($(curcontl[i]).attr("id") == "MenuLevelOne") {
	
Usersgridview1();

}
if ($(curcontl[i]).attr("id") == "MenuLevelTwo") {
	
Usersgridview2();

}
if ($(curcontl[i]).attr("id") == "Queue-ID") {
		
		Queuegridview();


}




if ($(curcontl[i]).attr("id") == "packageId") {
	$("#Rgtsgridview").trigger("click");

}
if ($(curcontl[i]).attr("id") == "FormName") {
	$("#Rgtsgridview").trigger("click");

}


if ($(curcontl[i]).attr("id") == "EditLRCN_PrCode") {
   
 $("#BTNRoi").trigger("click");
    
   
}

if ($(curcontl[i]).attr("id") == "EditLRCN_PurpCode") {
	
	$("#BTNRoi").trigger("click");

}

if ($(curcontl[i]).attr("id") == "EditLRCN_SchmCode") {
    //#region If you want Trigger Event Reg Here
 $("#BTNRoi").trigger("click");
    
    //#endregion
}

if ($(curcontl[i]).attr("id") == "WFIDHist") {
    //#region If you want Trigger Event Reg Here
 REPTHISTPAGEDATA()
    
    //#endregion
}

if ($(curcontl[i]).attr("id") == "PrcsID") {
    //#region If you want Trigger Event Reg Here
  $("#GetFileHistData").trigger("click");
    //#endregion
}
if ($(curcontl[i]).attr("id") == "PropPrcsID") {
    //#region If you want Trigger Event Reg Here
  AttachDMSFetch();
    //#endregion
}
if ($(curcontl[i]).attr("id") == "CustPrcsID") {
    //#region If you want Trigger Event Reg Here
  AttachDMSFetch();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "NewMaintableName") {
	FetchAddTbl();
	ClrList();
}

if ($(curcontl[i]).attr("id") == "NewWFName") {
	ClrTxt();
}

if ($(curcontl[i]).attr("id") == "EditMaintableName") {
	 // fillHistTblClmNames();
		 $("#EditAddTblName").trigger('click');

	}
	
	 
if ($(curcontl[i]).attr("id") == "HEditTabshwclm") {
    //#region If you want Trigger Event Reg Here
 //$("#BTNRoi").trigger("click");
  ForEditRightClmBlur();  
    //#endregion
}


if ($(curcontl[i]).attr("id") == "EditTableName") {
    
 $("#EditTableName").trigger("change");
    
}


if ($(curcontl[i]).attr("id") == "EVSM_SchmCode") {
    //#region If you want Trigger Event Reg Here
  GetROIEMI();
    //#endregion
}

if ($(curcontl[i]).attr("id") == "wkfwid") {
    //#region If you want Trigger Event Reg Here
  Getfilestatusflddata();
    //#endregion
}







if ($(curcontl[i]).attr("id") == "ReviewName") {


	clrscr();
	FetchTblColName();
	fetchdata();
	}

  if ($(curcontl[i]).attr("id") == "OFIP_AcctID") {
    $("#OFIP_AcctID").trigger("change");
    
} 

 if ($(curcontl[i]).attr("id") == "LNAP_CusName") {
   ViewAppDtl()
    
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

               $("#popupSPL").dialog("close");
				//popTable.fnDestroy();
   if(popTable[0].id == "TableSPL")
		{
        popTable.fnDestroy();
		$('#TableSPL').empty();
		}
   
   
    //popTable.fnDestroy();
            });
            



        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

        
       
        var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="Add" style="width:50px"  class="search_init" /></td>';

            footervalue = '<tfoot><tr>' + footervalue + '</tr></tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $("#TableSPL").append(footervalue);
            //  $('#Table1').dataTable()
            //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#Table1').dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#Table1').dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }
 
}

///




function handlejsonChk(result, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
    //to re-init datatable


   // myData = JSON.parse(result.d);
//
    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
		case 36:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]]]);
            break;
		case 37:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]]]);
            break;
		case 38:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]]]);
            break;
		case 39:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]]]);
            break;
		case 40:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]]]);
            break;
		case 41:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]]]);
            break;
		case 42:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]]]);
            break;
		case 43:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]]]);
            break;
		case 44:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]]]);
            break;
		case 45:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]]]);
            break;
		case 46:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]]]);
            break;
    }

    });
	
	
if(popTableChk!=null)
{
    if (!(typeof popTableChk === "undefined")) {
        /* destroy table*/
        //Delete the datable object first
		 
       
		
		if(popTableChk[0].id == "Table1")
		{
        popTableChk.fnDestroy();
		}
        popTableChk = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTableChk.fnDestroy();
        //Remove all the DOM elements
        $('#Table1').empty();
        /* destry table*/
        //        oSettings = popTableChk.fnSettings();
        //        popTableChk.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTableChk.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTableChk.fnDraw();

    }
	}
	if(popTable !=null)
	{
	
	if (!(typeof popTable === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

		if(popTable[0].id == "Table1")
		{
        popTable.fnDestroy();
		}
        popTable = null;
        // $('#Table1').dataTable( {"bDestroy": true	} );
       // popTable.fnDestroy();
        //Remove all the DOM elements
        $('#Table1').empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
	}
    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
        dtcolumn.push(obj);
    }
		


    if (typeof popTableChk === "undefined" || popTableChk === null) {
        popTableChk = $('#Table1').dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],

			 'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 6,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "210",
            "scrollX": true,
           
            "sPaginationType": "full_numbers",
			
            "fnDrawCallback": function (oSettings) {
			
	

            }
            });


            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

			 $("#Table1 tbody").live("tr", "click", function () {
				alertify.alert("1")
			});
			
			//$("#Table1").delegate("tr.rows", "click", function ()
	//$('#Table1').on('click', '#Qselectchk', function()
	$("#Table1 tr").click(function(event)
	{
	
	check = $("input:checked" );
    if(check) {
         
		event.stopPropagation();
    } else {
        
		return false;
    }
	  
	  
	   
	//$("#popup").dialog("close");	
	});
	//$("#Qselectchk").on("click", function(){

	
			
		/*	
        //$(".dataTables_scrollHead").remove();
         $('#Table1_wrapper').on('click', '#Save', function(){
       // $(document).on('click', '#Table1 tbody tr', function () {
		   
		   var data1="";
		   if($("input:checked" ).length == 0)
	{
	alertify.alert("Please Select a File");
	$("input:checked" ).prop( "checked", false );
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

                $("#popup").dialog("close");

            });
            
*/


        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTableChk.fnFilter(this.value, index, 1, 1);

        //                          });

        
      
       
		var HeaderBtnval="";
	   var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTableChk.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
		
					HeaderBtnval +='&nbsp;&nbsp;&nbsp;<img class="buttonPRS" id="Save" style ="width:25px; height:20px;" title="Insert" type="button" src=\"/SBIReimbursement/ThemeproLO/Common/Images/check1.png\"/>  (Click to insert)'
		
		
				$("#Table1").parents('div').find('.fg-toolbar').find(".dataTables_length").append(HeaderBtnval);
				 
       // footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="SAVE" style="width:50px"  class="search_init" /></td>';
		

            //footervalue = '<tfoot><tr>' + footervalue + '</tr></tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $("#Table1").append(footervalue);
            //  $('#Table1').dataTable()
            //        $('#Table1').after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTableChk.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#Table1').dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#Table1').dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

}



//

function handlejsonGrid(result,TableID, curcontl, hdncolumn, enablecontrol) {

    var dtData = [];
    var count = 0;
    //to re-init datatable

   // myData = JSON.parse(result.d);
  
    	myData = $(result);    	
    	
    var entry;
    var name;
         	
    entry = myData[0];
    
    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }
    
	if(hdncolumn != undefined)
	{
	if (hdncolumn.indexOf('|') != -1)
	{
	
	var GridType=hdncolumn.split('|')[1];
	var GridSize="";
	
	if (GridType == "Small")
	{
		GridSize = "160";
	}
	else if(GridType == "Medium")
	{
		GridSize = "260";
	}
	else if(GridType == "Large")
	{
		GridSize = "360";
	}
	else if(GridType == "ExtraLarge")
	{
		GridSize = "650";
	}
	else
	{
		GridSize = "160";
	}
	}
	else
	{
		GridSize = "160";
			hdncolumn=hdncolumn.split('|')[0];
	}
	
	}
	else
	{
		GridSize = "160";
	}
	
	

	
    $.each(myData, function (event) {

    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
		case 36:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]]]);
            break;
		case 37:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]]]);
            break;
		case 38:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]]]);
            break;
		case 39:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]]]);
            break;
		case 40:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]]]);
            break;
		case 41:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]]]);
            break;
		case 42:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]]]);
            break;
		case 43:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]]]);
            break;
		case 44:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]]]);
            break;
		case 45:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]]]);
            break;
		case 46:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]]]);
            break;
		case 47:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]]]);
            break;
		case 48:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]],this[dtData1[47]]]);
            break;
		case 49:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]],this[dtData1[47]],this[dtData1[48]]]);
            break;
		case 50:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]], this[dtData1[46]],this[dtData1[47]],this[dtData1[48]],this[dtData1[49]]]);
            break;
		case 51:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]], this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]]]);
            break;
		case 52:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]]]);
            break;
		case 53:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]]]);
            break;
		case 54:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]]]);
            break;
		case 55:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]]]);
            break;
		case 56:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]]]);
            break;
			
		case 57:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]]]);
            break;
		case 58:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]]]);
            break;
		case 59:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]]]);
            break;
		case 60:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]]]);
            break;
		case 61:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]]]);
            break;
		case 62:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]]]);
            break;
		case 63:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]]]);
            break;
		case 64:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]]]);
            break;
		case 65:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]]]);
            break;
		case 66:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]],this[dtData1[65]]]);
            break;		
		case 67:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]],this[dtData1[65]],this[dtData1[66]]]);
            break;	
		case 68:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]],this[dtData1[65]],this[dtData1[66]],this[dtData1[67]]]);
            break;	
		case 69:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]],this[dtData1[65]],this[dtData1[66]],this[dtData1[67]],this[dtData1[68]]]);
            break;	
		case 70:
			dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]], this[dtData1[35]], this[dtData1[36]], this[dtData1[37]], this[dtData1[38]], this[dtData1[39]], this[dtData1[40]], this[dtData1[41]], this[dtData1[42]], this[dtData1[43]], this[dtData1[44]], this[dtData1[45]],this[dtData1[46]], this[dtData1[47]],this[dtData1[48]],this[dtData1[49]],this[dtData1[50]],this[dtData1[51]],this[dtData1[52]],this[dtData1[53]],this[dtData1[54]],this[dtData1[55]],this[dtData1[56]],this[dtData1[57]],this[dtData1[58]],this[dtData1[59]],this[dtData1[60]],this[dtData1[61]],this[dtData1[62]],this[dtData1[63]],this[dtData1[64]],this[dtData1[65]],this[dtData1[66]],this[dtData1[67]],this[dtData1[68]],this[dtData1[69]]]);
            break;
    }

    });
    	
    if (!(typeof popTable === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

		
		
		
		if(popTable[0].id == "Table1")
		{
        popTable.fnDestroy();
		}
		
		/*if($('#TableSPL').html()!="")
		{
			$('#TableSPL').empty();
			$('#TableSPL').parents('div').find('thead').remove();
			
		}	*/	

		
       // popTable.fnDestroy();
        popTable = null;
        // $('#' + TableID).dataTable( {"bDestroy": true	} );
        //popTable.fnDestroy();
        //Remove all the DOM elements
        $('#' + TableID).empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
    //dynamically add column name
    var dtcolumn = [];
	
	

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
        dtcolumn.push(obj);
    }

    if(myData[0].Action == "")
	{
    	dtData = [];
	}
	var cnt =0;
	if (TableID == 'TableDevtn')
	{
	cnt = 9 
	}
	else
	{
	cnt=6
	}
	if (count>=6)
	{
	var cc = parseInt(count)-parseInt(cnt);
	
	var hideClm = [];
	
	var hideClm1='[';
	
	for(c=parseInt(cc);c<count;c++)
	{
		if(c==parseInt(count)-1)
		hideClm1=hideClm1+c.toString();
	else
		hideClm1=hideClm1+c.toString()+',';
		
	}
		
	hideClm1=hideClm1+']';
	hideClm = hideClm1;
	}
	
    if (typeof popTable === "undefined" || popTable === null) {
        popTable = $('#' + TableID).dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': false,
           "aaSorting": [],
            "pageLength": 10,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': false,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": GridSize,
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
			  //"aoColumnDefs": [{ "bVisible": false, "aTargets": jQuery.parseJSON(hideClm) }],
			  "aoColumnDefs": [ { "sClass": "dpass", "aTargets": jQuery.parseJSON(hideClm)} ],

            "fnDrawCallback": function (oSettings) {
            	
            	 var a= $("#"+TableID+"_wrapper").find('.dataTables_scrollHeadInner').find('thead');
				//var a= $('.dataTables_scrollHeadInner').find('thead');
                 
				$('.dataTables_scrollHeadInner').find('thead');
                  
                  $("#"+TableID).closest('table').find('thead').remove();
                  $("#"+TableID).prepend(a);
                 $("#"+TableID+"_wrapper").find('.dataTables_scrollHead').remove();
				 
                  				  
				  var GridNodeName =$("#"+TableID).attr('name');
                  var LabelValue = LoadGridLablName(GridNodeName);
                  

                  if($("#"+TableID).find('thead tr').length >= 2)
                {
                  LabelValue="";
                 $("#"+TableID).find('thead').append(LabelValue);
                }
                  
                  else
                  {
                  $("#"+TableID).find('thead').prepend(LabelValue);
                  $("#"+TableID).find('thead').find('tr:nth-child(2)').css('display','none');
                  }
                  
                 
                  
                  
            }
            });
        
       
        
      

        
        
            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

        //$(".dataTables_scrollHead").remove();
            $("#Table1 tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTable.fnGetData(this);
                                
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
                           
			      if ($(curcontl[i]).attr("id") == "TXT_BrkLinkId") {
                                $("#TXT_BrkLinkId").trigger('change');

                            }
                            if ($(curcontl[i]).attr("id") == "id_UserID") {
                                $("#id_UserID").trigger('change');

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

               // $("#popup").dialog("close");

            });
            



        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#' + TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

            
          //  $("#"+TableID+'_info').contents().filter(function(){
         //       return this.nodeType === 3;
         //   }).remove();
        
            $("#"+TableID+'_info').text('');
            
            
        var footervalue = "";
     //   $('.dataTables_info').empty();
        
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><a type="button" name="search_engine" id="" title="Add" style="width:20px;height:20px;" ><img id="New'+TableID+'" name="'+TableID+'" style="width:20px;height:20px;" class="Table2 NewProcess" src="SBIReimbursement/Common/Images/file_add.png"></a></td>';
            footervalue = '<tfoot><tr>' + footervalue + '</tr><tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');
 
            //footervalue = "";
			var reviewbtnchk= $($("#"+ TableID ).find("tbody tr td a")[0]).find("img").attr("title");
			
				var alength = $($("#"+ TableID ).find("tbody tr td")[0]).find('a').length;
		
		
		
				if(alength != 1)
			{
				
			if(hdncolumn== "NOEDT" || hdncolumn == "NODEL")
			{
			
			}	
else
{
footervalue ="";
	$("#"+ TableID +'_info').append(footervalue);
	
}	
			}
			
			
			//to increase the grid table width start
           $("#"+TableID).css('width','100%'); 
            //to increase the grid table width end
                        
            //  $('#' + TableID).dataTable()
            //        $('#' + TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#' + TableID).dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#' + TableID).dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

    //if($('#' + TableID).find('tbody').find('tr').find('td')[3].innerHTML == "Blank" )
    	//{
   // $('#' + TableID).find('tbody').find('tr').remove();
    	//}
}


function handlejsonModPageGrid(result,TableID, curcontl, hdncolumn, enablecontrol) {

 var dtData = [];
    var count = 0;
    //to re-init datatable


   // myData = JSON.parse(result.d);

    myData = $(result);
    
    var entry;
    var name;
    entry = myData[0];

    for (name in entry) {
        if (entry.hasOwnProperty(name)) {
            dtData1[count] = name;
            count++;
            //dtData1.push(name);

        }

    }


    $.each(myData, function (event) {


    	switch (count) {
        case 1:
            dtData.push([this[dtData1[0]]]);
            break;
        case 2:
            dtData.push([this[dtData1[0]], this[dtData1[1]]]);
            break;
        case 3:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]]]);
            break;
        case 4:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]]]);
            break;
        case 5:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]]]);
            break;
        case 6:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]]]);
            break;
        case 7:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]]]);
            break;
        case 8:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]]]);
            break;
        case 9:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]]]);
            break;
        case 10:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]]]);
            break;
        case 11:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]]]);
            break;
        case 12:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]]]);
            break;
        case 13:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]]]);
            break;
        case 14:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]]]);
            break;
        case 15:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]]]);
            break;
        case 16:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]]]);
            break;
        case 17:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]]]);
            break;
        case 18:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]]]);
            break;
        case 19:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]]]);
            break;
        case 20:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]]]);
            break;
        case 21:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]]]);
            break;
        case 22:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]]]);
            break;
        case 23:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]]]);
            break;
        case 24:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]]]);
            break;
        case 25:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]]]);
            break;
        case 26:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]]]);
            break;
        case 27:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]]]);
            break;
        case 28:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]]]);
            break;
        case 29:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]]]);
            break;

        case 30:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]]]);
            break;
        case 31:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]]]);
            break;
        case 32:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]]]);
            break;
        case 33:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]]]);
            break;
        case 34:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]]]);
            break;
        case 35:
            dtData.push([this[dtData1[0]], this[dtData1[1]], this[dtData1[2]], this[dtData1[3]], this[dtData1[4]], this[dtData1[5]], this[dtData1[6]], this[dtData1[7]], this[dtData1[8]], this[dtData1[9]], this[dtData1[10]], this[dtData1[11]], this[dtData1[12]], this[dtData1[13]], this[dtData1[14]], this[dtData1[15]], this[dtData1[16]], this[dtData1[17]], this[dtData1[18]], this[dtData1[19]], this[dtData1[20]], this[dtData1[21]], this[dtData1[22]], this[dtData1[23]], this[dtData1[24]], this[dtData1[25]], this[dtData1[26]], this[dtData1[27]], this[dtData1[28]], this[dtData1[29]], this[dtData1[30]], this[dtData1[31]], this[dtData1[32]], this[dtData1[33]], this[dtData1[34]]]);
            break;
    }

    });
    if (!(typeof popTableModPageGrid === "undefined")) {
        /* destroy table*/
        //Delete the datable object first

        popTableModPageGrid.fnDestroy();
		if(popTableModPageGrid[0].id == "Table1")
		{
        popTableModPageGrid.fnDestroy();
		}
        popTableModPageGrid = null;
        // $('#'+TableID).dataTable( {"bDestroy": true	} );
        //Remove all the DOM elements
        $('#'+TableID).empty();
        /* destry table*/
        //        oSettings = popTable.fnSettings();
        //        popTable.fnClearTable(this);
        //        for (var i = 0; i < dtData.length; i++) {
        //            popTable.oApi._fnAddData(oSettings, dtData[i]);
        //        }
        //        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        //        popTable.fnDraw();

    }
    //dynamically add column name
    var dtcolumn = [];

    for (var i = 0; i < count; i++) {
        var obj = {};
        obj["sTitle"] = dtData1[i];
//added by manimala... <<start>> for date sorting 
   if (dtData1[i].indexOf('Date') !== -1)  {obj["sType"] =  "date"; }
       //added by manimala... <<end>>
        dtcolumn.push(obj);
    }

 if(myData[0].Actions == "")
	{
    	dtData = [];
	}

	
		var cc = parseInt(count)-3;
	
	var hideClm = [];
	
	var hideClm1='[';
	
	for(c=parseInt(cc);c<count;c++)
	{
		if(c==parseInt(count)-1)
		hideClm1=hideClm1+c.toString();
	else
		hideClm1=hideClm1+c.toString()+',';
		
	}
		
	hideClm1=hideClm1+']';
	hideClm = hideClm1;

	
    if (typeof popTableModPageGrid === "undefined" || popTableModPageGrid === null) {
        popTableModPageGrid = $('#'+TableID).dataTable({  //grid is the id of the table
            //'aaData': [["0000000003", "Non Member", "Samuel", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000004", "Non Member", "Uche", "", "", "", "/Date(252441000000)/", "", "", "", "Salaried", "Male", "S", "Graduate", ""], ["0000000005", "Non Member", "ngozi", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000006", "Non Member", "Mohammed Ameen", "", "", "", "/Date(220905000000)/", "", "", "", "Salaried", "Male", "M", "Post Graduate", ""], ["0000000011", "Non Member", "Suresh", "", "", "", "/Date(315513000000)/", "", "", "", "Business", "Male", "S", "Under Graduate", ""], ["0000000012", "Non Member", "ff", "", "", "", "/Date(567973800000)/", "", "", "", "Others", "Male", "M", "", ""], ["0000000013", "Non Member", "sss", "", "", "", "/Date(631132200000)/", "", "", "", "Salaried", "Male", "S", "", ""], ["0000000014", "Non Member", "SSS", "", "", "", "/Date(252441000000)/", "", "", "", "Not-Avail", "Male", "S", "", ""], ["0000000015", "Non Member", "RAjagovarthanan", "", "", "", "/Date(268165800000)/", "0444666666", "rajagovarthanan@encoretheme.com", "", "Salaried", "Male", "M", "Under Graduate", ""], ["0000000016", "Non Member", "Gabriel", "", "", "", "/Date(220905000000)/", "", "", "", "", "Male", "", "", ""], ["0000000017", "Non Member", "ddd", "", "", "", "/Date(567973800000)/", "", "", "", "Prof", "Male", "S", "", ""], ["0000000018", "Non Member", "Kumar", "", "", "", "/Date(567973800000)/", "", "", "", "Business", "Male", "M", "", ""]],
            //"aoColumns": [{ "sTitle": "Cusid" }, { "sTitle": "custype" }, { "sTitle": "FullName" }, { "sTitle": "Permanent Address" }, { "sTitle": "Business Address" }, { "sTitle": "Correspondence  Address" }, { "sTitle": "DOB" }, { "sTitle": "Phone No" }, { "sTitle": "Email" }, { "sTitle": "age" }, { "sTitle": "Occupation" }, { "sTitle": "Gender" }, { "sTitle": "Maritalstatus" }, { "sTitle": "Education" }, { "sTitle": "idno"}],
            'aaData': dtData,
            "aoColumns": dtcolumn, 
            "bAutoWidth": false,
            "autoWidth": false,
            'bPaginate': true,
           "aaSorting": [],
            "pageLength": 7,
            "bDeferRender": true,
            'bInfo': true,
            'bFilter': true,
            "bDestroy": true,
            "bJQueryUI": true,
           
           "scrollY": "270",
            "scrollX": true,
           

            "sPaginationType": "full_numbers",
			 "aoColumnDefs": [ { "sClass": "dpass", "aTargets": jQuery.parseJSON(hideClm)} ],
            "fnDrawCallback": function (oSettings) {
            	
            	

            }
            });


            $(document).on('click', '#Table1 tbody th', function () {

                alertify.alert('1');

            });

			// For Queue Count Start
			
			//$("#"+$("ul").find('.active').attr('id')).find('span').attr('id').text();
			//$("#"+$("#"+($("ul").find('.active').attr('id'))).find('span').attr('id')).text($('#Table1_info').text().split(" ")[5])
			$("#"+$(enablecontrol).find('span').attr('id')).text($('#Table1_info').text().split(" ")[5])
			
			//  For Queue Count End
			
			
			
        //$(".dataTables_scrollHead").remove();
  /*          $("#Table1 tbody").delegate("tr", "click", function () {
       // $(document).on('click', '#Table1 tbody tr', function () {

                var aData = popTable.fnGetData(this);
                                
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

                            if ($(curcontl[i]).attr("id") == "mode") {
                                //#region If you want Trigger Event Reg Here
                                $("#ActivityID").trigger('change');
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
                
               // $("#popup").dialog("close");

            });
            
*/


        //                          $("#Table1_length").append('<table><tr><td><select><option value="1">Id</option></select></td><td><input id="searchpopup" type="text"/></td></tr></table><input id="checkvalueexist" value="search" type="button"/>');
        //        $('#'+TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
        //                          $('#searchpopup').keyup(function () {
        //                              index = 0;
        //                              popTable.fnFilter(this.value, index, 1, 1);

        //                          });

        
       
        var footervalue = "";
     /*   if (typeof hdncolumn != "undefined") {
            for (var ii = 0; ii < count; ii++) {

                if (typeof hdncolumn[ii] != "undefined") {
                    popTable.fnSetColumnVis(hdncolumn[ii], false);
                }
                else {
                    footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                }
            }
        }

        else {

            for (var iii = 0; iii < count; iii++) {
               
                footervalue += '<td><input type="button" name="search_engine" style="width:15px"  class="search_init" /></td>';
                

            }
        }*/
        footervalue += '<td><input type="button" name="search_engine" id="GridAdd" value="Add" style="width:50px"  class="search_init" /></td>';

            footervalue = '<tfoot><tr>' + footervalue + '</tr><tfoot>';
            //$("#Table1_length").append('<input id="checkvalueexist" value="search" type="button"/>');

            footervalue = "";
            $("#Table1").append(footervalue);
            //  $('#'+TableID).dataTable()
            //        $('#'+TableID).after('<table><tr><td><input type="text"/></td></tr></table>');
            var asInitVals = [];
            /*footer search*/
            $("tfoot input").keyup(function () {
                /* Filter on the column (the index) of this element */
                popTable.fnFilter(this.value, $("tfoot input").index(this));
            });

        
        


        /*
        * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
        * the footer
        */
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });



        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
        /*footer search*/
        $('#checkvalueexist').click(function () {
           
            var nNodes = $('#'+TableID).dataTable().fnGetNodes();
            // alertify.alert(nNodes.length);
            if (nNodes.length > 0) {
                for (var i = 0; i < nNodes.length; i++) {

                    var dat = $('#'+TableID).dataTable().fnGetData(nNodes[i]);
                    if ('36' == dat[0]) {

                        alert(LoadFrmXML("V0073") + dat[0]);
                        return false;
                    }
                    if (dat[1] == 'The Proposed Projec') {
                        alert(LoadFrmXML("V0074") + dat[1]);
                        return false;
                    }


                }

            }
        });
    }

}




function handlejson_Whdn(result, curcontl, hdncolumn, enablecontrol) {
    Assignpopup = curcontl;
    AssignJSON_To_Grid_WImg(result.d, oTable_Grid_Branch);
}



function handlejson_Whdn_FWelBranch(result, curcontl, hdncolumn, enablecontrol) {
    Assignpopup = curcontl;
    AssignJSON_To_Grid_WImg_ForPrdID(result.d, oTable_PopBrAcctID);
}

function handlejson_Whdn_FBrID(result, curcontl, hdncolumn, enablecontrol) {
    Assignpopup = curcontl;
    AssignJSON_To_Grid_WImg_ForPrdID(result.d, oTable_Grid_Branch);
}




function handlejson_Whdn_FPrdID(result, curcontl, hdncolumn, enablecontrol) {
    Assignpopup = curcontl;
    AssignJSON_To_Grid_WImg_ForPrdID(result.d, oTable_Grid_PrdID);
}
function handlejson_Whdn_FAcctID(result, curcontl, hdncolumn, enablecontrol) {
    Assignpopup = curcontl;
    AssignJSON_To_Grid_WImg(result.d, oTable_Grid_AcctID);
}
 