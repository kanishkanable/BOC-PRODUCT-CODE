<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<%-- <%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.encore.extracts.utility.CommonMethods"%>
<%@page import="com.encore.extracts.dao.MakerChecker"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Dash Board</title>
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
	function collateralDeatils() {
		$("#formId").attr("action", "collateralDetails");
		$("#formId").submit();
	}
	function guaranteeDetails() {
		$("#formId").attr("action", "guaranteeDetails");
		$("#formId").submit();
	}
	/* function slCustomsDetails() {
		$("#formId").attr("action", "sl_customs");
		$("#formId").submit();
	} */
	function dailyFXDue() {
		$("#formId").attr("action", "DailyFXDue");
		$("#formId").submit();
	}

	/* BSR Extract starts */
	function bsrHome() {
		$("#formId").attr("action", "bsrHome");
		$("#formId").submit();
	}
	/* function ejbClient() {
		$("#formId").attr("action", "ejbClient");
		$("#formId").submit();
	}
	 */
	/* BSR Extract ends */
</script>
<jsp:include page="../view/includes/header.jsp"></jsp:include>
</head>
<body class="body_bg">

	<%
	String zoneId = CommonMethods.getZoneID();
	String makerChecker = request.getRemoteUser();
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
						<!-- <li style="text-align: left; background-color: #fdc60d"><a
							href="https://ftilive.bankofceylon.local/tiplus2-global">Close</a></li> -->
						<!--  <li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.23.28.26:9443/tiplus2-global">Close</a></li> -->
						<li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.24.28.89:9443/tiplus2-global">Close</a></li>
						<!-- <li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.23.28.59:9443/tiplus2-global">Close</a></li> ***********-->
					</ul>
				</div>
			</div>
			<div class="col-md-10 content_box">
				<div class="row page_content">
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Extracts</h5>
						</div>
						<ul class="nav nav-pills nav-stacked"
							style="margin-top: 25px; font-size:">
							<li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="collateralDeatils()">Collateral Details</a></li>
							<li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="guaranteeDetails()">Guarantee Details</a></li>
							<%
							// N-Able Pvt LTD / 20-02-2023
							// BOCS validation added with Seychelles implementation.
							// Same BOCM functionality used as per the given instructions by BOC.

							if (zoneId != null && !zoneId.isEmpty() && !zoneId.contains("MALDIVES") && !zoneId.contains("SEYCHELLES")) {
							%>
							<!-- <li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="slCustomsDetails()">SL Customs</a></li> -->
							<li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="dailyFXDue()">Daily Foreign Exchange Due
									on Next Day</a></li>
							<%
							}
							%>
							<!-- BSR Extract starts -->
							<li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="bsrHome()">BSR Details</a></li>
							<!-- BSR Extract ends -->
							<!-- <li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="ejbClient()">EJB Client</a></li> -->
						</ul>
					</div>
				</div>
			</div>
	</extracts:form>
</body>
</html>