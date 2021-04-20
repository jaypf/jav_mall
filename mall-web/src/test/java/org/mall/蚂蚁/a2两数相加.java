package org.mall.蚂蚁;

import org.junit.Test;

/**
 * @ClassName a2两数相加
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/18 3:59
 * @Version 1.0
 */
public class a2两数相加 {
//    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
//    请你将两个数相加，并以相同形式返回一个表示和的链表。
//
//    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
//    示例 1：
//
//    输入：l1 = [2,4,3], l2 = [5,6,4]
//    输出：[7,0,8]
//    解释：342 + 465 = 807.
//
//    示例 2：
//
//    输入：l1 = [0], l2 = [0]
//    输出：[0]
//
//    示例 3：
//
//    输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//    输出：[8,9,9,9,0,0,0,1]

    //[0,8,6,5,6,8,3,5,7]
    //[6,7,8,0,8,5,8,9,7]

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode build(int[] nums){
        ListNode node = new ListNode(0);
        ListNode cur = node;
        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return node.next;
    }

    @Test
    public void test() {
//        ListNode l1 =  build(new int[]{2,4,3});
//        ListNode l2 =  build(new int[]{5,6,4});
        ListNode l1 =  build(new int[]{0,8,6,5,6,8,3,5,7});
        ListNode l2 =  build(new int[]{6,7,8,0,8,5,8,9,7});


        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        //记录进位
        int num = 0;
        while (l1 != null || l2 != null){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + num;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            num = sum >= 10 ? 1 : 0;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }

        if(num > 0){
            cur.next = new ListNode(num);
        }
        return ans.next;
    }
}
