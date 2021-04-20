package org.mall.蚂蚁;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @ClassName a204查找质数
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/11 5:30
 * @Version 1.0
 */
public class a204查找质数 {

    int num = 100000;

    @Test
    public void test(){
        long l = System.currentTimeMillis();
        int count = countPrimes1(num);
        long r = System.currentTimeMillis();
        System.out.println(count);
        System.out.println(r-l);
    }

    /**
     * @Description 枚举
     * @Param [n]
     * @Author Jay
     * @Date 2021/4/11 6:02
     * @return int
     **/
    public int countPrimes(int n) {
        int count = 0;
        HashSet<Character> set = new HashSet(5);
        set.add('1');
        set.add('3');
        set.add('5');
        set.add('7');
        set.add('9');
        for (Integer i = 1; i < n; i++) {
            boolean sign = true;
            String sn = i.toString();
            if(!set.contains(sn.charAt(sn.length()-1))){
                continue;
            }
            for (int j = 2; j < i; j++) {
                if(i % j == 0){
                    sign = false;
                    break;
                }
            }
            if(sign){
                count++;
            }
        }
        return count;
    }

    /**
     * @Description
     * 考虑到如果 y 是 x的因数，那么 x/y 也必然是 x 的因数，因此我们只要校验 y 或者 x/y 即可。
     * 而如果我们每次选择校验两者中的较小数，则不难发现较小数一定落在 [2,根号x] 的区间中，
     * 因此我们只需要枚举 [2,根号x] 中的所有数即可，
     * 这样单次检查的时间复杂度从 O(n)降低至了 O(根号n)。
     * @Param [n]
     * @Author Jay
     * @Date 2021/4/11 6:02
     * @return int
     **/
    public int countPrimes1(int n) {
        int count = 0;
        for (int i = 2; i < n; ++i) {
            boolean sign = true;
            for (int j = 2; j <= i/j; ++j) {
                if (i % j == 0) {
                    sign =  false;
                    break;
                }
            }
            if(sign){
                count++;
            }
        }
        return count;
    }

    /**
     * @Description
     * 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞提出，称为厄拉多塞筛法，简称埃氏筛。
     *
     * 我们考虑这样一个事实：如果 xxx 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     *
     * 我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。从小到大遍历每个数，
     * 如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 0，这样在运行结束的时候我们即能知道质数的个数。
     *
     * 这种方法的正确性是比较显然的：这种方法显然不会将质数标记成合数；另一方面，当从小到大遍历到数 x 时，
     * 倘若它是合数，则它一定是某个小于 x 的质数 y 的整数倍，故根据此方法的步骤，我们在遍历到 y 时，就一定会在此时将 x 标记为 isPrime[x]=0。因此，这种方法也不会将合数标记为质数。
     *
     * 当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x*x 开始标记，
     * 因为 2x,3x,…这些数一定在 x*x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。
     * @Param [n]
     * @Author Jay
     * @Date 2021/4/11 6:06
     * @return int
     **/
    public int countPrimes2(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}
