<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="com.encore.extracts.utility.CommonMethods"%>
<%@page import="com.encore.extracts.dao.MakerChecker"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">

<title>EJB Client</title>

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
 
</script>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	String ejbResponse = "";
String tiRequ = "";
if (request.getParameter("input") != null) {
	String requestMsg = request.getParameter("input");
	//if (request.getAttribute("tokenChecker").equals(true)) {
		try {
			tiRequ = StringEscapeUtils.unescapeHtml(requestMsg);
			ejbResponse = CommonMethods.ejbProcess(tiRequ);
	
	//ejbResponse = TIPlusEJBClient.process(tiRequ);
		} catch (Exception e) {
	e.printStackTrace();
		}
	//}
}
%>
</head>
<body class="body_bg">
	<extracts:form method="post" id="formId" name="form">
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
						<h5 style="font-weight: bold; font-size: 13px;">&nbsp;EJB
							Client</h5>
					</div>
					<div class="col-md-10">
						<div class="row">
							<div class="col-sm-5 col-sm-offset-1 ">
								<h4>EJB Request</h4>
								<!-- <h4 style="color:#00aed7;"><font face="Times new roman">Request</font></h4> -->
								<extracts:textarea name="input" id="ejbreq" rows="25" cols="60"
									spellcheck="false" class="form-control" />
							</div>
							<div class="col-sm-1 ">
								<input class="btn btn-warning butpos" type="submit"
									value="Submit" />
							</div>
							<div class=" col-sm-5">
								<h4>EJB Response</h4>
								<textarea name="response" id="ejbres" rows="25" cols="60"
									spellcheck="false" class="form-control" ><c:out value = "<%=ejbResponse%>"/></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</extracts:form>
</body>
</html>