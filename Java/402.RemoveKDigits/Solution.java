/* Greedy: O(n)
 * 1. Delete sb.charAt(index) if sb.charAt(index + 1) < sb.charAt(index)g
 * 2. Delete the the remaining digits from the back of sb
 * 3. Delete the leading zeors, if they exist
 */

import java.util.*; // Stack

public class Solution {
    public String removeKdigits(String num, int k) {
        StringBuffer sb = new StringBuffer(num);
        
        // Delete sb.charAt(index) if sb.charAt(index + 1) < sb.charAt(index)
        int index = 0;
        while(k > 0 && index < (sb.length() - 1)){
            if(sb.charAt(index + 1) < sb.charAt(index)){
                sb.deleteCharAt(index);
                --k;
                index = (index > 0)? index - 1: 0; 
            }
            else{
                ++index;
            }
        }
        
        // Delete the the remaining digits from the back of sb
        while(k > 0 && sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
            --k;
        }
        
        // Delete the leading zeors, if they exist
        while(sb.length() > 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        return (sb.length() == 0)? "0": sb.toString();
    }
 
    public static void main(String[] args){
        Solution sol;
        String num = "1432219";
        String rNum ;
        int k;

        sol = new Solution();
        k = 3;

        System.out.println("num: " + num);

        rNum = sol.removeKdigits(num, k);
        
        System.out.println("rNum: " + rNum);
	}
}
