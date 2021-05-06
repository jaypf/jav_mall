package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a9回文数
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/14 15:25
 * @Version 1.0
 */
public class a9回文数 {
//    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
//    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
//
//             
//
//    示例 1：
//
//    输入：x = 121
//    输出：true

    @Test
    public void test() {
        boolean ans = isPalindrome1(121);
        System.out.println(ans);
    }

    public boolean isPalindrome(int x) {
        boolean ans = true;
        String str = String.valueOf(x);
        int left = 0;
        int right = str.length() - 1;
        while (left < right){
            if(str.charAt(left) != str.charAt(right)){
                ans = false;
                break;
            }
            left++;
            right--;
        }
        return ans;
    }

    public boolean isPalindrome1(int x) {
        if(x < 0){
            return false;
        }
        int y = 0;
        while (x > y){
          y = y * 10 + x / 10;
          x /= 10;
        }
        return x == y || y == x/10;
    }
}
