/* Circular queue and Stack: Time:O(1), Space:O(n).
 * 1. Have a circular queue with head's freq = 0, head.next's freq = 1, head.next.next's freq = 2 and so on
 * 2. Have a map "val2Node" to record the mapping between val and node, which the node means the highest freq of the val
 * 3. Update "val2Node" when doing push and pop
 * 4. Return the malware with the maximum spreding area
 */

import java.util.*;

/* Definition for binary tree */
public class FreqStack {
    private class Node{
        Deque<Integer> stack;
        Node next;
        Node prev;
        Node(){stack = new LinkedList<>(); next = this; prev = this;}
    }
    Map<Integer, Node> val2Node;
    Node head;
    
    public FreqStack() {
        head = new Node();
        val2Node = new HashMap<>();
    }
    
    public void push(int x) {
        Node node = val2Node.containsKey(x)? val2Node.get(x): head;
        //hightest frequency
        if(node.next == head){
            Node newNode = new Node();
            newNode.stack.add(x);
            node.next = newNode;
            head.prev = newNode;
            newNode.next = head;
            newNode.prev = node;
            val2Node.put(x, newNode);
        }else{
            Node nextNode = node.next;
            nextNode.stack.add(x);
            val2Node.put(x, nextNode);
        }
    }
    
    public int pop() {
        Node node = head.prev;
        int top = node.stack.pollLast();
        if(node.prev != head){
            val2Node.put(top, node.prev);
        }else{
            val2Node.remove(top);
        }
        
        if(node.stack.isEmpty()){
            node.prev.next = head;
            head.prev = node.prev;
        }
        return top;
    }
 
    public static void main(String[] args){
        FreqStack stack = new FreqStack();
        int val;
        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        val = 7;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        val = 7;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        val = 4;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");
        
        System.out.println("pop():" + stack.pop());
        System.out.println("pop():" + stack.pop());
        System.out.println("pop():" + stack.pop());
        System.out.println("pop():" + stack.pop());
    }
}
