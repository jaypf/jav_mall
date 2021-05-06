package org.mall.蚂蚁呀嘿;

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
}
