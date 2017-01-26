/* BFS: O(m*n)
 * 1. Push the border of ocean into queue
 * 2. BFS the border of Pacific and Atlantic respectively
 * 3. Return the intersection
 */

import java.util.*;

public class Solution{
    private LinkedList<Integer> queue;
    private HashSet<Integer> pacList;
    private HashSet<Integer> atlList;
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        HashSet<Integer> list;
        int x, y, i, j, k;
        List<int[]> topList;
        int[] xy;
        int[] nextPoss;
        int nextPos;
        int currPos;
        int size;
        int yOffset;
        int xOffset;
        
        pacList = new HashSet<Integer>();
        atlList = new HashSet<Integer>();
        queue = new LinkedList<Integer>();
        topList = new ArrayList<int[]>();
        nextPoss = new int[4];
        
        if(matrix.length == 0){
            return topList;
        }
        
        for(k = 0; k < 2; ++k){
            /* climb from Pacific */
            if(k == 0 ){
                list = pacList;
                for(x = 0; x < matrix[0].length; ++x){
                    currPos = x;
                    queue.add(currPos);
                    list.add(currPos);
                }
                for(y = 1; y < matrix.length; ++y){
                    currPos = 150 * y;
                    queue.add(currPos);
                    list.add(currPos);
                }
            }
            /* climb from Atlantic */
            else{
                list = atlList;
                for(x = 0; x < matrix[0].length; ++x){
                    currPos = x + 150 * (matrix.length - 1);
                    queue.add(currPos);
                    list.add(currPos);
                }
                for(y = 0; y < (matrix.length - 1); ++y){
                    currPos = 150 * y + (matrix[0].length - 1);
                    queue.add(currPos);
                    list.add(currPos);
                }
            }
        
            /* BFS */
            while(queue.size() > 0){
                size = queue.size();
                for(i = 0; i < size; ++i){
                    currPos = queue.poll();
                    y = currPos / 150;
                    x = currPos % 150;
                    nextPoss[0] = (y > 0)? 150 * (y - 1) + x: -1; //up
                    nextPoss[1] = (x > 0)? 150 * y  + (x - 1): -1; //left
                    nextPoss[2] = (y < (matrix.length - 1))? 150 * (y + 1) + x: -1; //down
                    nextPoss[3] = (x < (matrix[0].length - 1))? 150 * y + (x + 1): -1; //right
                    for(j = 0; j < 4; ++j){
                        nextPos = nextPoss[j];
                        if((nextPos != -1) && (!list.contains(nextPos)) && 
                           (matrix[currPos / 150][currPos % 150] <= matrix[nextPos / 150][nextPos % 150])){
                            list.add(nextPos);
                            queue.add(nextPos);
                        }
                    }
                }//for
            }//while
        }//for
        
        for(int pos: pacList){
            if(atlList.contains(pos)){
                y = pos / 150;
                x = pos % 150;
                xy = new int[2];
                xy[0] = y;
                xy[1] = x;
                topList.add(xy);
            }
        }

        return topList;
    }

    public static void main(String[] args){
        Solution sol;
        List<int[]> topList;
        int[][] matrix = {{1, 2, 2, 3, 5},
                          {3, 2, 3, 4, 4},
                          {2, 4, 5, 3, 1},
                          {6, 7, 1, 4, 5},
                          {5, 1, 1, 2, 4}};
        
        sol = new Solution();
        topList = sol.pacificAtlantic(matrix);
        
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        System.out.println("topList: ");
        for(int[] pos: topList){
            System.out.print(Arrays.toString(pos) + ", ");
        }
        System.out.println("");
    }
}
