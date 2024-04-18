package MainPackage;

import CarPackage.Car;
import CarRentalSystemPackage.CarRentalSystem;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("1", "Mercedes", "S-class", 4000.0);
        Car car2 = new Car("2", "Audi", "A4", 3500.0);
        Car car3 = new Car("3", "Toyota", "Fortuner", 3000.0);
        Car car4 = new Car("4", "Hyundai", "Verna", 2500.0);
        Car car5 = new Car("5", "Ford", "Aspire", 1500.0);
        Car car6 = new Car("6", "Nissan", "Terrano", 2000.0);
        Car car7 = new Car("7", "Tesla", "Model 3", 3000.0);
        Car car8 = new Car("8", "Nissan", "Leaf", 1000.0);
        Car car9 = new Car("9", "Tata", "Tiago EV", 500.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);
        rentalSystem.addCar(car7);
        rentalSystem.addCar(car8);
        rentalSystem.addCar(car9);

        rentalSystem.Heading();
        rentalSystem.menu();
    }
}


