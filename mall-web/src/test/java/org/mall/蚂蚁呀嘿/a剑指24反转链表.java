package org.mall.蚂蚁呀嘿;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @ClassName a剑指24反转链表
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/14 19:01
 * @Version 1.0
 */
public class a剑指24反转链表 {
//    定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
//
//
//    示例:
//
//    输入: 1->2->3->4->5->NULL
//    输出: 5->4->3->2->1->NULL

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode ans = reverseList(head);
//        ListNode ans = reverseBetween(head, 2, 4);
        System.out.println(JSONObject.toJSONString(ans));
    }
 

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode g = node;
         ListNode p = node.next;

        for (int i = 0; i < left-1; i++) {
            g = g.next;
            p = p.next;
        }

        for (int i = left; i < right; i++) {
            ListNode move = p.next;
            p.next = p.next.next;

            move.next = g.next;
            g.next = move;
        }

        return node.next;
    }
}
