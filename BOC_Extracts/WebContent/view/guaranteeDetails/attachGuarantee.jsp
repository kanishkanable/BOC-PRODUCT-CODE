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
<title>Attach Guarantee</title>
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
//String userName = "SUPERVISOR";
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

	$('#guaranteeNature').change(function() {
		validateGuaranteeNature();
	}); 
});

	function formclose() {
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
		$("#action").val("guaranteeDetails");
		$("#formId").attr("action", "fectchCustomerLimits");
		$("#formId").submit();
	}
	
	function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
       if (charCode != 46 && charCode > 31  && (charCode < 48 || charCode > 57))
          return false;

       return true;
    }
	function validateGuaranteeNature(){
		$('#validateGuaranteeNature').val(null);
		var  guaranteeNature = $("#guaranteeNature").val();
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
 		if (guaranteeNature != null) {
 		$("#formId").attr("action", "validateGuaranteeNature");
			$("#formId").submit();
		} 
	}
	function submitGuaranteeAttachment() {
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
				$("#formId").attr("action", "submitGuaranteeAttachment");
				$("#formId").submit();
		 		}
				}
				else{
				    alert('Maturity date must be greater than current date.');
				}
			}else{
				alert('Entered CIF in Counter Unique ID field is not matched with the Limit customer');
				 
			}
			
		}
	}
	function RejectGuaranteeAttachment() {
		var chkmndtry = ChkMandatoryFields("IsRequired");

		if (chkmndtry == "No") {
			return;
		} else {
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
		
			if (notesVal == "") {
				alert("Fill the mandatory fields");
				document.getElementById('notesID').focus();

			} else{	
				$("#formId").attr("action", "rejectGuaranteeAttachment");
				$("#formId").submit();
			}
			 
			}
		}
	}
	function ApproveGuaranteeAttachment() {
		var chkmndtry = ChkMandatoryFields("IsRequired");

		if (chkmndtry == "No") {
			return;
		} else {
			var answer = confirm("Do you want to Approve?");
	 		if (answer) {
			var limitCategoryID = $("#limitCategoryID").val();
			$("#categoryDescription").val($("#limitCategory").val());
			$("#category").val(limitCategoryID);
			 $("#userNames").val(user);
			 $("#userRoles").val(userRole);
			 var memonicVal = $("#customerID").val();
			 $("#menonic").val(memonicVal);
			
			var makerCheckerActionVal = $("#makerChkActionID").val();
			$("#MakerChkAction").val(makerCheckerActionVal);
			$("#formId").attr("action", "approveGuaranteeAttachment");
			$("#formId").submit();
	 		}
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
			}
		}
		return 'Yes';
	}
	function ChkMandatoryDateFields() {
		var date = $("#maturitydate").val();
		
		return date;
		}
	function ChkCustomerFields() {
		var entiredCIF = $("#counterpartyUniqueIdentifier").val();	 
		//var providerCIF = $("#providerUniqueIdentifier").val();	
		var tiCIF = $("#customerID").val();
		 
		if(entiredCIF != tiCIF){
			return 'No'
		}	
		/* if(providerCIF != tiCIF){
			return 'No'
		}	 */
		return 'Yes';
	}
	
	function validateGuaranteeType() {
		$('#validateSecurityCode').val(null);
		var collateralType = $("#guaranteeType").val();
		$('body').modal();
		$('body').removeClass('removePageLoad');
		$('input,a').removeClass('removePageLoad');
		$('input,a').addClass('addPageLoad');
		$('body').addClass('addPageLoad');
		if (collateralType != null || collateralType != undefined) {
			$("#formId").attr("action", "validateGuaranteeType");
			$("#formId").submit();
		}
	}
	
	
	function guaranteeSelection() {
		$("#formId").attr("action", "guaranteeSelection");
		$("#formId").submit();
	}
