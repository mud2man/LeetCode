/* Binary search: Time:O(n * logn), Space:O(1).
 */

import java.util.*; // Stack


public class Solution {
    public int maxLength(int[] ribbons, int k) {
        long totalLength = 0;
        int maxLength = 0;
        for(int ribbon: ribbons){
            totalLength += (long)ribbon;
            maxLength = Math.max(ribbon, maxLength);
        }
        if(totalLength < (long)k){
            return 0;
        }
        
        int lb = 1;
        int hb = maxLength;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int count = 0;
            for(int ribbon: ribbons){
                count += (ribbon / mid);
            }
            if(count >= k){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        return hb;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] ribbons = {9, 7, 5};
        int k = 3;
        System.out.println("ribbons:" + Arrays.toString(ribbons) + ", k:" + k);
        System.out.println("max length:" + sol.maxLength(ribbons, k));
    }
}
