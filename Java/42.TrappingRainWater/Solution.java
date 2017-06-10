/* : stack: O(n)
 * 1. If the next height is lower than previous one, push it into stack
 * 2. If the next height is higher than previous one, accumulate volume and pop stack
 * 3. In the latter case, if stack.size > 1 do accumulating, oterwise replace the current top 
 * 
 * ex: height[]={0,1,0,2,1,0,1,3}, vol = 0, stack ={}
 * idx = 1, vol = 0, stack = {(1, 1)}
 * idx = 2, vol = 0, stack = {(1, 1), (0, 2)}
 * idx = 3, vol = 1, stack = {(2, 3)}
 * idx = 4, vol = 1, stack = {(2, 3), (1, 4)}
 * idx = 5, vol = 1, stack = {(2, 3), (1, 4), (0. 5)}
 * idx = 6, vol = 2, stack = {(2, 3), (1, 6)}
 * idx = 6, vol = 5, stack = {(2, 3)}
 * idx = 6, vol = 5, stack = {(3, 6)}
 *
 */

import java.util.*;

public class Solution{
    private class HeightPos{
        int height;
        int pos;
        HeightPos(int h, int p){height = h; pos = p;}
    }
    
    public int trap(int[] height) {
        LinkedList<HeightPos> stack;
        int vol, pos, len, wth;
        
        stack = new LinkedList<HeightPos>();
        
        for(pos = 0; pos < height.length && height[pos] == 0; pos++);
        
        if(pos == height.length){
            return 0;
        }
        
        vol = 0;
        stack = new LinkedList<HeightPos>();
        stack.add(new HeightPos(height[pos], pos));
        for(pos = pos + 1; pos < height.length; pos++){
            if(stack.getLast().height > height[pos]){
                stack.add(new HeightPos(height[pos], pos));
            }
            else if(stack.getLast().height < height[pos]){
                if(stack.size() > 1){
                    if(stack.get(stack.size() - 2).height > height[pos]){
                        len = pos - stack.get(stack.size() - 2).pos - 1;
                        wth = height[pos] - stack.get(stack.size() - 1).height;
                        vol = vol + len * wth;
                        stack.getLast().height = height[pos];
                        stack.getLast().pos = pos;
                    }
                    else{
                        while(stack.size() > 1 && stack.getLast().height < height[pos]){
                            len = pos - stack.get(stack.size() - 2).pos - 1;
                            wth = Math.min(stack.get(stack.size() - 2).height, height[pos]) - stack.getLast().height;
                            vol = vol + len * wth;
                            stack.pollLast();
                        }
                        
                        if(stack.getLast().height < height[pos]){
                            stack.getLast().height = height[pos];
                            stack.getLast().pos = pos;
                        }
                        else{
                            stack.add(new HeightPos(height[pos], pos));
                        }
                    }
                }
                else{
                    stack.getLast().height = height[pos];
                    stack.getLast().pos = pos;
                }
            }
            else{
                stack.getLast().pos = pos;
            }
        }
        return vol;
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
