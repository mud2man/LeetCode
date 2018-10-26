/* Preorder: Time:O(logn), Space:O(1). LeetCode has an one shot solution
 * 1. In serialize, find the 'n' first, and append node's val in preOrder
 * 2. In deserialize, take the datas[idx[0]] as root's value, and increase idx[0]
 */

import java.util.*;

public class Codec {
    private int getN(Node root){
        if(root.children.size() == 0){
            return 0;
        }
        int n = root.children.size();
        for(int i = 0; i < root.children.size(); ++i){
            n = Math.max(n, getN(root.children.get(i)));
        }
        return n;
    }
    
    private void serialize(Node root, int n, StringBuilder data) {
        data.append(Integer.toString(root.val) + "_");
        for(int i = 0; i < n; ++i){
            if(i < root.children.size()){
                serialize(root.children.get(i), n, data);
            }
            else{
               data.append("#_"); 
            }
        }
    }
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return "null";
        }
        
        int n = getN(root);
        StringBuilder data = new StringBuilder("");
        serialize(root, n, data);
        return Integer.toString(n) + "_" + data.toString();
    }
    
    private Node deserialize(String[] datas, int[] idx, int n){
        String data = datas[idx[0]++];
        if(data.equals("#")){
            return null;
        }
        else{
            Node root = new Node(Integer.valueOf(data), new ArrayList<>());
            for(int i = 0; i < n; ++i){
                Node child = deserialize(datas, idx, n);
                if(child != null){
                   root.children.add(child);
                }
            }
            return root;
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals("null")){
            return null;
        }
        
        String[] datas = data.split("_");
        int n = Integer.valueOf(datas[0]);
        int[] idx = {1};
        return deserialize(datas, idx, n);
    }
}
