/* DFS: Time: O(Valid Parentheses#), Space: O(Valid Parentheses#)
 * 1. Get the boundry of left part from left end, which contains more ')', e.g., (a)())()(() => (a)())
 * 2. Get the boundry of right part from right end, which contains more '(', e.g., (a)())()(() => (()
 * 3. Get the middle part which has same '(' # and ')' #
 * 4. Use backtrack to find all valid parentheses of left with minimum removing of ')'
 * 5. Use backtrack to find all valid parentheses of right with minimum removing of '('
 * 6. Concatenate all left, middle, and right and add them into parentheses
 */

import java.util.*;

public class Solution{
    private void backtrack(String path, List<String> ret, char[] pair, String s, int remain, int curr, int score){
        if(remain == 0){
            ret.add(path + s.substring(curr));
            return;
        }
        if(score > 0 || curr == s.length()){
            return;
        }
        
        while(curr < s.length() && s.charAt(curr) != pair[1]){
            score += (s.charAt(curr) == pair[0])? -1: 0;
            path += String.valueOf(s.charAt(curr++));
        }
        int count = 0;
        while(curr < s.length() && s.charAt(curr) == pair[1]){
            count++;
            curr++;
        }
        for(int i = 0; i <= count; ++i){
            backtrack(path, ret, pair, s, remain - count + i, curr, score);
            path += String.valueOf(pair[1]);
            score++;
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        int[] ptr = {-1, s.length()};
        int[] count = new int[2];
        int[] max = new int[2];
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            count[0] = (c == '(')? count[0] - 1: (c == ')')? count[0] + 1: count[0];
            if(count[0] > max[0]){
                max[0] = count[0];
                ptr[0] = i;
            }
            c = s.charAt(s.length() - 1 - i);
            count[1] = (c == ')')? count[1] - 1: (c == '(')? count[1] + 1: count[1];
            if(count[1] > max[1]){
                max[1] = count[1];
                ptr[1] = s.length() - 1 - i;
            }
        }
        
        String left = s.substring(0, ptr[0] + 1);
        String middle = s.substring(ptr[0] + 1, ptr[1]);
        String right = s.substring(ptr[1]);
        right = new StringBuilder(right).reverse().toString();
        List<String> lefts = new ArrayList<>();
        List<String> rights = new ArrayList<>();
        backtrack("", lefts, new char[]{'(', ')'}, left, max[0], 0, 0);
        backtrack("", rights, new char[]{')', '('}, right, max[1], 0, 0);
        List<String> parentheses = new ArrayList<>();
        if(lefts.isEmpty()){
            lefts.add("");
        }
        if(rights.isEmpty()){
            rights.add("");
        }
        for(String l: lefts){
            for(String r: rights){
                StringBuilder tmp = new StringBuilder(r);
                parentheses.add(l + middle + tmp.reverse().toString());
            }
        }
        return parentheses;
    }
 
    public static void main(String[] args){
        String s = "(a)())()";
        Solution sol = new Solution();
        
        System.out.println("s:" + s);
        System.out.println("answer:" + sol.removeInvalidParentheses(s));
    }
}
