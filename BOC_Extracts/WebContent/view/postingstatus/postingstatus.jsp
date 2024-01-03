<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Posting & Swift Status</title>
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

<jsp:include page="../includes/headerPostingStatus.jsp"></jsp:include>

</head>
<body class="body_bg">
	<extracts:form method="post" id="formId" name="form">
		<div class="row">

			<div class="col-md-2">
				<div class="side_nav" style="width: 100%;">
					<ul class="nav nav-pills nav-stacked">
						<!-- <li style="text-align: left; background-color: #fdc60d"><a
							href="https://ftilive.bankofceylon.local/tiplus2-global">Close</a></li> -->
						<!-- <li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.23.28.26:9443/tiplus2-global">Close</a></li> -->
						<li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.24.28.89:9443/tiplus2-global">Close</a></li>

						<!-- <li style="text-align: left; background-color: #fdc60d"><a
							href="https://172.23.28.59:9443/tiplus2-global">Close</a></li> -->
					</ul>
				</div>
			</div>
			<div class="col-md-10 content_box" style="height: 100%">
				<div class="row page_content">
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Posting
								& Swift Status</h5>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Master Reference" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="masterRef" name="postingVO.masterRef"
												cssClass="form-control text_box" maxlength="20" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Event Reference" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="eventRef" name="postingVO.eventRef"
												cssClass="form-control text_box" maxlength="6" />
										</div>
									</div>
								</div>
							</div>
						</div>


						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-2">
									<extracts:submit name="refreshCtry" id="refreshCtry"
										action="postingstatus" value="Refresh"></extracts:submit>
								</div>
								<div class="col-md-2">
									<extracts:submit name="statuschange" id="statuschange"
										action="statuschange" value="Posting Succeeded"></extracts:submit>
								</div>
							</div>
						</div>
					</div>
				</div>
	</extracts:form>

	<div class="col-md-12" style="margin-bottom: 25px"></div>
	<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Posting
		Status</h5>
	<div class="col-md-12">
		<div class="form-group" style="min-height: 20px; overflow: auto;">
			<table border="1px" align="left"
				style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Master
								Reference</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Event
								Reference</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Posting
								Status</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Account
								Number</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Transaction
								Amount</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Credit
								/ Debit</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>TransactionID</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>ReqFileName</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>ResFileName</label></th>

					</tr>
					<extracts:iterator value="postingList" status="ctrstatus">
						<tr style="backtfboound: maroon;" class="eventrefresh"
							id='postingList_<extracts:property value="%{#ctrstatus.count}"/>'
							onClick="selectlister(this)" ondblclick="postinglist(this);">
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="masterRef" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="eventRef" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="status" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="right">
									<extracts:property value="accountNumber" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="right">
									<extracts:property value="tranAmount" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="creditDebit" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="TransactionID" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="requestFilename" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="responseFilename" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>

						</tr>

					</extracts:iterator>
				</tbody>
			</table>

		</div>
	</div>

	<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Swift Status</h5>

	<div class="col-md-12">
		<div class="form-group" style="min-height: 20px; overflow: auto;">
			<table border="1px" align="left"
				style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Master
								Reference</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Event
								Reference</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Swift
								Status</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Received
								Date</label></th>
						<th
							style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Processed
								Date</label></th>

					</tr>
					<extracts:iterator value="swiftList" status="ctrstatus1">
						<tr style="backtfboound: maroon;" class="eventrefresh"
							id='swiftList_<extracts:property value="%{#ctrstatus1.count}"/>'
							onClick="selectlister(this)" ondblclick="swiftlist(this);">
							<td style="text-align: left; padding: 4px 5px; width: 200px;">
								<div class="form-group" align="left">
									<extracts:property value="masterRef" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="eventRef" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="status" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px; width: 200px;">
								<div class="form-group" align="left">
									<extracts:property value="receivedDate" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
							<td style="text-align: left; padding: 4px 5px;">
								<div class="form-group" align="left">
									<extracts:property value="processedDate" />
									<div class="col-md-8 input-group input-group-md"></div>
								</div>
							</td>
						</tr>

					</extracts:iterator>
				</tbody>
			</table>

		</div>
	</div>


</body>
</html>