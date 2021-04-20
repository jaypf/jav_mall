package org.mall.蚂蚁;

import org.junit.Test;

/**
 * @ClassName a121买卖股票的最佳时期
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/11 11:48
 * @Version 1.0
 */
public class a121买卖股票的最佳时期 {


    int[] nums = new int[]{7,1,5,3,6,4};

    @Test
    public void test(){
        int maxprofit = maxProfit2(nums);
        System.out.println(maxprofit);
    }

    public int maxProfit(int[] prices) {

        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    public int maxProfit1(int[] prices) {
        //最大利润
        int maxprofit = 0;
        //假设最低价格
        int minprice = prices[0];
        for (int i = 0; i <prices.length; i++) {
            if(prices[i] < minprice){
                minprice = prices[i];
            }else if(prices[i] - minprice > maxprofit){
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }


    public int maxProfit2(int[] prices) {
        //最大利润
        int maxprofit = 0;
        //假设最低价格
        int minprice = prices[0];
        for (int i = 0; i <prices.length; i++) {
            minprice = Math.min(minprice, prices[i]);
            maxprofit = Math.max(maxprofit, prices[i] - minprice);
        }
        return maxprofit;
    }
}
