(function( $ ){
	
	 

	$(document).on('click', '.NewProcess', function () {
		var Module=$(this).attr("name");
		var Username=$("#UserName").val();
		var op= UI_getdata(Module,"ENTRY",Username,'','','RIMB_sGetProcActvId');
		processId = $(op).find("PRCSID").html();
		activityId = $(op).find("ACTVID").html();
		URL = $(op).find("URL").html();
		UserRole=$("#UserRole").val();
		var refNum=$("#MAS_REIMBSYSREF").val();
		window.location.href= window.location.origin + URL+'?processId=' + processId + '&activityId='+activityId+'&UserRole=' + UserRole+'&Module='+Module+"&RembursId="+refNum;
	});

	

	
	
	

	$(document).on('click', '#Close', function () {
	    URL = "/InterfaceDemo/Summary";
	    window.location.href= window.location.origin + URL;
	});
	
	$(document).on('click', '#ReimbClose', function () {
		console.log("window loc ori"+window.location.origin);
		if(window.location.origin.includes("172.24")){
	    URL = "https://ftilive.bankofceylon.local/tiplus2-global";
	    }
		else if(window.location.origin.includes("172.23.28.5"))
			{
			URL = "https://ftilive.bankofceylon.local/tiplus2-global";
			}
		 else{ URL = "https://ftilive.bankofceylon.local/tiplus2-global";}
	    window.top.location.href = URL;
	});
	$(document).on('click', '#Discard', function () {
		$(document).find('input,text,textarea,select').each(function(){
			if(!$(this).hasClass("Ctrl") && $(this).attr("type") != "hidden"){
				$(this).val('');
			}
		});
	});

	
	
 
 
 
 
 
 
 $(document).on('click', '#GridDelete', function () {
 
 
 
  var strconfirm = confirm("Are you sure you want to delete?");
    if (strconfirm == true) {
		
	 var tableid=$(this).parents('table').attr('id')
         $(this).closest('tr').remove();
		
		
		 //Repco Validation Start
	 
	//Upto50L ApplicationForm Co-Applicant 
	 
	   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "CPDT_CusID")
   {
  	  TotincomeDtl();
   }
	  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "CPDT_CusID")
   {
  	  TotExstgLon();
   }
		 
		  //Application From -- Employee details
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "TOBS_BusYear")
     {
   	 TotAmtEmp(); 
     }  
      if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "AIPY_BusSrc")
     {
   	 TotAmtEmp(); 
     }  
     
	 //Process Note Borrow of Applicant
	 if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "DOFF_AccHoldrName")
     {
   	  TotSecurity();
     }
	 // Process Note-- Borrowing of Applicant
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "APPB_BrwrName")
     {
   	  TotBrwAppl();
     }
	 //Application Form -- Course Detail
	 if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FDTL_CourseName")
     {
   	  TotCourceDtl();
     }
	//Application From -- Bank Dealings
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "APPD_AccHoldrName")
     {
   	  TotDpsitAppln();
     } 
     
   //Application From -- Bank Dealings
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "DTBW_BrwrName")
     {
   	  TotDrtBrw();
     }  
   //Application From -- Bank Dealings
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "IDBW_BrwrName")
     {
    	 TotIDrtBrw();

     }
	 
   //ProcessNote -- Course Detail
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FEDT_CourseName")
     {
    	 TotalCourseDtl();
     }
	 
	 
	 //staffLoan
	 	  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "REPB_LonNatr")
     {
    	 TotalLiabRepcoDtl();
		 

     }
	 
	   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "OTHR_BankFinInsName")
     {
    	 TotalLiabOthrDtl();
		 

     }
	 
	 //Application Form -- StaffLiabilities
     
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "RBNK_LonNatr")
     {
    	 TotrepcoDtl();
     }
     
     
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "OBNK_BankFinInsName")
     {
    	  TototherbnkDtl();
     }
      
      
 //Application Form -- Credit Facilities --Above50L
      
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FNDB_FacltyNatr")
      {
    	  TotFBCreDetls();
      }
      
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "NFDB_FacltyNatr")
      {
    	  TotNFBCreDetls();
      }
     
	 
	 
	 //UPTO50L ProcessNote -- HO Processing
	  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FLSN_LonNatr")
     {
    	  calcServiceValuationFee();
     }
	  
	  
	  //  Above50L Application From -- Bank Dealings
	     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text()  == "EXTD_AccHoldrName")
	     {
	   	  TotExistDpsit();
	     } 
	     
	     
	     //Application From -- Employee details
	     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text()  == "OTAI_SalSrc")
	       {
	  		 SalOtherTotIncm();
	  		 SalTotAmt();
	     	
	       } 
	 
	 //Repco Validation End
	 
	 //MicroFinance (HO Processing) Start
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "LOND_CusID")
      {
    	  TotLoanDetls();
      }
      
	  
	  
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FLSN_GrpName")
      {
    	  TotFinallimit();
      }
      
    
   	
      
      //MicroFinance (HO Processing) End
		
		 //Change select value yes based on Grid row count
         GridRowChkval(tableid)
         //Change select value yes based on Grid row count 
    }
 
 


 });
 
 
