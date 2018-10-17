/* DFS: Time:O(n), Space:O(n). LeetCode has Space:O(1) solution
 * 1. Use "record" set to record the visited node of this time, "visited" is to store the node ever visited
 * 2. In dfs, if visited contains the curr, return false, because the node is visited before and fail
 * 3. If, record contains the curr, check if the loop valid
 * 4. Otherwise, jump to next position, and call dfs
 */

import java.util.*;

public class Solution{
    private boolean check(int[] nums, int curr){
        int start = curr;
        int step = nums[curr];
        curr = (curr + step + nums.length) % nums.length;
        while(start != curr){
            if(step * nums[curr] < 0){
                return false;
            }
            curr = (curr + nums[curr] + nums.length) % nums.length;
        }
        return true;
    }
    
    private boolean dfs(int[] nums, int curr, int prev, Set<Integer> visited, Set<Integer> record){
        if(visited.contains(curr)){
            return false;
        }

        if(record.contains(curr)){
            return (prev != -1 && curr != prev)? check(nums, curr): false;
        }

        record.add(curr);
        int step = nums[curr];
        int next = (curr + step + nums.length) % nums.length;
        return dfs(nums, next, curr, visited, record);
    }
    
    public boolean circularArrayLoop(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < nums.length; ++i){
            Set<Integer> record = new HashSet<>();
            if(!visited.contains(i) && dfs(nums, i, -1, visited, record)){
                return true;
            }
            visited.addAll(record);
        }
        return false;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, -1, 1, 2, 2};
        
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("loop exists: " + sol.circularArrayLoop(nums));
    }
}
