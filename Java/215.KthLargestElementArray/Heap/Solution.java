/* Heap: Time:O(n*logk), Space:O(k)
 */         

import java.util.*;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            if(minHeap.size() < k){
                minHeap.add(num);
            }else{
                if(minHeap.peek() < num){
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args){
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("Kth largest: " + sol.findKthLargest(nums, k));
    }
}
