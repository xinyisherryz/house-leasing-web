package houseSystem.dal;

import houseSystem.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DealersDao extends UsersDao {
	private static DealersDao instance = null;
	protected DealersDao() {
		super();
	}
	public static DealersDao getInstance() {
		if (instance == null) {
			instance = new DealersDao();
		}
		return instance;
	}


	public Dealers create(Dealers dealer) throws SQLException {
		create(new Users(dealer.getUserId(), dealer.getFirstName(), dealer.getLastName(), dealer.getPhone(), 
				dealer.getEmail(),dealer.getGender(), dealer.getAge()));
		
		String insertDealer = "INSERT INTO Dealers(UserId,CompanyId,YearsOfExperience) "
				+ " VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDealer);

			insertStmt.setInt(1, dealer.getUserId());
			insertStmt.setInt(2, dealer.getCompany().getCompanyId());
			insertStmt.setInt(3, dealer.getYearsOfExperience());

			insertStmt.executeUpdate();
			
			return dealer;
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
	
	public Dealers getDealerById(int dealerId) throws SQLException {
		String selectDealer = "SELECT UserId,CompanyId,YearsOfExperience "
				+ "FROM Dealers WHERE DealerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDealer);
			selectStmt.setInt(1, dealerId);
			results = selectStmt.executeQuery();
			CompaniesDao companiesDao = CompaniesDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			
			if(results.next()) {
				int resultDealerId = results.getInt("DealerId");
				int companyId = results.getInt("CompanyId");
				int yearsOfExperience = results.getInt("YearsOfExperience");
				
				Companies company = companiesDao.getCompanyById(companyId);
				Users user = usersDao.getUserFromUserId(resultDealerId);
				Dealers dealer = new Dealers(resultDealerId, user.getFirstName(), user.getLastName(), user.getPhone(), 
						user.getEmail(), user.getGender(), user.getAge(), company, yearsOfExperience);
				return dealer;
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
	
	public List<Dealers> getDealersByCompany(Companies company) throws SQLException {
		List<Dealers> dealers = new ArrayList<Dealers>();
		String selectDealers = "SELECT UserId,CompanyId,YearsOfExperience "
				+ "FROM Dealers WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDealers);
			selectStmt.setInt(1, company.getCompanyId());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int userId = results.getInt("UserId");
				int yearsOfExperience = results.getInt("YearsOfExperience");
				Users user = usersDao.getUserFromUserId(userId);
				
				Dealers dealer = new Dealers(userId, user.getFirstName(), user.getLastName(), user.getPhone(), 
						user.getEmail(), user.getGender(), user.getAge(), company, yearsOfExperience);
				dealers.add(dealer);
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
		return dealers;
	}

	public Dealers updateYearsOfExperience(Dealers dealer, int newYearsOfExperience) throws SQLException {
		String updateDealer = "UPDATE Dealers SET YearsOfExperience=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDealer);
			updateStmt.setInt(1, newYearsOfExperience);
			updateStmt.setInt(2, dealer.getYearsOfExperience());
			updateStmt.executeUpdate();
			
			dealer.setYearsOfExperience(newYearsOfExperience);
			return dealer;
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

	public Dealers delete(Dealers dealer) throws SQLException {
		String deleteDealer = "DELETE FROM Dealers WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDealer);
			deleteStmt.setInt(1, dealer.getUserId());
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
