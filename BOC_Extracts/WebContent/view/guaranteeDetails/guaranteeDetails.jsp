<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<%@page import="com.encore.extracts.utility.CommonMethods"%>
<%@page import="com.encore.extracts.dao.MakerChecker"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Guarantee Details</title>
<link rel="stylesheet" href="<extracts:url value="/css/jquery-ui.css"/>" />
<link rel="stylesheet" href="<extracts:url value="css/style.css" /> " />
<link type="text/css" rel="stylesheet"
	href="<extracts:url value="css/bootstrap.css"/>" />
<link type="text/css" rel="stylesheet"
	href="<extracts:url value="css/bootstrap-dropdown.css" />" />
<link type="text/css" rel="stylesheet"
	href="<extracts:url value="css/headfoot.css"/>" />
<link rel="stylesheet" href="<extracts:url value="css/datepicker.css"/>"></link>
<link href="css/font-awesome.css" rel="stylesheet"></link>
<link type="text/css" rel="stylesheet" href="css/commonTiplus.css"></link>
<link rel="stylesheet"
	href="<extracts:url value="css/jquery-ui.min.css"/>"></link>
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
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>


<jsp:include page="../includes/header.jsp"></jsp:include>
<script type="text/javascript">
	function customerSelection() {
		$("#action").val("guaranteeDetails");
		$("#formId").attr("action", "customerSelection");
		$("#formId").submit();
	}
	function fectchCustomerLimits() {
		var id = document.getElementById("customerID").innerHTML;
		if (id != null || id != undefined) {
			$("#action").val("guaranteeDetails");
			$("#formId").attr("action", "fectchCustomerLimits");
			$("#formId").submit();
		} else
			alert("Please input Customer ID");

	}
	function limitList(event) {
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
			//limitCategory
			var limitCategory = $("#limitCategory_" + val).attr('id');
			var limitCategoryVal = $("#" + limitCategory).val();
			$('#lmtCategory').val($.trim(limitCategoryVal));
			//limitAmount
			var limitAmount = $("#limitAmount_" + val).attr('id');
			var limitAmountVal = $("#" + limitAmount).val();
			$('#lmtAmount').val($.trim(limitAmountVal));
			//limitCurrency
			var limitCurrency = $("#limitCurrency_" + val).attr('id');
			var limitCurrencyVal = $("#" + limitCurrency).val();
			$('#lmtCurrency').val($.trim(limitCurrencyVal));
			//limitExpiry
			var limitExpiry = $("#limitExpiry_" + val).attr('id');
			var limitExpiryVal = $("#" + limitExpiry).val();
			$('#lmtExpiry').val($.trim(limitExpiryVal));
			//status
			var status = $("#status_" + val).attr('id');
			var statusVal = $("#" + status).val();
			$('#lmtStatus').val($.trim(statusVal));
			//limitCategoryID
			var limitCategoryID = $("#limitCategoryID_" + val).attr('id');
			var limitCategoryIDVal = $("#" + limitCategoryID).val();
			$('#lmtCategoryID').val($.trim(limitCategoryIDVal));
			//makerchekerAction
			var makerchekerActionID = $("#makerCheckerAction_" + val)
					.attr('id');
			var makerchekerActionVal = $("#" + makerchekerActionID).val();
			$('#makerChkAction').val($.trim(makerchekerActionVal));
			$("#formId").attr("action", "attachGuarantee");
			$("#formId").submit();
		}
	}
	function selectlister(event) {
		$(".eventrefresh").removeClass("highlighted");
		$(event).addClass('highlighted');
		// $(event).addClass('highlighted');
	}
</script>
<style type="text/css">
li {
	list-style: none;
}

