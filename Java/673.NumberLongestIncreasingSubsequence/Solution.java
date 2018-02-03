/* Dynamic Programming: Time:O(n^2), Space: O(n)
 * 1. dp[i] store the node, where dp[i].length = the maximum length of subsequence ends with nums[i], dp[i].count = the number of 
      subsequence with length dp[i].length
 * 2. In each loop, traverse from 0 to (i - 1), and update dp[i] when nums[i] > nums[j]
 * 3. Finally, accumulate the count of all node in dp, where dp[i].length == maxLength
 * 
 * ex: nums = {1, 3, 5, 4, 7}
 *     dp[] = {{1, 1}, {2, 1}, {3, 1}, {3, 1}, {4, 2}}
 */         

import java.util.*;

public class Solution {
    private class Node{
        int length;
        int count;
        Node(int l, int c){length = l; count = c;}
    }
    
    public int findNumberOfLIS(int[] nums) {
        int maxLength = 0;
        Node[] dp = new Node[nums.length];
        
        for(int i = 0; i < nums.length; ++i){
            Node node = new Node(1, 1);
            for(int j = 0; j < i; ++j){
                if(nums[i] > nums[j]){
                    if(node.length == (dp[j].length + 1)){
                        node.count += dp[j].count;
                    }
                    else if(node.length <= dp[j].length){
                        node.length = dp[j].length + 1;
                        node.count = dp[j].count;
                    }
                    else{
                        continue;
                    }
                }
            }
                     
            maxLength = Math.max(maxLength, node.length);
            dp[i] = node;
        }
        
        int count = 0;
        for(Node node: dp){
            count += (node.length == maxLength)? node.count: 0;
        }
        
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 3, 5, 4, 7};
        
        sol = new Solution();
        System.out.println("number of longest increasing subsequence: " + sol.findNumberOfLIS(nums));
    }
}
