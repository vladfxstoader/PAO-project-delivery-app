package models;

public class Food extends Product {
    private Boolean isVegan;
    private Integer prepareTime;
    private Integer size;

    public Food(String name, Float price, Boolean isVegan, Integer prepareTime, Integer size) {
        super(name, price);
        this.size = size;
        this.isVegan = isVegan;
        this.prepareTime = prepareTime;
    }

    public Food() {

    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    @Override
    public String toString() {
        String vegan;
        if (isVegan)
            vegan = "vegan";
        else
            vegan = "non-vegan";
        return "Food - " + super.toString() + " - " + size + "g, " + vegan +
                ", preparation time: " + prepareTime + " minutes";
    }
}
