package houseSystem.servlet;

import houseSystem.dal.*;
import houseSystem.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/favoritehousedelete")
public class FavoriteHouseDelete extends HttpServlet {
	
	protected FavoritesDao favoritesDao;
	protected HousesDao housesDao;
	
	@Override
	public void init() throws ServletException {
		housesDao = HousesDao.getInstance();
		favoritesDao = FavoritesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
//        message.put()
        req.getRequestDispatcher("/FavoriteHouseDelete.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        String userId = req.getParameter("userId");
        String houseId = req.getParameter("houseId");
        if (userId == null || userId.trim().isEmpty() || houseId == null || houseId.trim().isEmpty()) {
            messages.put("title", "Invalid userId or houseId.");
        } else {
        	messages.put("title", "Delete Favorite House for userId: " + userId);
        }
       
        
        try {
        	Favorites favorite = favoritesDao.deleteFavoriteByUserIdAndHouseId(Integer.parseInt(userId), Integer.parseInt(houseId));
        	if (favorite == null) {
	            messages.put("title", "Successfully deleted favorite: " + "(userId: " + userId + ", houseId: " + houseId + ")");
	            messages.put("disableSubmit", "true");
	        } else {
	        	messages.put("title", "Failed to delete userId: " + userId);
	        	messages.put("disableSubmit", "false");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/FavoriteHouseDelete.jsp").forward(req, resp);
	}
}
