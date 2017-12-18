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

<form:form action="service/administrator/edit.do" modelAttribute="additionalService">

	<form:hidden path="id"/>

	<div >
		<acme:textbox code="service.name" path="name"/>
		<br/>
		
		<acme:textbox code="service.openingTime" path="openingTime"/>
		<br/>
		
		<acme:textbox code="service.closingTime" path="closingTime"/>
		<br/>
		
		<acme:textbox code="service.duration" path="duration"/>
		<br/>
		
		<acme:textbox code="service.cost" path="cost"/>
		<br/>
		
		<acme:textbox code="service.maximumCapacity" path="maximumCapacity"/>
		<br/>
	</div>

	<div id="botones">
		<acme:cancel url="service/list.do" code="service.cancel"/>
		<acme:submit code="service.save" name="save" />
		<jstl:if test="${additionalService.id != 0}">
			<acme:delete name="delete" code="service.delete" msg="service.delete.confirm"/>
		</jstl:if>
	</div>

</form:form>