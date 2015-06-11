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

    <link rel="stylesheet" href="<c:url value="/resources/jquery/DatetimePicker/jquery.datetimepicker.css"/>">
    <script src="<c:url value="/resources/jquery/DatetimePicker/jquery.datetimepicker.js"/>"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
            $("#datetimepicker").datetimepicker({
            });
            $("#datetimepicker2").datetimepicker({
            });
        });
    </script>
</head>
<body>
<h2 style="text-align: center;">
    <c:if test="${operation == 'add'}">Add Activity Details</c:if>
    <c:if test="${operation == 'update'}">Update Activity Details</c:if>
    <c:if test="${operation == 'view'}">View Activity Details</c:if>
</h2>

<form:form method="POST" action="/ipcs_control/persistActivity?operation=${operation}" commandName="activity">
    <table align="center" width="60%">
        <tr>
            <td><form:label path="name">Activity</form:label></td>
            <td><form:input path="name"/></td>
            <td width="150px"><form:hidden path="objectId"></form:hidden></td>
            <td>Activity Type</td>
            <td>
                <form:select path="activityType.name" selected="activityType.name" items="${activityTypes}" itemLabel="name" itemValue="name">
                </form:select>
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="name" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="activityType.name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="location">Location</form:label></td>
            <td>
                <form:input path="location"/>

            </td>
            <td width="150px"></td>
            <td><form:label path="host.account_name">Teacher</form:label></td>
            <td>
                <form:select path="host.account_name" selected="host.account_name" items="${teachers}" itemLabel="account_name" itemValue="account_name">
                </form:select>
            </td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="location" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="host.account_name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="startTime">Start Date</form:label></td>
            <td><form:input id ="datetimepicker" path="startTime"/></td>
            <td width="150px"></td>
            <td><form:label path="endTime">End Time</form:label></td>
            <td><form:input id ="datetimepicker2"  path="endTime"/></td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="startTime" cssClass="error"/></td>
            <td width="150px"></td>
            <td colspan="2"><form:errors path="endTime" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="5" align="center">
                <c:if test="${operation != 'view'}"><input type="submit" value="Submit"/></c:if>
                &nbsp; &nbsp; &nbsp;
                <a href="<c:url value='/listActivity' />">
                    <input type="button" value="Back"/>
                </a>
            </td>
        </tr>
    </table>
</form:form>
</body>