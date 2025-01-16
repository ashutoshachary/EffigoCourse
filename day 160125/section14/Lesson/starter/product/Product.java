package product;

public abstract class Product {
    private double price;
    private String color;
    private String brand;

    public Product(double price2, String color2, String brand2) {
        // TODO Auto-generated constructor stub
        this.price = price2;
        this.color = color2;
        this.brand = brand2;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void fold() {
        System.out.println("Product folded" + this.getBrand() + " " + this.getClass().getSimpleName());
    }

    public abstract void wear();

}
