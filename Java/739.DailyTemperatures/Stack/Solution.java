/* Monotonous Stack: Time:O(n), Space:O(k)
 * 1. Have a stack in decending order, with element = {temp, index}
 * 2. Traverse from rightest, and pop stack until encounter a higher temp. And then push the current {temp[i], i} to stack
 */

import java.util.*;

public class Solution{
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> stack = new LinkedList<>();
        int[] daysOfWait = new int[temperatures.length];
        for(int i = temperatures.length - 1; i >= 0; --i){
            while(!stack.isEmpty() && stack.peekLast()[0] <= temperatures[i]){
                stack.pollLast();
            }
            daysOfWait[i] = (!stack.isEmpty())? stack.peekLast()[1] - i: 0;
            stack.add(new int[]{temperatures[i], i});
        }
        return daysOfWait;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        
        System.out.println("temperatures:" + Arrays.toString(temperatures));
        System.out.println("days:" + Arrays.toString(sol.dailyTemperatures(temperatures)));
    }
}
