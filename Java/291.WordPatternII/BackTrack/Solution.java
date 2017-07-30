/* Backtrack:
 * 1. Use charToStringMap to store the char to string mapping
 * 2. Use used to store the used mapped string
 * 3. Call backtracker with the parameter patternIdx and strIdx
 * If patternIdx == pattern.length() and strIdx == str.length() return true
 * 
 * ex: pattern = "abab", str = "redblueredblue"
 * Assume patternIdx = 2, strIdx = 7 => charToStringMap = {"red", "blue", ...}, used = {"red", "blue"}
 * idx = 7: lastSubString = r => fail
 * idx = 8: lastSubString = re => fail
 * idx = 9: lastSubString = red => success, call backtracker(pattern, str, 3, 10, charToStringMap, used)
 * idx = 10: lastSubString = redb => fail
 * idx = 11: lastSubString = redbl => fail
 * idx = 12: lastSubString = redblu => fail
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public boolean backTracker(String pattern, String str, int patternIdx, int strIdx, String[] charToStringMap, Set<String> used){
        if(patternIdx == pattern.length() && strIdx == str.length()){
            return true;
        }
        
        if(patternIdx >= pattern.length() || strIdx >= str.length() || 
           (str.length() - strIdx) < (pattern.length() - patternIdx)){
            return false;
        }
        
        char patternChar = pattern.charAt(patternIdx);
        for(int idx = strIdx; idx < str.length(); ++idx){
            String lastSubString = str.substring(strIdx, idx + 1);
            if(lastSubString.equals(charToStringMap[patternChar - 'a'])){
                if(backTracker(pattern, str, patternIdx + 1, idx + 1, charToStringMap, used)){
                    return true;
                }
            }
            else if(charToStringMap[patternChar - 'a'].equals("")){
                if(!used.contains(lastSubString)){
                    //push
                    charToStringMap[patternChar - 'a'] = lastSubString;
                    used.add(lastSubString);
                    
                    if(backTracker(pattern, str, patternIdx + 1, idx + 1, charToStringMap, used)){
                        return true;
                    }
                    
                    //pop
                    charToStringMap[patternChar - 'a'] = "";
                    used.remove(lastSubString);
                }
            }
        }
        return false;
    }
    
    public boolean wordPatternMatch(String pattern, String str) {
        String[] charToStringMap = new String[26];
        for(int i = 0; i < 26; ++i){
            charToStringMap[i] = new String("");
        }
        Set<String> used = new HashSet<String>();

        return backTracker(pattern, str, 0, 0, charToStringMap, used);
    }
    public static void main(String[] args){
        String str = "redblueredblue";
        String pattern = "abab";
        Solution sol = new Solution();
        
        System.out.println("str:" + str);
        System.out.println("pattern:" + pattern);
        System.out.println("is match ?:" + sol.wordPatternMatch(pattern, str));
    }
}
