/* Dynamic programming:
 * 1. 0th row is string, 0th column is pattern
 * 2. dp[y][x] = the list of char to string mapping, contains the x-th character of string, and y-th character of pattern
 * 3. dp[y][x] = union{dp[y - 1][z - 1]}, where y <= z <= x
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    private class CharToStrings{
        List<String[]> charToStrings;
        CharToStrings(){ charToStrings = new ArrayList<String[]>();}
    }
    
    private boolean isExist(String[] charToStrings, String str){
        for(String usedStr: charToStrings){
            if(usedStr.equals(str)){
                return true;
            }
        }
        return false;
    }
    
    public boolean wordPatternMatch(String pattern, String str) {
        CharToStrings[][] dp = new CharToStrings[pattern.length()][str.length()];
    
        if(str.length() == 0 && pattern.length() == 0){
            return true;
        }
        else if(pattern.length() == 0){
            return false;
        }else if(str.length() < pattern.length()){
            return false;
        }
        
        // Init the first row
        for(int x = 0; x < str.length(); ++x){
            CharToStrings charToStrings = new CharToStrings();
            String[] strings = new String[26];
            for(int i = 0; i < 26; ++i){
                strings[i] = new String("");
            }
            String lastSubString = str.substring(0, x + 1);
            strings[pattern.charAt(0) - 'a'] = lastSubString;
            charToStrings.charToStrings.add(strings);
            dp[0][x] = charToStrings;
        }
            
        for(int y = 1; y < pattern.length(); ++y){
            int end = y;
            char c = pattern.charAt(y);
            for(int x = y; x < str.length(); ++x){
                CharToStrings currCharToStrings = new CharToStrings();
                for(int z = x; z >= end; --z){
                    String lastSubString = str.substring(z, x + 1);
                    CharToStrings prevCharToStrings = dp[y - 1][z - 1];
                    for(String[] charToString: prevCharToStrings.charToStrings){
                        if(charToString[c - 'a'].equals(lastSubString)){
                            currCharToStrings.charToStrings.add(charToString);
                        }
                        else if(charToString[c - 'a'].equals("")){
                            if(!isExist(charToString, lastSubString)){
                                String[] newCharToString = charToString.clone();
                                newCharToString[c - 'a'] = lastSubString;
                                currCharToStrings.charToStrings.add(newCharToString);
                            }
                        }
                    }
                }
                dp[y][x] = currCharToStrings;
            }
        }
        
        if(!dp[pattern.length() - 1][str.length() - 1].charToStrings.isEmpty()){
            return true;
        }
        else{
            return false;
        }
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
