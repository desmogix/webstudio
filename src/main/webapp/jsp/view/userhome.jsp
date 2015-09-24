<%-- 
    Document   : user_home
    Created on : 20-Sep-2015, 10:19:33
    Author     : luigi@santivetti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${username} WSEDS</title>
        <%@include file="/jspf/home.jspf"%>
        <%@include file="/jspf/alerts.jspf"%> 
        <%@include file="/jspf/services.jspf"%> 
        <%@include file="/jspf/blog.jspf"%> 
        
        <%@include file="/jspf/register.jspf"%>
        <c:if test="${pageContext.request.userPrincipal.name == 'desmogix'}">
                    <%@include file="/jspf/logout.jspf"%>
                    <%@include file="/jspf/userhome.jspf"%>
                </c:if>
              <c:if test="${pageContext.request.userPrincipal.name != 'desmogix'}">
                    <%@include file="/jspf/login.jspf"%>
                </c:if>
        
    </head>
    <body>
        <h1>Web Studio Everyday Service</h1>
        <h2>Personal profile</h2>
        <h3>Welcome, ${username}.</h3>
        <c:url value="/services" var="ServicesURL" />
        <h4><a href="${ServicesURL}">Services</a></h4>
        
    </body>
</html>
