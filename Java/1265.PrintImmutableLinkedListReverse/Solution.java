/* Stack: Time:O(n), Space:O(n)
 * 1.Insert nodes in stack, pop the the top and print
 */     

import java.util.*; // Stack

public class Solution {
    interface ImmutableListNode {
        public void printValue(); // print the value of this node.
        public ImmutableListNode getNext(); // return the next node.
    };

    public void printLinkedListInReverse(ImmutableListNode head) {
        if(head == null){
            return;
        }
        Deque<ImmutableListNode> queue = new LinkedList<>();
        queue.add(head);
        while(head.getNext() != null){
            queue.add(head.getNext());
            head = head.getNext();
        }
        while(!queue.isEmpty()){
            queue.pollLast().printValue();
        }
    }
  
    public static void main(String[] args){
        System.out.println("no demo code");
    }
}
