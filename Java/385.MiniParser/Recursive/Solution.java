/* Recursive: O(n)
 * 1. Traverse from left to right
 * 2. If encounter '[', call helper, and add the result to "ni"
 * 3. If encounter ']', break the loop
 * 4, If encounter ',', increase index, and add the result to "ni"
 * 5, Otherwise, parse the integer, and return the ni""
 */

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
    private NestedInteger helper(String s, int[] idx){
        NestedInteger ni = new NestedInteger();
        while(idx[0] < s.length()){
            if(s.charAt(idx[0]) == ']'){
                idx[0]++;
                break;
            }else if(s.charAt(idx[0]) == '['){
                idx[0]++;
                if(s.charAt(idx[0]) == ']'){
                    idx[0]++;
                    return ni;
                }else{
                    ni.add(helper(s, idx));
                }
            }else if(s.charAt(idx[0]) == ','){
                idx[0]++;
                ni.add(helper(s, idx));
            }else{
                boolean isNegative = false;
                if(s.charAt(idx[0]) == '-'){
                    isNegative = true;
                    idx[0]++;
                }
                int num = 0;
                while(idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))){
                    num = num * 10 + (int)(s.charAt(idx[0]++) - '0');
                }
                num = isNegative? -num: num;
                ni.setInteger(num);
                return ni;
            }
        }
        return ni;
    }
    
    public NestedInteger deserialize(String s) {
        int[] idx = {0};
        return helper(s, idx);
    }
}
