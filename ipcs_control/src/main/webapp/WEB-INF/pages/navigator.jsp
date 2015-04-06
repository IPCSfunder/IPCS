<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

</head>
<body>
<table>
<tr>
<td>
<a href="<c:url value='/addStudent' />" >Create Student</a>
</td>

<td>
<a href="<c:url value='/addTeacher' />" >Add Teacher</a>
</td>

<td>
<a href="<c:url value='/listStudent' />" >List Students</a>
</td>

<td>
<a href="<c:url value='/listTeacher' />" >List Teachers</a>
</td>

</tr>

</table>
</body>