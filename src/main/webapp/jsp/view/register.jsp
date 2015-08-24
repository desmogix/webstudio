<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- 
    Document   : register
    Created on : 22-Aug-2015, 09:23:33
    Author     : guyfox
-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">         
        <%@include file="/jspf/home.jspf"%>  
                    
        <title>WSEDS: please enter your details to register.</title>    
    </head>
    <body>        
        <h1>WSEDS: please enter your details to register.</h1>
         
        <!-- Register an account. -->    
        <c:url value="/account/postRegister" var="postRegisterURL" />
        <form:form id="postRegister" action="${postRegisterURL}" method="post" modelAttribute="account">                                           
            <table border="0">
                <tr>
                    <td align="left"><form:label path="name">Name</form:label></td>  
                    <td><form:input path="name" /></td>
                    <td><font color="red"><form:errors path="name" /></font></td>
                </tr>
               <tr>
                    <td align="left"><form:label path="surname">Surname</form:label></td>  
                    <td><form:input path="surname" /></td>
                    <td><font color="red"><form:errors path="surname" /></font></td>
                </tr>
                
                <tr>
                    <td align="left"><form:label path="email">Email</form:label></td>   
                    <td><form:input path="email" /></td>
                    <td><font color="red"><form:errors path="email" /></font></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register">&nbsp;<input type="Reset" value="Reset"></td>  
                    <td></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
