/* DFS: O(n)
 * 1. Have a list "depths" to store the sum of integers on its depth
 * 2. Use traverse to caculate "depths", and accumulate all numbers in "depth" with given depth as weight
 */

import java.util.*; 
interface NestedInteger {
    // Constructor initializes an empty nested list.
    public void  NestedInteger();

    // Constructor initializes a single integer.
    public void NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class Solution {
    private void traverse(int depth, List<Integer> depths, List<NestedInteger> nestedList){
        if(depths.size() == depth){
            depths.add(0);
        }
        
        for(NestedInteger ni: nestedList){
            if(ni.isInteger()){
                depths.set(depth, depths.get(depth) + ni.getInteger()); 
            }else{
                traverse(depth + 1, depths, ni.getList());
            }
        }
    }
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> depths = new ArrayList<>();
        traverse(0, depths, nestedList);
        int depth = depths.size();
        int sum = 0;
        for(int num: depths){
            sum += num * (depth--);
        }
        return sum;
    }
}
