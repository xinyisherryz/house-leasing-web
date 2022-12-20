/**
 * @author zeyushen;
 */
package houseSystem.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import houseSystem.model.*;

public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	
	/**
	 * Save the CreditCards instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public CreditCards create(CreditCards creditcard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, FirstName, LastName, ExpYear, ExpMonth, Buyer) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);

			insertStmt.setString(1, creditcard.getCardNumber());
			
			insertStmt.setString(2, creditcard.getFirstName());
			insertStmt.setString(3, creditcard.getLastName());
			insertStmt.setInt(4, creditcard.getExpYear());
			insertStmt.setInt(5, creditcard.getExpMonth());
			insertStmt.setInt(6, creditcard.getBuyer().getUserId());
			
			insertStmt.executeUpdate();
		
			return creditcard;
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
	 * Delete the CreditCards instance.
	 * This runs a DELETE statement.
	 */
	public CreditCards delete(CreditCards creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setString(1, creditCard.getCardNumber());
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
	
	/**
	 * Get the CreditCards record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single CreditCards instance.
	 */
	public CreditCards getCreditCardByCardNumber(String cardNumber) throws SQLException {
		String selectCreditCard = "SELECT CardNumber, FirstName, LastName, ExpYear, ExpMonth, Buyer FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, cardNumber);
			BuyersDao buyersDao = BuyersDao.getInstance();  // get from user
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultCardNumber = results.getString("CardNumber");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int expYear = results.getInt("ExpYear");
				int expMonth = results.getInt("ExpMonth");
				
				int buyerId = results.getInt("Buyer");
				Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);
				
				CreditCards creditCard = new CreditCards(resultCardNumber, firstName, lastName, expYear, expMonth, buyer);
				return creditCard;
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
	
	public List<CreditCards> getCreditCardsByUserId(int userId) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards = "SELECT CardNumber, FirstName, LastName, ExpYear, ExpMonth, BuyerId FROM CreditCards WHERE buyerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		BuyersDao buyersDao = BuyersDao.getInstance();  // get from buyer
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultCardNumber = results.getString("CardNumber");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int expYear = results.getInt("ExpYear");
				int expMonth = results.getInt("ExpMonth");
				
				int buyerId = results.getInt("buyerId");
				Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);
				
				CreditCards creditCard = new CreditCards(resultCardNumber, firstName, lastName, expYear, expMonth, buyer);
				creditCards.add(creditCard);
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
		return creditCards;
	}
	
	public CreditCards updateExpiration(CreditCards creditCard, int newExpYear, int newExpMonth) throws SQLException {
		String updateExpiration = "UPDATE CreditCards SET ExpYear=?, ExpMonth=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			
			updateStmt.setInt(1, newExpYear);
			updateStmt.setInt(2, newExpMonth);
			updateStmt.setString(3, creditCard.getCardNumber());
			updateStmt.executeUpdate();

			creditCard.setExpYear(newExpYear);
			creditCard.setExpMonth(newExpMonth);
			return creditCard;
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
	
}