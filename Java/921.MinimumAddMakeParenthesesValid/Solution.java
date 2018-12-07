/* Greedy: Time:O(n), Space:O(1). LeetCode has one shot solution
 * 1. Have an array "pair" to record the '(' number and ')' number
 * 2. Traverse from left, and treat '(' as producer, and ')' as consumer. The accumulate count with pair[1]
 * 3. Traverse from right, and treat ')' as producer, and '(' as consumer. The accumulate count with pair[0]
 * 4. The first loop is to count how many ')' not be paired
 * 5. The second loop is to count how many '(' not be paired
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
                }
                else{
                    pair[1]++;
                }
            }
        }
        
        //')':producer, '(':consumer
        int count = pair[1];
        pair = new int[2];
        for(int i = S.length() - 1; i >= 0; --i){
            char c = S.charAt(i);
            if(c == ')'){
                pair[1]++;
            }
            else{
                if(pair[1] > 0){
                    pair[1]--;
                }
                else{
                    pair[0]++;
                }
            }
        }
        count += pair[0];
        return count;
    }
     
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "()))((";
        System.out.println("S: " + S);
        System.out.println("fix count: " + sol.minAddToMakeValid(S));
    }
}
