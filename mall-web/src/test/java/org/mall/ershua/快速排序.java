package org.mall.ershua;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName 快速排序
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/6/8 17:58
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
        //基准元素
        int provit = arrs[startIndex];
        //打个桩
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if(arrs[i] < provit){
                //元素arrs[i]小于基准元素，桩右移
                mark++;
                //小于基准元素的数据跟桩调换位置
                int temp = arrs[i];
                arrs[i] = arrs[mark];
                arrs[mark] = temp;
            }
        }
        //遍历结束，基准元素与桩调换位置
        arrs[startIndex] = arrs[mark];
        arrs[mark] = provit;
        return mark;
    }
}
