/* BST: Time:O(nlogn), Space:O(n). However LeetCode has a shorter solution 
 * 1. Sort nums, and take the median everytime to insert the tree. And we have the node contains leftCount, selfCount
 * 2. Because the tree has no duplicates, so it can be guarantee a balanced tree
 * 3. Then traverse nums, and get the according tree node. We need to accumulate lessCount when we go to right branch
 * 4. If the target node's selfCount is zero, we need to call remove
 * 5. When doing remove, we take the leftest node of the deleteNode's right child, and replace with the deleteNode
 * 6. Then call remove() again given the next delete node = the leftest node of the deleteNode's right child
 */

import java.util.*;

public class Solution{
    private class TreeNode{
        int val;
        int leftCount;
        int selfCount;
        TreeNode left;
        TreeNode right;
        TreeNode farther;
        TreeNode(int v, int l, int s, TreeNode f){val = v; leftCount = l; selfCount = s; farther = f;}
    }
    
    private void helper(TreeNode node, TreeNode farther, int val){
        if(node == null){
            TreeNode newNode = new TreeNode(val, 0, 1, farther);
            if(farther.val > val){
                farther.left = newNode;
            }
            else{
                farther.right = newNode;
            }
        }
        else if(node.val == val){
            node.selfCount++;
        }
        else if(node.val > val){
            node.leftCount++;
            helper(node.left, node, val);
        }
        else{
            helper(node.right, node, val);
        }
    }
    
    private TreeNode insert(TreeNode root, List<Integer> nums, int l, int r){
        if(l > r){
            return null;
        }
        int mid = (l + r) / 2;
        int val = nums.get(mid);
        
        if(root == null){
            root = new TreeNode(val, 0, 1, null);
        }
        else{
            helper(root, null, val);
        }
        insert(root, nums, l, mid - 1);
        insert(root, nums, mid + 1, r);
        return root;
    }
    
    private TreeNode search(TreeNode root, int key, int[] lessCount){
        if(root.val == key){
            root.selfCount--;
            lessCount[0] += root.leftCount;
            if(root.selfCount == 0){
                return root;
            }
            else{
                return null;
            }
        }
        else if(root.val > key){
            root.leftCount--;
            return search(root.left, key, lessCount);
        }
        else{
            lessCount[0] += (root.selfCount + root.leftCount);
            return search(root.right, key, lessCount);
        }
    }
    
    private void remove(TreeNode deleteNode){
        if(deleteNode == null){
            return;
        }
        
        if(deleteNode.right == null){
            if(deleteNode.left != null){
                deleteNode.left.farther = deleteNode.farther;
            }
            if(deleteNode.farther != null){
                if(deleteNode.farther.left == deleteNode){
                    deleteNode.farther.left = deleteNode.left;
                }
                else{
                    deleteNode.farther.right = deleteNode.left;
                }
            }
        }
        else{
            TreeNode nextDeleteNode = deleteNode.right;
            while(nextDeleteNode.left != null){
                nextDeleteNode = nextDeleteNode.left;
            }
            deleteNode.val = nextDeleteNode.val;
            deleteNode.selfCount = nextDeleteNode.selfCount;
            
            TreeNode node = deleteNode.right;
            while(node.left != null){
                node.leftCount -= nextDeleteNode.selfCount;
                node = node.left;
            }
            remove(nextDeleteNode);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0){
            return new ArrayList<>();
        }
        
        List<Integer> numList = new ArrayList<>();
        for(int num: nums){
            numList.add(num);
        }
        Collections.sort(numList);
        
        TreeNode root = insert(null, numList, 0, numList.size() - 1);
            
        List<Integer> counts = new ArrayList<>();
        for(int num: nums){
            int[] lessCount = {0};
            TreeNode deleteNode = search(root, num, lessCount);
            counts.add(lessCount[0]);
            remove(deleteNode);
        }
        return counts;
    }

    public static void main(String[] args){
        int[] nums = {5, 2, 6, 1};
        Solution sol = new Solution();
        
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("count: " + sol.countSmaller(nums));
    }
}
