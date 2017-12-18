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


<!-- Display diet -->

<h3>
	<spring:message code="diet.expirationMoment" />
	:
	<jstl:out value="${diet.getExpirationMoment()}" />
</h3>


<h3>
	<spring:message code="diet.estimateLostWeight" />
	:
	<jstl:out value="${diet.getEstimateLostWeight()}" />
</h3>


<h3>
	<spring:message code="diet.hints" />
	:
	<jstl:out value="${diet.getHints()}" />
</h3>

<jstl:choose>
	<jstl:when test="${owner}">
		<security:authorize access="hasRole('CUSTOMER')">

			<h3>
				<spring:message code="diet.meals" />
			</h3>
			<jstl:forEach items="${meals}" var="meal">
				<table border="2px" style="width: 300px">
					<!-- Lo cambiaremos por CSS -->
					<tr>

						<td style='background-color: #D3D3D3'><h5>
								<spring:message code="diet.name" />
							</h5></td>
						<td><h5>
								<jstl:out value="${meal.name}" />
							</h5></td>
					</tr>
					<tr>
						<td style='background-color: #D3D3D3'><h5>
								<spring:message code="diet.kcal" />
							</h5></td>
						<td><h5>
								<jstl:out value="${meal.kcal}" />
							</h5></td>
					</tr>
					<tr>
						<td style='background-color: #D3D3D3'><h5>
								<spring:message code="diet.nutritionalValue" />
							</h5></td>
						<td><h5>
								<jstl:out value="${meal.nutritionalValue}" />
							</h5></td>
					</tr>
					<tr>
						<td style='background-color: #D3D3D3'><h5>
								<spring:message code="diet.schedule" />
							</h5></td>
						<td><h5>
								<jstl:out value="${meal.schedule}" />
							</h5></td>
					</tr>
				</table>
			</jstl:forEach>
		</security:authorize>
	</jstl:when>
</jstl:choose>

<security:authorize access="hasRole('NUTRITIONIST')">

	<h3>
		<spring:message code="diet.meals" />
	</h3>
	<jstl:forEach items="${meals}" var="meal">
		<table border="2px" style="width: 300px">
			<!-- Lo cambiaremos por CSS -->
			<tr>

				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="diet.name" />
					</h5></td>
				<td><h5>
						<jstl:out value="${meal.name}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="diet.kcal" />
					</h5></td>
				<td><h5>
						<jstl:out value="${meal.kcal}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="diet.nutritionalValue" />
					</h5></td>
				<td><h5>
						<jstl:out value="${meal.nutritionalValue}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5>
						<spring:message
								code="diet.schedule" />
					</h5></td>
				<td><h5>
						<jstl:out value="${meal.schedule}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5><a
							href="meal/display.do?mealId=${meal.getId() }">
						<spring:message code="meal.display" /></a>
					</h5></td>
			</tr>
		</table>
	</jstl:forEach>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${diet.getId() }">
			<spring:message code="postAssessment" />
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
