<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="extracts" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.encore.extracts.dao.MakerChecker"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=9,chrome=1">
<title>Attach Collateral</title>
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
<%
	String userName = request.getRemoteUser();
//String userName = "USER1";
String isMakerChecker = "Maker";
if (userName != null && !userName.isEmpty()) {
	isMakerChecker = MakerChecker.checkIsMakerOrChecker(userName);
}
%>
<script type="text/javascript">
var user = "<%=userName%>"; 
var userRole = "<%=isMakerChecker%>";
	$(document).ready(function() {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
	function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if (charCode != 46 && charCode > 31  && (charCode < 48 || charCode > 57))
          return false;

       return true;
    }
	function formclose() {
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
		$("#action").val("collateralDetails");
		$("#formId").attr("action", "fectchCustomerLimits");
		$("#formId").submit();
	}
	function submitCollateralAttachment() {
		var chkmndtry = ChkMandatoryFields("IsRequired");
	 if (chkmndtry == "No") {
		return;
		} else { 
		var chkCustomerValidate = ChkCustomerFields();
		if(chkCustomerValidate =="Yes"){
			var chkMaturityDate = ChkMandatoryDateFields();
			var CurrentDate = new Date();
			chkMaturityDate = new Date(chkMaturityDate);
			if(chkMaturityDate > CurrentDate){
					var answer = confirm("Do you want to Submit?");
					if (answer) {
					var limitCategoryID = $("#limitCategoryID").val();
					$("#categoryDescription").val($("#limitCategory").val());
					$("#category").val(limitCategoryID);
					$("#userNames").val(user);
					$("#userRoles").val(userRole);
					var memonicVal = $("#customerID").val();
					$("#menonic").val(memonicVal);
					$("#formId").attr("action", "submitAttachment");
					$("#formId").submit();
					
					}
			}else{
			    alert('Maturity date is not greater than the current date.');
			}
		}else{
			alert('Entered CIF in Counter Unique ID field is not matched with the Limit customer');
			 
		}
		
		}
	} 
 	function RejectCollateralAttachment() { 
 		var answer = confirm("Do you want to Reject?");
 		if (answer) {
 			var limitCategoryID = $("#limitCategoryID").val();
			 
			$("#categoryDescription").val($("#limitCategory").val());
			$("#category").val(limitCategoryID);
			
			var notesVal = $("#notesID").val();	
			 $("#userNames").val(user);
			 $("#userRoles").val(userRole);
			  var memonicVal = $("#customerID").val();
			 $("#menonic").val(memonicVal);
		//	$("#checkNotes").val('notesID');
			 
			if (notesVal == "") {
				alert("Fill the mandatory fields");
				document.getElementById('notesID').focus();

			} else {
				 
				$("#formId").attr("action", "rejectAttachment");
				$("#formId").submit();
			} 
 		}
			
	}
 	function ApproveCollateralAttachment() {
	 
		var answer = confirm("Do you want to Approve?");
 		if (answer) {
 			 
			var limitCategoryID = $("#limitCategoryID").val();
			$("#categoryDescription").val($("#limitCategory").val());
			$("#category").val(limitCategoryID);
			 $("#userNames").val(user);
			 $("#userRoles").val(userRole);
			 var memonicVal = $("#customerID").val();
			 $("#menonic").val(memonicVal);
			
			var collMakerCheckerActionVal = $("#collMakerChkActionID").val();
			$("#collMakerChkAction").val(collMakerCheckerActionVal);
			$("#formId").attr("action", "approveAttachment");
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
			} else {
				//document.getElementById('SubmitSuccess').value = "";
				//alert(value);
				
			}
		}
		return 'Yes';
	}
	
	function ChkMandatoryDateFields() {
		var date = $("#maturityDate").val();
		
		return date;
		}
	
	function ChkCustomerFields() {
		var entiredCIF = $("#counterpartyUniqueId").val();
	 
		var tiCIF = $("#customerID").val();
		 
		if(entiredCIF != tiCIF){
			return 'No'
		}		
		return 'Yes';
	}
	function validateCollateralType() {
		$('#validateSecurityCode').val(null);
		var collateralType = $("#collateralType").val();
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
		if (collateralType != null || collateralType != undefined) {
			$("#formId").attr("action", "validateCollateralType");
			$("#formId").submit();
		}
	}
	
	
	function collateralSelection() {
		$("#formId").attr("action", "collateralSelection");
		$("#formId").submit();
	}
