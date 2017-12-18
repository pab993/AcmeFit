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
			<display:column property="expirationMoment" title="${expirationHeader}" />

			<spring:message code="diet.estimateLostWeight" var="lostHeader" />
			<display:column property="estimateLostWeight" title="${lostHeader}" />

			<spring:message code="diet.hints" var="hintsHeader" />
			<display:column property="hints" title="${hintsHeader}" />
			
				
				<display:column>
					<a href="diet/display.do?dietId=${row.id}">
						<spring:message code="display.diet" />
					</a>
				</display:column>
		

		</display:table>
		
	<security:authorize access="hasRole('NUTRITIONIST')">
		<input type="button" name="create" value="<spring:message code="diet.create"/>" onclick="javascript: window.location.replace('diet/createDiet.do')"/>
	</security:authorize>	
	
	<input type="button" name="cancel" value="<spring:message code="cancel"/>" onclick="javascript: window.location.replace('welcome/index.do')"/>

