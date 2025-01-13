// public class Car {
//     String make;
//     double price;
//     int year;
//     String color;
// }

import java.util.Arrays;

public class Car {
    private String make; // Object's fields.
    private double price;
    private int year;
    private String color;
    private String[] parts;

    // Constructor's parameters.
    public Car(String make, double price, int year, String color, String[] parts) {
        this.make = make;
        this.price = price;
        this.year = year;
        this.color = color;
        this.parts = Arrays.copyOf(parts, parts.length);// Initializing an array of 5 strings.
    }

    // copy constructor

    public Car(Car car) {
        this.make = car.make;
        this.price = car.price;
        this.year = car.year;
        this.color = car.color;
        this.parts = Arrays.copyOf(car.parts, car.parts.length);
    }
    // Gater's

    public String getMake() {
        return make;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    // setters

    public void setMake(String make) {
        this.make = make;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getParts() {
        return Arrays.copyOf(this.parts, this.parts.length);
    }

    public void setParts(String[] parts) {
        this.parts = Arrays.copyOf(parts, parts.length);
    }

    public void drive() {
        System.out.println(
                "You bought the beautiful  " + this.year + " " + this.make + " " + this.color + " with the price $"
                        + this.price);
        System.out.println("Driving the car...\n");
    }

    @Override
    public String toString() {
        return "Make: " + this.make + ".\n"
                + "Price: " + this.price + ".\n"
                + "Year: " + this.year + ".\n"
                + "Color: " + this.color + ".\n"
                + "Parts: " + Arrays.toString(parts) + ".\n";
    }

}