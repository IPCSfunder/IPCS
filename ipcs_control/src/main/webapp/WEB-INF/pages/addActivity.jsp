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
    <c:if test="${operation == 'add'}">Add Activity Details</c:if>
    <c:if test="${operation == 'update'}">Update Activity Details</c:if>
</h2>

<form:form method="POST" action="/ipcs_control/persistActivity?operation=${operation}" commandName="activity">
    <table align="center" width="50%">
        <tr>
            <td><form:label path="name">Activity</form:label></td>
            <td><form:input path="name"/></td>
            <td width="150px"><form:hidden path="objectId"></form:hidden></td>
            <td><form:label path="host.account_name">Teacher</form:label></td>
            <td><form:input path="host.account_name"/></td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="name" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="host.account_name" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="1"><form:label path="location">Location</form:label></td>
            <td colspan="4"  with="50%">
                <form:input path="location"/>

            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="location" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <td><form:label path="startTime">Time</form:label></td>
            <td><form:input id ="datepicker" path="startTime"/></td>
            <td width="150px"></td>
            <td></td>
            <td>
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="startTime" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"></td>


        <tr>
            <td colspan="5" align="center">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>