package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName a226反转二叉树
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/14 18:14
 * @Version 1.0
 */
public class a226反转二叉树 {
//    翻转一棵二叉树。
//
//    示例：
//
//    输入：
//
//            4
//            /   \
//            2     7
//            / \   / \
//            1   3 6   9
//
//    输出：
//
//            4
//            /   \
//            7     2
//            / \   / \
//            9   6 3   1


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void test() {

    }

    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode $node = node.left;
            node.left = node.right;
            node.right = $node;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
