/* Two Pointer: Time:O(n^2), Space:O(1). The optimal solution is Manacher's Algorithm
 * 1. Have a utility method extend to conut the palidrom with extending tow ends 'h' and 't'
 * 2. Traverse string s, and extend with odd and even length
 */          

import java.util.*; // Stack

public class Solution {
    private int extend(String s, int h, int t){
        int count = 0;
        while(h >= 0 && t < s.length() && s.charAt(h) == s.charAt(t)){
            count++;
            --h;
            ++t;
        }
        return count;
    }
    
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); ++i){
            count += extend(s, i, i);
            count += extend(s, i - 1, i);
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol;
        String s = "aaa";

        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("count: " + sol.countSubstrings(s));
    }
}
