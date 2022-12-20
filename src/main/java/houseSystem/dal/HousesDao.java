package houseSystem.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import houseSystem.model.*;

public class HousesDao {
    protected ConnectionManager connectionManager;
    private static HousesDao instance = null;
    protected HousesDao() {
        connectionManager = new ConnectionManager();
    }
    public static HousesDao getInstance() {
        if(instance == null) {
            instance = new HousesDao();
        }
        return instance;
    }

    public Houses create(Houses house) throws SQLException {
        String insertHouse =
                "INSERT INTO Houses(Url, Region, RegionUrl, Price, HouseType, SqFeet, Beds, Baths, CatsAllowed," +
                        "DogsAllowed, SmokingAllowed, WheelchairAccess, ElectricVehicleCharge, ComesFurnished," +
                        "LaundryOption, ParkingOption, ImgUrl, Description, Lat, Lon, State)" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertHouse,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, house.getUrl());
            insertStmt.setString(2, house.getRegion());
            insertStmt.setString(3, house.getRegionUrl());
            insertStmt.setInt(4, house.getPrice());
            insertStmt.setString(5, house.getHouseType().name());
            insertStmt.setInt(6, house.getSqFeet());
            insertStmt.setInt(7, house.getBeds());
            insertStmt.setDouble(8, house.getBaths());
            insertStmt.setBoolean(9, house.isCatsAllowed());
            insertStmt.setBoolean(10, house.isDogsAllowed());
            insertStmt.setBoolean(11, house.isSmokingAllowed());
            insertStmt.setBoolean(12, house.isWheelchairAccess());
            insertStmt.setBoolean(13, house.isElectricVehicleCharge());
            insertStmt.setBoolean(14, house.isComesFurnished());
            insertStmt.setString(15, house.getLaundryOption().name());
            insertStmt.setString(16, house.getParkingOption().name());
            insertStmt.setString(17, house.getImgUrl());
            insertStmt.setString(18, house.getDescription());
            insertStmt.setDouble(19, house.getLat());
            insertStmt.setDouble(20, house.getLon());
            insertStmt.setString(21, house.getState());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int houseId = -1;
            if(resultKey.next()) {
                houseId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            house.setHouseId(houseId);
            return house;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Houses getHouseById(int houseId) throws SQLException {
        String selectHouse = "SELECT HouseId, Url, Region, RegionUrl, Price, HouseType, SqFeet, Beds, Baths, CatsAllowed," +
                "DogsAllowed, SmokingAllowed, WheelchairAccess, ElectricVehicleCharge, ComesFurnished," +
                "LaundryOption, ParkingOption, ImgUrl, Description, Lat, Lon, State FROM Houses WHERE HouseId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHouse);
            selectStmt.setInt(1, houseId);
            results = selectStmt.executeQuery();

            if(results.next()) {
                int resultHouseId = results.getInt("HouseId");
                String url = results.getString("Url");
                String region = results.getString("Region");
                String regionUrl = results.getString("RegionUrl");
                int price = results.getInt("Price");
                HouseType houseType = HouseType.valueOf(
                        results.getString("HouseType"));
                int sqFeet = results.getInt("SqFeet");
                int beds = results.getInt("Beds");
                double baths = results.getDouble("Baths");
                boolean catsAllowed = results.getBoolean("CatsAllowed");
                boolean dogsAllowed = results.getBoolean("DogsAllowed");
                boolean smokingAllowed = results.getBoolean("SmokingAllowed");
                boolean wheelchairAccess = results.getBoolean("WheelchairAccess");
                boolean electricVehicleCharge = results.getBoolean("ElectricVehicleCharge");
                boolean comesFurnished = results.getBoolean("ComesFurnished");
                
                LaundryOption laundryOption = LaundryOption.noLaundryOnSite;
                if(results.getString("LaundryOption") != null && results.getString("LaundryOption").length() != 0) {
                	laundryOption = LaundryOption.valueOf(results.getString("LaundryOption"));
                   }
                
                ParkingOption parkingOption = ParkingOption.noParking;
                if(results.getString("ParkingOption") != null && results.getString("ParkingOption").length() != 0) {
                    parkingOption = ParkingOption.valueOf(results.getString("ParkingOption"));
                   }
                
                
                String imgUrl = results.getString("ImgUrl");
                String description = results.getString("Description");
                int lat = results.getInt("Lat");
                int lon = results.getInt("Lon");
                String state = results.getString("State");

                Houses house = new Houses(resultHouseId, url, region, regionUrl, price, houseType, sqFeet, beds, baths, catsAllowed,
                        dogsAllowed, smokingAllowed, wheelchairAccess, electricVehicleCharge, comesFurnished,
                        laundryOption, parkingOption, imgUrl, description, lat, lon, state);
                return house;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Houses> getHouseByHouseType(HouseType houseType) throws SQLException {
        List<Houses> houses = new ArrayList<Houses>();
        String selectHouses = "SELECT HouseId, Url, Region, RegionUrl, Price, HouseType, SqFeet, Beds, Baths, CatsAllowed," +
                "DogsAllowed, SmokingAllowed, WheelchairAccess, ElectricVehicleCharge, ComesFurnished, " +
                "LaundryOption, ParkingOption, ImgUrl, Description, Lat, Lon, State FROM Houses WHERE HouseType = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHouses);
            selectStmt.setString(1, houseType.name());
            results = selectStmt.executeQuery();

            while(results.next()) {
                int resultHouseId = results.getInt("HouseId");
                String url = results.getString("Url");
                String region = results.getString("Region");
                String regionUrl = results.getString("RegionUrl");
                int price = results.getInt("Price");
                int sqFeet = results.getInt("SqFeet");
                int beds = results.getInt("Beds");
                double baths = results.getDouble("Baths");
                boolean catsAllowed = results.getBoolean("CatsAllowed");
                boolean dogsAllowed = results.getBoolean("DogsAllowed");
                boolean smokingAllowed = results.getBoolean("SmokingAllowed");
                boolean wheelchairAccess = results.getBoolean("WheelchairAccess");
                boolean electricVehicleCharge = results.getBoolean("ElectricVehicleCharge");
                boolean comesFurnished = results.getBoolean("ComesFurnished");
                LaundryOption laundryOption = LaundryOption.valueOf(
                        results.getString("LaundryOption"));
                ParkingOption parkingOption = ParkingOption.valueOf(
                        results.getString("ParkingOption"));
                String imgUrl = results.getString("ImgUrl");
                String description = results.getString("Description");
                int lat = results.getInt("Lat");
                int lon = results.getInt("Lon");
                String state = results.getString("State");

                Houses house = new Houses(resultHouseId, url, region, regionUrl, price, houseType, sqFeet, beds, baths, catsAllowed,
                        dogsAllowed, smokingAllowed, wheelchairAccess, electricVehicleCharge, comesFurnished,
                        laundryOption, parkingOption, imgUrl, description, lat, lon, state);
                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return houses;
    }

    public List<Houses> getHouseByPrice(int price) throws SQLException {
        List<Houses> houses = new ArrayList<Houses>();
        String selectHouses = "SELECT HouseId, Url, Region, RegionUrl, Price, HouseType, SqFeet, Beds, Baths, CatsAllowed," +
                "DogsAllowed, SmokingAllowed, WheelchairAccess, ElectricVehicleCharge, ComesFurnished," +
                "LaundryOption, ParkingOption, ImgUrl, Description, Lat, Lon, State FROM Houses WHERE Price = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHouses);
            selectStmt.setInt(1, price);
            results = selectStmt.executeQuery();

            if(results.next()) {
                int resultHouseId = results.getInt("HouseId");
                String url = results.getString("Url");
                String region = results.getString("Region");
                String regionUrl = results.getString("RegionUrl");
                HouseType houseType = HouseType.valueOf(
                        results.getString("HouseType"));
                int sqFeet = results.getInt("SqFeet");
                int beds = results.getInt("Beds");
                double baths = results.getDouble("Baths");
                boolean catsAllowed = results.getBoolean("CatsAllowed");
                boolean dogsAllowed = results.getBoolean("DogsAllowed");
                boolean smokingAllowed = results.getBoolean("SmokingAllowed");
                boolean wheelchairAccess = results.getBoolean("WheelchairAccess");
                boolean electricVehicleCharge = results.getBoolean("ElectricVehicleCharge");
                boolean comesFurnished = results.getBoolean("ComesFurnished");
                LaundryOption laundryOption = LaundryOption.valueOf(
                        results.getString("LaundryOption"));
                ParkingOption parkingOption = ParkingOption.valueOf(
                        results.getString("ParkingOption"));
                String imgUrl = results.getString("ImgUrl");
                String description = results.getString("Description");
                int lat = results.getInt("Lat");
                int lon = results.getInt("Lon");
                String state = results.getString("State");

                Houses house = new Houses(resultHouseId, url, region, regionUrl, price, houseType, sqFeet, beds, baths, catsAllowed,
                        dogsAllowed, smokingAllowed, wheelchairAccess, electricVehicleCharge, comesFurnished,
                        laundryOption, parkingOption, imgUrl, description, lat, lon, state);
                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return houses;
    }

    public Houses updateHousePrice(Houses house, int newPrice) throws SQLException {
        String updateHousePrice = "UPDATE Houses SET Price=? WHERE HouseId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateHousePrice);
            updateStmt.setInt(1, newPrice);
            updateStmt.setInt(2, house.getHouseId());
            updateStmt.executeUpdate();

            house.setPrice(newPrice);
            return house;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Houses delete(Houses house) throws SQLException {
        String deleteLeaseHistory = "DELETE FROM Houses WHERE HouseId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteLeaseHistory);
            deleteStmt.setInt(1, house.getHouseId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }


}
