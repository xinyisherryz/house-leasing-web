package houseSystem.model;


public class Companies {
	protected int companyId;
	protected String companyName;
	protected int foundedTime; // or year?
	protected String founder;
	protected int companySize; // in SQL, it's String
	protected int ranking;
	
	public Companies(int companyId, String companyName, int foundedTime, String founder, int companySize, int ranking) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.foundedTime = foundedTime;
		this.founder = founder;
		this.companySize = companySize;
		this.ranking = ranking;
	}
	
	public Companies(int companyId) {
		this.companyId = companyId;
	}
	
	public Companies(String companyName, int foundedTime, String founder, int companySize, int ranking) {
		this.companyName = companyName;
		this.foundedTime = foundedTime;
		this.founder = founder;
		this.companySize = companySize;
		this.ranking = ranking;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(int foundedTime) {
		this.foundedTime = foundedTime;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public int getCompanySize() {
		return companySize;
	}

	public void setCompanySize(int companySize) {
		this.companySize = companySize;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	
	
}