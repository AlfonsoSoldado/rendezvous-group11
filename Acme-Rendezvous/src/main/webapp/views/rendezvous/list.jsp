<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous" requestURI="${requestUri}" id="row">
	
	<!-- Attributes -->
	
	<security:authorize access="hasRole('USER')">
	<display:column> <acme:links url="rendezvous/user/edit.do?rendezvousId=${row.id}" code="rendezvous.edit" /> </display:column>
	</security:authorize>
	
	<acme:column code="rendezvous.name" property="name" />
	<acme:column code="rendezvous.description" property="description" />
	<acme:column code="rendezvous.moment" property="moment" />
	<acme:column code="rendezvous.finalMode" property="finalMode" />
	<display:column><acme:links url="rendezvous/listSimilar.do?rendezvousId=${row.id }" code="rendezvous.similar"/></display:column>
	<display:column> <acme:links url="announcement/list.do?rendezvousId=${row.id}" code="rendezvous.announcement" /> </display:column>
	<display:column> <acme:links url="user/listCreator.do?rendezvousId=${row.id}" code="rendezvous.user" /> </display:column>
	
	<security:authorize access="hasRole('USER')">
	<display:column> <acme:links url="question/user/create.do?rendezvousId=${row.id}" code="rendezvous.question.create" /> </display:column>
	</security:authorize>
	
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('USER')">
	<acme:links url="rendezvous/user/create.do" code="rendezvous.create" />
</security:authorize>