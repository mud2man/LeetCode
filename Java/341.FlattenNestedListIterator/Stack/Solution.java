/* Stack: Time:O(n^2)
 * 1. Use stack to store the nestedList
 * 2. For hasNext(), check if top is an integer, otherwise, retrieve the nestedList and push then into stack
 * 3. For next(), just return top.getInteger()
 */

import java.util.*;
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new LinkedList<NestedInteger>();
        for(int i = nestedList.size() - 1; i >= 0; --i){
            stack.add(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pollLast().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            NestedInteger top = stack.peekLast();
            if(top.isInteger()){
                return true;
            }
            
            List<NestedInteger> nestedList = stack.pollLast().getList();
            for(int i = nestedList.size() - 1; i >= 0; --i){
                stack.add(nestedList.get(i));
            }
        }
        return false;
    }
}
