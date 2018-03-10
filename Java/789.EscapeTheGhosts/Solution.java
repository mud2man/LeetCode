/* Math: Time:O(n), Space:O(1), where n is the number of ghosts
 * 1. If all ghosts is farrer than player, then none of gohsts can catch the player. So return true
 * 2. Otherwise, one of the ghosts can get the target than player does
 */

import java.util.*;

public class Solution{
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int playerDistance = Math.abs(0 - target[0]) + Math.abs(0 - target[1]);
        for(int[] gohst: ghosts){
            int ghostDistance = Math.abs(gohst[0] - target[0]) + Math.abs(gohst[1] - target[1]);
            if(ghostDistance <= playerDistance){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] ghosts = {{1, 0}, {0, 3}};
        int[] target = {0 ,1};

        sol = new Solution();
        System.out.print("ghosts: ");
        for(int[] ghost: ghosts){
            System.out.print(Arrays.toString(ghost) + ", ");
        }
        System.out.println("");
        System.out.println("target: " + Arrays.toString(target));
        System.out.println("can escape?: " + sol.escapeGhosts(ghosts, target));
    }
}
