/* Dynamic Programming: Time:O(n^2), Space:O(n^2)
 * 1. dp.get(i).get(diff) is the number of sequence with difference "diff" with length at least 2 ending with A[i]
 * 2. Traverse the input array A from left, then update dp and accumulate returned value "count"
 * 3. If dp.get(j).containsKey(diff), we accumulate dp.get(i) with dp.get(j).get(diff) + 1
 * 4. Otherwise, accumulate dp.get(i) 1
 */

import java.util.*; // Stack

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        Map<Integer, Map<Long, Integer>> dp = new HashMap<>();
        dp.put(0, new HashMap<>());
        for(int i = 1; i < A.length; ++i){
            int num0 = A[i];
            Map<Long, Integer> currDiffMap = new HashMap<>();
            for(int j = i - 1; j >= 0; --j){
                int num1 = A[j];
                long diff = (long)A[i] - (long)A[j];
                int localCount = 1;
                if(dp.get(j).containsKey(diff)){
                    count += dp.get(j).get(diff);
                    localCount += dp.get(j).get(diff);
                }
                currDiffMap.putIfAbsent(diff, 0);
                currDiffMap.put(diff, currDiffMap.get(diff) + localCount);
            }
            dp.put(i, currDiffMap);
        }
        return count; 
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 2, 3, 4}; 
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("slices: " + sol.numberOfArithmeticSlices(A));
    }
}
