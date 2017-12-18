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

<form:form action="booking/customer/book.do" modelAttribute="booking">

	<form:hidden path="id"/>
	<form:hidden path="service"/>

	<div >
		<acme:textbox code="service.name" path="service.name" readonly="true"/>
		<br/>
	
		<acme:textbox code="booking.moment" path="moment"/>
		<br/>
	</div>

	<div id="botones">

		<acme:submit code="booking.save" name="save" />
		<input type="button" name="cancel" value="<spring:message code="booking.cancel"/>" onclick="javascript: window.location.replace('service/list.do')"/>

	</div>

</form:form>