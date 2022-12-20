package houseSystem.model;

public class Admins extends Users {
	
	public Admins(int userId, String firstName, String lastName, String phone, String email, String gender, int age) {
		super(userId, firstName, lastName, phone, email, gender, age);
	}
	
	public Admins(int userId) {
		super(userId);
	}
	
	public Admins(String firstName, String lastName, String phone, String email, String gender, int age) {
		super(firstName, lastName, phone, email, gender, age);
	}
}