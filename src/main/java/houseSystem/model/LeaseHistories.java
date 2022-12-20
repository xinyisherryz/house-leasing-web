package houseSystem.model;

public class LeaseHistories {
    protected int leaseId;
    protected Houses house;
    protected Owners owner;
    protected Dealers dealer;
    protected Buyers buyer;
    protected double price;

    public LeaseHistories(int leaseId, Houses house, Owners owner, Dealers dealer, Buyers buyer, double price) {
        this.leaseId = leaseId;
        this.house = house;
        this.owner = owner;
        this.dealer = dealer;
        this.buyer = buyer;
        this.price = price;
    }

    public LeaseHistories(int companyId) {
        this.leaseId = leaseId;
    }

    public LeaseHistories(Houses house, Owners owner, Dealers dealer, Buyers buyer, double price) {
        this.house = house;
        this.owner = owner;
        this.dealer = dealer;
        this.buyer = buyer;
        this.price = price;
    }

    public int getLeaseId() {
        return this.leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public Houses getHouse() {
        return house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Dealers getDealer() {
        return dealer;
    }

    public void setDealer(Dealers dealer) {
        this.dealer = dealer;
    }

    public Buyers getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyers buyer) {
        this.buyer = buyer;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
