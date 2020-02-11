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
  <c:if test="${not empty param.message}">
    <p style="color:red"><c:out value="${param.message}" /></p>
  </c:if>

  <form:form action="authorize" modelAttribute="logPass">
    Login: <form:input path="login"/><br/>
    Password: <form:password path="password"/><br/>
    <input type="submit" value="submit"/>
  </form:form>
  
  

  <a href="registration">registration</a>
</body>
</html>