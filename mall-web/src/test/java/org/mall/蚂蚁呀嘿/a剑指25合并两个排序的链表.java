package org.mall.蚂蚁呀嘿;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @ClassName a剑指25合并两个排序的链表
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/12 16:57
 * @Version 1.0
 */
public class a剑指25合并两个排序的链表 {

//    输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
//
//    示例1：
//
//    输入：1->2->4, 1->3->4
//    输出：1->1->2->3->4->4

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;

        ListNode ans = mergeTwoLists(l1, l2);
        System.out.println(JSONObject.toJSONString(ans));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //创建两个引用cur,node指向同一个节点0
        ListNode cur = new ListNode(0);
        ListNode node = cur;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next =  l1 == null ? l2 : l1;
        //抛掉开头的0节点
        return node.next;
    }
}
