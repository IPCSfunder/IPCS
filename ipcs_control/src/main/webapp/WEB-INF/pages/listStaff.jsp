<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<style type="text/css">
		<%@include file="CSS/style.css" %>
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<div style="text-align: center;">Children List</div>

<br/>
<br/>

<div align="center"><a href="<c:url value='/addStaff' />"><button>Add</button></a></div>

<br/>
<br/>


   <table align="center" class="tg">
   <tr>
		<th width="200">Staff Name</th>
		<th width="200">Class</th>
	    <th width="200">Operation</th>
	</tr>
   
   <c:forEach items="${command}" var="person">
		<tr>
			<td>${person.account_name}</td>
			<td>${person.account_name}</td>
			<td><a href="<c:url value='/addStaff?account_name=${person.account_name}'/>">View Details</a></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>