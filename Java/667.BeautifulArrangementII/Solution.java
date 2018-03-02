/* Math: Time:O(n), Space:O(1)
 * 1. Fill in the first (n - k) elements with 1, 2, 3,... (n - k)
 * 2. Fill in the rest positions, n, (n - k + 1), (n - 1), (n - k + 2), ....
 * 
 * ex: n = 5, k = 3, array = {1, 2, 5, 3, 4}
 * ex: n = 5, k = 4, array = {1, 5, 2, 4, 3}
 */

import java.util.*;

public class Solution{
    public int[] constructArray(int n, int k) {
        int[] array = new int[n];
        array[0] = 1;
        
        for(int i = 1; i < (n - k); ++i){
            array[i] = i + 1;
        }
        
        int diff = k;
        for(int i = (n - k); i < n; ++i){
            array[i] = array[i - 1] + diff;
            diff = (diff > 0)? -(diff - 1): -(diff + 1); 
        }
        
        return array;
    }
 
    public static void main(String[] args){
        Solution sol;
        int n = 3;
        int k = 2;
        
        sol = new Solution();
        System.out.println("n:" + n + ", k:" + k);
        System.out.println("beautiful arrangement:" + Arrays.toString(sol.constructArray(n, k)));
    }
}
