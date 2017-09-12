/* Recursive: O(n)
 * 1. Traverse from left to right
 * 2. If encounter '[', new an NestedInteger and push it onto stack
 * 3. If encounter ']', pop stack
 * 4, If encounter ',', just increase ptr
 * 5, Otherwise, parse the integer, and add to the toppest NestedInteger
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public NestedInteger deserialize(String s) {
        if(s.charAt(0) != '['){
            return new NestedInteger(Integer.parseInt(s)); 
        }
        
        Stack<NestedInteger> stack = new Stack<NestedInteger>();
        NestedInteger answer = new NestedInteger();
        stack.push(answer);
        
        int ptr = 1;
        while(ptr < s.length()){
            switch (s.charAt(ptr)){
                case '[':
                    NestedInteger top = stack.peek();
                    NestedInteger newTop = new NestedInteger();
                    top.add(newTop);
                    stack.push(newTop);
                    ptr++;
                    break;
                case ']':
                    stack.pop();
                    ptr++;
                    break;
                case ',':
                    ptr++;
                    break;
                default:
                    int commaPtr = (s.indexOf(',', ptr) == -1)? Integer.MAX_VALUE: s.indexOf(',', ptr);
                    int bracePtr = (s.indexOf(']', ptr) == -1)? Integer.MAX_VALUE: s.indexOf(']', ptr);
                    int nextPtr = Math.min(commaPtr, bracePtr);
                    int integer = Integer.parseInt(s.substring(ptr, nextPtr));
                    NestedInteger ni = new NestedInteger(integer);
                    stack.peek().add(ni);
                    ptr = nextPtr;
            }
        }
        
        return answer;
    }
}
