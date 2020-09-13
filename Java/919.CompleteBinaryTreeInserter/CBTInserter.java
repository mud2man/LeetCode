/* Tree: Time:O(n), Space:O(1)
 * 1. Use nodeList to store all the tree node, where the paraent of nodeList.get(i) is nodeList.get(i / 2)
 */     

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class CBTInserter {
    List<TreeNode> nodeList;
    public CBTInserter(TreeNode root) {
        nodeList = new ArrayList<>();
        nodeList.add(null);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode front = queue.pollFirst();
                nodeList.add(front);
                if(front.left != null){
                    queue.add(front.left);
                }
                if(front.right != null){
                    queue.add(front.right);
                }
            }
        }
    }
    
    public int insert(int v) {
        TreeNode child = new TreeNode(v);
        int childIdx = nodeList.size();
        nodeList.add(child);
        int parentIdx = childIdx / 2;
        TreeNode parent = nodeList.get(parentIdx);
        if(childIdx % 2 == 0){
            parent.left = child;
        }else{
            parent.right = child;
        }
        return parent.val;
    }
    
    public TreeNode get_root() {
        return nodeList.get(1);
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        CBTInserter tree = new CBTInserter(root);
        System.out.println("insert:" + tree.insert(2));
        System.out.println("get_root:" + tree.get_root().val);
    }
}
