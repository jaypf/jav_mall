package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName a15三数之和
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/16 11:34
 * @Version 1.0
 */
public class a15三数之和 {
//    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
//    使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//
//    注意：答案中不可以包含重复的三元组。
//
//
//
//    示例 1：
//
//    输入：nums = [-1,0,1,2,-1,-4]
//    输出：[[-1,-1,2],[-1,0,1]]

    @Test
    public void test() {
        List<List<Integer>> ans = threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(ans.toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        //各个组合元素数量
        int res = 0;
        int depth = 3;
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>(depth);
        dfs1(ans, path, res, depth, nums, used, list);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, Deque<Integer> path, int res, int depth, int[] nums, boolean[] used) {
        if(depth == path.size()){
            int sum = 0;
            for (int num : path) {
                sum += num;
            }
            if(sum == res){
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs(ans, path, res, depth, nums, used);
            path.removeLast();
            used[i] = false;
        }
    }

    private void dfs1(List<List<Integer>> ans, Deque<Integer> path, int res, int depth, int[] nums, boolean[] used, ArrayList<Integer> list) {
        if(depth == path.size()){
            int sum = 0;
            for (int num : path) {
                sum += num;
            }
            if(sum == res){
                list.clear();
                list.addAll(path);
                Collections.sort(list);
                AtomicBoolean bool = new AtomicBoolean(false);
                ans.forEach(a -> bool.set(a.containsAll(list)));
                if(bool.get()){
                    return;
                }
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs1(ans, path, res, depth, nums, used, list);
            path.removeLast();
            used[i] = false;
        }
    }
}
