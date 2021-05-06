package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a704二分查找
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/12 16:33
 * @Version 1.0
 */
public class a704二分查找 {

    //给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
    // 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

    int[] nums = {-1,0,3,5,9,12};
    int target = 9;

    @Test
    public void test() throws Exception {
        int ans = search(nums, target);
        System.out.println("ans="+ans);
    }

    public int search(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int rignt = nums.length-1;
        while(left <= rignt){
            int mid = (left+rignt)/2;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                rignt = mid - 1;
            }else {
                ans = mid;
                break;
            }
        }
        return ans;
    }
}
