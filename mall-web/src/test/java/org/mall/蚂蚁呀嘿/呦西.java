package org.mall.蚂蚁呀嘿;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 呦西
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/8 18:27
 * @Version 1.0
 */
public class 呦西 {

    @Test
    public void test3() throws Exception {
        String str = "abcabcbb";

        int leftIndex = 0;
        int len = 0;
//        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        //右指针一直移动
        for (int rigntIndex = 0; rigntIndex < str.length(); rigntIndex++) {
            char c = str.charAt(rigntIndex);
            if(map.containsKey(c)){
                //发现重复元素，前者索引
                Integer moveInteger = map.get(c);
                //发现重复索引，左指针直接跳到左侧重复索引的下一个
                leftIndex = Math.max(leftIndex, moveInteger+1);
            }
            //长度
            if( rigntIndex + 1 - leftIndex > len){
                len = rigntIndex + 1 - leftIndex;
            }
            //len = Math.max(len, rigntIndex + 1 - leftIndex);
            //存入元素及其索引，有重复的value替换成后者的索引
            map.put(c, rigntIndex);
        }
        System.out.println(len);
    }


    @Test
    public void twoSum1() {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] r = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                Integer key = map.get(target - nums[i]);
                r[0] = key;
                r[1] = i;
            }
            map.put(nums[i],i);
        }
        System.out.println(Arrays.toString(r));
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] $pre;
    int[] $post;
    @Test
    public void constructFromPrePost() {
        int[] pre = {1,2,4,5,3,6,7};
        int[] post =  {4,5,2,6,7,3,1};
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        $pre = pre;
        $post = post;
        TreeNode node1 = build1(0, pre.length - 1, 0, post.length - 1);
//        TreeNode node = build(0, 0, post.length - 1);
        System.out.println(JSONObject.toJSONString(node1));
    }

    public TreeNode build(int qs, int hs, int len){
        if(qs >= len || hs >= len){
            return null;
        }
        //根节点val
        int rv = $pre[qs];
        TreeNode root = new TreeNode(rv);

        //后序遍历数组中根节点索引
        int ri = map.get(rv);
        root.left = build(qs+1, hs, hs+ri);
        root.right = build(hs,ri, ri-hs);
        return root;
    }

    //ri = 2
    //                         1       3       0       2
    //                         4       6       3       5
    public TreeNode build1(int qs, int qe, int hs, int he){
        if(qs >= qe || hs >= he){
            return null;
        }
        //根节点val
        int rv = $pre[qs];
        TreeNode root = new TreeNode(rv);

        //后序遍历数组中根节点索引
        int ri = map.get(rv);
        root.left = build1(qs+1,qs+ri-hs, hs, ri-1);
        root.right = build1(qs+ri-hs+1, qe, ri+1, he-1);
        return root;
    }


    int[] nums = {5,7,7,8,8,10};
    int target = 8;
    @Test
    public void searchRange() {
        int[] r;
        int leftIndex = find(nums, true);
        int rightIndex = find(nums, false);
        if(leftIndex == -1){
            r = new int[]{-1,-1};
        }
        r = new int[]{leftIndex,rightIndex};
    }

    public int find(int[] nums, boolean isLeft){
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right){
            int mid = (left + right) /2;
            int val = nums[mid];
            if(val > target){
                right = mid-1;
            }else if(isLeft && val == target){
                right = mid-1;
                ans = mid;
            }else if(!isLeft && val == target){
                left = mid+1;
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return ans;
    }


    @Test
    public void countPrimes() {
        int n = 3;
        int count = 0;

        for (int i = 2; i < n; i++) {
            boolean sign = true;
            for (int j = 2; j < i; j++) {
                if(i % j == 0){
                    sign = false;
                    break;
                }
            }
            if(sign){
                System.out.print(i);
                System.out.print(",");
            }
        }
    }

    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
