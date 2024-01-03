$(document)
		.on(
				"click",
				'.CustSrch',
				function() {
					/* alert('test'); */
					/*
					 * var xml = submitdata("DBfields"); $("#xml").val(xml);
					 * alert('test1'); alert(xml);
					 * $("#CustSync").attr("action","/InterfaceDemo/Custsearch");
					 * $("#CustSync").submit();
					 */

					var chk1 = $("#CUS_CHK").val();
					var chk = $("#CUS_VER").val();
					var check1 = true;
					if (chk == "off") {
						/* alert(chk); */
						if (check1) {
							var param1 = $("#CUS_NUM").val();

							$.ajax({

								url : "/InterfaceDemo/Cust_CHK",
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
									.alert("Customer Already Exists in TI. Please tick the Check Box to Update the Customer");
							$("#CUS_CHK").show();
							$('#CUS_CHKHDR').show();
							$("#CUS_VER").val("on");
						} else if (chk1 == "Success") {
							$("#CustSync").attr("action",
									"/InterfaceDemo/Custsearch");
							$("#CustSync").submit();
						}
					} else if (chk == "on") {
						var chk1 = document.getElementById("CUS_CHK");
						if (chk1.checked == true) {
							$("#CustSync").attr("action",
									"/InterfaceDemo/Custsearch");
							$("#CustSync").submit();
						}

						if (chk1.checked == false) {

						}

					}

				});