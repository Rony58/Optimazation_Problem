import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static double Main(int capacity, double[] profits, double[] weights) {
        // Calculate profit per unit weight for each item
        double[] profitPerWeight = new double[profits.length];
        for (int i = 0; i < profits.length; i++) {
            profitPerWeight[i] = profits[i] / weights[i];
        }

        // Sort the items based on profit per unit weight in descending order
        Item[] items = new Item[profits.length];
        for (int i = 0; i < profits.length; i++) {
            items[i] = new Item(profits[i], weights[i], profitPerWeight[i]);
        }
        Arrays.sort(items);

        double totalProfit = 0;
        int currentCapacity = capacity;

        for (int i = 0; i < items.length; i++) {
            if (items[i].weight <= currentCapacity) {
                totalProfit += items[i].profit;
                currentCapacity -= items[i].weight;
            } else {
                double fraction = (double) currentCapacity / items[i].weight;
                totalProfit += items[i].profit * fraction;
                break;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        double[] profits = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the profit and weight for item " + (i + 1) + ":");
            profits[i] = scanner.nextDouble();
            weights[i] = scanner.nextDouble();
        }

        scanner.close();

        double maxProfit = Main(capacity, profits, weights);
        double roundedProfit = Math.round(maxProfit * 100.0) / 100.0;
        System.out.println("Maximum possible profit: $" + roundedProfit);
    }

    static class Item implements Comparable<Item> {
        double profit;
        double weight;
        double profitPerWeight;

        public Item(double profit, double weight, double profitPerWeight) {
            this.profit = profit;
            this.weight = weight;
            this.profitPerWeight = profitPerWeight;
        }

        @Override
        public int compareTo(Item other) {
            if (this.profitPerWeight > other.profitPerWeight) {
                return -1;
            } else if (this.profitPerWeight < other.profitPerWeight) {
                return 1;
            }
            return 0;
        }
    }
}
