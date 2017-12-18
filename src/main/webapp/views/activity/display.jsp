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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<LINK REL="stylesheet" TYPE="text/css" HREF="style/button.css">

<!-- Display activity -->

<h3>
	<spring:message code="activity.nameRoom" />: <jstl:out value="${activity.getNameRoom()}" />
</h3>

<h3>
	<spring:message code="activity.nameActivity" />: <jstl:out value="${activity.getNameActivity()}" />
</h3>

<h3>
	<spring:message code="activity.description" />: <jstl:out value="${activity.getDescription()}" />
</h3>

<h3>
	<spring:message code="activity.duration" />: <jstl:out value="${activity.getDuration()}" />
</h3>

<h3>
	<spring:message code="activity.intensity" />: <jstl:out value="${activity.getIntensity()}" />
</h3>

<h3>
	<spring:message code="activity.schedule" />: <jstl:out value="${activity.getSchedule()}" />
</h3>

<h3>
	<spring:message code="activity.currentAttendance" />: <jstl:out value="${activity.getCurrentAttendance()}" />
</h3>

<h3>
	<spring:message code="activity.maximumAttendance" />: <jstl:out value="${activity.getMaximumAttendance()}" />
</h3>


<security:authorize access="isAuthenticated()">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${activity.getId() }"> <spring:message
				code="postAssessment" />
		</a>
	</h3>
	
	<jstl:if test="${activity.getCurrentAttendance() < activity.getMaximumAttendance() }">
	
	
	<h3>
	
		<a href="activity/customer/signUp.do?activityId=${activity.getId()}" id="buttonStyle" ><spring:message code="activity.signUp" /></a>
											
	</h3>
	</jstl:if>
	
</security:authorize>

<display:table name="assessments" id="row" requestURI="${requestURI}"
	pagesize="5" class = "table table-hover" >

	<spring:message code="assessment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="assessment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="assessment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" />

	<spring:message code="assessment.actorName" var="actorNameHeader" />
	<display:column property="actor.name" title="${actorNameHeader}" />
	
	<display:column>
		<a href="actor/display.do?actorId=${row.actor.id}"> <spring:message
				code="display.actor" />
		</a>
	</display:column>
</display:table>