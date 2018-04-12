/* Bit Manipulation: Time:O(logn), Space:O(1)
 * 1. Translate n to 2's base, we will reallize the following cases
 * 2. If n is even, divide with 2 is best
 * 3. If n is 3, then return count + 2 
 * 4. If n is odd, and has more than of equal to 2 the tail one, add 1 is the best
 */

import java.util.*;

public class Solution{
    public int integerReplacement(int n) {
        int count = 0;
        long m = (long)n;
        while(m > 0){
            if(m % 2 == 0){
                m = m / 2;
            }
            else{
                if(m == 3){
                    return count + 2;
                }
                if((m & 3) == 3){
                    m++;
                }
                else{
                    m--;
                }
            }
            count++;
        }
        return count - 1;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 7;
        
        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("replacement time: " + sol.integerReplacement(n));
    }
}
