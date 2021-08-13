package org.mall.ershua;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName 树
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/6/8 16:00
 * @Version 1.0
 */
public class 树 {

    static LinkedList<Integer> list = new LinkedList(Arrays.asList(new Integer[]{3,2,9,null,null,10,null, null,8,null,4}));

    static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 构建二叉树
     * @param inputList 输入序列
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        // 这里的判空很关键：如果元素是空，则不在进一步递归
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = createBinaryTree(list);
        System.out.println("前序遍历");
        preorderTraversal(root);
        System.out.println("前序遍历-栈");
        preOrderTraveralWithStack(root);
        System.out.println("层序遍历");
        levelOrderTraversal(root);
    }

    private static void preorderTraversal(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        preorderTraversal(root.leftChild);
        preorderTraversal(root.rightChild);
    }


    private static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if(!stack.isEmpty()){
                TreeNode pop = stack.pop();
                treeNode = pop.rightChild;
            }
        }
    }


    private static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if(node.leftChild != null){
                queue.offer(node.leftChild);
            }
            if(node.rightChild != null){
                queue.offer(node.rightChild);
            }
        }
    }

}
