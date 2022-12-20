package houseSystem.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import houseSystem.model.*;


public class LeaseHistoriesDao {
    protected ConnectionManager connectionManager;
    private static LeaseHistoriesDao instance = null;
    protected LeaseHistoriesDao() {
        connectionManager = new ConnectionManager();
    }
    public static LeaseHistoriesDao getInstance() {
        if(instance == null) {
            instance = new LeaseHistoriesDao();
        }
        return instance;
    }

    public LeaseHistories create(LeaseHistories leaseHistory) throws SQLException {
        String insertLeaseHistory =
                "INSERT INTO LeaseHistories(HouseId, OwnerId, DealerId, BuyerId, Price) " +
                        "VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertLeaseHistory,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, leaseHistory.getHouse().getHouseId());
            insertStmt.setInt(2, leaseHistory.getOwner().getUserId());
            insertStmt.setInt(3, leaseHistory.getDealer().getUserId());
            insertStmt.setInt(4, leaseHistory.getBuyer().getUserId());
            insertStmt.setDouble(5, leaseHistory.getPrice());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int leaseHistoryId = -1;
            if(resultKey.next()) {
                leaseHistoryId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            leaseHistory.setLeaseId(leaseHistoryId);
            return leaseHistory;
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

    public LeaseHistories getLeaseHistoryById(int leaseId) throws SQLException {
        String selectLeaseHistory = "SELECT LeaseId, HouseId, OwnerId,"
                + "DealerId, BuyerId, Price FROM LeaseHistories WHERE LeaseId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLeaseHistory);
            selectStmt.setInt(1, leaseId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            if(results.next()) {
                int resultLeaseId = results.getInt("LeaseId");
                int houseId = results.getInt("HouseId");
                int ownerId = results.getInt("OwnerId");
                int dealerId = results.getInt("DealerId");
                int buyerId = results.getInt("BuyerId");
                int price = results.getInt("Price");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                LeaseHistories leaseHistory = new LeaseHistories(resultLeaseId, house,
                        owner, dealer, buyer, price);
                return leaseHistory;
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

    public List<LeaseHistories> getLeaseHistoryByHouseId(int houseId) throws SQLException {
        List<LeaseHistories> leaseHistories = new ArrayList<LeaseHistories>();
        String selectLeaseHistory = "SELECT LeaseId, HouseId, OwnerId,"
                + "DealerId, BuyerId, Price FROM LeaseHistories WHERE HouseId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLeaseHistory);
            selectStmt.setInt(1, houseId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            if(results.next()) {
                int resultLeaseId = results.getInt("LeaseId");
                int ownerId = results.getInt("OwnerId");
                int dealerId = results.getInt("DealerId");
                int buyerId = results.getInt("BuyerId");
                int price = results.getInt("Price");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                LeaseHistories leaseHistory = new LeaseHistories(resultLeaseId, house,
                        owner, dealer, buyer, price);
                leaseHistories.add(leaseHistory) ;
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
        return leaseHistories;
    }

    public List<LeaseHistories> getLeaseHistoryByDealerId(int dealerId) throws SQLException {
        List<LeaseHistories> leaseHistories = new ArrayList<LeaseHistories>();
        String selectLeaseHistory = "SELECT LeaseId, HouseId, OwnerId,"
                + "DealerId, BuyerId, Price FROM LeaseHistories WHERE DealerId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLeaseHistory);
            selectStmt.setInt(1, dealerId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            if(results.next()) {
                int resultLeaseId = results.getInt("LeaseId");
                int houseId = results.getInt("HouseId");
                int ownerId = results.getInt("OwnerId");
                int buyerId = results.getInt("BuyerId");
                int price = results.getInt("Price");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                LeaseHistories leaseHistory = new LeaseHistories(resultLeaseId, house,
                        owner, dealer, buyer, price);
                leaseHistories.add(leaseHistory) ;
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
        return leaseHistories;
    }

    public List<LeaseHistories> getLeaseHistoryByPrice(double price) throws SQLException {
        List<LeaseHistories> leaseHistories = new ArrayList<LeaseHistories>();
        String selectLeaseHistory = "SELECT LeaseId, HouseId, OwnerId,"
                + "DealerId, BuyerId, Price FROM LeaseHistories WHERE Price=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLeaseHistory);
            selectStmt.setDouble(1, price);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            if(results.next()) {
                int resultLeaseId = results.getInt("LeaseId");
                int houseId = results.getInt("HouseId");
                int ownerId = results.getInt("OwnerId");
                int dealerId = results.getInt("DealerId");
                int buyerId = results.getInt("BuyerId");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                LeaseHistories leaseHistory = new LeaseHistories(resultLeaseId, house,
                        owner, dealer, buyer, price);
                leaseHistories.add(leaseHistory) ;
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
        return leaseHistories;
    }

    public LeaseHistories updatePrice(LeaseHistories leaseHistory, double newPrice) throws SQLException {
        String updatePrice = "UPDATE LeaseHistories SET Price=? WHERE LeaseId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePrice);
            updateStmt.setDouble(1, newPrice);
            updateStmt.setInt(2, leaseHistory.getLeaseId());
            updateStmt.executeUpdate();

            leaseHistory.setPrice(newPrice);
            return leaseHistory;
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

    public LeaseHistories delete(LeaseHistories leaseHistory) throws SQLException {
        String deleteLeaseHistory = "DELETE FROM LeaseHistories WHERE LeaseId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteLeaseHistory);
            deleteStmt.setInt(1, leaseHistory.getLeaseId());
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
