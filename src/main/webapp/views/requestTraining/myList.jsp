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

		
	<!-- Listing table -->
	
	<display:table name = "requestTrainings" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
		
		<spring:message code="customer.name.application" var="customerHeader" />
		<display:column property="customer.name" title="${customerHeader}" />
		
		<spring:message code="personalTraining.nameActivity" var="activityHeader" />
		<display:column property="personalTraining.nameActivity" title="${activityHeader}"/>
		
		<%-- <a href="personalTraining/display.do?dietId=${row.diet.id }"><spring:message code="nutritionist.diet"/></a> --%>
		
		<spring:message code = "requestTraining.status" var = "statusHeader" />
		<display:column property = "status" title = "${statusHeader}" />
		
		<display:column>
			<a href="requestTraining/edit.do?requestTrainingId=${row.id}"> <spring:message code="requestTraining.edit" /></a>
		</display:column>

	</display:table>
	
	<input type="button" name="cancel" value="<spring:message code="requestTraining.cancel"/>" onclick="javascript: window.location.replace('welcome/index.do')"/>