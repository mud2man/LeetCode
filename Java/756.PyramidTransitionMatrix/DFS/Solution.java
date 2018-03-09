/* HashMap + DFS: Time:O(n^2*m), Space:O(n^2*m), where n is the length of "bottom", m is the length of "allowed"
 * 1. Use DFS to merge the (bottom.charAt(index), bottom.charAt(index + 1)) every time
 * 2. In every trial, try k times where (bottom.charAt(index), bottom.charAt(index + 1)) has k different merged result
 * 3. Break when return true
 *
 * ex: bottom = "XYZ", allowed = {"XYD", "YZE", "DEA", "FFF"}
 * ************ layer1 ************
 * time[0]: "XYZ" => DYZ, index = 0
 * time[1]: "DYZ" => DE, index = 1

 * ************ layer2 ************
 * time[2]: "DE" => A, index = 0
 */

import java.util.*;

public class Solution{
    private boolean dfs(String bottom, int index, HashMap<String, Set<String>> builderMap){
        if(bottom.length() == 1){
            return true;
        }
        
        String key = bottom.substring(index, index + 2);
        if(!builderMap.containsKey(key)){
            return false;
        }
        
        Set<String> candidates = builderMap.get(key);
        for(String candidate: candidates){
            String nextBottom = "";
            boolean ret = false;
            if(index == (bottom.length() - 2)){
                nextBottom = bottom.substring(0, index) + candidate;
                ret = dfs(nextBottom, 0, builderMap);
            }
            else{
                nextBottom = bottom.substring(0, index) + candidate + bottom.substring(index + 1);
                ret = dfs(nextBottom, index + 1, builderMap);
            }
            
            if(ret == true){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        HashMap<String, Set<String>> builderMap = new HashMap<String, Set<String>>();
        for(String builder: allowed){
            String key = builder.substring(0, 2);
            String value = builder.substring(2);
            builderMap.putIfAbsent(key, new HashSet<String>());
            builderMap.get(key).add(value);
        }
        
        return dfs(bottom, 0, builderMap);
    }

    public static void main(String[] args){
        Solution sol;
        List<String> allowed = new ArrayList<String>(Arrays.asList("XYD", "YZE", "DEA", "FFF"));
        String bottom = "XYZ";

        sol = new Solution();
        System.out.println("allowed: " + allowed);
        System.out.println("bottom: " + bottom);
        System.out.println("can be pyramid: " + sol.pyramidTransition(bottom, allowed));
    }
}
