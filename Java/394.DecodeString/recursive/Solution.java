/* Recursive: O(n)
 * 1. Traverse the string, call "helper" if encounter '['
 * 2. Before calling helper, append the previous string
 * 3. After calling "helper", append the return string "numStr" times
 *
 * ex: s = ab2[---s---]bc
 * time[0]: charStr = ab, numStr = 2
 * time[1]: retS = ab[---s---][----s----]
 * time[2]: retS = ab[---s---][----s----]bc
 */

import java.util.*;

public class Solution{
    private class Ret{
        int nextIdx;
        String str;
        Ret(int n, String s){nextIdx = n; str = s;}
    }
    
    public Ret helper(String s, int currIdx){
        StringBuilder numStr = new StringBuilder("");
        StringBuilder charStr = new StringBuilder("");
        StringBuilder retStr = new StringBuilder("");
        Ret ret;
        char c;
        int i;
        
        while((currIdx < s.length()) && (s.charAt(currIdx) != ']')){
            c = s.charAt(currIdx);
            if(c == '['){
                ret = helper(s, currIdx + 1);
                retStr.append(charStr);
                for(i = 0; i < Integer.parseInt(numStr.toString()); ++i){
                    retStr.append(ret.str);
                }
                numStr.setLength(0);
                charStr.setLength(0);
                currIdx = ret.nextIdx;
            }
            else if(Character.isDigit(c)){
                numStr.append(c); 
                currIdx++;
            }
            else{
                charStr.append(c);
                currIdx++;
            }
        }
        
        if(charStr.length() > 0){
            retStr.append(charStr);
        }
        
        ret = new Ret(currIdx + 1, retStr.toString());
        return ret;
    }
    
    public String decodeString(String s) {
        String decodeStr;
        Ret ret;
        
        ret = helper(s, 0);
        
        return ret.str;
    }

    public static void main(String[] args){
        Solution sol;
        String s = "3[a]2[bc]";
        String ans;

        sol = new Solution();

        System.out.println("s: " + s);
        ans = sol.decodeString(s);
        System.out.println("ans: " + ans);
    }
}
