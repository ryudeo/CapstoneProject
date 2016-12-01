package ryudeo.capstoneproject.Database;

/**
 * Created by RYU on 2016. 12. 1..
 */

public class ExerInfo {

    private String name;
    private int quantity;

    public ExerInfo() {

    }
    public ExerInfo(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
