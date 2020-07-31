/* DFS: Time:O(n), Space:O(n)
 * 1. Use dfs to traverse tree and record the longest path starting at node and longest path passing through root 
 */     

import java.util.*; // Stack

public class Solution {
    class Node {
        public int val;
        public List<Node> children;
        
        public Node() {
            children = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }
        
        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // ret = {longest path starting at root, longest path passing through root}
    private int[] dfs(Node root){
        int[] ret = new int[2];
        if(root == null){
            return ret;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(Node child: root.children){
            int[] childRet = dfs(child);
            ret[0] = Math.max(ret[0], childRet[0] + 1);
            ret[1] = Math.max(ret[1], childRet[1]);
            if(minHeap.size() < 2){
                minHeap.add(childRet[0]);
            }else{
                if(minHeap.peek() < childRet[0]){
                    minHeap.poll();
                    minHeap.add(childRet[0]);
                }
            }
        }
        if(minHeap.size() == 2){
            int lengthThroughRoot = minHeap.poll() + minHeap.poll() + 2;
            ret[1] = Math.max(ret[1], lengthThroughRoot);
        }
        return ret;
    }
    
    public int diameter(Node root) {
        int[] ret = dfs(root);
        return Math.max(ret[0], ret[1]);
    }
 
    public static void main(String[] args){
        System.out.println("no example");
    }
}
