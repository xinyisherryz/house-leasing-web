package houseSystem.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import houseSystem.model.*;

public class DealsRatingsDao {
    protected ConnectionManager connectionManager;
    private static DealsRatingsDao instance = null;
    protected DealsRatingsDao() {
        connectionManager = new ConnectionManager();
    }
    public static DealsRatingsDao getInstance() {
        if(instance == null) {
            instance = new DealsRatingsDao();
        }
        return instance;
    }

    public DealsRatings create(DealsRatings dealsRating) throws SQLException {
        String insertDealsRating = "INSERT INTO DealsRatings(HouseId,OwnerId,DealerId,Rating) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertDealsRating, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setInt(1, dealsRating.getHouse().getHouseId());
            insertStmt.setInt(2, dealsRating.getOwner().getUserId());
            insertStmt.setInt(3, dealsRating.getDealer().getUserId());
            insertStmt.setInt(4, dealsRating.getRating());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int ratingId = -1;
            if(resultKey.next()) {
                ratingId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            dealsRating.setRatingId(ratingId);

            return dealsRating;
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

    public DealsRatings getDealsRatingById(int ratingId) throws SQLException {
        String selectDealsRating = "SELECT RatingId, HouseId, OwnerId,"
                + "DealerId, Rating FROM DealsRatings WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDealsRating);
            selectStmt.setInt(1, ratingId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();

            if(results.next()) {
                int resultRatingId = results.getInt("RatingId");
                int houseId = results.getInt("HouseId");
                int ownerId = results.getInt("OwnerId");
                int dealerId = results.getInt("DealerId");
                int rating = results.getInt("Rating");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);

                DealsRatings dealsRating = new DealsRatings(resultRatingId, house,
                        owner, dealer, rating);
                return dealsRating;
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

    public List<DealsRatings> getDealsRatingByRating(int rating) throws SQLException {
        List<DealsRatings> dealsRatings = new ArrayList<DealsRatings>();
        String selectDealsRatings = "SELECT RatingId, HouseId, OwnerId,"
                + "DealerId, Rating FROM DealsRatings WHERE Rating=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDealsRatings);
            selectStmt.setInt(1, rating);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            OwnersDao ownersDao = OwnersDao.getInstance();
            DealersDao dealersDao = DealersDao.getInstance();

            while(results.next()) {
                int resultRatingId = results.getInt("RatingId");
                int houseId = results.getInt("HouseId");
                int ownerId = results.getInt("OwnerId");
                int dealerId = results.getInt("DealerId");

                Houses house = housesDao.getHouseById(houseId);
                Owners owner = ownersDao.getOwnerById(ownerId);
                Dealers dealer = dealersDao.getDealerById(dealerId);

                DealsRatings dealsRating = new DealsRatings(resultRatingId, house,
                        owner, dealer, rating);
                dealsRatings.add(dealsRating);
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
        return dealsRatings;
    }

    public DealsRatings updateRating(DealsRatings dealsRating, int newRating) throws SQLException {
        String updateRating = "UPDATE Houses SET Rating=? WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRating);
            updateStmt.setInt(1, newRating);
            updateStmt.setInt(2, dealsRating.getRatingId());
            updateStmt.executeUpdate();

            dealsRating.setRating(newRating);
            return dealsRating;
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


    public DealsRatings delete(DealsRatings dealsRating) throws SQLException {
        String deleteDealsRating = "DELETE FROM DealsRatings WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteDealsRating);
            deleteStmt.setInt(1, dealsRating.getRatingId());
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
