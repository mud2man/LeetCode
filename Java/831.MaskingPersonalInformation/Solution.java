/* Time:O(n), SpaceO(n)
 * 1. Check if '@' exist, and traverse and mark the string S
 */

import java.util.*;

//Definition for singly-linked list.
public class Solution{
    public String maskPII(String S) {
        StringBuilder masked = new StringBuilder("");
        int atIdx = S.indexOf('@');
        if(atIdx != -1){
            String first = S.substring(0, atIdx);
            masked.append(Character.toLowerCase(first.charAt(0)));
            masked.append("*****");
            masked.append(Character.toLowerCase(first.charAt(first.length() - 1)));
            for(int i = atIdx; i < S.length(); ++i){
                char c = Character.isLetter(S.charAt(i))? Character.toLowerCase(S.charAt(i)): S.charAt(i); 
                masked.append(c);
            }
        }
        else{
            LinkedList<Character> number = new LinkedList<Character>();
            for(int i = 0; i < S.length(); ++i){
                char c = S.charAt(i);
                if(Character.isDigit(c)){
                    number.add(c);
                }
            }
                
            for(int i = 0; i < 4; ++i){
                masked.insert(0, number.pollLast());
            }
            masked.insert(0, "***-");
            for(int i = 0; i < 3; ++i){
                number.pollLast();
            }
            masked.insert(0, "***-");
            for(int i = 0; i < 3; ++i){
                number.pollLast();
            }
                
            if(!number.isEmpty()){
                masked.insert(0, '-');
                while(!number.isEmpty()){
                    number.pollLast();
                    masked.insert(0, '*');
                }
                masked.insert(0, '+');
            }
        }
        return masked.toString();
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "LeetCode@LeetCode.com";
        System.out.println("S: " + S);
        System.out.println("after marked: " + sol.maskPII(S));
    }
}
