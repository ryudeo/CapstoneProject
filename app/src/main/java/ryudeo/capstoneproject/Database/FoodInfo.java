package ryudeo.capstoneproject.Database;


/**
 * Created by RYU on 2016. 11. 29..
 */

public class FoodInfo{

    private String name;
    private int quantity;
    private String kcal = "0kcal";
    private String ea;

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {

        String kcalRemovedPostfix = kcal.replace("kcal", "");

        int kcal = 0;
        if (kcalRemovedPostfix != null && !kcalRemovedPostfix.isEmpty()) {

            kcal = Integer.parseInt(kcalRemovedPostfix);
        }
        return kcal;
    }


    public FoodInfo() {

    }

    public FoodInfo(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;

    }

}
