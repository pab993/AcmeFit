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


<form:form action="meal/edit.do" modelAttribute="meal">

	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="diet"/>
	<form:hidden path="ingredients"/>
	
	<fieldset>

		<legend>
			<b> <spring:message code="editMeal" />
			</b>
		</legend>

		<acme:textbox code="meal.name" path="name" />
		<br />
		<acme:textbox code="meal.kcal" path="kcal" />
		<br />
		<acme:textbox code="meal.nutritionalValue" path="nutritionalValue" />
		<br />
		<acme:textbox code="meal.schedule" path="schedule" />
		<br />
		

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit"/>
	<acme:cancel url="welcome/index.do" code="cancel"/>

</form:form>