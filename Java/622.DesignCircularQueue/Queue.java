/* Circule double linkedlist: O(1)
 * 1. Have an circular queue with the "front" pointer, and integer number "remain"
 * 2. If front == null, queue is empty
 * 3. If front.next == front, queue has only node
 */

import java.util.*;

public class Queue {
    private class Node{
        int val;
        Node next;
        Node prev;
        Node(int v){val = v; next = this; prev = this;}
    }
    int remain;
    Node front;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public Queue(int k) {
        remain = k;
        front = null;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(remain == 0){
            return false;
        }
        remain--;
        if(front == null){
            front = new Node(value);
        }
        else{
            Node rear = front.prev;
            Node newNode = new Node(value);
            rear.next = newNode;
            front.prev = newNode;
            newNode.prev = rear;
            newNode.next = front;
        }
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(front == null){
            return false;
        }
        else if(front == front.next){
            remain++;
            front = null;
            return true;
        }
        else{
            remain++;
            Node rear = front.prev;
            Node nextFront = front.next;
            rear.next = nextFront;
            nextFront.prev = rear;
            front = nextFront;
            return true;
        }
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return (front == null)? -1: front.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return (front == null)? -1: front.prev.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (front == null);
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (remain == 0);
    }
 
    public static void main(String[] args){
        Queue queue = new Queue(3);
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
