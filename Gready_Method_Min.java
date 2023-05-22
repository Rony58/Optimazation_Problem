import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int Main(int targetProfit, double[] profits, double[] weights) {
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

        int count = 0;
        int currentProfit = 0;

        for (int i = 0; i < items.length; i++) {
            if (currentProfit >= targetProfit) {
                break;
            }

            int numItems = (int) Math.min((targetProfit - currentProfit) / items[i].profit, 1);
            currentProfit += numItems * items[i].profit;
            count += numItems;
        }

        if (currentProfit < targetProfit) {
            return -1; // Target profit cannot be achieved
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the target profit: ");
        int targetProfit = scanner.nextInt();

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

        int minItems = Main(targetProfit, profits, weights);

        if (minItems == -1) {
            System.out.println("Target profit cannot be achieved.");
        } else {
            System.out.println("Minimum number of items to achieve the target profit: " + minItems);
        }
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
