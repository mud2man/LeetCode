/* DFS: Time:O(n), Space:O(n)
 * 1. Return "appleAndSteps" pair from DFS, and apply it to collect all apples
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    private int[] dfs(int root,  Map<Integer, List<Integer>> parent2Children, List<Boolean> hasApple){    
        int[] appleAndSteps =(hasApple.get(root))? new int[]{1, 0}: new int[]{0, 0};
        List<Integer> children = parent2Children.getOrDefault(root, new ArrayList<>());
        for(int child: children){
            int[] appleAndStepsFromChild = dfs(child, parent2Children, hasApple);
            appleAndSteps[0] +=(appleAndStepsFromChild[0] > 0)? appleAndStepsFromChild[0]: 0;
            appleAndSteps[1] +=(appleAndStepsFromChild[0] > 0)? appleAndStepsFromChild[1] + 2: 0;
        }
        return appleAndSteps;
    }
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> parent2Children = new HashMap<>();
        for(int[] edge: edges){
            parent2Children.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
        }
        int[] appleAndSteps = dfs(0, parent2Children, hasApple);
        return appleAndSteps[1];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        int n = 7;
        List<Boolean> hasApple = Arrays.asList(false, false, true, false, false, true, false);
        System.out.println("n:" + n);
        System.out.println("hasApple:" + hasApple);
        System.out.println("edges:");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("minimum time:" + sol.minTime(n, edges, hasApple));
    }
}
