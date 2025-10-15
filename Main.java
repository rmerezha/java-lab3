import java.util.Arrays;
import java.util.Objects;

/**
 * Demonstrates creation, sorting, and searching of an array of Airplane objects.
 * Sorting is done by model in ascending order and capacity in descending order.
 * After sorting, a target Airplane can be located in the array using equals().
 */
public class Main {

    /**
     * Entry point of the program.
     * Initializes an array of Airplane objects, sorts them, prints the sorted array,
     * and searches for a target Airplane.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Airplane[] planes = {
            new Airplane("Boeing", 180, 900, 5000, 40000),
            new Airplane("Airbus", 180, 920, 4800, 38000),
            new Airplane("Boeing", 200, 880, 5000, 41000),
            new Airplane("Cessna", 6, 300, 1200, 1200),
            new Airplane("Boeing", 220, 900, 5000, 39000)
        };

        System.out.println("Original array:");
        printPlanes(planes);

        Arrays.sort(planes);
        System.out.println("\nSorted array:");
        printPlanes(planes);

        Airplane target = new Airplane("Boeing", 200, 880, 5000, 41000);

        int index = findAirplane(planes, target);
        if (index != -1) {
            System.out.println("\nFound target airplane at index " + index + ":");
            System.out.println(planes[index]);
        } else {
            System.out.println("\nTarget airplane not found.");
        }
    }

    /**
     * Prints a formatted table of Airplane objects.
     *
     * @param planes the array of Airplane objects to print
     */
    private static void printPlanes(Airplane[] planes) {
        System.out.printf("%-10s %8s %10s %8s %10s%n", "Model", "Capacity", "MaxSpeed", "Range", "Weight");
        System.out.println("---------------------------------------------------------------");
        for (Airplane plane : planes) {
            System.out.printf("%-10s %8d %10.0f %8d %10.0f%n",
                    plane.model, plane.capacity, plane.maxSpeed, plane.range, plane.weight);
        }
    }

    /**
     * Searches for a target Airplane in an array using equals().
     *
     * @param planes the array of Airplane objects to search
     * @param target the Airplane object to find
     * @return the index of the target Airplane in the array, or -1 if not found
     */
    private static int findAirplane(Airplane[] planes, Airplane target) {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * Represents an airplane with model, capacity, maximum speed, range, and weight.
 * Implements Comparable to allow sorting by model (ascending) and capacity (descending),
 * then by remaining fields in ascending order.
 */
class Airplane implements Comparable<Airplane> {
    String model;
    int capacity;
    double maxSpeed;
    int range;
    double weight;

    /**
     * Constructs an Airplane object with all fields initialized.
     *
     * @param model    the airplane model
     * @param capacity passenger capacity
     * @param maxSpeed maximum speed in km/h
     * @param range    flight range in km
     * @param weight   weight in kg
     */
    public Airplane(String model, int capacity, double maxSpeed, int range, double weight) {
        this.model = model;
        this.capacity = capacity;
        this.maxSpeed = maxSpeed;
        this.range = range;
        this.weight = weight;
    }

    /**
     * Compares this Airplane with another for sorting.
     * Primary sort: model (ascending)
     * Secondary sort: capacity (descending)
     * Remaining fields: maxSpeed, range, weight (ascending)
     *
     * @param other the other Airplane to compare to
     * @return negative if this < other, zero if equal, positive if this > other
     */
    @Override
    public int compareTo(Airplane other) {
        int cmp = this.model.compareTo(other.model);
        if (cmp != 0) return cmp;
        cmp = Integer.compare(other.capacity, this.capacity);
        if (cmp != 0) return cmp;
        cmp = Double.compare(this.maxSpeed, other.maxSpeed);
        if (cmp != 0) return cmp;
        cmp = Integer.compare(this.range, other.range);
        if (cmp != 0) return cmp;
        return Double.compare(this.weight, other.weight);
    }

    /**
     * Checks equality based on all fields.
     *
     * @param o the object to compare
     * @return true if all fields are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airplane)) return false;
        Airplane airplane = (Airplane) o;
        return capacity == airplane.capacity &&
               Double.compare(airplane.maxSpeed, maxSpeed) == 0 &&
               range == airplane.range &&
               Double.compare(airplane.weight, weight) == 0 &&
               Objects.equals(model, airplane.model);
    }

    /**
     * Returns a hash code based on all fields.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(model, capacity, maxSpeed, range, weight);
    }

    /**
     * Returns a formatted string representation of the airplane.
     *
     * @return formatted string with all fields
     */
    @Override
    public String toString() {
        return String.format("%-10s %8d %10.0f %8d %10.0f",
                model, capacity, maxSpeed, range, weight);
    }
}
