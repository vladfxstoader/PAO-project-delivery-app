package models;

public class Driver extends Person {
    private Float rating;

    public Driver(String name, Integer age, String email, String phoneNumber, Float rating) {
        super(name, age, email, phoneNumber);
        this.rating = rating;
    }

    public Driver() {
    }

    @Override
    public String toString() {
        return "Driver - " + super.toString() +
                ", rating: " + rating;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
