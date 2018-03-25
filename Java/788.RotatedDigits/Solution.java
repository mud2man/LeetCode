/* Math: Time:O(logN), Space:O(1)
 * 1. Assume N is abc, get the valid count of (a-1)XX, then get the count of a(b-1)X, finally get the count of abX.
 * 2. Add all the three count, it's is the answer
 * ex: N = 857 => count of 7XX = (5*7*7 - 2*3*3), count of 84X = (3*7 - 2*3), count of 856 is 5
 *     the answer = 227 + 15 + 5
 */

import java.util.*;

public class Solution{
    int[] a = {0, 1, 2, 5, 6, 8, 9};
    int[] b = {0, 1, 8};
    
    private int getCount(int target, int[] nums){
        int count = 0;
        for(int num: nums){
            if(num < target){
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
    
    public int rotatedDigits(int N) {
        int copyN = N;
        LinkedList<Integer> number = new LinkedList<Integer>();
        while(copyN> 0){
            number.addFirst(copyN % 10);
            copyN = copyN /10;
        }
        
        int increaseBase = (int)Math.pow(7, number.size() - 1); 
        int decreaseBase = (int)Math.pow(3, number.size() - 1);
        boolean needDecrease = true;
        boolean needAdd = false;
        int ret = 0;
        while(!number.isEmpty()){
            int msb = number.pollFirst();
            int addCount = getCount(msb, a);
            int subCoung = getCount(msb, b);
            ret = ret + addCount * increaseBase;
            ret = (needDecrease)? ret - subCoung * decreaseBase: ret;
            if(msb == 3 || msb == 4 || msb == 7){
                needAdd = false;
                break;
            }
            else if(msb == 2 || msb == 5 || msb == 6 || msb == 9){
                needAdd = true;
                needDecrease = false;
            }
            increaseBase = increaseBase / 7;
            decreaseBase = decreaseBase / 3;
        }
        
        ret = (needAdd)? ret + 1: ret;
        return ret;
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int N = 15;
        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("valid rotations: " + sol.rotatedDigits(N));
    }
}
