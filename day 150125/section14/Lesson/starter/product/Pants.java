package product;

public class Pants extends Product {

    private int waist;
    private int length;

    public Pants(String price, String color, String brand, String waist, int length) {
        super(price, color, brand);
        this.waist = Integer.parseInt(waist);
        this.length = length;
    }

    public int getWaist() {
        return this.waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
