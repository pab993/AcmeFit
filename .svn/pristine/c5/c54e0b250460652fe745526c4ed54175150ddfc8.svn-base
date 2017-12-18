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

<jstl:if test="${var == true }">
<!-- Listing table -->
		<display:table name="meals" id="row" requestURI="${requestURI}"
			pagesize="5" class = "table table-hover" >

			<spring:message code="meal.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" />

			<spring:message code="meal.kcal" var="kcalHeader" />
			<display:column property="kcal" title="${kcalHeader}" />

			<spring:message code="meal.nutritionalValue" var="nutritionalHeader" />
			<display:column property="nutritionalValue" title="${nutritionalHeader}" />
			

 			<spring:message code="meal.ingredients" var="ingredientsHeader" />
			<display:column title="${ingredientsHeader}" >
				<a href="ingredient/list.do?mealId=${row.id}"> <spring:message code="meal.ingredients" />
				</a>
			</display:column>
		 

		</display:table>

		
	<input type="button" name="cancel" value="<spring:message code="cancel"/>" onclick="javascript: window.location.replace('nutritionist/diets.do?nutritionistId=${row.diet.nutritionist.id}')"/>
</jstl:if>