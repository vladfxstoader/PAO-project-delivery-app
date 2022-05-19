package models;

public class User extends Person {
    private String address;

    public User(String name, Integer age, String email, String phoneNumber, String address) {
        super(name, age, email, phoneNumber);
        this.address = address;
    }

    public User() {
        
    }

    @Override
    public String toString() {
        return "User - " + super.toString() +
                ", address: " + address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
