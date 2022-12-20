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


@WebServlet("/buyerdelete")
public class BuyerDelete extends HttpServlet {
	
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
        req.getRequestDispatcher("/BuyerDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

    	Integer buyerId = Integer.parseInt(req.getParameter("buyerId"));
        try {
        	Buyers buyer = new Buyers(buyerId);
        	buyer = buyersDao.delete(buyer);
	        if (buyer == null) {
	            messages.put("title", "Successfully deleted buyerId: " + buyerId);
	            messages.put("disableSubmit", "true");
	        } else {
	        	messages.put("title", "Failed to delete buyerId: " + buyerId);
	        	messages.put("disableSubmit", "false");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/BuyerDelete.jsp").forward(req, resp);
    }
}
