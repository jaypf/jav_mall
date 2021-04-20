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
public class 快速排序 {

    private static Integer[] nums = new Integer[]{1,4,2,6,3,9,5,8};


    @Test
    public void test(){
        quickStort(nums,0, nums.length-1);
        System.out.printf(Arrays.toString(nums));
    }

    public void  quickStort(Integer[] arrs, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int partition = partition(arrs, startIndex, endIndex);
        quickStort(arrs, startIndex, partition-1);
        quickStort(arrs, partition+1, endIndex);
    }


    public int partition(Integer[] arrs, int startIndex, int endIndex){
        int pivot = arrs[startIndex];
        int mark = startIndex;
        for (int i = startIndex+1; i <= endIndex; i++) {
            if(pivot > arrs[i]){
                mark++;
                int temp = arrs[i];
                arrs[i] = arrs[mark];
                arrs[mark] = temp;
            }
        }
        arrs[startIndex] = arrs[mark];
        arrs[mark] = pivot;
        return mark;
    }
}
