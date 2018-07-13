/* Union and find: Time:O(klog(m*n)), Space:O(k). LeetCode also apply union by rank, you should learn it
 * 1. Have a map child2Parent to store the child-parent mapping 
 * 2. Traverse "positions" and apply union and find to update islands count
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
            for(int[] dir: dirs){
                int nextY = y + dir[0];
                int nextX = x + dir[1];
                int nextPos = nextY * n + nextX;
                if(nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || !islands.contains(nextPos)){
                    continue;
                }
                //find
                root = find(child2Parent, nextPos);
                roots.add(root);
            }
            
            //union
            for(int r: roots){
                child2Parent.put(r, root);
            }
            
            if(root != -1){
                child2Parent.put(pos, root);
            }
            else{
                child2Parent.put(pos, pos);
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
