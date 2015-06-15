<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <style type="text/css">
        <%@include file="CSS/style.css" %>
    </style>
    <link rel="stylesheet" href="<c:url value="/resources/jquery/DatePicker/jquery-ui.css"/>">
    <script src="<c:url value="/resources/jquery/DatePicker/jquery.js"/>"></script>
    <script src="<c:url value="/resources/jquery/DatePicker/jquery-ui.js"/>"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker({
                yearRange: "-50:+0",
                changeYear: true
            });
        });
    </script>
</head>
<body>
<h2 style="text-align: center;">
    <c:if test="${operation == 'add'}">Add Staff Details</c:if>
    <c:if test="${operation == 'update'}">Update Staff Details</c:if>
    <c:if test="${operation == 'view'}">View Staff Details</c:if>
</h2>

<form:form name="htmlform" method="POST" action="/ipcs_control/persistStaff?operation=${operation}">
    <table align="center" width="60%">
        <tr>
            <td><form:label cssClass="required" path="personDetail.firstName">First Name</form:label></td>
            <td><form:input path="personDetail.firstName"/></td>
            <td width="150px"><form:hidden path="roles[0].name" value="STAFF"></form:hidden><form:hidden path="objectId"></form:hidden></td>
            <td><form:label cssClass="required" path="personDetail.lastName">Last Name</form:label></td>
            <td><form:input path="personDetail.lastName"/></td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.firstName" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="personDetail.lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label cssClass="required" path="personDetail.dateOfBirth">Date of Birth </form:label></td>
            <td>
                <form:input id="datepicker" path="personDetail.dateOfBirth"/>

            </td>
            <td width="150px"></td>
            <td><form:label cssClass="required" path="personDetail.sex">Gender</form:label></td>
            <td>
                <form:radiobutton path="personDetail.sex" value="MALE"/> Male
                <form:radiobutton path="personDetail.sex" value="FEMALE"/> Female
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.dateOfBirth" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="personDetail.sex" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label cssClass="required" path="personDetail.nationality">Nationality</form:label></td>
            <td colspan="4">
                <form:select path="personDetail.nationality" selected="personDetail.nationality"
                             items="${nationalities}">
                </form:select>
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="personDetail.nationality" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <td colspan="5" align="center">
                <c:if test="${operation != 'view'}"><input type="submit" value="Submit"/></c:if>
                &nbsp; &nbsp; &nbsp;
                <a href="<c:url value='/listStaff' />">
                    <input type="button" value="Back"/>
                </a>
            </td>

        </tr>
    </table>
</form:form>
</body>