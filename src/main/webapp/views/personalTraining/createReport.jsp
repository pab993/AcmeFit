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

<form:form action="personalTraining/createReport.do" modelAttribute="report">
<form:hidden path="id" />
<form:hidden path="version" />

	<fieldset>

		<legend>
			<b> <spring:message code="report.create" />
			</b>
		</legend>

		<acme:textbox code="report.timeSpent" path="timeSpent" />
		<br />
		<acme:textbox code="report.kcalSpent" path="kcalSpent" />
		<br />
		<acme:textbox code="report.mediumPulse" path="mediumPulse" />
		<br />
		<acme:textbox code="report.exercisedMuscles" path="exercisedMuscles" />
		<br />
		<acme:textbox code="report.score" path="score" />
		<br />
		<acme:textbox code="report.hints" path="hints" />
		<br />

	</fieldset>

	<acme:submit name="submitReport" code="submit" />
	<acme:cancel url="welcome/index.do" code="cancel"/>
	

</form:form>