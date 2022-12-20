package houseSystem.model;


public class Owners extends Users {
     
	protected Houses house;
	
	public Owners(int userId, String firstName, String lastName, String phone, String email, String gender, 
			int age, Houses house) {
		super(userId, firstName, lastName, phone, email, gender, age);
		
		this.house = house;
	}
	
	public Owners(int userId) {
		super(userId);
	}
	
	public Owners(String firstName, String lastName, String phone, String email, String gender, 
			int age, Houses house) {
		super(firstName, lastName, phone, email, gender, age);
		
		this.house = house;
	}

	public Houses getHouse() {
		return house;
	}

	public void setHouse(Houses house) {
		this.house = house;
	}


	
	
}