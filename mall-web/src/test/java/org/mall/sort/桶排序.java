package org.mall.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName a0冒泡排序
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/8 0:30
 * @Version 1.0
 */
public class 桶排序 {

    private static Integer[] nums = new Integer[]{1,4,2,6,3,9,5,8};

    @Test
    public void test(){
        //桶的数量【这里桶的数量等于原始元素的数量】
        int bucketNum = nums.length;
        //初始化桶
        List<List<Integer>> buckets = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        //获取最大值、最小值
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        //区间跨度【（最大值-最小值）/ (桶数量-1) 】
        double d = (max - min) / (bucketNum - 1.0);

        //填充桶
        for (int i = 0; i < nums.length; i++) {
            //定位桶
            int num = (int)Math.floor((nums[i] - min) / d);
            buckets.get(num).add(nums[i]);
        }

        //各个桶内进行排序【Collections.sort->JDK底层使用了归并排序或者归并排序的优化版本】
        buckets.forEach(b -> Collections.sort(b));

        //输出
        int[] arrs = new int[bucketNum];
        int index = 0;
        for(List<Integer> list : buckets){
            for (int n : list){
                arrs[index] = n;
                index++;
            }
        }
        System.out.printf(Arrays.toString(arrs));
    }

}
