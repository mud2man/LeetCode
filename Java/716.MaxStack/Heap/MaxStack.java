/* Circular queue + Heap + Map: Time:O(logn), Space:O(n)
 * 1. Have an circular queue with "head", which head.prev is top, and head is bottom
 * 2. Have a maximum heap
 * 3. Have a map with key is number, value is stack of nodes
 * 4. All push, and pop need to update the three data structures
 */

import java.util.*;

public class MaxStack {
        private class Node{
        int val;
        Node next;
        Node prev;
        Node(int v){val = v; next = this; prev = this;}
    }
    
    private class MaxHeapComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y - x;
        }    
    }
    
    PriorityQueue<Integer> maxHeap;
    Node head;
    Map<Integer, Stack<Node>> map;
    int count = 0;
    
    /** initialize your data structure here. */
    public MaxStack() {
        maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        map = new HashMap<Integer, Stack<Node>>();
        head = null;
    }

    public void push(int x) {
        Node newNode = new Node(x);
        if(head == null){
            head = newNode;
        }
        else{
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev.next = newNode;
            head.prev = newNode;
        }
        maxHeap.add(x);
        map.putIfAbsent(x, new Stack<Node>());
        map.get(x).push(newNode);
    }
    
    public int pop() {
        Node top = head.prev;
        if(top == head){
            head = null;
        }
        else{
            top.prev.next = head;
            head.prev = top.prev;
        }

        maxHeap.remove(top.val);
        map.get(top.val).pop();
        return top.val;
    }
    
    public int top() {
        return head.prev.val;
    }
    
    public int peekMax() {
        return maxHeap.peek();
    }
    
    public int popMax() {
        int val = maxHeap.poll();
        Node deleteNode = map.get(val).pop();
        deleteNode.next.prev = deleteNode.prev;
        deleteNode.prev.next = deleteNode.next;
        if(deleteNode == head){
            head = deleteNode.next;
            head = (head == deleteNode)? null: head;
        }
        return val;
    }

    public static void main(String[] args){
        MaxStack stack = new MaxStack();
        int val;

        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");

        val = 1;
        stack.push(val);
        System.out.println("push(" + val + ")");

        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");

        System.out.println("top: " + stack.top());
        System.out.println("popMax: " + stack.popMax());
        System.out.println("top: " + stack.top());
        System.out.println("peekMax: " + stack.peekMax());
        System.out.println("pop: " + stack.pop());
        System.out.println("top: " + stack.top());
    }
}
