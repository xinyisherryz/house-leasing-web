<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>Create House</h1>
	<form action="housecreate" method="post">
		<p>
			<label for="url">URL</label>
			<input id="url" name="url" value="">
		</p>
		<p>
			<label for="region">Region</label>
			<input id="region" name="region" value="">
		</p>
		<p>
			<label for="regionUrl">RegionUrl</label>
			<input id="regionUrl" name="regionUrl" value="">
		</p>
		<p>
			<label for="price">Price</label>
			<input id="price" name="price" value="">
		</p>
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
			<label for="sqFeet">SqFeet</label>
			<input id="sqFeet" name="sqFeet" value="">
		</p>
		<p>
			<label for="beds">Beds</label>
			<input id="beds" name="beds" value="">
		</p>
		<p>
			<label for="baths">Baths</label>
			<input id="baths" name="baths" value="">
		</p>
		<p>
			<label for="catsAllowed">Cats Allowed</label>
			<select id="catsAllowed" name="catsAllowed">
            <option value=true>Allowed</option>
            <option value=false>Not Allowed</option>
            </select>
		</p>
		<p>
			<label for="dogsAllowed">Dogs Allowed</label>
			<select id="dogsAllowed" name="dogsAllowed">
            <option value=true>Allowed</option>
            <option value=false>Not Allowed</option>
            </select>
		</p>
		<p>
			<label for="smokingAllowed">Smoking Allowed</label>
			<select id="smokingAllowed" name="smokingAllowed">
            <option value=true>Allowed</option>
            <option value=false>Not Allowed</option>
            </select>
		</p>
		<p>
			<label for="wheelchairAccess">Wheelchair Access</label>
			<select id="wheelchairAccess" name="wheelchairAccess">
            <option value=true>Yes</option>
            <option value=false>No</option>
            </select>
		</p>
		<p>
			<label for="electricVehicleCharge">ElectricVehicle Charge</label>
			<select id="electricVehicleCharge" name="electricVehicleCharge">
            <option value=true>Yes</option>
            <option value=false>No</option>
            </select>
		</p>
		<p>
			<label for="comesFurnished">Comes Furnished</label>
			<select id="comesFurnished" name="electricVehicleCharge">
            <option value=true>Yes</option>
            <option value=false>No</option>
            </select>
		</p>
		<p>
			<label for="laundryOption">Laundry Option</label>
			<select id="laundryOption" name="laundryOption">
            <option value="laundryInBuilding">Laundry in building</option>
            <option value="laundryOnSite">Laundry on site</option>
            <option value="noLaundryOnSite">No laundry on site</option>
            <option value="washerDryerHookups">W/D hookups</option>
            <option value="washerDryerInUnit">W/D in unit</option>
        </select>
		</p>
		<p>
			<label for="parkingOption">Parking</label>
			<select id="parkingOption" name="parkingOption">
            <option value="attachedGarage">Attached garage</option>
            <option value="carport">Carport</option>
            <option value="detachedGarage">Detached garage</option>
            <option value="noParking">No parking</option>
            <option value="offStreetParking">Off-street parking</option>
            <option value="streetParking">Street parking</option>
            <option value="valetParking">Valet parking</option>
        </select>
		</p>
		<p>
			<label for="imgUrl">ImgUrl</label>
			<input id="imgUrl" name="imgUrl" value="">
		</p>
		<p>
			<label for="description">Description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="lat">Lat</label>
			<input id="lat" name="lat" value="">
		</p>
		<p>
			<label for="lon">Lon</label>
			<input id="lon" name="lon" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
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