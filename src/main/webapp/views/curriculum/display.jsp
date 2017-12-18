<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Display Curriculum -->

<jstl:if test="${curriculum.id != null}">

	<h3><spring:message code="curriculum.photograph" /></h3>
	<img height="65" width="65" src="${curriculum.getPhotograph()}" />
	
	<h3><spring:message code="curriculum.employee"/></h3>
	<jstl:out value="${curriculum.getEmployee().getName()}"/>
	
	<h3><spring:message code="curriculum.academicFormation"/></h3>
	<jstl:out value="${curriculum.getAcademicFormation()}"/>
	
	<h3><spring:message code="curriculum.experience" /></h3>
	<jstl:out value="${curriculum.getExperience()}"/>
	
	<h3><spring:message code="curriculum.uploadTime" /></h3>
	<fmt:formatDate value="${curriculum.getUploadTime()}" pattern="dd/MM/yyyy HH:mm"/>
	<%-- <jstl:out value="${curriculum.getUploadTime()}" pattern="dd/MM/yyyy HH:mm"/> --%>
	
	<br>
	<br>

</jstl:if>

<jstl:if test="${curriculum.id == null }">

<h2><spring:message code="curriculum.empty.customer"/></h2>

</jstl:if>