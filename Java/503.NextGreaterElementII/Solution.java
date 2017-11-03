/* Stack: Time:O(n) Space:O(n)
 * 1. Have a stack to keep the decreasing order of index-value pair
 * 2. Traverse the input array twice by index from 0 to 2*nums.length
 * 3. Check if the value on the top is smaller than current value nums[i]
 * 4. If so, pop the top and fill in the number by greaterElements[top[0]] = num
 * 5. Last, pop all the elements and fill -1 if the value of top is equal to max
 */

import java.util.*;

public class Solution{
    public int[] nextGreaterElements(int[] nums) {
        Stack<int[]> stack = new Stack<int[]>();
        int[] greaterElements = new int[nums.length];
        
        int length = nums.length;
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < 2 * length; ++i){
            int index = i % length;
            int num = nums[index];
            maxValue = Math.max(maxValue, num);
            if(stack.isEmpty()){
                stack.push(new int[]{index, num});
            }
            else{
                while(!stack.isEmpty()){
                    if(stack.peek()[1] < num){
                        int[] top = stack.pop();
                        greaterElements[top[0]] = num;
                    }
                    else{
                        break;
                    }
                }
                stack.push(new int[]{index, num});
            }
        }
        
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            if(maxValue == top[1]){
                greaterElements[top[0]] = -1;
            }
        }
        
        return greaterElements;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 1};
        Solution sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("next greater numbers " + Arrays.toString(sol.nextGreaterElements(nums)));
    }
}
