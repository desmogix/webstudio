<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/jspf/home.jspf"%>  
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        
        
        
        <p>Request a secure login page <a href="index.jsp">index</a></p>
        
        <p>To register with the library, please <i>click</i> the button below:</p>       
        <c:url value="/user/getRegister" var="getRegisterURL" />
        <form:form action="${getRegisterURL}" method="get">   
            <input type="submit" value="Register">                
        </form:form>  
        
        
      
    </body>
</html>
