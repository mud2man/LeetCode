/* BFS: O(n)
 * 1. unWeightSum = the sum of integers of all the previous level 
 * 2. When enter the deper level, let weightSum += unWeightSum => make the sum of previous levels gain one more level weight
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
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unWeightSum = 0;
        int weightSum = 0;
        while(!nestedList.isEmpty()){
            List<NestedInteger> newNestedList = new ArrayList<NestedInteger>();
            for(NestedInteger ni: nestedList){
                if(ni.isInteger()){
                    unWeightSum += ni.getInteger();
                }else{
                    newNestedList.addAll(ni.getList());
                }
            }
            weightSum += unWeightSum;
            nestedList = newNestedList;
        }
        return weightSum;
    }
}
