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

<form:form action="creditCard/edit.do" modelAttribute="creditCard">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>

	<div >
	
		<fieldset id="credit"> 
	
		
			<legend> <spring:message code="datos.creditCard" /> </legend>
			
			
			<div> 
			
				<form:label path="holderName">
					<spring:message code="consumer.creditCard.holderName" />:
				</form:label>
				<form:input path="holderName" />
				<form:errors cssClass="error" path="holderName" />
				
			</div>
			<br />
			
			<div>
			
				<form:label path="brandName">
					<spring:message code="consumer.creditCard.brandName" />:
				</form:label>
				<form:input path="brandName" />
				<form:errors cssClass="error" path="brandName" />
			
			</div>
			<br />
			
			<div>
			
				<form:label path="number">
					<spring:message code="consumer.creditCard.number" />:
				</form:label>
				<form:input path="number" />
				<form:errors cssClass="error" path="number" />
			
			</div>
			<br />
			
			<div>
			
				<form:label path="expirationMonth">
					<spring:message code="consumer.creditCard.expirationMonth" />:
				</form:label>
				<form:input path="expirationMonth" />
				<form:errors cssClass="error" path="expirationMonth" />
			
			</div>
			<br />
			
			<div>
			
				<form:label path="expirationYear">
					<spring:message code="consumer.creditCard.expirationYear" />:
				</form:label>
				<form:input path="expirationYear" />
				<form:errors cssClass="error" path="expirationYear" />
			
			</div>
			<br />
			
			<div> 
			
				<form:label path="CVV">
					<spring:message code="consumer.creditCard.cvv" />:
				</form:label>
				<form:input path="CVV" />
				<form:errors cssClass="error" path="CVV" />
		
			</div>
			<br />
		
		</fieldset>
	
	</div>

		<div id="botones">

		<acme:submit code="creditCard.submit" name="save" />
		<jstl:if test="${creditCard.id != 0}">
			<acme:delete name="delete" code="creditCard.delete" msg="confirm.creditCard.delete"/>
		</jstl:if>
		<input type="button" name="cancel" value="<spring:message code="creditCard.cancel"/>" onclick="javascript: window.location.replace('welcome/index.do')"/>

	</div>

</form:form>