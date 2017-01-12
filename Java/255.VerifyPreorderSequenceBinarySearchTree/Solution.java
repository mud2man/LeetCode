/* Stack: O(n), but optimal solution is on leetcode while use only O(1) space
 * 1. If preorder[i + 1] > preorder[i], which always valid
 * 2. Pop stack untio stack.top > preorder[i], and update the lower bound under the case
 * 3. If preorder[i + 1] > preorder[i], check if preorder[i] > lower bound
 * ex: preorder = [4, 2, 1, 5, 3]
 * 
 * time[0]: lb = -max, stack = [4]
 * time[1]: lb = -max, stack = [4, 2]
 * time[2]: lb = -max, stack = [4, 2, 1]
 * time[3]: lb = 4, stack = [5]
 * time[4]: lb = 4, stack = [5] => preorder[4]=3 < lb=4, return false
 */

import java.util.*; // Stack


public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack();
        int i;
        int lb;
        stack = new Stack<Integer>();
        
        if(preorder.length == 0){
            return true;
        }
        
        lb = Integer.MIN_VALUE;
        stack.push(preorder[0]);
        for(i = 1; i < preorder.length; ++i){
            if(preorder[i] < preorder[i - 1]){
                if(preorder[i] > lb){
                    stack.push(preorder[i]);
                }
                else{
                    return false;
                }
            }
            else{
                while((!stack.empty()) && (stack.peek() < preorder[i])){
                    lb = Math.max(lb, stack.peek());
                    stack.pop();
                }
                stack.push(preorder[i]);
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        Solution sol;
        boolean isValid;
        int[] preorder = {4, 2, 1, 5, 3};

        sol = new Solution();

        System.out.println("preorder: " + Arrays.toString(preorder));

        isValid = sol.verifyPreorder(preorder);
        
        System.out.println("isValid: " + isValid);
	}
}
