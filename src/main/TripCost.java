package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: TripCost.java is a class that represents the cost calculation
 * for a trip, including distance, gas cost, hotel cost, food cost, attractions,
 * and total trip cost.
 */

public class TripCost {
    private final double distance; // Distance traveled for the trip
    private final double gasCostPerUnit; // Cost per unit of gas
    private final double gasMileage; // Gas mileage of the vehicle
    private final int numberOfDays; // Number of days for the trip
    private final double hotelCost; // Cost of hotel accommodation per day
    private final double foodCost; // Cost of food per day
    private final double attractions; // Cost of attractions
    private final double totalTripCost; // Total cost of the trip

    // Constructor
    public TripCost(double distance, double gasCostPerUnit, double gasMileage,
            int numberOfDays, double hotelCost, double foodCost, double attractions) {
        this.distance = distance;
        this.gasCostPerUnit = gasCostPerUnit;
        this.gasMileage = gasMileage;
        this.numberOfDays = numberOfDays;
        this.hotelCost = hotelCost;
        this.foodCost = foodCost;
        this.attractions = attractions;
        this.totalTripCost = calculateTotalTripCost();
    }

    // Getter methods
    public double getDistance() {
        return distance;
    }

    public double getGasCostPerUnit() {
        return gasCostPerUnit;
    }

    public double getGasMileage() {
        return gasMileage;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public double getHotelCost() {
        return hotelCost;
    }

    public double getFoodCost() {
        return foodCost;
    }

    public double getAttractions() {
        return attractions;
    }

    public double getTotalTripCost() {

        return totalTripCost;
    }

    // Method to compute and return total trip cost
    public double calculateTotalTripCost() {
        // Calculate gasoline cost based on distance and gas mileage
        double gasolineCost = (distance / gasMileage) * gasCostPerUnit;
        // Calculate total cost including hotel, food, and attractions
        double totalCost = gasolineCost + (hotelCost + foodCost) * numberOfDays + attractions;
        // Check if total cost is non-negative
        if (totalCost <= 0) {
            throw new IllegalArgumentException("Total trip cost cannot be negative or zero.");
        }
        return totalCost;
    }
}
