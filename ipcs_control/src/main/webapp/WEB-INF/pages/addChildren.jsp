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
<h2 style="text-align: center;">Add Child Details</h2>

<form name="htmlform" method="post" action="/ipcs/persistChildren">
    <table align="center">
        <tr>
            <td><label for="fist_name">First Name</label></td>
            <td><input  type="text" name="fist_name"/></td>
            <td width="150px"></td>
            <td><label for="last_name">Last Name</label></td>
            <td><input  type="text" name="last_name"/></td>
        </tr>

        <tr>
            <td><label for="date_of_birth">Date of Birth </label></td>
            <td><input  type="text" name="date_of_birth"/> </td>
            <td width="150px"></td>
            <td><label for="sex">Gender</label></td>
            <td><input  type="text" name="sex"/></td>
        </tr>


        <tr>
            <td><label for="address">Home Address </label></td>
            <td colspan="4"><input  type="text" name="address"/> </td>
        </tr>

        <tr>
            <td colspan="5" align="center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form>
</body>