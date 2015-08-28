
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
                    
        <title>WSEDS: please enter your details to register.</title>    
    </head>
    <body>        
        <h1>WSEDS: please enter your details to register.</h1>
         
        <!-- Register a user (account). -->    

        <c:url value="/user/postRegister" var="postRegisterURL" />
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
                    <td align="left"><form:label path="user.username">Username</form:label></td>  
                    <td><form:input path="user.username" /></td>
                    <td><font color="red"><form:errors path="user.username" /></font></td>
                </tr>
               <tr>
                    <td align="left"><form:label path="user.password">Password</form:label></td>  
                    <td><form:password path="user.password" /></td>
                    <td><font color="red"><form:errors path="user.password" /></font></td>
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
