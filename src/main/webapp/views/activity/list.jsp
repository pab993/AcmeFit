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
	
	<display:table name = "activities" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
		
		<spring:message code = "activity.nameRoom" var = "nameRoomHeader" />
		<display:column property = "nameRoom" title = "${nameRoomHeader}" />
		
		<spring:message code = "activity.nameActivity" var = "nameActivityHeader" />
		<display:column property = "nameActivity" title = "${nameActivityHeader}" />
	
		<spring:message code="activity.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" />
	
		<spring:message code="activity.duration" var="durationHeader" />
		<display:column property="duration" title="${durationHeader}" />
		
		<spring:message code="activity.intensity" var="intensityHeader" />
		<display:column property="intensity" title="${intensityHeader}"/>
		
		<spring:message code="activity.schedule" var="scheduleHeader" />
		<display:column property="schedule" title="${scheduleHeader}" format="{0,date,dd/MM/yyyy HH:mm:ss}"/>
		
		<spring:message code="activity.currentAttendance" var="currentAttendanceHeader" />
		<display:column property="currentAttendance" title="${currentAttendanceHeader}"/>
	
		<spring:message code="activity.maximumAttendance" var="maximumAttendanceHeader" />
		<display:column property="maximumAttendance" title="${maximumAttendanceHeader}"/>
		
		<display:column>
				<a href="trainer/display.do?trainerId=${row.trainer.id}">
					<spring:message code="activity.trainer.display" />
				</a>
		</display:column>
		
		<display:column>
				<a href="activity/display.do?activityId=${row.id}">
					<spring:message code="activity.display" />
				</a>
		</display:column>
	</display:table>
	
	<acme:cancel code="activity.cancel" url ="welcome/index.do"/>