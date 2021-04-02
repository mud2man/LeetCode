/* Stack: Time:O(n), Space:O(n)
 * 1. Chaange '(' to '{', '))' to '}' and ')' to '}' with insertCount++
 * 2. Remove all pairs 
 * 3. Scan remainStack, and insertCount + 2 if hit '{'. Otherwise, insertCount++
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int minInsertions(String s) {
        int insertCount = 0;
        Deque<Character> stack = new LinkedList<>();
        char top =(s.charAt(0) == '(')? '{': s.charAt(0);
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == '('){
                if(top == ')'){
                    insertCount++;
                    top = '}';
                }
                stack.add(top);
                top = '{';
            }else{
                if(top == ')'){
                    top = '}';
                }else{
                    stack.add(top);
                    top = ')';
                }
            }
        }
        if(top == ')'){
            insertCount++;
            top = '}';
        }
        stack.add(top);
        
        Deque<Character> remainStack = new LinkedList<>();
        while(!stack.isEmpty()){
            char front = stack.pollFirst();
            if(!remainStack.isEmpty() && remainStack.peekLast() == '{' && front == '}'){
                remainStack.pollLast();
            }else{
                remainStack.add(front);
            }
        }
        for(char c: remainStack){
            insertCount +=(c == '{')? 2: 1;
        }
        return insertCount;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "(()))";
        System.out.println("s:" + s);
        System.out.println("minimum insertion:" + sol.minInsertions(s));
    }
}
