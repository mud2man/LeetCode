/* Greedy: Time:O(n), Space:O(n).
 * 1. Have a maximum heap "maxHeap" sorted with the count of every character
 * 2. Have a wait queue "waitQueue", add the node polled ffrom maxHeap
 * 3. When the size of wiatQueue is equal to k, which mean the head is wait long enough. We can poll it and add it to maxHeap
 * 4. Repeat until maxHeap is empty 
 */

import java.util.*;

public class Solution{
    private class HeapNode{
        char letter;
        int count;
        HeapNode(char l, int c){letter = l; count = c;}
    }
    
    private class HeapComparator implements Comparator <HeapNode>{
        public int compare(HeapNode x, HeapNode y){
            return y.count - x.count;
        }
    }
    
    public String rearrangeString(String s, int k) {
        if(k == 0 || k == 1){
            return s;
        }

        int[] frequency = new int[26];
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            frequency[c - 'a']++;
        }
        
        PriorityQueue<HeapNode> maxHeap = new PriorityQueue<HeapNode>(new HeapComparator());
        for(int i = 0; i < 26; ++i){
            if(frequency[i] > 0){
                maxHeap.add(new HeapNode((char)('a' + i), frequency[i]));
            }
        }
          
        StringBuilder ret = new StringBuilder("");
        LinkedList<HeapNode> waitQueue = new LinkedList<HeapNode>();
        while(!maxHeap.isEmpty()){
            HeapNode top = maxHeap.poll();
            ret.append(top.letter);
            top.count--;
            waitQueue.add(top);
            if(waitQueue.size() == k){
                HeapNode head = waitQueue.pollFirst();
                if(head.count > 0){
                    maxHeap.add(head);
                }
            }
        }
        
        return (s.length() == ret.length())? ret.toString(): "";
    }
 
    public static void main(String[] args){
        Solution sol;
        int k  = 2;
        String s = "aaadbbcc";
        sol = new Solution();
        
        System.out.println("s: " + s);
        System.out.println("k: " + k);
        System.out.println("after rearranged: " + sol.rearrangeString(s, k));
    }
}
