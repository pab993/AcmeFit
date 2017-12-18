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
		<display:table name="nutritionists" id="row" requestURI="${requestURI}"
			pagesize="5" class = "table table-hover" >

			<spring:message code="nutritionist.picture" var="pictureHeader" />
			<display:column style="width:48px" title="${pictureHeader}" ><img height="48" width="48" src="${row.picture}"></display:column>

			<spring:message code="nutritionist.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" />

			<spring:message code="nutritionist.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />

			<spring:message code="nutritionist.email" var="emailHeader" />
			<display:column property="email" title="${emailHeader}" />
			
			<security:authorize access="hasRole('CUSTOMER')">
				<spring:message code="nutritionist.diets" var="dietsHeader" />
				<display:column title="${dietsHeader}" >
					<a href="nutritionist/diets.do?nutritionistId=${row.id}"> <spring:message
					code="nutritionist.diets" />
					</a>
				</display:column>
			</security:authorize>

			<display:column>
				<a href="actor/display.do?actorId=${row.id}"> <spring:message
						code="display.actor" />
				</a>
			</display:column>
		</display:table>
		
<input type="button" name="cancel" value="<spring:message code="cancel"/>" onclick="javascript: window.location.replace('welcome/index.do')"/>
<%-- <acme:cancel url="/welcome/index.do" code="cancel"/> --%>