import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Consol {
        private static Connection connection;

        public static void main(String[] args) throws SQLException {
            TestConnection testConnection = new TestConnection();
            connection = testConnection.createConnection();

            try {
                displayMenu();
            } finally {
                testConnection.closeConnection();
            }
        }

        private static void displayMenu() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("==== Car Rental Application ====");
                System.out.println("1. Insert Car");
                System.out.println("2. Insert Customer");
                System.out.println("3. Insert Rental");
                System.out.println("4. Display All Cars");
                System.out.println("5. Display All Customers");
                System.out.println("6. Display All Rentals");
                System.out.println("7. Update Car");
                System.out.println("8. Update Customer");
                System.out.println("9. Update Rental");
                System.out.println("10. Delete Car");
                System.out.println("11. Delete Customer");
                System.out.println("12. Delete Rental");
                System.out.println("13. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        insertCar();
                        break;
                    case 2:
                        insertCustomer();
                        break;
                    case 3:
                        insertRental();
                        break;
                    case 4:
                        displayAllCars();
                        break;
                    case 5:
                        displayAllCustomers();
                        break;
                    case 6:
                        displayAllRentals();
                        break;
                    case 7:
                        updateCar();
                        break;
                    case 8:
                        updateCustomer();
                        break;
                    case 9:
                        updateRental();
                        break;
                    case 10:
                        deleteCar();
                        break;
                    case 11:
                        deleteCustomer();
                        break;
                    case 12:
                        deleteRental();
                        break;
                    case 13:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 13);

            scanner.close();
        }
        private static void insertCar() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== Insert Car ====");

            System.out.print("Enter car brand and model: ");
            String brandModel = scanner.nextLine();

            System.out.print("Enter fuel type: ");
            String fuelType = scanner.nextLine();

            System.out.print("Enter registration number: ");
            String regNumber = scanner.nextLine();

            System.out.print("Enter registration date: ");
            String regDate = scanner.nextLine();

            System.out.print("Enter vehicle kilometers: ");
            int kilometers = scanner.nextInt();

            try {
                Bil car = new Bil(brandModel, fuelType, regNumber, regDate, kilometers);
                car.saveToDatabase(connection);
                System.out.println("Car successfully inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting car: " + e.getMessage());
            }
        }
        private static void deleteCar() throws SQLException {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Car ID to delete: ");
            int carID = scanner.nextInt();

            String query = "DELETE FROM Biler WHERE BilID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, carID);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Car deleted successfully.");
                } else {
                    System.out.println("Car not found or deletion failed.");
                }
            }
        }

        private static void updateCar() throws SQLException {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Car ID to update: ");
            int carID = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            System.out.println("Enter updated car details:");
            System.out.print("Brand and Model: ");
            String brandModel = scanner.nextLine();
            System.out.print("Fuel Type: ");
            String fuelType = scanner.nextLine();
            System.out.print("Registration Number: ");
            String registrationNumber = scanner.nextLine();
            System.out.print("Registration Date: ");
            String registrationDate = scanner.nextLine();
            System.out.print("Mileage: ");
            int mileage = scanner.nextInt();

            String query = "UPDATE Biler SET MaerkeModel = ?, BraendstofType = ?, Registreringsnummer = ?, " +
                    "RegistreringDATO = ?, KoeretoejKilometer = ? WHERE BilID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, brandModel);
                preparedStatement.setString(2, fuelType);
                preparedStatement.setString(3, registrationNumber);
                preparedStatement.setString(4, registrationDate);
                preparedStatement.setInt(5, mileage);
                preparedStatement.setInt(6, carID);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Car updated successfully.");
                } else {
                    System.out.println("Car not found or update failed.");
                }
            }
        }

        private static void insertCustomer() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== Insert Customer ====");

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();

            System.out.print("Enter postal code: ");
            String postalCode = scanner.nextLine();

            System.out.print("Enter city name: ");
            String city = scanner.nextLine();

            System.out.print("Enter mobile phone: ");
            String mobilePhone = scanner.nextLine();

            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter driver's license number: ");
            String licenseNumber = scanner.nextLine();

            System.out.print("Enter license issuance date: ");
            String issuanceDate = scanner.nextLine();

            try {
                Lejer customer = new Lejer(name, address, Integer.parseInt(postalCode), city, mobilePhone, phone, email, licenseNumber, issuanceDate);
                customer.saveToDatabase(connection);
                System.out.println("Customer successfully inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting customer: " + e.getMessage());
            }
        }
        private static void updateCustomer() throws SQLException {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Customer ID to update: ");
            int customerID = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            System.out.println("Enter updated customer details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Postal Code: ");
            int postalCode = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            System.out.print("City: ");
            String city = scanner.nextLine();
            System.out.print("Mobile Phone: ");
            String mobilePhone = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Driver's License Number: ");
            String licenseNumber = scanner.nextLine();
            System.out.print("License Issuance Date: ");
            String issuanceDate = scanner.nextLine();

            String query = "UPDATE Lejere SET Navn = ?, Adresse = ?, PostnummerID = ?, ByNavn = ?, " +
                    "Mobiltelefon = ?, Telefon = ?, Email = ?, KoerekortNummer = ?, KoerekortUdstedelsesdato = ? " +
                    "WHERE LejerID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setInt(3, postalCode);
                preparedStatement.setString(4, city);
                preparedStatement.setString(5, mobilePhone);
                preparedStatement.setString(6, phone);
                preparedStatement.setString(7, email);
                preparedStatement.setString(8, licenseNumber);
                preparedStatement.setString(9, issuanceDate);
                preparedStatement.setInt(10, customerID);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer updated successfully.");
                } else {
                    System.out.println("Customer not found or update failed.");
                }
            }
        }

    private static void deleteCustomer() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID to delete: ");
        int customerID = scanner.nextInt();

        String query = "DELETE FROM Lejere WHERE LejerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found or deletion failed.");
            }
        }
    }


    private static void insertRental() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Insert Rental ====");

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter rental start date and time: ");
        String startDate = scanner.nextLine();

        System.out.print("Enter rental end date and time: ");
        String endDate = scanner.nextLine();

        System.out.print("Enter max kilometers allowed: ");
        int maxKilometers = scanner.nextInt();

        System.out.print("Enter starting kilometers: ");
        int startKilometers = scanner.nextInt();

        try {
            Lejekontrakt rental = new Lejekontrakt(customerId, carId, startDate, endDate, maxKilometers, startKilometers);
            rental.saveToDatabase(connection);
            System.out.println("Rental successfully inserted!");
        } catch (SQLException e) {
            System.out.println("Error inserting rental: " + e.getMessage());
        }
    }
    private static void updateRental() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Rental ID to update: ");
        int rentalID = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.println("Enter updated rental details:");
        System.out.print("Customer ID: ");
        int customerID = scanner.nextInt();
        System.out.print("Car ID: ");
        int carID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Start Date and Time: ");
        String startDate = scanner.nextLine();
        System.out.print("End Date and Time: ");
        String endDate = scanner.nextLine();
        System.out.print("Max Kilometers: ");
        int maxKilometers = scanner.nextInt();
        System.out.print("Starting Kilometers: ");
        int startKilometers = scanner.nextInt();

        String query = "UPDATE Lejekontrakter SET LejerID = ?, BilID = ?, FraDatoTid = ?, TilDatoTid = ?, " +
                "MaxKilometer = ?, StartKilometer = ? WHERE KontraktID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, carID);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setInt(5, maxKilometers);
            preparedStatement.setInt(6, startKilometers);
            preparedStatement.setInt(7, rentalID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Rental updated successfully.");
            } else {
                System.out.println("Rental not found or update failed.");
            }
        }
    }

    private static void deleteRental() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Rental ID to delete: ");
        int rentalID = scanner.nextInt();

        String query = "DELETE FROM Lejekontrakter WHERE KontraktID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rentalID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Rental deleted successfully.");
            } else {
                System.out.println("Rental not found or deletion failed.");
            }
        }
    }


    private static void displayAllCars() {
        try {
            List<Bil> cars = Bil.getAllBiler(connection);
            System.out.println("==== All Cars ====");
            for (Bil car : cars) {
                System.out.println(car);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving cars: " + e.getMessage());
        }
    }

    private static void displayAllCustomers() {
        try {
            List<Lejer> customers = Lejer.getAllLejere(connection);
            System.out.println("==== All Customers ====");
            for (Lejer customer : customers) {
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
    }

    private static void displayAllRentals() {
        try {
            List<Lejekontrakt> rentals = Lejekontrakt.getAllLejekontrakter(connection);
            System.out.println("==== All Rentals ====");
            for (Lejekontrakt rental : rentals) {
                System.out.println(rental);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving rentals: " + e.getMessage());
        }
    }

}
