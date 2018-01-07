/* Time:O(n^2*k) Space:O(1), where n is the number string, k is the length of string
 * 1. If the the longest uncommon subsequence comes from string S, then S must be the lonnger uncommon subsequence
 * 2. Therefore, we just need to find the string S among the input strs, s.t. S is not subsequence to others
 */

import java.util.*;

public class Solution{
    private boolean isSubsequence(String source, String target){
        if(target.length() > source.length()){
            return false;
        }
        else{
            int i = 0;
            int j = 0;
            while(i < source.length() && j < target.length()){
                while(i < source.length() && source.charAt(i) != target.charAt(j)){
                    ++i;
                }
                
                if(i < source.length()){
                    ++i;
                    ++j; 
                }
            }
            return (j == target.length());
        }
    }
        
    public int findLUSlength(String[] strs) {
        int maxLength = -1;
        
        for(int i = 0; i < strs.length; ++i){
            String target = strs[i];
            int length = target.length();
            for(int j = 0; j < strs.length; ++j){
                if(i != j && isSubsequence(strs[j], target)){
                    length = -1;
                    break;
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        
        return maxLength;
    }

    public static void main(String[] args){
        Solution sol;
        String[] strs = {"aba", "cdc", "eae"};

        System.out.println("strs: " + Arrays.toString(strs));
        sol = new Solution();    
        System.out.println("length: " + sol.findLUSlength(strs));
    }
}
