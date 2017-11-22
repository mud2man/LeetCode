/* Greedy: Time:O(n*m), Space:O(1)
 * 1. Have two pointers ptrS and ptrP
 * 2. If p.charAt(ptrP) == '?' || p.charAt(ptrP) == s.charAt(ptrS), then shift ptrS and ptrP
 * 3. If p.charAt(ptrP) == '*', then only shift ptrP, and record matchIndex as ptrS, 
      which means s.substring(0, matchIndex + 1) can be matched
 * 4. If starIndex != -1(p have '*'), reset ptrP as starIndex + 1, and ptrS = matchIndex + 1, to restart match
 *
 */

import java.util.*;

public class Solution{
    public boolean isMatch(String s, String p) {
        int ptrS = 0;
        int ptrP = 0;
        int starIndex = -1;
        int matchIndex = 0;
        
        while(ptrS < s.length()){
            if(ptrP < p.length() && (p.charAt(ptrP) == '?' || p.charAt(ptrP) == s.charAt(ptrS))){
                ptrS++;
                ptrP++;
            }
            else if(ptrP < p.length() && p.charAt(ptrP) == '*'){
                starIndex = ptrP;
                ptrP++;
                matchIndex = ptrS;
            }
            else if(starIndex != -1){
                matchIndex++;
                ptrS = matchIndex;
                ptrP = starIndex + 1;
            }
            else{
                return false;
            }
        }
        
        for(int i = ptrP; i < p.length(); ++i){
            if(p.charAt(i) != '*'){
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "bbaab";
        String p = "**a**b";
        
        System.out.println("s: " + s);
        System.out.println("p: " + p);
        System.out.println("isMatch:" + sol.isMatch(s, p));
    }
}
