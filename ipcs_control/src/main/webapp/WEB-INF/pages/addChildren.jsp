<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <style type="text/css">
        <%@include file="CSS/style.css" %>
    </style>
    <link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
    <script src="<c:url value="/resources/jquery/jquery.js"/>"></script>
    <script src="<c:url value="/resources/jquery/jquery-ui.js"/>"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>
</head>
<body>
<h2 style="text-align: center;">
    <c:if test="${operation == 'add'}">Add Child Details</c:if>
    <c:if test="${operation == 'update'}">Update Child Details</c:if>
</h2>

<form:form name="htmlform" method="POST" action="/ipcs_control/persistChild?operation=${operation}">
    <table align="center" width="50%">
        <tr>
            <td><form:label path="personDetail.firstName">First Name</form:label></td>
            <td><form:input path="personDetail.firstName"/></td>
            <td width="150px"><form:hidden path="roles[0].name" value="CHILDREN"></form:hidden><form:hidden path="objectId"></form:hidden></td>
            <td><form:label path="personDetail.lastName">Last Name</form:label></td>
            <td><form:input path="personDetail.lastName"/></td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.firstName" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="personDetail.lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="personDetail.dateOfBirth">Date of Birth </form:label></td>
            <td>
                <form:input id="datepicker" path="personDetail.dateOfBirth"/>

            </td>
            <td width="150px"></td>
            <td><form:label path="personDetail.sex">Gender</form:label></td>
            <td>
                <form:radiobutton path="personDetail.sex" value="MALE"/> Male
                <form:radiobutton path="personDetail.sex" value="FEMALE"/> Female
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.sex" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <td><form:label path="personDetail.age">Age</form:label></td>
            <td><form:input path="personDetail.age"/></td>
            <td width="150px"></td>
            <td><form:label path="personDetail.nationality">Nationality</form:label></td>
            <td>
                <form:select path="personDetail.nationality">
                    <form:option value="Singapore" selected="selected">Singapore</form:option>
                    <form:option value="China">China</form:option>
                    <form:option value="America">America</form:option>
                </form:select>

            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.age" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"></td>
        </tr>
    </table>

    <br>
    <hr>
    <br>

    <table align="center" width="50%">
        <tr>
            <td></td>
            <td>Name</td>
            <td>Relationship</td>
            <td colspan="2">Mobile</td>

        </tr>

        <tr>
            <td><form:label path="contacts[0].contacterName">Primary Contact</form:label><form:hidden path="contacts[0].objectId"></form:hidden></td>
            <td><form:input path="contacts[0].contacterName"/></td>
            <td>
                <form:select path="contacts[0].relationshipType.name">
                    <form:option value="TEACHER" selected="selected">Teacher</form:option>
                    <form:option value="PARENT">Parent</form:option>
                    <!--<form:option value="FATHER">Fatyher</form:option>
                    <form:option value="GRANDMOTHER">Grandmother</form:option>
                    <form:option value="GRANDFATHER">Grandfather</form:option>-->
                </form:select>
                <form:hidden path="contacts[0].primary" value="true"></form:hidden>
            </td colspan="2">
            <td><form:input path="contacts[0].mobileNumber"/></td>
        </tr>


        <tr>
            <td><form:label path="contacts[1].contacterName">Secondary Contact</form:label><form:hidden path="contacts[1].objectId"></form:hidden></td>
            <td><form:input path="contacts[1].contacterName"/></td>
            <td>
                <form:select path="contacts[1].relationshipType.name">
                    <form:option value="TEACHER" selected="selected">Teacher</form:option>
                    <form:option value="PARENT">Parent</form:option>
                </form:select>
                <form:hidden path="contacts[1].primary" value="false"></form:hidden>
            </td>
            <td colspan="2"><form:input path="contacts[1].mobileNumber"/></td>
        </tr>

        <tr>
            <td colspan="5" align="center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>

    </table>
</form:form>
</body>