$(document).on('click', '#GridAdd', function () {
	
 var tablest = "<table cellspacing=\"2\" cellpadding=\"2\" width=\"80%\">";
 var name="";
 var value="";
 var GridPGURL="";
 var rowchk="0";

 
var tableid= $(this).parents("div:first").parent("div").siblings(".dataTables_scroll").find(".dataTables_scrollBody").find('table').attr('id');
var tableid1=tableid+" "+"Add";



 //CHFL Validation
 
    if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[2]).text() == "LPRD_PrtyTyp")
  {
     PrdPrptyTyp();
	 Securitycnt(); 
	 var Chk= PrdctSecChk()
	if(Chk=='No')
	return;
  }
   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[2]).text() == "CLDB_UniqIdentProf")
  {
	  
	   
	   var Chk= GridCntChk(tableid)
		if(Chk=='No')
		return;
	  
	
  }

 
      if ( $("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "DINT_Trnch")
  {
      var Chkval=DsbrstmntChk()
	if(Chkval=='No')
	{
	//alert ("Only One Disbursement per Process is Allowed")
alert ("Can't add more than one row");
	return
	}
  }
  
         if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "ELDT_ApplName")
  {
     TopupChk()
  }
  

 //CHFL validation

//Repco Validation Start
         
            
       //ABove50L -- ApplicationForm --Securities
         if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "IMVP_PropertyQwner")
         {
        	 Approvalobtained();
         }
    	 
    	  
         
         
         
//Repco Validation End


 //iframeheight set start
  var iframemainhgt = parseFloat($(window.frameElement).height());
  var iframehgt = "";
 if($(window.frameElement).parents().find('#AllForms').html()==undefined)
 {
 iframehgt = parseFloat($(window.frameElement).height()+$("#"+tableid+"GridPop").height()-221);
 $(window.frameElement).css('height',iframehgt+'px');
 }
  //iframeheight set end


$("#"+tableid+"GridPop").find('.gridClrfields').val('');
$("#"+tableid+"GridPop").find('.IsCURCommaFields').val('0')

if($($("#"+tableid+"GridPop").find('input[type="file"]')).closest('td').find('input[type="hidden"]').val()=="")
{
	$("#"+tableid+"GridPop").find('input[type="file"]').val('');
	$("#"+tableid+"GridPop").find('input[type="file"]').attr('disabled',false);
$($("#"+tableid+"GridPop").find('input[type="file"]')).closest('td').find('input[type="button"]').val('Upload');
}


var rowindex = $("#"+tableid).find('tbody').find('tr').length;

var FieldPrfxP = $("body",document).find("input")[1].id.split('_')[0];
var FieldPrfx = $("#"+tableid+"GridPop").find("input")[1].id.split('_')[0];
	 
	document.getElementById(FieldPrfx+"_PrcsID").value=document.getElementById(FieldPrfxP+"_PrcsID").value;
	document.getElementById(FieldPrfx+"_ActivityID").value=document.getElementById(FieldPrfxP+"_ActivityID").value;
	document.getElementById(FieldPrfx+"_ModifiedBy").value=document.getElementById(FieldPrfxP+"_ModifiedBy").value;
	document.getElementById(FieldPrfx+"_CreatedBy").value=document.getElementById(FieldPrfxP+"_CreatedBy").value;
	document.getElementById(FieldPrfx+"_DtCreated").value=document.getElementById(FieldPrfxP+"_DtCreated").value;
	document.getElementById(FieldPrfx+"_DtModified").value=document.getElementById(FieldPrfxP+"_DtModified").value;

	
	/*if(FieldPrfx=="FNAS")
	{
		SelMonth();
		
	}*/
	
	var Flag = "Add";
	
	//for security calculation start

 $(document).find('#SecDetlDrp').trigger('click');

	//for securtiy calculation end

tablest = tablest +"<tfoot><tr><td><img id=\"GridBTNSb\" name=\""+rowindex+"_"+iframemainhgt+"\" title=\"Submit\" class=\""+tableid1+"\" src=\"ThemeproLO/Common/Images/tick.png\" /><img id=\"GridBTNCn\" name=\""+iframemainhgt+"\" class=\""+tableid+"\" title=\"Cancel\" src=\"ThemeproLO/Common/Images/cross.png\" /></td>"
tablest = tablest +"<td></td></tr></tfoot></table>"

