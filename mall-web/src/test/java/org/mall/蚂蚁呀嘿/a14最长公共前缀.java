package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a14最长公共前缀
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/11 13:05
 * @Version 1.0
 */
public class a14最长公共前缀 {

    //   String[] strs = {"flower","flow","flight"};
    String[] strs = {"a"};

    @Test
    public void test() {
        String ans = longestCommonPrefix(strs);
        System.out.println(ans);
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
