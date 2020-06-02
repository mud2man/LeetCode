/* TrieTree: Time:O(n), Space:O(n), where n is character#
 * 1. Build the trie tree and we discard children when we hit last node. Otherwise, make the last-level node's isLast = true
 * 2. Vist tree via dfs, and put the path into firstLevelFolders if the node is a leaf
 */     


import java.util.*; // Stack

public class Solution {
    class TrieNode{
        boolean isLast;
        String name;
        Map<String, TrieNode> children;
        TrieNode(String s){name = s; children = new HashMap<>(); isLast = false;}
    }
    TrieNode root = new TrieNode("");
    
    private void insert(String path){
        String[] levels = path.split("/");
        TrieNode node = root;
        for(int i = 1; i < levels.length; ++i){
            String level = levels[i];
            if(node.isLast){
                return;
            }
            node.children.putIfAbsent(level, new TrieNode(level));
            node = node.children.get(level);
        }
        node.isLast = true;
        node.children = null;
    }
    
    private void dfs(TrieNode node, String path, List<String> firstLevelFolders){
        if(node.isLast){
            firstLevelFolders.add(path);
            return;
        }
        for(Map.Entry<String, TrieNode>entry: node.children.entrySet()){
            String nextFolderName = entry.getKey();
            TrieNode next = entry.getValue();
            dfs(next, path + "/" + nextFolderName, firstLevelFolders);
        }
    }
    
    public List<String> removeSubfolders(String[] folder) {
        for(String path: folder){
            insert(path);
        }
        List<String> firstLevelFolders = new ArrayList<>();
        dfs(root, "", firstLevelFolders);
        return firstLevelFolders;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] folder = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
        System.out.println("folder:" + Arrays.toString(folder));
        System.out.println("after removing:" + sol.removeSubfolders(folder));
    }
}
