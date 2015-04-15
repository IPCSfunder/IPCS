<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <style type="text/css">
        <%@include file="CSS/style.css" %>
    </style>
</head>
<body>
<form:form method="POST" action="/ipcs/persistChildren">
   <table align="center">
    <tr>
        <td><form:label path="account_name">Name</form:label></td>
        <td><form:input path="account_name" /></td>

        <td><form:label path="password_hash">Password</form:label></td>
        <td><form:input path="password_hash" /></td>
    </tr>
    <tr>
        <td><form:label path="roles[0].name">Role</form:label></td>
        <td><form:input path="roles[0].name" /></td>

        <td><form:label path="schools[0].name">School</form:label></td>
        <td><form:input path="schools[0].name" /></td>
    </tr>    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>