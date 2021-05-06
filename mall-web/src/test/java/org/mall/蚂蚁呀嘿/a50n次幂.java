package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName a50n次幂
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/19 17:00
 * @Version 1.0
 */
public class a50n次幂 {

//    实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
//
//    示例 1：
//
//    输入：x = 2.00000, n = 10
//    输出：1024.00000

    private String aass;
    public String aass123 = "a";

    @Test
    public void test() {
        try {
            Class<?> aClass = Class.forName("org.mall.蚂蚁呀嘿.a50n次幂");
            Object o = aClass.newInstance();
            Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
            Field[] declaredFields = aClass.getDeclaredFields();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            Field aass123 = aClass.getDeclaredField("aass123");

            Field[] fields = aClass.getFields();
            Method[] methods = aClass.getMethods();
            Constructor<?>[] constructors = aClass.getConstructors();
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double ans = myPow(3, 10);
        System.out.println(ans);
    }

    //二进制-快速幂
    public double myPow(double x, int n) {
//        if(x == 0 && n < 0){
////            throw new RuntimeException("小朋友，你脑袋上是否有很多问号?");
//            System.out.println("小朋友，你脑袋上是否有很多问号?");
//        }
        if(x == 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }

        //eg1:5^77 这里只需要先计算77的二进制数1001101，
        //从右往左计算:
        // 最后的结果就是5^(2^0 * 1) * 5^(2^1 * 0) * 5^(2^2 * 1) * 5^(2^3 * 1) * 5^(2^4 * 0) * 5^(2^5 * 0) * 5^(2^6 * 1)
        //$            5^(1 * 1) * 5^(2 * 0) * 5^(4 * 1) * 5^(8 * 1) * 5^(16 * 0) * 5^(32 * 0) * 5^(64 * 1)
        //            5^1       * 5^0       * 5^4       * 5^8       * 5^0        * 5^0        * 5^64
        //           5^(1      +0          +4          +8          +0          +0           +64)
        //$          5^(77)

        //eg2:3^10 这里只需要先计算10的二进制数1010，
        //从右往左计算:
        // 最后的结果就是3^(2^0 * 0) * 3^(2^1 * 1) * 3^(2^2 * 0) * 3^(2^3 * 1)
        //$           3^(1 * 0)   * 3^(2 * 1)   * 3^(4 * 0)   * 3^(8 * 1)
        //            3^0        * 3^2         * 3^0        * 3^8
        //            3^(0      +2           +0            +8)
        //$           3^12

        double ans = 1D;
        //为了防止-2147483648转为正数时溢出，这里先把指数转为long型
        long y = n;
        //如果指数小于1，计算的是他的正次幂的倒数
        if(y < 0){
            y = -y;
            x = 1/x;
        }
        //基数为x^1(因为二进制最右是2^0=1，所以第一位是x^(2^0)=x^1=x,后续没一轮都对自己做平方操作)
        double record = x;
        while (y > 0){
            //当最后一位为1时加入结果，eg: 5^(4 * 1)里的1,为0时不需要加入eg:5^(2 * 0)里的0
            if((y&1) == 1){
                ans *= record;
            }
            //每一轮都做一次平方(因为转成了二进制所以每次的计算都是在前边的基础上平方)
            record *= record;
            //右移一位，舍弃最右边一位，说明这个位置已经计算过，移到最后变成0了就说明计算完成了
            y>>=1;
        }
        return ans;
    }

    //分治法-快速幂
    public double myPow2(double x, int n) {
        if(x == 0 && n < 0){
//            throw new RuntimeException("小朋友，你脑袋上是否有很多问号?");
            System.out.println("小朋友，你脑袋上是否有很多问号?");
        }
        if(x == 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }

        long y = n;
        double ans = pow(x, y);
        ans = n > 0 ? ans : 1 / ans;
        return ans;
    }

    public double pow(double x, long n){
        if(n == 1){
            return x;
        }
        if(n % 2 == 0){
            double record = pow(x, n / 2);
            return record * record;
        }else {
            double record = pow(x, n / 2);
            return record * record * x;
        }
    }

    //暴力解
    public double myPow1(double x, int n) {
        if(x == 0 && n < 0){
//            throw new RuntimeException("小朋友，你脑袋上是否有很多问号?");
            System.out.println("小朋友，你脑袋上是否有很多问号?");
        }
        if(x == 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        double ans = 1D;
        int j = Math.abs(n);
        for (int i = 0; i < j; i++) {
           ans *= x;
        }
        ans = n > 0 ? ans : 1 / ans;
        return ans;
    }

}
