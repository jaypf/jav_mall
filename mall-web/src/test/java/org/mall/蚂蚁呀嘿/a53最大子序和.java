package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a53最大子序和
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/12 11:00
 * @Version 1.0
 */
public class a53最大子序和 {

    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

    @Test
    public void test() throws Exception {
        int sum = maxSubArray(nums);
        System.out.println("sum="+sum);
    }


    public int maxSubArray(int[] nums) {
        //f(i) = max(f(i-1)+num[i], num[i])
        //dp(i) = max(dp(i-1)+num[i], num[i])
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

}
