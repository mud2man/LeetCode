/* TrieTree: Time:O(n), Space:O(n), n is characters#
 * 1. Have a trie tree indexed by the folder
 * 2. Implement insert and search
 */

import java.util.*;


public class FileSystem{
    private class TrieNode{
        int val;
        Map<String, TrieNode> child;
        TrieNode(int v){val = v; child = new HashMap<>();}
    }
    TrieNode root;
    
    public FileSystem() {
        root = new TrieNode(0);
    }
    
    public boolean createPath(String path, int value) {
        String[] stack = path.split("/");
        TrieNode node = root;
        for(int i = 1; i < stack.length; ++i){
            String folder = stack[i];
            if(node.child.containsKey(folder)){
                node = node.child.get(folder);
            }else{
                if(i == stack.length - 1){
                    node.child.put(folder, new TrieNode(value));
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public int get(String path) {
        String[] stack = path.split("/");
        TrieNode node = root;
        for(int i = 1; i < stack.length; ++i){
            String folder = stack[i];
            if(node.child.containsKey(folder)){
                node = node.child.get(folder);
            }else{
                return -1;
            }
        }
        return node.val;
    }
  
    public static void main(String[] args){
        FileSystem sol = new FileSystem();
        String path = "/a";
        int val = 1;
        System.out.println(String.format("createPath(%s, %d):%b", path, val, sol.createPath(path, val)));
        System.out.println(String.format("get(%s):%d", path, sol.get(path)));
    }
}
