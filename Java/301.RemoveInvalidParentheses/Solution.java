/* Stack + Backtrack: Time: O(Valid Parentheses#), Space: O(Valid Parentheses#)
 * 1. Traverse the string with stack to record all invalid parentheses. And it can catch the minimum number of invalid paranthess
 * 2. Use backtracker to find all valid right part of string, and left part as well
 * 3. Combine right part and left part to get the whole answers
 */

import java.util.*;

public class Solution{
    private class Parentheses{
        boolean left;
        int index;
        Parentheses(boolean l, int i){left = l; index = i;}
    }
    
    private void getLeftStrings(String s, int index, StringBuilder tracker, List<String> strings, 
                                LinkedList<Integer> dummyRightParenthesesIndexs){

        if(dummyRightParenthesesIndexs.isEmpty() || index == s.length()){
            if(dummyRightParenthesesIndexs.isEmpty()){
                strings.add(tracker.toString() + s.substring(index));
            }
            return;
        }
        
        int boundry = dummyRightParenthesesIndexs.pollFirst();
        while(index <= boundry){
            char c = s.charAt(index);
            if(c != ')'){
                tracker.append(c);
                ++index;
            }
            else{
                String oldTracker = tracker.toString();
                getLeftStrings(s, index + 1, tracker, strings, dummyRightParenthesesIndexs);
                //recover
                tracker = new StringBuilder(oldTracker);
                while(index <= boundry && s.charAt(index) == ')'){
                    tracker.append(s.charAt(index));
                    ++index;
                }
            }
        }
        dummyRightParenthesesIndexs.addFirst(boundry);
    }
 
    private void getRightStrings(String s, int index, StringBuilder tracker, List<String> strings, 
                                LinkedList<Integer> dummyLeftParenthesesIndexs){

        if(dummyLeftParenthesesIndexs.isEmpty() || index < 0){
            if(dummyLeftParenthesesIndexs.isEmpty()){
                strings.add(s.substring(0, index + 1) + tracker.toString());
            }
            return;
        }
        
        int boundry = dummyLeftParenthesesIndexs.pollLast();
        while(index >= boundry){
            char c = s.charAt(index);
            if(c != '('){
                tracker.insert(0, c);
                --index;
            }
            else{
                String oldTracker = tracker.toString();
                getRightStrings(s, index - 1, tracker, strings, dummyLeftParenthesesIndexs);
                //recover
                tracker = new StringBuilder(oldTracker);
                while(index >= boundry && s.charAt(index) == '('){
                    tracker.insert(0, s.charAt(index));
                    --index;
                }
            }
        }
        dummyLeftParenthesesIndexs.add(boundry);
    }
    
    public List<String> removeInvalidParentheses(String s) {
        LinkedList<Parentheses> stack = new LinkedList<Parentheses>();
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c == '('){
                stack.add(new Parentheses(true, i));
            }
            else if(c == ')'){
                if(!stack.isEmpty() && stack.peekLast().left == true){
                    stack.pollLast();
                }
                else{
                    stack.add(new Parentheses(false, i));
                }
            }
        }
        
        LinkedList<Integer> dummyLeftParenthesesIndexs = new LinkedList<Integer>();
        LinkedList<Integer> dummyRightParenthesesIndexs = new LinkedList<Integer>();
        for(Parentheses p: stack){
            if(p.left){
                dummyLeftParenthesesIndexs.add(p.index);
            }
            else{
                dummyRightParenthesesIndexs.add(p.index);
            }
        }
        
        String leftString = "";
        String midString = "";
        String rightString = "";
        if(!dummyLeftParenthesesIndexs.isEmpty() && !dummyRightParenthesesIndexs.isEmpty()){
            leftString = s.substring(0, dummyRightParenthesesIndexs.peekLast() + 1);
            midString = s.substring(dummyRightParenthesesIndexs.peekLast() + 1, dummyLeftParenthesesIndexs.peekFirst());
            rightString = s.substring(dummyLeftParenthesesIndexs.peekFirst(), s.length());
        }
        else if(!dummyLeftParenthesesIndexs.isEmpty() && dummyRightParenthesesIndexs.isEmpty()){
            midString = s.substring(0, dummyLeftParenthesesIndexs.peekFirst());
            rightString = s.substring(dummyLeftParenthesesIndexs.peekFirst(), s.length());
        }   
        else if(dummyLeftParenthesesIndexs.isEmpty() && !dummyRightParenthesesIndexs.isEmpty()){
            leftString = s.substring(0, dummyRightParenthesesIndexs.peekLast() + 1);
            midString = s.substring(dummyRightParenthesesIndexs.peekLast() + 1, s.length());
        }
        else{
            List<String> answers = new ArrayList<String>();
            answers.add(s);
            return answers;
        }

        int offset = leftString.length() + midString.length();
        for(int i = 0; i < dummyLeftParenthesesIndexs.size(); ++i){
            dummyLeftParenthesesIndexs.set(i, dummyLeftParenthesesIndexs.get(i) - offset);
        }
        
        List<String> leftStrings = new ArrayList<String>();
        getLeftStrings(leftString, 0, new StringBuilder(""), leftStrings, dummyRightParenthesesIndexs);
        List<String> rightStrings = new ArrayList<String>();
        getRightStrings(rightString, rightString.length() - 1, new StringBuilder(""), rightStrings, dummyLeftParenthesesIndexs);
        
        List<String> answers = new ArrayList<String>();
        for(String left: leftStrings){
            for(String right: rightStrings){
                String answer = left + midString + right;
                answers.add(answer);
            }
        }
        
        if(answers.isEmpty()){
            answers.add("");
        }
        
        return answers;
    }

    public static void main(String[] args){
        String s = "(a)())()";
        Solution sol = new Solution();
        
        System.out.println("s:" + s);
        System.out.println("answer:" + sol.removeInvalidParentheses(s));
    }
}
