package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName a剑指最小的k个数
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/12 16:42
 * @Version 1.0
 */
public class a剑指最小的k个数 {

//    输入整数数组 arr ，找出其中最小的 k 个数。
//    例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//    示例 1：
//
//    输入：arr = [3,2,1], k = 2
//    输出：[1,2] 或者 [2,1]
    int[] arr = {3,2,1};
    int k = 2;

    @Test
    public void test() throws Exception {
        int[] ans = getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(ans));
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[arr.length];
        //使用排序算法排序，取前k个值

        return ans;
    }
}
