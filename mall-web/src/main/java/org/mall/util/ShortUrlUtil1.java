package org.mall.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName ShortUrlUtil1
 * @Description
 * 1.  将长网址用md5算法生成32位签名串，分为4段,，每段8个字符。
 *
 * 2.  对这4段循环处理，取每段的8个字符, 将他看成16进制字符串与0x3fffffff(30位1)的位与操作，
 * 超过30位的忽略处理。多了也没用因为下面要分成6段  嘿嘿正好取整。注意用Long型变量（长度问题  你懂得）
 *
 * 3.  将每段得到的30位字符（后台以long十进制显示）又分成6段，通过移位运算将每5位分别与字符数组求与运算（0x0000003D），
 * 得到其在字符数组中的索引并取出拼串。
 *
 * 4.  这样一个md5字符串可以获得4个6位串，取里面的任意一个就可作为这个长url的短url地址
 * @Author Jay.Jia
 * @Date 2021/4/28 11:33
 * @Version 1.0
 */
public class ShortUrlUtil1 {
    public static void main(String[] args) {
        // 长连接
        String longUrl = "https://fordevtest2.nttdatadm.com/user-center/swagger-ui.html#/";
        // 转换成的短链接后6位码
        String[] shortCodeArray = shortUrl(longUrl);

        for (int i = 0; i < shortCodeArray.length; i++) {
            System.out.println(shortCodeArray[i]);// 任意一个都可以作为短链接码
        }
    }

    public static String[] shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z" };

        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = DigestUtils.md5Hex(key + url);
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
            // long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }

            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }
}
