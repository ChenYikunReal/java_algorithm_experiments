package dp;

import java.io.*;

/**
 * 实验2-2 租用游艇问题
 */
public class Solution2 {

    private static int[][] cost;

    private static final int INF = 0x3f3f3f3f;

    private static int num;

    /**
     * Floyd 求最短路
     * 动态规划思想，适用于有向图，仅能从上游移动到下游
     */
    private static void shortestPath() {
        for (int k = 0; k < num; k++) {
            for (int i = 0; i < k; i++) { // 只从上游到下游，i不必大于等于k
                for (int j = i+1; j < num; j++) { // j大于i即可
                    if (cost[i][k] != INF && cost[k][j] != INF && cost[i][k] + cost[k][j] < cost[i][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dp/input2.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dp/output2.txt"))){
            num = Integer.parseInt(reader.readLine());
            cost = new int[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    cost[i][j] = INF;
                }
            }
            for (int i = 0; i < num-1; i++) {
                String[] nums = reader.readLine().split("\\s+");
                for (int j = i+1; j < num; j++) {
                    cost[i][j] = Integer.parseInt(nums[j-i-1]);
                }
            }
            //求最短路并输出到文件
            shortestPath();
            writer.write(Integer.toString(cost[0][num-1]));
            // 标准输出各顶点之间的最短路径
            for (int i = 0; i < num; i++) {
                StringBuilder result = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    if (cost[i][j] == INF || i > j) {
                        result.append("INF\t");
                    } else {
                        result.append(cost[i][j]).append('\t');
                    }
                }
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
