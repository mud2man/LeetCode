/* Monotonous Stack: Time:O(n), Space:O(k)
 * 1. Have a stack of index in decending order in terms of temperatures[idx]
 * 2. Traverse from left, and pop stack as long as temperatures[stack.peekLast()] < temperatures[i], and update daysOfWait[idx] as (i - idx)
 */

import java.util.*;

public class Solution{
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] daysOfWait = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; ++i){
            int currTemp = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peekLast()] < currTemp){
                int idx = stack.pollLast();
                daysOfWait[idx] = i - idx;
            }
            stack.add(i);
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
