/* O(n)
 * 1. Reverse the whole string
 * 2. Reverse every word in the string
 */

import java.util.*;

public class Solution{
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        
        int currIndex = 0;
        StringBuilder reverseSb = new StringBuilder("");
        while(currIndex >= 0 && currIndex < sb.length()){
            int nexIndex = sb.indexOf(" ", currIndex);
            if(nexIndex == -1){
                break;
            }
            
            //space found
            if(nexIndex == currIndex){
                currIndex++;
                if(reverseSb.length() > 0 && reverseSb.charAt(reverseSb.length() - 1) != ' '){
                    reverseSb.append(' ');
                }
                continue;
            }
            
            //reverse word 
            for(int i = nexIndex - 1; i >= currIndex; --i){
                reverseSb.append(sb.charAt(i));
            }
            currIndex = nexIndex;
        }
        
        if(currIndex < sb.length()){
            for(int i = sb.length() - 1; i >= currIndex; --i){
                reverseSb.append(sb.charAt(i));
            }
        }

        //clean leading spaces
        if(reverseSb.length() > 0 && reverseSb.charAt(0) == ' '){
            reverseSb.deleteCharAt(0);
        }
        
        //clean tailing spaces
        if(reverseSb.length() > 0 && reverseSb.charAt(reverseSb.length() - 1) == ' '){
            reverseSb.deleteCharAt(reverseSb.length() - 1);
        }
        
        return reverseSb.toString();
    }
    
    public static void main(String[] args){
        Solution sol;
        String s;

        s = "the sky is blue";
        sol = new Solution();

        System.out.println("before reverse: " + s);
        System.out.println("after reverse: " + sol.reverseWords(s));
    }
}
