package houseSystem.model;


public class Dealers extends Users {
     
	protected Companies company;
	protected int yearsOfExperience;
	
	public Dealers(int userId, String firstName, String lastName, String phone, String email, String gender, 
			int age, Companies company, int yearsOfExperience) {
		super(userId, firstName, lastName, phone, email, gender, age);
		
		this.company = company;
		this.yearsOfExperience = yearsOfExperience;
	}
	
	public Dealers(int userId) {
		super(userId);
	}
	
	public Dealers(String firstName, String lastName, String phone, String email, String gender, 
			int age, Companies company, int yearsOfExperience) {
		super(firstName, lastName, phone, email, gender, age);
		
		this.company = company;
		this.yearsOfExperience = yearsOfExperience;
	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}	
	
	
}