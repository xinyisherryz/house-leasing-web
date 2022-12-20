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


@WebServlet("/houseupdate")
public class HouseUpdate extends HttpServlet {
	
	protected HousesDao housesDao;
	
	@Override
	public void init() throws ServletException {
		housesDao = HousesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer houseId = Integer.parseInt(req.getParameter("houseId"));
        try {
			Houses house = housesDao.getHouseById(houseId);
			if(house == null) {
				messages.put("success", "houseId does not exist.");
			}
			req.setAttribute("house", house);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/HouseUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer houseId = Integer.parseInt(req.getParameter("houseId"));
        try {
			Houses house = housesDao.getHouseById(houseId);
			if(house == null) {
				messages.put("success", "HouseId does not exist. No update to perform.");
			} else {
				String newPrice = req.getParameter("price");
				if (newPrice == null || newPrice.trim().isEmpty()) {
		            messages.put("success", "Please enter a valid price.");
		        } else {
		        	housesDao.updateHousePrice(house, Integer.parseInt(newPrice));
		        	messages.put("success", "Successfully updated houseId: " + houseId + " with a new price: " + newPrice);
		        }
			}
			req.setAttribute("house", house);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
        
        req.getRequestDispatcher("/HouseUpdate.jsp").forward(req, resp);
    }
}
