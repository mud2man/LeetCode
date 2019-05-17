/* Two pointers: Time:O(n^2), Space:O(1)
 * 1. Have two pointers left and right
 * 2. If (a >= 0), put answer[i] from (size - 1), and compare quadratic value of left and right to determine shift which one. It's like merge sort
 * 3. Otherwise, put answer[i] from 0
 */

import java.util.*;

public class Solution{
    private int quadraticFunction(int x, int a, int b, int c){
        return a* x * x + b * x + c;
    }
    
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int size = nums.length;
        int[] answer = new int[size];
        int left = 0;
        int right = size - 1;
        if(a >= 0){
            for(int i = size - 1; i >= 0; --i){
                int leftValue = quadraticFunction(nums[left], a, b, c);
                int rightValue = quadraticFunction(nums[right], a, b, c);
                answer[i] = (leftValue > rightValue)? leftValue: rightValue;
                left += (leftValue > rightValue)? 1: 0;
                right -= (leftValue <= rightValue)? 1: 0;
            }
        }else{
            for(int i = 0; i < size; ++i){
                int leftValue = quadraticFunction(nums[left], a, b, c);
                int rightValue = quadraticFunction(nums[right], a, b, c);
                answer[i] = (leftValue < rightValue)? leftValue: rightValue;
                left += (leftValue < rightValue)? 1: 0;
                right -= (leftValue >= rightValue)? 1: 0;
            }
        }
        return answer;
    }
 
    public static void main(String[] args){
        int[] nums = {-4, -2, 2, 4};
        int a = 1;
        int b = 3;
        int c = 5;
        Solution sol = new Solution();    
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("a: " + a + ", b: " + b + ", c: " + c);
        System.out.println("answer: " + Arrays.toString(sol.sortTransformedArray(nums, a, b, c)));
    }
}
