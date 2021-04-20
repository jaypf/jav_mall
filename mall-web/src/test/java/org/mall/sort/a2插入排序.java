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
public class a2插入排序 {

    private static Integer[] array = new Integer[]{1,4,2,6,3,9,5,8};


    @Test
    public void test(){
        for (int i = 0; i < array.length-1; i++) {
            int current = array[i+1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1] = current;
        }
        System.out.printf(Arrays.toString(array));
    }

}
