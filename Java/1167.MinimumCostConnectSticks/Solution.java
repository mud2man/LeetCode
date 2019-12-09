/* Greedy: Time:O(nlogn), Space:O(1)
 * 1. Pick the 2 minimum stickes, and do merge, then get the cost in each round
 * 2. Repeat the step1 until sticks# < 2
 *
 * Proof:
 * 1. If the optimal solution has the smallest was mergered to elementX on stage Y. 
 * 2. We can get the smaller of equivalent cost by replacing the one of the merged two element
 * 3. Based on this, we cna replacing the other element by the second smallest element with the smaller of equivalent cost
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
