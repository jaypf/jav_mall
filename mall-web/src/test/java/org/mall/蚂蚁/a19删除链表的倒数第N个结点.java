package org.mall.蚂蚁;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName a19删除链表的倒数第N个结点
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/15 23:31
 * @Version 1.0
 */
public class a19删除链表的倒数第N个结点 {
//    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
//    进阶：你能尝试使用一趟扫描实现吗？
//
//
//
//    示例 1：
//
//    输入：head = [1,2,3,4,5], n = 2
//    输出：[1,2,3,5]


    @Test
    public void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode listNode = removeNthFromEnd(listNode1, 2);
        System.out.println(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ans = new ListNode(0, head);
        ListNode cur = ans;
        int len = 0;
        while (head != null){
            head = head.next;
            len++;
        }
        for (int i = 1; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next  = cur.next.next;
        return ans.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
