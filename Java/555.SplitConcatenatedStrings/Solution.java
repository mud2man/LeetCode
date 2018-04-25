/* Time:O(n^2) Space:O(n), where n is the number of characters
 * 1. All the strings other than the startStr must become its lexicographically biggest status
 * 2. We get the lexicographically biggest string first, and treverse the strings and set the break point and compare it with maxStr
 */

import java.util.*;

public class Solution{
    public String splitLoopedString(String[] strs) {
        for(int i = 0; i < strs.length; ++i){
            StringBuilder sb = new StringBuilder(strs[i]);
            String reverseStr = sb.reverse().toString();
            strs[i] = (strs[i].compareTo(reverseStr) > 0)? strs[i]: reverseStr;
        }
        
        String maxStr = "";
        for(int i = 0; i < strs.length; ++i){
            String fixedPart = "";
            for(int j = 1; j < strs.length; ++j){
                fixedPart += strs[(i + j) % strs.length];
            }
            
            String reverseStr = new StringBuilder(strs[i]).reverse().toString();
            for(String startStr: new String[]{strs[i], reverseStr}){
                for(int k = 0; k < startStr.length(); ++k){
                    StringBuilder currentStr = new StringBuilder(startStr.substring(k));
                    currentStr.append(fixedPart);
                    currentStr.append(startStr.substring(0, k));
                    if(currentStr.toString().compareTo(maxStr) > 0){
                        maxStr = currentStr.toString();
                    }
                }
            }
        }
        
        return maxStr;
    } 

    public static void main(String[] args){
        Solution sol;
        String[] strs = {"abc", "xyz"};
        System.out.println("strs: " + Arrays.toString(strs));
        sol = new Solution();    
        System.out.println("maximun concatenated string: " + sol.splitLoopedString(strs));
    }
}
