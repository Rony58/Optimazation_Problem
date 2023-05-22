import java.util.Scanner;

public class KnapsackDynamic {

    public static int knapsackDynamic(int capacity, int[] profits, int[] weights, int numItems) {
        int[][] dp = new int[numItems + 1][capacity + 1];

        // Build the dynamic programming table
        for (int i = 1; i <= numItems; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(profits[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[numItems][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        int[] profits = new int[numItems];
        int[] weights = new int[numItems];

        for (int i = 0; i < numItems; i++) {
            System.out.println("Enter the profit and weight for item " + (i + 1) + ":");
            profits[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }

        scanner.close();

        int maxProfit = knapsackDynamic(capacity, profits, weights, numItems);
        System.out.println("Maximum profit: " + maxProfit);
    }
}
