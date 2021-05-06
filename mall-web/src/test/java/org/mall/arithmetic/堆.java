package org.mall.arithmetic;

import java.util.Arrays;

/**
 * @ClassName 堆
 * @Description TODO
 * @link https://leetcode-cn.com/leetbook/read/journey-of-algorithm/5oj2z5/
 * @Author Jay.Jia
 * @Date 2021/3/9 17:17
 * @Version 1.0
 */
public class 堆 {

    /**
     * “上浮”调整
     * （当二叉堆插入节点时，插入位置是完全二叉树的最后一个位置
     *  所以为了保持二叉堆的特性，需要进行上浮操作）
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        // temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            //无须真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * “下沉”调整
     * （二叉堆删除节点的过程和插入节点的过程正好相反，所删除的是处于堆顶的节点。
     * 这时，为了继续维持完全二叉树的结构，我们把堆的最后一个节点 临时补到原本堆顶的位置。
     *  所以这时需要进行下沉操作）
     * @param array 待调整的堆
     * @param parentIndex 要“下沉”的父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，则直接跳出
            if (temp <= array[childIndex])
                break;
            //无须真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     * 构建二叉堆，也就是把一个无序的完全二叉树调整为二叉堆，
     * 本质就是让所有非叶子节点依次「下沉」。
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，依次做“下沉”调整
        for (int i = (array.length-2)/2; i>=0; i--) {
            downAdjust(array, i, array.length);
        }
    }


    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
        array = new int[] {7,1,3,10,5,2,8,9,6};
        test(array);
        System.out.println(Arrays.toString(array));
    }




    public static void test(int[] array) {
        for (int i = (array.length - 2)/2; i > 0; i--) {
            int parentIndex = i;
            //假设左子节点为最小节点
            int minChildIndex = 2*i+1;
            //判断是否有右子节点(可能么有右子节点)
            if(minChildIndex <= array.length - 1){
                //如果左子节点 > 右子节点，则切换到右子节点与父节点比较
                if(array[minChildIndex] > array[minChildIndex+1]){
                    minChildIndex++;
                }
            }
            //父节点与较小子节点交换
            if(array[parentIndex] < array[minChildIndex]){
                int temp = array[parentIndex];
                array[parentIndex] = array[minChildIndex];
                array[minChildIndex] = temp;
            }
        }
    }

}
