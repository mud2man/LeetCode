/* DFS + HashMap: O(n)
 * 1. Traverse all nodes using DFS, and update frequencyMap and maxFrequency
 * 2. Retrieve all the sums with the highest frequency maxFrequency, and put them into answer
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
    private int dfs(TreeNode root, HashMap<Integer, Integer> frequencyMap, int[] maxFrequency){
        if(root == null){
            return 0;
        }
        
        int sum = dfs(root.left, frequencyMap, maxFrequency) + dfs(root.right, frequencyMap, maxFrequency) + root.val;
        if(!frequencyMap.containsKey(sum)){
            frequencyMap.put(sum, 0);
        }
        frequencyMap.put(sum, frequencyMap.get(sum) + 1);
        maxFrequency[0] = Math.max(maxFrequency[0], frequencyMap.get(sum));
        return sum;
    }
    
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        int[] maxFrequency = new int[1];
        List<Integer> sums = new ArrayList<Integer>();
        
        dfs(root, frequencyMap, maxFrequency);
        
        for(Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()){
            int sum = entry.getKey();
            int frequency = entry.getValue();
            if(frequency == maxFrequency[0]){
                sums.add(sum);
            }
        }
        
        int[] answer = new int[sums.size()];
        for(int i = 0; i < answer.length; ++i){
            answer[i] = sums.get(i);
        }
        return answer;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     5
         *    / \
         *   2   -5
         */
        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);

        int[] sums = sol.findFrequentTreeSum(root);
        
        System.out.println("most frequency sums: " + Arrays.toString(sums));
    }
}
