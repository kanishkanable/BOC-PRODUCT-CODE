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
    
   


    $('#save').click(function () { 
	
	
	if ($('#lstLeft').has('option').length>=1)
	{
	$('#lstLeft').removeClass("NewMndtry");
	}

          var chkmndtry = ChkMandatoryFields("NewMndtry");


           if (chkmndtry == "No") {
                return;
            }
           var attch = document.getElementById("list").value;
       	var dropdn = document.getElementById("Mndtry").value;
        if(attch != "" || dropdn!="" )
	    {
	    	//alert('Add Attachment type and Mandatory condition');
				alertify.alert(LoadFrmXML("V0128"));
	    	return
        }
    	getListValToDB('lstLeft','FromLBVal');
   		
            var FORMXML = submitdata("ISDBfields");
            var param = "New";
            var spname = "SAM_sINSAttchConfig";
            
            $.ajax({
            	
                url: "ThemeproLO/UI_formdatains_ConfigScreens",
                data: { xml: FORMXML, param: param, spname: spname },
                async:false,
           type:"Post",
                success : function OnSuccess_submit(Result) {
                	
                	//alert(Result);
                    if (Result == 'Success') {
                       
                         alertify.alert(LoadFrmXML("V0108"));
                        Newclearscreen();
                    
                    }
                    else {
                        // DateChangeBack("ISDatefield");
                        alertify.alert(LoadFrmXML("V0074"));          
                    }
                },
                error: function OnError_submit(xmlRequest) {
                    //DateChangeBack("ISDatefield");
                    alertify.alert(LoadFrmXML("V0075"));

                }
            });                         
    
    });
    
    
    
    
    
    $('#Update').click(function () { 
        var chkmndtry = ChkMandatoryFields("EditMndtry");


     if (chkmndtry == "No") {
          return;
        }
     var attch = document.getElementById("Editlist").value;
 	var dropdn = document.getElementById("EditMndtry").value;
     if(attch != "" || dropdn!="" )
	    {
    	
		 alertify.alert(LoadFrmXML("V0128"));
	    	return
     }
    document.getElementById("FromLBVal").value = "";
    
	getListValToDB('EditlstLeft','EditFromLBVal');
		
        var FORMXML = submitdata("ISDBfields");
        var param = "Update";
        var spname = "SAM_sINSAttchConfig";
        
        $.ajax({
        	
            url: "ThemeproLO/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param, spname: spname },
            async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
                if (Result == 'Success') {
                   
                	alertify.alert(LoadFrmXML("V0123"));
					alertify.alert(LoadFrmXML("V0128"));
                    editclearscreen();
                }
                else {
                    // DateChangeBack("ISDatefield");
					alertify.alert(LoadFrmXML("V0121"));
                	//alertify.alert("Already Exsist");           
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
            	
				alertify.alert(LoadFrmXML("V0075"));

            }
        });                         

});
    
    
    $('#Deletebtn').click(function () { 
        var chkmndtry = ChkMandatoryFields("EditMndtry");


     if (chkmndtry == "No") {
          return;
        }
    document.getElementById("FromLBVal").value = "";
   var wfname = $("#Editname").val();
	var formname = $("#EditFormID").val();
	
 
	
	
	alertify.confirm("This will Delete the Entire  "+"'"+wfname+"'" + " "+"'"+formname+"'" +"  "+"Data" , function (e) {
    if (e) {
	
	
	getListValToDB('EditlstLeft','EditFromLBVal');
		
        var FORMXML = submitdata("ISDBfields");
        var param = "Delete";
        var spname = "SAM_sINSAttchConfig";
        
        $.ajax({
        	
            url: "ThemeproLO/UI_formdatains_ConfigScreens",
            data: { xml: FORMXML, param: param, spname: spname },
            async:false,
            type:"Post",
            success : function OnSuccess_submit(Result) {
            	
            	//alert(Result);
                if (Result == 'Success') {
                   
                	
						alertify.alert(LoadFrmXML("V0124"));
                    editclearscreen();
                }
                else {
                    // DateChangeBack("ISDatefield");
                	//alertify.alert("Please Choose a file");  
				alertify.alert(LoadFrmXML("V0117"));					
                }
            },
            error: function OnError_submit(xmlRequest) {
                //DateChangeBack("ISDatefield");
				alertify.alert(LoadFrmXML("V0075"));
            	

            }
        });  

		}
		else{
		return;
		}
		});

});
    
    $("#Qup").on("click", function () {
        listbox_move('EditlstLeft', 'up');
        //alert("ok");
    });
    $("#Qdown").on("click", function () {
        listbox_move('EditlstLeft', 'down');
        //alert("ok");
    });
    $("#up").on("click", function () {
        listbox_move('lstLeft', 'up');
        //alert("ok");
    });
    $("#down").on("click", function () {
        listbox_move('lstLeft', 'down');
        //alert("ok");
    });
       
});

