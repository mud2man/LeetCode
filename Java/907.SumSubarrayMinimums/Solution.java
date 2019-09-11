/* Monotonous queue: Time:O(n), Space:O(n)
 * 1. The element of queue is {A[k], sum of minimums ending at a[i], index} 
 * 2. If queue.peekLast()[0] >= A[i], poll the tail and take its index. e.g., {1,2,1,3} = {1, 1, 1, 3} from 3's perspective
 * 3. And then, accumulate subSum with tail[0] * (tail[2] - secondTail[2])
 * 4, Finally, if queue is not empty, accumulate subSum with queue.peekLast()[1]
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int sumSubarrayMins(int[] A) {
        int sum = 0;
        Deque<int[]> queue = new LinkedList<>();
        for(int i = 0; i < A.length; ++i){
            int subSum = A[i];
            int[] tail = new int[]{A[i], 0, i};
            while(!queue.isEmpty() && queue.peekLast()[0] >= tail[0]){
                int[] secondTail = queue.pollLast();
                subSum = subSum + tail[0] * (tail[2] - secondTail[2]);
                tail[2] = secondTail[2];
            }
            subSum = (!queue.isEmpty())? (subSum + queue.peekLast()[1]): subSum;
            sum = (sum + subSum) % 1_000_000_007;
            tail[1] = subSum;
            queue.add(tail);
        }
        return sum;
    }
 
    public static void main(String[] args){
        int[] A = {3, 1, 2, 4};
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("sum of minimums: " + sol.sumSubarrayMins(A));
    }
}
