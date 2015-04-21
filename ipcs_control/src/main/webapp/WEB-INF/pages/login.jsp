<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <style type="text/css">
        <%@include file="CSS/style.css" %>
    </style>
</head>
<body>
<div style="text-align: center;"> Integrated Preschool Connectivity System</div>
<h2 style="text-align: center;">Admi1n Login</h2>
<div align="center">
<form:form method="POST" action="/ipcs_control/adminLogin">
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
        <td align="center" colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
</body>
</html>