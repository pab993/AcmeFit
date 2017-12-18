<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="actor/edit.do" modelAttribute="actor">
<form:hidden path="id" />
<form:hidden path="version" />
	<fieldset>

		<legend>
			<b> <spring:message code="actor.personalData" />
			</b>
		</legend>

		<acme:textbox code="actor.name" path="name" />
		<br />
		<acme:textbox code="actor.surname" path="surname" />
		<br />
		<acme:textbox code="actor.phone" path="phone" />
		<br />
		<acme:textbox code="actor.email" path="email" />
		<br />
		<acme:textbox code="actor.address" path="address" />
		<br />
		<acme:textbox code="actor.picture" path="picture" />
		<br />

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit" />
	<acme:cancel url="welcome/index.do" code="cancel"/>

</form:form>