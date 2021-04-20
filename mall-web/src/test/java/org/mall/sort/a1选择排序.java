package org.mall.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName a0冒泡排序
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/8 0:30
 * @Version 1.0
 */
public class a1选择排序 {

    private static Integer[] nums = new Integer[]{1,4,2,6,3,9,5,8};


    @Test
    public void test(){

        for (int i = 0; i < nums.length; i++) {
            int min = i;
            //内层从有序右边界开始遍历查找
            for (int j = i; j < nums.length; j++) {
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;
        }


        System.out.printf(Arrays.toString(nums));
    }

}
