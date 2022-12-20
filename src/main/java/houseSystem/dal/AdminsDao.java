/**
 * @author zeyushen;
 */
package houseSystem.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import houseSystem.model.*;



public class AdminsDao extends UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AdminsDao instance = null;
	protected AdminsDao() {
		connectionManager = new ConnectionManager();
	}
	public static AdminsDao getInstance() {
		if(instance == null) {
			instance = new AdminsDao();
		}
		return instance;
	}

	/**
	 * Save the Admins instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Admins create(Admins admin) throws SQLException {
		create(new Users(admin.getUserId(), admin.getFirstName(),admin.getLastName(), admin.getPhone(), admin.getEmail(), admin.getGender(),admin.getAge()));
		
		String insertAdmin = "INSERT INTO Admins(UserId) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdmin);

			insertStmt.setInt(1, admin.getUserId());
			
			insertStmt.executeUpdate();
			return admin;
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
	 * Delete the Admins instance.
	 * This runs a DELETE statement.
	 */
	public Admins delete(Admins admin) throws SQLException {
		String deleteUser = "DELETE FROM Admins WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, admin.getUserId());
			deleteStmt.executeUpdate();
			
			super.delete(admin);
			
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
	 * Get the Admins record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Admins getAdminFromUserId(int userId) throws SQLException {
		String selectAdmin = "SELECT Admins.UserId AS UserId, FirstName, LastName, Phone, Email, Gender, Age FROM Admins WHERE UserId=?;"
				+ "FROM Admins INNER JOIN Users"
				+ " ON Admins.UserId = Users.UserId "
				+ " WHERE Admins.UserId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
			selectStmt.setInt(1, userId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String phone = results.getString("Phone");
				String email = results.getString("Email");
				String gender = results.getString("Gender");
				int age = results.getInt("Age");
				
				Admins admin = new Admins(resultUserId, firstName, lastName, phone, email, gender, age);
				return admin;
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