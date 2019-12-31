/* HashMap + BFS: Time:O(n^2*m), Space:O(n^2*m), where n is the length of "bottom", m is the length of "allowed"
 * 1. Have a list "bottomList" to store all the possible characters on the current level
 * 2. Then move up and generate possible combinations for next level
 * 3. If it terminates during the process, then return false
 */

import java.util.*;

public class Solution{
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        HashMap<String, Set<String>> builderMap = new HashMap<String, Set<String>>();
        for(String builder: allowed){
            String key = builder.substring(0, 2);
            String value = builder.substring(2);
            builderMap.putIfAbsent(key, new HashSet<String>());
            builderMap.get(key).add(value);
        }
        
        List<Set<String>> bottomList = new ArrayList<Set<String>>();
        for(int i = 0; i < bottom.length(); ++i){
            Set<String> set = new HashSet<String>();
            set.add(bottom.substring(i, i + 1));
            bottomList.add(set);
        }
        
        while(bottomList.size() > 1){
            List<Set<String>> nextBottomList = new ArrayList<Set<String>>();
            for(int i = 0; i < (bottomList.size() - 1); ++i){
                Set<String> leftSet = bottomList.get(i);
                Set<String> rightSet = bottomList.get(i + 1);
                Set<String> result = new HashSet<String>();
                for(String leftChar: leftSet){
                    for(String rightChar: rightSet){
                        String str = leftChar + rightChar;
                        if(builderMap.containsKey(str)){
                            result.addAll(builderMap.get(str));
                        }
                    }
                }
                if(result.size() == 0){
                    return false;
                }
                nextBottomList.add(result);
            }
            bottomList = nextBottomList;
        }
        return true;
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