$("#"+tableid+"GridPop").find('tfoot').remove();
$("#"+tableid+"GridPop").find('table:last').append(tablest);
$(".gridclass").addClass("ui-dialog-grid");
 
  $("#"+tableid+"_wrapper").css("display", "none");
 $("#"+tableid+"GridPop").css("display", "block");

    $("#"+tableid+"GridPop").addClass("Contshow");
 

 $(document).find('form').css('opacity', '0.5');
 $("#"+tableid+"GridPop").prependTo('body');
  $(document.body).prepend('<div id="gridspace"></br></div>')
 var scrollPos =  $("#gridspace").offset().top;
 $(window).scrollTop(scrollPos);
  //$("#"+tableid+"GridPop").attr("tabindex",-1).focus();
  $("#gridspace").attr("tabindex",-1).focus();
   $("#gridspace").attr("tabindex","");
   $(document).find('form').css("pointer-events","none");
   
   
   //Disable Key event Add Class start
    $(document).find('form').addClass('tabdisable');
   
   //Disable Key event Add Class end
   
//After Value Bind
           if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "PRVL_PrptyNo")
  {
	  if( $("#"+tableid+"GridPop").find("#PRVL_PrptyNo").val() !='')
	  {
     PrptyAddr()
	}
  }
//After Value bind	
	
 });



 
 $(document).on('click', '#GridSbBTN', function () {
  
 var tableid= $("#GridSbBTN").attr("name").split("|")[0];
 var flag =$("#GridSbBTN").attr("name").split("|")[1];
  $(document).find('form').removeClass('tabdisable');
    $("#GridSbBTN").remove();

  //Trigger Function On Submit **Start**
 if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "CADT_CoApplID")
 {
	 CoApplntdatagrid();
 } 
 
 if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "GRDT_GuarID")
 {
	 Guardatagrid();
 } 

  if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "PREL_PropNo")
  {
     LonAmtRatio();  
  }
  if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "EVSA_ApplID")
  {
     Incmdata();
	 GetROIEMI();
  }
  
  if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "EVSC_CoApplID")
  {
     Incmdata();
	 GetROIEMI();
  }

   if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "RMDT_SancLevel")
  {
     ChkSbmtLvlrow("Assign")
  }
  
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "RKRT_RatingLevel")
  {
     ChkSbmtLvlrow("Assign")
  }
  
   
  

  
/* if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "PRAD_SecDetl")
  {
     SecDetlDrpdwnchk(flag); 
  }*/
     // Trigger Function On Submit **End**
 

 });
 
 
 $(document).on('click', '#GridBTNCn', function (e) { 
 
  var tableid= $("#GridBTNCn").attr("class");
  var ifrmemainhgt = "";
  ifrmemainhgt= $(this)[0].name;
  
  if($(window.frameElement).parents().find('#AllForms').html()==undefined)
{
  //iframe height start
 $(window.frameElement).css('height',$(this).attr('name')+'px');
 //iframe height end
 }
   $(this).closest('div').hide();
// $("#"+tableid+"_wrapper").css("display", "none");
 $("#"+tableid+"_wrapper").css("display", "block");
 $("#gridspace").remove();
 
 

  
$(document).find('form').css("opacity", ""); 
$(document).find('form').find('#'+tableid).append()
$(document).find('form').find("#"+tableid+"_wrapper").after($("#"+tableid+"GridPop"));
$(document).find('form').css("pointer-events","");
$("#"+tableid+"_wrapper").attr("tabindex",-1).focus();
$("#"+tableid+"_wrapper").attr("tabindex","")

 $(document).find('form').removeClass("tabdisable");

 });
 
 
    var methods = {

        init: function() {
            return this.each(function(){
                methods.initCells.apply($(this));

                // add new row button
                if ($(this).find(".grid-row-add").length == 0) {
                    var link = $('<a class="grid-row-add" href="#">Add Row</a>');
                    link.click(function() {
                        var table = $(this).parent();
                        methods.addRow.apply(table, arguments);
                        return false;
                    });
                    $(this).append(link);
                }

                // add first row if grid is empty
                if ($(this).find(".grid-row").length == 0) {
                    methods.addRow.apply($(this), arguments);
                }
                
                $(this).find("th:last-child").after("<th style=\"border: 0 none;\"></th>");
            });
        },

        initCells: function() {
            // make cells editable
            $(this).find(".grid-row .grid-cell").editable(methods.updateCell, {
                 type: "text",
                 tooltip: "Click to edit",
                 cssclass: "grid-cell-input",
                 width: "none",
                 submit: "OK",
                 data : function (content, setting) {
                     return $(this).next("input").val();
                 }
            });
            
            // add delete link for each row
            $(this).find(".grid-row").each(function() {
                if ($(this).find(".grid-row-del").length == 0) {
                    var td = $('<td class="grid-cell-options"><a class="grid-row-del" href="#">X</a></td>');
                    $(this).append(td);
                }
                $(this).find(".grid-row-del").unbind();
                $(this).find(".grid-row-del").click(methods.deleteRow);
            });
        },

        updateCell: function(value, settings) {
            var input = $(this).siblings("input");
            input.attr("value", value);
            if (value == "") {
                value = "Click to edit";
            }
            // trigger change
            var el = $(this).parent().parent().parent().parent().parent();
            setTimeout(function() {
                $(el).trigger("change");
            }, 100);
            return value;
        },

        addRow: function() {
            return $(this).each(function(){
                // get table
                var table = $(this).find("table");
                
                // clone template
                var template = $(table).find(".grid-row-template");
                var newRow = $(template).clone();
                newRow.removeClass("grid-row-template");
                newRow.addClass("grid-row");
                newRow.removeAttr("style");

                // set input names and values
                var rowIndex = $(table).find("tr").length - 2;
                methods.updateInput(newRow, rowIndex);

                // append row
                table.append(newRow);

                methods.initCells.apply(this);

                // trigger change
                $(this).trigger("change");

               
            });
        },

        updateInput: function(row, rowIndex) {
            $(row).find(".grid-cell").each(function(index, column) {
                var input = $(column).siblings(".grid-input");
                var name = $(column).attr("id") + "_" + rowIndex;
                var value = $(column).text();
                input.attr("name", name);
                input.attr("value", value);
            });
        },

        deleteRow: function() {
            if (confirm("Delete row?")) {
                var row = $(this).parent().parent();
                var table = row.parent();
                row.remove();

                // reset input names
                table.find(".grid-row").each(function(rowIndex, row) {
                    methods.updateInput(row, rowIndex);
                });

                // trigger change
                var el = table.parent();
                $(el).trigger("change");
            }
            return false;
        }

    };

    $.fn.formgrid = function( method ) {

        if ( methods[method] ) {
            return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.formgrid' );
        }

    };

})( jQuery );

