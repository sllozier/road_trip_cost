package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: GenerateTripData.java is a utility class to generate random trip data,
 * calculate the total trip cost for each trip, and write the trip data to a JSON file.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTripData {

    public static void main(String[] args) {
        String filePath = "trips.json";
        int numTrips = 50;

        // Generate random trips
        List<Trip> trips = generateRandomTrips(numTrips);

        // Write generated trip data to file
        writeTripsToFile(trips, filePath);

    }

    // Generate random trips
    private static List<Trip> generateRandomTrips(int numTrips) {
        List<Trip> trips = new ArrayList<>();
        Random random = new Random();

        // Randomize trip parameters
        for (int i = 0; i < numTrips; i++) {
            // Random distance between 50 and 1000
            double distance = 50 + random.nextDouble() * 950;
            // Random gas cost between 1.5 and 5.0
            double gasCostPerUnit = 1.5 + random.nextDouble() * 3.5;
            // Random gas mileage between 10 and 40
            double gasMileage = 10 + random.nextDouble() * 30;
            // Random number of days between 1 and 10
            int numberOfDays = 1 + random.nextInt(10);
            // Random hotel cost between 0 and 500
            double hotelCost = 0 + random.nextDouble() * 500;
            // Random food cost between 0 and 300
            double foodCost = 0 + random.nextDouble() * 300;
            // Random attractions cost between 0 and 200
            double attractions = 0 + random.nextDouble() * 200;

            // Calculate total trip cost
            double totalTripCost = calculateTotalTripCost(distance, gasCostPerUnit, gasMileage, numberOfDays, hotelCost,
                    foodCost, attractions);

            // Create Trip object and add to list
            Trip trip = new Trip(distance, gasCostPerUnit, gasMileage, numberOfDays, hotelCost, foodCost, attractions,
                    totalTripCost);
            trips.add(trip);
        }

        return trips;
    }

    // Calculate total trip cost based on trip parameters
    private static double calculateTotalTripCost(double distance, double gasCostPerUnit, double gasMileage,
            int numberOfDays,
            double hotelCost, double foodCost, double attractions) {
        double gasolineCost = (distance / gasMileage) * gasCostPerUnit;
        double totalCost = gasolineCost + (hotelCost + foodCost) * numberOfDays + attractions;
        return totalCost;
    }

    // Write trips data to a JSON file
    private static void writeTripsToFile(List<Trip> trips, String filePath) {
        // Open a FileWriter for the specified file path
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the opening bracket of the JSON array
            writer.write("[\n");
            // Iterate through each Trip object in the list
            for (int i = 0; i < trips.size(); i++) {
                // Write the JSON representation of the current Trip object
                writer.write(trips.get(i).toJson());
                // If it's not the last Trip object in the list
                if (i < trips.size() - 1) {
                    // Write a comma and newline to separate the JSON objects
                    writer.write(",\n");
                }
            }
            // Write the closing bracket of the JSON array
            writer.write("\n]");
            // Catch any IOException that may occur during file writing
        } catch (IOException e) {
            // Print the stack trace of the IOException
            e.printStackTrace();
        }
    }

    // Trip class representing a single trip
    static class Trip {
        double distance;
        double gasCostPerUnit;
        double gasMileage;
        int numberOfDays;
        double hotelCost;
        double foodCost;
        double attractions;
        double totalTripCost;

        // Constructor for Trip
        public Trip(double distance, double gasCostPerUnit, double gasMileage, int numberOfDays, double hotelCost,
                double foodCost, double attractions, double totalTripCost) {
            this.distance = distance;
            this.gasCostPerUnit = gasCostPerUnit;
            this.gasMileage = gasMileage;
            this.numberOfDays = numberOfDays;
            this.hotelCost = hotelCost;
            this.foodCost = foodCost;
            this.attractions = attractions;
            this.totalTripCost = totalTripCost;
        }

        // Method to convert Trip object to JSON format
        public String toJson() {
            return String.format(
                    "{ \"distance\": %.2f, \"gasCostPerUnit\": %.2f, \"gasMileage\": %.2f, \"numberOfDays\": %d, \"hotelCost\": %.2f, \"foodCost\": %.2f, \"attractions\": %.2f, \"totalTripCost\": %.2f }",
                    distance, gasCostPerUnit, gasMileage, numberOfDays, hotelCost, foodCost, attractions,
                    totalTripCost);
        }
    }
}
