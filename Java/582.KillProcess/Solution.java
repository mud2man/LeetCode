/* HashMap: Time:O(n), Space:O(n)
 * 1. Build a adjacentList, and apply DFS to find the subtree
 */         

import java.util.*;

public class Solution {
    private void dfs(int pid, Map<Integer, List<Integer>> adjacentList, List<Integer> killList){
        killList.add(pid);
        
        if(!adjacentList.containsKey(pid)){
            return;
        }
        
        for(int child: adjacentList.get(pid)){
            dfs(child, adjacentList, killList);
        }
    }
    
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> adjacentList = new HashMap<Integer, List<Integer>>();
        Iterator<Integer> pidItr = pid.iterator();
        Iterator<Integer> ppidItr = ppid.iterator();
        while(pidItr.hasNext()){
            int child = pidItr.next();
            int parent = ppidItr.next();
            adjacentList.putIfAbsent(parent, new ArrayList<Integer>());
            adjacentList.get(parent).add(child);
        }
        List<Integer> killList = new ArrayList<Integer>();
        dfs(kill, adjacentList, killList);
        return killList;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        List<Integer> pid = new ArrayList<Integer>(Arrays.asList(1, 3, 10, 5)); 
        List<Integer> ppid = new ArrayList<Integer>(Arrays.asList(3, 0, 5, 3)); 
        int kill = 5;
        System.out.println("pid: " + pid);
        System.out.println("ppid: " + ppid);
        System.out.println("kill: " + kill);
        System.out.println("kill pid: " + sol.killProcess(pid, ppid, kill));
    }
}
