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


@WebServlet("/housecreate")
public class HouseCreate extends HttpServlet {
	
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
        req.getRequestDispatcher("/HouseCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        System.out.println("here");
        System.out.println(req.getParameter("parkingOption"));
        
        
    	String url = req.getParameter("url");
    	String region = req.getParameter("region");
    	String regionUrl = req.getParameter("regionUrl");
    	Integer price = Integer.parseInt(req.getParameter("price"));
    	HouseType houseType = HouseType.valueOf(req.getParameter("houseType"));
    	Integer sqFeet = Integer.parseInt(req.getParameter("sqFeet"));
    	Integer beds = Integer.parseInt(req.getParameter("beds"));
    	Double baths = Double.parseDouble(req.getParameter("baths"));
    	Boolean catsAllowed = Boolean.parseBoolean(req.getParameter("catsAllowed"));
    	Boolean dogsAllowed = Boolean.parseBoolean(req.getParameter("dogsAllowed"));
    	Boolean smokingAllowed = Boolean.parseBoolean(req.getParameter("smokingAllowed"));
    	Boolean wheelchairAccess = Boolean.parseBoolean(req.getParameter("wheelchairAccess"));
    	Boolean electricVehicleCharge = Boolean.parseBoolean(req.getParameter("electricVehicleCharge"));
    	Boolean comesFurnished = Boolean.parseBoolean(req.getParameter("comesFurnished"));
    	LaundryOption laundryOption = LaundryOption.valueOf(req.getParameter("laundryOption"));
    	ParkingOption parkingOption = ParkingOption.valueOf(req.getParameter("parkingOption"));
    	String imgUrl = req.getParameter("imgUrl");
    	String description = req.getParameter("description");
    	Double lat = Double.parseDouble(req.getParameter("lat"));
    	Double lon = Double.parseDouble(req.getParameter("lon"));
    	String state = req.getParameter("state");
        try {
        	// Exercise: parse the input for StatusLevel.
        	Houses house = new Houses(url, region, regionUrl, price, houseType, sqFeet,
                    beds, baths, catsAllowed, dogsAllowed, smokingAllowed,
                    wheelchairAccess, electricVehicleCharge, comesFurnished,
                    laundryOption, parkingOption, imgUrl, description,
                    lat, lon, state);
        	house = housesDao.create(house);
        	messages.put("success", "Successfully created: " + "houseId: " + house.getHouseId());
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        req.getRequestDispatcher("/HouseCreate.jsp").forward(req, resp);
    }
}
