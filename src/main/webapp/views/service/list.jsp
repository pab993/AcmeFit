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
	
	<display:table name = "services" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
		
		<spring:message code = "service.name" var = "serviceNameHeader" />
		<display:column property = "name" title = "${serviceNameHeader}" />
				
		<spring:message code = "service.openingTime" var = "openingTimeHeader" />
		<display:column property = "openingTime" title = "${openingTimeHeader}" format="{0,date,HH:mm}"/>
		
		<spring:message code = "service.closingTime" var = "closingTimeHeader" />
		<display:column property = "closingTime" title = "${closingTimeHeader}" format="{0,date,HH:mm}"/>
		
		<spring:message code = "service.duration" var = "durationHeader" />
		<display:column property = "duration" title = "${durationHeader}" />
		
		<spring:message code = "service.cost" var = "costHeader" />
		<display:column property = "cost" title = "${costHeader}" />
	
		<spring:message code="service.maximumCapacity" var="maximumCapacityHeader" />
		<display:column property="maximumCapacity" title="${maximumCapacityHeader}" />
		
		<display:column>
			<a href="service/display.do?serviceId=${row.id}">
				<spring:message code="service.display"/>
			</a>
		</display:column>
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="service/administrator/edit.do?serviceId=${row.id}">
				<spring:message code="service.edit"/>
			</a>
		</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="booking/customer/book.do?serviceId=${row.id}">
				<spring:message code="booking.book"/>
			</a>
		</display:column>
		</security:authorize>
	</display:table>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<div>
		<a href="service/administrator/create.do"><spring:message code="service.create"/></a>
	</div>
	</security:authorize>
	
	<acme:cancel code="service.back" url ="welcome/index.do"/>