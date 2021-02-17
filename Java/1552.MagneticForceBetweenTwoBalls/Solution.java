/* Binary search: Time:O(m * logn), Space:O(1)
 * 1. Fix the distance and see if we can put m balls in one round
 * 2. Binary search the longest distance between 1 and (position[position.length - 1] - position[0])
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private boolean isFit(int[] position, int dis, int m) {
        m--;
        int prev = position[0];
        for(int i = 1; i < position.length && m > 0; i++){
            if(position[i] - prev >= dis){
                m--;
                prev = position[i];
            }
        }
        return (m == 0);
    }
    
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int lb = 1;
        int hb = position[position.length - 1] - position[0];
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(isFit(position, mid, m)){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        return hb;
    }
  
    public static void main(String[] args){
        int[] position = {1, 2, 3, 4, 7};
        int m = 3;
        Solution sol = new Solution();
        System.out.println("position:"  + Arrays.toString(position) + ", m:" + m);
        System.out.println("max distance:" + sol.maxDistance(position, m));
    }
}
