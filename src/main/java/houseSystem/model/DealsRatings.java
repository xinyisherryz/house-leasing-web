package houseSystem.model;

public class DealsRatings {
    protected int ratingId;
    protected Houses house;
    protected Owners owner;
    protected Dealers dealer;
    protected int rating;

    public DealsRatings(int ratingId, Houses house, Owners owner, Dealers dealer, int rating) {
        this.ratingId = ratingId;
        this.house = house;
        this.owner = owner;
        this.dealer = dealer;
        this.rating = rating;
    }

    public DealsRatings(int companyId) {
        this.ratingId = ratingId;
    }

    public DealsRatings(Houses house, Owners owner, Dealers dealer, int rating) {
        this.house = house;
        this.owner = owner;
        this.dealer = dealer;
        this.rating = rating;
    }

    public int getRatingId() {
        return this.ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
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

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
