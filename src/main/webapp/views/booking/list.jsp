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
	
	<display:table name = "bookings" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
		
		<spring:message code = "service.name" var = "serviceNameHeader" />
		<display:column property = "service.name" title = "${serviceNameHeader}" />
				
		<spring:message code = "booking.moment" var = "momentHeader" />
		<display:column property = "moment" title = "${momentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
		
		<spring:message code = "booking.status" var = "statusHeader" />
		<display:column property = "status" title = "${statusHeader}" />
	
		<spring:message code="booking.withPenalty" var="withPenaltyHeader" />
		<display:column title="${withPenaltyHeader}">
			<jstl:if test="${row.withPenalty}">
				<spring:message code="booking.yes"/>
			</jstl:if>
			<jstl:if test="${!row.withPenalty}">
				<spring:message code="booking.no"/>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${row.status == 'CONFIRMED' }">
				<a href="booking/customer/cancel.do?bookingId=${row.id}" onclick="return confirm('<spring:message code="booking.cancelBooking.confirm" />')">
					<spring:message code="booking.cancelBooking"  />
				</a>
			</jstl:if>
		</display:column>
	</display:table>
	
	<div>
		<spring:message code="booking.alertCancel1" /> <jstl:out value="${ configurationSystem.cancelTimeLimit }" /> <spring:message code="booking.alertCancel2" /> <jstl:out value="${ configurationSystem.penaltyAmount }" /> &euro;
	</div>
	
	<acme:cancel code="booking.back" url ="welcome/index.do"/>