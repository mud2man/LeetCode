/* Greedy: Time:O(n), Space:O(n). However Leetcode has shorter answer
 * 1. Create a maximum heap sorted with the count of every character
 * 2. Rertieve character until maxHeap is empty
 * 3. Every time, take k nodes from maxHeap
 * 4. First, assign answer[currentIndex] with answer[previousIndex] if cache has answer[previousIndex] 
 * 5. Second,retrieve the unused node from cache, and put it into the closest available position where answer[index] == '?' 
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
        
        if(k > 26){
            return "";
        }
        
        int[] frequency = new int[26];
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            frequency[c - 'a']++;
        }
        
        //build the maximum heap
        PriorityQueue<HeapNode> maxHeap = new PriorityQueue<HeapNode>(new HeapComparator());
        for(int i = 0; i < 26; ++i){
            if(frequency[i] > 0){
                maxHeap.add(new HeapNode((char)('a' + i), frequency[i]));
            }
        }
           
        HashMap<Character, HeapNode> map = new HashMap<Character, HeapNode>();
        LinkedList<HeapNode> cache = new LinkedList<HeapNode>();
        char[] answer = new char[s.length()];
        Arrays.fill(answer, '?');
        int remain = s.length(); 
        int currentIndex = 0;
        while(!maxHeap.isEmpty()){
            for(int i = 0; i < k && !maxHeap.isEmpty(); ++i){
                HeapNode node = maxHeap.poll();
                node.count--;
                map.put(node.letter, node);
                cache.add(node);
                remain--;
            }
            
            if(cache.size() < k && remain > 0){
                return "";
            }
            
            //assign answer[currentIndex] with answer[previousIndex] if cache has answer[previousIndex]    
            int previousIndex = currentIndex - k;
            int size = (previousIndex >= 0)? k: 0;
            for(int i = 0; i < size; ++i){
                char c = answer[previousIndex];
                if(map.containsKey(c)){
                    answer[currentIndex] = c;
                    if(map.get(c).count > 0){
                        maxHeap.add(map.get(c));
                    }
                    map.remove(c);
                }
                currentIndex++;
                previousIndex++;
            }
            
            //retrieve the unused node from cache, and put it into the closest available position where answer[index] == '?'
            int tempIndex = (currentIndex > 0)? currentIndex - k: 0;
            while(!cache.isEmpty()){
                HeapNode node = cache.pollFirst();
                char c = node.letter;
                if(map.containsKey(c)){
                    while(answer[tempIndex] != '?'){tempIndex++;}
                    answer[tempIndex] = c;
                    if(node.count > 0){
                        maxHeap.add(node);
                    }
                    map.remove(c);
                }
            }
            currentIndex = Math.max(tempIndex + 1, currentIndex);
        }
        
        return new String(answer);
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
