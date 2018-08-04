/* Math: Time:O(logn), Space:O(1)
 * 1. If n cane be expressed as b^e + b^(e-1) + ... + 1, then n = (b^(e + 1) - 1) / (b - 1)
 * 2. Because (b + 1)^e >= n > b^e,  b + 1 >= root(n, e) > b
 * 3. e ranges from 1 to log2(num) 
 * 4. Traverse e from 1 to log2(num), and get the base, then check if num == (b^(e + 1) - 1) / (b - 1)
 */

import java.util.*;

public class Solution{
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int maxExpt = (int) (Math.log(num) / Math.log(2));
        for(int expt = maxExpt; expt > 0; --expt){
            long base = (long) Math.pow(num - 1, (double)1 / (double)expt);
            BigInteger bBase = new BigInteger(Long.toString(base));
            BigInteger bPow = bBase.pow(expt + 1);
            BigInteger bNum = new BigInteger(Long.toString(num));
            // check num ?= (base^(expt + 1) - 1) / (base - 1)
            if(bNum.multiply(bBase.subtract(BigInteger.ONE)).equals(bPow.subtract(BigInteger.ONE))){
                return Long.toString(base);
            }
        }
        return Long.toString(num - 1);
    }
 
    public static void main(String[] args){
        String n = "13";
        Solution sol = new Solution();

        System.out.println("n:" + n);
        System.out.println("smallest good base:" + sol.smallestGoodBase(n));
    }
}
