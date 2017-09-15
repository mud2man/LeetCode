/* Trie: O(n), but leetcode has better solution
 * 1. Build a trie based on the input nums
 * 2. With respect to every num, find its partnet which can be the maximun xor
 * 3. Scan from 31-th bit to 0-th bit
 * 4. If the i-th bit is 0 and right != null, pick up the right path, otherwise pick up the left
 * 5. If the i-th bit is 1 and left != null, pick up the left path, otherwise pick up the right
 */

import java.util.*; // Stack

public class Solution {
    private class TriexNode{
        TriexNode left;
        TriexNode right;
        int val;
        TriexNode(int v){val = v;}
    }
    
    private void addNode(TriexNode root, int target){
        for(int offset = 31; offset >= 0; offset--){
            int mask = 1 << offset;
            if((target & mask) == 0){
                if(root.left == null){
                    root.left = new TriexNode(0);
                }
                root = root.left;
            }
            else{
                if(root.right == null){
                    root.right = new TriexNode(0);
                }
                root = root.right;
            }
        }
        root.val = target;
    }
    
    private int getPartner(TriexNode root, int target){
        for(int offset = 31; offset >= 0; offset--){
            int mask = 1 << offset;
            if((target & mask) == 0){
                if(root.right != null){
                    root = root.right;
                }
                else{
                    root = root.left;
                }
            }
            else{
                if(root.left != null){
                    root = root.left;
                }
                else{
                    root = root.right;
                }
            }
        }
        return root.val;
    }
    
    public int findMaximumXOR(int[] nums) {
        // Construct triex
        TriexNode root = new TriexNode(0);
        for(int num: nums){
             addNode(root, num);
        }
        
        // Get the maximum Xor w.r.t. num
        int maxXor = 0;
        for(int num: nums){
            int partner = getPartner(root, num);
            maxXor = Math.max(maxXor, num ^ partner);
        }
        
        return maxXor;
    }

    public static void main(String[] args){
        int removeCount;
        Solution sol;
        int[] nums = {3, 10, 4};

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("max xor: " + sol.findMaximumXOR(nums));
    }
}
