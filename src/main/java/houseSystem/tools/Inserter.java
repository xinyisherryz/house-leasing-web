/*
 * @Author: Yufan Lu, 001582223
 */

package houseSystem.tools;

import houseSystem.dal.*;
import houseSystem.model.*;
import java.util.List;
import java.util.Date;
import java.sql.SQLException;

public class Inserter{
	public static void main(String[] args) throws SQLException {
		UsersDao userDao = UsersDao.getInstance();
		
		//Users
		Users user = new Users("firstName", "lastName", "123", "123@a.com", "male", 23);
		user = userDao.create(user);
		System.out.println(user);
	}
	
}