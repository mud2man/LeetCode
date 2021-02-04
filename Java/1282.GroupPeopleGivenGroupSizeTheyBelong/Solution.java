/* Map: Time:O(n), Space:O(n)
 * 1. Have map "size2Group" to store size-group map, and put the group to groups after size2Group.get(groupSizes[i]).size() == groupSizes[i]
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> size2Group = new HashMap<>();
        List<List<Integer>> groups = new ArrayList<>();
        for(int i = 0; i < groupSizes.length; i++){
            size2Group.computeIfAbsent(groupSizes[i], key -> new ArrayList<>()).add(i);
            if(size2Group.get(groupSizes[i]).size() == groupSizes[i]){
                groups.add(size2Group.get(groupSizes[i]));
                size2Group.remove(groupSizes[i]);
            }
        }
        return groups;
    }
  
    public static void main(String[] args){
        int[] groupSizes = {3, 3, 3, 3, 3, 1, 3};
        Solution sol = new Solution();
        System.out.println("groupSizes:" + Arrays.toString(groupSizes));
        System.out.println("groups:" + sol.groupThePeople(groupSizes));
    }
}
