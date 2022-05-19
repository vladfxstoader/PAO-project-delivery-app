package models;

public class Beverage extends Product {
    private Boolean isAlcoholic;
    private Integer ml;

    public Beverage(String name, Float price, Boolean isAlcoholic, Integer ml) {
        super(name, price);
        this.isAlcoholic = isAlcoholic;
        this.ml = ml;
    }

    public Beverage() {

    }

    public Boolean getAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(Boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public Integer getMl() {
        return ml;
    }

    public void setMl(Integer ml) {
        this.ml = ml;
    }

    public String toString() {
        String alcoholic;
        if (isAlcoholic)
            alcoholic = "alcoholic";
        else
            alcoholic = "non-alcoholic";
        return "Beverage - " + super.toString() + " - " + ml + "ml, " + alcoholic;
    }
}