function Onloadgrid(FieldID,tableid)
{

    var str_array = FieldID.split(',');
    var str_array1 = tableid.split(',');
var FieldIDn = "";

if (FieldID != "") {

    for (var m = 0; m < str_array.length; m++) {

        FieldIDn = str_array[m];
        tableid = str_array1[m];

        var xmldata = document.getElementById(FieldIDn).value;

        if (xmldata != "") {
            //if(xmldata!="<LKYC_Addressdetl />")
            //{
            var str_xml = xmldata;
            var parser_xml = new DOMParser();
            var oXml = parser_xml.parseFromString(str_xml, "text/xml");
            var obj_elementcol = oXml.documentElement;


            //////// ONLOAD TO ADD ROWS

            var tbrowcount = obj_elementcol.childNodes[0].childNodes.length;
            var template = $('#' + tableid).find(".grid-row-template");

            if (obj_elementcol.childNodes[0] != null) {
                for (var i = 0; i < obj_elementcol.childNodes[0].childNodes.length; i++) {

                    ///////////////////

                    //if(i!=0)	  
                    //{
                    window["text" + i] = $(template).clone(); ;
                    window["text" + i].removeClass("grid-row-template");
                    window["text" + i].addClass("grid-row");
                    window["text" + i].removeAttr("style");
                    $('#' + tableid).append(window["text" + i]);
                    //////////////////
                    //}

                    for (var j = 0; j < obj_elementcol.childNodes[0].childNodes[i].childNodes.length; j++) {

                        var s1 = obj_elementcol.childNodes[0].childNodes[i].childNodes[j].nodeName;
                        var fieldvalue = obj_elementcol.childNodes[0].childNodes[i].childNodes[j].innerHTML;

                        var a = i;
                        i = parseInt(i) + 1;

                        document.getElementsByName(s1).item(i).value = fieldvalue;
                        document.getElementsByName(s1).item(i).text = fieldvalue;

                        i = a;

                    }
                }

            }
            $(".grid").formgrid();
            //}
            //   else{

            // $(".grid_79").formgrid();

            // }
        }
        else {

            $(".grid").formgrid();

        }
    }
}
else {

    $(".grid").formgrid();

}
}

function Gridsubmitdata(TableID) {
   
var name="";
var fieldid="";
var value="";
var reg = new RegExp('^[0-9]+$');



var formxml="<Data>";


$('#'+TableID).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper').find("span").remove().end().html();

for(j=0;j<$("#"+TableID).find('tbody').find('tr').length;j++)
{
formxml= formxml + "<row>";

var rowwise = $("#"+TableID).find('tbody').find('tr')[j];

for(i=1;i<$("#"+TableID).find('thead').find('tr:nth-child(2)').find('th').length;i++)
 {
    name = $("#"+TableID).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[i].innerHTML;
   
   if(reg.test($(rowwise).find('td:eq('+i+')').text().replace(/,/g, ""))==true)
  {
//if($(rowwise).find('td:eq('+i+')').text().match(/^((\d{0,3}(,\d{3})+)|\d+)(\.\d{2})?$/))
	//{
  value = $(rowwise).find('td:eq('+i+')').text().replace(/,/g, "");
	}
	else
	{
		value = $(rowwise).find('td:eq('+i+')').text();
	}
  // value = $(rowwise).find('td:eq('+i+')').text().replace(/[^\d\.\-\ ]/g, '');
   value = value.replace(/\&/g, "and");
   if(value != "")
   {
var formxml=formxml+"<"+name+">"+ value  +"</"+name+">";
   }
}
formxml= formxml + "</row>";
}
var formxml = formxml + "</Data>";
//alert(formxml);
return formxml;

}



