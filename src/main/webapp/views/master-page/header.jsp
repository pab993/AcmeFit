<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div style="text-align: center">
	<img src="images/logo.png" width="400px" alt="Acme-Fit Co., Inc." />
</div>



<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv" href="administrator/dashboard.do"><spring:message	code="master.page.dashboard" /></a>
			<li><a class="fNiv" href="customer/administrator/list.do"><spring:message	code="master.page.listCustomer" /></a>
			<li><a class="fNiv" href="administrator/showAssessments.do"><spring:message	code="master.page.showAssessments" /></a>
			
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="security/registerTrainer.do"><spring:message
								code="master.page.register.Trainer" /></a></li>
					<li><a href="security/registerNutritionist.do"><spring:message
								code="master.page.register.Nutritionist" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="invoice/list.do"><spring:message
						code="master.page.invoice" /></a>
			<li><a class="fNiv" href="nutritionist/list.do"><spring:message
						code="master.page.nutritionist.list" /></a>
			<li><a class="fNiv" href="trainer/list.do"><spring:message
						code="master.page.trainer.list" /></a>
			<li><a class="fNiv"><spring:message code="master.page.diet" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="diet/myDiets.do"><spring:message
								code="master.page.diet.myDiets" /></a></li>
				</ul></li>
			<li><a class="fNiv" href="booking/customer/myBookings.do"><spring:message
						code="master.page.bookings" /></a>
			<li><a class="fNiv"><spring:message
						code="master.page.requests" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="requestDiet/myList.do"><spring:message
								code="master.page.myRequestDiet" /></a></li>
					<li><a href="requestDiet/myListPending.do"><spring:message
								code="master.page.myRequestDietPending" /></a></li>
					<li><a href="requestTraining/myList.do"><spring:message
								code="master.page.myRequestTraining" /></a></li>
					<li><a href="requestTraining/myListPending.do"><spring:message
								code="master.page.myRequestTrainingPending" /></a></li>
				</ul></li>
			<%-- <li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li> --%>
		</security:authorize>

		<!-- Todos los usuarios registrados como no registrados pueden realizar las siguientes acciones !! -->
		<li><a class="fNiv"><spring:message
					code="master.page.activities" /></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="activity/search.do"><spring:message
							code="master.page.activity.search" /></a></li>
				<li><a href="activity/list.do"><spring:message
							code="master.page.activity.list" /></a></li>
				<security:authorize access="hasRole('TRAINER')">
					<li><a href="activity/create.do"><spring:message
								code="master.page.activity.create" /></a></li>
				</security:authorize>
			</ul></li>
		<li><a href="competition/list.do"><spring:message
					code="master.page.competition.list" /></a></li>

		<security:authorize access="hasRole('TRAINER')">
			<li><a class="fNiv" href="requestTraining/myPTList.do"><spring:message
						code="master.page.trainer.requestsTrainings" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv" href="requestDiet/list.do"><spring:message code="master.page.nutritionist.requestsDiets" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.nutritionist.diets" /></a>
				<ul>
					<li class="arrow"/>
						<li><a href="diet/createDiet.do"><spring:message code="master.page.nutritionist.createDiet" /></a></li>
						<li><a href="diet/nutritionistDiets.do"><spring:message code="master.page.nutritionist.nutri.diets" /></a></li>
				</ul>
		</security:authorize>

		<security:authorize access="hasRole('TRAINER')">
			<li><a class="fNiv"><spring:message
					code="master.page.trainer.personalTraining" /></a>
			<ul>
				<li class="arrow"></li>
					<li><a href="personalTraining/createPT.do"><spring:message
								code="master.page.trainer.createPersonalTraining" /></a></li>
					<li><a href="personalTraining/listPT.do"><spring:message
								code="master.page.trainer.listPersonalTraining" /></a></li>
			</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv" href="security/register.do"><spring:message
						code="master.page.register" /></a></li>

		</security:authorize>

		<security:authorize access="hasAnyRole('CUSTOMER', 'ADMINISTRATOR')">
			<li><a href="service/list.do"><spring:message
						code="master.page.services" /></a></li>
		</security:authorize>

		<security:authorize access="hasAnyRole('NUTRITIONIST', 'TRAINER')">
			<li><a href="curriculum/list.do"><spring:message
						code="master.page.curriculum.list" /></a></li>
			<li><a href="customer/list.do"><spring:message
						code="master.page.customer.list" /></a></li>
		</security:authorize>

		<security:authorize
			access="hasAnyRole('NUTRITIONIST', 'TRAINER', 'CUSTOMER')">
			<li><a href="creditCard/edit.do"><spring:message
						code="master.page.creditCard" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize
						access="hasAnyRole('NUTRITIONIST', 'TRAINER', 'CUSTOMER')">
						<li><a href="actor/edit.do"><spring:message
									code="master.page.editActor" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div class="lang" align="center">
	<a href="?language=en"><img class="langImg"
		src="images/icon_id_en.png"></a> <a href="?language=es"><img
		class="langImg" src="images/icon_id_es.png"></a>
</div>

