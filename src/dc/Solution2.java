package dc;

public class Solution2 {

    private static int[][] array;

    /**
     * 递归函数
     * @param n
     * @param num = 2^n
     */
    private static void function(int n, int num) {
        if (n == 1) {
            array[0][0] = 0;
            array[1][0] = 1;
            return;
        }
        for(int i = 0; i < num/2; i++) {
            array[i][n-1] = 0;
            array[num-i-1][n-1] = 1;
        }//根据规则 这一次各个位置 末位填0或1
        function(n-1, num/2);
        for(int i = num/2; i < num; i++) {
            for(int j=0; j<n-1; j++) {
                array[i][j]= array[num-i-1][j];
            }
        }
    }

    public static void main(String[] args) {
        //TODO
    }

}
