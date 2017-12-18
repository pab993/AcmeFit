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

<form:form action="competition/administrator/create.do" modelAttribute="competition">

	<form:hidden path="id"/>

	<div >
		<acme:textbox code="competition.startDate" path="startDate"/>
		<br/>
		
		<acme:textbox code="competition.closingDate" path="closingDate"/>
		<br/>
		
		<acme:textbox code="competition.description" path="description"/>
		<br/>
		
		<acme:textbox code="competition.rules" path="rules"/>
		<br/>
		
		<acme:textbox code="competition.prize" path="prize"/>
		<br/>
		
	</div>

	<div id="botones">
		<acme:cancel url="competition/list.do" code="competition.cancel"/>
		<acme:submit code="competition.save" name="save" />
	</div>

</form:form>