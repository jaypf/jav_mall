package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a141环形链表
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/14 19:14
 * @Version 1.0
 */
public class a141环形链表 {
//    给定一个链表，判断链表中是否有环。
//
//    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
//    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
//    如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
//    如果链表中存在环，则返回 true 。 否则，返回 false 。

    public boolean hasCycle(ListNode node) {
        ListNode l = node;
        ListNode r = node;
        while (r != null && r.next != null){
            l = l.next;
            r = r.next.next;
            if(l == r){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode l = head.next;
        ListNode r = head.next;
        while (l != r){
            if(r == null || r.next == null){
                return false;
            }
            l = l.next;
            r = r.next.next;
        }
        return true;
    }

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
        l4.next = l3;

        boolean ans = hasCycle1(head);
        System.out.println(ans);
    }
    

}
