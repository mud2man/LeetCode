/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack to track the chars in S
 * 2. Poll or replace the top if the scanned char is the successor. s.t. 'a' -> 'b', 'b' -> 'c'
 */     

import java.util.*; // Stack

public class Solution {
    public boolean isValid(String S) {
        Deque<Character> stack = new LinkedList<>();
        for(char c: S.toCharArray()){
            if(stack.isEmpty()){
                stack.add(c);
            }else{
                if(stack.peekLast() == 'a' && c == 'b'){
                    stack.pollLast();
                    stack.add(c);
                }else if(stack.peekLast() == 'b' && c == 'c'){
                    stack.pollLast();
                }else{
                    stack.add(c);
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "aabcbc";
        System.out.println("S:" + S);
        System.out.println("isValid:" + sol.isValid(S));
    }
}
