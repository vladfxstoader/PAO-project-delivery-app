package models;

import java.util.Objects;

public class Restaurant {
    private Integer id;
    private String name;
    private Float rating;
    private String address;

    public Restaurant(String name, Float rating, String address) {
        this.name = name;
        this.rating = rating;
        this.address = address;
    }

    public Restaurant() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Float getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(rating, that.rating) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, address);
    }



    @Override
    public String toString() {
        return "Restaurant - " + name + ", rating: " + rating + " (" + address + ")\n";

    }
}
