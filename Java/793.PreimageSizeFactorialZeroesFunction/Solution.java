/* Math + BinarySearch: O((logK)^2),
 * 1. The zero# depends on how many number can be divided by 5, 5^2, 5^3,..., because 2, 2^2, 2^2 is more than 5's
 * 2. The number# given K is either 0 or 5, so we use binary search try to find the number containing K zeros
 * 3. If we find the number, then return 5. Otherwise, we return 0
 */

import java.util.*;
import java.util.concurrent.locks.*;

public class Solution {
    private int getZeroCount(long n){
        int count = 0;
        long base = 5;
        while(base <= n){
            count += (int)(n / base);
            base *= 5;
        }
        return count;
    }
    
    public int preimageSizeFZF(int K) {
        long lb = 0;
        long hb = 5 * (long)K;
        while(lb <= hb){
            long mid = (lb + hb) / 2;
            int zeroCount = getZeroCount(mid);
            if(zeroCount == K){
                return 5;
            }else if(zeroCount < K){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        return 0;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int K = 5;
        System.out.println("K:" + K);   
        System.out.println("zeros#:" + sol.preimageSizeFZF(K));   
	}
}
