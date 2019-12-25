/* DFS: Time:O(n^2), Space:O(n). We can try BFS next time
 * 1. Hava "mykey" to stroe all my visted keys
 * 2. We mark status[box] to -1 to present the box was visited
 * 3. We put the unvisited box to "nextBoxes", in case we find the key later
 * 4. We stop call dfs if we hvae no key for all nextBoxes
 */

import java.util.*;


/* Definition for binary tree */
public class Solution {
    private int dfs(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, List<Integer> boxes, Set<Integer> mykey){
        int count = 0;
        List<Integer> nextBoxes = new LinkedList<>();
        for(int box: boxes){
            if(status[box] == -1){
                continue;
            }
            if(status[box] == 0 && !mykey.contains(box)){
                nextBoxes.add(box);
                continue;
            }
            status[box] = -1;
            count += candies[box];
            candies[box] = 0;
            for(int key: keys[box]){
                mykey.add(key); 
            }
            for(int containedBox: containedBoxes[box]){
                if(status[containedBox] != -1){
                    nextBoxes.add(containedBox);
                }
            }
        }
        
        for(int nextBox: nextBoxes){
            if(status[nextBox] == 1 || (status[nextBox] == 0 && mykey.contains(nextBox))){
                count += dfs(status, candies, keys, containedBoxes, nextBoxes, mykey);
                break;
            }
        }
        return count;
    }
    
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        List<Integer> boxes = new LinkedList<>();
        for(int initialBoxe: initialBoxes){
            boxes.add(initialBoxe);   
        }
        return dfs(status, candies, keys, containedBoxes, boxes, new HashSet<>());
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] status = {1, 0, 1, 0};
        int[] candies = {7, 5, 4, 100};
        int[][] keys = {{}, {}, {1}, {}};
        int[][] containedBoxes = {{1, 2}, {3}, {}, {}};
        int[] initialBoxes = {0};
        System.out.println("status:" + Arrays.toString(status));
        System.out.println("candies:" + Arrays.toString(candies));
        System.out.println("keys:");
        for(int[] key: keys){
            System.out.print(Arrays.toString(key) + ",");
        }
        System.out.println("\ncontainedBoxes:");
        for(int[] containedBox: containedBoxes){
            System.out.print(Arrays.toString(containedBox) + ",");
        }
        System.out.println("\ninitialBoxes:" + Arrays.toString(initialBoxes));
        System.out.println("maximum candies:" + sol.maxCandies(status, candies, keys, containedBoxes, initialBoxes));
	}
}
