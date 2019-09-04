/* Map: Time:O(n), Space:O(n), LeetCode has a Space:O(1) solution
 * 1. Visit list and construct a map "copiedPairs"
 * 2. Vist the new list and assign the random pointer
 */

import java.util.*;

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) {label = x;}
}

public class DeepCopy {
    public RandomListNode copyRandomList(RandomListNode head){
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        /* clone main trunk */
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode preNode = dummy;
        for(RandomListNode node = head; node != null; node = node.next){
            map.computeIfAbsent(node, key -> new RandomListNode(key.label));
            RandomListNode cloneNode = map.get(node);
            if(node.random != null && !map.containsKey(node.random)) {
                map.computeIfAbsent(node.random, key -> new RandomListNode(key.label));
            }
            cloneNode.random = map.get(node.random);
            preNode.next = cloneNode;
            preNode = cloneNode;
        }
        return dummy.next;
    }

    public static void main(String[] args){
        DeepCopy dc = new DeepCopy();
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.random = head.next;
        head.next.random = head.next.next;
        head.next.next.random = head;

        System.out.println("head:");
        RandomListNode node = head;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

        RandomListNode cloneHead = dc.copyRandomList(head);
        
        System.out.println("cloneHead:");
        node = cloneHead;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

    }
}
