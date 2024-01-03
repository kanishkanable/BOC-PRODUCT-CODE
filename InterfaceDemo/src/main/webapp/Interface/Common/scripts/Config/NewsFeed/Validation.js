/***************** New Page******************/
function addlistval(){
	
	var attch = document.getElementById("NewsFeedArea").value;
	
	//var FormID=document.getElementById("FormID").value;
	var res = attch
	
	var NewsFeedAreaBox = document.getElementById("NewsFeed");
	 var option = document.createElement("OPTION");
	   
	    
	    if(attch == "")
	    {
	    	//alert('Fill Attachment type and Mandatory condition');
			alert(LoadFrmXML("V0163"));
        }
	    else if (jQuery("#NewsFeed").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=NewsFeed]").append(option);
     	   document.getElementById("NewsFeedArea").value="";
  	       
        }
        
        else
        {
        	for (var i = 0; i < NewsFeedAreaBox.options.length; i++) 
        	{
        		if (NewsFeedAreaBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			
					alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=NewsFeed]").append(option); 
        			document.getElementById("NewsFeedArea").value="";
        		   
        		    return;  
        	}     

	
	
}

function Editlistval()
{
	var list = document.getElementById('NewsFeed');
	 
	    var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alert("Please select an option to move.");
				alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	//var spldata= attchval.split('|');
	     
	     //	var data1= $(spldata)[0];
	     //	var data2= $(spldata)[1];
	     
	     	document.getElementById("NewsFeedArea").value=attchval;
	     	
	list.remove(list.selectedIndex);

	} 
	 
}

function Deletelistval()
{
	var selectbox = document.getElementById('NewsFeed');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
			alert(LoadFrmXML("V0129"));
	    	//alert("Please select an option to move.");
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
	
	var attch = document.getElementById("EditNewsFeedArea").value;
	
	var res = attch
	
	var listBox = document.getElementById("EditNewsFeed");
	 var option = document.createElement("OPTION");
	   
	    
	  if(attch == "" )
	    {
		  //alert('Fill Attachment type and Mandatory condition');
		  alert(LoadFrmXML("V0128"));
      }
	    else if (jQuery("#EditNewsFeed").find("option").val() == null)
        {
	    	option.innerHTML = res;
     	    option.value = res;
     	   $("[id*=EditNewsFeed]").append(option);
     	   document.getElementById("EditNewsFeedArea").value="";
  	     
        }
        
        else
        {
        	for (var i = 0; i < listBox.options.length; i++) 
        	{
        		if (listBox.options[i].value.split('|')[0].toLowerCase()==(res.toLowerCase().split('|')[0]))
        		{
        			//alert('Already here'); 
					alert(LoadFrmXML("V0121"));
        			return;
        		}
        	}
        			option.innerHTML = res;
        			option.value = res;
        			$("[id*=EditNewsFeed]").append(option); 
        			document.getElementById("EditNewsFeedArea").value="";
        		   
        		    return;  
        	}  
}
function Editpgeditlistval()
{
	var list = document.getElementById('EditNewsFeed');
	 var selIndex = list.selectedIndex;

	    if (-1 == selIndex) {
	    	//alert("Please select an option to move.");
			alert(LoadFrmXML("V0129"));
	        return;
	    }
	 var i;
	for(i=0;i<=list.options.length;i++)
	{
	if(list.options[i].selected)

	var indx = list.selectedIndex;
	    
	     	var attchval = list[list.selectedIndex].value;
	     
	     	
	     
	     	document.getElementById("EditNewsFeedArea").value=attchval;
	     	
	list.remove(list.selectedIndex);

	} 
}

function Editdeletelistval()
{
	var selectbox = document.getElementById('EditNewsFeed');
	 var selIndex = selectbox.selectedIndex;

	    if (-1 == selIndex) {
	    	//alert("Please select an option to move.");
			alert(LoadFrmXML("V0129"));
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

function FetchNewsFeed()
{
   var param1,param2,param3,param4,param5;
   param1='';param2='';param3='';param4='';param5='';
   var spname = "Sam_sFetchNewsFeed";
$.ajax({
    	
        url: "ThemeproLO/UI_GetData",
        data: { param1: param1, param2: param2, param3: param3,param4: param4,param5: param5,spname: spname },
        async:false,
   type:"Post",
        success : function OnSuccess_submit(Result) {
        	
        	//alert(Result);
        	   $("#NewsFeedIcon").val($(Result).find('Iconsname').text());
              $("#NewsFeed").append($(Result).find('option'));
            
         
        },
        error: function OnError_submit(xmlRequest) {
            //DateChangeBack("ISDatefield");
            alert(LoadFrmXML("V0075"));

        }
    }); 
}