<%--
 * index.jsp
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

<spring:message var="name" code="nameProject" />
<spring:message var="lang" code="language" />
<jstl:if test="${lang == 'es' }">

	<p>
		En cumplimiento con el deber de informaci�n recogido en art�culo 10 de
		la Ley 34/2002, de 11 de julio, de Servicios de la Sociedad de la
		Informaci�n y del Comercio Electr�nico,le informamos:

		<jstl:out value="${name }" />
		se encuentra inscrita en el Registro Mercantil de Murcia, Tomo 2236,
		Libro 0, folio 52, hoja MU-52949, inscripci�n 4.<br>
		<jstl:out value="${name }" />
		con CIF B733347494, es una sociedad domiciliada a los efectos de la
		presente informaci�n en la Avda. Europa, Parcelas 2-5 y 2-6 Pol�gono
		Industrial Las Salinas, 30840 Alhama de Murcia, y es en la actualidad
		la encargada de la explotaci�n, gesti�n y funcionamiento del sitio web
		www.
		<jstl:out value="${name }" />
		.com

	</p>


	<h2>Terminos y condiciones del uso de datos</h2>
	<p>
		La visita a este sitio Web no implica que el usuario est� obligado a
		facilitar ninguna informaci�n. En el caso de que el usuario facilite
		alguna informaci�n de car�cter personal, los datos recogidos en este
		sitio web ser�n tratados de forma leal y l�cita con sujeci�n en todo
		momento a los principios y derechos recogidos en la Ley Org�nica
		15/1999, de 13 de diciembre, de Protecci�n de Datos de Car�cter
		Personal (LOPD), y dem�s normativa de desarrollo. <br> - Apartado
		"Registrar usuario" <br> De acuerdo a la Ley Org�nica 15/1999 de
		13 de Diciembre, de Protecci�n de Datos de Car�cter Personal (LOPD),
		le informamos que mediante la cumplimentaci�n de los formularios, sus
		datos personales quedar�n incorporados y ser�n tratados en ficheros de
		<jstl:out value="${name }" />
		. La principal finalidad de dicho fichero es la gesti�n de los
		usuarios registrados en nuestra web.
		<jstl:out value="${name }" />
		asegura la confidencialidad de los datos aportados y garantiza que, en
		ning�n caso, ser�n cedidos para ning�n otro uso sin mediar
		consentimiento previo y expreso de nuestros usuarios. S�lo le
		pediremos aquellos datos necesarios para la prestaci�n del servicio
		requerido y �nicamente ser�n empleados para este fin. - Informaci�n
		recogida orientada a mejoras web.<br>Para mejorar nuestros
		servicios on-line estamos analizando el portal de
		<jstl:out value="${name }" />
		, la correcta navegaci�n del usuario y el borrado virtual de la
		informaci�n. Esta informaci�n ser� confidencial y �nicamente ser�
		utilizada para mejorar la seguridad y funcionamiento del portal
		on-line. <br> - Uso de



	</p>



	<h2>Uso de cookies</h2>
	<p>
		En nuestro portal se utilizan dos tipos de cookies.<br>
		<lu>

		<li>Cookies de sesi�n de usuario: dise�adas para recabar y
			almacenar datos mientras el usuario accede a una p�gina web. Se
			utiliza para almacenar informaci�n que s�lo interesa conservar para
			la prestaci�n del servicio solicitado por el usuario en una sola
			ocasi�n (por ejemplo, una lista de productos adquiridos).</li>
		<li>Cookies de idioma: esta se utiliza para guardar la
			preferencia de idioma, mientras se usa el portal.</li>

		</lu>
	</p>

</jstl:if>

<jstl:if test="${lang == 'en' }">

	<p>
		In compliance with the duty of information contained in Article 10 of
		Law 34/2002, of July 11, Services of the Information Society and
		Electronic Commerce, we inform you:

		<jstl:out value="${name }" />
		It is registered in the Mercantile Registry of Murcia, Volume 2236,
		Book 0, folio 52, sheet MU-52949, inscription 4.<br>
		<jstl:out value="${name }" />
		with CIF B733347494, is a company domiciled for the purposes of this
		information on the Avda. Europa, Plots 2-5 and 2-6 Las Salinas
		Industrial Park, 30840 Alhama de Murcia, and is currently in charge of
		the exploitation, management and operation of the website www.
		<jstl:out value="${name }" />
		.com

	</p>


	<h2>Terms and conditions of data use</h2>
	<p>
		The visit to this website does not imply that the user is obliged to
		provide any information. In the event that the user provides any
		information of a personal nature, the data collected on this website
		will be treated in a fair and lawful manner, subject at all times to
		the principles and rights set forth in Organic Law 15/1999, of 13
		December, Protection of Personal Data (LOPD), and other development
		regulations. <br> - Section "Register user" <br> According
		to Organic Law 15/1999 of December 13, Protection of Personal Data
		(LOPD), we inform you that by filling in the forms, your personal data
		will be incorporated and will be treated in files of
		<jstl:out value="${name }" />
		. The main purpose of this file is the management of registered users
		on our website.
		<jstl:out value="${name }" />
		ensures the confidentiality of the data provided and guarantees that,
		in no case, will be transferred for any other use without the prior
		and express consent of our users. We will only ask you for the data
		necessary to provide the required service and will only be used for
		this purpose. - Information collected oriented to web improvements. <br>
		To improve our online services we are analyzing the
		<jstl:out value="${name }" />
		portal, the correct user navigation and the virtual erasure of
		information. This information will be confidential and will only be
		used to improve the security and functioning of the online portal.


	</p>



	<h2>Cookies Policie</h2>
	<p>
		In our portal, two types of cookies are used.<br>
		<lu>

		<li>User session cookies: designed to collect and store data
			while the user accesses a web page. It is used to store information
			that only interests to keep for the provision of the service
			requested by the user on a single occasion (for example, a list of
			products purchased).</li>
		<li>Language cookies: this is used to save the language preference, while using the portal.</li>

		</lu>
	</p>

</jstl:if>




