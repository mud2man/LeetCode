/* Math: O(logn)
 * 1. Find all the elements by GCD
 * 2. That is residure = bigger % smaller, and put residure into element set "elements"
 * 3. Then let bigger = smaller, smaller = residure
 * 4. Repeat step2 and step3 until residure = 0.
 * 5. Then apply dynamic programming, dp[i] = true/false if i can be got by the "elements"
 * 6. In every loop where 0 <= i <= z, dp[i + elemnet] = dp[i + element] for all element in elemnts
 * 7. However, the smallest element is actually the factor for all elements in "elements"
 * 8. So, We can return the result by determine if z can be devided by the smallest element
 *
 */          

import java.util.*; // Stack

public class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z > (x + y)){
            return false;
        }
        
        if(z == 0){
            return true;
        }
        
        int bigger = Math.max(x, y);
        int smaller = Math.min(x, y);
        int residure = (smaller != 0)? bigger % smaller: 0;
        while(residure > 0){
            bigger = smaller;
            smaller = residure;
            residure = bigger % smaller;
        }
        
        if((smaller != 0) && (z % smaller == 0)){
            return true;
        }
        else{
            return false;
        }
    }    
 
    public static void main(String[] args){
        Solution sol;
        int x = 3;
        int y = 5;
        int z = 4;
        sol = new Solution();

        System.out.println("x:" + x + ", y:" + y + " ,z:" + z);
        boolean isMeasurable = sol.canMeasureWater(x, y , z);
        System.out.println("isMeasurable: " + isMeasurable);
    }
}
