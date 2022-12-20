<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Buyer</title>
</head>
<body>
	<h1>Create Buyer</h1>
	<form action="buyercreate" method="post">
		<p>
			<label for="firstName">FirstName</label>
			<input id="firstName" name="firstName" value="">
		</p>
		<p>
			<label for="lastName">LastName</label>
			<input id="lastName" name="lastName" value="">
		</p>
		<p>
			<label for="phone">Phone</label>
			<input id="phone" name="phone" value="">
		</p>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<label for="gender">Gender</label>
			<select id="gender" name="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
            </select>
		</p>
		<p>
			<label for="age">Age</label>
			<input id="age" name="age" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>