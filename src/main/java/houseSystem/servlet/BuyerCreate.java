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


@WebServlet("/buyercreate")
public class BuyerCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/BuyerCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
    	String firstName = req.getParameter("firstName");
    	String lastName = req.getParameter("lastName");
    	String phone = req.getParameter("phone");
    	String email = req.getParameter("email");
    	String gender = req.getParameter("gender");
    	Integer age = Integer.parseInt(req.getParameter("age"));
        try {
        	// Exercise: parse the input for StatusLevel.
        	Buyers buyer = new Buyers(firstName, lastName, phone, email, gender, age);
        	buyer = buyersDao.create(buyer);
        	messages.put("success", "Successfully created: " + "buyer id: " + buyer.getUserId() + ", first name: " + firstName + ", last name: " + lastName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/BuyerCreate.jsp").forward(req, resp);
    }
}
