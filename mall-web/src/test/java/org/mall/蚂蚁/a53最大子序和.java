package org.mall.蚂蚁;

import org.junit.Test;

/**
 * @ClassName a53最大子序和
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/12 0:50
 * @Version 1.0
 */
public class a53最大子序和 {

    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

    @Test
    public void test() {
        int ans = maxSubArray(nums);
        System.out.println(ans);
    }

    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = sum;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            sum = Math.max(sum, dp[i]);
        }
        return sum;
    }


}
