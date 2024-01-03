<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="branchSelection" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collateral type code</title>
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
	function selectlister(event) {
		$(".eventrefresh").removeClass("highlighted");
		$(event).addClass('highlighted');
		// $(event).addClass('highlighted');
	}
	function codelist(event) {
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
			var val2 = test[1];
			var test1 = $("#codeType_" + val).attr('id');
			var test2 = $("#codeDes_" + val2).attr('id');
			var value_key1 = $("#" + test1).val();
			var value_key2 = $("#" + test2).val();
			$('#collateralType').val($.trim(value_key1));
			$('#collateralDescription').val($.trim(value_key2));
			$("#formId").attr("action", "returnCOllateral");
			$("#formId").submit();
		}
	}
	function formclose() {
		$("#formId").attr("action", "returnCOllateral");
		$("#formId").submit();
	}
</script>
<jsp:include page="../includes/header.jsp"></jsp:include>


</head>
</head>
<body class="body_bg">
	<branchSelection:form method="post" id="formId" name="form">
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
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Collateral
								code Selection</h5>
						</div>
					</div>
					<div class="col-md-12">
						<div class="row page_content">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-4 Control-label"
										style="font-weight: normal;">Collateral code *</label>
									<div class="col-md-6 input-group input-group-md">
										<branchSelection:textfield id="codeType"
											name="custVO.codeType" cssClass="form-control text_box" />
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
										style="font-weight: normal;">Collateral Description</label>
									<div class="col-md-6 input-group input-group-md">
										<branchSelection:textfield id="codeDes" name="custVO.codeDes"
											cssClass="form-control text_box" />
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
									<dir class="col-md-4"></dir>
									<div class="col-md-4 input-group input-group-md">
										<branchSelection:submit name="refreshsbrh"
											action="fetchCodeDetails" cssClass="button-new" onclick=""
											value="Refresh" />
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
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Collateral
												code</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Collateral
												description</label></th>


									</tr>
									<branchSelection:iterator value="codeDetailsList"
										status="status">
										<tr style="backtfboound: maroon;" class="eventrefresh"
											id='codeDetailsList_<branchSelection:property value="%{#status.count}"/>'
											onClick="selectlister(this)" ondblclick="codelist(this);">
											<td style="text-align: left; padding: 4px 5px; width: 200px;">
												<div class="form-group" align="left">
													<branchSelection:property value="codeType" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<branchSelection:property value="codeDes" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>

											<branchSelection:hidden id='%{"codeType_" + #status.count}'
												value="%{codeType}" />
											<branchSelection:hidden id='%{"codeDes_" + #status.count}'
												value="%{codeDes}" />

										</tr>
									</branchSelection:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<branchSelection:hidden name="collVO.collateralNumber" />
		<branchSelection:hidden id='collateralType'
			name="collVO.collateralType" />
		<branchSelection:hidden name="custVO.limitExpiry" />
		<branchSelection:hidden name="custVO.mnemonic" />
		<branchSelection:hidden name="custVO.limitAmount" />
		<branchSelection:hidden name="custVO.limitCategory" />
		<branchSelection:hidden name="custVO.limitCategoryID" />
		<branchSelection:hidden name="custVO.limitCurrency" />
		<branchSelection:hidden id='collateralDescription'
			name="collVO.collateralDescription" />
		<branchSelection:hidden name="collVO.claimNature" />
		<branchSelection:hidden name="collVO.counterpartyUniqueId" />
		<branchSelection:hidden name="collVO.originalValue" />
		<branchSelection:hidden name="collVO.issueDate" />
		<branchSelection:hidden name="collVO.maturityDate" />
		<branchSelection:hidden name="collVO.linkNature" />
		<branchSelection:hidden name="collVO.collateralCurrency" />
		<branchSelection:hidden name="collVO.currentValue" />
		<branchSelection:hidden name="collVO.collateralRevaluationDate" />
		<branchSelection:hidden name="collVO.branchCode" />
		<branchSelection:hidden name="collVO.natureOfLimit" />
		<branchSelection:hidden name="collVO.narrative" />
	</branchSelection:form>

</body>
</html>