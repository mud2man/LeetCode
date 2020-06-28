/* Math: Time:O(n^0.5), Space:O(n^0.5)
 * 1. Iterate possible factor from 1 to n^0.5
 * 2. The second half of factors can be got from the first half
 * 3. If we fail to get k-th factor from the first half, we can get the k-th factor (n / factors.get(factors.size() - k)
 */     

import java.util.*; // Stack

public class Solution {
    public int kthFactor(int n, int k) {
        int sqrtRoot = (int)Math.sqrt(n);
        List<Integer> factors = new ArrayList<>();
        for(int i = 1; i <= sqrtRoot; ++i){
            if(n % i == 0){
                factors.add(i);
            }
            if(factors.size() == k){
                return i;
            }
        }
        
        k -= factors.size();
        int lastFactor = factors.get(factors.size() - 1);
        k +=(lastFactor * lastFactor == n)? 1: 0;
        return (k > factors.size())? -1: (n / factors.get(factors.size() - k));  
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 12; // factors = {1, 2, 3, 4, 6, 12}
        int k = 3;
        System.out.println("n:" + n + ", k:" + k);
        System.out.println("k-th factor:" + sol.kthFactor(n, k));
    }
}
