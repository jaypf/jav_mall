package org.mall.蚂蚁呀嘿;

import org.junit.Test;

/**
 * @ClassName a92反转链表Ⅱ
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/18 4:59
 * @Version 1.0
 */
public class a92反转链表Ⅱ {
//    给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
//    请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
//
//    示例 1：
//
//    输入：head = [1,2,3,4,5], left = 2, right = 4
//    输出：[1,4,3,2,5]
//
//    示例 2：
//
//    输入：head = [5], left = 1, right = 1
//    输出：[5]

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
        ListNode head =  build(new int[]{1,2,3,4,5});
        ListNode node = reverseBetween(head, 2, 4);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
//        1、我们定义两个指针，分别称之为 g(guard 守卫) 和 p(point)。
//        我们首先根据方法的参数 left 确定 g 和 p 的位置。将 g 移动到第一个要反转的节点的前面，
//        将 p 移动到第一个要反转的节点的位置上。我们以 left=2，right=4为例。
//        2、将 p 后面的元素删除，然后添加到 g 的后面。也即头插法。
//        3、根据 left 和 right 重复步骤（2）
//        4、返回 dummyHead.next

        if(head == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;
        for (int i = 0; i < left - 1; i++) {
            g = g.next;
            p = p.next;
        }

        // 头插法插入节点
        for (int i = 0; i < right - left; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;

            removed.next = g.next;
            g.next = removed;
        }
        return dummyHead.next;
    }

    public ListNode reverseBetween1(ListNode head, int left, int right) {
        //根据left、right把链表分成三部分
        //pre：子节点的前一个节点
        //leftNode：子链表头节点
        //rightNode：子链表尾节点
        //last：子链表尾节点的后一个节点

        if(head == null){
            return head;
        }
        ListNode node = new ListNode(0, head);
        ListNode pre = node;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;//子链表头节点
        ListNode last = rightNode.next;//子链表尾节点的后一个节点
        rightNode.next = null;
        pre.next = null;

        //这个subNode其实就是rightNode
        ListNode subNode = reverse(leftNode);
//        pre.next = subNode;
        pre.next = rightNode;

        //反转之前的leftNode变成了现在子链表的尾节点
        leftNode.next = last;

        return node.next;
    }


    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

}
