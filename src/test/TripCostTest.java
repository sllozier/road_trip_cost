package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: TripCostTest.java is a test class that performs various tests on the TripCost class,
 * including positive and negative tests to ensure correct behavior in different scenarios.
 */

import main.TripCost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class TripCostTest {

    public static void main(String[] args) {
        // Execute tests and report results
        boolean test1 = testCalculateTotalTripCostFromJson();
        boolean test2 = testNegativeGasMileage();

        // Output the results of the test
        System.out.println("Test 1: Calculate Total Trip Cost from JSON - " + (test1 ? "Passed" : "Failed"));
        System.out.println("Test 2: Negative Test with Invalid Gas Mileage - " + (test2 ? "Passed" : "Failed"));

    }

    // Positive Test 1: Calculate Total Trip Cost from JSON.
    public static boolean testCalculateTotalTripCostFromJson() {
        // Determine the project root directory in a flexible way
        // This helps when using Makefile
        Path projectRootPath = Paths.get(System.getProperty("user.dir"));
        Path jsonFilePath = projectRootPath.resolve("trips.json");

        try {
            // Read JSON data from file

            String jsonData = new String(Files.readAllBytes(jsonFilePath));

            // Parse JSON data and get list of all trips
            List<main.TripCost> trips = parseJsonData(jsonData);

            // Randomly select a trip
            main.TripCost randomTrip = getRandomTrip(trips);

            // Calculate total trip cost for the random trip
            double calculatedCost = randomTrip.calculateTotalTripCost();

            double totalTripCostFromJson = randomTrip.getTotalTripCost();

            // Compare the calculated total trip cost with the totalTripCost from the JSON
            if (Math.abs(calculatedCost - totalTripCostFromJson) > 0.001) {

                return false;
            }

            return true; // Test passed if there is no mismatch
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Negative Test 1: Invalid Gas Mileage
    public static boolean testNegativeGasMileage() {
        // Determine the project root directory in a flexible way
        Path projectRootPath = Paths.get(System.getProperty("user.dir"));
        Path jsonFilePath = projectRootPath.resolve("trips.json");

        try {
            // Read JSON data from file
            String jsonData = new String(Files.readAllBytes(jsonFilePath));

            // Parse JSON data and calculate total trip cost
            List<main.TripCost> trips = parseJsonData(jsonData.toString());
            List<main.TripCost> modifiedTrips = new ArrayList<>();

            // Modify gas mileage to an invalid value for each trip
            for (main.TripCost trip : trips) {
                main.TripCost modifiedTrip = new TripCost(
                        trip.getDistance(),
                        trip.getGasCostPerUnit(),
                        // Example of invalid gas mileage
                        -10,
                        trip.getNumberOfDays(),
                        trip.getHotelCost(),
                        trip.getFoodCost(),
                        trip.getAttractions());
                modifiedTrips.add(modifiedTrip);
            }

            // Calculate total trip cost for modified trips
            for (main.TripCost trip : modifiedTrips) {
                double totalCost = trip.calculateTotalTripCost();
                // Check if total cost is negative or zero
                if (totalCost <= 0) {
                    return false; // Test failed
                }
            }

            // Assuming all trips are processed successfully
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to parse JSON data and create a list of Trip objects
    private static List<main.TripCost> parseJsonData(String jsonData) {
        List<main.TripCost> trips = new ArrayList<>();

        // Remove "[" at the beginning and "]" at the end of the JSON data
        if (jsonData.startsWith("[") && jsonData.endsWith("]")) {
            jsonData = jsonData.substring(1, jsonData.length() - 1);
        }

        // Split JSON string by "},{" to separate trips
        String[] tripStrings = jsonData.split("\\},\\s*\\{");

        // Parse each trip string and create Trip objects
        for (String tripString : tripStrings) {
            // Remove leading "{" and trailing "}" from each trip string
            tripString = tripString.replaceAll("^\\{|\\}$", "");

            // Parse single trip data and add Trip object to the list
            trips.add(parseSingleTrip(tripString));
        }

        return trips;
    }

    // Method to parse a single trip data and create a Trip object
    private static main.TripCost parseSingleTrip(String tripData) {
        double distance = 0, gasCostPerUnit = 0, gasMileage = 0, hotelCost = 0, foodCost = 0, attractions = 0;
        int numberOfDays = 0;

        // Split trip data into key-value pairs
        String[] keyValuePairs = tripData.split(",\\s*");

        // Parse each key-value pair and extract the corresponding value
        for (String pair : keyValuePairs) {
            String[] parts = pair.split(":\\s*");
            String key = parts[0].replaceAll("^\"|\"$", ""); // Remove double quotes from key
            String value = parts[1].replaceAll("^\"|\"$", ""); // Remove double quotes from value

            switch (key) {
                case "distance":
                    distance = Double.parseDouble(value);
                    break;
                case "gasCostPerUnit":
                    gasCostPerUnit = Double.parseDouble(value);
                    break;
                case "gasMileage":
                    gasMileage = Double.parseDouble(value);
                    break;
                case "numberOfDays":
                    numberOfDays = Integer.parseInt(value);
                    break;
                case "hotelCost":
                    hotelCost = Double.parseDouble(value);
                    break;
                case "foodCost":
                    foodCost = Double.parseDouble(value);
                    break;
                case "attractions":
                    attractions = Double.parseDouble(value);
                    break;
            }
        }

        // Create and return Trip object
        return new main.TripCost(distance, gasCostPerUnit, gasMileage, numberOfDays, hotelCost, foodCost, attractions);
    }

    // Method to randomly select a trip from the list
    private static main.TripCost getRandomTrip(List<main.TripCost> trips) {
        Random random = new Random();
        int randomIndex = random.nextInt(trips.size());
        return trips.get(randomIndex);
    }

}
