/* Map: Time:O(1), Space:O(m), where m is flip number
 * 1. Have a map vir2Phy to achieve virtual array. If virIdx is in vir2Phy, then the corresponding phyIdx is vir2Phy.get(virIdx)
 * 2. Otherwise, virIdx = phyIdx
 * 3. When flip, we pick a random virIdx between 0 and (length - 1), the swap it with the last virIdx
 * 4. When swap, we need to handle to check if the last virIdx has phyIdx
 */

import java.util.*;

public class Solution {
    Map<Integer, Integer> vir2Phy;
    int depth;
    int width;
    int length;
    Random rand;
    
    public Solution(int n_rows, int n_cols) {
        rand = new Random();
        vir2Phy = new HashMap<>();
        depth = n_rows;
        width = n_cols;
        length = depth * width;
    }
    
    public int[] flip() {
        int virIdx = rand.nextInt(length--);
        int phyIdx = (!vir2Phy.containsKey(virIdx))? virIdx: vir2Phy.get(virIdx);
        
        //swap
        if(vir2Phy.containsKey(length)){
            vir2Phy.put(virIdx, vir2Phy.get(length));
        }
        else{
            vir2Phy.put(virIdx, length);
        }
        return new int[]{phyIdx / width, phyIdx % width};
    }
    
    public void reset() {
        length = depth * width;
        vir2Phy = new HashMap<>();
    }
 
    public static void main(String[] args){
        int n_rows = 1;
        int n_cols = 2;
        Solution sol = new Solution(n_rows, n_cols);
        
        System.out.println("n_rows: " + n_rows);
        System.out.println("n_cols: " + n_cols);
        System.out.println("flip(): " + Arrays.toString(sol.flip()));
        System.out.println("flip(): " + Arrays.toString(sol.flip()));
        sol.reset();
        System.out.println("reset(): ");
        System.out.println("flip(): " + Arrays.toString(sol.flip()));
    }
}
