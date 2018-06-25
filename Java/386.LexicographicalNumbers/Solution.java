/* Time:O(nlogn), Space:O(1)
 * 1. Call getNext to get the next number given previous number
 * 2. If append 0 not over the upper bound, return (prev * 10)
 * 3. Otherwise, accumulate previous number, and delete the tailing zeroes
 * 4. If next is over ub, divide next with 10, and add 1, then delete the tailing zeroes, then return next
 */         

import java.util.*;

public class Solution {
    private int removeTailingZeros(int num){
        while(num % 10 == 0){
            num = num / 10;
        }
        return num;
    }
    
    private int getNext(int prev, int ub){
        if(prev * 10 <= ub){
            return prev * 10;
        }
        
        int next = prev + 1;
        next = removeTailingZeros(next);        
        while(next > ub){
            next = next / 10 + 1;
        }
        next = removeTailingZeros(next);

        return next;        
    }
    
    public List<Integer> lexicalOrder(int n) {
        List<Integer> nums = new ArrayList<Integer>();
        int prev = 1;
        nums.add(prev);
        for(int i = 1; i < n; ++i){
            int next = getNext(prev, n);
            nums.add(next);
            prev = next;
        }
        return nums;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 13;
        System.out.println("n: " + n);
        System.out.println("lexicographical numbers: " + sol.lexicalOrder(n));
    }
}
