var ws = new WebSocket("ws://"+window.location.hostname+":"+window.location.port+"/ThemePro_LSW/wschat");
//var ws = new WebSocket("ws://"+window.location.origin+"/ThemePro_LSW/wschat");
//var ws = new WebSocket("ws://192.168.168.33:8080/ThemePro_LSW/wschat");
//
ws.onopen = function()
{
	//var User = getUrlParam("User");
	var User = GetCurrentUser();
	
			var User = GetCurrentUser();	
		if (User == "roleAnonymous")
		{
			User="";
		}
	
	var sessionVar = localStorage['myKey'];
	ws.send("usr|"+User+"|"+sessionVar);

};

ws.onmessage = function(message)
{             
	//document.getElementById("chatlog").textContent += message.data + "\n";
	alert("Session Logged out...");
	window.close();
};
            
function postToServer()
{    
	
		var User = GetCurrentUser();	
		if (User == "roleAnonymous")
		{
			User="";
		}
	if(User!="")
		{
		ws.send("chk|"+User); 
		//document.getElementById("msg").value = "";
		}
	
}
            
function closeConnect()
{
	ws.close();
}

function OnDashBoardLoad() 
{
	//var User = getUrlParam("User");
	var User = GetCurrentUser();
	
			var User = GetCurrentUser();	
		if (User == "roleAnonymous")
		{
			User="";
		}
	
	ws.send("usr|"+User); 
}
