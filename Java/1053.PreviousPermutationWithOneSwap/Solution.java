/* Greedy: TimeO(n), Space:O(1)
 * 1. Remember the previous digit "prevDigit", and traverse from right to left
 * 2. Stop when we see currDigit > prevDigit, since we found a swap position 
 * 3. We shift "j" to right as long as A[j + 1] < currDigit
 * 4. Correct j to i + 1 if A[j] == A[i + 1]. then swap index i and j
 */

import java.util.*;

public class Solution {
    public int[] prevPermOpt1(int[] A) {
        int prevDigit = A[A.length - 1];
        for(int i = A.length - 2; i >= 0; --i){
            int currDigit = A[i];
            if(currDigit > prevDigit){
                int j = i + 1; 
                while(j + 1 < A.length && A[j + 1] < currDigit){
                    ++j;
                }
                j = (A[j] == A[i + 1])? i + 1: j;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                break;
            }
            prevDigit = currDigit;
        }
        return A;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {3, 1, 1, 3};
        System.out.println("before A: " + Arrays.toString(A));
        System.out.println("after A: " + Arrays.toString(sol.prevPermOpt1(A)));
    }
}
