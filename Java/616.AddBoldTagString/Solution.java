/* Hash: Time:O(n*m*k), Space:O(n), where n = s length, m = word length, k = word number. Can use boolean array for a shorter answer
 * 1. Use boolean array to store if bold for every character
 * 2. Reconstruct the string by the boolean array
 */

import java.util.*;

public class Solution{
    public String addBoldTag(String s, String[] dict) {
        boolean[] boldArray = new boolean[s.length()];
        
        for(int i = 0; i < s.length(); ++i){
            int maxLength = 0;
            for(String word: dict){
                maxLength = s.startsWith(word, i)? Math.max(maxLength, word.length()): maxLength;
            }
            for(int j = 0; j < maxLength; ++j){
                boldArray[i + j] = true;
            }
        }
        
        System.out.println("boldArray:" + Arrays.toString(boldArray));
        StringBuilder taggedString = new StringBuilder("");
        if(boldArray[0] == true){
            taggedString.append("<b>");
        }
        taggedString.append(s.charAt(0));
        
        for(int i = 1; i < boldArray.length; ++i){
            char c = s.charAt(i);
            if(boldArray[i - 1] == false && boldArray[i] == true){
                taggedString.append("<b>");
            }
            else if(boldArray[i - 1] == true && boldArray[i] == false){
               taggedString.append("</b>"); 
            }
            taggedString.append(c);
        }
        
        if(boldArray[boldArray.length - 1] == true){
            taggedString.append("</b>");
        }
        
        return taggedString.toString();
    }

    public static void main(String[] args){
        Solution sol;
        String[] dict = {"aaa","aab","bc"};
        String s = "aaabbcc";

        sol = new Solution();    
        System.out.println("dict: " + Arrays.toString(dict));
        System.out.println("s: " + s);
        System.out.println("bold string: " + sol.addBoldTag(s, dict));
    }
}
