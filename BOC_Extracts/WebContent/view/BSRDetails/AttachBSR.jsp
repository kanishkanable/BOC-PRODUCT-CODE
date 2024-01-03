<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Attach BSR</title>
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
	function formclose() {
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
		$("#formId").attr("action", "fetchCustomerLimitDetails");
		$("#formId").submit();
	}
	function submitBSRDetails() {
		var chkmndtry = ChkMandatoryFields("IsRequired");

		if (chkmndtry == "No") {
			return;
		} else {
			var limitCategoryID = $("#limitCategoryID").val();
			$("#categoryDescription").val($("#limitCategory").val());
			$("#category").val(limitCategoryID);
			$("#formId").attr("action", "submitBSRDetails");
			$("#formId").submit();
		}
	}

	function deleteBSRDetails() {
		var deleteFlag = confirm("Are you sure you want to delete BSR details?");
		if (deleteFlag == true) {
		var limitCategoryID = $("#limitCategoryID").val();
		$("#categoryDescription").val($("#limitCategory").val());
		$("#category").val(limitCategoryID);
		$("#formId").attr("action", "deleteBSRDetails");
		$("#formId").submit();
		}
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
				alert("Fill the mandatory fields");
				document.getElementById(fieldid).focus();
				return 'No';

			}
		}
		return 'Yes';
	}

	function districtCodeSelection() {
		$("#formId").attr("action", "districtCodeSelection");
		$("#formId").submit();
	}

	function populationGroupCodeSelection() {
		$("#formId").attr("action", "populationGroupCodeSelection");
		$("#formId").submit();
	}

	function organisationTypeSelection() {
		$("#formId").attr("action", "organisationTypeSelection");
		$("#formId").submit();
	}

	function occupationCodeSelection() {
		$("#formId").attr("action", "occupationCodeSelection");
		$("#formId").submit();
	}

	function categoryBorrowerCodeSelection() {
		$("#formId").attr("action", "categoryBorrowerCodeSelection");
		$("#formId").submit();
	}

	function assetClassBorrAccountSelection() {
		$("#formId").attr("action", "assetClassBorrAccountSelection");
		$("#formId").submit();
	}

	function secUnsecLoanCodeSelection() {
		$("#formId").attr("action", "secUnsecLoanCodeSelection");
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
						<li style="text-align: left; background-color: #fdc60d"><a
							href="#" onclick="submitBSRDetails()">Submit</a></li>
						<extracts:if test="(bsrVo.isBsrDetailsAvailable=='Yes')">
							<li style="text-align: left; background-color: #fdc60d"><a
								href="#" onclick="deleteBSRDetails()">Delete</a></li>
						</extracts:if>
					</ul>
				</div>
			</div>
			<div class="col-md-10 content_box">
				<div class="row page_content">
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;
								Customer Limit Details</h5>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Customer" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="customerID" name="custVO.mnemonic"
												cssClass="form-control text_box" maxlength="20"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Category" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="limitCategory"
												name="custVO.limitCategory" cssClass="form-control text_box"
												readonly="true" />
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
												name="Amount" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="limitAmount"
												name="custVO.limitAmount" cssClass="form-control text_box"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Currency" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="limitCurrency"
												name="custVO.limitCurrency" cssClass="form-control text_box"
												readonly="true" />
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
												name="Expiry" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="limitExpiry"
												name="custVO.limitExpiry" cssClass="form-control text_box"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<div class="col-md-6 input-group input-group-md">
											<extracts:hidden id="limitCategoryID"
												name="custVO.limitCategoryID"
												cssClass="form-control text_box" readonly="true" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="page_collapsible collapse-open" id="body-section1">
							<span></span>
							<h5 style="font-weight: bold; font-size: 13px;">&nbsp;
								Attach BSR Details</h5>
						</div>
						<extracts:if test="hasActionErrors()">
							<div style="color: red; text-align: center;">
								<h4>
									<extracts:actionerror />
								</h4>
							</div>
						</extracts:if>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Place of Utilisation of Credit - District Code" /> <a
											href="#" onclick="districtCodeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="placeUtilisationCreditDistCode"
												name="bsrVo.placeUtilisationCreditDistCode"
												cssClass="form-control text_box" maxlength="3"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Place of Utilisation of Credit - Population Group Code" />
											<a href="#" onclick="populationGroupCodeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="placeUtilisationCreditPopGrpCode"
												name="bsrVo.placeUtilisationCreditPopGrpCode"
												cssClass="form-control text_box" maxlength="1"
												readonly="true" />
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
												name="Organisation Code" /> <a href="#"
											onclick="organisationTypeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="organisationCode"
												name="bsrVo.organisationCode"
												cssClass="form-control text_box" maxlength="2"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6" id="secCode">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Occupation Code" /> <a href="#"
											onclick="occupationCodeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="occupationCode"
												name="bsrVo.occupationCode"
												cssClass="form-control text_box" maxlength="5"
												readonly="true" />
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
												name="Category of Borrower Code" /> <a href="#"
											onclick="categoryBorrowerCodeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="categoryBorrowerCode"
												name="bsrVo.categoryBorrowerCode"
												cssClass="form-control text_box" maxlength="2"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Asset Classification of Borrowal Account Code" /> <a
											href="#" onclick="assetClassBorrAccountSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="assetClassiBorrowalAccoutCode"
												name="bsrVo.assetClassiBorrowalAccoutCode"
												cssClass="form-control text_box" maxlength="1"
												readonly="true" />
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
												name="Secured/ Un-Secured Loan Code" /> <a href="#"
											onclick="secUnsecLoanCodeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> </label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="securedUnsecuredLoanCode"
												name="bsrVo.securedUnsecuredLoanCode"
												cssClass="form-control text_box" maxlength="1"
												readonly="true" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<extracts:hidden id="category" name="bsrVo.category" />
						<extracts:hidden id="categoryDescription"
							name="bsrVo.categoryDescription" />

						<extracts:hidden name="bsrVo.isBsrDetailsAvailable" />

					</div>
				</div>
			</div>
		</div>
	</extracts:form>
</body>
</html>