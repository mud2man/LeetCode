/* Circular linkedlist: Time:O(n), Space:O(n)
 * 1. Tail id head.prev
 */

import java.util.*;

public class Solution{
    private class Node{
        int val;
        Node next;
        Node prev;
        Node(int v){val = v; next = this; prev = this;}
    }
    int length;
    Node head;

    /** Initialize your data structure here. */
    public Solution() {
        this.length = 0;
        this.head = null;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index < 0 || index >= this.length){
            return -1;
        }
        Node itr = head;
        for(int i = 0; i < index; ++i){
            itr = itr.next;
        }
        return itr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        this.length++;
        Node newNode = new Node(val);
        if(length > 1){
            newNode.next = this.head;
            newNode.prev = this.head.prev;
            this.head.prev.next = newNode;
            this.head.prev = newNode;
        }
        this.head = newNode; 
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        this.length++;
        Node newNode = new Node(val);
        if(length > 1){
            newNode.next = this.head;
            newNode.prev = this.head.prev;
            this.head.prev.next = newNode;
            this.head.prev = newNode;
        }else{
            this.head = newNode;
        }
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index == 0 || (index == -1 && this.head == null)){
            addAtHead(val);
        }else if(index == this.length){
            addAtTail(val);
        }else if(index > 0 && index < this.length){
            this.length++;
            Node itr = head;
            for(int i = 0; i < index; ++i){
                itr = itr.next;
            }
            Node newNode = new Node(val);
            newNode.next = itr;
            newNode.prev = itr.prev;
            itr.prev.next = newNode;
            itr.prev = newNode;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index >= 0 && index < this.length){
            this.length--;
            if(this.length == 0){
                this.head = null;
            }else{
                Node itr = head;
                for(int i = 0; i < index; ++i){
                    itr = itr.next;
                }
                this.head = (index == 0)? this.head.next: this.head;
                itr.prev.next = itr.next;
                itr.next.prev = itr.prev;
            }
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        sol.addAtHead(1);
        sol.addAtTail(3);
        sol.addAtIndex(1, 2);
        System.out.println("get(1):" + sol.get(1));
        sol.deleteAtIndex(1);
        System.out.println("get(1):" + sol.get(1));
    }
}
