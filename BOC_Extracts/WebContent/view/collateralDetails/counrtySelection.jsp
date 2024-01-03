<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Country Search</title>
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

<jsp:include page="../includes/header.jsp"></jsp:include>
<script type="text/javascript">
	function selectlister(event) {
		$(".eventrefresh").removeClass("highlighted");
		$(event).addClass('highlighted');
		// $(event).addClass('highlighted');
	}
	function countrylist(event) {
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
			var test1 = $("#cntryCode_" + val).attr('id');
			var value_key1 = $("#" + test1).val();
			$('#countryCode').val($.trim(value_key1));
			$("#formId").attr("action", "customerSelection");
			$("#formId").submit();
		}

	}
	function formclose() {
		$("#formId").attr("action", "customerSelection");
		$("#formId").submit();
	}
</script>

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
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Country
								Search</h5>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Code" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="countryCode"
												name="custVO.countryCode" cssClass="form-control text_box"
												maxlength="2" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Name" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="countryName"
												name="custVO.countryName" cssClass="form-control text_box"
												maxlength="35" />
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
											<extracts:submit name="refreshCtry" id="refreshCtry"
												action="countrySelection" value="Refresh"></extracts:submit>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group"
						style="min-height: 20px; overflow: auto; height: 80vh;">
						<table border="1px" align="left"
							style="width: 100%; border-collapse: collapse;">
							<tbody>
								<tr>
									<th
										style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Code</label></th>
									<th
										style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Name</label></th>
									<th
										style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Tenor</label></th>
									<th
										style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>ISO
											Country Code</label></th>

								</tr>
								<extracts:iterator value="countryList" status="ctrstatus">
									<tr style="backtfboound: maroon;" class="eventrefresh"
										id='countryList_<extracts:property value="%{#ctrstatus.count}"/>'
										onClick="selectlister(this)" ondblclick="countrylist(this);">
										<td style="text-align: left; padding: 4px 5px; width: 200px;">
											<div class="form-group" align="left">
												<extracts:property value="cntryCode" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>
										<td style="text-align: left; padding: 4px 5px;">
											<div class="form-group" align="left">
												<extracts:property value="ctryName" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>
										<td style="text-align: left; padding: 4px 5px;">
											<div class="form-group" align="left">
												<extracts:property value="ctryTenor" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>
										<td style="text-align: left; padding: 4px 5px;">
											<div class="form-group" align="left">
												<extracts:property value="isoCtryCode" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>

										<extracts:hidden id='%{"cntryCode_" + #ctrstatus.count}'
											value="%{cntryCode}" />
									</tr>
								</extracts:iterator>
							</tbody>
						</table>

					</div>
				</div>

			</div>

		</div>
		<extracts:hidden id="mnemonic" name="custVO.mnemonic" />
		<extracts:hidden id="fullName" name="custVO.fullName" />
		<extracts:hidden id="cust_corp" name="custVO.cust_corp" />
		<extracts:hidden id="correspondent" name="custVO.correspondent" />
		<extracts:hidden id="bank" name="custVO.bank" />
		<extracts:hidden id="number" name="custVO.number" />
		<extracts:hidden id="location" name="custVO.location" />
		<extracts:hidden id="mainLocalCustomers"
			name="custVO.mainLocalCustomers" />

	</extracts:form>
</body>
</html>