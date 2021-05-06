package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName a46全排列
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/15 16:09
 * @Version 1.0
 */
public class a46全排列 {
//    给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
//    示例:
//
//    输入: [1,2,3]
//    输出:
//            [
//            [1,2,3],
//            [1,3,2],
//            [2,1,3],
//            [2,3,1],
//            [3,1,2],
//            [3,2,1]
//            ]

    @Test
    public void test() {
        List<List<Integer>> ans = permute(new int[]{1,2,3});
        System.out.println(ans.toString());
    }


    public List<List<Integer>> permute(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        //记录已经遍历过的元素
        boolean[] used = new boolean[len];
        //当前深度
        int depth = 0;
        //记录遍历到当前深度时添加进去的元素(LIFO)
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, res, len, used, depth, path);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, int len, boolean[] used, int depth, Deque<Integer> path){
        //当深度与元素数量相等时说明到了最底一层，此时把结果添加，返回
        if(depth == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if(used[i]){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, res, len, used, depth+1, path);
            //回溯
            path.removeLast();
            used[i] = false;
        }
    }


}
