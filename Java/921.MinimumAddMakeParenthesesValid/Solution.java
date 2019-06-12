/* Greedy: Time:O(n), Space:O(1).
 * 1. Have an array "pair" to record the '(' number and ')' number
 * 2. Traverse from left, and treat '(' as producer, and ')' as consumer. The accumulate count with pair[0] and pair[1]
 * 3. pair[1] is to count how many ')' not be paired, pair[0] is the count of  how many '(' not be paired
 */

import java.util.*;

public class Solution{
    public int minAddToMakeValid(String S) {
        //pair[0]:number of '(', pair[1]:number of ')'
        int[] pair = new int[2];
        //'(':producer, ')':consumer
        for(int i = 0; i < S.length(); ++i){
            char c = S.charAt(i);
            if(c == '('){
                pair[0]++;
            }
            else{
                if(pair[0] > 0){
                    pair[0]--;
                }else{
                    pair[1]++;
                }
            }
        }
        return pair[0] + pair[1];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "()))((";
        System.out.println("S: " + S);
        System.out.println("fix count: " + sol.minAddToMakeValid(S));
    }
}
