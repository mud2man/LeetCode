/* Lowest common ancestor: Time:O(n), Space:O(n). LeetCode has 16-line solution 
 * 1. Construct map "child2Parent"
 * 2. Put the chain of regions to "region1Chain"
 * 3. Go upward from region2, and check if we hvae intersection between itr and region1Chain. Return itr if intersection found
 */

import java.util.*; // Stack

public class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> child2Parent = new HashMap<>();
        for(List<String> region: regions){
            String parent = region.get(0);
            for(int i = 1; i < region.size(); ++i){
                child2Parent.put(region.get(i), parent);
            }
        }
        
        Set<String> region1Chain = new HashSet<>();
        String itr = region1;
        while(itr != null){
            region1Chain.add(itr);
            itr = child2Parent.get(itr);
        }
        itr = region2;
        while(!region1Chain.contains(itr)){
            itr = child2Parent.get(itr);
        }
        return itr;
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
