package org.mall.ershua;

/**
 * @ClassName a206反转链表
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/8 2:18
 * @Version 1.0
 */
public class a206反转链表 {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    //输入：head = [1,2,3,4,5]
    //输出：[5,4,3,2,1]

    static ListNode createNodeList(int[] arr){
        if(arr.length < 1){
            return null;
        }
        ListNode root = new ListNode();
        ListNode r = root;
        for (int i : arr) {
            ListNode node = new ListNode(i);
            r.next = node;
            r = r.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode root = createNodeList(new int[]{1, 2, 3, 4, 5});
        System.out.println(root);

        ListNode ans = reversal(root);
        System.out.println(ans);
    }

    static ListNode reversal(ListNode root){
        if(root == null || root.next == null){
            return root;
        }
        ListNode reversal = reversal(root.next);
        root.next.next = root;
        root.next = null;
        return reversal;
    }

    static ListNode reversal1(ListNode root){
        ListNode result = null;
        ListNode cur = root;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = result;
            result = cur;
            cur = next;
        }
        return result;
    }
}
