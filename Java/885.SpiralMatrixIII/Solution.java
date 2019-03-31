/* Math: Time:O(2R * 2C), Space:O(1)
 * 1. Have 4 direction "dirs" with clockwise order, and update next position based on dir
 * 2. Only put the valid "position" into "positions"
 * 3. Increase length every two times
 * 4. Repeat update "position" until the number of valid position is R*C
 */

import java.util.*;

public class Solution{
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int idx = 0;
        int[] position = {r0, c0};
        int[][] positions = new int[R * C][2];
        int length = 1;
        while(idx < positions.length){
            for(int j = 0; j < 4; ++j){
                for(int l = 0; l < length; ++l){
                    if(position[0] >= 0 && position[0] < R && position[1] >= 0 && position[1] < C) {
                        positions[idx][0] = position[0];
                        positions[idx++][1] = position[1];
                    }
                    position[0] += dirs[j][0];
                    position[1] += dirs[j][1];
                }
                length += (j % 2 == 1)? 1: 0;
            }
        }
        return positions;
    }
	
    public static void main(String[] args){
        Solution sol = new Solution();
        int R = 1;
        int C = 4;
        int r0 = 0;
        int c0 = 0;
        int[][] positions = sol.spiralMatrixIII(R, C, r0, c0);
        for(int[] position: positions){
            System.out.println(Arrays.toString(position));
        }
	}
}