/***************** New Page******************/
function addlistval(){
	
	var attch = document.getElementById("list").value;
	var dropdn = document.getElementById("Mndtry").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("lstLeft");
	 var option = document.createElement("OPTION");
	   
	    
	    if(attch == "" || dropdn=="" )
	    {
	    	//alertify.alert('Fill Attachment type and Mandatory condition');
			alertify.alert(LoadFrmXML("V0128"));
        }
	    else if (jQuery("#lstLeft").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=lstLeft]").append(option);
     	   document.getElementById("list").value="";
  	       document.getElementById("Mndtry").value="";
        }
        
        else
        {
        	for (var i = 0; i < listBox.options.length; i++) 
        	{
        		if (listBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			
					alertify.alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=lstLeft]").append(option); 
        			document.getElementById("list").value="";
        		    document.getElementById("Mndtry").value="";
        		    return;  
        	}     
        

	
/*
	var attch = document.getElementById("list").value;
	var dropdn = document.getElementById("Mndtry").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("<%= lstLeft.ClientID%>");
	 var option = document.createElement("OPTION");
	    option.innerHTML = res;
	    option.value = res;
	    $("[id*=lstLeft]").append(option);
	    
	    document.getElementById("list").value="";
	    document.getElementById("Mndtry").value="";
	*/
	
	 
	
}
function Editlistval()
{
	var list = document.getElementById('lstLeft');
	 
	    var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
				alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	var spldata= attchval.split('|');
	     
	     	var data1= $(spldata)[0];
	     	var data2= $(spldata)[1];
	     
	     	document.getElementById("list").value=data1;
	     	document.getElementById("Mndtry").value=data2;
	list.remove(list.selectedIndex);

	} 
	 
}

function Deletelistval()
{
	var selectbox = document.getElementById('lstLeft');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
			alertify.alert(LoadFrmXML("V0129"));
	    	//alertify.alert("Please select an option to move.");
	        return;
	    }
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
	if(selectbox.options[i].selected)
		
	selectbox.remove(i);
	
	}
	 
 
}

/***************** Edit Page******************/

function Editaddlistval(){
	
	var attch = document.getElementById("Editlist").value;
	var dropdn = document.getElementById("EditMndtry").value;
	var res = attch+'|'+dropdn;
	
	var listBox = document.getElementById("EditlstLeft");
	 var option = document.createElement("OPTION");
	   
	    
	  if(attch == "" || dropdn=="" )
	    {
		  //alertify.alert('Fill Attachment type and Mandatory condition');
		  alertify.alert(LoadFrmXML("V0128"));
      }
	    else if (jQuery("#EditlstLeft").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=EditlstLeft]").append(option);
     	   document.getElementById("Editlist").value="";
  	       document.getElementById("EditMndtry").value="";
        }
        
        else
        {
        	for (var i = 0; i < listBox.options.length; i++) 
        	{
        		if (listBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			//alertify.alert('Already here'); 
					alertify.alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=EditlstLeft]").append(option); 
        			document.getElementById("Editlist").value="";
        		    document.getElementById("EditMndtry").value="";
        		    return;  
        	}  
}
function Editpgeditlistval()
{
	var list = document.getElementById('EditlstLeft');
	 var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
			alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	var spldata= attchval.split('|');
	     
	     	var data1= $(spldata)[0];
	     	var data2= $(spldata)[1];
	     
	     	document.getElementById("Editlist").value=data1;
	     	document.getElementById("EditMndtry").value=data2;
	list.remove(list.selectedIndex);

	} 
}

function Editdeletelistval()
{
	var selectbox = document.getElementById('EditlstLeft');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
	    	//alertify.alert("Please select an option to move.");
			alertify.alert(LoadFrmXML("V0129"));
	        return;
	    }
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
		if(selectbox.options[i].selected)
		
			selectbox.remove(i);
	
	}
    
    
}

/*****************************************/



function Newclearscreen()
{
	document.getElementById("name").value = "";
	document.getElementById("packageId").value = "";
	document.getElementById("FormID").value = "";
	document.getElementById("list").value = "";
	document.getElementById("Mndtry").value = "";
	document.getElementById("FromLBVal").value = "";
	$('#lstLeft > option').remove();
}
function editclearscreen()
{
	document.getElementById("Editname").value = "";
	document.getElementById("EditpackageId").value = "";
	document.getElementById("EditFormID").value = "";
	document.getElementById("Editlist").value = "";
	document.getElementById("EditMndtry").value = "";
	document.getElementById("EditFromLBVal").value = "";
	$('#EditlstLeft > option').remove();
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
 




function listbox_move(listID, direction) {

    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;

    if (-1 == selIndex) {
    	//alertify.alert("Please select an option to move.");
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
