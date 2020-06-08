package dc;

import java.io.*;

public class Solution3 {

    private static int num;

    private static Advertisement[] advertisements;

    private static class Advertisement implements Comparable<Advertisement> {
        String name; // 名称
        Integer num; // 数量
        String email; // 电子邮件
        String startTime; // 开始时间
        String endTime; // 结束时间
        Advertisement(String name, int num, String email, String startTime, String endTime) {
            this.name = name;
            this.num = num;
            this.email = email;
            this.startTime = startTime;
            this.endTime = endTime;
        }
        @Override
        public int compareTo(Advertisement obj) {
            int nameResult = this.name.compareTo(obj.name);
            int numResult = this.num.compareTo(obj.num);
            int emailResult = this.email.compareTo(obj.email);
            int startTimeResult = this.startTime.compareTo(this.endTime);
            int endTimeResult = this.endTime.compareTo(obj.endTime);
            return (nameResult != 0 ? nameResult : numResult != 0 ? numResult : emailResult != 0 ? emailResult :
                    startTimeResult != 0 ? startTimeResult : endTimeResult);
        }
    }

    private static void merge(int first1, int last1, int last2) {
        //定义辅助数组temp
        Advertisement[] temp = new Advertisement[num];
        //设置两个待合并的起止区间
        int i = first1, j = last1+1, index = first1;
        //依次取两序列中小者放入temp
        while (i <= last1 && j <= last2) {
            if (advertisements[i].compareTo(advertisements[j]) <= 0) {
                temp[index++] = advertisements[i++];
            } else {
                temp[index++] = advertisements[j++];
            }
        }
        //对第一个子序列进行收尾处理
        while (i <= last1) {
            temp[index++] = advertisements[i++];
        }
        //对第二个子序列进行收尾处理
        while (j <= last2) {
            temp[index++] = advertisements[j++];
        }
        //将合并后的结果传回数组record
        for (index = first1; index <= last2; index++) {
            advertisements[index] = temp[index];
        }
    }

    private static void mergePass(int h) {
        int i = 0;
        //有两个长度为h的子序列
        while (i <= num - 2*h + 1) {
            merge(i, i+h-1, i+2*h-1);
            i += 2*h;
        }
        //子序列有一个长度小于h
        if (i < num-h+1) {
            merge(i, i+h-1, num -1);
        }
    }

    private static void mergeSort() {
        //初始时子序列长度为1
        int h = 1;
        while (h < num) {
            //一趟排序
            mergePass(h);
            h*=2;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dc/input3.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/dc/output3.txt"))){
            num = 4;
            advertisements = new Advertisement[num];
            for (int i = 0; i < num; i++) {
                advertisements[i] = new Advertisement(reader.readLine(), Integer.parseInt(reader.readLine()),
                        reader.readLine(), reader.readLine(), reader.readLine());
            }
            mergeSort();
            for (int i = 0; i < num; ) {
                Advertisement obj = advertisements[i];
                writer.write(++i + "\n");
                writer.write("\t" + obj.name + "\n");
                writer.write("\t" + obj.num + "\n");
                writer.write("\t" + obj.email + "\n");
                writer.write("\t" + obj.startTime + "\n");
                writer.write("\t" + obj.endTime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