function RemoveExtraGridRow(TableID, FieldsClassName, GridClassName) {
     
    var rowCount = $("#" + TableID).find("tr").length - 2;
    var count = document.getElementById(FieldCountID).value;

    for (j = 1; j <= rowCount; j++) {
       
        for (i = 0; i < count; i++) {
            name = document.getElementById(TableID).getElementsByClassName(FieldsClassName).item(i).name;
            fieldid = document.getElementById(TableID).getElementsByClassName(FieldsClassName).item(i).id;

            // value = document.getElementsByClassName(FieldsClassName).item(j).value;
            value = document.getElementsByName(name).item(j).value;
            var formxml = formxml + "<" + name + ">" + value + "</" + name + ">";

        }
      
    }

    $("." + GridClassName).formgrid();

}

function AssignGridXmltoField(FieldID,GridXML) {

    document.getElementById(FieldID).value = GridXML;
   
}

function LoadEmptyGrid_fieldsCount(FieldID, GridClassName, GridFieldsClassName) {

    //document.getElementById(FieldID).value = $("." + GridFieldsClassName).length;
    document.getElementById(FieldID).value = document.getElementById(GridClassName).getElementsByClassName(GridFieldsClassName).length
  //  $("." + GridClassName).formgrid();

}


function GridCalculation(TableID, InputFieldName, OutputFieldNameID) {

    var rowCount = $("#" + TableID).find("tr").length - 2;

    var x = document.getElementsByTagName("input");
    var y = document.getElementsByTagName("select");
    var Value = "0";
    var fieldid = "";
    var value = "";
    var valuesel = "";
    var type = "";
    //var count = document.getElementById(FieldCountID).value;
    var row = "";
    var formid = document.getElementsByTagName("form").item('0').name

    var obj = $('#' + TableID);

    var formxml = "<Data>";

    for (j = 1; j <= rowCount; j++) {
       // formxml = formxml + "<row>";

         Value = parseFloat(Value)+parseFloat(document.querySelectorAll('#' + InputFieldName)[j].value);
        
       
       /* for (i = 0; i < count; i++) {
            name = document.getElementById(TableID).getElementsByClassName(FieldsClassName).item(i).name;
            fieldid = document.getElementById(TableID).getElementsByClassName(FieldsClassName).item(i).id;

            // value = document.getElementsByClassName(FieldsClassName).item(j).value;
            value = document.getElementsByName(name).item(j).value;
            var formxml = formxml + "<" + name + ">" + value + "</" + name + ">";

        }*/
       // formxml = formxml + "</row>";
    }
    
    document.getElementById(OutputFieldNameID).value = Value;

}


 $(document).on('click', '#GridBTNSb', function (e) { 
 
 var name1="";
 var value="";
//var Editrowindex=parseInt($(this)[0].name);
var Editrowindex= $(this)[0].name.split('_')[0];

var ifrmemainhgt= $(this)[0].name.split('_')[1];
 	  
//var rowindex=($(this).closest('td').parent()[0].sectionRowIndex)+1;
 var split = $(this)[0].className.split(' ');
 
 var tableid=split[0];

 
 var CHkSub=split[1];
var trcount="";
 var classn="";
 var result="";
 

var chkmndtry = GridMndtry("GridMndtry_"+tableid);


	    if (chkmndtry == "No") {
	        return;
	    }
		
		
	//CHFL Submit Validation	
   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[2]).text() == "FNAB_ApplFinancialYear")
  {
    var Chkval=AssestChk()
	if(Chkval=='No')
	{
	return
	}
	
  }
  
       if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FCAB_Name")
  {
    var Chkval=AssestChk('FCAB_ApplTotLiability','FCAB_ApplTotAsset')
	if(Chkval=='No')
	{
	return
	}
	
  }
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FCBA_Name")
  {
    var Chkval=AssestChk('FCBA_ApplTotLiability','FCBA_ApplTotAsset')
	if(Chkval=='No')
	{
	return
	}
	
  }
          
		  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "PRVL_PrptyNo")
  {
	  if( $("#"+tableid+"GridPop").find("#PRVL_PrptyNo").val() !='')
	  {
    var Rtnrst= PrptyMrktVal()
	if(Rtnrst==false)
	{
		return
	}
	}
  }

  //CHFL Submit Validation
  
 
 if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "RMDT_SancLevel")
  {
    //$(window.parent.document).find("#RECM_EdtChk").trigger('click');
	if(CalcClkchk() == 'No')
	{
		return;
	}
	
	result=calIIRvalSbmt();
	if(result != 'No')
	{
	
		if($("#RMDT_IIRExcptnlResn").val()=="")
	{
alertify.confirm(result+'|RMDT_IIRExcptnlResn', function (e) {
    if (e) 
	{
$("#RMDT_IIRExcptnlResn").prop('disabled', false);
$("#RMDT_IIRExcptnlResn").addClass('GridMndtry');
$("#RMDT_IIRExcptnlResn").focus();
$("#RMDT_IIRExcptnlResn").closest('td').find('span').show();
	} 
	else 
	{
	$("#RMDT_IIRExcptnlResn").prop('disabled', true);
     $("#RMDT_IIRExcptnlResn").removeClass('GridMndtry');
	$("#RMDT_IIRExcptnlResn").closest('td').find('span').hide();
		return ;
    }
	
		});
return;
	}
	}
	result=LvrlcrCalSbmt()
		if( result!= '')
	{
	if($("#RMDT_ExcptnlResn").val()=="")
	{
alertify.confirm(result+'|RMDT_ExcptnlResn', function (e) {
    if (e) 
	{
$("#RMDT_ExcptnlResn").prop('disabled', false);
$("#RMDT_ExcptnlResn").addClass('GridMndtry');
$("#RMDT_ExcptnlResn").focus();
$("#RMDT_ExcptnlResn").closest('td').find('span').show();
	} 
	else 
	{
	$("#RMDT_ExcptnlResn").prop('disabled', true);
       $("#RMDT_ExcptnlResn").removeClass('GridMndtry');
	$("#RMDT_ExcptnlResn").closest('td').find('span').hide();
		return;
    }
		});
		}	
	}
  } 
  
   if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "RKRT_RatingLevel")
  {
	if(CalcClkchk() == 'No')
	{
		return;
	}
  }

 //Custom validation for gridpopup submit end
 //Repco validation start 
