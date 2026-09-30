/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busoccupancy;

import java.util.Scanner;


public class BusOccupancy {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ===== 1. ENTER DATA =====
        System.out.print("How many stops are on the route? ");
        int stopCount = Integer.parseInt(input.nextLine().trim());

        System.out.print("Enter the bus's seating capacity: ");
        int capacity = Integer.parseInt(input.nextLine().trim());

        String[] stopNames = new String[stopCount];
        int[] boarding = new int[stopCount];
        int[] alighting = new int[stopCount];

        for (int i = 0; i < stopCount; i++) {
            System.out.println("\n--- Stop " + (i + 1) + " ---");
            System.out.print("Stop name: ");
            stopNames[i] = input.nextLine().trim();

            System.out.print("Passengers boarding at " + stopNames[i] + ": ");
            boarding[i] = Integer.parseInt(input.nextLine().trim());

            System.out.print("Passengers alighting at " + stopNames[i] + ": ");
            alighting[i] = Integer.parseInt(input.nextLine().trim());
        }

        // ===== 2. OCCUPANCY CALCULATION + 3. CAPACITY CHECK =====
        int[] occupancy = new int[stopCount];
        int currentPassengers = 0;      // start from an empty bus
        int overCapacityCount = 0;

        System.out.println("\n===== OCCUPANCY REPORT =====");
        for (int i = 0; i < stopCount; i++) {
            // Alighting more than currently on the bus -> data entry error
            if (alighting[i] > currentPassengers + boarding[i]) {
                System.out.println("Data entry error at " + stopNames[i]
                        + ": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                currentPassengers = 0;
            } else {
                currentPassengers = currentPassengers + boarding[i] - alighting[i];
            }

            occupancy[i] = currentPassengers;
            System.out.println("Passengers on the bus after " + stopNames[i] + ": " + currentPassengers);

            // Capacity check
            if (currentPassengers > capacity) {
                System.out.println("Warning: Bus is over capacity at " + stopNames[i] + "!");
                overCapacityCount++;
            }
        }
        System.out.println("Number of stops the bus was over capacity: " + overCapacityCount);

        // ===== 4. DISPLAY ALL =====
        System.out.println("\n===== ALL STOPS =====");
        System.out.printf("%-20s %-12s %-12s %-10s%n",
                "Stop Name", "Boarding", "Alighting", "Occupancy");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < stopCount; i++) {
            System.out.printf("%-20s %-12d %-12d %-10d%n",
                    stopNames[i], boarding[i], alighting[i], occupancy[i]);
        }

        // ===== 5. STATISTICS =====
        System.out.println("\n===== STATISTICS =====");

        // Busiest stop (highest number of passengers boarding)
        int busiestIndex = 0;
        for (int i = 1; i < stopCount; i++) {
            if (boarding[i] > boarding[busiestIndex]) {
                busiestIndex = i;
            }
        }
        System.out.println("Busiest stop: " + stopNames[busiestIndex]
                + " (" + boarding[busiestIndex] + " passengers boarding)");

        // Average occupancy across all stops
        int total = 0;
        for (int i = 0; i < stopCount; i++) {
            total += occupancy[i];
        }
        double average = (double) total / stopCount;
        System.out.printf("Average occupancy across all stops: %.2f%n", average);

        // Stops that exceeded capacity
        System.out.println("Stops that exceeded the bus capacity: " + overCapacityCount);

        // Final occupancy check
        int finalOccupancy = occupancy[stopCount - 1];
        if (finalOccupancy != 0) {
            System.out.println("Warning: " + finalOccupancy
                    + " passengers still on the bus after the final stop — please check your data.");
        } else {
            System.out.println("Final occupancy is 0. Data looks consistent.");
        }

        input.close();
    }
}
    
    

