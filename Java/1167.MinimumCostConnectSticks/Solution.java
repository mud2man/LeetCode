/* Greedy: Time:O(n), Space:O(1). We need to prove greedy algo can be optimal
 * 1. Pick the 2 minimum stickes, and do merge, then get the cost in each round
 * 2. Repeat the step1 until sticks# < 2
 */

import java.util.*;

public class Solution{
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int stick: sticks){
            minHeap.add(stick);
        }
        int cost = 0;
        while(minHeap.size() >= 2){
            int mergedStick = minHeap.poll() + minHeap.poll();
            cost += mergedStick;
            minHeap.add(mergedStick);
        }
        return cost;
    }
 
    public static void main(String[] args){
        int[] sticks = {2, 4, 3};
        Solution sol = new Solution();
        System.out.println("sticks:" + Arrays.toString(sticks));
        System.out.println("minimum cost:" + sol.connectSticks(sticks));
    }
}
