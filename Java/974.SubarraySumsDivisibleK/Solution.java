/* Hash: Time:O(n), Space:O(n)
 * 1. Record the map sum2Count, where sum is module by K
 * 2. Accumulate count of the subarray ending with A[i] has sum with target and K - target(target > 0) / K + target(target < 0)
 */

import java.util.*; // Stack

public class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> sum2Count = new HashMap<>();
        sum2Count.put(0, 1);
        int sum = 0;
        int count = 0;
        for(int a: A){
            sum = (sum + a) % K;
            int target = sum;
            count += sum2Count.getOrDefault(target, 0);
            if(target != 0){
               count += sum2Count.getOrDefault((target > 0)? -(K - target): (K + target), 0); 
            }
            sum2Count.putIfAbsent(sum, 0);
            sum2Count.put(sum, sum2Count.get(sum) + 1);
        }
        return count; 
    }
  
    public static void main(String[] args){
        int K = 5;
        int[] A = {4, 5, 0, -2, -3, 1};
        Solution sol = new Solution();
        System.out.println(String.format("K: %d", K));
        System.out.println(String.format("A: %s", Arrays.toString(A)));
        System.out.println(String.format("subarray#: %d", sol.subarraysDivByK(A, K)));
    }
}
