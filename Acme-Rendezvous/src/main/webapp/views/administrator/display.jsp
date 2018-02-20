<%--
 * layout.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="administrator.averageRedezvousUser"
	var="averageRedezvousUser" />

<spring:message code="administrator.EstandardDesviationRedezvousUser"
	var="EstandardDesviationRedezvousUser" />

<spring:message
	code="administrator.ratioCreateAndNoCreateRendezvousUser"
	var="ratioCreateAndNoCreateRendezvousUser" />

<spring:message code="administrator.ratioUserConRendezvous"
	var="ratioUserConRendezvous" />

<spring:message code="administrator.ratioUserSinRendezvous"
	var="ratioUserSinRendezvous" />

<spring:message code="administrator.averageUsersRendezvous"
	var="averageUsersRendezvous" />

<spring:message code="administrator.EstandardDesviationUsersRendezvous"
	var="EstandardDesviationUsersRendezvous" />

<spring:message code="administrator.averageRendezvousRSVPTruePerUser"
	var="averageRendezvousRSVPTruePerUser" />

<spring:message
	code="administrator.EstandardDesviationRendezvousRSVPTruePerUser"
	var="EstandardDesviationRendezvousRSVPTruePerUser" />

<spring:message code="administrator.topRendezvous" var="topRendezvous" />


<h3>
	<jstl:out value="${averageRedezvousUser}" />
</h3>
<h3>
	<jstl:out value="${EstandardDesviationRedezvousUser}" />
</h3>
<h3>
	<jstl:out value="${ratioCreateAndNoCreateRendezvousUser}" />
</h3>
<h3>
	<jstl:out value="${ratioUserConRendezvous}" />
</h3>
<h3>
	<jstl:out value="${ratioUserSinRendezvous}" />
</h3>
<h3>
	<jstl:out value="${averageUsersRendezvous}" />
</h3>
<h3>
	<jstl:out value="${EstandardDesviationUsersRendezvous}" />
</h3>
<h3>
	<jstl:out value="${averageRendezvousRSVPTruePerUser}" />
</h3>
<h3>
	<jstl:out value="${EstandardDesviationRendezvousRSVPTruePerUser}" />
</h3>
<h3>
	<jstl:out value="${topRendezvous}" />
</h3>

