<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
  <c:if test="${not empty message}">
    <p style="color:red"><c:out value="${message}" /></p>
  </c:if>
  <form:form action="register" modelAttribute="userLogPass">
    Name: <form:input path="user.name"/><br/>
    Surname: <form:input path="user.surname"/><br/>
    Login: <form:input path="logPass.login"/><br/>
    Password: <form:password path="logPass.password"/><br/>
    <input type="submit" value="submit"/>
  </form:form>
  
  
</body>
</html>