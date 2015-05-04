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
<div align="center">
<ul>
    <li><a href="<c:url value='/listChildren' />">Children</a></li>
    <li><a href="<c:url value='/listStaff' />">Staff</a></li>
    <li><a href="<c:url value='/listMessage' />">Message</a></li>
    <li><a href="<c:url value='/listActivity' />">Schedule</a></li>
</ul>
</div>
</body>