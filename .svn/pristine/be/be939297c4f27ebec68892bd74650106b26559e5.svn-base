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

<form:form action="activity/create.do" modelAttribute="activity">
<form:hidden path="id" />
<form:hidden path="version" />
<form:hidden path="currentAttendance" />
<form:hidden path="maximumAttendance" />
	<fieldset>

		<legend>
			<b> <spring:message code="activity.create" />
			</b>
		</legend>

		<acme:textbox code="activity.nameRoom" path="nameRoom" />
		<br />
		<acme:textbox code="activity.nameActivity" path="nameActivity" />
		<br />
		<acme:textbox code="activity.description" path="description" />
		<br />
		<acme:textbox code="activity.duration" path="duration" />
		<br />
		<acme:textbox code="activity.intensity" path="intensity" />
		<br />
		<acme:textbox code="activity.schedule" path="schedule" />
		<br />
		<acme:textbox code="activity.maximumAttendance" path="maximumAttendance" />
		<br />

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit" msg="confirm.activity.create"/>
	<acme:cancel url="welcome/index.do" code="cancel"/>
	

</form:form>