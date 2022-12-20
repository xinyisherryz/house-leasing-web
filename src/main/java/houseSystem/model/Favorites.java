package houseSystem.model;

public class Favorites {
    protected int favoriteId;
    protected Houses house;
    protected Buyers buyer;

    public Favorites(int favoriteId, Houses house, Buyers buyer) {
        this.favoriteId = favoriteId;
        this.house = house;
        this.buyer = buyer;
    }

    public Favorites(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Favorites(Houses house, Buyers buyer) {
        this.house = house;
        this.buyer = buyer;
    }

    public int getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Houses getHouse() {
        return house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }

    public Buyers getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyers buyer) {
        this.buyer = buyer;
    }
}
