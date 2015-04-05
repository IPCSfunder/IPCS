<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Admin Login</h2>
<form:form method="POST" action="/ipcs/adminLogin">
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
</html>