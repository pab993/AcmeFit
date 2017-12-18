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


<!-- Display trainer -->

<h3>
	<spring:message code="trainer.picture" />: <%-- <jstl:out value="${trainer.getPicture()}" /> --%>
	<br>
	<img height="165" width="165" src="${trainer.getPicture()}" />
</h3>

<h3>
	<spring:message code="trainer.name" />: <jstl:out value="${trainer.getName()}" />
</h3>


<h3>
	<spring:message code="trainer.surname" />: <jstl:out value="${trainer.getSurname()}" />
</h3>


<h3>
	<spring:message code="trainer.email" />: <jstl:out value="${trainer.getEmail()}" />
</h3>

<h3>
	<spring:message code="trainer.phone" />: <jstl:out value="${trainer.getPhone()}" />
</h3>

<h3>
	<spring:message code="trainer.address" />: <jstl:out value="${trainer.getAddress()}" />
</h3>

<security:authorize access="isAuthenticated()">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${trainer.getId() }"> <spring:message
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
