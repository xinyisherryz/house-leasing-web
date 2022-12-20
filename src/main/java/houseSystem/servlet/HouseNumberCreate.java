package houseSystem.servlet;

import houseSystem.dal.*;
import houseSystem.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/housenumbercreate")
public class HouseNumberCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/HouseNumberCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
    	String houseType = req.getParameter("houseType");
    	
        try {
        	List<Houses> houses = housesDao.getHouseByHouseType(HouseType.valueOf(houseType));
        	Integer number = houses.size();
        	messages.put("success", number + "");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/HouseNumberCreate.jsp").forward(req, resp);
    }
}
