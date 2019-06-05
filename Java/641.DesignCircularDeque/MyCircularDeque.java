/* List: Time:O(1), Space:O(n)
 * 1. Have a private class "Node" to do circular deque
 */

import java.util.*; 

public class MyCircularDeque {
    private class Node{
        int val;
        Node next;
        Node prev;
        Node(){val = -1; next = this; prev = this;}
        Node(int v, Node n, Node p){val = v; next = n; prev = p;}
    }
    int size;
    int usedCount;
    Node head;
    
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.head = new Node();
        this.size = k;
        this.usedCount = 0;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(usedCount == size){
            return false;
        }else{
            Node node = new Node(value, head.next, head);
            head.next.prev = node;
            head.next = node;
            usedCount++;
            return true;
        }
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(usedCount == size){
            return false;
        }else{
            Node node = new Node(value, head, head.prev);
            head.prev.next = node;
            head.prev = node;
            usedCount++;
            return true;
        }
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(usedCount == 0){
            return false;
        }else{
            head.next.next.prev = head;
            head.next = head.next.next;
            usedCount--;
            return true;
        }
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(usedCount == 0){
            return false;
        }else{
            head.prev.prev.next = head;
            head.prev = head.prev.prev;
            usedCount--;
            return true;
        }
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return (usedCount == 0)? -1: head.next.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return (usedCount == 0)? -1: head.prev.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return (usedCount == 0);
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (usedCount == size);
    }

    public static void main(String[] args){
        int k = 3;
        System.out.println("k:" + k);
        MyCircularDeque circularDeque = new MyCircularDeque(k); // set the size to be 3
        System.out.println(circularDeque.insertLast(1));            // return true
        System.out.println(circularDeque.insertLast(2));            // return true
        System.out.println(circularDeque.insertFront(3));           // return true
        System.out.println(circularDeque.insertFront(4));           // return false, the queue is full
        System.out.println(circularDeque.getRear());            // return 2
        System.out.println(circularDeque.isFull());             // return true
        System.out.println(circularDeque.deleteLast());         // return true
        System.out.println(circularDeque.insertFront(4));           // return true
        System.out.println(circularDeque.getFront());           // return 4
    }
}
