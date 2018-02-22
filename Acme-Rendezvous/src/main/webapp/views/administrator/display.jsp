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

<h4>
	<spring:message code="administrator.averageRedezvousUser" />
</h4>
<h3>
	<jstl:out value="${averageRedezvousUser}" />
</h3>
<h4>
	<spring:message code="administrator.estandardDesviationRedezvousUser" />
</h4>
<h3>
	<jstl:out value="${estandardDesviationRedezvousUser}" />
</h3>
<h4>
	<spring:message code="administrator.ratioUserConRendezvous" />
</h4>
<h3>
	<jstl:out value="${ratioUserConRendezvous}" />
</h3>
<h4>
	<spring:message code="administrator.ratioUserSinRendezvous" />
</h4>
<h3>
	<jstl:out value="${ratioUserSinRendezvous}" />
</h3>
<h4>
	<spring:message code="administrator.averageUsersRendezvous" />
</h4>


<h3>
	<jstl:out value="${averageUsersRendezvous}" />
</h3>
<h4>
	<spring:message code="administrator.estandardDesviationUsersRendezvous" />
</h4>
<h3>
	<jstl:out value="${estandardDesviationUsersRendezvous}" />
</h3>
<h4>
	<spring:message code="administrator.averageRendezvousRSVPTruePerUser" />
</h4>
<h3>
	<jstl:out value="${averageRendezvousRSVPTruePerUser}" />
</h3>
<h4>
	<spring:message
		code="administrator.estandardDesviationRendezvousRSVPTruePerUser" />
</h4>
<h3>
	<jstl:out value="${estandardDesviationRendezvousRSVPTruePerUser}" />
</h3>
<h4>
	<spring:message code="administrator.averageannouncementsRendezvous" />
</h4>
<h3>
	<jstl:out value="${averageannouncementsRendezvous}" />
</h3>
<h4>
	<spring:message
		code="administrator.estandardDesviationAnnouncementsUser" />
</h4>

<h3>
	<jstl:out value="${estandardDesviationAnnouncementsUser}" />
</h3>
<h4>
	<spring:message code="administrator.redezvousSimiliars10" />
</h4>

<h4>
	<jstl:if test="${redezvousSimiliars10==null}">
		<spring:message code="administrator.noHay" />
	</jstl:if>
</h4>
<h3>
<jstl:if test="${redezvousSimiliars10!=null}">
	
		<jstl:forEach var="row" items="${redezvousSimiliars10}">

			<jstl:out value="${row.name }"></jstl:out>
		</jstl:forEach>
</jstl:if>
</h3>
<h4>
	<spring:message
		code="administrator.averageNumberOfQuestionsPerRendezvous" />
</h4>
<h3>
	<jstl:out value="${averageNumberOfQuestionsPerRendezvous}" />
</h3>
<h4>
	<spring:message
		code="administrator.estandardDesviationOfQuestionsPerRendezvous" />
</h4>
<h3>
	<jstl:out value="${estandardDesviationOfQuestionsPerRendezvous}" />
</h3>
<h4>
	<spring:message
		code="administrator.averageOfAnswerPerQuestionsPerRendezvous" />
</h4>
<h3>
	<jstl:out value="${averageOfAnswerPerQuestionsPerRendezvous}" />
</h3>
<h4>
	<spring:message
		code="administrator.estandardDesviationOfAnswerPerQuestionsPerRendezvous" />
</h4>
<h3>
	<jstl:out
		value="${estandardDesviationOfAnswerPerQuestionsPerRendezvous}" />
</h3>
<h4>

	<spring:message code="administrator.RendezvousConMas075Announcement" />
</h4>


<h3>
	<jstl:forEach var="row" items="${RendezvousConMas075Announcement}">
		<jstl:out value="${row.name }"></jstl:out>
	</jstl:forEach>
</h3>
<h4>
	<spring:message code="administrator.averageRepliesComment" />
</h4>
<h3>
	<jstl:out value="${averageRepliesComment}" />
</h3>
<h4>
	<spring:message code="administrator.estandardDesviationRepliesComment" />
</h4>

<h3>
	<jstl:out value="${estandardDesviationRepliesComment}" />
</h3>




<!-- falta lo del top -->















