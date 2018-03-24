/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. Have an array "minNoSwap", minNoSwap[i] indicates the minimun swaps if A[i] and B[i] not swapped
 * 2. Have an array "minSwap", minNoSwap[i] indicates the minimun swaps if A[i] and B[i] swapped
 * 3. Update minNoSwap and minSwap, if B[i - 1] < B[i] && A[i - 1] < A[i]
 * 4. Update minNoSwap and minSwap, if B[i - 1] < A[i] && A[i - 1] < B[i]
 */

import java.util.*;

public class Solution{
    public int minSwap(int[] A, int[] B) {
        int size = A.length;
        int[] minNoSwap = new int[2];
        int[] minSwap = new int[2];
        minSwap[0] = 1;
        
        for(int i = 1; i < size; ++i){
            //caculate the minimum swap count if A[i] and B[i] not swaped
            minNoSwap[i % 2] = size;
            if(A[i - 1] < A[i] && B[i - 1] < B[i]){
                minNoSwap[i % 2] = minNoSwap[(i - 1) % 2];
            }
            
            if(B[i - 1] < A[i] && A[i - 1] < B[i]){
                minNoSwap[i % 2] = Math.min(minNoSwap[i % 2], minSwap[(i - 1) % 2]);
            }
            
            //caculate the minimum swap count if A[i] and B[i] swaped
            minSwap[i % 2] = size;
            if(A[i - 1] < B[i] && B[i - 1] < A[i]){
                minSwap[i % 2] = minNoSwap[(i - 1) % 2] + 1;
            }
            
            if(B[i - 1] < B[i] && A[i - 1] < A[i]){
                minSwap[i % 2] = Math.min(minSwap[i % 2], minSwap[(i - 1) % 2] + 1);
            }
        }
        
        return Math.min(minNoSwap[(size - 1) % 2], minSwap[(size - 1) % 2]);
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] A = {1, 3, 5, 4};
        int[] B = {1, 2, 3, 7};
        
        sol = new Solution();
        System.out.println("A[]: " + Arrays.toString(A));
        System.out.println("B[]: " + Arrays.toString(B));
        System.out.println("minimum swaps: " + sol.minSwap(A, B));
    }
}
