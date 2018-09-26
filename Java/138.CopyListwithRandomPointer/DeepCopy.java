/* Map: Time:O(n), Space:O(n), LeetCode has a Space:O(1) solution
 * 1. Visit list and construct a map "copiedPairs"
 * 2. Vist the new list and assign the random pointer
 */

import java.util.*;

class RandomListNode {
    int label;
    RandomListNode next, random;
    
    RandomListNode(int x) {
        this.label = x;
    }
}

public class DeepCopy {
    public RandomListNode copyRandomList(RandomListNode head){
        HashMap<RandomListNode, RandomListNode> map;
        RandomListNode node, cloneNode, cloneHead, preNode;
        
        map = new HashMap<RandomListNode, RandomListNode>();
        
        /* clone main trunk */
        preNode = null;
        cloneHead = null;
        for(node = head; node != null; node = node.next){
            if(!map.containsKey(node))
                map.put(node, new RandomListNode(node.label));
            cloneNode = map.get(node);
            if(node.random != null && !map.containsKey(node.random))
                map.put(node.random, new RandomListNode(node.random.label));   
            cloneNode.random = map.get(node.random);
            if(preNode == null){
                cloneHead = cloneNode;
            }
            else{
                preNode.next = cloneNode;
            }
            preNode = cloneNode;
        }
        
        return cloneHead;
    }

    public static void main(String[] args){
        RandomListNode head;
        RandomListNode cloneHead;
        RandomListNode node;
        DeepCopy dc;

        dc = new DeepCopy();

        head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.random = head.next;
        head.next.random = head.next.next;
        head.next.next.random = head;

        System.out.println("head:");
        node = head;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

        cloneHead = dc.copyRandomList(head);
        
        System.out.println("cloneHead:");
        node = cloneHead;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

    }
}
