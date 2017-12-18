<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="security/register.do" modelAttribute="customerForm">

	<fieldset > 
	
	<legend><b> <spring:message code="customer.accountData" /> </b></legend>
	
		<acme:textbox code="customer.username" path="username"/>
		<br/>
	
		<acme:password code="customer.password" path="password"/>
		<br/>
	
		<acme:password code="customer.repeatPassword" path="repeatPassword"/>
	
	</fieldset>
	
	<fieldset > 
	
	<legend><b> <spring:message code="customer.personalData" /> </b></legend>
		
		<acme:textbox code="customer.name" path="name"/>
		<br />
		<acme:textbox code="customer.surname" path="surname"/>
		<br />
		<acme:textbox code="customer.phone" path="phone"/>
		<br />
		<acme:textbox code="customer.email" path="email"/>
		<br />
		<acme:textbox code="customer.address" path="address"/>
		<br />
		<acme:textbox code="customer.picture" path="picture"/>
		<br />
		
	</fieldset>
	
	
			
		<fieldset > 
			<legend><jstl:out value=">"></jstl:out>   <form:checkbox onclick="functionCheck2();" id="check2Id" path="check2"/> <b><spring:message code="customer.creditCard" /></b></legend>
			
			
			<div id="creditCardForm">
			
				<acme:textbox code="customer.creditCard.holderName" path="holderName"/>
				<br/>
				<acme:textbox code="customer.creditCard.brandName" path="brandName"/>
				<br/>
				<acme:textbox code="customer.creditCard.number" path="number"/>
				<br/>
				<acme:textbox code="customer.creditCard.expirationMonth" path="expirationMonth"/>
				<br/>
				<acme:textbox code="customer.creditCard.expirationYear" path="expirationYear"/>
				<br/>
				<acme:textbox code="customer.creditCard.cvv" path="CVV"/>
			</div>	
			
		</fieldset>
	
	<!-- Terms&Conditions -->
	
	<form:checkbox id="check1Id" onclick="functionCheck1();" path="check1"/>
	<spring:message code="customer.accept" />
	<a href="termsAndConditions/termsAndConditions.do">
	<spring:message code="customer.lopd"/></a>
		
	

	<acme:submit id="submitButton" name="submit" code="customer.submit"/>

</form:form>

<script type="text/javascript">
//document.getElementById("creditCardForm").style.visibility = "hidden";
document.getElementById("submitButton").disabled = true;
document.getElementById("check1Id").checked = false;

function functionCheck1() {
	
	var x = document.getElementById("check1Id").checked;
	
	if(x == true){
		document.getElementById("submitButton").disabled = false;
	}
	else{
		document.getElementById("submitButton").disabled = true;
	}
}

function functionCheck2() {
	
	var x = document.getElementById("check2Id").checked;
	
	if(x == true){
		document.getElementById("creditCardForm").style.visibility = "visible";
	}
	else{
		document.getElementById("creditCardForm").style.visibility = "hidden";
	}
}


</script>