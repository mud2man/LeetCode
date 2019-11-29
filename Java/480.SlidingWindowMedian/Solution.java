/* Heap: Time:O(nlogk), Space:O(k)
 * 1. Have a maxHeap to store the smaller half
 * 2. Have a minHeap to store the bigger half
 */

import java.util.*;

public class Solution{
    private void update(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int val, boolean isInsert){
        if(isInsert){
            if(maxHeap.isEmpty() || maxHeap.peek() >= val){
                maxHeap.add(val);
            }else{
                minHeap.add(val);
            }
        }else{
            if(maxHeap.peek() >= val){
                maxHeap.remove(val);
            }else{
                minHeap.remove(val);
            }
        }
        
        if(minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        }
        if(maxHeap.size() > (minHeap.size() + 1)){
            minHeap.add(maxHeap.poll());
        }
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y.compareTo(x)));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < k - 1; ++i){
            update(maxHeap, minHeap, nums[i], true);
        }
        
        double[] medians = new double[nums.length - k + 1];
        for(int i = k - 1, j = 0; i < nums.length; ++i, ++j){
            update(maxHeap, minHeap, nums[i], true);
            medians[j] =(k % 2 == 1)? (double)maxHeap.peek(): ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
            update(maxHeap, minHeap, nums[i - k + 1], false);
        }
        return medians;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution() ;
        int k = 3;
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("k:" + k);
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("medians:" + Arrays.toString(sol.medianSlidingWindow(nums, k)));
    }
}
