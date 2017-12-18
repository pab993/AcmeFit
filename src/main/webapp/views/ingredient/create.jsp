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

<form:form action="ingredient/create.do" modelAttribute="ingredient">
<form:hidden path="id" />
<form:hidden path="version" />
	<fieldset>

		<legend>
			<b> <spring:message code="ingredient.create" />
			</b>
		</legend>

		<acme:textbox code="ingredient.name" path="name" />
		<br />
		<acme:textbox code="ingredient.multiplicity" path="multiplicity" />
		<br />
		<acme:textbox code="ingredient.unit" path="unit" />
		
		

	</fieldset>

	<acme:submit id="submitButton" name="submit" code="submit" msg="confirm.createIngredient" />
	<acme:cancel url="welcome/index.do" code="cancel"/>
	

</form:form>