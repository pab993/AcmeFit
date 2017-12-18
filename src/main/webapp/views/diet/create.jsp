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

<form:form action="diet/createDiet.do" modelAttribute="diet">
<form:hidden path="id" />
<form:hidden path="version" />
<form:hidden path="assessments" />
<form:hidden path="rewardsPoint" />
	<fieldset>

		<legend>
			<b> <spring:message code="diet.create" />
			</b>
		</legend>

		<acme:textbox code="diet.expirationMoment" path="expirationMoment" />
		<br />
		<acme:textbox code="diet.estimateLostWeight" path="estimateLostWeight" />
		<br />
		<acme:textbox code="diet.hints" path="hints" />
		<br />
		<acme:textbox code="diet.cost" path="cost" />
		<br />
		

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit" msg="confirm.createDiet" />
	<acme:cancel url="welcome/index.do" code="cancel"/>
	

</form:form>