import java.util.Arrays;
import java.util.Objects;

public class Main {

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

    private static void printPlanes(Airplane[] planes) {
        System.out.printf("%-10s %8s %10s %8s %10s%n", "Model", "Capacity", "MaxSpeed", "Range", "Weight");
        System.out.println("---------------------------------------------------------------");
        for (Airplane plane : planes) {
            System.out.printf("%-10s %8d %10.0f %8d %10.0f%n",
                    plane.model, plane.capacity, plane.maxSpeed, plane.range, plane.weight);
        }
    }

    private static int findAirplane(Airplane[] planes, Airplane target) {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

class Airplane implements Comparable<Airplane> {
    String model;
    int capacity;
    double maxSpeed;
    int range;
    double weight;

    public Airplane(String model, int capacity, double maxSpeed, int range, double weight) {
        this.model = model;
        this.capacity = capacity;
        this.maxSpeed = maxSpeed;
        this.range = range;
        this.weight = weight;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(model, capacity, maxSpeed, range, weight);
    }

    @Override
    public String toString() {
        return String.format("%-10s %8d %10.0f %8d %10.0f",
                model, capacity, maxSpeed, range, weight);
    }
}
