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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing table -->

<div>
	<display:table name="invoices" id="row" requestURI="${requestURI}"
		pagesize="5" class="table table-hover">

		<spring:message code="invoice.customer" var="customerHeader" />
		<display:column property="customer.name" title="${customerHeader}" />

		<spring:message code="invoice.invoiceDate" var="invoiceDateHeader" />
		<display:column property="invoiceDate" title="${invoiceDateHeader}" />

		<spring:message code="invoice.paid" var="paidHeader" />
		<display:column property="paid" title="${paidHeader}" />

		<spring:message code="invoice.totalPrice" var="totalPriceHeader" />
		<display:column property="totalPrice" title="${totalPriceHeader}" />

		<display:column>
			<a href="invoice/display.do?invoiceId=${row.id}"> <spring:message
					code="invoice.display" />
			</a>
		</display:column>

	</display:table>
</div>
<br />

<acme:cancel code="invoice.cancel" url="welcome/index.do" />