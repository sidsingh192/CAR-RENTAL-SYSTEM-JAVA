package CarRentalSystemPackage;
import CarPackage.Car;
import CustomerPackage.Customer;
import RentalPackage.Rental;
import java.util.Scanner;

public class CarRentalSystem {
    private Car[] cars;
    private Customer[] customers;
    private Rental[] rentals;
    private int carCount;
    private int customerCount;
    private int rentalCount;

    public CarRentalSystem() {
        cars = new Car[100]; // Adjust the size as needed
        customers = new Customer[100]; // Adjust the size as needed
        rentals = new Rental[100]; // Adjust the size as needed
        carCount = 0;
        customerCount = 0;
        rentalCount = 0;
    }

    public void addCar(Car car) {
        cars[carCount++] = car;
    }

    public void addCustomer(Customer customer) {
        customers[customerCount++] = customer;
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals[rentalCount++] = new Rental(car, customer, days);
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (int i = 0; i < rentalCount; i++) {
            if (rentals[i].getCar() == car) {
                rentalToRemove = rentals[i];
                break;
            }
        }
        if (rentalToRemove != null) {
            for (int i = 0; i < rentalCount; i++) {
                if (rentals[i] == rentalToRemove) {
                    System.arraycopy(rentals, i + 1, rentals, i, rentalCount - i - 1);
                    rentalCount--;
                    break;
                }
            }
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void Heading() {
        System.out.println("                  ***************************************************\n");
        System.out.println("                  *            WELCOME TO AIRCAR RENTAL             *\n");
        System.out.println("                  ***************************************************\n");
    }

    public void segments() {
        System.out.println("---------------Premium Segments---------------\n");
        System.out.println("1. Mercedes S-class - ₹4000 per day");
        System.out.println("2. Audi A4 - ₹3500 per day");
        System.out.println("3. Toyotal Fortuner - ₹3000 per day\n");
        System.out.println("---------------Basic Segments---------------\n");
        System.out.println("4. Hyundai Verna - ₹2500 per day");
        System.out.println("5. Ford Aspire - ₹1500 per day");
        System.out.println("6. Nissan Terrano - ₹2000 per day\n");
        System.out.println("---------------E-V Segments---------------\n");
        System.out.println("7. Tesla Model 3 - ₹3000 per day");
        System.out.println("8. Nissan Leaf - ₹1000 per day");
        System.out.println("9. Tata Tiago EV - ₹500 per day\n");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=========== AirCar Rental System ============\n");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Show rented cars for a customer");
            System.out.println("4. Exit\n");
            System.out.print("Enter your choice by pressing the Id no. : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\n=========== Rent a Car ============\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars segments: \n");
                segments();
                System.out.print("\nEnter the car ID you want to rent: ");
                int carChoice = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer("CUS" + (customerCount + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (int i = 0; i < carCount; i++) {
                    if (cars[i].isAvailable() && carChoice == Integer.parseInt(cars[i].getCarId())) {
                        selectedCar = cars[i];
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: ₹%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.\n");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n======== Return a Car ========\n");
                System.out.print("Enter the car ID you want to return: ");
                int carChoice = scanner.nextInt();
                scanner.nextLine();

                Car carToReturn = null;
                for (int i = 0; i < carCount; i++) {
                    if (carChoice == Integer.parseInt(cars[i].getCarId()) && !cars[i].isAvailable()) {
                        carToReturn = cars[i];
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (int i = 0; i < rentalCount; i++) {
                        if (rentals[i].getCar() == carToReturn) {
                            customer = rentals[i].getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                System.out.println("\n========== Show Rented Cars ==========\n");
                System.out.print("Enter the customer name: ");
                String customerName = scanner.nextLine();

                boolean foundCustomer = false;
                for (int i = 0; i < customerCount; i++) {
                    if (customerName.equals(customers[i].getName())) {
                        foundCustomer = true;
                        System.out.println("Rented cars for " + customers[i].getName() + ":\n");
                        for (int j = 0; j < rentalCount; j++) {
                            if (rentals[j].getCustomer() == customers[i]) {
                                Car rentedCar = rentals[j].getCar();
                                System.out.println(rentedCar.getCarId() + " - " + rentedCar.getBrand() + " "
                                        + rentedCar.getModel() + "  \n");
                            }
                        }
                        break;
                    }
                }

                if (!foundCustomer) {
                    System.out.println("Customer not found.");
                }
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }
}