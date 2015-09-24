
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page language="java" contentType="text/html;charset=UTF-8"%>


<!-- 
    Document   : register
    Created on : 22-Aug-2015, 09:23:33
    Author     : guyfox
-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">         
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
        <title>Sign WSEDS</title>    
    </head>
    <body>        
        <h1>Web Studio Everyday Service</h1>
        <h2>Register</h2>
        <h3>Please insert your details</h3> 
        <!-- Register an account. -->    

        <c:url value="/register/postRegister" var="postRegisterURL" />
        <form:form id="postRegister" action="${postRegisterURL}" method="post" modelAttribute="registrationForm">                                           
            <table border="0">
                <tr>
                    <td align="left"><form:label path="account.name">Name</form:label></td>  
                    <td><form:input path="account.name" /></td>
                    <td><font color="red"><form:errors path="account.name" /></font></td>
                </tr>
               <tr>
                    <td align="left"><form:label path="account.surname">Surname</form:label></td>  
                    <td><form:input path="account.surname" /></td>
                    <td><font color="red"><form:errors path="account.surname" /></font></td>
                </tr>
                
                <tr>
                    <td align="left"><form:label path="account.email">Email</form:label></td>   
                    <td><form:input path="account.email" /></td>
                    <td><font color="red"><form:errors path="account.email" /></font></td>
                </tr>
               
                <tr>
                    <td align="left"><form:label path="credentials.username">Username</form:label></td>  
                    <td><form:input path="credentials.username" /></td>
                    <td><font color="red"><form:errors path="credentials.username" /></font></td>
                </tr>
               <tr>
                    <td align="left"><form:label path="credentials.password">Password</form:label></td>  
                    <td><form:password path="credentials.password" /></td>
                    <td><font color="red"><form:errors path="credentials.password" /></font></td>
                </tr>
                <tr>
                    <td></td>
                    <td>${yeah}</td>  
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register">&nbsp;<input type="Reset" value="Reset"></td>  
                    <td></td>
                </tr>
            </table>
        </form:form>
         
        
        <p>
            I though registration process being part of a spring-web-flow.
            By the way, Account and User credentials are mapped against two different tables.
            Account is  referred through FK UserCred_id.
        </p> 
    </body>
</html>
