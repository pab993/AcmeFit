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



<jstl:choose>
	<jstl:when test="${principalNoValido}">
	<h3>
	<spring:message code="principalNoValido" />
</h3>
	</jstl:when>
	<jstl:otherwise>
		<display:table name="trainers" id="row" requestURI="${requestURI}"
			pagesize="5" class="table table-hover">

			<spring:message code="trainer.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" />

			<spring:message code="trainer.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />

			<spring:message code="trainer.email" var="emailHeader" />
			<display:column property="email" title="${emailHeader}" />

			<%-- <display:column>
				<a href="customer/requestTrainer.do?trainerId=${row.id}">
					<spring:message code="requestTrainer" />
				</a>
			</display:column> --%>
			
			<display:column>
				<a href="trainer/personalTraining.do?trainerId=${row.id}">
					<spring:message code="personalTrainings.list" />
				</a>
			</display:column>
			
			<display:column>
				<a href="actor/display.do?actorId=${row.id}"> <spring:message
						code="display.actor" />
				</a>
			</display:column>
		</display:table>

	</jstl:otherwise>
</jstl:choose>

<acme:cancel code="cancel" url="welcome/index.do" />