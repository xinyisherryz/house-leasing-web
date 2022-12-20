<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="finduser" method="post">
		<h1>Search for a User by userId</h1>
		<p>
			<label for=userId>User Id</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>User Id</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Age</th>
            </tr>
            
            <tr>
                <td><c:out value="${user.getUserId()}" /></td>
                <td><c:out value="${user.getFirstName()}" /></td>
                <td><c:out value="${user.getLastName()}" /></td>
                <td><c:out value="${user.getPhone()}" /></td>
                <td><c:out value="${user.getEmail()}" /></td>
                <td><c:out value="${user.getGender()}" /></td>
                <td><c:out value="${user.getAge()}" /></td>
                <!--  go to servlet/FavoriteHouseList.java-->
                <td><a href="favoritehouselist?userId=<c:out value="${user.getUserId()}"/>">Favorite Houses</a></td>
                <!--  go to servlet/UserDelete.java-->
                <td><a href="userdelete?userId=<c:out value="${user.getUserId()}"/>">Delete</a></td>
                <!--  go to servlet/UserUpdate.java-->
                <td><a href="userupdate?userId=<c:out value="${user.getUserId()}"/>">Update</a></td>
            </tr>   
       </table>
</body>
</html>
