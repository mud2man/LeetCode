/* Union and Find: O(n^2*logn) But LeetCode has O(n^2) solution
 */

import java.util.*;


public class Solution{
    private int find(int[] roots, int target){
        int farther = roots[target];
        int son = target;
        while(farther != son){
            // compression
            roots[son] = roots[farther];
            son = farther;
            farther = roots[farther];
        } 
        return farther;
    }
    
    public int findCircleNum(int[][] M) {
        int size = M.length;
        int[] roots = new int[size];
        
        for(int i = 0; i < size; ++i){
            roots[i] = i;
        }
        
        int circleNum = size;
        for(int y = 0; y < size; ++y){
            for(int x = y + 1; x < size; ++x){
                if(M[y][x] == 1){
                    int me = x;
                    int friend = y;
                    int myRoot = find(roots, me);
                    int friendRoot = find(roots, friend);
                    
                    // join
                    if(myRoot != friendRoot){
                        circleNum--;
                        roots[myRoot] = Math.min(myRoot, friendRoot);
                        roots[friendRoot] = roots[me];   
                    }
                }
            }
        }
        return circleNum;
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
