package product;

public class Shirt extends Product {

    private Size size;

    public Shirt(double price, String color, String brand, Size size) {
        super(price, color, brand);
        this.size = size;
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public void fold() {
        super.fold();
    }

    @Override
    public void wear() {
        System.out.println("Wearing shirt of size " + this.getSize());
    }

    @Override
    public String toString() {
        return "Shirt [size=" + size + ", brand=" + getBrand() + ", color=" + getColor() + ", price=" + getPrice()
                + "]";
    }
}
