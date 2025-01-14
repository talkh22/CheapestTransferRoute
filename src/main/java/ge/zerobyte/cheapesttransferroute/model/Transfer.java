package ge.zerobyte.cheapesttransferroute.model;

/**
 * The Transfer object class
 * with attributes: cost and weight,
 * and setter/getter methods.
 */


public class Transfer {

    private double weight;
    private double cost;


    public Transfer(double weight, double cost) {
        this.weight = weight;
        this.cost = cost;
    }


    public double getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        Transfer t = (Transfer)o;
        return t.getCost() == this.cost && t.getWeight() == this.weight;
    }

}
