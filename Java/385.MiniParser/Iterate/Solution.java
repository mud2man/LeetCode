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
        
        Deque<NestedInteger> stack = new LinkedList<>();
        NestedInteger answer = new NestedInteger();
        stack.add(answer);
        int ptr = 1;
        while(ptr < s.length()){
            switch (s.charAt(ptr)){
                case '[':
                    NestedInteger newTop = new NestedInteger();
                    stack.peekLast().add(newTop);
                    stack.add(newTop);
                    ptr++;
                    break;
                case ']':
                    stack.pollLast();
                    ptr++;
                    break;
                case ',':
                    ptr++;
                    break;
                default:
                    boolean isNegative = false;
                    if(s.charAt(ptr) == '-'){
                        isNegative = true;
                        ptr++;
                    }
                    int num = 0;
                    while(ptr < s.length() && Character.isDigit(s.charAt(ptr))){
                        num = num * 10 + (int)(s.charAt(ptr++) - '0');
                    }
                    num = isNegative? -num: num;
                    NestedInteger ni = new NestedInteger(num);
                    stack.peekLast().add(ni);
            }
        }
        return answer;
    }
}
