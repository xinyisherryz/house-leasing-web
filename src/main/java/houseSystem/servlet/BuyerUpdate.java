package houseSystem.servlet;

import houseSystem.dal.*;
import houseSystem.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import houseSystem.model.Users;


@WebServlet("/buyerupdate")
public class BuyerUpdate extends HttpServlet {
	
	protected BuyersDao buyersDao;
	
	@Override
	public void init() throws ServletException {
		buyersDao = BuyersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer buyerId = Integer.parseInt(req.getParameter("buyerId"));
        try {
			Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);
			if(buyer == null) {
				messages.put("success", "UserName does not exist.");
			}
			req.setAttribute("buyer", buyer);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/BuyerUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer buyerId = Integer.parseInt(req.getParameter("buyerId"));
        try {
			Users buyer = buyersDao.getBuyerFromUserId(buyerId);
			if(buyer == null) {
				messages.put("success", "buyerId does not exist. No update to perform.");
			} else {
				String newPhone = req.getParameter("phone");
				String newEmail = req.getParameter("email");
				if ((newPhone == null || newPhone.trim().isEmpty()) && (newEmail == null || newEmail.trim().isEmpty())) {
		            messages.put("success", "Please enter a valid phone number or email address.");
		        } else {
        	        if(newPhone != null && !newPhone.trim().isEmpty()) {
        	        	buyer = buyersDao.updateUserPhone(buyer, newPhone);
    		        	messages.put("success", "Successfully updated buyerId: " + buyerId + ", new phone number: " + newPhone);
        	        }
        	        if(newEmail != null && !newEmail.trim().isEmpty()) {
        	        	buyer = buyersDao.updateUserEmail(buyer, newEmail);
    		        	messages.put("success", "Successfully updated buyerId: " + buyerId + ", new email address: " + newEmail);
        	        }
		        }
			}
			req.setAttribute("buyer", buyerId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/BuyerUpdate.jsp").forward(req, resp);
    }
}
