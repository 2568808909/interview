package com.ccb.interview.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
    Map<Integer, Node> cache;  // 存储缓存的内容
    Map<Integer, LinkedHashSet<Node>> freqMap; // 存储每个频次对应的双向链表
    int size;
    int capacity;
    int min; // 存储当前最小频次

    public LFUCache(int capacity) {
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (size == capacity) {
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
        }
    }

    void freqInc(Node node) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) {
            min = freq + 1;
        }
        // 加入新freq对应的链表
        node.freq++;
        LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(node);
    }

    void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(1, set);
        }
        set.add(node);
        min = 1;
    }

    Node removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }
}

class Node {
    int key;
    int value;
    int freq = 1;

    public Node() {}

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
//使用堆
//class LFUCache {
//
//    Map<Integer, Node> cache;
//    Queue<Node> queue;
//    int capacity;
//    int size;
//    int idx = 0;
//
//    public LFUCache(int capacity) {
//        cache = new HashMap<>(capacity);
//        if (capacity > 0) {
//            queue = new PriorityQueue<>(capacity);
//        }
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        Node node = cache.get(key);
//        if (node == null) {
//            return -1;
//        }
//        node.freq++;
//        node.idx = idx++;
//        queue.remove(node);
//        queue.offer(node);
//        return node.value;
//
//    }
//
//    public void put(int key, int value) {
//        if (capacity == 0) {
//            return;
//        }
//        Node node = cache.get(key);
//        if (node != null) {
//            node.value = value;
//            node.freq++;
//            node.idx = idx++;
//            queue.remove(node);
//            queue.offer(node);
//        } else {
//            if (size == capacity) {
//                cache.remove(queue.peek().key);
//                queue.poll();
//                size--;
//            }
//            Node newNode = new Node(key, value, idx++);
//            cache.put(key, newNode);
//            queue.offer(newNode);
//            size++;
//        }
//    }
//}
//
//class Node implements Comparable<Node> {
//    int key;
//    int value;
//    int freq;
//    int idx;
//
//    public Node() {}
//
//    public Node(int key, int value, int idx) {
//        this.key = key;
//        this.value = value;
//        freq = 1;
//        this.idx = idx;
//    }
//
//    public int compareTo(Node node) {
//        int diff = freq - node.freq;
//        return diff != 0? diff: idx - node.idx;
//    }
//}
