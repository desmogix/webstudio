<%-- 
    Document   : login
    Created on : 31-Aug-2015, 11:28:39
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
        <title>JSP Page</title>
        <%@include file="/jspf/home.jspf"%>
        <%@include file="/jspf/alert.jspf"%> 
        <%@include file="/jspf/service.jspf"%> 
        <%@include file="/jspf/blog.jspf"%> 
        <%@include file="/jspf/login.jspf"%>
    </head>
    <body>
        <h1>Web Studio Everyday Service</h1>
        <h2>Login</h2>
        
        
        
        <c:url value="/account/getRegister" var="getRegisterURL" />
        <c:url value="/jsp/view/login_1.jsp" var="getAuthenticationURL" />
        
            <table border="0">
                <form:form action="${getRegisterURL}" method="get">
                    <tr><td>Click on Register to create a new account</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td align="middle"><input type="submit" value="Register"> </td></tr>  
                </form:form>
                <form:form action="${getAuthenticationURL}" method="get">
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>Click on Login to access to your profile</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td align="middle"><input type="submit" value="Login"></td></tr> 
                </form:form>  
            </table>            
        
            
            
    </body>
</html>
