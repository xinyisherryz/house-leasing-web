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


@WebServlet("/favoritehouselist")
public class FavoriteHouseList extends HttpServlet {
	
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
		
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("title", "Invalid userId.");
        } else {
        	messages.put("title", "Liked Houses for userId: " + userId);
        	messages.put("userId", userId);
        }
       
        
        List<Favorites> favoriteList = new ArrayList<Favorites>();
        List<Houses> houseList = new ArrayList<Houses>();
        try {
        	favoriteList = favoritesDao.getFavoritesByBuyerId(Integer.parseInt(userId));
        	for(Favorites favorite : favoriteList) {
        		houseList.add(favorite.getHouse());
        	}
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("houseList", houseList);
        req.getRequestDispatcher("/FavoriteHouseList.jsp").forward(req, resp);
	}
}
