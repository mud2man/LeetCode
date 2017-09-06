/* stack: O(n)
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
    private NestedInteger top;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new LinkedList<NestedInteger>();

        for(int i = nestedList.size() - 1; i >= 0; --i){
            stack.add(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return top.getInteger();
    }

    @Override
    public boolean hasNext() {
        top = stack.pollLast();
        while(top != null && !top.isInteger()){
            List<NestedInteger> nestedList = top.getList();
            for(int i = nestedList.size() - 1; i >= 0; --i){
                stack.add(nestedList.get(i));
            }
            
            if(!stack.isEmpty()){
                top = stack.pollLast();
            }
            else{
                top = null;
            }
        }

        return (top != null);
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
