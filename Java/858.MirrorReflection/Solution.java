/* Math: Time:O(logp), Space:O(1)
 * 1. Imagine the room has no ceil, the left wall and right wall extends infinitely
 * 2. When the ray hit the receiver, the distance in y direction must multiple of p
 * 3. So we can get the least common multiple between p and q
 * 4. If (lcm / q) % 2 == 1, it mean the ray hit the receiver of right wall, then we can determine which one is hitted by (lcm / p) % 2 == 1
 * 5. Otherwise, it hit receiver#2
 */         

import java.util.*;

public class Solution {
    private int getGcf(int p, int q){
        int big = (p > q)? p: q;
        int small = (p > q)? q: p;
        if(big % small == 0){
            return small;
        }
        else{
            return getGcf(small, big % small);
        }
    }
    
    public int mirrorReflection(int p, int q) {
        int gcf = getGcf(p, q);
        int num = 0;
        int lcm = gcf * (p / gcf) * (q / gcf);
        //right wall
        if((lcm / q) % 2 == 1){
            num = ((lcm / p) % 2 == 1)? 1: 0;
        }
        //left wall
        else{
            num = 2;
        }
        return num;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int p = 2; 
        int q = 1; 
        System.out.println("p: " + p + ", q:" + q);
        System.out.println("mirror#: " + sol.mirrorReflection(p, q));
    }
}
