package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName a283移动零
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/14 16:02
 * @Version 1.0
 */
public class a283移动零 {
//    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
//    示例:
//
//    输入: [0,1,0,3,12]
//    输出: [1,3,12,0,0]
//
//    说明:
//
//    必须在原数组上操作，不能拷贝额外的数组。
//    尽量减少操作次数

    @Test
    public void test() {
        int[] ans = moveZeroes1(new int[]{0, 1, 0, 3, 12});
        System.out.println(Arrays.toString(ans));
    }

    public int[] moveZeroes(int[] nums) {
        int[] ans = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                ans[index] = nums[i];
                index++;
            }
        }
        return ans;
    }

    public int[] moveZeroes1(int[] nums) {
        int j = 0;
        for (int num : nums){
            if(num != 0){
                nums[j] = num;
                j++;
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

}