.fetchButton {
	font-weight: bold;
}
</style>
</head>
<body class="body_bg">
<%
		String zoneId = CommonMethods.getZoneID();
	String makerChecker = request.getRemoteUser();
	//String makerChecker="SUPERVISOR";
	String isMakerChecker = "Maker";
	if (makerChecker != null && !makerChecker.isEmpty()) {
		isMakerChecker = MakerChecker.checkIsMakerOrChecker(makerChecker);
	}

	// 		out.println(zoneId);
	%>
	<label style="font-weight: normal;"><%=makerChecker%></label>
	<label style="font-weight: normal;"><%=isMakerChecker%></label>
	<extracts:form method="post" id="formId" name="form">
		<div class="row">

			<div class="col-md-2">
				<div class="side_nav" style="width: 100%;">
					<ul class="nav nav-pills nav-stacked">
						<li style="text-align: left; background-color: #fdc60d"><a
							href="dashboard">Close</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10 content_box">
				<div class="row page_content">
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Guarantee
								Details</h5>
						</div>
						<extracts:if test="hasActionErrors()">
							<div style="color: red; text-align: center;">
								<h4>
									<extracts:actionerror />
								</h4>
							</div>
						</extracts:if>
						<extracts:if test="hasActionMessages()">
							<div style="color: green; text-align: center; font-weight: bold;">
								<h4>
									<extracts:actionmessage />
								</h4>
							</div>
						</extracts:if>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Customer" /><a href="#" onclick="customerSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="customerID" name="custVO.mnemonic"
												cssClass="form-control text_box" maxlength="20" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<div class="col-md-4 input-group input-group-md">
											<extracts:submit name="refreshLimits" id="refreshLimits"
												cssClass="fetchButton" onclick="fectchCustomerLimits()"
												value="Fetch Limits"></extracts:submit>
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
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Category
												Description</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Limit</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Currency</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Expiry</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Status</label></th>
									</tr>
									<extracts:iterator value="customerLimitList"
										status="limitStatus">
										<tr style="backtfboound: maroon;" class="eventrefresh"
											id='customerLimitList_<extracts:property value="%{#limitStatus.count}"/>'
											onClick="selectlister(this)" ondblclick="limitList(this);">
											<td style="text-align: left; padding: 4px 5px; width: 200px;">
												<div class="form-group" align="left">
													<extracts:property value="limitCategory" />
													<extracts:hidden value="limitCategoryID" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="right">
													<extracts:property value="limitAmount" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="limitCurrency" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="limitExpiry" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="makerCheckerAction" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<extracts:hidden
												id='%{"limitCategory_" + #limitStatus.count}'
												value="%{limitCategory}" />
											<extracts:hidden id='%{"limitAmount_" + #limitStatus.count}'
												value="%{limitAmount}" />
											<extracts:hidden
												id='%{"limitCurrency_" + #limitStatus.count}'
												value="%{limitCurrency}" />
											<extracts:hidden id='%{"limitExpiry_" + #limitStatus.count}'
												value="%{limitExpiry}" />
											<extracts:hidden id='%{"status_" + #limitStatus.count}'
												value="%{status}" />
											<extracts:hidden
												id='%{"limitCategoryID_" + #limitStatus.count}'
												value="%{limitCategoryID}" />
											<extracts:hidden
												id='%{"makerCheckerAction_" + #limitStatus.count}'
												value="%{makerCheckerAction}" />
										</tr>
									</extracts:iterator>
								</tbody>
							</table>
						</div>
					</div>
					<extracts:hidden id="lmtCategory" name="custVO.limitCategory"
						cssClass="form-control text_box" />
					<extracts:hidden id="lmtAmount" name="custVO.limitAmount"
						cssClass="form-control text_box" />
					<extracts:hidden id="lmtCurrency" name="custVO.limitCurrency"
						cssClass="form-control text_box" />
					<extracts:hidden id="lmtExpiry" name="custVO.limitExpiry"
						cssClass="form-control text_box" />
					<extracts:hidden id="lmtStatus" name="custVO.status"
						cssClass="form-control text_box" />
					<extracts:hidden id="lmtCategoryID" name="custVO.limitCategoryID"
						cssClass="form-control text_box" />
					<extracts:hidden id="action" name="custVO.action"
						cssClass="form-control text_box" />
					<extracts:hidden id="makerChkAction"
						name="custVO.makerCheckerAction" cssClass="form-control text_box" />
				</div>
			</div>
		</div>

	</extracts:form>
</body>
</html>