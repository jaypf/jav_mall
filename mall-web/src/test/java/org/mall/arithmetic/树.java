package org.mall.arithmetic;

import java.util.*;
import java.util.concurrent.DelayQueue;

/**
 * @ClassName 树
 * @Description TODO
 * @link https://leetcode-cn.com/leetbook/read/journey-of-algorithm/5obws7/
 * @Author Jay.Jia
 * @Date 2021/3/8 17:31
 * @Version 1.0
 */
public class 树 {
    /**
     * 构建二叉树
     * @param inputList 输入序列
     */
    public static TreeNode createBinaryTree(LinkedList<Integer>inputList){
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

    /**
     * 二叉树前序遍历
     * @param node 二叉树节点
     */
    public static void preOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树中序遍历
     * @param node 二叉树节点
     */
    public static void inOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraveral(node.leftChild);
        System.out.println(node.data);
        inOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树后序遍历
     * @param node 二叉树节点
     */
    public static void postOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        System.out.println(node.data);
    }

    /**
     * 二叉树节点
     */
    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList =
                new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null, null,8,null,4}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("===============================深度优先遍历====================================：");
        System.out.println("前序遍历：");
        preOrderTraveral(treeNode);
        System.out.println("中序遍历：");
        inOrderTraveral(treeNode);
        System.out.println("后序遍历：");
        postOrderTraveral(treeNode);
        System.out.println("栈-前序遍历：");
        preOrderTraveralWithStack(treeNode);
        System.out.println("栈-中序遍历：");
        inOrderTraveralWithStack(treeNode);
        System.out.println("栈-后序遍历：");
        postOrderTraveralWithStack(treeNode);
        System.out.println("队列-后序遍历：");
        postOrderTraveralWithQueue(treeNode);
        System.out.println("===============================广度优先遍历====================================：");
        levelOrderTraversal(treeNode);
        System.out.println("===============================锯齿遍历(先左后右)====================================：");
        levelOrderTraversal1(createBinaryTree(new LinkedList<Integer>(Arrays.asList(new Integer[]{3,9,20,null,null,15,7}))));
    }



    //===============================非递归遍历====================================

    /**
     * 二叉树非递归前序遍历
     * @param root 二叉树根节点
     */
    public static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                //打印访问到的节点
                System.out.println(treeNode.data);
                //当前节点入栈
                stack.push(treeNode);
                //获取当前节点的左子节点
                treeNode = treeNode.leftChild;
            }

            //代码进行到这里，说明刚才的打印的节点左子节点为null,接下来访问右子节点
            if(!stack.isEmpty()){
                //从栈顶取出刚才打印的那个节点，获取其右子节点
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树非递归中序遍历
     * @param root 二叉树根节点
     */
    public static void inOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                //当前节点入栈
                stack.push(treeNode);
                //获取当前节点的左子节点
                treeNode = treeNode.leftChild;
            }

            //代码进行到这里，说明刚才的打印的节点左子节点为null,接下来访问右子节点
            if(!stack.isEmpty()){
                //从栈顶取出刚才打印的那个节点，获取其右子节点
                treeNode = stack.pop();
                //打印访问到的节点
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }


    /**
     * 二叉树非递归后序遍历
     * @param root 二叉树根节点
     */
    public static void postOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> rigntStack = new Stack<>();
        Stack<TreeNode> aaa = new Stack<>();
        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                //打印访问到的节点
                rigntStack.push(treeNode);
                //当前节点入栈
                stack.push(treeNode);
                //获取当前节点的左子节点
                treeNode = treeNode.rightChild;
            }

            //代码进行到这里，说明刚才的打印的节点左子节点为null,接下来访问右子节点
            if(!stack.isEmpty()){
                //从栈顶取出刚才打印的那个节点，获取其右子节点
                treeNode = stack.pop();
                treeNode = treeNode.leftChild;
            }
        }
        while (!rigntStack.isEmpty()){
            aaa.push(rigntStack.pop());
        }
        aaa.forEach(r -> System.out.println(r.data));
    }

    /**
     * 二叉树非递归后序遍历
     * @param root 二叉树根节点
     */
    public static void postOrderTraveralWithQueue(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()){
            while (treeNode != null){
                //打印访问到的节点
                queue.offer(treeNode);
                //当前节点入栈
                stack.push(treeNode);
                //获取当前节点的左子节点
                treeNode = treeNode.rightChild;
            }

            //代码进行到这里，说明刚才的打印的节点左子节点为null,接下来访问右子节点
            if(!stack.isEmpty()){
                //从栈顶取出刚才打印的那个节点，获取其右子节点
                treeNode = stack.pop();
                treeNode = treeNode.leftChild;
            }
        }
        while (!queue.isEmpty()){
            System.out.println(queue.removeLast().data);
        }
    }


    /**
     * 二叉树层序遍历
     * @param root 二叉树根节点
     */
    public static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.println(poll.data);
            if(poll.leftChild != null){
                queue.offer(poll.leftChild);
            }
            if(poll.rightChild != null){
                queue.offer(poll.rightChild);
            }
        }
    }


    public static List<List<Integer>> levelOrderTraversal1(TreeNode root){
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if(root == null){
            return ans;
        }

        boolean isOrderLeft = true;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.data);
                } else {
                    levelList.offerFirst(curNode.data);
                }
                if (curNode.leftChild != null) {
                    nodeQueue.offer(curNode.leftChild);
                }
                if (curNode.rightChild != null) {
                    nodeQueue.offer(curNode.rightChild);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        System.out.println(ans);
        return ans;
    }
}