//Staff Loan -- Salary Pension Detail
if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "SLRY_Mnth")
{
result=ToCheckApplcnt(Editrowindex,CHkSub);
if(result!='')
{
alert(result);
return;
}
}

if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "PNSN_Mnth")
{
result=pensioner(Editrowindex,CHkSub);
if(result!='')
{
alert(result);
return;
}
}
//Repco validation end
 
 
 
 
  if($("#"+tableid).find('.dataTables_empty').text()=="No data available in table")
 {
 	
 $("#"+tableid).find('.dataTables_empty').closest('tr').remove();

 }
 
 var rowindex = $("#"+tableid).find('tbody').find('tr').length+1;
 
 var BTNvalue="<a \"><img style=\"width:20px;height:20px;\" id=\"GridEdit\" name=\"GridEdit\" title=\"Edit\" src=\"ThemeproLO/Common/Images/Edit.png\" /> </a> <a \"> <img style=\"width:20px;height:20px;\" id=\"GridDelete\" name=\"GridDelete\" title=\"Delete\" src=\"ThemeproLO/Common/Images/file_delete.png\" /> </a>";	 
 $("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper').find("span").remove().end().html();
 
 
 trcount = $("#"+tableid).find('tbody').find('tr').length;
 
 if(trcount % 2 == 0)
 {
 classn = "odd";	  
 }
 else
 {	  
 classn = "even";	
 }
 
 var tablest = "<tr role=\"row\" class=\""+classn+"\">";
 
 var totalClm = $("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').length;
 
 for(i=0;i<$("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').length;i++)
 {
 
 if(CHkSub == "Edit")
 {
 name1=$($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[i]).text();
 
 value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val()||$(this).parents().find('[name='+name1+']').val() ;
/* if($("#"+tableid+"GridPop").find('[name='+name1+']').hasClass("IsCURCommaFields")||$(this).parents().find('[name='+name1+']').hasClass("IsCURCommaFields"))
 {
	value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val().replace(/,/g, "")||$(this).parents().find('[name='+name1+']').val().replace(/,/g, ""); 
	value =  CURCommaSep(parseFloat(value).toFixed(0))
 }*/
  
 /*if($("#"+tableid+"GridPop").find('[name='+name1+']').hasClass("IsCURCommaFields")||$(this).parents().find('[name='+name1+']').hasClass("IsCURCommaFields"))
 {
	value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val().replace(/,/g, "")||$(this).parents().find('[name='+name1+']').val().replace(/,/g, ""); 
 }
   else
   {
	   value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val()||$(this).parents().find('[name='+name1+']').val();
   }*/
 
 if(name1 == "Action")
 {
 var z;// = $("#"+tableid).find('tr')[rowindex];
 
// $(a).find('td:eq('+i+')').text(BTNvalue);
 }
 else
 {	  
 var a = $("#"+tableid).find('tbody').find('tr')[Editrowindex];
 
 $(a).find('td:eq('+i+')').text(value);
 } 
 

 }
 else
 {
 
 name1=$($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[i]).text();
 
 value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val()||$(this).parents().find('[name='+name1+']').val();
/* if($("#"+tableid+"GridPop").find('[name='+name1+']').hasClass("IsCURCommaFields")||$(this).parents().find('[name='+name1+']').hasClass("IsCURCommaFields"))
 {
	value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val().replace(/,/g, "")||$(this).parents().find('[name='+name1+']').val().replace(/,/g, ""); 
	value =  CURCommaSep(parseFloat(value).toFixed(0))
 }*/
 /*if($("#"+tableid+"GridPop").find('[name='+name1+']').hasClass("IsCURCommaFields")||$(this).parents().find('[name='+name1+']').hasClass("IsCURCommaFields"))
 {
	value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val().replace(/,/g, "")||$(this).parents().find('[name='+name1+']').val().replace(/,/g, ""); 
 }
   else
   {
	   value =  $("#"+tableid+"GridPop").find('[name='+name1+']').val()||$(this).parents().find('[name='+name1+']').val();
   }*/

 
 if(name1 == "Action")
 {
 tablest  = tablest + "<td>"+BTNvalue+"</td>";
 }
 else
 {	  

if(i >= parseInt(totalClm)-6)
{
 tablest  = tablest + "<td class=\" dpass\">"+value+"</td>";
}
else{

 tablest  = tablest + "<td>"+value+"</td>";	
	
}
 } 
 
 }
// rowindex=$(this).closest('tr').index();
//tablest = tablest + "<td>"+name+"<input class=\"GridEDFLD\" value=\""+value+"\" name=\""+rowindex+"\" id=\""+name+"\"/></td>";

 } 
 
 tablest  = tablest + "</tr>"
 

 if(CHkSub != "Edit")
 {
	  $("#"+tableid).find('tbody').append(tablest);
 }

$("#"+tableid+"GridPop").find('.gridClrfields').val('');
//$("#"+tableid+"GridPop").find('.IsCURCommaFields').val('0')
 
 var flag = getUrlParam("Flag");
 
// $("form").prepend("<img id=\"GridSbBTN\" type=\"hidden\" name=\""+tableid+"|"+flag+"\" src=\"ThemeproLO/Common/Images/tick.png\">");

 $("#"+tableid+"_wrapper").css("display", "block");
 $("#"+tableid+"GridPop").css("display", "none");
 
 
  //iframe height start
var iframehgt = "";
iframehgt = parseFloat(ifrmemainhgt);
if($(window.frameElement).parents().find('#AllForms').html()==undefined)
{
$(window.frameElement).height(iframehgt);
}
  $("#"+tableid+"GridPop").removeClass("Contshow");
  $("#gridspace").remove();
 
$(document).find('form').css("opacity", ""); 
$(document).find('form').find('#'+tableid).append()  
$(document).find('form').find("#"+tableid+"_wrapper").after($("#"+tableid+"GridPop"));
 $(document).find('form').css("pointer-events","");
  $("#"+tableid+"_wrapper").attr("tabindex",-1).focus();
    $("#"+tableid+"_wrapper").attr("tabindex","")
	
 $(document).find('form').removeClass('tabdisable');
	
 //iframe height end

 //Change select value yes based on Grid row count
 GridRowChkval(tableid)
 //Change select value yes based on Grid row count
 
 
  //Trigger Function On Submit **Start**

 
 if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "LCPD_CoApplID")
 {
	 CoApplntdatagrid();
 } 
 
 if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "GRDT_GuarID")
 {
	 Guardatagrid();
 } 
 
  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "PREL_PropNo")
  {
     LonAmtRatio();  
  }
  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "EVSA_ApplID")
  {
     Incmdata();
	 GetROIEMI();
  }
  
  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "EVSC_CoApplID")
  {
     Incmdata();
	 GetROIEMI();
  }

   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "RMDT_SancLevel")
  {
     ChkSbmtLvlrow("Assign")
  }
  
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "RKRT_RatingLevel")
  {
     ChkSbmtLvlrow("Assign")
  }
  
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "CAIT_CoApplId")
  {
     CoApplIncmDetl($($('#Table5').find('tbody').find('tr')[Editrowindex]).find('td')[0])
  }

  
  
  //MicroFinance (HO Processing) Start
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "LOND_CusID")
      {
    	  TotLoanDetls();
      }
      
	  
	  
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FLSN_GrpName")
      {
    	  TotFinallimit();
      }
      
      
      //MicroFinance (HO Processing) End
	  
