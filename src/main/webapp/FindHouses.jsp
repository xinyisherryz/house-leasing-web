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
	<form action="findhouses" method="post">
		<h1>Search for a House by type</h1>
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
	<br/>
	
	<h1>Matching Houses</h1>
        <table border="1">
            <tr>
                <th>House Id</th>
                <th>Url</th>
                <th>Region Id</th>
                <th>Region Url</th>
                <th>Price</th>
                <th>House Type</th>
                <th>Sq Feet</th>
                <th>Beds</th>
                <th>Baths</th>
                <th>House Id</th>
                <th>Cats Allowed</th>
                <th>Dogs Allowed</th>
                <th>Smoking Allowed</th>
                <th>House Id</th>
                <th>Wheel Chair Access</th>
                <th>Electric Vehicle Charge</th>
                <th>Comes Furnished</th>
                <th>Laundry Option</th>
                <th>Parking Option</th>
                <th>Img Url</th>
                <th>Description</th>
                <th>Lat</th>
                <th>Lons</th>
                <th>State</th>
            </tr>
            
            <c:forEach items="${houseList}" var="house" >
                <tr>
                    <td><c:out value="${house.getHouseId()}" /></td>
                    <td><c:out value="${house.getUrl()}" /></td>
                    <td><c:out value="${house.getRegion()}" /></td>
                    <td><c:out value="${house.getRegionUrl()}" /></td>
                    <td><c:out value="${house.getPrice()}"/></td>
                    <td><c:out value="${house.getHouseType().name()}" /></td>
                    <td><c:out value="${house.getSqFeet()}" /></td>
                    <td><c:out value="${house.getBeds()}" /></td>
                    <td><c:out value="${house.getBaths()}" /></td>
                    <td>
                    	<c:choose>
						    <c:when test="${house.isCatsAllowed()}">
						        Allowed
						        <br />
						    </c:when>    
						    <c:otherwise>
						        Not Allowed 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td>
                    	<c:choose>
						    <c:when test="${house.isDogsAllowed()}">
						        Allowed
						        <br />
						    </c:when>    
						    <c:otherwise>
						        Not Allowed 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td>
                    	<c:choose>
						    <c:when test="${house.isSmokingAllowed()}">
						        Allowed
						        <br />
						    </c:when>    
						    <c:otherwise>
						        Not Allowed 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td>
                    	<c:choose>
						    <c:when test="${house.isWheelchairAccess()}">
						        Yes
						        <br />
						    </c:when>    
						    <c:otherwise>
						        No 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td>
                    	<c:choose>
						    <c:when test="${house.isElectricVehicleCharge()}">
						        Yes
						        <br />
						    </c:when>    
						    <c:otherwise>
						        No 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td>
                    	<c:choose>
						    <c:when test="${house.isComesFurnished()}">
						        Yes
						        <br />
						    </c:when>    
						    <c:otherwise>
						        No 
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
					<td><c:out value="${house.getLaundryOption().name()}" /></td>
					<td><c:out value="${house.getParkingOption().name()}" /></td>
					<td><c:out value="${house.getImgUrl()}" /></td>
					<td><c:out value="${house.getDescription()}" /></td>
					<td><c:out value="${house.getLat()}" /></td>
					<td><c:out value="${house.getLon()}" /></td>
					<td><c:out value="${house.getState()}" /></td>
                </tr>
            </c:forEach>
            <p>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
       </table>
</body>
</html>
