<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Display Curriculum -->

<jstl:if test="${curriculum.id != null}">

 	<table border="2px" style="width:300px"> <!-- Lo cambiaremos por CSS -->
     		<tr>    
			
                <td style='background-color:#D3D3D3'><h5><spring:message code="curriculum.photograph" /></h5></td>
                <td><img height="65" width="65" src="${curriculum.getPhotograph()}" /></td>
            </tr>
            <tr>
                <td style='background-color:#D3D3D3'><h5><spring:message code="curriculum.employee"/></h5></td>
                <td><h5><jstl:out value="${curriculum.getEmployee().getName()}" /></h5></td>
            </tr>
            <tr>
				<td style='background-color:#D3D3D3'><h5><spring:message code = "curriculum.academicFormation"/></h5></td>
                <td><h5><jstl:out value="${curriculum.getAcademicFormation()} "/></h5></td>
            </tr>
            <tr>	
				<td style='background-color:#D3D3D3'><h5><spring:message code = "curriculum.experience"/></h5></td>
                <td><h5><jstl:out value="${curriculum.getExperience()}"/></h5></td>
            </tr>
            <tr>	
				<td style='background-color:#D3D3D3'><h5><spring:message code = "curriculum.uploadTime"/></h5></td>
                <td><h5><fmt:formatDate value="${curriculum.getUploadTime()}" pattern="dd/MM/yyyy HH:mm"/></h5></td>
            </tr>
  </table>
 	
	<br>
	<br>

	<a href="curriculum/edit.do?curriculumId=${curriculum.getId()}"><spring:message code="curriculum.edit" /></a>
</jstl:if>


<jstl:if test="${curriculum.id==null}">
			<a href="curriculum/create.do"><spring:message code="curriculum.create" /></a>
</jstl:if>




<%-- <display:table name="curriculums" id="curriculum" pagesize="5" requestURI="${requestURI}" class="displaytag">

	<security:authorize access="hasAnyRole('NUTRITIONIST', 'TRAINER')">
		
	
		<spring:message code="curriculum.photograph" var="photographHeader"/>
		<display:column title="${photographHeader}" ><img height="65" width="65" src="${curriculum.getPhotograph()}" /></display:column>
		<display:column>
			<a href="${curriculum.photo}"><spring:message code="curriculum.photo" /></a>
		</display:column>
		
		<spring:message code="curriculum.employee" var="employeeHeader"/>
		<display:column title="${employeeHeader}" property="employee.name"/>
			
		
		<spring:message code="curriculum.academicFormation" var="academicFormationHeader" />
		<display:column title="${academicFormationHeader}" property="academicFormation" />
		
		<spring:message code="curriculum.experience" var="experienceHeader" />
		<display:column title="${experienceHeader}" property="experience" />
		
		<spring:message code="curriculum.uploadTime" var="uploadTimeHeader" />
		<display:column title="${uploadTimeHeader}" property="uploadTime" />
		
		
		<display:column>
			<a href="curriculum/edit.do?curriculumId=${curriculum.id}"><spring:message code="curriculum.edit" /></a>
		</display:column>
	
	</security:authorize>
</display:table>

<jstl:if test="${curriculum.id==null}">
			<a href="curriculum/create.do"><spring:message code="curriculum.create" /></a>
</jstl:if> --%>