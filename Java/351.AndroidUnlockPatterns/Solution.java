/* Backtracking: O(#subpath)
 * 1. Have class NeighborList for every position, 
 * 2. neighbor = the direct accessable neighbor, 
 * 3. crossNeighbor = the indirect neighbor, e.g. one the cross neighbor of 1 is 3, we need to visit 2 first
 * 4, Have an array visited to record the visited position
 * 5. Use backtracker to visit the position 1, 2, 5, and store the result to count1, count2, and count5
 * 6. return (count1 * 4) + (count2 * 4) + count5;
 */

import java.util.*;
public class Solution{
        private class NeighborList{
        int[] neighbor;
        int[][] crossNeighbor;
    }
    
    private void backtracker(int position, int length, boolean[] visited, NeighborList[] neighborLists, int m, int n, int[] count){
        if((length + 1) > n){
            return;
        }
        
        //push
        visited[position] = true;
                                                                                                                      
        length++;                                                                             
        if(length >= m && length <= n){
            count[0]++;
        }
        
        int[] neighbor = neighborLists[position].neighbor;
        for(int nr: neighbor){
            if(visited[nr] == false){
                backtracker(nr, length, visited, neighborLists, m, n, count);
            }
        }
                                                                                                                      
        int[][] crossNeighbor = neighborLists[position].crossNeighbor;
        for(int[] cn: crossNeighbor){
            if(visited[cn[0]] == false && visited[cn[1]] == true){
                backtracker(cn[0], length, visited, neighborLists, m, n, count);
            }
        }
                                                                                 
        //pop
        visited[position] = false;
    }
    
    public int numberOfPatterns(int m, int n) {
        NeighborList[] neighborLists = new NeighborList[10];
        
        for(int i = 1; i < 10; ++i){
            neighborLists[i] = new NeighborList();
        }
        
        neighborLists[1].neighbor = new int[]{2, 4, 5, 6, 8};
        neighborLists[1].crossNeighbor = new int[][]{{3, 2}, {7, 4}, {9, 5}};
        neighborLists[2].neighbor = new int[]{1, 3, 4, 5, 6, 7, 9};
        neighborLists[2].crossNeighbor = new int[][]{{8, 5}};
        neighborLists[3].neighbor = new int[]{2, 4, 5, 6, 8};
        neighborLists[3].crossNeighbor = new int[][]{{1, 2}, {7, 5}, {9, 6}};
        neighborLists[4].neighbor = new int[]{1, 2, 3, 5, 7, 8, 9};
        neighborLists[4].crossNeighbor = new int[][]{{6, 5}};
        neighborLists[5].neighbor = new int[]{1, 2, 3, 4, 6, 7, 8, 9};
        neighborLists[5].crossNeighbor = new int[][]{};
        neighborLists[6].neighbor = new int[]{1, 2, 3, 5, 7, 8, 9};
        neighborLists[6].crossNeighbor = new int[][]{{4, 5}};
        neighborLists[7].neighbor = new int[]{2, 4, 5, 6, 8};
        neighborLists[7].crossNeighbor = new int[][]{{1, 4}, {3, 5}, {9, 8}};
        neighborLists[8].neighbor = new int[]{1, 3, 4, 5, 6, 7, 9};
        neighborLists[8].crossNeighbor = new int[][]{{2, 5}};
        neighborLists[9].neighbor = new int[]{2, 4, 5, 6, 8};
        neighborLists[9].crossNeighbor = new int[][]{{1, 5}, {3, 6}, {7, 8}};
        
        int[] count1 = new int[1];
        int[] count2 = new int[1];
        int[] count5 = new int[1];
        boolean[] visited = new boolean[10];
        
        backtracker(1, 0, visited, neighborLists, m, n, count1);
        backtracker(2, 0, visited, neighborLists, m, n, count2);
        backtracker(5, 0, visited, neighborLists, m, n, count5);
        
        return (count1[0] * 4) + (count2[0] * 4) + count5[0];
    }

    public static void main(String[] args){
        Solution sol;
        int n;
        int m;

        sol = new Solution();
        m = 1;
        n = 1;

        System.out.println("#path: " + sol.numberOfPatterns(m, n) + ", min:" + m +", max:" + n);
    }
}
