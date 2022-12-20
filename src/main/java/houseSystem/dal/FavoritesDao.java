package houseSystem.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import houseSystem.model.*;

public class FavoritesDao {
    protected ConnectionManager connectionManager;
    private static FavoritesDao instance = null;
    protected FavoritesDao() {
        connectionManager = new ConnectionManager();
    }
    public static FavoritesDao getInstance() {
        if(instance == null) {
            instance = new FavoritesDao();
        }
        return instance;
    }

    public Favorites create(Favorites favorite) throws SQLException {
        String insertFavorite =
                "INSERT INTO Favorites(HouseId, BuyerId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFavorite,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, favorite.getHouse().getHouseId());
            insertStmt.setInt(2, favorite.getBuyer().getUserId());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int favoriteId = -1;
            if(resultKey.next()) {
                favoriteId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            favorite.setFavoriteId(favoriteId);
            return favorite;
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

    public Favorites getFavoriteById(int favoriteId) throws SQLException {
        String selectFavorite = "SELECT FavoriteId, HouseId, BuyerId "
                + "FROM Favorites WHERE FavoriteId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorite);
            selectStmt.setInt(1, favoriteId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            if(results.next()) {
                int resultFavoriteId = results.getInt("FavoriteId");
                int houseId = results.getInt("HouseId");
                int buyerId = results.getInt("BuyerId");

                Houses house = housesDao.getHouseById(houseId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                Favorites favorite = new Favorites(resultFavoriteId, house, buyer);
                return favorite;
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

    public List<Favorites> getFavoritesByHouseId(int houseId) throws SQLException {
        List<Favorites> favorites = new ArrayList<Favorites>();
        String selectFavorites = "SELECT FavoriteId, HouseId, BuyerId "
                + "FROM Favorites WHERE HouseId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorites);
            selectStmt.setInt(1, houseId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            while(results.next()) {
                int resultFavoriteId = results.getInt("FavoriteId");
                int buyerId = results.getInt("BuyerId");

                Houses house = housesDao.getHouseById(houseId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                Favorites favorite = new Favorites(resultFavoriteId, house, buyer);
                favorites.add(favorite);
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
        return favorites;
    }

    public List<Favorites> getFavoritesByBuyerId(int buyerId) throws SQLException {
        List<Favorites> favorites = new ArrayList<Favorites>();
        String selectFavorites = "SELECT FavoriteId, HouseId, BuyerId FROM Favorites WHERE BuyerId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorites);
            selectStmt.setInt(1, buyerId);
            results = selectStmt.executeQuery();
            HousesDao housesDao = HousesDao.getInstance();
            BuyersDao buyersDao = BuyersDao.getInstance();

            while(results.next()) {
                int favoriteId = results.getInt("FavoriteId");
                int houseId = results.getInt("HouseId");

                Houses house = housesDao.getHouseById(houseId);
                Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);

                Favorites favorite = new Favorites(favoriteId, house, buyer);
                favorites.add(favorite);
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
        return favorites;
    }
    
    public Favorites updateFavoriteHouseId(Favorites favorite, Houses newHouse) throws SQLException {
        String updateFavoriteHouseId = "UPDATE Favorites SET HouseId=? WHERE FavoriteId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFavoriteHouseId);
            updateStmt.setInt(1, newHouse.getHouseId());
            updateStmt.setInt(2, favorite.getFavoriteId());
            updateStmt.executeUpdate();

            favorite.setHouse(newHouse);
            return favorite;
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

    public Favorites delete(Favorites favorite) throws SQLException {
        String deleteFavorite = "DELETE FROM Favorites WHERE FavoriteId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavorite);
            deleteStmt.setInt(1, favorite.getFavoriteId());
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
    
    
	public Favorites deleteFavoriteByUserIdAndHouseId(int buyerId, int houseId) throws SQLException {
		String deleteFavorite = "DELETE FROM Favorites WHERE buyerId=? and houseId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavorite);
            deleteStmt.setInt(1, buyerId);
            deleteStmt.setInt(2, houseId);
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
