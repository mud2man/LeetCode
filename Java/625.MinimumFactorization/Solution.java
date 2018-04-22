/* Greedy: Time:O(logn), Space:O(logn)
 * 1. The valid n is the one only can be factorized by {2, 3, 4, 5, 6, 7, 8, 9}
 * 2. Then we can try in the decending order onr the factors to factorize the number n
 * 3. In this way, we can put the biggest digit to the last position of queue
 * 4. Transform the queue to integer to get the answer
 */

import java.util.*;

public class Solution{
    public int smallestFactorization(int a) {
        if(a < 10){
            return a;
        }
        
        int[] factors = {2, 3, 4, 5, 6, 7, 8, 9};
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = factors.length - 1; i >= 0 && a > 1; --i){
            while(a % factors[i] == 0){
                queue.addFirst(factors[i]);
                a = a / factors[i];
            }
        }
        
        if(a > 1){
            return 0;
        }
        
        long ret = 0;
        while(!queue.isEmpty()){
            ret = ret * 10 + queue.pollFirst();
            if(ret > (long)Integer.MAX_VALUE){
                return 0;
            }
        }
        
        return (int)ret;
    }
 
    public static void main(String[] args){
        int n = 48;
        Solution sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("smallest factorization: " + sol.smallestFactorization(n));
    }
}