</script>
<style type="text/css">
li {
	list-style: none;
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
								Attach Guarantee Details</h5>
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
												name="Guarantee Type *" /><a href="#"
											onclick="guaranteeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeTypeCode"
												name="guaVO.guaranteeTypeCode"
												onblur="validateGuaranteeType()"
												cssClass="form-control text_box IsRequired" maxlength="32" readonly="true"/>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Number * (C+SUBTOTAL CODE+CIF)" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeNumber"
												name="guaVO.guaranteeNumber"
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
												name="Guarantee Description *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="guaranteeDescription"
												name="guaVO.guaranteeDescription" rows="7"
												cssClass="form-control text_box1 IsRequired"
												maxlength="1024" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Counterparty Unique Identifier *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="counterpartyUniqueIdentifier"
												name="guaVO.counterpartyUniqueIdentifier"
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
												name="Provider Unique Identifier *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="providerUniqueIdentifier"
												name="guaVO.providerUniqueIdentifier"
												cssClass="form-control text_box IsRequired" maxlength="32" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeNature" name="Amount Based"
												value="Amount Based" cssClass="form-control text_box"
												readonly="true" />
											<extracts:hidden id="guaranteeNature"
												name="guaVO.guaranteeNature" value="0" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="row page_content">
								<%-- <c:if test="${guaVO.validateGuaranteeNature == '0' }"> --%>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Amount *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeAmount"
												name="guaVO.guaranteeAmount"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30" />
										</div>
									</div>
								</div>
								<%-- </c:if>
								<c:if test="${ guaVO.validateGuaranteeNature == '1' }">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Exposure Percentage *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="exposurePercentage"
													name="guaVO.exposurePercentage"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30" />
											</div>
										</div>
									</div>
								</c:if>
								<c:if
									test="${(not empty guaVO.validateGuaranteeNature) && guaVO.validateGuaranteeNature == '2' }">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Exposure Percentage CAP *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="exposurePercentageCAP"
													name="guaVO.exposurePercentageCAP"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30" />
											</div>
										</div>
									</div>
								</c:if> --%>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Currency Code *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="currencyCode"
												name="guaVO.currencyCode"
												cssClass="form-control text_box IsRequired" maxlength="5" />
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
											<extracts:textfield id="issueDate" name="guaVO.issueDate"
												cssClass="datepicker form-control text_box IsRequired" />
										</div>
									</div>

								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Maturity date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="maturitydate"
												name="guaVO.maturitydate"
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
												name="Received Date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="receivedDate"
												name="guaVO.receivedDate"
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
											<extracts:textfield id="branchCode" name="guaVO.branchCode"
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
												id="natureOfLimit" name="guaVO.natureOfLimit"
												cssClass="form-control text_box" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Limit Purpose" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="narrative" name="guaVO.narrative"
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
											<extracts:textarea id="notesIDs" name="guaVO.notes" rows="7"
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
											<input type="button" name="attachGuarantee"
												id="attachGuarantee"
												onclick="return submitGuaranteeAttachment()" value="Submit" />
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
												name="Guarantee Type *" /><!-- <a href="#"
											onclick="guaranteeSelection()"><img
												src="images/Search-icon.png" alt="serach"
												style="WIDTH: 15px; height: 15px;" /></a> --></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeTypeCode"
												name="guaVO.guaranteeTypeCode"
												onblur="validateGuaranteeType()"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Number * (C+SUBTOTAL CODE+CIF)" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeNumber"
												name="guaVO.guaranteeNumber"
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
												name="Guarantee Description *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="guaranteeDescription"
												name="guaVO.guaranteeDescription" rows="7"
												cssClass="form-control text_box1 IsRequired"
												maxlength="1024" readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Counterparty Unique Identifier *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="counterpartyUniqueIdentifier"
												name="guaVO.counterpartyUniqueIdentifier"
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
												name="Provider Unique Identifier *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="providerUniqueIdentifier"
												name="guaVO.providerUniqueIdentifier"
												cssClass="form-control text_box IsRequired" maxlength="32"
												readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Nature *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeNature" name="Amount Based"
												value="Amount Based" cssClass="form-control text_box"
												readonly="true" />
											<extracts:hidden id="guaranteeNature"
												name="guaVO.guaranteeNature" value="0" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<%-- <c:if test="${guaVO.validateGuaranteeNature == '0' }"> --%>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Guarantee Amount *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="guaranteeAmount"
												name="guaVO.guaranteeAmount"
												onkeypress="return isNumberKey(this)"
												cssClass="form-control text_box IsRequired" maxlength="30"
												readonly="true" />
										</div>
									</div>
								</div>
								<%-- </c:if>
								<c:if test="${ guaVO.validateGuaranteeNature == '1' }">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Exposure Percentage *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="exposurePercentage"
													name="guaVO.exposurePercentage"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30"
													readonly="true" />
											</div>
										</div>
									</div>
								</c:if>
								<c:if
									test="${(not empty guaVO.validateGuaranteeNature) && guaVO.validateGuaranteeNature == '2' }">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"><extracts:text
													name="Exposure Percentage CAP *" /></label>
											<div class="col-md-6 input-group input-group-md">
												<extracts:textfield id="exposurePercentageCAP"
													name="guaVO.exposurePercentageCAP"
													onkeypress="return isNumberKey(this)"
													cssClass="form-control text_box IsRequired" maxlength="30"
													readonly="true" />
											</div>
										</div>
									</div>
								</c:if> --%>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Currency Code *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="currencyCode"
												name="guaVO.currencyCode"
												cssClass="form-control text_box IsRequired" maxlength="5"
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
											<extracts:textfield id="issueDate" name="guaVO.issueDate"
												cssClass="form-control text_box IsRequired"
												readonly="true" />
										</div>
									</div>

								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Maturity date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="maturitydate"
												name="guaVO.maturitydate"
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
												name="Received Date *" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textfield id="receivedDate"
												name="guaVO.receivedDate"
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
											<extracts:textfield id="branchCode" name="guaVO.branchCode"
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
												list="#{'0':'Revolving','1':'Non-Revolving'}" 
												id="natureOfLimit" name="guaVO.natureOfLimit"
												cssClass="form-control text_box" readonly="true" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"><extracts:text
												name="Limit Purpose" /></label>
										<div class="col-md-6 input-group input-group-md">
											<extracts:textarea id="narrative" name="guaVO.narrative"
												rows="7" cssClass="form-control text_box1 IsRequired"
												maxlength="256" readonly="true" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row page_content">
								<c:choose>
									<c:when
										test="${guaVO.makerCheckerAction eq 'Approval Pending' }">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-md-4 Control-label"
													style="font-weight: normal;"><extracts:text
														name="Reason For Rejection" /></label>
												<div class="col-md-6 input-group input-group-md">
													<extracts:textarea id="notesID" name="guaVO.notes" rows="7"
														cssClass="form-control text_box1" maxlength="256" />
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
																name="Reason For Rejection" /> </label>
														<div class="col-md-6 input-group input-group-md">
															<extracts:textarea id="notesID" name="guaVO.notes"
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
						<c:if test="${guaVO.makerCheckerAction eq 'Approval Pending' }">
							<div class="col-md-12">
								<div class="row page_content">
									<div class="col-md-6">
										<label class="col-md-4 Control-label"
											style="font-weight: normal;"></label>
										<div class="col-md-4 input-group input-group-md">
											<extracts:hidden id="makerChkActionID" value="Approved" />
											<input type="button" name="approveattachGuarantee"
												id="approveattachGuarantee"
												onclick="return ApproveGuaranteeAttachment()"
												value="Approve" />
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-4 Control-label"
												style="font-weight: normal;"></label>
											<div class="col-md-4 input-group input-group-md">
												<extracts:hidden id="makerChkActionID" value="Rejected" />
												<input type="button" name="rejectattachGuarantee"
													id="rejectattachGuarantee"
													onclick="return RejectGuaranteeAttachment()" value="Reject" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<%
							}
						%>
						<extracts:hidden name="guaVO.validateSecurityCode"
							id="validateSecurityCode"></extracts:hidden>
						<extracts:hidden id="category" name="guaVO.category" />
						<extracts:hidden id="categoryDescription"
							name="guaVO.categoryDescription" />
						<extracts:hidden id="action" name="custVO.action"
							cssClass="form-control text_box" />
						<extracts:hidden name="guaVO.validateGuaranteeNature"
							id="validateGuaranteeNature"></extracts:hidden>
						<extracts:hidden id="MakerChkAction"
							name="guaVO.makerCheckerAction" />
						<extracts:hidden id="userNames" name="guaVO.userName" />
						<extracts:hidden id="userRoles" name="guaVO.userRole" />
						<extracts:hidden id="menonic" name="guaVO.customerID" />
					</div>
				</div>
			</div>
		</div>
	</extracts:form>

</body>
</html>