<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

</head>
<body>
<form:form method="POST" action="/ipcs_control/persistTeacher">
   <table>
    <tr>
        <td><form:label path="account_name">Name</form:label></td>
        <td><form:input path="account_name" /></td>
    </tr>
    <tr>
        <td><form:label path="password_hash">Password</form:label></td>
        <td><form:input path="password_hash" /></td>
    </tr>    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>