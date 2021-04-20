package org.mall.蚂蚁;

import org.junit.Test;

/**
 * @ClassName a7整数反转
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/11 10:23
 * @Version 1.0
 */
public class a7整数反转 {

    int n = 1534236469;

    @Test
    public void test(){

        int x = n;
        int rev = 0;
        while (x != 0){
            //余数，每次拿到数字的最后一位
            int pop = x % 10;
            if (rev * 10 > Integer.MAX_VALUE || (rev * 10 == Integer.MAX_VALUE && pop > 7)) {
                rev = 0;
                break;
            }
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                rev = 0;
                break;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                rev = 0;
                break;
            }
            rev = rev * 10 + pop;
            x = x / 10;
        }
        System.out.println(rev);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE-1);
    }

}
