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


@WebServlet("/favoritecreate")
public class FavoriteCreate extends HttpServlet {
	
	protected BuyersDao buyersDao;
	protected HousesDao housesDao;
	protected FavoritesDao favoritesDao;
	
	@Override
	public void init() throws ServletException {
		buyersDao = BuyersDao.getInstance();
		housesDao = HousesDao.getInstance();
		favoritesDao = FavoritesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FavoriteCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
    	Integer buyerId = Integer.parseInt(req.getParameter("buyerId"));
    	Integer houseId = Integer.parseInt(req.getParameter("houseId"));
    	
        try {
        	// Exercise: parse the input for StatusLevel.
        	Buyers buyer = buyersDao.getBuyerFromUserId(buyerId);
        	Houses house = housesDao.getHouseById(houseId);
        	Favorites favorite = new Favorites(house, buyer);
        	favorite = favoritesDao.create(favorite);
        	messages.put("success", "Successfully created: " + "favoriteId: " + favorite.getFavoriteId() + ", buyerId: " + buyerId + ", houseId: " + houseId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/FavoriteCreate.jsp").forward(req, resp);
    }
}
