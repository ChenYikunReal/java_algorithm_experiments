package bt;

import java.io.*;

public class Solution1 {

    // 集装箱数
    private static int num;

    // 集装箱重量数组
    private static int[] weights;

    // 第一艘轮船的载重量
    private static int costs;

    // 当前载重量
    private static int tempWeight;

    // 当前最优载重量
    private static int bestWeight;

    // 剩余集装箱重量
    private static int left;

    // 当前解
    private static int[] tempX;

    // 当前最优解
    private static int[] bestX;

    private static void backtrace(int i) {
        // 到达叶子结点  i此时的值=叶节点+1
        if (i > num-1 && tempWeight > bestWeight) {
            for (int j = 0; j < num; j++) {
                bestX[j] = tempX[j];
            }
            bestWeight = tempWeight;
            return;
        }
        left -= weights[i];
        // 递归搜索 左子树
        if (tempWeight + weights[i] <= costs) {
            tempX[i] = 1;
            tempWeight += weights[i];
            backtrace(i+1);
            // 回溯
            tempWeight -= weights[i];
        }
        // 递归搜索 右子树
        if (tempWeight + left > bestWeight) {
            tempX[i] = 0;
            backtrace(i+1);
        }
        left += weights[i];
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/bt/input1.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/bt/output1.txt"))) {
            String[] line1 = reader.readLine().split("\\s+");
            num = Integer.parseInt(line1[0]);
            costs = Integer.parseInt(line1[1]);
            weights = new int[num];
            tempX = new int[num];
            bestX = new int[num];
            tempWeight = bestWeight = 0;
            String[] nums = reader.readLine().split("\\s+");
            for (int i = 0; i < num; i++) {
                weights[i] = Integer.parseInt(nums[i]);
                left += weights[i];
            }
            backtrace(0);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < num; i++) {
                builder.append(bestX[i]).append(' ');
            }
            String result = builder.toString().trim();
            System.out.println("最优解：" + result);
            System.out.println("最优值：" + bestWeight);
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
