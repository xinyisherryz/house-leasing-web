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


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        try {
			Users user = usersDao.getUserFromUserId(userId);
			if(user == null) {
				messages.put("success", "userId does not exist.");
			}
			req.setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        try {
			Users user = usersDao.getUserFromUserId(userId);
			if(user == null) {
				messages.put("success", "UserName does not exist. No update to perform.");
			} else {
				String newPhone = req.getParameter("phone");
				String newEmail = req.getParameter("email");
				if ((newPhone == null || newPhone.trim().isEmpty()) && (newEmail == null || newEmail.trim().isEmpty())) {
		            messages.put("success", "Please enter a valid phone number or email address.");
		        } else {
        	        if(newPhone != null && !newPhone.trim().isEmpty()) {
        	        	user = usersDao.updateUserPhone(user, newPhone);
    		        	messages.put("success", "Successfully updated userId: " + userId + ", new phone number: " + newPhone);
        	        }
        	        if(newEmail != null && !newEmail.trim().isEmpty()) {
        	        	user = usersDao.updateUserEmail(user, newEmail);
    		        	messages.put("success", "Successfully updated userId: " + userId + ", new email address: " + newEmail);
        	        }
		        }
			}
			req.setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
