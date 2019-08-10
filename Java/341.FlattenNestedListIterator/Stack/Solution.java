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
    Integer next = null;
    Deque<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.addAll(nestedList);
        next();
    }

    @Override
    public Integer next() {
        Integer currNext = next;
        while(!stack.isEmpty() && !stack.peekFirst().isInteger()){
            List<NestedInteger> list = stack.pollFirst().getList();
            for(int i = list.size() - 1; i >= 0; --i){
                stack.addFirst(list.get(i));
            }
        }
        next = (!stack.isEmpty())? stack.pollFirst().getInteger(): null;
        return currNext;
    }

    @Override
    public boolean hasNext() {
        return (next != null);
    }
}
