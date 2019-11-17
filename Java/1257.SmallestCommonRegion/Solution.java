/* Lowest common ancestor: Time:O(n), Space:O(n) 
 * 1. Construct a N-Tree given region list
 * 2. Run getLca as long as the current node has no parent(root)
 */

import java.util.*; // Stack

public class Solution {
    private class TreeNode{
        String name;
        Set<TreeNode> children;
        TreeNode(String n){name = n; children = new HashSet<>();}
    }
    
    private TreeNode getLca(TreeNode root, TreeNode child1, TreeNode child2){
        if(root == null){
            return null;
        }else if(root == child1 || root == child2){
            return root;
        }else{
            List<TreeNode> nodes = new ArrayList<>();
            for(TreeNode child: root.children){
                TreeNode node = getLca(child, child1, child2);
                if(node != null){
                    nodes.add(node);
                }
            }
            return (nodes.size() == 2)? root: (nodes.size() == 1)? nodes.get(0): null;
        }
    }
    
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, TreeNode> name2Node = new HashMap<>();
        Set<String> childrenNames = new HashSet<>();
        for(List<String> list: regions){
            String parentName = list.get(0);
            TreeNode parent = name2Node.getOrDefault(parentName, new TreeNode(parentName));
            name2Node.put(parentName, parent);
            for(int i = 1; i < list.size(); ++i){
                String childName = list.get(i);
                TreeNode child = name2Node.getOrDefault(childName, new TreeNode(childName));
                name2Node.put(childName, child);
                parent.children.add(child);
                childrenNames.add(childName);
            }
        }
        for(Map.Entry<String, TreeNode> entry: name2Node.entrySet()){
            String name = entry.getKey();
            if(!childrenNames.contains(name)){
                TreeNode lca = getLca(entry.getValue(), name2Node.get(region1), name2Node.get(region2));
                if(lca != null){
                    return lca.name;
                }
            }
        }
        return null;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<String>> regions = new ArrayList<>();
        regions.add(Arrays.asList("Earth", "North America", "South America"));
        regions.add(Arrays.asList("North America", "United States", "Canada"));
        regions.add(Arrays.asList("United States", "New York", "Boston"));
        regions.add(Arrays.asList("Canada", "Ontario", "Quebec"));
        regions.add(Arrays.asList("South America", "Brazil"));
        String region1 = "Quebec";
        String region2 = "New York";
        System.out.println("regions:" + regions);
        System.out.println("region1:" + region1 + ", region2:" + region2);
        System.out.println("smallest common region:" + sol.findSmallestRegion(regions, region1, region2));
    }
}
