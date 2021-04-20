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
public class a0冒泡排序 {

    private static Integer[] nums = new Integer[]{1,4,2,6,3,9,5,8};


    @Test
    public void test(){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                }
            }
        }
        System.out.printf(Arrays.toString(nums));
    }

    @Test
    public void test1(){
        for (int i = 0; i < nums.length; i++) {
            boolean isStorted = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                    //进行过交换，不是有序的所以标记为false
                    isStorted = false;
                }
            }
            //数列已经有序，跳出循环
            if(isStorted){
                break;
            }
        }
        System.out.printf(Arrays.toString(nums));
    }

    @Test
    public void test2(){
        //最后一次交换位置
        int lastIndex = 0;
        //无序数列的边界，每次比较只需比到这里为止
        int stortBorder = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < stortBorder; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                    lastIndex = j;
                }
            }
            stortBorder = lastIndex;
        }
        System.out.printf(Arrays.toString(nums));
    }

    @Test
    public void test3(){
        //最后一次交换位置
        int lastIndex = 0;
        //无序数列的边界，每次比较只需比到这里为止
        int stortBorder = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            boolean isStored = true;
            for (int j = 0; j < stortBorder; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                    lastIndex = j;
                    isStored = false;
                }
            }
            stortBorder = lastIndex;
            if(isStored){
                break;
            }
        }
        System.out.printf(Arrays.toString(nums));
    }


    /**
     * @Description 鸡尾酒排序
     * @Param []
     * @Author Jay
     * @Date 2021/4/8 0:48
     * @return void
     **/
    @Test
    public void test4(){
        for (int i = 0; i < nums.length/2; i++) {
            //奇数轮
            for (int j = 0; j <nums.length-i-1; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                }
            }
            //偶数轮
            for (int j = nums.length-i-1; j <0; j--) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                }
            }
        }
        System.out.printf(Arrays.toString(nums));
    }

    @Test
    public void test5(){
        for (int i = 0; i < nums.length/2; i++) {
            //奇数轮
            boolean isStored = true;
            for (int j = i; j <nums.length-i-1; j++) {
                if(nums[j] > nums[j+1]){
                    int d = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = d;
                    isStored = false;
                }
            }
            if(isStored){
                break;
            }
            //偶数轮
            isStored = true;
            for (int j = nums.length-i-1; j > i; j--) {
                if(nums[j] < nums[j-1]){
                    int d = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = d;
                    isStored = false;
                }
            }
            if(isStored){
                break;
            }
        }
        System.out.printf(Arrays.toString(nums));
    }
}