//Repco Validation Start 

    //Upto50L ApplicationForm Co-Applicant 
 	 
	   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "CPDT_CusID")
  {
 	  TotincomeDtl();
  }
	  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "CPDT_CusID")
  {
 	  TotExstgLon();
  }

      // Process Note-- Borrowing of Applicant
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "DOFF_AccHoldrName")
      {
    	  TotSecurity();
      }
      // Process Note-- Borrowing of Applicant
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "APPB_BrwrName")
     {
   	  TotBrwAppl();
     }

      //Application Form -- Course Detail
 	 if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FDTL_CourseName")
      {
    	  TotCourceDtl();
      }
 	 

     //Application From -- Bank Dealings
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "APPD_AccHoldrName")
     {
   	  TotDpsitAppln();
     } 
     
   //Application From -- Employee details
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "TOBS_BusYear")
     {
   	 TotAmtEmp(); 
     }  
      if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "AIPY_BusSrc")
     {
   	 TotAmtEmp(); 
     }  
     
     
    //  
    if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "DTBW_BrwrName")
     {
   	  TotDrtBrw();
     }  
   //Application From -- Bank Dealings
     if ($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1].innerHTML == "IDBW_BrwrName")
     {
    	 TotIDrtBrw();

     }  

     //ProcessNote -- Course Detail
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FEDT_CourseName")
     {
    	 TotalCourseDtl();
     }
	 
	   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FLSN_LonNatr")
     {
    	 DrpChngVal("HOPR_NxtLvl","")
     }
	 	
