<%-- 
    Document   : service
    Created on : 31-Aug-2015, 11:27:19
    Author     : luigi@santivetti
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Services WSEDS</title>
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
        <h2>Services</h2>
    </body>
</html>
