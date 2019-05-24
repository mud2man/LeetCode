/* Iterator and Stack: O(n)
 * 1. Use stack to store the nestedList's iterator
 * 2. For hasNext(), check if next is null
 * 3. For next(), pop and push satck to get next
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
    Integer next;
    Deque<Iterator<NestedInteger>> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        NestedInteger nestedInteger = null;
        if(!nestedList.isEmpty()){
            stack.add(nestedList.iterator());
            nestedInteger = stack.peekLast().next();
        }
        while(nestedInteger != null && !nestedInteger.isInteger() && !nestedInteger.getList().isEmpty()){
            stack.add(nestedInteger.getList().iterator());
            nestedInteger = stack.peekLast().next();
        }
        next = (nestedInteger != null && nestedInteger.isInteger())? nestedInteger.getInteger(): next();
    }

    @Override
    public Integer next() {
        Integer nextNext = null;
        while(!stack.isEmpty() && nextNext == null){
            Iterator<NestedInteger> top = stack.peekLast();
            if(!top.hasNext()){
                stack.pollLast();
            }else{
                NestedInteger nestedInteger = top.next();
                while(!nestedInteger.isInteger() && !nestedInteger.getList().isEmpty()){
                    stack.add(nestedInteger.getList().iterator());
                    nestedInteger = stack.peekLast().next();
                }
                nextNext = nestedInteger.isInteger()? nestedInteger.getInteger(): null;
            }
        }
        Integer ret = next;
        next = nextNext;
        return (ret == null)? nextNext: ret;
    }

    @Override
    public boolean hasNext() {
        return (next != null);
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