//StaffLoan
		if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "REPB_LonNatr")
     {
    	 TotalLiabRepcoDtl();
		 

     }
	 
	   if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "OTHR_BankFinInsName")
     {
    	 TotalLiabOthrDtl();
		 

     }
	//Application Form -- StaffLiabilities
     
     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "RBNK_LonNatr")
     {
    	 TotrepcoDtl();
     }
     
     
      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "OBNK_BankFinInsName")
     {
    	  TototherbnkDtl();
     }
	 
	  //UPTO50L ProcessNote -- HO Processing
	  if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FLSN_LonNatr")
     {
    	  calcServiceValuationFee();
     }
	  
	  
	    if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "FNDB_FacltyNatr")
	      {
	    	  TotFBCreDetls();
	      }
	      
	      if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text() == "NFDB_FacltyNatr")
	      {
	    	  TotNFBCreDetls();
	      }
	      
	      //  Above50L Application From -- Bank Dealings
		     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text()  == "EXTD_AccHoldrName")
		     {
		   	  TotExistDpsit();
		     } 
		     
		     
		     if ($($("#"+tableid).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[1]).text()  == "OTAI_SalSrc")
		       {
		  		 SalOtherTotIncm();
		  		 SalTotAmt();
		     	
		       } 
      //Repco Validation End
   //Trigger Function On Submit **end**
 
 });

 
 


function Griddivdsbl(divid)
{

  var inputs = $('#'+divid).find('input');

          for (var i = 0; i < inputs.length; i++)
	 {
              
		//inputs[i].style.background = "#e8e8ee";
		inputs[i].disabled=true;
		//inputs[i].className='xdETTROText';
		//inputs[i].tabIndex = "-1";
		//elArr[i].style.cursor=  'not-allowed';
		//elArr[i].style.cursor=  '';
		  
        }

		  var selects = $('#'+divid).find('select');

          for (var i = 0; i < selects.length; i++)
	 {
              
		//inputs[i].style.background = "#e8e8ee";
		selects[i].disabled=true;
		//inputs[i].className='xdETTROText';
		//selects[i].tabIndex = "-1";
		//elArr[i].style.cursor=  'not-allowed';
		//elArr[i].style.cursor=  '';
		  
        }

	  var imgs = $('#'+divid).find('img');

          for (var i = 0; i < imgs.length; i++)
	 {
              
		//inputs[i].style.background = "#e8e8ee";
		if(imgs[i].id != "GridView"&&$(imgs[i]).closest('a').attr('id')!="attachview" &&$(imgs[i]).closest('a').attr('id')!="comments")
		{
			
		//	if(imgs[i].id=="GridEdit")
			//{
				
			//	imgs[i].src.replace(imgs[i].src.split('/').pop(-1),"review1.png");
			//}
			
		imgs[i].style.display="none";
		}
		//inputs[i].className='xdETTROText';
		//imgs[i].tabIndex = "-1";
		//elArr[i].style.cursor=  'not-allowed';
		//elArr[i].style.cursor=  '';
		  
        }
		
		
		  var textareas = $('#'+divid).find('textarea');

          for (var i = 0; i < textareas.length; i++)
	 {
              
		//inputs[i].style.background = "#e8e8ee";
		textareas[i].disabled=true;
		//inputs[i].className='xdETTROText';
		//imgs[i].tabIndex = "-1";
		//elArr[i].style.cursor=  'not-allowed';
		//elArr[i].style.cursor=  '';
		  
        }


		
}
 $(document).on('click', '#GridCnBTN', function () { 
 
// $(this).closest('div').find('input,select,textarea').val('');
 JPopup.hide("GridPopupShow");
$("#GridCnBTN").remove();
$("#gridspace").remove();
 //to make the parent window disable start
//var win = window.top.jQuery("#assignmentExternalForm").context;
//$(win).find("article:visible").find('#PageIframe'+$(win).find("article:visible").attr('id').split('b')[1]).contents().find('#assignmentExternalForm').contents().find('body').find('.sweet-overlay').remove()
 //to make the parent window disable end
 
 //$(this).closest('div').dialog('close');  
 
 });

