package org.mall.蚂蚁;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName a448找到所有在数组中消失的数字
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/11 7:09
 * @Version 1.0
 */
public class a448找到所有在数组中消失的数字 {

    int[] arr = new int[]{4,3,2,7,8,2,3,1};

    @Test
    public void test(){
        List<Integer> list = findDisappearedNumbers(arr);
        System.out.printf(list.toString());
    }

    /**
     * @Description
     * 假设长度为8的数组，如果都没有出现过重复的数字则应该为{1,2,3,4,5,6,7,8}
     * @Param [nums]
     * @Author Jay
     * @Date 2021/4/11 7:12
     * @return java.util.List<java.lang.Integer>
     **/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        //假设长度为8的数组，如果都没有出现过重复的数字则应该为{1,2,3,4,5,6,7,8}
        for (int i = 0; i < len; i++) {
            //找到该元素在长度为len的数组中原本该存在的位置
            int val = Math.abs(nums[i]);
            //元素本应该存在的位置
//            int index = val % len - 1;
//            if(index == -1){
//                index = len - 1;
//            }
            int index = val - 1;
            if(nums[index] > 0){
                nums[index] *= -1;
            }
        }
        for (int i = 0; i < len; i++) {
            if(nums[i] > 0){
                list.add(i+1);
            }
        }
        return list;
    }


}
