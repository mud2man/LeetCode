/* O(n^2)
 * 1. Have a tuple(left, right, height) to present a square 
 * 2. Pick up a square from positions[i] form left to right
 * 3. Check if the picked squrae overlap with all the square from 0-th to (i - 1)-th
 * 4. If overlapped, uupdate the height with "newSquare[2] = Math.max(oldSquare[2] + height, newSquare[2])"
 * 5. Append the square into squares, and upadte maxHeight = Math.max(maxHeight, newSquare[2])
 */          

import java.util.*; // Stack

public class Solution {
    private boolean isOverlap(int[] square1, int[] square2){
        int[] left;
        int[] right;
        
        if(square1[0] < square2[0]){
            left = square1;
            right = square2;
        }
        else{
            left = square2;
            right = square1;
        }
        
        return (left[1] > right[0]);
    }
    
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> heights = new ArrayList<Integer>();
        List<int[]> squares = new ArrayList<int[]>();
        
        int maxHeight = 0;
        for(int[] position: positions){
            int height = position[1];
            int[] newSquare = new int[]{position[0], position[0] + position[1], height};

            for(int[] oldSquare: squares){
                if(isOverlap(oldSquare, newSquare)){
                    newSquare[2] = Math.max(oldSquare[2] + height, newSquare[2]);
                }
            }
            maxHeight = Math.max(maxHeight, newSquare[2]);
            squares.add(newSquare);
            heights.add(maxHeight);
        }
        
        return heights;
    }
  
    public static void main(String[] args){
        Solution sol;
        int[][] positions = {{1, 2}, {2, 3}, {6, 1}};

        sol = new Solution();
        
        System.out.println("positions: ");
        for(int[] row: positions){
            System.out.println(Arrays.toString(row));
        }

        List<Integer> heights = sol.fallingSquares(positions);
        System.out.println("heights: " + heights);
    }
}
