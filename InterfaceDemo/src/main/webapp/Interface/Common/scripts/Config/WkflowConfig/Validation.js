function GridMndryChkFrWF(TableID) {
   
var name="";
var fieldid="";
var value="";

var formxml="<Data>";


$('#'+TableID).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper').find("span").remove().end().html();

for(j=0;j<$("#"+TableID).find('tbody').find('tr').length;j++)
{
formxml= formxml + "<row>";

var rowwise = $("#"+TableID).find('tbody').find('tr')[j];

for(i=1;i<$("#"+TableID).find('thead').find('tr:nth-child(2)').find('th').length;i++)
 {
    name = $("#"+TableID).find('thead').find('tr:nth-child(2)').find('th').find('.DataTables_sort_wrapper')[i].innerHTML;
   value = $(rowwise).find('td:eq('+i+')').text().replace(/,/g, "");
   
   if(value != "")
   {
var formxml=formxml+"<"+name+">"+ value  +"</"+name+">";
   }
   else
	   {
	  // alert("fill all the fields")
	   return 'No';
	   }
}
formxml= formxml + "</row>";
}
var formxml = formxml + "</Data>";
//alert(formxml);
return 'Yes';

}
function ChkUniqueParam(TableName,UniqueParam) {
	 var x="";
	 var tablename=document.getElementById(TableName).value;
     var Param=document.getElementById(UniqueParam).value;
     var spname='sam_sChkFormUniqueParam'
     $.ajax({
         url: "/ThemePro_LSW/UI_formdatains_ConfigScreens",
         data: { xml: tablename,param:Param,spname:spname },
         async: false,
  
         type: 'POST',
         success: function OnSuccess_submit(data) {
             if (data == "No") {
            	 
             x=data;
             }
           //  else
            	// {
            	// return "Yes"
            	// }

         }
        
 	});
     if(x=="No")
    	 {
    	 return "No";
    	 }
     else
    	 {
    	 return "Yes";
    	 }
	}