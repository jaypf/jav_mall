package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.HashMap;

/**
 * @ClassName a146LRU缓存机制
 * @Description TODO
 * @Author Jay
 * @Date 2021/4/18 2:27
 * @Version 1.0
 */
public class a146LRU缓存机制 {
//    运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
//
//    实现 LRUCache 类：
//
//    LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
//    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
//    void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
//    当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间

//    示例：
//
//    输入
//      ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//    输出
//      [null, null, null, 1, null, -1, null, -1, 3, 4]
//
//    解释
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        lRUCache.get(1);    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        lRUCache.get(2);    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        lRUCache.get(1);    // 返回 -1 (未找到)
//        lRUCache.get(3);    // 返回 3
//        lRUCache.get(4);    // 返回 4

    @Test
    public void test() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        int i3 = lRUCache.get(3);// 返回 3
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println("i="+i+",i1="+i1+",i2="+i2+",i3="+i3+",i4="+i4);
    }


    private Node head;
    private Node end;
    //缓存存储上限
    private int limit;

    private HashMap<Integer, Node> hashMap;

    class Node {
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        public Node pre;
        public Node next;
        public int key;
        public int value;
    }

    class LRUCache {

        public LRUCache(int capacity) {
            limit = capacity;
            hashMap = new HashMap<>();
        }

        public int get(int key) {
            Node node = hashMap.get(key);
            if(node == null){
                return -1;
            }
            //refresh,需要把最近访问元素移动到最末尾
            refreshNode(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = hashMap.get(key);
            if(node == null){
                if(hashMap.size() >= limit){
                    Integer oldKey = removeNode(head);
                    hashMap.remove(oldKey);
                }
                node = new Node(key, value);
                addNode(node);
                hashMap.put(key, node);
            }else {
                //如果Key存在，则刷新Key-Value
                node.value = value;
                refreshNode(node);
            }
        }

        public void remove(String key) {
            Node node = hashMap.get(key);
            removeNode(node);
            hashMap.remove(key);
        }

        /**
         * 刷新被访问的节点位置
         * @param node 被访问的节点
         */
        private void refreshNode(Node node) {
            //如果是尾节点无需移动
            if(node == end){
                return;
            }
            //移除节点
            removeNode(node);
            //重新插入节点
            addNode(node);
        }

        /**
         * 删除节点
         * @param node 要删除的节点
         */
        private Integer removeNode(Node node) {
            //如果是唯一的节点
            if(node == head && node == end){
                head = null;
                end = null;
            }
            //如果是头结点
            else if (node == head){
                head = head.next;
                head.pre = null;
            }
            //如果是尾结点
            else if (node == end){
                end = end.pre;
                end.next = null;
            }
            //如果是中间节点
            else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            return node.key;
        }

        /**
         * 尾部插入节点
         * @param node 要插入的节点
         */
        private void addNode(Node node) {
            if(head == null){
                head = node;
            }
            if(end != null){
                end.next = node;
                node.pre = end;
                node.next = null;
            }
            end = node;
        }

    }


}
