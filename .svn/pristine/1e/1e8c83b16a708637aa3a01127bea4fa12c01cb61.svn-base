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
	<jstl:if test="${!owner}">
	
		<display:table name = "requestTrainings" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
			
			<spring:message code = "requestTraining.status" var = "statusHeader" />
			<display:column property = "status" title = "${statusHeader}" />
			
			<spring:message code = "requestTraining.nameActivity" var = "nameActivityHeader" />
			<display:column property = "personalTraining.nameActivity" title = "${nameActivityHeader}" />
	
			<spring:message code = "requestTraining.intensity" var = "intensityHeader" />
			<display:column property = "personalTraining.intensity" title = "${intensityHeader}" />
			
			<spring:message code="requestTraining.cost" var="costHeader" />
			<display:column property="personalTraining.cost" title="${costHeader}" />
	
			<display:column>
				<a href="requestTraining/edit.do?requestTrainingId=${row.id}"> <spring:message code="requestTraining.edit" /></a>
			</display:column>
	
			</display:table>
		</jstl:if>	
		
	<!-- Listing table -->
	<jstl:if test="${owner}">
	
		<display:table name = "requestTrainings" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
			
			<spring:message code = "requestTraining.status" var = "statusHeader" />
			<display:column property = "status" title = "${statusHeader}" />
			
			<spring:message code = "requestTraining.nameActivity" var = "nameActivityHeader" />
			<display:column property = "personalTraining.nameActivity" title = "${nameActivityHeader}" />
	
			<spring:message code = "requestTraining.intensity" var = "intensityHeader" />
			<display:column property = "personalTraining.intensity" title = "${intensityHeader}" />
			
			<spring:message code="requestTraining.cost" var="costHeader" />
			<display:column property="personalTraining.cost" title="${costHeader}" />
	
		<jstl:if test="${owner2}">		
		<display:column>		
				<a href="requestTraining/cancelled.do?requestTrainingId=${row.id}"
				onclick="return confirm('<spring:message code = "requestTraining.confirm.cancelled"/>')">
					<spring:message code="requestTraining.cancelled" />
				</a>	
		</display:column>
		</jstl:if>
		
	
			</display:table>
		</jstl:if>			
	<acme:cancel code="requestTraining.cancel" url ="welcome/index.do"/>