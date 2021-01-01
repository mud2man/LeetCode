/* Sliding window: Time:O(n), Space:O(1)
 * 1. Update maxPlus as we shift X-wide sliding window from left to right by -(grumpy[i - X] * customers[i - X])+(grumpy[i] * customers[i])
 * 2. Accumulate base as we shift X-wide sliding window
 * 3. The answer is base + maxPlus
 */     

import java.util.*; // Stack

public class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int base = 0;
        int plus = 0;
        for(int i = 0; i < X; i++){
            base += (1- grumpy[i]) * customers[i];
            plus += grumpy[i] * customers[i];
        }
        
        int maxPlus = plus;
        for(int i = X; i < customers.length; i++){
            base += (1- grumpy[i]) * customers[i];
            plus -= grumpy[i - X] * customers[i - X];
            plus += grumpy[i] * customers[i];
            maxPlus = Math.max(maxPlus, plus);
        }
        return base + maxPlus;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        System.out.println("customers:" + Arrays.toString(customers));
        System.out.println("grumpy:" + Arrays.toString(grumpy));
        System.out.println("X:" + X);
        System.out.println("max satisfied:" + sol.maxSatisfied(customers, grumpy, X));
    }
}
