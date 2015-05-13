<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
<h2>Customer SignUp Form - JSR303 @Valid example</h2>
<form:form action="signup" method="POST" commandName="person">
	<form:errors path="*" cssClass="errorBlock" element="div"/>
	<table>
		<tr>
			<td> <spring:message code="label.person.name"/></td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name" cssClass="error" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.person.age"/></td>
			<td><form:input path="age"/></td>
			<td><form:errors path="age" cssClass="error" /></td>
		</tr>
		<tr>
				<td colspan="3"><input type="submit" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>