package org.mall.arithmetic;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @ClassName 动态规划
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/3/12 10:55
 * @Version 1.0
 */
public class 动态规划 {


    /**
     * @Description 凑硬币
     * 给你k种面值的硬币，面值分别为c1, c2 ... ck，每种硬币的数量无限，再给一个总金额amount，
     * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：
     *
     * //coins 中是可选硬币面值，amount 是目标金额
     * int coinChange(int[] coins, int amount);
     *
     * 比如说k = 3，面值分别为 1，2，5，总金额amount = 11。那么最少需要 3 枚硬币凑出，即 11 = 5 + 5 + 1。
     * @Param
     * @Author Jay.Jia
     * @Date 2021/3/12 10:56
     * @return
     **/
    int amount = 11;
    int[] coins = {1,2,5};
    int sum = 0;
    int index = 0;
    @Test
    public void 凑硬币(){

        if(amount < 0){
            System.out.println("-1");
            return;
        }
        if(amount == 0){
            System.out.println("0");
            return;
        }


        int i = coinChange(coins, amount);
        System.out.println(i);
    }

    int coinChange(int[] coins, int amount) {
        // 数组大小为 amount + 1，初始值也为 amount + 1
        //每种情况的最大金额都是amount
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount;
        }
        // base case,代表0金额的时候需要的银币个数为0
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 在求所有子问题 + 1 的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    int r = 0;
    int dp(int sum){
        LinkedList<Integer> list = new LinkedList<>();
        for (int coin : coins){
            int afterSum = amount - coin;
            if(afterSum < 0){
                continue;
            }
            r = Math.min(afterSum, dp(afterSum)+1);
        }
        return r;
    }

}
