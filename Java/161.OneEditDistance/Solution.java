/* Two pointer: O(n + m)
 * 1. If the two strings have the same length, compare each character one by one
 * 2. Otherwise, let longString denote the longer string, shortString be the shorter one
 * 3. Traverse from ptrLong = 0 and ptrShort = 0, accumulate diffCount and move ptrLong forward when encounters a different char
 */

import java.util.*;

public class Solution{
    public boolean isOneEditDistance(String s, String t) {
        int diffCount = 0;
        
        if(s.length() == t.length()){
            for(int ptr = 0; ptr < s.length(); ++ptr){
                if(s.charAt(ptr) != t.charAt(ptr)){
                    diffCount++;
                }
            }
        }
        else{
            String longString;
            String shortString;
            if(s.length() > t.length()){
                longString = s;
                shortString = t;
            }
            else{
                longString = t;
                shortString = s;
            }
            
            int ptrLong = 0;
            int ptrShort = 0;
            while(ptrLong < longString.length() && ptrShort < shortString.length()){
                if(longString.charAt(ptrLong) != shortString.charAt(ptrShort)){
                    ptrLong++;
                    diffCount++;
                }
                else{
                    ptrLong++;
                    ptrShort++;
                }
            }
            diffCount += longString.length() - ptrLong;
        }
        
        return (diffCount == 1);
    }

    public static void main(String[] args){
        Solution sol;
        String s = "abc";
        String t = "abcd";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("t: " + t);
        System.out.println("isOneEditDistance: " + sol.isOneEditDistance(s, t));
    }
}
