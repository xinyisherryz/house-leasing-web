/**
 * @author zeyushen;
 */
package houseSystem.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import houseSystem.model.*;



public class BuyersDao extends UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BuyersDao instance = null;
	protected BuyersDao() {
		connectionManager = new ConnectionManager();
	}
	public static BuyersDao getInstance() {
		if(instance == null) {
			instance = new BuyersDao();
		}
		return instance;
	}

	/**
	 * Save the Buyers instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Buyers create(Buyers buyer) throws SQLException {
		Users user = create(new Users(buyer.getFirstName(),buyer.getLastName(), buyer.getPhone(), buyer.getEmail(), buyer.getGender(),buyer.getAge()));
		
		String insertBuyer = "INSERT INTO Buyers(UserId) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBuyer);

			insertStmt.setInt(1, user.getUserId());
			
			insertStmt.executeUpdate();
			buyer.setUserId(user.getUserId());
			return buyer;
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


	/**
	 * Delete the Buyers instance.
	 * This runs a DELETE statement.
	 */
	public Buyers delete(Buyers buyer) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, buyer.getUserId());
			deleteStmt.executeUpdate();
			
			super.delete(buyer);
			
			// Return null so the caller can no longer operate on the Persons instance.
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

	/**
	 * Get the Buyers record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Buyers getBuyerFromUserId(int userId) throws SQLException {
		String selectBuyer = "select userId, firstName, lastName, phone, email, gender, age from users where userId=?";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBuyer);
			selectStmt.setInt(1, userId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int buyerId = results.getInt("UserId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String phone = results.getString("Phone");
				String email = results.getString("Email");
				String gender = results.getString("Gender");
				int age = results.getInt("Age");
				
				Buyers buyer = new Buyers(buyerId, firstName, lastName, phone, email, gender, age);
				return buyer;
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
}