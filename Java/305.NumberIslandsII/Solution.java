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
        Map<Integer, Integer> child2Parent = new HashMap<>();
        Map<Integer, Integer> child2rank = new HashMap<>();
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int number = 0;
        List<Integer> ret = new ArrayList<>();
        
        for(int[] position: positions){
            int y = position[0];
            int x = position[1];
            int pos = y * n + x;
            islands.add(pos);
            Set<Integer> roots = new HashSet<>();
            int root = -1;
            int maxRank = -1;
            for(int[] dir: dirs){
                int nextY = y + dir[0];
                int nextX = x + dir[1];
                int nextPos = nextY * n + nextX;
                if(nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || !islands.contains(nextPos)){
                    continue;
                }
                //find
                int r = find(child2Parent, nextPos);
                if(child2rank.get(r) > maxRank){
                    maxRank = child2rank.get(r);
                    root = r;
                }
                roots.add(r);
            }
            
            //union by rank
            for(int r: roots){
                if(r == root){
                    continue;
                }
            
                if(maxRank > child2rank.get(r)){
                    child2Parent.put(r, root);
                }
                else{
                    child2Parent.put(r, root);
                    child2rank.put(root, ++maxRank);
                }
            }
            
            if(root != -1){
                child2Parent.put(pos, root);
                if(maxRank == 0){
                    child2rank.put(root, ++maxRank);
                }
            }
            else{
                child2Parent.put(pos, pos);
                child2rank.put(pos, 0);
            }
            number -= (roots.size() - 1);
            ret.add(number);
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
