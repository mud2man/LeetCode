/* Union Find: Time:O(n^2 * alpha(n^2)), Space:O(n^2)
 * 1. Have a 2D array "root" to identify the root of each element
 * 2. Set the root of boundry as depth * width, and store it in root[depth][0]
 * 3. Traverse the grid from left and top
 * 4. Find the top root and bottom root if the current element is connected with other element or boundry
 * 5. Accumulate count if topRoot == bottomRoot, because the condition of forming closed region is to form the cycle, which is topRoot == bottomRoot
 * 6. Union the current element if it connect with top elemet
 * 7. Union the current element if it connect with bottom element
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    private int find(int[][] root, int y, int x){
        if(root[y][x] == y * root[0].length + x){
            return root[y][x];
        }
        else{
            //compression
            root[y][x] = root[root[y][x] / root[0].length][root[y][x] % root[0].length];
            return find(root, root[y][x] / root[0].length, root[y][x] % root[0].length);
        }
    }

    public int regionsBySlashes(String[] grid) {
        int depth = grid.length;
        int width = grid[0].length();
        int[][] root = new int[depth + 1][width];
        int[][] rank = new int[depth + 1][width];
        root[depth][0] = depth * width;
        int count = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                root[y][x] = y * width + x;
                char c = grid[y].charAt(x);
                int topRoot = -1;
                int bottomRoot = -1;
                if(c == '/'){
                    if(y == 0 || x == width - 1){
                        topRoot = find(root, depth, 0);
                    }
                    else if(y > 0 && grid[y - 1].charAt(x) == '\\'){
                        topRoot = find(root, y - 1, x);
                    }
                    else if(y > 0 && x < width - 1 && grid[y - 1].charAt(x + 1) == '/'){
                        topRoot = find(root, y - 1, x + 1);
                    }
                    
                    if(y == depth - 1 || x == 0){
                        bottomRoot = find(root, depth, 0);
                    }
                    else if(x > 0 && grid[y].charAt(x - 1) == '\\'){
                        bottomRoot = find(root, y, x - 1);
                    }
                }
                else if(c == '\\'){
                    if(y == 0 || x == 0){
                        topRoot = find(root, depth, 0);
                    }
                    else if(y > 0 && grid[y - 1].charAt(x) == '/'){
                        topRoot = find(root, y - 1, x);
                    }
                    else if(y > 0 && x > 0 && grid[y - 1].charAt(x - 1) == '\\'){
                        topRoot = find(root, y - 1, x - 1);
                    }
                    else if(x > 0 && grid[y].charAt(x - 1) == '/'){
                        topRoot = find(root, y, x - 1);
                    }
                    
                    if(y == depth - 1 || x == width - 1){
                        bottomRoot = find(root, depth, 0);
                    }
                }

                count += (c != ' ' && topRoot != -1 && bottomRoot != -1 && topRoot == bottomRoot)? 1: 0;
                //union by rank
                if(c != ' ' && topRoot != -1){
                    if(rank[y][x] > rank[topRoot / width][topRoot % width]){
                        root[topRoot / width][topRoot % width] = root[y][x];
                    }
                    else if(rank[y][x] < rank[topRoot / width][topRoot % width]){
                        root[y][x] = root[topRoot / width][topRoot % width];
                    }
                    else{
                        root[topRoot / width][topRoot % width] = root[y][x];
                        rank[topRoot / width][topRoot % width]++;
                    }
                }
                if(c != ' ' && bottomRoot != -1){
                    bottomRoot = find(root, bottomRoot / width, bottomRoot % width);
                    int mayChangeY = root[y][x] / width;
                    int mayChangeX = root[y][x] % width;
                    if(rank[mayChangeY][mayChangeX] > rank[bottomRoot / width][bottomRoot % width]){
                        root[bottomRoot / width][bottomRoot % width] = root[mayChangeY][mayChangeX];
                    }
                    else if(rank[mayChangeY][mayChangeX] < rank[bottomRoot / width][bottomRoot % width]){
                        root[mayChangeY][mayChangeX] = root[bottomRoot / width][bottomRoot % width];
                    }
                    else{
                        root[mayChangeY][mayChangeX] = root[bottomRoot / width][bottomRoot % width];
                        rank[bottomRoot / width][bottomRoot % width]++;
                    }
                }
            }
        }
        return count + 1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] grid = {" /", "/ "};
        System.out.println("grid: ");
        for(String row: grid){
            System.out.println(row);
        }
        System.out.println("number of regions: " + sol.regionsBySlashes(grid));
    }
}
