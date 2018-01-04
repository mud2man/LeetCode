/* Two pointers: Time:O(n^2), Space:O(1)
 * 1. Have two pointers left and right
 * 2. If (a >= 0), put answer[i] from (size - 1), and compare quadratic value of left and right to determine shift which one
      like merge sort
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
                if(leftValue > rightValue){
                    answer[i] = leftValue;
                    left++;
                }
                else{
                    answer[i] = rightValue;
                    right--;
                }
            }
        }
        else{
            for(int i = 0; i < size; ++i){
                int leftValue = quadraticFunction(nums[left], a, b, c);
                int rightValue = quadraticFunction(nums[right], a, b, c);
                if(leftValue < rightValue){
                    answer[i] = leftValue;
                    left++;
                }
                else{
                    answer[i] = rightValue;
                    right--;
                }
            }
        }
        
        return answer;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {-4, -2, 2, 4};
        int a = 1;
        int b = 3;
        int c = 5;

        sol = new Solution();    
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("answer: " + Arrays.toString(sol.sortTransformedArray(nums, a, b, c)));
    }
}
