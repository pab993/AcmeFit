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
<jstl:if test="${!owner}">
			<a href="actor/edit.do"><spring:message code="actor.edit" /></a>
		<display:table name="requestsDiets" id="row" requestURI="${requestURI}"
			pagesize="5" class = "table table-hover" >

			<spring:message code="customer.name.application" var="customerHeader" />
			<display:column property="customer.name" title="${customerHeader}" />

			<spring:message code="nutritionist.diet" var="dietHeader" />
			<display:column title="${dietHeader}" ><a href="diet/display.do?dietId=${row.diet.id }"><spring:message code="nutritionist.diet"/></a></display:column>

			<spring:message code="request.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}" />
			
			<display:column>
				<a href="requestDiet/edit.do?requestDietId=${row.id}"> <spring:message code="requestDiet.edit" /></a>
			</display:column>

		</display:table>
</jstl:if>
	
<!-- Listing table -->	

<jstl:if test="${owner}">		
	<display:table name = "requestDiets" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "table table-hover" >
				
		<spring:message code = "requestDiet.status" var = "statusHeader" />	
		<display:column property = "status" title = "${statusHeader}" />	
		
		<spring:message code = "requestDiet.expirationMoment" var = "expirationMomentHeader" />
		<display:column property = "diet.expirationMoment" title = "${expirationMomentHeader}" />
	
		<spring:message code = "requestDiet.estimateLostWeight" var = "estimateLostWeightHeader" />
		<display:column property = "diet.estimateLostWeight" title = "${estimateLostWeightHeader}" />
		
		<spring:message code="requestDiet.cost" var="costHeader" />
		<display:column property="diet.cost" title="${costHeader}" />
				
		<spring:message code="requestDiet.rewardsPoint" var="rewardsPointHeader" />
		<display:column property="diet.rewardsPoint.points" title="${rewardsPointHeader}" />	
		
		<jstl:if test="${owner2}">		
		<display:column>		
				<a href="requestDiet/cancelled.do?requestDietId=${row.id}"
				onclick="return confirm('<spring:message code = "requestDiet.confirm.cancelled"/>')">
					<spring:message code="requestDiet.cancelled" />
				</a>	
		</display:column>
		</jstl:if>
				
	</display:table>
</jstl:if>
			
<input type="button" name="cancel" value="<spring:message code="cancel"/>" onclick="javascript: window.location.replace('welcome/index.do')"/>