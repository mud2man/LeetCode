/* Time:O(n), Space:O(1). LeetCodes has one shot solution
 * 1. The string must be composed of {unpaired ')'} + {')' paired '('} + {unpaired '('}
 * 2. Check if ')' valid by traversing from left, if count < 0, we can use startCount to shift the error
 * 3. Check if '(' valid by traversing from right, if count < 0, we can use startCount to shift the error
 */         

import java.util.*;

public class Solution {
    public boolean checkValidString(String s) {
        int starCount = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            switch (c){
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                case '*':
                    starCount++;
                    break;
            }
            if(count < 0){
                starCount--;
                count++;
                if(starCount < 0){
                    return false;
                }
            }
        }
        
        starCount = 0;
        count = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            char c = s.charAt(i);
            switch (c){
                case '(':
                    count--;
                    break;
                case ')':
                    count++;
                    break;
                case '*':
                    starCount++;
                    break;
            }
            if(count < 0){
                starCount--;
                count++;
                if(starCount < 0){
                    return false;
                }
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "(*))";
        System.out.println("s: " + s);
        System.out.println("is valid ?: " + sol.checkValidString(s));
    }
}
