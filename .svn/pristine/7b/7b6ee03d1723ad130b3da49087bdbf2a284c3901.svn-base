<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="curriculum/edit.do" modelAttribute="curriculumForm">
	
	<form:hidden path="curriculumId" />
	<%-- <form:hidden path="version" />
	<form:hidden path="employee"/>
	<form:hidden path="uploadTime"/> --%>
		
	<acme:textbox code="curriculum.photograph" path="photograph"/>
	<br />
	
	<acme:textarea code="curriculum.academicFormation" path="academicFormation"/>
	<br />
	
	<acme:textarea code="curriculum.experience" path="experience"/>
	<br />
	
<%-- 	<form:label path="uploadTime" >
		<spring:message code="curriculum.uploadTime" />:
	</form:label>
	<form:textarea path="uploadTime" />
	<form:errors cssClass="error" path="uploadTime" />
	<br /> --%>
	
	<input type="submit" name="save" value="<spring:message code="curriculum.save" />" />&nbsp;
	<jstl:if test="${curriculumForm.getCurriculumId() != 0}">
		<input type="submit" name="delete" value="<spring:message code="curriculum.delete" />" onclick="return confirm('<spring:message code="curriculum.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="curriculum.cancel"/>" onclick="javascript: window.location.replace('curriculum/list.do')"/>
	
</form:form>