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

<!-- Display personalTraining -->

<h3>
	<spring:message code="personalTraining.nameRoom" />: <jstl:out value="${personalTraining.getNameRoom()}" />
</h3>

<h3>
	<spring:message code="personalTraining.nameActivity" />: <jstl:out value="${personalTraining.getNameActivity()}" />
</h3>

<h3>
	<spring:message code="personalTraining.description" />: <jstl:out value="${personalTraining.getDescription()}" />
</h3>

<h3>
	<spring:message code="personalTraining.duration" />: <jstl:out value="${personalTraining.getDuration()}" />
</h3>

<h3>
	<spring:message code="personalTraining.intensity" />: <jstl:out value="${personalTraining.getIntensity()}" />
</h3>

<h3>
	<spring:message code="personalTraining.schedule" />: <jstl:out value="${personalTraining.getSchedule()}" />
</h3>

<h3>
	<spring:message code="personalTraining.currentAttendance" />: <jstl:out value="${personalTraining.getCurrentAttendance()}" />
</h3>

<h3>
	<spring:message code="personalTraining.maximumAttendance" />: <jstl:out value="${personalTraining.getMaximumAttendance()}" />
</h3>

<h3>
	<spring:message code="personalTraining.cost" />: <jstl:out value="${personalTraining.getCost()}" />
</h3>


<security:authorize access="isAuthenticated()">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${personalTraining.getId() }"> <spring:message
				code="postAssessment" />
		</a>
	</h3>
	
	<jstl:if test="${personalTraining.getCurrentAttendance() < personalTraining.getMaximumAttendance() }">
	
	
	<h3>
	
		<a href="personalTraining/customer/signUp.do?personalTrainingId=${personalTraining.getId()}" id="buttonStyle" ><spring:message code="personalTraining.signUp" /></a>
											
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