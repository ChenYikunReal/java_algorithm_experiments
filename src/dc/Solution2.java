package dc;

import java.io.*;

public class Solution2 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dc/input2.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dc/output2.txt"))){
            int bitNum = Integer.parseInt(reader.readLine());
            for(int i = 0; i < (int)Math.pow(2, bitNum); i++){
                int num = (i >> 1) ^ i;
                StringBuilder result = new StringBuilder();
                for(int j = bitNum-1; j >= 0; j--){
                    result.append((num >> j) & 1);
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
