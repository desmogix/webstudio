
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
        <title>Sign In WSEDS</title>    
        <c:import url="/jsp/header.jsp" />
    </head>
    <body>        
        <h1>Web Studio Everyday Service</h1>
        <h2>Register</h2>
        <h3>Please insert your details</h3> 
        <!-- Register an account. -->    

        <c:url value="/public/register/postregister" var="postRegisterURL" />
        <form:form action="${postRegisterURL}" method="post" modelAttribute="registrationForm">                                           
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
                    <td align="left"><form:label path="credentials.username">Email</form:label></td>  
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
                    <td><font color="green"> <a style="text-decoration:none; color:inherit; font-size:30px">${yeah}</a>   </font></td>  
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register" /></td>  
                    <td></td>
                </tr>
            </table>
        </form:form>
         
        
         
    </body>
</html>
