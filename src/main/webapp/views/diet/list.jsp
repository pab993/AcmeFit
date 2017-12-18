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
		<display:table name="diets" id="row" requestURI="${requestURI}"
			pagesize="5" class = "table table-hover" >

			<spring:message code="diet.expirationMoment" var="expirationHeader" />
			<display:column property="expirationMoment" title="${expirationHeader}" format="{0,date,dd/MM/yyyy}"/>

			<spring:message code="diet.estimateLostWeight" var="lostHeader" />
			<display:column property="estimateLostWeight" title="${lostHeader}" />

			<spring:message code="diet.hints" var="hintsHeader" />
			<display:column property="hints" title="${hintsHeader}" />
			
			<jstl:if test="${dietsF.contains(row)}">
				<spring:message code="diet.meals" var="mealsHeader" />
				<display:column title="${mealsHeader}" >
					<a href="diet/meals.do?dietId=${row.id}"> <spring:message code="diet.meals" />
					</a>
				</display:column>
<			</jstl:if>
			
			<security:authorize access="hasRole('CUSTOMER')">
				<display:column>
					<a href="diet/application.do?dietId=${row.id}"> <spring:message code="diet.application" /></a>
				</display:column>
				
			</security:authorize>
				<display:column>
					<a href="diet/display.do?dietId=${row.id}">
						<spring:message code="display.diet" />
					</a>
				</display:column>
		

		</display:table>
	
<input type="button" name="cancel" value="<spring:message code="cancel"/>" onclick="javascript: window.location.replace('nutritionist/list.do')"/>

