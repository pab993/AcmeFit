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
	
	<display:table name = "competitions" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
		
		<spring:message code = "competition.prize" var = "prizeHeader" />
		<display:column property = "prize" title = "${prizeHeader}" />
		
		<spring:message code = "competition.description" var = "descriptionHeader" />
		<display:column property = "description" title = "${descriptionHeader}" />
	
		<spring:message code="competition.rules" var="rulesHeader" />
		<display:column property="rules" title="${rulesHeader}" />
	
		<spring:message code="competition.startDate" var="startDateHeader" />
		<display:column property="startDate" title="${startDateHeader}" format="{0,date,dd/MM/yyyy}"/>
		
		<spring:message code="competition.closingDate" var="closingDateHeader" />
		<display:column property="closingDate" title="${closingDateHeader}" format="{0,date,dd/MM/yyyy}"/>

		<security:authorize access="hasRole('CUSTOMER')">
			<display:column>
				<a href="competition/customer/register.do?competitionId=${row.id }"><spring:message code="competition.customer.register"/></a>
			</display:column>
		</security:authorize>

	</display:table>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<div>
		<a href="competition/administrator/create.do"><spring:message code="competition.create"/></a>
	</div>
	</security:authorize>
	
	
	
	<acme:cancel code="cancel" url ="welcome/index.do"/>