/* Sort: Time:O(n^2), Space:O(1)
 * 1. Find the biggest number starting from A.length, and do "reverse" move it to the head
 * 2. And do reverse again to move it from head to its position
 *
 * ex: A = {3, 2, 4, 1}
 * time[0] = {4, 2, 3, 1}, sequence = {3}
 * time[1] = {1, 3, 2, 4}, sequence = {3, 4}
 * time[2] = {3, 1, 2, 4}, sequence = {3, 4, 2}
 * time[3] = {2, 1, 3, 4}, sequence = {3, 4, 2, 3}
 * time[4] = {1, 2, 3, 4}, sequence = {3, 4, 2, 3, 2}
 */

import java.util.*; // Stack

public class Solution {
    private void reverse(int[] A, int end){
        int start = 0;
        while(start < end){
            int tmp = A[start];
            A[start++] = A[end];
            A[end--] = tmp;
        }
    }
    
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> sequence = new ArrayList<>();
        for(int i = A.length; i > 1; --i){
            int j = 0;
            for(j = 0; j < i; ++j){
                if(A[j] == i){
                    break;
                }
            }
            
            if(j == i - 1){
                continue;
            }
            else if(j == 0){
                sequence.add(i);
                reverse(A, i - 1);
            }
            else{
                sequence.add(j + 1);
                reverse(A, j);
                sequence.add(i);
                reverse(A, i - 1);
            }
        }
        return sequence;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {3, 2, 4, 1};
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("sequence: " + sol.pancakeSort(A));
    }
}
