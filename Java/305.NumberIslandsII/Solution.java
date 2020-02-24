/* Union and find: Time:O(k), Space:O(k)
 * 1. Have a map child2Parent to store the child-parent mapping, and child2rank to store rank
 * 2. Traverse "positions" and apply union and find (path compression and union by rank) to update islands count
 */

import java.util.*;

public class Solution{
   private int find(Map<Integer, Integer> child2Parent, int child){
        int parent = child2Parent.get(child);
        if(parent == child){
            return child;
        }
        //compression
        int grandParent = child2Parent.get(parent);
        child2Parent.put(child, grandParent);
        return find(child2Parent, grandParent);
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        Set<Integer> islands = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> child2Parent = new HashMap<>();
        Map<Integer, Integer> child2rank = new HashMap<>();
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<Integer> ret = new ArrayList<>();
        for(int[] position: positions){
            int y = position[0];
            int x = position[1];
            int pos = y * n + x;
            if(visited.contains(pos)){
                ret.add(islands.size());
                continue;
            }
            visited.add(pos);
            child2Parent.put(pos, pos);
            child2rank.put(pos, 1);
            Set<Integer> roots = new HashSet<>();
            int rootWithMaxRank = -1;
            int maxRank = -1;
            for(int[] dir: dirs){
                int nextY = y + dir[0];
                int nextX = x + dir[1];
                int nextPos = nextY * n + nextX;
                if(nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || !visited.contains(nextPos)){
                    continue;
                }
                //find
                int root = find(child2Parent, nextPos);
                roots.add(root);
                if(child2rank.get(root) > maxRank){
                    maxRank = child2rank.get(root);
                    rootWithMaxRank = root;
                }
            }
            islands.add(rootWithMaxRank);
            
            //union by rank
            for(int root: roots){
                if(root == rootWithMaxRank){
                    continue;
                }
                islands.remove(child2Parent.get(root));
                child2Parent.put(root, rootWithMaxRank);
                child2rank.put(rootWithMaxRank, (maxRank == child2rank.get(root))? ++maxRank: maxRank);
            }
            ret.add(islands.size());
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        int m = 3;
        int n = 3;
        
        System.out.println("m: " + m);
        System.out.println("n: " + n);
        for(int[] position: positions){
            System.out.println(Arrays.toString(position));
        }
        System.out.println("island number: " + sol.numIslands2(m, n, positions));
    }
}
