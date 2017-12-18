<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="invoice/administrator/edit.do" modelAttribute="invoiceForm">
	
	<form:hidden path="customer"/>
	<form:hidden path="dietsPrice"/>
	<form:hidden path="personalTrainingsPrice"/>
	<form:hidden path="montlyPayment"/>
	<form:hidden path="discount"/>
	
	<h2><spring:message code="invoiceform.monthlyPayment" />: <jstl:out value=" ${ invoiceForm.montlyPayment}" /> </h2>
	
	<fieldset > 
	
		
	
	<legend><b> <acme:textbox code="invoice.diets" readonly="true" path="numberDiets"/> </b></legend>
	
		<h3><spring:message code="invoice.priceTotal" />: <jstl:out value=" ${ invoiceForm.dietsPrice}" /></h3> 
	
	</fieldset>
	
	<fieldset > 
	
	<legend><b> <acme:textbox code="invoice.personalTrainings" readonly="true" path="numberPersonalTraingin"/> </b></legend>
		<h3><spring:message code="invoice.priceTotal" />: <jstl:out value=" ${ invoiceForm.personalTrainingsPrice}" /></h3> 
	
	</fieldset>
	
	<b><spring:message code="invoiceForm.discount" />: <jstl:out value=" ${ invoiceForm.discount}"/></b>
	
	<br/><br/>

	
	
	<legend> <b><acme:textbox readonly="true" code="invoice.totalPrice" path="invoice.totalPrice"/></b></legend>
	
	
	
	<acme:submit id="submitButton" name="submit" code="submit" />
	<acme:cancel url="welcome/index.do" code="cancel"/>
	
	</form:form>
	
	
	
	

