/* Two points: O(n)
 * 1. Set left = 0, right = height.length - 1
 * 2. If height[left] < height[right], left++ and caculate current area
 * 3. Otherwise, right-- and caculate current area
 */

import java.util.*;

public class Solution{
    public int maxArea(int[] height) {
        int left, right, maxArea, currArea;
        
        left = 0;
        right = height.length - 1;
        maxArea = 0;
        currArea = 0;
        
        while(left < right){
            currArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currArea);
            if(height[left] < height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        Solution sol;
        int[] height = {3, 2, 1, 5, 1};
        int maxArea;
        
        sol = new Solution();

        System.out.println("height =" +Arrays.toString(height));
        maxArea = sol.maxArea(height);
        System.out.println("maxArea = " + maxArea);
    }
}
