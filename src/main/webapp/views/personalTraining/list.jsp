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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing table -->
<display:table name="personalTrainings" id="row"
	requestURI="${requestURI}" pagesize="5" class="table table-hover">

	<spring:message code="activity.nameRoom" var="nameRoomHeader" />
	<display:column property="nameRoom" title="${nameRoomHeader}" />

	<spring:message code="activity.nameActivity" var="nameActivityHeader" />
	<display:column property="nameActivity" title="${nameActivityHeader}" />

	<spring:message code="activity.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="activity.duration" var="durationHeader" />
	<display:column property="duration" title="${durationHeader}" />

	<spring:message code="activity.intensity" var="intensityHeader" />
	<display:column property="intensity" title="${intensityHeader}" />

	<spring:message code="activity.schedule" var="scheduleHeader" />
	<display:column property="schedule" title="${scheduleHeader}"
		format="{0,date,dd/MM/yyyy HH:mm:ss}" />

	<spring:message code="personalTraining.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" />

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a
				href="personalTraining/application.do?personalTrainingId=${row.id}">
				<spring:message code="personalTraining.application" />
			</a>
		</display:column>
	</security:authorize>
	<jstl:choose>
	<jstl:when test="${row.getReport().getId() == null}">
		<security:authorize access="hasRole('TRAINER')">
			<display:column>
				<a
					href="personalTraining/createReport.do?personalTrainingId=${row.id}">
					<spring:message code="personalTraining.createReport" />
				</a>
			</display:column>
		</security:authorize>
	</jstl:when>
	<jstl:otherwise>
		<display:column>
			<a
				href="personalTraining/displayReport.do?personalTrainingId=${row.id}">
				<spring:message code="personalTraining.displayReport" />
			</a>
		</display:column>
	</jstl:otherwise>
	</jstl:choose>
</display:table>

<input type="button" name="cancel"
	value="<spring:message code="cancel"/>"
	onclick="javascript: window.location.replace('trainer/list.do')" />
