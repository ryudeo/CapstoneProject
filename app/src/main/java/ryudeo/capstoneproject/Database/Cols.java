package ryudeo.capstoneproject.Database;

/**
 * Created by RYU on 2016. 11. 28..
 */

public class Cols {
    private String type = "";
    private int quantity;
    private long timeStamp;

    public Cols(String type, int quantity, long timeStamp){
        this.type = type;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
    }
}
