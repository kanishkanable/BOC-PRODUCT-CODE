<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Asset Classification of Borrowal Account Search</title>
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
	}

	function assetClassBorrAccountList(event) {
		$(".assetClassBorrAccountList").removeClass("highlighted");
		$(event).addClass("highlighted");
		var assetClassBorrAccountCode = $(event).find("td").eq(1).text().trim();
		$('#assetClassBorrAccountCodeGridValue').val(assetClassBorrAccountCode);
		$("#formId").attr("action", "openAttachBSRPage");
		$("#formId").submit();
	}

	function formclose() {
		$("#formId").attr("action", "openAttachBSRPage");
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
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Asset
								Classification of Borrowal Account Search</h5>
						</div>

						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Asset Classification of Borrowal Account" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="assetClassBorrAccountFilter"
												name="bsrLookupVo.assetClassBorrAccountFilter"
												cssClass="form-control text_box" maxlength="200" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Code" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="assetClassBorrAccountCodeFilter"
												name="bsrLookupVo.assetClassBorrAccountCodeFilter"
												cssClass="form-control text_box" maxlength="1" />
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
										<div class="col-md-6"></div>
										<div class="col-md-4 input-group input-group-md">
											<extracts:submit name="refreshAssetClassBorrAccountDetails"
												id="refreshAssetClassBorrAccountDetails"
												action="fetchAssetClassBorrAccountDetails" value="Refresh"></extracts:submit>
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
											style="padding: 4px 5px; width: 500px; font-weight: bold; position: sticky; top: 0;"><label>Asset
												Classification of Borrowal Account</label></th>
										<th
											style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Code</label></th>

									</tr>
									<extracts:iterator value="assetClassBorrAccountList"
										status="status">

										<tr style="backtfboound: maroon;" class="eventrefresh"
											id='assetClassBorrAccountList_<extracts:property value="%{#status.count}"/>'
											onClick="selectlister(this)"
											ondblclick="assetClassBorrAccountList(this);">

											<td style="text-align: left; padding: 4px 5px; width: 500px;">
												<div class="form-group" align="left">
													<extracts:property value="assetClassBorrAccount" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>

											<td style="text-align: left; padding: 4px 5px;">
												<div class="form-group" align="left">
													<extracts:property value="assetClassBorrAccountCode" />
													<div class="col-md-8 input-group input-group-md"></div>
												</div>
											</td>
										</tr>
									</extracts:iterator>
								</tbody>
							</table>
						</div>
					</div>

					<extracts:hidden id="assetClassBorrAccountCodeGridValue"
						name="bsrVo.assetClassiBorrowalAccoutCode" />

					<extracts:hidden name="bsrVo.placeUtilisationCreditDistCode" />
					<extracts:hidden name="bsrVo.placeUtilisationCreditPopGrpCode" />
					<extracts:hidden name="bsrVo.organisationCode" />
					<extracts:hidden name="bsrVo.occupationCode" />
					<extracts:hidden name="bsrVo.categoryBorrowerCode" />
					<extracts:hidden name="bsrVo.securedUnsecuredLoanCode" />


					<extracts:hidden name="custVO.mnemonic" />
					<extracts:hidden name="custVO.limitCategory" />
					<extracts:hidden name="custVO.limitAmount" />
					<extracts:hidden name="custVO.limitCurrency" />
					<extracts:hidden name="custVO.limitExpiry" />

					<extracts:hidden name="custVO.limitCategoryID" />

					<extracts:hidden name="bsrVo.isBsrDetailsAvailable" />

				</div>
			</div>
		</div>
	</extracts:form>
</body>
</html>