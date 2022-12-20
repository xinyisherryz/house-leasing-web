/**
 * @author zeyushen;
 */
package houseSystem.model;

public class Buyers extends Users {
	
	public Buyers(int userId, String firstName, String lastName, String phone, String email, String gender, int age) {
		super(userId, firstName, lastName, phone, email, gender, age);
	}
	
	public Buyers(int userId) {
		super(userId);
	}
	
	public Buyers(String firstName, String lastName, String phone, String email, String gender, int age) {
		super(firstName, lastName, phone, email, gender, age);
	}
}