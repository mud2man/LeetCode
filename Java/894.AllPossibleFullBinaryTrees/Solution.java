/* Dynamic Programming: Time:O(n), Space:O(n), where n is the tree#. LC has recursive solution
 * 1. dp[i] = the list of full binary trees where node# = i
 * 2. dp[i] can be abbotain by select its left/right sub-tree pair from dp[1]/dp[i - 1 - 1], dp[3]/dp[i - 3 - 1], ....
 * 3. dp[N] is the answer
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
        public List<TreeNode> allPossibleFBT(int N) {
        if(N % 2 == 0){
            return new ArrayList<>();
        }
        
        List<List<TreeNode>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        List<TreeNode> firstList = new ArrayList<>();
        firstList.add(new TreeNode(0));
        dp.add(firstList);
        for(int i = 2; i <= N; ++i){
            if(i % 2 == 0){
                dp.add(new ArrayList<>());
            }
            else{
                List<TreeNode> trees = new ArrayList<>();
                for(int l = 1; l < i; l += 2){
                    List<TreeNode> lefts = dp.get(l);
                    List<TreeNode> rights = dp.get(i - l - 1);
                    for(TreeNode left: lefts){
                        for(TreeNode right: rights){
                            TreeNode root = new TreeNode(0);
                            root.left = left;
                            root.right = right;
                            trees.add(root);
                        }
                    }
                }
                dp.add(trees);
            }
        }
        return dp.get(N);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 7;
        System.out.println("N: " + N);
        System.out.println("full binary trees: " + sol.allPossibleFBT(N));
    }
}
