package houseSystem.model;

public class CreditCards {
	protected String cardNumber;
	protected String firstName;
	protected String lastName;
	protected int expYear;
	protected int expMonth;
	protected Buyers buyer;
	
	public CreditCards(String cardNumber, String firstName, String lastName, int expYear, int expMonth, Buyers buyer) {
		this.cardNumber = cardNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.expYear = expYear;
		this.expMonth = expMonth;
		this.buyer = buyer;
	}
	
	public CreditCards(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public Buyers getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyers buyer) {
		this.buyer = buyer;
	}
	
}