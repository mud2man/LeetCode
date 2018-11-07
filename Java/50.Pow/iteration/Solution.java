/* Map: Time:O(logn), Space:O(1).
 * 1. View exponential n as binary
 * 2. Let initial value is 1, and multiply it with x^(2^weight)
 *
 ex: pow(2, 5), x = 2, n = 2'b101  
 * time[-1]: ans = 1, currentPproduct = 2
 * time[0]: ans = 1 * 2, currentPproduct = 4
 * time[1]: ans = 2, currentPproduct = 16
 * time[2]: ans = 2 * 16 = 32, currentPproduct = 256
 *
 */         

import java.util.*;

public class Solution {
    public double myPow(double x, int n) {
        long e = (long)n;
        if (e < 0) {
            x = 1 / x;
            e = -e;
        }

        double ans = 1;
        double currentProduct = x;
        for (long i = e; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * currentProduct;
            }
            currentProduct = currentProduct * currentProduct;
        }
        return ans;
    }
 
    public static void main(String[] args){
        Solution sol;
        double x = 2.0;
        int n = 10;
        sol = new Solution();
        System.out.println("x: " + x);
        System.out.println("n: " + n);
        System.out.println("pow(" + x + ", " + n + "): " + sol.myPow(x, n));
    }
}
