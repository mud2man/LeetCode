/* Backtrack: Time:O(n!), Space:O(n)
 * 1. Sort input array A, and put it into queue
 * 2. In the backtrack, accumulate count if queue is empty
 * 3. Then, pick the front of queue and call backtrack if (permutation.isEmpty() || isSquare(permutation.peekLast(), curr))
 * 4. Put the used front back into the tail of queue for restoring queue
 */

import java.util.*;

public class Solution{
    private boolean isSquare(int x, int y){
        int sum = x + y;
        return ((int)Math.sqrt(sum) * (int)Math.sqrt(sum) == sum);
    }
    
    private void backtrack(Deque<Integer> queue, Deque<Integer> permutation, int[] count){
        if(queue.isEmpty()){
            count[0]++;
            return;
        }
        
        int prev = queue.peekFirst() - 1;
        int size = queue.size();
        for(int i = 0; i < size; ++i){
            int curr = queue.pollFirst();
            if(prev == curr){
                queue.add(curr);
                continue;
            }
            if(permutation.isEmpty() || isSquare(permutation.peekLast(), curr)){
                permutation.add(curr);
                backtrack(queue, permutation, count);
                permutation.pollLast();
            }
            queue.add(curr);
            prev = curr;
        }
    }
    
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        Deque<Integer> queue = new LinkedList<Integer>();
        for(int a: A){
            queue.add(a);
        }
        int[] count = {0};
        backtrack(queue, new LinkedList<Integer>(), count);
        return count[0];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 17, 8};
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("number of squareful arrays: " + sol.numSquarefulPerms(A));
    }
}
