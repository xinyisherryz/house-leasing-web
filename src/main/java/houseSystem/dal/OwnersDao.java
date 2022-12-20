package houseSystem.dal;

import houseSystem.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OwnersDao extends UsersDao {
	private static OwnersDao instance = null;
	protected OwnersDao() {
		super();
	}
	public static OwnersDao getInstance() {
		if (instance == null) {
			instance = new OwnersDao();
		}
		return instance;
	}


	public Owners create(Owners owner) throws SQLException {
		create(new Users(owner.getUserId(), owner.getFirstName(), owner.getLastName(), owner.getPhone(), 
				owner.getEmail(),owner.getGender(), owner.getAge()));
		
		String insertOwner = "INSERT INTO Owners(UserId,HouseId) "
				+ " VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOwner);

			insertStmt.setInt(1, owner.getUserId());
			insertStmt.setInt(2, owner.getHouse().getHouseId());
			insertStmt.executeUpdate();
			
			return owner;
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
		}
	}
	

	public Owners getOwnerById(int ownerId) throws SQLException {
		String selectOwner = "SELECT OwnerId,HouseId "
				+ "FROM Owners WHERE OwnerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOwner);
			selectStmt.setInt(1, ownerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HousesDao housesDao = HousesDao.getInstance();
			
			if(results.next()) {
				int resultOwnerId = results.getInt("OwnerId");
				int houseId = results.getInt("HouseId");
				
				Users user = usersDao.getUserFromUserId(resultOwnerId);
				Houses house = housesDao.getHouseById(houseId);
				Owners owner = new Owners(resultOwnerId, user.getFirstName(), user.getLastName(), user.getPhone(), 
						user.getEmail(), user.getGender(), user.getAge(), house);
				return owner;
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

	public Owners delete(Owners owner) throws SQLException {
		String deleteOwner = "DELETE FROM Owners WHERE OwnerId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOwner);
			deleteStmt.setInt(1, owner.getUserId());
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
