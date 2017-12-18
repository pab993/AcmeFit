<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div style="text-align: center">
	<p>
		<spring:message code="welcome.greeting.prefix" />
		${name}
		<spring:message code="welcome.greeting.suffix" />
	</p>

	<p>
		<spring:message code="welcome.greeting.current.time" />
		${moment}
	</p>
</div>

<div style="text-align: center">
	<img id=bannerContainer src="images/banner1.png"
		alt="Acme-Fit Co., Inc." />

	<jstl:if test="${showAssessment}">
		<display:table name="assessments" id="row" requestURI="${requestURI}"
			pagesize="5" class="table table-hover">

			<spring:message code="assessment.createMoment"
				var="createMomentHeader" />
			<display:column property="createMoment" title="${createMomentHeader}"
				format="{0,date,dd/MM/yyyy}" />

			<spring:message code="assessment.title" var="titleHeader" />
			<display:column property="title" title="${titleHeader}" />

			<spring:message code="assessment.text" var="textHeader" />
			<display:column property="text" title="${textHeader}" />

			<spring:message code="assessment.stars" var="starsHeader" />
			<display:column property="stars" title="${starsHeader}" />




		</display:table>
	</jstl:if>
</div>

<script>
	function changeBanner() {

		var bannerSelector = "images/banner"
				+ (Math.floor(Math.random() * (3 - 1)) + 1) + ".png";
		document.getElementById("bannerContainer").src = bannerSelector;
	}

	setInterval('changeBanner()', 5000);
</script>
