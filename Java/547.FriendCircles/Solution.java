/* DFS: Time:O(n^2), Space:O(n). There are two solutions: BFS, UnionFind
 * 1. Have a array visited to record if we meet the person
 * 2. Iterate all person if we never meet (visited[person] == false), and call dfs to visit all his friends
 * 3. In dfs, visit all the person's friend. If we didn't meet the friend visited[friend], the call dfs again
 * 4. Therefore, when we invoke dfs to visit the person, we will visit all his friends
 * 5. We only need to accumulate circle count on the first-level loop if visited[person] == false
 */

import java.util.*;


public class Solution{
    private void dfs(int person, boolean[] visited, int[][] matrix){
        if(visited[person] == true){
            return;
        }
       
        visited[person] = true;
        int[] friendList = matrix[person];
        for(int friend = 0; friend < friendList.length; ++friend){
            if(friendList[friend] == 1){
                dfs(friend, visited, matrix);
            }
        }
    }
    
    public int findCircleNum(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        
        int size = matrix.length;
        int circles = 0;
        boolean[] visited = new boolean[size];
        for(int person = 0; person < size; ++person){
            if(visited[person] == false){
                dfs(person, visited, matrix);
                circles++;
            }
        }
        return circles;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] matrix = {{1, 1, 0},
                          {1, 1, 0},
                          {0, 0, 1}};
        int circleNum;

        circleNum = sol.findCircleNum(matrix);
        
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("circleNum: " + circleNum);
    }
}
