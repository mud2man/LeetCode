/* Map: Time:O(logn), Space:O(logn). LeetCode has O(logn)-O(1) solution
 * 1. Have a map "cache" to store the mapping between exponent to number
 * 2. Call helper recursively, where helper calculate the closest number given exponent "n"
 * 3. Because during the first call of helper, we store all possible expoent<->number pair into cache. 
 * 4. So the next helper will get the answer with O(1)
 */         

import java.util.*;

public class Solution {
    public double helper(double x, long n, Map<Long, Double> cache){
        if(cache.containsKey(n)){
            return cache.get(n);
        }

        long exp = 1;
        double num = x;
        for(exp = 1; 2 * exp <= n; exp *= 2){
            cache.put(exp, num);
            num = num * num;
        }
        return num * helper(x, n - exp, cache);
    }
    
    public double myPow(double x, int n) {
        Map<Long, Double> cache = new HashMap<>();
        cache.put(0L, 1.0);
        return (n >= 0)? helper(x, (long)n, cache): helper(1/x, -(long)n, cache);
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
