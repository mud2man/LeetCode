/* Binary Serach: Time:O(nlogm), Space:O(1), where n is piles#, m is max(piles)
 * 1. Set up lb = 1, ub = max(piles), and do binary search to find the minimum eating speed
 */

import java.util.*; // Stack

public class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int lb = 1;
        int hb = Arrays.stream(piles).max().getAsInt();
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int hours = 0;
            for(int pile: piles){
                hours += (pile - 1) / mid + 1;
            }
            if(hours <= H){
                hb = mid - 1; //slow down
            }else{
                lb = mid + 1; //speed up
            }
        }
        return lb;
    }

    public static void main(String[] args){
        int[] piles = {3, 6, 7, 11};
        int H = 8;
        System.out.println("piles:" + Arrays.toString(piles));
        System.out.println("H:" + H);
        Solution sol = new Solution();
        System.out.println("minimum eating speed:" + sol.minEatingSpeed(piles, H));
    }
}
