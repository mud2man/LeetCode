/* Circule double linkedlist: O(1)
 * 1. Have an circular queue with the "front" pointer, and integer number "remain"
 * 2. If front == null, queue is empty
 * 3. If front.next == front, queue has only node
 */

import java.util.*;

public class MyCircularQueue {
    private class Node{
        int val;
        Node next;
        Node prev;
        Node(int v){val = v; next = this; prev = this;}
    }
    int remain;
    Node head;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.remain = k;
        this.head = new Node(-1);
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(remain == 0){
            return false;
        }
        remain--;
        Node newNode = new Node(value);
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        remain++;
        Node rear = head.prev;
        rear.prev.next = head;
        head.prev = rear.prev;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty()? -1: head.prev.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty()? -1: head.next.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (head.next == head);
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (remain == 0);
    }
  
    public static void main(String[] args){
        MyCircularQueue queue = new MyCircularQueue(3);
        int val;

        val = 1;
        System.out.println("enQueue(" + val + "): " + queue.enQueue(val));

        val = 2;
        System.out.println("enQueue(" + val + "): " + queue.enQueue(val));

        val = 3;
        System.out.println("enQueue(" + val + "): " + queue.enQueue(val));

        val = 4;
        System.out.println("enQueue(" + val + "): " + queue.enQueue(val));

        System.out.println("rear(): " + queue.Rear());
        System.out.println("isFull(): " + queue.isFull());
        System.out.println("deQueue(): " + queue.deQueue());
        
        val = 4;
        System.out.println("enQueue(" + val + "): " + queue.enQueue(val));

        System.out.println("rear(): " + queue.Rear());
    }
}
