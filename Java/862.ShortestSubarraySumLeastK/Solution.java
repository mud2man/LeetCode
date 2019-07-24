/* Slide window: O(n)
 * 1. If the all elemnet array int A >= 0, it's would be easy
 * 2. Change A[i] = {number, length}, and maintain each element A[i][0] >= 0
 * 3. Have a window "queue" with 0 < sum < K, and "len" to record its length
 * 4. If num >= 0, add it to end of window, and pop front out of "queue" until sum < K, and update minLen
 * 5. If num < 0, merge the num with the ned of queue until end[0] > 0, while end[1] record its length 
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public int shortestSubarray(int[] A, int K) {
        Deque<int[]> queue = new LinkedList<>();
        int sum = 0;
        int len = 0;
        int minLen = A.length + 1;
        for(int num: A){
            if(num >= 0){
                sum += num;
                len++;
                queue.add(new int[]{num, 1});
                while(sum >= K && len > 0){
                    minLen = Math.min(minLen, len);
                    int[] front = queue.pollFirst();
                    sum -= front[0];
                    len -= front[1];
                }
            }else{
                sum += num;
                len++;
                int[] end = new int[]{num, 1};
                while(!queue.isEmpty() && end[0] <= 0){
                    end[0] += queue.peekLast()[0];
                    end[1] += queue.peekLast()[1];
                    queue.pollLast();
                }
                
                if(end[0] > 0){
                    queue.addLast(end);
                }else{
                    sum = 0;
                    len = 0;
                }
            }
        }
        return (minLen == A.length + 1)? -1: minLen;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {2, -1, 2};
        int K = 3;
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("K: " + K);
        System.out.println("minimum length: " + sol.shortestSubarray(A, K));
    }
}
