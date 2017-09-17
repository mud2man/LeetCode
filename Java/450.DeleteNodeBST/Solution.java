/* O(hight)
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
    private void find(TreeNode[] fartherAndSon, int key){
        TreeNode son = fartherAndSon[1];
        
        if(son == null){
            return;
        }
        
        if(son.val == key){
            return;
        }
        else if(son.val > key){
            fartherAndSon[0] = son;
            fartherAndSon[1] = son.left;
            find(fartherAndSon, key);
        }
        else{
            fartherAndSon[0] = son;
            fartherAndSon[1] = son.right;
            find(fartherAndSon, key);
        }
    }
    
    private void delete(TreeNode[] fartherAndSon){
        TreeNode farther = fartherAndSon[0];
        TreeNode son = fartherAndSon[1];

        if(son.right != null && son.left != null){
            fartherAndSon[0] = son.right;
            fartherAndSon[1] = fartherAndSon[0].left;
            
            //find the leftest node
            while(fartherAndSon[1] != null && fartherAndSon[1].left != null){
                fartherAndSon[0] = fartherAndSon[1];
                fartherAndSon[1] = fartherAndSon[1].left;
            }
            
            if(fartherAndSon[1] != null){
                son.val = fartherAndSon[1].val;
                delete(fartherAndSon);
            }
            else{
                son.val =  fartherAndSon[0].val;
                fartherAndSon[0] = son;
                fartherAndSon[1] = son.right;
                delete(fartherAndSon);
            }
        }
        else{
            TreeNode successor = (son.left != null)? son.left: son.right;
            if(farther.left == son){
                farther.left = successor;
            }
            else{
                farther.right = successor;
            }
        }
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode[] fartherAndSon = new TreeNode[]{dummy, root};
        find(fartherAndSon, key);
        
        if(fartherAndSon[1] != null){
            delete(fartherAndSon);
        }
        
        return dummy.left;
    }
 
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        else{
            inorder(root.left);
            System.out.print(root.val + ", ");
            inorder(root.right);
        }
    }
 
    public static void main(String[] args)
    {
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        int key;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        key = 20;
        sol = new Solution();
        
        System.out.println("before delete " + key);
        sol.inorder(root);
        System.out.println("");
        
        root = sol.deleteNode(root, key);
        
        System.out.println("after delete " + key);
        sol.inorder(root);
        System.out.println("");
    }
}
