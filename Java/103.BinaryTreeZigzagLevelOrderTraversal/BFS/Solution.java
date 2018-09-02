/* In oredr: Time:O(n), Space:O(n)
 * 1. Traverse BST inorderly, and transfom it in place
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> levels = new LinkedList<List<Integer>>();
        int hight = 0;
        TreeNode currNode;
        
        if(root == null){
            return levels;
        }
        
        queue.add(root); 
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new LinkedList<Integer>();
            if(hight % 2 == 0){
                //left to right
                for(int i = 0; i < size; ++i){
                    currNode =  queue.pollLast();
                    level.add(currNode.val);
                    if(currNode.left != null){
                        queue.addFirst(currNode.left);
                    }
                    if(currNode.right != null){
                        queue.addFirst(currNode.right);
                    }
                }
            }
            else{
                //right to left
                for(int i = 0; i < size; ++i){
                    currNode =  queue.pollFirst();
                    level.add(currNode.val);
                    if(currNode.right != null){
                        queue.addLast(currNode.right);
                    }
                    if(currNode.left != null){
                        queue.addLast(currNode.left);
                    } 
                }
            }
            levels.add(level);
            hight++;
        }
        return levels;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     3
         *    / \
         *   9  20
         *      / \ 
         *     15  7  
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> zigzag = sol.zigzagLevelOrder(root);
        System.out.println("zigzag: " + zigzag);
    }
}
