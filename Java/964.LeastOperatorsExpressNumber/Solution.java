/* Divide&Conquer: Time:O(log(target)), Space:O(log(target))
 * 1. If the number "target" is least expression, the MSB is either (target / base) * base + ...or (target / base + 1) * base - ...
 * 2. We use (target + "." + base) as the key to record the case we visited 
 * 3. When (target < x) the soultion is the minimum of (target * 2 e.g. ,+ x / x + x / x + ...) and (+ x - x / x - x / x)
 * 4. Finally, we get the two values from two keys (target + "." + base) and (target + "." + base / x), and pick the minimum as answer
 */

import java.util.*; // Stack

public class Solution {
    private long helper(Map<String, Long> cache, long base, long exp, long x, long target){
        String key = Long.toString(target) + "." + Long.toString(base);
        if(target < x){
            return Math.min(target * 2, (x - target) * 2 + 1);
        }else if(cache.containsKey(key)){
            return cache.get(key);
        }else{
            long ans = 0;
            if(target == base){
                ans = exp;
            }else{
                long factor = target / base;
                long ans0 = factor * exp;
                long ans1 = (factor + 1) * exp;
                ans0 += helper(cache, base / x, exp - 1, x, target - factor * base);
                ans1 += helper(cache, base / x, exp - 1, x, (factor + 1) * base - target);
                ans = Math.min(ans0, ans1);
            }
            cache.put(key, ans);
            return ans;
        }
    }
    
    public int leastOpsExpressTarget(int x, int target) {
        if(target == 1){
            return 1;
        }
        
        long base = 1;
        long exp = 0;
        while(base < target){
            base *= x;
            exp++;
        }
        Map<String, Long> cache = new HashMap<>();
        helper(cache, base, exp, (long)x, (long)target);
        long ans0 = cache.getOrDefault(Long.toString(target) + "." + Long.toString(base), Long.MAX_VALUE) - 1;
        long ans1 = cache.getOrDefault(Long.toString(target) + "." + Long.toString(base / x), Long.MAX_VALUE) - 1;
        return (int)Math.min(ans0, ans1);
    }
  
    public static void main(String[] args){
        int x = 3;
        int target = 19;
        Solution sol = new Solution();
        System.out.println("x: " + x + ", target:" + target);
        System.out.println("least express: " + sol.leastOpsExpressTarget(x, target));
    }
}
