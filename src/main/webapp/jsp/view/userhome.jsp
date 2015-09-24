<%-- 
    Document   : user_home
    Created on : 20-Sep-2015, 10:19:33
    Author     : luigi@santivetti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${username} WSEDS</title>
        <c:import url="/jsp/header.jsp" />
    </head>
    <body>
        <h1>Web Studio Everyday Service</h1>
        <h2>Personal profile</h2>
        <h3>Welcome, ${username}.</h3>
        <%--
        <c:url value="/public/services" var="ServicesURL" />
        <h4><a href="${ServicesURL}">Services</a></h4>
       
        <c:out value="${pageContext.request.contextPath}" />
        <h4><a href="${pageContext.request.contextPath}${linkToHome}">Home</a></h4>
        --%>
        
    </body>
</html>
