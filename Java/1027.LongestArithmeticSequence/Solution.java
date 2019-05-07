/* Dynamic programming: Time:O(n^2), Space:O(n^2)
 * 1. Have a hahmap list "dp" to store the length given "diff" with ending at A[i]
 * 2. Traverse A[i] and traverse back to get the maximum length ending at A[i] with diff = (A[i] - A[j])
 */

import java.util.*; // Stack

public class Solution {
    public int longestArithSeqLength(int[] A) {
        List<Map<Integer, Integer>> dp = new ArrayList<>();
        for(int i = 0; i < A.length; ++i){
            dp.add(new HashMap<>());
        }
        
        int max = 2;
        for(int i = 0; i < A.length; ++i){
            for(int j = i - 1; j >= 0; --j){
                int diff = A[i] - A[j];
                if(dp.get(j).containsKey(diff) && !dp.get(i).containsKey(diff)){
                    dp.get(i).put(diff, dp.get(j).get(diff) + 1);
                    max = Math.max(max, dp.get(i).get(diff));
                }
                dp.get(i).putIfAbsent(diff, 2);
            }        
        }
        return max;
    }

    public static void main(String[] args){
        int[] A = {9, 4, 7, 2, 10};
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("longest length: " + sol.longestArithSeqLength(A));
    }
}
