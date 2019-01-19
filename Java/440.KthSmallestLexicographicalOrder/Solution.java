/* Two points: Time:O(logn), Space:O(1)
 * 1. Let curr starting from 1, and caculate steps# to reach next(curr + 1)
 * 2. If steps >= steps, assign curr with next. Otherwise, go to next level of teh tree and curr = curr * 10, and --k
 * 3. We caculate steps level by level until curr > n
 *
 * ex: treen = 13
 *          --------null--------
 *         |      | | | | | | | |
 *         1,     2,3,4,5,6,7,8,9
 *       / | \
 *      10,11,12
 */

import java.util.*;

public class Solution {
    public int findKthNumber(int n, int k) {
        --k;
        int curr = 1;
        while(k > 0){
            if(curr % 9 == 9){
                --k;
                curr = curr * 10;
            }
            else{
                int next = curr + 1;
                int steps = getSteps(n, curr, next);
                if(k >= steps){
                    k -= steps;
                    curr = next;
                }
                else{
                    --k;
                    curr = curr * 10;
                }
            }
        }
        return curr;
    }
    
    private int getSteps(int n, int curr, int next){
        long steps = 0;
        long longN = (long)n;
        long longCurr = (long)curr;
        long longNext = (long)next;
        while(longCurr <= longN){
            steps += Math.min(longNext, longN + 1) - longCurr;
            longCurr = longCurr * 10;
            longNext = longNext * 10;
        }
        return (int)steps;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 13;
        System.out.println("n: " + n);
        System.out.println("count of digit one: " + sol.countDigitOne(n));
    }
}
