<%--
 * register_Explorer.jsp
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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="user/register_User.do" modelAttribute="userForm">

	<form:hidden path="user.id"/>
	<form:hidden path="user.version"/>

	<acme:textbox code="actor.username" path="user.userAccount.username" />
	<jstl:choose>
		<jstl:when test="${userForm.user.id==0}">
			<acme:password code="actor.password" path="user.userAccount.password" />
			<br />
			<acme:password code="actor.password" path="confirmPassword" />
			<br />
		</jstl:when>
		<jstl:otherwise></jstl:otherwise>
	</jstl:choose>

	<acme:textbox code="actor.name" path="user.name" />
	<acme:textbox code="actor.surname" path="user.surname" />
	<acme:textbox code="actor.email" path="user.email" />
	<acme:textbox code="actor.phoneNumber" path="user.phoneNumber" />
	<acme:textbox code="actor.postalAddress" path="user.postalAddress" />

	<acme:date code="actor.dateBorn" path="user.dateBorn"  placeholder="dd/MM/yyyy"/>
	
	<jstl:if test="${userForm.user.id == 0}">
   		<form:label path="terms">
		<spring:message code="actor.legal.agree"/><a href="misc/legal.do"><spring:message code="actor.legal.info"/></a>
		</form:label>
		<form:checkbox id="terms" path="terms" />
		<form:errors cssClass="error" path="terms"/>
   </jstl:if>
	
	<br />
	
	<acme:submit name="save" code="actor.submit" />
	<acme:cancel url="/" code="answer.cancel" />
	
	<br />
</form:form>