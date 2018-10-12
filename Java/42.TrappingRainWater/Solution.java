/* Monotonous stack: Time:O(n), Space:O(n)
 * 1. Virtual stack is {stack, top, nextTop}, where node = {height, index}, and maintain a decreasing order in terms of height
 * 2. If the next height is lower than previous one, push it into stack
 * 3. If the next height is equals to  previous one, update its index with top[1] = nextTop[1];
 * 2. If the next height is higher than previous one, accumulate volume and pop stack, and push nexTop to stack if nextTop[0] < top[0]
 * 
 * ex: height[]={0, 1, 0, 2, 1, 0, 1, 3}, water = 0, stack ={}
 * idx = 0, water = 0, stack = {(0, 0)}
 * idx = 1, water = 0, stack = {(1, 1)}
 * idx = 2, water = 0, stack = {(1, 1), (0, 2)}
 * idx = 3, water = 1, stack = {(2, 3)}
 * idx = 4, water = 1, stack = {(2, 3), (1, 4)}
 * idx = 5, water = 1, stack = {(2, 3), (1, 4), (0. 5)}
 * idx = 6, water = 2, stack = {(2, 3), (1, 6)}
 * idx = 7, water = 5, stack = {(3, 7)}
 */

import java.util.*;

public class Solution{
    public int trap(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        
        int water = 0;
        Deque<int[]> stack = new LinkedList<>();
        int[] top = new int[]{height[0], 0};
        for(int i = 1; i < height.length; ++i){
            int[] nextTop = new int[]{height[i], i};
            if(nextTop[0] < top[0]){
                stack.add(top);
                top = nextTop;
            }
            else if(nextTop[0] == top[0]){
                top[1] = nextTop[1];
            }
            else{
                while(!stack.isEmpty() && nextTop[0] > top[0]){
                    water += (nextTop[1] - stack.peekLast()[1] - 1) * (Math.min(nextTop[0], stack.peekLast()[0]) - top[0]);
                    top = stack.pollLast();
                }
                
                if(nextTop[0] < top[0]){
                    stack.add(top);
                    top = nextTop;
                }
                else if(nextTop[0] == top[0]){
                    top[1] = nextTop[1];
                }
                else{
                    top = nextTop;
                }
            }
        }
        return water; 
    }

    public static void main(String[] args){
        String s;
        Solution sol;
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3};
        int volume;
        
        sol = new Solution();

        System.out.println("height =" +Arrays.toString(height));
        volume = sol.trap(height);
        System.out.println("trapped watter = " + volume);
    }
}
