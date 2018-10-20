/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack, and a variable "min" to denote a lower bound
 * 2. If current number "num" is smaller than top, means it still in left subtree. Push it
 * 2. If current number "num" is bigger than top, means we are in right subtree. Pop stack and update "min" with max(min, stack.pollLast())
 * 3. Also check if "num" is bigger than "min"
 *
 * ex: preorder = [4, 2, 1, 5, 3]
 * time[0]: lb = -max, stack = [4]
 * time[1]: lb = -max, stack = [4, 2]
 * time[2]: lb = -max, stack = [4, 2, 1]
 * time[3]: lb = 4, stack = [5]
 * time[4]: lb = 4, stack = [5] => preorder[4]=3 < lb = 4, return false
 */

import java.util.*; // Stack


public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new LinkedList<>();
        int min = Integer.MIN_VALUE;
        for(int num: preorder){
            if(stack.isEmpty()){
                stack.add(num);
            }
            else{
                if(min > num){
                    return false;
                }
                while(!stack.isEmpty() && stack.peekLast() <= num){
                    if(stack.peekLast() == num){
                        return false;
                    }
                    else{
                        min = Math.max(min, stack.pollLast());
                    }
                }
                stack.add(num);
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
