/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack to store the intermediate result
 * 2. When encounter '(', push 0 to stack
 * 3. When encounter ')', change top to 1, if top is 0. Otherwise, pop number from stack until top is 0. then push newTop * 2.
 * 4. Pop stack to empty, and return the result
 *
 * ex: S="(()(()))"
 * time[0]: stack: {0}
 * time[1]: stack: {0, 0}
 * time[2]: stack: {0, 1}
 * time[3]: stack: {0, 1, 0}
 * time[4]: stack: {0, 1, 0, 0}
 * time[5]: stack: {0, 1, 0, 1}
 * time[6]: stack: {0, 1, 2}
 * time[7]: stack: {6}
 */         

import java.util.*;

public class Solution {
        public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < S.length(); ++i){
            char c = S.charAt(i);
            if(c == '('){
                stack.push(0);
            }
            else{
                if(stack.peek() == 0){
                    stack.pop();
                    stack.push(1);
                }
                else{
                    int newTop = 0;
                    while(stack.peek() != 0){
                        newTop += stack.pop();
                    }
                    stack.pop();
                    stack.push(newTop * 2);
                }
            }
        }
        
        int score = 0;
        while(!stack.isEmpty()){
            score += stack.pop();
        }
        return score;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        String S = "(()(()))"; 
        System.out.println("S: " + S);
        System.out.println("score #: " + sol.scoreOfParentheses(S));
    }
}
