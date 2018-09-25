/* Math: Time:O(n), Space:O(1)
 * 1. Have 2 utlity methods "multiply" and "multiplyTen"
 * 2. "multiply" used for ret*a^exp, "multiplyTen" used for ret^10
 * 3. Traverse from b[0] to b[1] to increase exponential, and get the result
 */         

import java.util.*;

public class Solution {
    private int multiply(int ret, int base, int exp){
        if(exp == 0){
            return ret % 1337; 
        }
        
        ret = ret % 1337;
        base = base % 1337;
        for(int i = 0; i < exp; ++i){
            ret = (ret * base) % 1337; 
        }
        return ret;
    }
    
    private int multiplyTen(int base){
        int ret = 1;
        base = base % 1337;
        for(int i = 0; i < 10; ++i){
            ret = (ret * base) % 1337; 
        }
        return ret;
    }
    
    public int superPow(int a, int[] b) {
        int ret = multiply(1, a, b[0]);
        for(int i = 1; i < b.length; ++i){
            ret = multiplyTen(ret);
            ret = multiply(ret, a, b[i]);
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int a = 2;
        int[] b = {1, 0};
        System.out.println("a: " + a);
        System.out.println("b: " + Arrays.toString(b));
        System.out.println("pow: " + sol.superPow(a, b));
    }
}
