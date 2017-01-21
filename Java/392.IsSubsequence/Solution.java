/* Two pointers: O(n)
 * 1. Traverse the input string "t" from the leftest
 * 2. Keep the current position "idxs" of string "s" and "idxt" of string "t"
 * 3. If the idxs == s.length(), string "s" is a subsequence of string "t"
 */

import java.util.*;

public class Solution{
    public boolean isSubsequence(String s, String t) {
        int idxs;
        int idxt;
        
        if(s.length() == 0){
            return true;
        }
        
        for(idxt = 0, idxs = 0; idxt < t.length(); idxt++){
            if(s.charAt(idxs) == t.charAt(idxt)){
                idxs++;
                if(idxs == s.length()){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        String t = "ahbgdc";
        String s = "abc";
        boolean isSubsequence;

        sol = new Solution();

        System.out.println("t: " + t);
        System.out.println("s: " + s);

        isSubsequence = sol.isSubsequence(s, t);

        System.out.println("isSubsequence: " + isSubsequence);
    }
}
