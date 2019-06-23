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
    Deque<Iterator<NestedInteger>> stack;
    Integer next;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.add(nestedList.iterator());
        next();
    }

    @Override
    public Integer next() {
        Integer prevNext = next;
        Integer nextNext = null;
        while(!stack.isEmpty() && nextNext == null){
            //push the iterator of List<NestedInteger>
            while(stack.peekLast().hasNext()){
                NestedInteger ni = stack.peekLast().next();
                if(ni.isInteger()){
                    nextNext = ni.getInteger();
                    break;
                }else{
                    stack.add(ni.getList().iterator());
                }
            }
            //pop the burn out iterator 
            while(!stack.isEmpty() && !stack.peekLast().hasNext()){
                stack.pollLast();
            }
        }
        next = nextNext;
        return prevNext;
    }

    @Override
    public boolean hasNext() {
        return(next != null);
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
