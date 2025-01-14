package ge.zerobyte.cheapesttransferroute.model;

/**
 * The Transfer object class
 * with attributes: cost and weight,
 * and setter/getter methods.
 */


public class Transfer {

    private int weight;
    private int cost;


    public Transfer(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }


    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWeight() {
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
