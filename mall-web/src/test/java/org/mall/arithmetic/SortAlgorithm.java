package org.mall.arithmetic;

import lombok.var;
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName SortAlgorithm
 * @Description 十大排序算法
 * @Author Jay.Jia
 * @Date 2021/2/25 17:37
 * @Version 1.0
 */
public class SortAlgorithm {

    //private final static int[] nums = {1,12,-5,-6,50,3};


    /**
     * @Description
     * 冒泡排序（Bubble Sort
     *
     * 冒泡排序 是一种简单的排序算法。它重复地走访过要排序的数列，依次比较两个元素，
     * 如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，
     * 也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     *
     * 1.1 算法描述
     *     比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *     对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *     针对所有的元素重复以上的步骤，除了最后一个；
     *     重复步骤1~3，直到排序完成。
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/25 17:39
     * @return void
     **/
    @Test
    public void test1冒泡排序(){
        int[] nums = {1,12,-5,-6,50,3};
        //正序
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[j+1] > nums[j]){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));

        //倒序
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[j] > nums[j+1]){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * @Description
     * 选择排序（Selection Sort）
     *
     * 选择排序(Selection - sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，
     * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *
     * 2.1 算法描述
     *
     * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
     *
     *     初始状态：无序区为R[1..n]，有序区为空；
     *     第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，
     *     将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     *     n-1趟结束，数组有序化了。
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/25 17:50
     * @return void
     **/
    @Test
    public void test2选择排序(){
        int[] nums = {1,12,-5,-6,50,3};
        //将替换的索引
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] < nums[index]){
                    //索引替换
                    index = j;
                }
            }
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * @Description
     * 插入排序（Insertion Sort）
     *
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 3.1 算法描述
     *
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     *
     *     从第一个元素开始，该元素可以认为已经被排序；
     *     取出下一个元素，在已经排序的元素序列中从后向前扫描；
     *     如果该元素（已排序）大于新元素，将该元素移到下一位置；
     *     重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     *     将新元素插入到该位置后；
     *     重复步骤2~5
     * @Param
     * @Author Jay.Jia
     * @Date 2021/2/25 18:14
     * @return
     **/
    @Test
    public void test3插入排序() {
        int[] nums = {1, 12, -5, -6, 50, 3};

        for (int i = 1; i < nums.length; i++) {
            int curNum = nums[i];
            int curIndex = i;
            while (curIndex > 0 && curNum < nums[curIndex - 1] ){
                nums[curIndex] = nums[curIndex - 1];
                curIndex --;
            }
            nums[curIndex] = curNum;
        }

        System.out.println(Arrays.toString(nums));
    }


    /**
     * @Description
     * 希尔排序（Shell Sort）
     *
     * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * 4.1 算法描述
     *
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
     *
     *     选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     *     按增量序列个数k，对序列进行k 趟排序；
     *     每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/26 11:50
     * @return void
     **/
    @Test
    public void test4希尔排序() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        //间隙
        for (var gap = Math.floor(nums.length/2); gap > 0 ; gap = Math.floor(gap/2)) {
            //内部为插入排序
            for (int i = 1; i < nums.length; i++) {
                int curNum = nums[i];
                int curIndex = i;
                while (curIndex > 0 && curNum < nums[curIndex - 1] ){
                    nums[curIndex] = nums[curIndex - 1];
                    curIndex --;
                }
                nums[curIndex] = curNum;
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * @Description
     * 归并排序（Merge Sort）
     *
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * 5.1 算法描述
     *
     *     把长度为n的输入序列分成两个长度为n/2的子序列；
     *     对这两个子序列分别采用归并排序；
     *     将两个排序好的子序列合并成一个最终的排序序列
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/26 15:13
     * @return void
     **/
    @Test
    public void test5归并排序() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        //TODO
        System.out.println(Arrays.toString(nums));
    }


    /**
     * @Description
     * 快速排序（Quick Sort）
     *
     * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
     * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * 6.1 算法描述
     *
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     *
     *     从数列中挑出一个元素，称为 “基准”（pivot）；
     *     重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     *     在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     *     递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/26 15:21
     * @return void
     **/
    @Test
    public void test6快速排序() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        //TODO
        System.out.println(Arrays.toString(nums));
    }


    /**
     * @Description
     * 堆排序（Heap Sort）
     *
     * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，
     * 并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
     * 7.1 算法描述
     *
     *     将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
     *     将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
     *     由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，
     *     得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
     * @Param []
     * @Author Jay.Jia
     * @Date 2021/2/26 15:27
     * @return void
     **/
    @Test
    public void test7堆排序() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        //TODO
        System.out.println(Arrays.toString(nums));
    }
















}
