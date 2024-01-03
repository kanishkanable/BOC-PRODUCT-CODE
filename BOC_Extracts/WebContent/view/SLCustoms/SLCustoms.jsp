<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="slcustoms" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SLCustom Extract</title>
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-dropdown.css" />
<link type="text/css" rel="stylesheet" href="css/headfoot.css" />
<link rel="stylesheet" href="css/datepicker.css"></link>
<link href="css/font-awesome.css" rel="stylesheet"></link>
<link type="text/css" rel="stylesheet" href="css/commonTiplus.css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.collapsible.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="js/macxdnie81.js"></script>
<script type="text/javascript" src="js/maxcdnie82.js"></script>
<script type="text/javascript" src="js/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/highlight.pack.js"></script>
<script type="text/javascript" src="js/date_search.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/commonTiplus.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript">
	function branchSelection() {
		$("#formId").attr("action", "branchSelection");
		$("#formId").submit();
	}
	function extract() {
		var chkmndtry = ChkMandatoryFields("IsRequired");

		if (chkmndtry == "No") {
			return;
		} else {
		$("#formId").attr("action", "fetchRecords");
		$("#formId").submit();
		}
	}
	function formclose() {
		$("#formId").attr("action", "dashboard");
		$("#formId").submit();
	}
	function custListCancelMaker() {
		$("#custList").attr("action", "slcustListCancel");
		$("#custList").submit();
	}
	function cancel() {
		document.getElementById("formId").reset();

	}
	function ChkMandatoryFields(FieldsClassName) {
		var x = document.getElementsByClassName(FieldsClassName);
		var fieldid = "";
		var value = "";
		for (var i = 0; i < x.length; i++) {
			name = document.getElementsByClassName(FieldsClassName).item(i).name;
			fieldid = document.getElementsByClassName(FieldsClassName).item(i).id;
			value = document.getElementsByClassName(FieldsClassName).item(i).value;
			if (value == "" || value == "--Select--") {
				alert("Please select or input branch code");
				document.getElementById(fieldid).focus();
				return 'No';

			} else {
				//document.getElementById('SubmitSuccess').value = "";
			}
		}
		return 'Yes';
	}
	function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if (charCode != 46 && charCode > 31  && (charCode < 48 || charCode > 57))
          return false;

       return true;
    }
</script>
<jsp:include page="../includes/header.jsp"></jsp:include>
</head>
<body class="body_bg">
	<slcustoms:form method="post" id="formId" name="form">
		<div class="row">
			<div class="col-md-2">
				<div class="side_nav" style="width: 100%;">
					<ul class="nav nav-pills nav-stacked">
						<li style="text-align: left; background-color: #fdc60d"><a
							href="#" onclick="formclose()">Close</a></li>
					</ul>
				</div>
			</div>

			<div class="col-md-10 content_box">
				<div class="row page_content">
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;SL
								Customs</h5>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;">Input Branch<a href="#"
											onclick="branchSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a>
										</label>
										<div class="col-md-6 input-group input-group-md">
											<slcustoms:textfield id="branchCode"
												name="slcustomVO.branchCode" onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" />

										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;">Sub Product Type</label>
										<div class="col-md-6 input-group input-group-md">
											<slcustoms:select id="subPrdTyp" name="slcustomVO.subPrdType"
												list="subProductType" listKey="key" listValue="value"
												headerValue="<----->" headerKey=""
												style="width: 138px; height: 25px">
											</slcustoms:select>
										</div>
									</div>

								</div>

							</div>


							<div style="clear: both; height: 70px;">
								<!--  -->
							</div>


							<div class="col-md-12" >
								<div class="row page_content" >
                                   
									<div class="col-md-6" >
										<div class="form-group">
										<div style="margin-left: 500px"></div>
											<dir class="col-md-4" style="margin-left: 170px"></dir>
											<div class="col-md-4 input-group input-group-md">
											<input name="Extract" id="Extract" type="button"
													onclick="return extract()" value="Extract" ></input>
												<%-- <slcustoms:submit name="Extract" id="Extract"
													onclick="extract()" value="Extract" ></slcustoms:submit> --%>
											</div>
										</div>
									</div>


									<div class="col-md-6">
										<div class="form-group">
											
											<div class="col-md-4 input-group input-group-md">
											<input name="Cancel" id="Cancel" type="button"
													onclick="cancel()" value="Cancel"></input>
												<%-- <slcustoms:submit name="Cancel" id="Cancel"
													onclick="cancel()" value="Cancel"></slcustoms:submit> --%>
											</div>
										</div>
									</div>

								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>

	</slcustoms:form>

</body>
</html>