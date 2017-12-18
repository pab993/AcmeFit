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


<form:form action="requestDiet/edit.do" modelAttribute="requestDietForm">

	<form:hidden path="requestDietId" />
	<form:hidden path="diet"/>
	
	<form:select path="status">
		<spring:message code="requestDiet.status.pending" var="pendingHeader"/><form:option value="PENDING" label="${pendingHeader}"/>	
		<spring:message code="requestDiet.status.accepted" var="acceptedHeader"/><form:option value="ACCEPTED" label="${acceptedHeader}" />	
		<spring:message code="requestDiet.status.denied" var="deniedHeader"/><form:option value="DENIED" label="${deniedHeader}" />			
	</form:select>
	<br>
	<br>

	<input type="submit" name="save" value="<spring:message code="application.save" />" />&nbsp;


	<input type="submit" name="delete" value="<spring:message code="application.delete" />" onclick="return confirm('<spring:message code="application.confirm.delete" />')" />&nbsp;

	<input type="button" name="cancel" value="<spring:message code="application.cancel"/>" onclick="javascript: window.location.replace('nutritionist/diets.do?nutritionistId=${nutritionistId}')"/>

</form:form>