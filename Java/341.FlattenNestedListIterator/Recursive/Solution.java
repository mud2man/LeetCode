/* Recursive: O(n)
 * 1. Fallten the list recursively in tne constructor
 * 2. Return the i-th element in list
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
    private List<Integer> list;
    private int idx;
    
    public void flattenHelper(List<NestedInteger> nestedList){
        for(NestedInteger ni: nestedList){
            if(ni.isInteger()){
                list.add(ni.getInteger());
            }
            else{
                flattenHelper(ni.getList()); 
            }
        }
    }
    
    public NestedIterator(List<NestedInteger> nestedList) {
        idx = 0;
        list = new ArrayList<Integer>();
        flattenHelper(nestedList);    
    }

    @Override
    public Integer next() {
        return list.get(idx++);  
    }

    @Override
    public boolean hasNext() {
        return (idx < list.size());
    }
}
