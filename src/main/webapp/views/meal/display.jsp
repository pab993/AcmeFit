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




<security:authorize access="hasRole('NUTRITIONIST')">	

<h3>
	<spring:message code="meal.name" />: <jstl:out value="${meal.getName()}" />
</h3>


<h3>
	<spring:message code="meal.kcal" />: <jstl:out value="${meal.getKcal()}" />
</h3>


<h3>
	<spring:message code="meal.nutritionalValue" />: <jstl:out value="${meal.getNutritionalValue()}" />
</h3>

<h3>
	<spring:message code="meal.schedule" />: <jstl:out value="${meal.getSchedule()}" />
</h3>

<h3>
		<a href="meal/edit.do?mealId=${meal.getId() }"> <spring:message
				code="editMeal" />
		</a>
	</h3>
	<h3>
		<spring:message code="meal.ingredients" />
	</h3>
	
	<jstl:forEach items="${ingredients}" var="ingredient">
		<table border="2px" style="width: 300px">
			<!-- Lo cambiaremos por CSS -->
			<tr>

				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="meal.name" />
					</h5></td>
				<td><h5>
						<jstl:out value="${ingredient.name}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="ingredient.multiplicity" />
					</h5></td>
				<td><h5>
						<jstl:out value="${ingredient.multiplicity}" />
					</h5></td>
			</tr>
			<tr>
				<td style='background-color: #D3D3D3'><h5>
						<spring:message code="ingredient.unit" />
					</h5></td>
				<td><h5>
						<jstl:out value="${ingredient.unit}" />
					</h5></td>
			</tr>
		</table>
	</jstl:forEach>
	<h3>
		<a href="ingredient/create.do?mealId=${meal.getId() }"> <spring:message
				code="createIngredient" />
		</a>
	</h3>
	
	
</security:authorize>

		

