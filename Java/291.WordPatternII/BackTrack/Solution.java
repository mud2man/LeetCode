/* Backtrack: Time:O(n!), Space;O(n)
 * 1. Use "map" to store the char to string mapping
 * 2. Use "used" to store the used mapped string
 * 3. Call backtracker with the parameter patternIdx and strIdx
 * 4. If patternIdx == pattern.length() and strIdx == str.length() return true
 * 
 * ex: pattern = "abab", str = "redblueredblue"
 * Assume patternIdx = 2, strIdx = 7 => map = {"red", "blue", ...}, used = {"red", "blue"}
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
        private boolean backtrack(int pIdx, String pattern, int strIdx, String str, String[] map, Set<String> used){
        if(pIdx == pattern.length() && strIdx == str.length()){
            return true;
        }
        else if(pIdx >= pattern.length() || strIdx >= str.length()){
            return false;
        }
        else{
            char patternChar = pattern.charAt(pIdx);
            if(map[patternChar - 'a'] != null){
                if(strIdx + map[patternChar - 'a'].length() > str.length()){
                    return false;
                }
                else{
                    String strSubstring = str.substring(strIdx, strIdx + map[patternChar - 'a'].length());
                    if(map[patternChar - 'a'].equals(strSubstring)){
                        return backtrack(pIdx + 1, pattern, strIdx + map[patternChar - 'a'].length(), str, map, used);
                    }
                    else{
                        return false;
                    }
                }
            }
            else{
                for(int l = 1; l <= str.length() - strIdx; ++l){
                    String strSubstring = str.substring(strIdx, strIdx + l);
                    if(used.contains(strSubstring)){
                        continue;
                    }
                    else{
                        map[patternChar - 'a'] = strSubstring;
                        used.add(strSubstring);
                        if(backtrack(pIdx + 1, pattern, strIdx + l, str, map, used)){
                            return true;
                        }
                        used.remove(strSubstring);
                        map[patternChar - 'a'] = null;
                    }
                }
                return false;
            }
        }
    }
    
    public boolean wordPatternMatch(String pattern, String str) {
        String[] map = new String[26];
        Set<String> used = new HashSet<>();
        return backtrack(0, pattern, 0, str, map, used);
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
