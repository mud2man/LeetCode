/* Dynamic Programming: Time:O(n), Space:O(1)
 * 1. dp[i] is the maximum flowers can be planted considering [0, 1, 2, ,,, i]
 * 2. dp[i] = max(noTakenCount, takenCount) with considering flowerbed[i] and flowerbed[i - 1] is 1
 */         

import java.util.*;

public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] dp = new int[3];
        for(int i = 0; i < flowerbed.length; ++i){
            if(flowerbed[i] == 1){
                dp[i % 3] = (i >= 2)? dp[(i - 2) % 3]: 0;
            }
            else{
                if(i > 0 && flowerbed[i - 1] == 1){
                    dp[i % 3] = dp[(i - 1) % 3];
                }
                else{
                    int noTakenCount = (i >= 1)? dp[(i - 1) % 3]: 0;
                    int takenCount = (i >= 2)? dp[(i - 2) % 3] + 1: 1;
                    dp[i % 3] = Math.max(noTakenCount, takenCount);
                }
            }
        }
        
        return (dp[(flowerbed.length - 1) % 3] >= n);
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;
        sol = new Solution();
        System.out.println("flowerbed: " + Arrays.toString(flowerbed));
        System.out.println("n: " + n);
        System.out.println("can place ?: " + sol.canPlaceFlowers(flowerbed, n));
    }
}
