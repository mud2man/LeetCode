/* Recursive: O(n!)
 * 1. If any case can cuase opponent lose in the next match, the I win 
 * 2. If opponent can win under all the cases in the next matches, then I lose 
 */

import java.util.*;

public class Solution{
    public boolean canWin(String s) {
        int idx;
        String str;
        
        idx = s.indexOf("++");
        if(s == null || s.length() < 2){
            return false;
        }
        
        //System.out.println("s:" + s);
        for(idx = 0; idx < s.length(); ++idx){
            if(s.startsWith("++", idx)){
                str = s.substring(0, idx) + "--" +  s.substring(idx + 2);
                if(!canWin(str)){
                    return true;
                }
            }
        }
        return false;
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
