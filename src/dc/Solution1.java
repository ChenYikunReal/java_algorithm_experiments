package dc;

import java.io.*;

/**
 * 获取两有序等长数组的中位数
 * 时间O(logN) && 空间O(1)
 */
public class Solution1 {

    private static int search(int[] a, int[] b, int la, int ra, int lb, int rb) {
        int ma = (la + ra) / 2;
        int mb = (lb + rb + 1) / 2;
        if (la == ra) {
            return (lb == rb) ? Math.min(a[la], b[lb]) : Math.min(a[la], b[rb]);
        } else if (lb == rb) {
            return Math.min(b[lb], a[ra]);
        }
        if (la == ra-1 && lb == rb-1) {
            //第一个序列求中位数方法不变，第二个序列变为与第一序列相同的方法求得中位数
            mb = (lb + rb) / 2;
        }
        return a[ma] == b[mb] ? a[ma] : (a[ma] > b[mb] ? search(a, b, la, ma ,mb, rb) : search(a, b, ma, ra, lb, mb));
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dc/input1.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dc/output1.txt"))){
            int num = Integer.parseInt(reader.readLine());
            String[] nums1 = reader.readLine().split("\\s+"), nums2 = reader.readLine().split("\\s+");
            int[] a = new int[num], b = new int[num];
            for (int i = 0; i < num; i++) {
                a[i] = Integer.parseInt(nums1[i]);
                b[i] = Integer.parseInt(nums2[i]);
            }
            int result = search(a, b, 0, num-1, 0, num-1);
            System.out.println(result);
            writer.write(Integer.toString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
