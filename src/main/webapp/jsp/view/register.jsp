
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
        <c:url value="/user/postAccountRegister" var="postRegisterURL" />
        <form:form id="postAccountRegister" action="${postRegisterURL}" method="post" modelAttribute="account">                                           
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
        </form:form>
                
        <c:url value="/user/postUserRegister" var="postRegisterURL" />
        <form:form id="postUserRegister" action="${postRegisterURL}" method="post" modelAttribute="user">
                <tr>
                    <td align="left"><form:label path="username">Username</form:label></td>  
                    <td><form:input path="username" /></td>
                    <td><font color="red"><form:errors path="username" /></font></td>
                </tr>
               <tr>
                    <td align="left"><form:label path="password">Password</form:label></td>  
                    <td><form:input path="password" /></td>
                    <td><font color="red"><form:errors path="password" /></font></td>
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
