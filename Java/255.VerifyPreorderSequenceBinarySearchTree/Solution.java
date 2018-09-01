/* Stack: O(n) complextity, O(1) space
 * 1. If preorder[i + 1] > preorder[i], which is always valid
 * 2. Abusing the given array for the stack
 * 3. Pop stack until stack.top > preorder[i], and update the lower bound under the case
 * 4. Because in preorder, the left subtree tree and root (the nodes being visited earilier) is smaller than the nodes in right tree
 * 5. If preorder[i] > preorder[i + 1], check if preorder[i] > lower bound
 * ex: preorder = [4, 2, 1, 5, 3]
 * 
 * time[0]: lb = -max, stack = [4]
 * time[1]: lb = -max, stack = [4, 2]
 * time[2]: lb = -max, stack = [4, 2, 1]
 * time[3]: lb = 4, stack = [5]
 * time[4]: lb = 4, stack = [5] => preorder[4]=3 < lb = 4, return false
 */

import java.util.*; // Stack


public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int stackTopIdx = - 1;
        int popMax = Integer.MIN_VALUE;
        
        for(int num: preorder){
            // pop stack until stack.top > preorder[i] or reach empty stack
            while(stackTopIdx >= 0 && preorder[stackTopIdx] < num){
                popMax = Math.max(popMax, preorder[stackTopIdx--]);
            }
            
            // because preorder visit the right part later, the current value must be bigger than that of popped value  
            if(popMax < num){
                preorder[++stackTopIdx] = num;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        boolean isValid;
        int[] preorder = {4, 2, 1, 5, 3};

        sol = new Solution();

        System.out.println("preorder: " + Arrays.toString(preorder));
        isValid = sol.verifyPreorder(preorder);
        System.out.println("isValid: " + isValid);
    }
}
