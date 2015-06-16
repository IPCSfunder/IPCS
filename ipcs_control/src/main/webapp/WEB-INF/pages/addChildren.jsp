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
    <c:if test="${operation == 'add'}">Add Child Details</c:if>
    <c:if test="${operation == 'update'}">Update Child Details</c:if>
    <c:if test="${operation == 'view'}">View Child Details</c:if>
</h2>

<form:form name="htmlform" method="POST" action="/ipcs_control/persistChild?operation=${operation}" commandName="child">
    <table align="center" width="60%">

        <tr>
            <td><form:label cssClass="required" path="personDetail.firstName">First Name</form:label></td>
            <td><form:input path="personDetail.firstName"/></td>
            <td width="150px"><form:hidden path="roles[0].name" value="CHILDREN"></form:hidden><form:hidden
                    path="objectId"></form:hidden></td>
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
            <td><form:label cssClass="required" path="personDetail.address">Address</form:label></td>
            <td colspan="4">
                <form:input size="60%"  path="personDetail.address"/>
            </td>
        </tr>

        <tr>
            <td colspan="5"><form:errors path="personDetail.address" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label cssClass="required" path="personDetail.postcode">PostCode</form:label></td>
            <td colspan="4">
                <form:input path="personDetail.postcode"/>
            </td>
        </tr>

        <tr>
            <td colspan="5"><form:errors path="personDetail.postcode" cssClass="error"/></td>
        </tr>
    </table>

    <br>
    <hr>
    <br>

    <table align="center" width="60%">
        <tr>
            <td></td>
            <td>Name</td>
            <td>Relationship</td>
            <td colspan="2">Mobile</td>

        </tr>

        <tr>
            <td><form:label cssClass="required" path="contacts[0].contacterName">Primary Contact</form:label><form:hidden
                    path="contacts[0].objectId"></form:hidden></td>
            <td><form:input path="contacts[0].contacterName"/></td>
            <td>
                <form:select path="contacts[0].relationshipType.name" selected="contacts[0].relationshipType.name" items="${relationshipTypes}" itemLabel="name" itemValue="name">
                </form:select>
                <form:hidden path="contacts[0].primary" value="true"></form:hidden>
            </td>
            <td colspan="2"><form:input path="contacts[0].mobileNumber"/></td>
        </tr>

        <tr>
        <td colspan="2"><form:errors path="contacts[0].contacterName" cssClass="error"/></td>
        <td></td>
        <td colspan="2"><form:errors path="contacts[0].mobileNumber" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="contacts[1].contacterName">Secondary Contact</form:label><form:hidden
                    path="contacts[1].objectId"></form:hidden></td>
            <td><form:input path="contacts[1].contacterName"/></td>
            <td>
                <form:select path="contacts[1].relationshipType.name" selected="contacts[1].relationshipType.name" items="${relationshipTypes}" itemLabel="name" itemValue="name">
                </form:select>
                <form:hidden path="contacts[1].primary" value="false"></form:hidden>
            </td>
            <td colspan="2"><form:input path="contacts[1].mobileNumber"/></td>
        </tr>

        <tr>
            <td colspan="2"><form:errors path="contacts[1].contacterName" cssClass="error"/></td>
            <td></td>
            <td colspan="2"><form:errors path="contacts[1].mobileNumber" cssClass="error"/></td>
        </tr>
    </table>

    <br>
    <hr>
    <br>

    <table align="center" width="60%">


        <tr>
            <td><form:label path="activities">Class</form:label></td>
            <td>

                <form:select multiple="true" path="activities">
                <form:options items="${classes}" itemValue="objectId" itemLabel="name"/>
                </form:select>
            </td>
            <td width="150px"></td>
            <td>
            </td>
        </tr>
        <tr>
            <td colspan="2"><form:errors path="activities" cssClass="error"/></td>
            <td></td>
            <td colspan="2"></td>
        </tr>

        <tr>
            <td colspan="5" align="center">
                <c:if test="${operation != 'view'}"><input type="submit" value="Submit"/></c:if>
                &nbsp; &nbsp; &nbsp;
                <a href="<c:url value='/listChildren' />">
                    <input type="button" value="Back"/>
                </a>
            </td>
        </tr>
    </table>
</form:form>
</body>