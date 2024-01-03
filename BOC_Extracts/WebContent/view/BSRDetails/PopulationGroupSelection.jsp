<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Population Group Search</title>
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

	function populationGroupList(event) {
		$(".populationGroupList").removeClass("highlighted");
		$(event).addClass("highlighted");
		var populationGroupCode = $(event).find("td").eq(2).text().trim();
		$('#populationGroupCodeGridValue').val(populationGroupCode);
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
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;Population
								Group Search</h5>
						</div>

						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Population Group of Centre" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="populationGroupofCentreFilter"
												name="bsrLookupVo.populationGroupofCentreFilter"
												cssClass="form-control text_box" maxlength="400" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Population of Centre" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="populationofCentreFilter"
												name="bsrLookupVo.populationofCentreFilter"
												cssClass="form-control text_box" maxlength="400" />
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
												name="Code" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="populationGroupCodeFilter"
												name="bsrLookupVo.populationGroupCodeFilter"
												cssClass="form-control text_box" maxlength="1" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group"></div>
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
										<extracts:submit name="refreshPopulationGroup"
											id="refreshPopulationGroup"
											action="fetchPopulationGroupDetails" value="Refresh"></extracts:submit>
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
										style="padding: 4px 5px; width: 300px; font-weight: bold; position: sticky; top: 0;"><label>Population
											Group of Centre</label></th>
									<th
										style="padding: 4px 5px; width: 300px; font-weight: bold; position: sticky; top: 0;"><label>Population
											of Centre</label></th>
									<th
										style="padding: 4px 5px; font-weight: bold; position: sticky; top: 0;"><label>Code</label></th>


								</tr>
								<extracts:iterator value="populationGroupList" status="status">

									<tr style="backtfboound: maroon;" class="eventrefresh"
										id='populationGroupList_<extracts:property value="%{#status.count}"/>'
										onClick="selectlister(this)"
										ondblclick="populationGroupList(this);">

										<td style="text-align: left; padding: 4px 5px; width: 300px;">
											<div class="form-group" align="left">
												<extracts:property value="populationGroupofCentre" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>

										<td style="text-align: left; padding: 4px 5px; width: 300px;">
											<div class="form-group" align="left">
												<extracts:property value="populationofCentre" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>

										<td style="text-align: left; padding: 4px 5px;">
											<div class="form-group" align="left">
												<extracts:property value="populationGroupCode" />
												<div class="col-md-8 input-group input-group-md"></div>
											</div>
										</td>
									</tr>
								</extracts:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<extracts:hidden id="populationGroupCodeGridValue"
					name="bsrVo.placeUtilisationCreditPopGrpCode" />

				<extracts:hidden name="bsrVo.placeUtilisationCreditDistCode" />
				<extracts:hidden name="bsrVo.organisationCode" />
				<extracts:hidden name="bsrVo.occupationCode" />
				<extracts:hidden name="bsrVo.categoryBorrowerCode" />
				<extracts:hidden name="bsrVo.assetClassiBorrowalAccoutCode" />
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

	</extracts:form>
</body>
</html>