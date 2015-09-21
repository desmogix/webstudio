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
                <title>Sign in</title>
                <%@include file="/jspf/home.jspf"%>
                <%@include file="/jspf/alert.jspf"%> 
                <%@include file="/jspf/service.jspf"%> 
                <%@include file="/jspf/blog.jspf"%> 
                <b><%@include file="/jspf/login.jspf"%></b>
                <%@include file="/jspf/register.jspf"%>
        </head>


        <body>
                <h1>Web Studio Everyday Service</h1>
                <h2>Login</h2>

                <c:url value="/login" var="postSignInURL" />
                <form:form id="postLogin" action="${postSignInURL}" method="post" modelAttribute="credentials" >
                    <table>
                        <c:if test="${(param.error)!=null }"> <tr><td></td><td align="middle"><b><font color="red">Bad credentials</font></b></td></tr></c:if>
                            <tr>
                                <td align="left"><form:label id="j_username" path="username">Username</form:label></td>  
                                <td><form:input id="username"  path="username" /></td>
                            </tr>
                            <tr>
                                <td align="left"><form:label id="j_password" path="password">Password</form:label></td>  
                                <td><form:password id="password"  path="password" /></td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> </td>
                                <td align="middle"><input type="submit" value="Sign in"></td>
                            </tr>      
                    </table>
                </form:form>
        
        </body>
</html>
