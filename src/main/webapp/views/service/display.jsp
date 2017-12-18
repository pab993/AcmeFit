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


<!-- Display service -->

<h3>
	<spring:message code="service.name" />: <jstl:out value="${service.name}" />
</h3>


<h3>
	<spring:message code="service.openingTime" />: <jstl:out value="${service.openingTime}" />
</h3>


<h3>
	<spring:message code="service.closingTime" />: <jstl:out value="${service.closingTime}" />
</h3>


<h3>
	<spring:message code="service.duration" />: <jstl:out value="${service.duration}" />

</h3>

<h3>
	<spring:message code="service.cost" />: <jstl:out value="${service.cost}" />
</h3>


<h3>
	<spring:message code="service.maximumCapacity" />: <jstl:out value="${service.maximumCapacity}" />
</h3>


<security:authorize access="isAuthenticated()">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${service.getId() }"> <spring:message
				code="postAssessment" />
		</a>
	</h3>
</security:authorize>

<display:table name="assessments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

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