package org.mall.arithmetic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName 初级算法
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/3/5 16:31
 * @Version 1.0
 */
public class 初级算法 {


    @Test
    public void removeDuplicates(){
        int[] nums = {1,1,2,3,3};
        int j = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] != nums[++j]){
                nums[i] = nums[j];
            }
        }
        System.out.println(j-1);
        System.out.println(Arrays.toString(nums));
    }
}
