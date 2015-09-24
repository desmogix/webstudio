
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page language="java" contentType="text/html;charset=UTF-8"%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <b><%@include file="/jspf/home.jspf"%></b>
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
        <title>Home WSEDS</title>
    </head>

    <body>
        
        <h1>Web Studio Everyday Service</h1>
        <h2>Home ${pageContext.request.userPrincipal.name}</h2>
        <h3>Select from the menu above your destination</h3>
        
        
        
    </body>
</html>
