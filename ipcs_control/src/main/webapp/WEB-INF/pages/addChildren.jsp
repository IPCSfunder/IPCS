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

<form name="htmlform" method="post" action="/ipcs_control/persistChildren">
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
            <td><label for="age">Age</label></td>
            <td><input  type="text" name="age"/> </td>
            <td width="150px"></td>
            <td><label for="nationality">Nationality</label></td>
            <td><input  type="text" name="nationality"/></td>
        </tr>


        <tr>

            <td><label for="address">Home Address </label></td>
            <td colspan="4"><input  type="text" name="address"/> </td>
        </tr>
    </table>
    <hr />
    <table align="center">
        <tr>
            <td></td>
            <td>Name</td>
            <td>Relationship</td>
            <td>Mobile</td>
        </tr>

        <tr>
            <td><label for="primary_contact">Primary Contact</label></td>
            <td><input  type="text" name="primary_contact"/> </td>
            <td><input  type="text" name="primary_relationship"/> </td>
            <td><input  type="text" name="primary_mobile_number"/> </td>
        </tr>

        <tr>
            <td><label for="secondary_contact">Second Contact</label></td>
            <td><input  type="text" name="secondary_contact"/> </td>
            <td><input  type="text" name="secondary_relationship"/> </td>
            <td><input  type="text" name="secondary_mobile_number"/> </td>
        </tr>


        <tr>
            <td><label for="teacher">Teacher</label></td>
            <td><input  type="text" name="teacher"/> </td>
            <td></td>
            <td></td>
        </tr>

        <tr>
            <td colspan="4" align="center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form>
</body>