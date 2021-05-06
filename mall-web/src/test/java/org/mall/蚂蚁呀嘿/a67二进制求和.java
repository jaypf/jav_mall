package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a67二进制求和
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/12 0:33
 * @Version 1.0
 */
public class a67二进制求和 {

    @Test
    public void test() {
        String ans = Integer.toBinaryString(Integer.parseInt("11",2) + Integer.parseInt("1",2));
        System.out.println(ans);
    }

    public String addBinary(String a, String b) {
        String bigNumberA = a;
        String bigNumberB = b;
        //1.把两个大整数用数组逆序存储，数组长度等于较大整数位数+1
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int[] arrayA = new int[maxLength+1];
        for(int i=0; i< bigNumberA.length(); i++){
            arrayA[i] = bigNumberA.charAt(bigNumberA.length()-1-i) - '0';
        }
        int[] arrayB = new int[maxLength+1];
        for(int i=0; i< bigNumberB.length(); i++){
            arrayB[i] = bigNumberB.charAt(bigNumberB.length()-1-i) - '0';
        }
        //2.构建result数组，数组长度等于较大整数位数+1
        int[] result = new int[maxLength+1];
        //3.遍历数组，按位相加
        for(int i=0; i<maxLength; i++){
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];
            //判断是否进位
            if(temp >= 1){
                temp = 0;
                result[i+1] = 1;
            }
            result[i] = temp;
        }
        //4.把result数组再次逆序并转成String
        StringBuilder sb = new StringBuilder();
        //是否找到大整数的最高有效位
        boolean findFirst = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if(!findFirst){
                if(result[i] == 0){
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

}
