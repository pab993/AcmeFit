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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<security:authorize access="hasRole('ADMINISTRATOR')">
	
	<div>
		<display:table name="minAvgMaxActivitiesTrainer" id="minAvgMaxActivitiesTrainer" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.minAvgMaxActivitiesTrainer" var="dash1" />
			<display:column title="${dash1}">
				<h3> <jstl:out value="${minAvgMaxActivitiesTrainer}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="minAvgMaxDietsByCustomer" id="minAvgMaxDietsByCustomer" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.minAvgMaxDietsByCustomer" var="dash2" />
			<display:column title="${dash2}">
				<h3> <jstl:out value="${minAvgMaxDietsByCustomer}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="findTrainersMoreActivities" id="findTrainersMoreActivities" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.findTrainersMoreActivities" var="dash3" />
			<display:column title="${dash3}">
				<h3> <jstl:out value="${findTrainersMoreActivities.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="findTrainerLessActivities" id="findTrainersLessActivities" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.findTrainersLessActivities" var="dash7" />
			<display:column title="${dash7}">
				<h3> <jstl:out value="${findTrainersLessActivities.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="findTrainersOrderByPersonalTrainings" id="findTrainersOrderByPersonalTrainings" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.findTrainersOrderByPersonalTrainings" var="dash4" />
			<display:column title="${dash4}">
				<h3> <jstl:out value="${findTrainersOrderByPersonalTrainings.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="rankingCustomersByInvoices" id="rankingCustomersByInvoices" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.rankingCustomersByInvoices" var="dash5" />
			<display:column title="${dash5}">
				<h3> <jstl:out value="${rankingCustomersByInvoices.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="findCustomerSpentMore70" id="findCustomerSpentMore70" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.findCustomerSpentMore70" var="dash6" />
			<display:column title="${dash6}">
				<h3> <jstl:out value="${findCustomerSpentMore70.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="findCustomerMoreActivities" id="findCustomerMoreActivities" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.findCustomerMoreActivities" var="dash8" />
			<display:column title="${dash8}">
				<h3> <jstl:out value="${findCustomerMoreActivities.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="customersWithMoreCanceledBookings" id="customersWithMoreCanceledBookings" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.customersWithMoreCanceledBookings" var="dash14" />
			<display:column title="${dash14}">
				<h3> <jstl:out value="${customersWithMoreCanceledBookings.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<h3>
		<spring:message code="dashboard.minAvgMaxBookingsPerService"/>
		</h3>
		<display:table name="minAvgMaxBookingsPerService" id="minAvgMaxBookingsPerService" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${minAvgMaxBookingsPerService[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${minAvgMaxBookingsPerService[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${minAvgMaxBookingsPerService[2]}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="servicesWithMorConfirmedBookings" id="servicesWithMorConfirmedBookings" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.servicesWithMorConfirmedBookings" var="dash16" />
			<display:column title="${dash16}">
				<h3> <jstl:out value="${servicesWithMorConfirmedBookings.name}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="ratioCanceledBookingsTotalBookings" id="ratioCanceledBookingsTotalBookings" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.ratioCanceledBookingsTotalBookings" var="dash17" />
			<display:column title="${dash17}">
				<h3> <jstl:out value="${ratioCanceledBookingsTotalBookings}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
	<div>
		<display:table name="ratioCanceledBookingsWPCanceledBookings" id="ratioCanceledBookingsWPCanceledBookings" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.ratioCanceledBookingsWPCanceledBookings" var="dash18" />
			<display:column title="${dash18}">
				<h3> <jstl:out value="${ratioCanceledBookingsWPCanceledBookings}" /> </h3> 
			</display:column>
		</display:table>
	</div>
	
		<h3>
		<spring:message code="nutritionistOrderByPopularity"/>
		</h3>
	
	<display:table name="nutritionistOrderByPopularity" id="nutritionistOrderByPopularity"
			class = "table table-hover" >
			
			<spring:message code="nutritionist.picture" var="pictureHeader" />
			<display:column style="width:48px" title="${pictureHeader}" ><img height="48" width="48" src="${row.picture}"></display:column>

			<spring:message code="nutritionist.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" />

			<spring:message code="nutritionist.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />

			<spring:message code="nutritionist.email" var="emailHeader" />
			<display:column property="email" title="${emailHeader}" />
			
		</display:table>
		<div>
		<display:table name="avgPersonalTrainingForCustomer" id="avgPersonalTrainingForCustomer" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.avgPersonalTrainingForCustomer" var="dash19" />
			<display:column title="${dash19}">
				<h3> <jstl:out value="${avgPersonalTrainingForCustomer}" /> </h3> 
			</display:column>
		</display:table>
	</div>

</security:authorize>