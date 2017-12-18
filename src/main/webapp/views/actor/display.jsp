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


<!-- Display actor -->

<h3>
	<spring:message code="actor.picture" />: 
	<br>
	<img height="165" width="165" src="${actor.getPicture()}" />
</h3>


<h3>
	<spring:message code="actor.name" />: <jstl:out value="${actor.getName()}" />
</h3>


<h3>
	<spring:message code="actor.surname" />: <jstl:out value="${actor.getSurname()}" />
</h3>


<h3>
	<spring:message code="actor.email" />: <jstl:out value="${actor.getEmail()}" />
</h3>

<h3>
	<spring:message code="actor.phone" />: <jstl:out value="${actor.getPhone()}" />
</h3>

<h3>
	<spring:message code="actor.address" />: <jstl:out value="${actor.getAddress()}" />
</h3>


<security:authorize access="isAuthenticated()">
	<jstl:if test="${actor.id != principal.id}">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${actor.getId() }"> <spring:message
				code="postAssessment" />
		</a>
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

<jstl:if test="${actor.id == principal.id}">
			<a href="actor/edit.do"><spring:message code="actor.edit" /></a>
</jstl:if>
