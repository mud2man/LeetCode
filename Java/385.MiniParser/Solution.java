/* Recursive: O(n)
 * 1. Traverse from left to right
 * 2. If encounter '[', call helper()
 * 3. If encounter ']', return
 * 4, If encounter '-', set isNegative to 1
 * 5, If encounter ',', translate numStr and add it to current "ni" object 
 * 6, Otherwise, update numStr
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
    private class Ret{
        int nextIdx;
        NestedInteger ni;
        Ret(int i, NestedInteger n){nextIdx = i; ni = n;}
    }
    
    public NestedInteger string2Ni(boolean isNegative, StringBuilder numStr){
        int num;
        NestedInteger ni;
        
        num = (isNegative)? 0 - Integer.parseInt(numStr.toString()) : Integer.parseInt(numStr.toString());
        ni = new NestedInteger(num);
        numStr.setLength(0);
        return ni;
    }
    
    public Ret helper(String s, int startIdx){
        NestedInteger niList;
        NestedInteger preNi;
        StringBuilder numStr;
        boolean isNegative;
        int i;
        char c;
        Ret ret;

        isNegative = false;
        numStr = new StringBuilder("");
        preNi = null;
        niList = new NestedInteger();
        
        for(i = startIdx; i < s.length(); ++i){
            c = s.charAt(i);
            if(c == '['){
                ret = helper(s, i + 1);
                i =  ret.nextIdx;
                preNi = ret.ni;
            }
            else if(c == '-'){
                isNegative = true;
            }
            else if(c == ','){
                if(numStr.length() > 0){
                    preNi = string2Ni(isNegative, numStr);
                }
                niList.add(preNi);
                isNegative = false;
            }
            else if(c == ']'){
                if(numStr.length() > 0){
                    preNi = string2Ni(isNegative, numStr);
                }
                if(preNi != null){
                    niList.add(preNi);
                }
                return new Ret(i, niList);
            }
            else{
                numStr.append(c);
            }
        }
        return new Ret(i + 1, string2Ni(isNegative, numStr));
    }
    
    public NestedInteger deserialize(String s) {
        if(s.charAt(0) == '[')
            return helper(s, 1).ni;
        else
            return helper(s, 0).ni;
    }
}
