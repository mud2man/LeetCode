/* Binary Search: Time:O(n*log(w)), Space:O(1), where w = sum(weights[0] + weights[1] + ... weights[n - 1])
 * 1. Binary seach min capacity between l and r
 * 2. Move l or r by the validation method "canShip"
 */

import java.util.*;

public class Solution{
    private boolean canShip(int[] weights, int D, int capacity){
        int days = 0;
        int currentShipWeight = 0;
        for(int weight: weights){
            currentShipWeight += weight;
            if(currentShipWeight > capacity){
                days++;
                currentShipWeight = weight; 
            }
        }
        days++;
        return (days <= D);
    }
    
    public int shipWithinDays(int[] weights, int D) {
        int l = 0;
        int r = 0;
        for(int weight: weights){
            l = Math.max(l, weight);
            r += weight;
        }
        
        while(l <= r){
            int mid = (l + r) / 2;
            if(canShip(weights, D, mid)){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }
	
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        System.out.println("weights:" + Arrays.toString(weights));
        System.out.println("D:" + D);
        System.out.println("least weight capacity:" + sol.shipWithinDays(weights, D));
	}
}
