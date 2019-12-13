/* Two Pointers: Time:O(h), Space:O(h)
 * 1. Search the node with key value
 * 2. Replace the value of the deleted node with its sucessor
 * 3. If the deleted has both right son and left son, the successor is the leftest node of the right sub-tree
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
    private void search(TreeNode[] fartherSon, int key){
        if(fartherSon[1] == null || fartherSon[1].val == key){
            return;
        }else if(fartherSon[1].val > key){
            fartherSon[0] = fartherSon[1];
            fartherSon[1] = fartherSon[1].left;
            search(fartherSon, key);
        }else{
            fartherSon[0] = fartherSon[1];
            fartherSon[1] = fartherSon[1].right;
            search(fartherSon, key);
        }
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode[] fartherSon = {dummy, root};
        search(fartherSon, key);
        TreeNode farther = fartherSon[0];
        TreeNode son = fartherSon[1];
        if(son == null){
            return root;
        }else if(son.left == null){
            if(farther.left == son){
                farther.left = son.right;
            }else{
                farther.right = son.right;
            }
        }else if(son.right == null){
            if(farther.left == son){
                farther.left = son.left;
            }else{
                farther.right = son.left;
            }
        }else{
            fartherSon = new TreeNode[]{son, son.right};
            while(fartherSon[1].left != null){
                fartherSon[0] = fartherSon[1];
                fartherSon[1] = fartherSon[1].left;
            }
            son.val = fartherSon[1].val;
            if(fartherSon[0] != son){
                fartherSon[0].left = fartherSon[1].right;
            }else{
                fartherSon[0].right = fartherSon[1].right;
            }
        }
        return dummy.left;
    }
  
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }else{
            inorder(root.left);
            System.out.print(root.val + ", ");
            inorder(root.right);
        }
    }
 
    public static void main(String[] args) {
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int key = 20;
        Solution sol = new Solution();
        
        System.out.println("before delete " + key);
        sol.inorder(root);
        System.out.println("");
        
        root = sol.deleteNode(root, key);
        
        System.out.println("after delete " + key);
        sol.inorder(root);
        System.out.println("");
    }
}
