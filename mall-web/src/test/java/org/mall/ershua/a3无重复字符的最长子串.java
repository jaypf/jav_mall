package org.mall.ershua;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName a3无重复字符的最长子串
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/19 16:24
 * @Version 1.0
 */
public class a3无重复字符的最长子串 {
//    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//    示例 1:
//
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//    示例 2:
//
//    输入: s = "bbbbb"
//    输出: 1
//    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

    public static void main(String[] args) {
        String str = "abcabcbb";
        int len = 0, leftIndex = 0, rightIndex = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (;rightIndex < str.length(); rightIndex++) {
            char c = str.charAt(rightIndex);
            if(map.containsKey(c)){
                //发现重复元素
                Integer integer = map.get(c);
                //左指针右移
                leftIndex = Math.max(leftIndex, integer+1);
            }
            len = Math.max(len, rightIndex + 1 - leftIndex);
            map.put(c, rightIndex);
        }
        System.out.println(len);
    }
}
