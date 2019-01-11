/*Math: Time:O(logn), Space:O(logn). We can optimize it to Space:O(1) solution
 * 1. Walk back from target point (tx, ty), because there is only one way back if tx != ty
 * 2. If ty > tx and two point are not the same, the possible back is (tx, ty - tx) and vice versa
 * 3. We can speed up the process by (tx, ty % tx) if sx != tx, and vice versa
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if(sx == tx && sy == ty){
            return true;
        }
        else if(tx == ty){
            return (sx == tx || sx == 0) & (sy == tx || sy == 0);
        }
        else if(sx > tx || sy > ty){
            return false;
        }
        else{
            if(ty > tx){
                if(sx == tx){
                    return ((ty - sy) % tx == 0)? true: false;
                }
                else{
                    return reachingPoints(sx, sy, tx, ty % tx);
                }
            }
            else{
                if(sy == ty){
                    return ((tx - sx) % ty == 0)? true: false;
                }
                else{
                    return reachingPoints(sx, sy, tx % ty, ty);
                }
            }
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int sx = 1;
        int sy = 1;
        int tx = 3;
        int ty = 5;
        System.out.println("sx:" + sx + ", sy:" + sy + ", tx:" + tx + ", ty:" + ty);
        System.out.println("can be reached:" + sol.reachingPoints(sx, sy, tx, ty));
    }
}
