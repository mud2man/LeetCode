/* Stack: Time:O(n), Space:O(n)
 * 1. Have two stacks "stack" and "maxStack", where maxStack[i] store the max value between stack[0] and stack[i]
 * 2. When popMax, pop the stack until (top == max), and push the rest of them back to stack and maxStack
 */

import java.util.*;

public class MaxStack {
    Deque<Integer> stack;
    Deque<Integer> maxStack;
    
    /** initialize your data structure here. */
    public MaxStack() { 
        stack = new LinkedList<>();
        maxStack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.add(x);
        if(maxStack.isEmpty() || maxStack.peekLast() < x){
            maxStack.add(x);
        }
        else{
            maxStack.add(maxStack.peekLast());
        }
    }
    
    public int pop() {
        maxStack.pollLast();
        return stack.pollLast();
    }
    
    public int top() {
        return stack.peekLast();
    }
    
    public int peekMax() {
        return maxStack.peekLast();
    }
    
    public int popMax() {
        Deque<Integer> tempStack = new LinkedList<>();
        int max = peekMax();
        while(max != stack.peekLast()){
            tempStack.add(stack.pollLast());
            maxStack.pollLast();
        }
        
        stack.pollLast();
        maxStack.pollLast();
        
        while(!tempStack.isEmpty()){
            int top = tempStack.pollLast();
            stack.add(top);
            if(maxStack.isEmpty() || maxStack.peekLast() < top){
                maxStack.add(top);
            }
            else{
                maxStack.add(maxStack.peekLast());
            }
        }
        return max;
    }
 
    public static void main(String[] args){
        MaxStack stack = new MaxStack();
        int val;

        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");

        val = 1;
        stack.push(val);
        System.out.println("push(" + val + ")");

        val = 5;
        stack.push(val);
        System.out.println("push(" + val + ")");

        System.out.println("top: " + stack.top());
        System.out.println("popMax: " + stack.popMax());
        System.out.println("top: " + stack.top());
        System.out.println("peekMax: " + stack.peekMax());
        System.out.println("pop: " + stack.pop());
        System.out.println("top: " + stack.top());
    }
}
