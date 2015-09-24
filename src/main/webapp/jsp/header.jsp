<%-- 
    Document   : header
    Created on : 22-Sep-2015, 19:00:20
    Author     : luigi@santivetti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header file</title>
        
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
</html>