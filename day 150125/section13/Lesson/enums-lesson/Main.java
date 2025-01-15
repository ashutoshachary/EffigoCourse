import model.Car;
import model.Car.TrafficLight;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("Nissan", 2020);
        car.drive(TrafficLight.RED);
    }

}
