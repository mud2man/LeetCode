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
        }
        else if(fartherSon[1].val > key){
            fartherSon[0] = fartherSon[1];
            fartherSon[1] = fartherSon[1].left;
            search(fartherSon, key);
        }
        else{
            fartherSon[0] = fartherSon[1];
            fartherSon[1] = fartherSon[1].right;
            search(fartherSon, key);
        }
    }
    
    private void delete(TreeNode[] fartherSon){
        if(fartherSon[1] == null){
            return;
        }
        
        if(fartherSon[1].right == null){
            if(fartherSon[0].left == fartherSon[1]){
                fartherSon[0].left = fartherSon[1].left;
            }
            else{
                fartherSon[0].right = fartherSon[1].left;
            }
        }
        else{
            if(fartherSon[1].right.left == null){
                if(fartherSon[1] == fartherSon[0].left){
                    fartherSon[0].left = fartherSon[1].right;
                }
                else{
                    fartherSon[0].right = fartherSon[1].right;
                }
                fartherSon[1].right.left = fartherSon[1].left;
            }
            else{
                TreeNode ptr0 = fartherSon[1].right;
                TreeNode ptr1 = ptr0.left;
                while(ptr1.left != null){
                    ptr0 = ptr1;
                    ptr1 = ptr1.left;
                }
                fartherSon[1].val = ptr1.val;
                ptr0.left = ptr1.right;
            }
        }
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode[] fartherSon = {dummy, root};
        search(fartherSon, key);
        delete(fartherSon);
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
