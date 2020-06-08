package dp;

import java.io.*;
import java.util.Arrays;

public class Solution1 {

    private static int[] coins;

    private static int amount, n;

    /**
     * 求出凑到指定金额的最小硬币数
     * 思路近似于完全背包问题
     * @return 不能凑出返回-1，能凑出返回硬币数量
     */
    private static int coinChange() {
        int inf = amount + 1;
        int[] dp = new int[amount+1]; // dp 大小比硬币数量多1
        Arrays.fill(dp, inf);
        // dp[i] 表示凑出i需要的最少硬币数，dp[i] == inf 表示凑不出i
        dp[0] = 0; // 凑出0需要0块硬币
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                int val = coins[j];
                if (val <= i) {
                    dp[i] = Math.min(dp[i], dp[i-val]+1);
                }
            }
        }
        System.out.println("DP array: " + Arrays.toString(dp));
        return (dp[amount] == inf) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dp/input1.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dp/output1.txt"))){
            n = Integer.parseInt(reader.readLine());
            if (n == 0) {
                System.out.println("[Info] The number of coins is 0");
            }
            coins = new int[n];
            String[] nums = reader.readLine().split("\\s+");
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(nums[i]);
            }
            amount = Integer.parseInt(reader.readLine());
            if (amount == 0) {
                System.out.println("[Info] The target amount is 0");
            }
            int result = coinChange();
            System.out.println("The result is: " + result);
            writer.write(Integer.toString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
