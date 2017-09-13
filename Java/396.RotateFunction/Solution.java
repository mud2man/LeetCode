/* Math: O(n)
 * 1. The difference form  F(0) to F(1) is unWeightedSum - A[size - 1] - A[size - 1] * (size - 1)
 * 2. The difference form  F(1) to F(2) is unWeightedSum - A[size - 2] - A[size - 2] * (size - 1)
 * 3. The difference form  F(2) to F(3) is unWeightedSum - A[size - 3] - A[size - 3] * (size - 1)
 * 4. Get the maximun among F(0), F(1), ....
 */

import java.util.*;

public class Solution {
    public int maxRotateFunction(int[] A) {
        int unWeightedSum = 0;
        int weightedSum = 0;
        int size = A.length;
        
        for(int i = 0; i < size; ++i){
            unWeightedSum += A[i];
            weightedSum += (i * A[i]);
        }
        
        int max = weightedSum;
        for(int i = size - 1; i > 0; --i){
            weightedSum = weightedSum - (size - 1) * A[i];
            weightedSum = weightedSum + unWeightedSum - A[i];
            max = Math.max(max, weightedSum);
        }
        
        return max;
    }

    public static void main(String[] args){
        Solution sol;
        int[] A = {4, 3, 2, 6};

        sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("max: " + sol.maxRotateFunction(A));
    }
}
