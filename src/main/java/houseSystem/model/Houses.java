package houseSystem.model;

public class Houses {
    protected int houseId;
    protected String url;
    protected String region;
    protected String regionUrl;
    protected int price;
    protected HouseType houseType;
    protected int sqFeet;
    protected int beds;
    protected double baths;
    protected boolean catsAllowed;
    protected boolean dogsAllowed;
    protected boolean smokingAllowed;
    protected boolean wheelchairAccess;
    protected boolean electricVehicleCharge;
    protected boolean comesFurnished;
    protected LaundryOption laundryOption;
    protected ParkingOption parkingOption;
    protected String imgUrl;
    protected String description;
    protected double lat;
    protected double lon;
    protected String state;

    
    
    public Houses(int houseId) {
		super();
		this.houseId = houseId;
	}

	public Houses(int houseId, String url, String region, String regionUrl, int price, HouseType houseType, int sqFeet,
                  int beds, double baths, boolean catsAllowed, boolean dogsAllowed, boolean smokingAllowed,
                  boolean wheelchairAccess, boolean electricVehicleCharge, boolean comesFurnished,
                  LaundryOption laundryOption, ParkingOption parkingOption, String imgUrl, String description,
                  double lat, double lon, String state) {
        this.houseId = houseId;
        this.url = url;
        this.region = region;
        this.regionUrl = regionUrl;
        this.price = price;
        this.houseType = houseType;
        this.sqFeet = sqFeet;
        this.beds = beds;
        this.baths = baths;
        this.catsAllowed = catsAllowed;
        this.dogsAllowed = dogsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.wheelchairAccess = wheelchairAccess;
        this.electricVehicleCharge = electricVehicleCharge;
        this.comesFurnished = comesFurnished;
        this.laundryOption = laundryOption;
        this.parkingOption = parkingOption;
        this.imgUrl = imgUrl;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.state = state;
    }

    public Houses(String url, String region, String regionUrl, int price, HouseType houseType, int sqFeet,
                  int beds, double baths, boolean catsAllowed, boolean dogsAllowed, boolean smokingAllowed,
                  boolean wheelchairAccess, boolean electricVehicleCharge, boolean comesFurnished,
                  LaundryOption laundryOption, ParkingOption parkingOption, String imgUrl, String description,
                  double lat, double lon, String state) {
        this.url = url;
        this.region = region;
        this.regionUrl = regionUrl;
        this.price = price;
        this.houseType = houseType;
        this.sqFeet = sqFeet;
        this.beds = beds;
        this.baths = baths;
        this.catsAllowed = catsAllowed;
        this.dogsAllowed = dogsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.wheelchairAccess = wheelchairAccess;
        this.electricVehicleCharge = electricVehicleCharge;
        this.comesFurnished = comesFurnished;
        this.laundryOption = laundryOption;
        this.parkingOption = parkingOption;
        this.imgUrl = imgUrl;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.state = state;
    }

//    public Houses(int houseId) {
//        this.houseId = houseId;
//    }
//
//    public Houses(String companyName, int foundedTime, String founder, int companySize, int ranking) {
//        this.url = url;
//        this.region = region;
//        this.price = price;
//        this.houseType = houseType;
//        this.sqFeet = sqFeet;
//        this.beds = beds;
//        this.baths = baths;
//        this.catsAllowed = catsAllowed;
//        this.dogsAllowed = dogsAllowed;
//        this.smokingAllowed = smokingAllowed;
//        this.wheelchairAccess = wheelchairAccess;
//        this.electricVehicleCharge = electricVehicleCharge;
//        this.comesFurnished = comesFurnished;
//        this.laundryOption = laundryOption;
//        this.parkingOption = parkingOption;
//        this.imgUrl = imgUrl;
//        this.description = description;
//        this.lat = lat;
//        this.lon = lon;
//        this.state = state;
//    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public int getSqFeet() {
        return sqFeet;
    }

    public void setSqFeet(int sqFeet) {
        this.sqFeet = sqFeet;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getBaths() {
        return baths;
    }

    public void setBaths(double baths) {
        this.baths = baths;
    }

    public boolean isCatsAllowed() {
        return catsAllowed;
    }

    public void setCatsAllowed(boolean catsAllowed) {
        this.catsAllowed = catsAllowed;
    }

    public boolean isDogsAllowed() {
        return dogsAllowed;
    }

    public void setDogsAllowed(boolean dogsAllowed) {
        this.dogsAllowed = dogsAllowed;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    public boolean isElectricVehicleCharge() {
        return electricVehicleCharge;
    }

    public void setElectricVehicleCharge(boolean electricVehicleCharge) {
        this.electricVehicleCharge = electricVehicleCharge;
    }

    public boolean isComesFurnished() {
        return comesFurnished;
    }

    public void setComesFurnished(boolean comesFurnished) {
        this.comesFurnished = comesFurnished;
    }

    public LaundryOption getLaundryOption() {
        return laundryOption;
    }

    public void setLaundryOption(LaundryOption laundryOption) {
        this.laundryOption = laundryOption;
    }

    public ParkingOption getParkingOption() {
        return parkingOption;
    }

    public void setParkingOption(ParkingOption parkingOption) {
        this.parkingOption = parkingOption;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getRegionUrl() {
		return regionUrl;
	}

	public void setRegionUrl(String regionUrl) {
		this.regionUrl = regionUrl;
	}
    
}
