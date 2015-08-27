
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
                    <td align="left"><form:label path="${account.name}">Name</form:label></td>  
                    <td><form:input path="${account.name}" /></td>
                    <td><font color="red"><form:errors path="${account.name}" /></font></td>
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
                    <td><form:input path="user.password" /></td>
                    <td><font color="red"><form:errors path="user.password" /></font></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register">&nbsp;<input type="Reset" value="Reset"></td>  
                    <td></td>
                </tr>
            </table>
        </form:form>
        
        
        <p>${registrationForm.account.name}</p>
        <p>${registrationForm.account.surname}</p>
        <p>${registrationForm.account.email}</p>
        ---------------
        <p>${registrationForm.user.username}</p>
        <p>${registrationForm.user.password}</p>
        ---------------
        +++++++++++++++
        ---------------
        <p>address: ${registrationForm.user}</p>
        <p>address: ${user}</p>
        <p>address: ${registrationForm.account}</p>
        <p>address: ${account}</p>
        
               <%--
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
          --%>
        
        
        <%--
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
        
        
        
        
        <tr>
                    <spring:bind path = "account.name">
                        <form method = "post">
                            <spring:bind path = "account.name">
                                <td align="left"><label path="account.name">Name</label></td>
                                <td><input name = "account.name" value = "${status.displayValue}" /></td>
                                <td><font color="red"><errors path="account.name" /></font></td>
                            </spring:bind>
                        </form>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path = "account.surname">
                        <form method = "post">
                            <td align="left"><label path="account.surname">Surname</label></td>
                            <td><input name = "account.surname" value = "${status.displayValue}" /></td>
                            <td><font color="red"><errors path="account.surname" /></font></td>
                        </form>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path = "account.email">
                        <form method = "post">
                            <td align="left"><label path="email">Email</label></td>
                            <td><input name = "account.email" value = "${status.displayValue}" /></td>
                            <td><font color="red"><errors path="account.email" /></font></td>
                        </form>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path = "user.username">
                        <form method = "post">
                            <td align="left"><label path="user.username">Username</label></td>
                            <td><input name = "user.username" value = "${status.displayValue}" /></td>
                            <td><font color="red"><errors path="user.username" /></font></td>
                        </form>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path = "user.password">
                        <form method = "post">
                            <td align="left"><label path="user.password">Password</label></td>
                            <td><input name = "user.password" value = "${status.displayValue}" /></td>
                            <td><font color="red"><errors path="user.password" /></font></td>
                        </form>
                    </spring:bind>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register">&nbsp;<input type="Reset" value="Reset"></td>  
                    <td></td>
                </tr>
        
        
        
        
        
        --%>
        
        
        
    </body>
</html>
