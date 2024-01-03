<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">

<title>Customer Selection</title>
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
	function fetchCustomerDetails() {
		$("#formId").attr("action", "fetchCustomerDetails");
		$("#formId").submit();
	}
	function selectlister(event) {
		$(".eventrefresh").removeClass("highlighted");
		$(event).addClass('highlighted');
		// $(event).addClass('highlighted');
	}
	function custlist(event) {
		var id = $('.highlighted').attr('id');
		if (id == undefined) {
			$('#selectrow').css('display', 'block');
		} else {
			$('body').modal();
			$('body').removeClass('removePageLoad');
			$('input,a').removeClass('removePageLoad');
			$('input,a').addClass('addPageLoad');
			$('body').addClass('addPageLoad');
			var test = id.split('_');
			var val = test[1];
			var test1 = $("#custMnemonic_" + val).attr('id');
			var value_key1 = $("#" + test1).val();
			$('#mnemonic').val($.trim(value_key1));
			$("#formId").attr("action", $("#action").val());
			$("#formId").submit();
		}
	}

	function countrySelection() {
		$("#formId").attr("action", "countrySelection");
		$("#formId").submit();
	}
	function formclose() {
		$("#formId").attr("action", $("#action").val());
		$("#formId").submit();
	}
</script>
<jsp:include page="../includes/header.jsp"></jsp:include>

</head>
<body class="body_bg">
	<extracts:form method="post" id="formId" name="form">
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
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Customer
								Search</h5>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Source banking
								business" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="sbb" name="custVO.sbb"
												value="Bank of Ceylon-Domestic" readonly="true"
												cssClass="form-control text_box" />
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12" style="display: none;">
								<div class="row page_content">
									<div class="col-md-3">
										<div class="form-group">
											<div class="input-group input-group-md"></div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<div class="input-group input-group-md">
												<extracts:checkbox cssStyle="width:20px;"
													name="custVO.cust_corp" id="cust_corp" value="true" />
												<label class="Control-label" style="font-weight: normal;"><extracts:text
														name="Customer/Corporate" /> </label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<div class="input-group input-group-md">
												<extracts:checkbox cssStyle="width:20px;"
													name="custVO.correspondent" id="correspondent" value="true" />
												<label class=" Control-label" style="font-weight: normal;"><extracts:text
														name="Correspondent" /> </label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<div class="input-group input-group-md">
												<extracts:checkbox cssStyle="width:20px;" id="bank"
													name="custVO.bank" value="true" />
												<label class=" Control-label" style="font-weight: normal;"><extracts:text
														name="Bank" /> </label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Mnemonic" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="mnemonic" name="custVO.mnemonic"
													cssClass="form-control text_box" maxlength="20" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Full Name" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="fullName" name="custVO.fullName"
													cssClass="form-control text_box" maxlength="35" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Number" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="number" name="custVO.number"
													cssClass="form-control text_box" maxlength="12" />
											</div>
										</div>
									</div>
									<div class="col-md-6" style="display: none;">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Location" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="location" name="custVO.location"
													cssClass="form-control text_box" maxlength="6" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12" style="display: none;">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Country" /><a href="#" onclick="countrySelection()"><img
													src="images/Search-icon.png" alt="serach"
													style="WIDTH: 15px; height: 15px;" /></a></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="country" name="custVO.countryCode"
													cssClass="form-control text_box" maxlength="2" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Main / local
									customers" /></label>
											<div class="col-md-6 input-group input-group-md">
												<select name="custVO.mainLocalCustomers"
													id="mainLocalCustomers">
													<option value="M">Main Bank Customers</option>
													<option value="L">Local Customers</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group"></div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<dir class="col-md-6"></dir>
											<div class="col-md-4 input-group input-group-md">
												<extracts:submit name="refreshCust" id="refreshCust"
													action="fetchCustomerDetails" value="Refresh"></extracts:submit>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group"
							style="min-height: 20px; overflow: auto; height: 60vh;">
							<table border="1px" align="left"
								style="width: 100%; border-collapse: separate;">
								<tbody>
									<tr>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Mnemonic</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Full
												Name</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Location</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Number</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Group</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Country</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Account
												Officer</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Blocked</label></th>
									</tr>
									<extracts:iterator value="custDetailsList" status="status">
										<tr style="backtfboound: maroon;" class="eventrefresh"
											id='custDetailsList_<extracts:property value="%{#status.count}"/>'
											onClick="selectlister(this)" ondblclick="custlist(this);">
											<td style="text-align: left; padding: 4px 5px; width: 200px;">
												<div class="form-group" align="left">
													<extracts:property value="custMnemonic" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custFullName" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custLocation" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custNumber" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custGroup" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custCountry" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custAccountOfficer" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="custBlocked" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<extracts:hidden id='%{"custMnemonic_" + #status.count}'
												value="%{custMnemonic}" />
										</tr>
									</extracts:iterator>
								</tbody>
							</table>
						</div>
					</div>
					<extracts:hidden id="action" name="custVO.action"
						cssClass="form-control text_box" />
				</div>
			</div>
		</div>
	</extracts:form>
</body>
</html>