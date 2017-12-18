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

<form:form action="personalTraining/createPT.do" modelAttribute="personalTraining">
<form:hidden path="id" />
<form:hidden path="version" />
<form:hidden path="trainer" />

	<fieldset>

		<legend>
			<b> <spring:message code="personalTraining.create" />
			</b>
		</legend>

		<acme:textbox code="personalTraining.nameRoom" path="nameRoom" />
		<br />
		<acme:textbox code="personalTraining.nameActivity" path="nameActivity" />
		<br />
		<acme:textbox code="personalTraining.description" path="description" />
		<br />
		<acme:textbox code="personalTraining.duration" path="duration" />
		<br />
		<acme:textbox code="personalTraining.intensity" path="intensity" />
		<br />
		<acme:textbox code="personalTraining.schedule" path="schedule" />
		<br />
		<acme:textbox code="personalTraining.cost" path="cost" />
		<br />

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit" />
	<acme:cancel url="welcome/index.do" code="cancel"/>
	

</form:form>