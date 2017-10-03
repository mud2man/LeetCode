/* Backtrack with memorization: O(n!)
 * 1. If any case can make sure I win in the next match, the I win 
 * 2. iWin return the result if the current user can win when he get the initial string s no matter me or opponant
 */

import java.util.*;

public class Solution{
    private boolean iWin(Set<String> winSet, Set<String> loseSet, String s){
        if(winSet.contains(s)){return true;}
        if(loseSet.contains(s)){return false;}
        
        int i = 0;
        while(i < s.length()){
            int nextIndex = s.indexOf("++", i);
            if(nextIndex != -1){
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(nextIndex++, '-');
                sb.setCharAt(nextIndex, '-');
                
                if(!iWin(winSet, loseSet, sb.toString())){
                    winSet.add(s);
                    return true;
                }
                i = nextIndex;
            }
            else{
                break;
            }
        }

        loseSet.add(s);
        return false;
    }
    
    public boolean canWin(String s) {
        Set<String> winSet = new HashSet<String>();
        Set<String> loseSet = new HashSet<String>();
        return iWin(winSet, loseSet, s);
    }

    public static void main(String[] args){
        Solution sol;
        String s;

        s = "++++";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("canWin: " + sol.canWin(s));
    }
}
