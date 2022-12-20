<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Numbers of Houses by Types</title>
</head>
<body>
	<h1>Create Numbers of Houses by Types</h1>
	<form action="housenumbercreate" method="post">
		<p>
			<label for="houseType">HouseType</label>
			<select id="houseType" name="houseType">
            <option value="apartment">Apartment</option>
            <option value="condo">Condo</option>
            <option value="assistedLiving">Assisted Living</option>
            <option value="cottageCabin">Cottage/Cabin</option>
            <option value="house">House</option>
            <option value="duplex">Duplex</option>
            <option value="flat">Flat</option>
            <option value="inLaw">In-law</option>
            <option value="loft">Loft</option>
            <option value="land">Land</option>
            <option value="manufactured">Manufactured</option>
            <option value="townhouse">Townhouse</option>
        	</select>
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<label for="successMessage">House Number: </label>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>