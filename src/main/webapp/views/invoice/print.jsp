<%--
 * details.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  %>
 
<link rel="stylesheet" href="styles/print.css" type="text/css">
 
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>


<h3><spring:message code = "invoice.details"/></h3>
 	<table border="2px" style="width:500px"> <!-- Lo cambiaremos por CSS -->
     		<tr>    
                <td><h5><spring:message code = "invoice.customer"/></h5></td>
                <td><h5><jstl:out value="${invoice.getCustomer().getName()}" /></h5></td>
            </tr>
            <tr>
                <td><h5><spring:message code = "invoice.invoiceDate"/></h5></td>
                <td><h5><jstl:out value="${invoice.getInvoiceDate()}" /></h5></td>
            </tr>
            <tr>
				<td><h5><spring:message code = "invoice.totalPrice"/></h5></td>
                <td><h5><jstl:out value="${invoice.getTotalPrice()}" /></h5></td>
            </tr>
 
 			<tr>            
			    <td colspan="2"><h5><spring:message code = "invoice.lineInvoices"/></h5></td>
			</tr>
 	</table>

 	<table border="2px" style="width:500px"> <!-- Lo cambiaremos por CSS -->
	           
            <tr>            
					<display:table name="lineInvoices" id="row" requestURI="${requestURI}"
					pagesize="5" class="table table-hover">
	
						<spring:message code="lineInvoice.concept" var="conceptHeader" />
						<display:column property="concept" title="${conceptHeader}" />
				
						<spring:message code="lineInvoice.quantity" var="quantityHeader" />
						<display:column property="quantity" title="${quantityHeader}" />
				
						<spring:message code="lineInvoice.price" var="priceHeader" />
						<display:column property="price" title="${priceHeader}" />
					</display:table>
			</tr>
		
  </table>

<br />
<br />

</body>

<script type="text/javascript">
	function printf(){
		
		document.getElementById('printButton').style.display = 'none';
		document.getElementById('cancelButton').style.display = 'none';
		window.print();
		document.getElementById('printButton').style.display = 'inline';
		document.getElementById('cancelButton').style.display = 'inline';

	}

</script>
<input id = "cancelButton" type="button" name="cancel"
			value="<spring:message code="invoice.cancel" />"
			onclick="history.go(-1)" />
<input id = "printButton" type="button" name="imprimir" value="<spring:message code="invoice.printInvoiceButton" />" onclick="printf();">
			
</html>