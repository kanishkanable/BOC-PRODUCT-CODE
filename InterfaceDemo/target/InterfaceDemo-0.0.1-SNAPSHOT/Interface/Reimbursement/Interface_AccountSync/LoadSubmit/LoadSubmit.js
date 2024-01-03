$(document)
		.on(
				"click",
				'.AccSrch',
				function() {

					//  var xml = submitdata("DBfields");
					// $("#xml").val(xml);
					//alert('test');
					var chk1 = $("#ACC_CHK").val();
					var chk = $("#ACC_VER").val();
					var check1 = true;
					if (chk == "off") {
						/*alert(chk);*/
						if (check1) {
							var param1 = $("#ACC_NUM").val();
							$.ajax({

								url : "/InterfaceDemo/Acc_CHK",
								data : {
									param1 : param1
								},
								async : false,
								// dataType: "json",
								dataType : "text",
								type : 'POST',
								success : function OnSuccess_submit(xml1) {
									OP = xml1;
									chk1 = xml1;

									// ajaxindicatorstop();
								},

								error : function(xml1) {
									// alertify.alert(LoadFrmXML("V0126"));
									window.alert(LoadFrmXML("V0126"));
									OP = "Fail";
									// ajaxindicatorstop();
								}

							});
						}

						if (chk1 == "Failure") {
							alertify
									.alert("Account Already Exists in TI. Please tick the Check Box to Update the Account");
							$("#ACC_CHK").show();
							$('#ACC_CHKHDR').show();
							$("#ACC_VER").val("on");
						} else if (chk1 == "Success") {
							$("#AcctSync").attr("action",
									"/InterfaceDemo/Acctsearch");
							$("#AcctSync").submit();
						}
					} else if (chk == "on") {
						var chk1 = document.getElementById("ACC_CHK");
						if (chk1.checked == true) {
							$("#AcctSync").attr("action",
									"/InterfaceDemo/Acctsearch");
							$("#AcctSync").submit();
						}

						if (chk1.checked == false) {

						}

					}

					/*$("#CustSync").attr("action","/InterfaceDemo/Custsearch");
					$("#CustSync").submit();*/

				});
/*
 $(document).on("click",'.AccSrch',function(){
 $("#AcctSync").attr("action","/InterfaceDemo/Close");
 $("#AcctSync").submit();
 });*/