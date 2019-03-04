/* Queue + Dynamic Programming: Time:O(n), Space:O(n). LeetCode has sliding window using Space:O(1)
 * 1. Use queue to store the flip index
 * 2. dp[i + 1] = the longest consecutive ones ending at index i 
 * 3. If queue.size() < K or A[i] == 1, dp[i + 1] = dp[i] + 1
 * 4. Otherwise, dp[i + 1] = i - queue.pollFirst()
 *
 * ex:     nums={0, 0, 1, 1, 0, 0, 1, 1, 0, 1}, K = 3
 * i = 0, dp={0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, queue={0} 
 * i = 1, dp={0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0}, queue={0, 1} 
 * i = 2, dp={0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0}, queue={0, 1} 
 * i = 3, dp={0, 1, 2, 3, 4, 0, 0, 0, 0, 0, 0}, queue={0, 1}
 * i = 4, dp={0, 1, 2, 3, 4, 4, 0, 0, 0, 0, 0}, queue={1, 4}
 * i = 5, dp={0, 1, 2, 3, 4, 4, 5, 0, 0, 0, 0}, queue={1, 4, 5} 
 * i = 6, dp={0, 1, 2, 3, 4, 4, 5, 6, 0, 0, 0}, queue={1, 4, 5} 
 * i = 7, dp={0, 1, 2, 3, 4, 4, 5, 6, 7, 0, 0}, queue={1, 4, 5} 
 * i = 8, dp={0, 1, 2, 3, 4, 4, 4, 6, 7, 7, 0}, queue={4, 5, 8}  
 * i = 9, dp={0, 1, 2, 3, 4, 4, 4, 6, 7, 7, 8}, queue={4, 5, 8}
 */          

import java.util.*; // Stack

public class Solution {
    public int longestOnes(int[] A, int K) {
        Deque<Integer> queue = new LinkedList<>();
        int[] dp = new int[A.length + 1];
        int max = 0;
        for(int i = 0; i < A.length; ++i){
            if(A[i] == 0){
                if(queue.size() < K){
                    queue.addLast(i);
                    dp[i + 1] = dp[i] + 1;
                }
                else if(!queue.isEmpty()){
                    int front = queue.pollFirst();
                    dp[i + 1] = i - front;
                    queue.addLast(i);
                }
            }
            else{
                dp[i + 1] = dp[i] + 1;
            }
            max = Math.max(max, dp[i + 1]);
        }
        return max;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int K = 3;
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 0, 1};
        System.out.println("K: " + K);
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maxConsecutiveOnes: " + sol.longestOnes(nums, K));
    }
}
