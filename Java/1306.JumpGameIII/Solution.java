/* BFS: Time:O(n), Space:O(1)
 * 1. Set arr[curr] to -1 after we visited it
 * 2. Continue the loop as long as queue is not empty
 */

import java.util.*;


public class Solution{
    public boolean canReach(int[] arr, int start) {
        Deque<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int curr = queue.pollFirst();
            if(arr[curr] == 0){
                return true;
            }
            if(curr + arr[curr] < arr.length && arr[curr + arr[curr]] != -1){
                queue.add(curr + arr[curr]);
            }
            if(curr - arr[curr] >= 0 && arr[curr - arr[curr]] != -1){
                queue.add(curr - arr[curr]);
            }
            arr[curr] = -1;
        }
        return false;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("can reach:" + sol.canReach(arr, start));
    }
}
