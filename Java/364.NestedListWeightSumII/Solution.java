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
    public void record(List<NestedInteger> nestedList, List<Integer> table, int depth){
        int sum;
        int i;
        
        sum = 0;
        for(NestedInteger Ni: nestedList){
            if(Ni.isInteger()){
                sum = sum + Ni.getInteger();
            }
            else{
                record(Ni.getList(), table, depth + 1);  
            }
        }
        
        if(depth > (table.size() - 1)){
            for(i = table.size(); i <= depth; ++i){
                table.add(0);
            }
        }
        
        table.set(depth, sum + table.get(depth));
    }
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int i;
        List<Integer> table;
        int depthSum;
        
        table = new ArrayList<Integer>();
        
        record(nestedList, table, 0);
        
        depthSum = 0;
        for(i = 0; i < table.size(); ++i){
            depthSum = depthSum + table.get(i) * (table.size() - i);
        }
        
        return depthSum;
    }
}
