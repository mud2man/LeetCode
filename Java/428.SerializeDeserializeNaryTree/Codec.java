/* Preorder: Time:O(n), Space:O(1)
 * 1. In serialize, encode as "children size" + "val" + "," in preorder
 * 2. In deserialize, get the "val" and children size "n", and passed a global variable "idx"
 * 3. Repeat n times for adding child
 * 4. The method can be proved by induction from forming a leave first
 */

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return "null";
        }
        int num = root.children.size();
        String data = Integer.toString(num) + "_" + root.val + "_";
        for(int i = 0; i < num; ++i){
            data += serialize(root.children.get(i));
        }
        return data;
    }
    
    private Node deserialize(String[] datas, int[] idx){
        int num = Integer.valueOf(datas[idx[0]++]);
        int val = Integer.valueOf(datas[idx[0]++]);
        Node root = new Node(val, new ArrayList<>());
        for(int i = 0; i < num; ++i){
            root.children.add(deserialize(datas, idx));
        }
        return root;
    }
        
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals("null")){
            return null;
        }
        String[] datas = data.split("_");
        int[] idx = {0};
        return deserialize(datas, idx);
    }
       
    public static void main(String[] args){
        Codec sol = new Codec();
        Node root = new Node(1, new ArrayList<>()); 
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        String data = sol.serialize(root);
        System.out.println("data: " + data);
        System.out.println("tree: " + sol.deserialize(data));
    }
}
