/* Map: Time:O(n^2), Space:O(n^2)
 * 1. Get the map "abSumCount" to store the sum of A[i] and B[j] to count
 * 2. Traverse C and D, and accumulate count if we found -(C[i] + D[j]) in abSumCount
 */

import java.util.*;


public class Solution{
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> abSumCount = new HashMap<>();
        for(int i = 0; i < A.length; ++i){
            for(int j = 0; j < B.length; ++j){
                int sum = A[i] + B[j];
                abSumCount.putIfAbsent(sum, 0);
                abSumCount.put(sum, abSumCount.get(sum) + 1);
            }
        }
        
        int count = 0;
        for(int i = 0; i < C.length; ++i){
            for(int j = 0; j < D.length; ++j){
                int cdsum = C[i] + D[j];
                count += abSumCount.containsKey(-cdsum)? abSumCount.get(-cdsum): 0;
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("B:" + Arrays.toString(B));
        System.out.println("C:" + Arrays.toString(C));
        System.out.println("D:" + Arrays.toString(D));
        System.out.println("count:" + sol.fourSumCount(A, B, C, D));
    }
}
