<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Favorite House List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>House Id</th>
                <th>Url</th>
                <th>Region</th>
                <th>Region Url</th>
                <th>Price</th>
                <th>House Type</th>
                <th>sqFeet</th>
                <th>beds</th>
                <th>baths</th>
                <th>Cats Allowed</th>
                <th>Dogs Allowed</th>
                <th>SmokingAllowed</th>
                <th>Wheelchair Access</th>
                <th>Electric Vehicle Charge</th>
                <th>Comes Furnished</th>
                <th>Laundry Option</th>
                <th>Parking Option</th>
                <th>Img Url</th>
                <th>Description</th>
                <th>Lat</th>
                <th>Lon</th>
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
					<td><a href="favoritehousedelete?userId=<c:out value="${messages.userId}"/>&houseId=<c:out value="${house.getHouseId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
