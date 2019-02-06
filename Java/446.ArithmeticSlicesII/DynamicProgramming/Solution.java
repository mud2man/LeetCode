/* Dynamic Programming: Time:O(n), Space:O(n)
 * 1. dp.get(i).get(diff) is the number of sequence with difference "diff" with length at least 2 ending with A[i]
 * 2. Traverse the input array A from left, then update dp and accumulate returned value "count"
 * 3. If dp.get(j).containsKey(diff), we accumulate dp.get(i) with dp.get(j).get(diff) + 1
 * 4. Otherwise, accumulate dp.get(i) 1
 */

import java.util.*; // Stack

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        List<Map<Long, Integer>> dp = new ArrayList<>();
        for(int i = 0; i < A.length; ++i){
            dp.add(new HashMap<>());
        }
        
        int count = 0;
        for(int i = 1; i < A.length; ++i){
            int num1 = A[i];
            for(int j = i - 1; j >= 0; --j){
                int num0 = A[j];
                long diff = (long)num1 - (long)num0;
                if(dp.get(j).containsKey(diff)){
                    count += dp.get(j).get(diff);
                    dp.get(i).putIfAbsent(diff, 0);
                    dp.get(i).put(diff, dp.get(i).get(diff) + dp.get(j).get(diff) + 1);
                }
                else{
                    dp.get(i).putIfAbsent(diff, 0);
                    dp.get(i).put(diff, dp.get(i).get(diff) + 1);
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
