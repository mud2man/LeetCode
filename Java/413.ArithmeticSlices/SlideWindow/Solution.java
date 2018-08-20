/* Sliding window: Time:O(n), Space:O(1)
 * 1. Have two pointers "head" and "tail"
 * 2. In every round, shift tail right until the difference changes, and get the number of slices
 * 3. Replace head with tail - 1
 *
 * ex: input 1, 2, 3, 4, 1, -2, -5
 * time[0]: head = 0, tail = 4, slices = 3
 * time[1]: head = 3, tail = 7, slices = 3
 */

import java.util.*; // Stack

public class Solution {
    private int getSlices(int head, int tail){
        int len = tail - head - 2;
        int num = (len * (len + 1)) / 2;
        return num;
    }
    
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3){
            return 0;
        }
        
        int head = 0;
        int tail = 0;
        int num = 0;
        while(head < A.length - 2){
            int diff = A[head + 1] - A[head];
            tail = head + 2;
            while(tail < A.length && A[tail] - A[tail - 1] == diff){
                tail++;
            }
            num += getSlices(head, tail);
            head = tail - 1;
        }
        return num;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 2, 3, 4}; 
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("slices: " + sol.numberOfArithmeticSlices(A));
    }
}
