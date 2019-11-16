/* Prefix sum: Time:O(n), Spaceo(n)
 * 1. Get the prefix sum of shifts. ex: {3, 5, 9} -> {3 + 5 + 9, 5 + 9, 9}
 * 2. Shift each character of S by shifts
 */

import java.util.*; // Stack

public class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        for(int i = shifts.length - 1, sum = 0; i >= 0; --i){
            shifts[i] = (sum + shifts[i]) % 26;
            sum = shifts[i];
        }
        
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < S.length(); ++i){
            char shiftedC = (char)((((int)(S.charAt(i) - 'a') + shifts[i]) % 26) + 'a');
            sb.append(shiftedC);
        }
        return sb.toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "abc";
        int[] shifts = {3, 5, 9};
        System.out.println("S:" + S);
        System.out.println("shifts:" + Arrays.toString(shifts));
        System.out.println("after shift:" + sol.shiftingLetters(S, shifts));
    }
}
