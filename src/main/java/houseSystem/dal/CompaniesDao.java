package houseSystem.dal;

import houseSystem.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompaniesDao {
	protected ConnectionManager connectionManager;
	
	private static CompaniesDao instance = null;
	protected CompaniesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompaniesDao getInstance() {
		if(instance == null) {
			instance = new CompaniesDao();
		}
		return instance;
	}


	public Companies create(Companies company) throws SQLException {
		String insertCompany = "INSERT INTO Companies(CompanyId,CompanyName,FoundedTime,"
				+ "Founder,CompanySize,Ranking) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompany);

			insertStmt.setInt(1, company.getCompanyId());
			insertStmt.setString(2, company.getCompanyName());
			insertStmt.setInt(3, company.getFoundedTime());
			insertStmt.setString(4, company.getFounder());
			insertStmt.setInt(5, company.getCompanySize());
			insertStmt.setInt(6, company.getRanking());
			insertStmt.executeUpdate();
			
			return company;
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
	
	public Companies getCompanyById(int companyId) throws SQLException {
		String selectCompany = "SELECT CompanyId,CompanyName,FoundedTime,Founder,"
				+ "CompanySize,Ranking FROM Companies WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setInt(1, companyId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultCompanyId = results.getInt("CompanyId");
				String companyName = results.getString("CompanyName");
				int foundedTime = results.getInt("FoundedTime");
				String founder = results.getString("Founder");
				int companySize = results.getInt("CompanySize");
				int ranking = results.getInt("Ranking");
				
				Companies company = new Companies(resultCompanyId, companyName, 
						foundedTime, founder, companySize, ranking);
				return company;
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
	
	public Companies getCompanyByName(String companyName) throws SQLException {
		String selectCompany = "SELECT CompanyId,CompanyName,FoundedTime,Founder,"
				+ "CompanySize,Ranking FROM Companies WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int companyId = results.getInt("CompanyId");
				String resultCompanyName = results.getString("CompanyName");
				int foundedTime = results.getInt("FoundedTime");
				String founder = results.getString("Founder");
				int companySize = results.getInt("CompanySize");
				int ranking = results.getInt("Ranking");
				
				Companies company = new Companies(companyId, resultCompanyName, 
						foundedTime, founder, companySize, ranking);
				return company;
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

	public Companies updateCompanyName(Companies company, String newCompanyName) throws SQLException {
		String updateCompany = "UPDATE Companies SET CompanyName=? WHERE companyId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompany);
			updateStmt.setString(1, newCompanyName);
			updateStmt.setString(2, company.getCompanyName());
			updateStmt.executeUpdate();
			
			company.setCompanyName(newCompanyName);
			return company;
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

	public Companies delete(Companies company) throws SQLException {
		String deleteCompany = "DELETE FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
			deleteStmt.setInt(1, company.getCompanyId());
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
