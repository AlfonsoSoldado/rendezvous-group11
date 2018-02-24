<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">
	<security:authorize access="hasRole('USER')">

		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:hidden path="comment" />
		<form:hidden path="attendant" />
		<form:hidden path="announcement" />
		<form:hidden path="similar" />

		<acme:textbox code="rendezvous.name" path="name" />
		<acme:textbox code="rendezvous.description" path="description" />
		<acme:date code="rendezvous.moment" path="moment"
			placeholder="dd/MM/yyyy HH:mm" />
		<acme:textbox code="rendezvous.gpsCoordinate.latitude"
			path="gpsCoordinate.latitude" />
		<acme:textbox code="rendezvous.gpsCoordinate.longitude"
			path="gpsCoordinate.longitude" />
		<acme:textbox code="rendezvous.gpsCoordinate.name"
			path="gpsCoordinate.namePlace" />
		<acme:textbox code="rendezvous.picture" path="picture" />
		
		<acme:selectBoolean code="rendezvous.finalMode" path="finalMode" items="${finalModes}" />
		<acme:selectBoolean code="rendezvous.adultOnly" path="adultOnly" items="${finalModes}" />

		<!-- Buttons -->

		<acme:submit name="save" code="rendezvous.save" />
		<acme:delete confirmationCode="rendezvous.confirm.delete"
			buttonCode="rendezvous.delete" id="${rendezvous.id }" />
		<acme:cancel url="rendezvous/list.do" code="rendezvous.cancel" />

	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<acme:delete confirmationCode="rendezvous.confirm.delete"
			buttonCode="rendezvous.delete" id="${rendezvous.id }" />
		<acme:cancel url="rendezvous/list.do" code="rendezvous.cancel" />
	</security:authorize>
</form:form>