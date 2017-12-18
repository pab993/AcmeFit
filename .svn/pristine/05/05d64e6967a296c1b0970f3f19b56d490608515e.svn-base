<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${requestTrainingForm.getRequestTrainingId() == 0}">
	<h2><spring:message code="message.application.pt"/></h2>
</jstl:if>

<jstl:if test="${requestTrainingForm.getRequestTrainingId() != 0 && requestTrainingStatus == 'PENDING'}">
	<h2><spring:message code="message.application.pt2"/></h2>
</jstl:if>

<jstl:if test="${requestTrainingForm.getRequestTrainingId() != 0 && requestTrainingStatus != 'PENDING'}">
	<h2><spring:message code="message.application.pt3"/></h2>
</jstl:if>

<form:form action="personalTraining/applicationConfirm.do" modelAttribute="requestTrainingForm">

	<form:hidden path="requestTrainingId" />
	<form:hidden path="personalTraining"/>
	
	<jstl:if test="${requestTrainingForm.getRequestTrainingId() == 0}">
		<input type="submit" name="save" value="<spring:message code="applicationTraining.save" />" />&nbsp;
	</jstl:if>
	<jstl:if test="${requestTrainingForm.getRequestTrainingId() != 0 && requestTrainingStatus == 'PENDING'}">
		<input type="submit" name="delete" value="<spring:message code="applicationTraining.delete" />" onclick="return confirm('<spring:message code="applicationTraining.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="applicationTraining.cancel"/>" onclick="javascript: window.location.replace('trainer/personalTraining.do?trainerId=${trainerId}')"/>

</form:form>