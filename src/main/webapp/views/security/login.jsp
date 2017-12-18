 <%--
 * login.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="styles/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
      <div class="modal-dialog">
		<div class="loginmodal-container">	
			<form:form action="j_spring_security_check" modelAttribute="credentials">
			
				<tr><td><br><br>
			
				<form:label path="username">
					<spring:message code="security.username" />
				</form:label>
				<form:input path="username" />	
				<form:errors class="error" path="username" />
				<br /><br />
			
				<form:label path="password">
					<spring:message code="security.password" />
				</form:label>
				<form:password path="password" />	
				<form:errors class="error" path="password" />
				<br /><br />
				
				<jstl:if test="${showError == true}">
					<div class="error">
						<spring:message code="security.login.failed" />
					</div>
				</jstl:if>
				
				
				<input type="submit" class="btn btn-primary" value="<spring:message code="security.login" />" />
				
			</form:form>
		</div>
	   </div>
  </body>
</html>