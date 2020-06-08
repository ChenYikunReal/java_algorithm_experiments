package dc;

import java.io.*;

public class Solution2 {

    private static int[][] grayCode;

    // 格雷码数量、格雷码的二进制位数
    private static final int MAX_NUM = 1025, MAX_BIT = 11;

    /**
     * 递归函数
     * @param n
     * @param num = 2^n
     */
    private static void function(int n, int num) {
        if (n == 1) {
            grayCode[0][0] = 0;
            grayCode[1][0] = 1;
            return;
        }
        for(int i = 0; i < num/2; i++) {
            grayCode[i][n-1] = 0;
            grayCode[num-i-1][n-1] = 1;
        }//根据规则 这一次各个位置 末位填0或1
        function(n-1, num/2);
        for(int i = num/2; i < num; i++) {
            for(int j=0; j<n-1; j++) {
                grayCode[i][j]= grayCode[num-i-1][j];
            }
        }
    }

    /**
     * 生成指定位数的格雷码
     * 把原问题分解为两个子问题，分别对两个子问题的每个数组后一位加0和1。
     * 以 mid 为中心，在第 b 位，前 n/2 行填 reverse，后 n/2 行填 1 - reverse
     * @param mid 中间位下标
     * @param n 涉及的格雷码的个数
     * @param b 当前位数，初始为1，随递归增加
     * @param reverse 为0则下一位为01，为1则下一位为10
     */
    private static void getGrayCode(int mid, int n, int b, int reverse) {
        if (n == 1) {
            return;
        }
        System.out.println("mid: " + mid + ", nLine: " + n + ", bit: " + b + ", reverse: " + reverse);
        // 第 b 位，包括 mid 的前 n/2 行，填 reverse
        for (int i = mid - n / 2 + 1; i <= mid; i++) {
            grayCode[i][b] = reverse;
            System.out.println("[" + i + "][" + b + "] ");
        }
        System.out.println("= " + reverse);
        // 第 b 位，不包含 mid 的后 n/2 行，填 1 - reverse
        for (int j = mid + 1; j < mid + n / 2 + 1; j++) {
            grayCode[j][b] = 1 - reverse;
            System.out.println("[" + j + "][" + b + "] ");
        }
        System.out.println("= " + (1 - reverse));
        // 填写下一位
        getGrayCode(mid - n / 4, n / 2, b + 1, 0);
        getGrayCode(mid + n / 4, n / 2, b + 1, 1);
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dc/input2.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dc/output2.txt"))){
            grayCode = new int[MAX_NUM][MAX_BIT];
            int nBit = Integer.parseInt(reader.readLine()), nLine = (int)Math.pow(2, nBit);
            System.out.println("nBit: " + nBit);
            // 生成格雷码
            getGrayCode(nLine / 2, nLine, 1, 0);
//            function(nBit, nLine);
            // 输出数据
            for (int i = 1; i <= nLine; i++) {
                StringBuilder result = new StringBuilder();
                for (int j = 1; j <= nBit; j++) {
                    result.append(grayCode[i][j]);
                }
                result.append('\n');
                System.out.print(result);
                writer.write(result.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
