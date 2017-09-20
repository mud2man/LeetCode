/* O(n)
 * 1. Create two pointers. i is pointed to the last index of creater
 * 2. The other one tailIdx is pointed to the last index of createe
 * 3. Loop until tailIdx is larger than n
 *
 * EX: n = 4 
 * time[0]: i = 0, tailIdx = 0, magic = [1, 2] 
 * time[1]: i = 1, tailIdx = 2, magic = [1, 2, 2] 
 * time[2]: i = 2, tailIdx = 4, magic = [1, 2, 2, 1, 1] 
 */

import java.util.*; // Stack

public class Solution {
    public int magicalString(int n) {
        int[] magic = new int[n + 3];
        magic[0] = 1;
        magic[1] = 2;
        magic[2] = 2;
        
        int tailIdx = 3;
        int oneCount = 1;
        for(int i = 2; i < n && tailIdx < n; ++i){
            int val = (i % 2) + 1;
            int count = magic[i];
            for(int j = 0; j < count; ++j){
                magic[tailIdx++] = val;
                if(tailIdx <= n && val == 1){
                    oneCount++;
                }
            }
        }
        return (n > 0)? oneCount: 0;
    }
 
    public static void main(String[] args){
        Solution sol;
        int n ; 

        sol = new Solution();
        n = 4;
        
        System.out.println("The number length magic string: " + n);
        System.out.println("The number of 1s in magic string: " + sol.magicalString(n));
    }
}
