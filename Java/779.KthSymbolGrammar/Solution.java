/* Math: Time:O(logn), Space:O(logn), where n is K
 * 1. We can use deduction to prove the number in N-th row concatenate with the number in (N-1)-th row 
 * 2. So we just need to find the K-th number in a single row, where the K-th number is produce by the K/2-th number
 * 3. When K is odd, K-th number = !((K/2)-th number). Otherwise, K-th number = ((K/2)-th number)
 */

import java.util.*;

public class Solution{
    private int helper(int index, boolean flip){
        if(index == 0){
            return (flip)? 1: 0;
        }
        else if(index == 1){
            return (flip)? 0: 1;
        }
        else{
            if(index % 2 == 1){
                return helper(index / 2, !flip);
            }
            else{
                return helper(index / 2, flip);
            }
        }
    }
    
    public int kthGrammar(int N, int K) {
        return helper(K - 1, false);
    }

    public static void main(String[] args){
        Solution sol;
        int N = 4;
        int K = 5;

        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("K: " + K);
        System.out.println("sequenc: 01101001");
        System.out.println("k-th nimber: " + sol.kthGrammar(N, K));
    }
}