</script>
<style type="text/css">
.hide {
	display: none;
}
</style>
</head>
<body class="body_bg">

	<label style="font-weight: normal;"><%=userName%></label>
	<label style="font-weight: normal;"><%=isMakerChecker%></label>
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
											<%-- <extracts:textfield id="mnemonic" name="custVO.customerID"
												cssClass="form-control text_box" /> --%>
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
												name="Catergory" /></label>
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
								Attach Collateral Details</h5>
						</div>
						<extracts:if test="hasActionErrors()">
							<div style="color: red; text-align: center;">
								<h4>
									<extracts:actionerror />
								</h4>
							</div>
						</extracts:if>
						<%
							if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
						%>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Number * (C+SUBTOTAL CODE+CIF)" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralNumber"
												name="collVO.collateralNumber"
												cssClass="form-control text_box IsRequired" maxlength="32" />
											<%-- <c:if test="${ collVO.noInput == 'CNum' }">
												<span style="color: red;">Please input Collateral
													Number</span>
											</c:if> --%>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Description *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="collateralDescription"
												name="collVO.collateralDescription" rows="7"
												cssClass="form-control text_box1 IsRequired" maxlength="256" />
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
												name="Collateral Type *" /><a href="#"
											onclick="collateralSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralType"
												name="collVO.collateralType"
												onblur="validateCollateralType()"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
										</div>
									</div>
								</div>
								<c:if test="${not empty collVO.validateSecurityCode }">
									<div class="col-md-6" id="secCode">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Security code *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="securityCode"
													name="collVO.securityCode"
													cssClass="form-control text_box IsRequired" maxlength="32" />
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Claim Nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="claimNature" name="Senior Based"
												value="Senior Based"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
											<extracts:hidden id="claimNature" name="collVO.claimNature"
												value="0" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Link nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="linkNature" name="Amount Based"
												value="Amount Based"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
											<extracts:hidden id="linkNature" name="collVO.linkNature"
												value="0" />
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
												name="Counterparty Unique id *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="counterpartyUniqueId"
												name="collVO.counterpartyUniqueId"
												cssClass="form-control text_box IsRequired" maxlength="32" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Currency *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralCurrency"
												name="collVO.collateralCurrency"
												cssClass="form-control text_box IsRequired" maxlength="5" />
										</div>
									</div>
								</div>

							</div>
						</div>
						<c:if test="${not empty collVO.validateSecurityCode }">
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Unit Count *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="unitCount" name="collVO.unitCount"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Unit Value *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="unitValue" name="collVO.unitValue"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Original value *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="originalValue"
												name="collVO.originalValue"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Current Value *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="currentValue"
												name="collVO.currentValue"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30" />
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
												name="Issue date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="issueDate" name="collVO.issueDate"
												cssClass="datepicker form-control text_box IsRequired" />
										</div>
									</div>

								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Revaluation Date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralRevaluationDate"
												name="collVO.collateralRevaluationDate"
												cssClass="datepicker form-control text_box IsRequired" />
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
												name="Maturity date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="maturityDate"
												name="collVO.maturityDate"
												cssClass="datepicker form-control text_box IsRequired" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Branch Code *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="branchCode" name="collVO.branchCode"
												cssClass="form-control text_box IsRequired" maxlength="32" />
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
												name="Nature Of Limit Line *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:select headerKey="" headerValue="--Select--"
												list="#{'0':'Revolving','1':'Non-Revolving'}"
												id="natureOfLimit" name="collVO.natureOfLimit"
												cssClass="form-control text_box IsRequired" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Limit Purpose" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="narrative" name="collVO.narrative"
												rows="7" cssClass="form-control text_box1 IsRequired"
												maxlength="256" />
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
												name="Reason For Rejection" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="notesIDs" name="collVO.notes" rows="7"
												cssClass="form-control text_box1" maxlength="256"
												readonly="true" />
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
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"></label>
										<div class="col-md-4 input-group input-group-md">

											<input type="button" name="attachLimits" id="attachLimits"
												onclick="return submitCollateralAttachment()" value="Submit" />
											<%-- <extracts: name="attachLimits" id="attachLimits"
												onclick="return submitCollateralAttachment()" value="Submit"></extracts:submit> --%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
							} else if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Checker")) {
						%>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Number * (C+SUBTOTAL CODE+CIF)" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralNumber"
												name="collVO.collateralNumber"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
											<%-- <c:if test="${ collVO.noInput == 'CNum' }">
												<span style="color: red;">Please input Collateral
													Number</span>
											</c:if> --%>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Description *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="collateralDescription"
												name="collVO.collateralDescription" rows="7"
												cssClass="form-control text_box1 IsRequired" maxlength="256"
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
												name="Collateral Type *" /> <!-- <a href="#"
											onclick="collateralSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px ;" /></a> --></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralType"
												name="collVO.collateralType"
												onblur="validateCollateralType()"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
										</div>
									</div>
								</div>
								<c:if test="${not empty collVO.validateSecurityCode }">
									<div class="col-md-6" id="secCode">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Security code *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="securityCode"
													name="collVO.securityCode"
													cssClass="form-control text_box IsRequired" maxlength="32"
													readonly="true" />
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Claim Nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="claimNature" name="Senior Based"
												value="Senior Based"
												cssClass="form-control text_box IsRequired" readonly="true" />
											<extracts:hidden id="claimNature" name="collVO.claimNature"
												value="0" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Link nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="linkNature" name="Amount Based"
												value="Amount Based"
												cssClass="form-control text_box IsRequired" readonly="true" />
											<extracts:hidden id="linkNature" name="collVO.linkNature"
												value="0" />
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
												name="Counterparty Unique id *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="counterpartyUniqueId"
												name="collVO.counterpartyUniqueId"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Currency *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralCurrency"
												name="collVO.collateralCurrency"
												cssClass="form-control text_box IsRequired" maxlength="5"
												readonly="true" />
										</div>
									</div>
								</div>

							</div>
						</div>
						<c:if test="${not empty collVO.validateSecurityCode }">
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Unit Count *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="unitCount" name="collVO.unitCount"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30"
													readonly="true" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Unit Value *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="unitValue" name="collVO.unitValue"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30"
													readonly="true" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<div class="col-md-12">
							<div class="row page_content">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Original value *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="originalValue"
												name="collVO.originalValue"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Current Value *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="currentValue"
												name="collVO.currentValue"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30"
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
												name="Issue date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="issueDate" name="collVO.issueDate"
												cssClass="form-control text_box IsRequired"
												readonly="true" />
										</div>
									</div>

								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Collateral Revaluation Date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="collateralRevaluationDate"
												name="collVO.collateralRevaluationDate"
												cssClass="form-control text_box IsRequired"
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
												name="Maturity date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="maturityDate"
												name="collVO.maturityDate"
												cssClass="form-control text_box IsRequired"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Branch Code *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="branchCode" name="collVO.branchCode"
												cssClass="form-control text_box IsRequired" maxlength="32"
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
												name="Nature Of Limit Line *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield headerKey="" headerValue="--Select--"
												list="#{'0':'Revolving','1':'Non-Revolving'} "
												id="natureOfLimit" name="collVO.natureOfLimit"
												cssClass="form-control text_box IsRequired" readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Limit Purpose" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="narrative" name="collVO.narrative"
												rows="7" cssClass="form-control text_box1 IsRequired"
												maxlength="256" readonly="true" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<c:choose>
									<c:when
										test="${collVO.collMakerCheckerAction eq 'Approval Pending' }">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-md-4 Control-label"
													style="font-weight: normal;"><extracts:text
														name="Reason For Rejection" /></label>
												<div class="col-md-6 input-group input-group-md">
													<extracts:textarea id="notesID" name="collVO.notes"
														rows="7" cssClass="form-control text_box1" maxlength="256" />
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-md-12">
											<div class="row page_content">
												<div class="col-md-6">
													<div class="form-group">
														<label class="col-md-4 Control-label"
															style="font-weight: normal;"><extracts:text
																name="Reason For Rejection" /></label>
														<div class="col-md-6 input-group input-group-md">
															<extracts:textarea id="notesID" name="collVO.notes"
																rows="7" cssClass="form-control text_box1"
																maxlength="256" readonly="true" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:if
							test="${collVO.collMakerCheckerAction eq 'Approval Pending' }">
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"></label>
											<div class="col-md-4 input-group input-group-md">
												<extracts:hidden id="collMakerChkActionID" value="Approved" />
												<input type="button" name="approveAttachLimits"
													id="approveAttachLimits"
													onclick="return ApproveCollateralAttachment()"
													value="Approve" />
												<%-- <extracts: name="attachLimits" id="attachLimits"
												onclick="return submitCollateralAttachment()" value="Submit"></extracts:submit> --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"></label>
											<div class="col-md-4 input-group input-group-md">
												<extracts:hidden id="collMakerChkActionID" value="Rejected" />
												<input type="button" name="rejectAttachLimits"
													id="rejectAttachLimits"
													onclick="return RejectCollateralAttachment()"
													value="Reject" />
												<%-- <extracts: name="attachLimits" id="attachLimits"
												onclick="return submitCollateralAttachment()" value="Submit"></extracts:submit> --%>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<%
							}
						%>
						<extracts:hidden name="collVO.validateSecurityCode"
							id="validateSecurityCode"></extracts:hidden>
						<extracts:hidden id="category" name="collVO.category" />
						<extracts:hidden id="categoryDescription"
							name="collVO.categoryDescription" />
						<extracts:hidden id="collMakerChkAction"
							name="collVO.collMakerCheckerAction" />
						<extracts:hidden id="userNames" name="collVO.userName" />
						<extracts:hidden id="userRoles" name="collVO.userRole" />
						<extracts:hidden id="menonic" name="collVO.customerID" />
						<extracts:hidden id="action" name="custVO.action" />

						<%-- <extracts:hidden id="onReload" name="collVO.onReload" /> --%>
					</div>
				</div>
			</div>
	</extracts:form>
</body>
</html>