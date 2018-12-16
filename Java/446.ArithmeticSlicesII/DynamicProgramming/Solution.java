/* Dynamic Programming: Time:O(n), Space:O(n)
 * 1. dp.get(i).get(diff) is the number of sequence with difference "diff" with length at least 2
 * 2. Traverse the input array A from right end, then update dp and accumulate returned value "num"
 */

import java.util.*; // Stack

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        List<Map<Long, Integer>> dp = new ArrayList<>();
        for(int i = 0; i < A.length; ++i){
            dp.add(new HashMap<>());
        }
        
        int count = 0;
        for(int i = A.length - 2; i >= 0; --i){
            int num0 = A[i];
            for(int j = i + 1; j < A.length; ++j){
                int num1 = A[j];
                long diff = (long)num1 - (long)num0;
                dp.get(i).putIfAbsent(diff, 0);
                if(dp.get(j).containsKey(diff)){
                    count += dp.get(j).get(diff);
                    dp.get(i).put(diff, dp.get(j).get(diff) + 1 + dp.get(i).get(diff));
                }
                else{
                    dp.get(i).put(diff, 1 + dp.get(i).get(diff));
                }
            }
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
