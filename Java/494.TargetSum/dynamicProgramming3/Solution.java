/* Dynamic programming: Time:O(n*k), Space:O(k), where k = (sum - S) / 2
 * 1. Split the nums to "left" and "right" parts
 * 2. Caculate the sum2Count from left and right respectively
 * 3. Traverse the entry of left "leftSum2Count", and look if there is "S - leftSum" in  "rightSum2Count". Then accumulate count
 */          

import java.util.*; // Stack

public class Solution {
    private Map<Integer, Integer> getSum2Count(int[] nums, int start, int end){
        Map<Integer, Integer> sum2Count = new HashMap<>();
        sum2Count.put(0, 1);
        for(int i = start; i <= end; ++i){
            int num = nums[i];
            Map<Integer, Integer> nextSum2Count = new HashMap<>();
            for(Map.Entry<Integer, Integer>entry: sum2Count.entrySet()){
                int sum = entry.getKey();
                int count = entry.getValue();
                int nextSum = sum + num;
                nextSum2Count.putIfAbsent(nextSum, 0);
                nextSum2Count.put(nextSum, nextSum2Count.get(nextSum) + count);
                nextSum = sum - num;
                nextSum2Count.putIfAbsent(nextSum, 0);
                nextSum2Count.put(nextSum, nextSum2Count.get(nextSum) + count);
            }
            sum2Count = nextSum2Count;
        }
        return sum2Count;
    }
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0){
            return 0;
        }else if(nums.length == 1){
            int count = 0;
            count += (nums[0] == S)? 1: 0;
            count += (nums[0] == -S)? 1: 0;
            return count;
        }else{
            int mid = (nums.length - 1) / 2;
            Map<Integer, Integer> leftSum2Count = getSum2Count(nums, 0, mid);
            Map<Integer, Integer> rightSum2Count = getSum2Count(nums, mid + 1, nums.length - 1);
            int count = 0;
            for(Map.Entry<Integer, Integer>entry: leftSum2Count.entrySet()){
                int leftSum = entry.getKey();
                int leftCount = entry.getValue();
                int rightSum = S - leftSum;
                if(rightSum2Count.containsKey(rightSum)){
                    count += leftCount * rightSum2Count.get(rightSum);
                }
            }
            return count;
        }
    }
 
    public static void main(String[] args){
        int[] nums = {1, 1, 1, 1, 1};
        int S = 1;
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("S: " + S);
        System.out.println("the number of ways of combinations which sum = " + S + ": " + sol.findTargetSumWays(nums, S));
    }
}
