/* Math: Time:O(logN), Space:O(logN)
 * 1. We can prove the encode is unique and continuous by induction, range of weight=-2^1 = (-2, 1), range of weight=-2^2 = (-2, 5), and range of -2^3 = (-10, 5)
 * 2. We construct the ranges until N is in the last range
 * 3. Append '1' if N is in the gap between the two range bigRange and smallRange
 * 4. Decrease N by base if N is in the range gap
 *
 * Table:               _________   
 *       weight=-2^0 <=| 0  , 1 |
 *       weight=-2^1 <=| -2 , 1 |
 *       weight=-2^2 <=| -2 , 5 |
 *       ..........................
 */

import java.util.*;


public class Solution{
    public String baseNeg2(int N) {
        int base = 1;
        List<int[]> ranges = new ArrayList<>();
        int[] range = {0, 1};
        while(N < range[0] || N > range[1]){
            ranges.add(range);
            base *= -2;
            range = new int[]{Math.min(range[0], range[0] + base), Math.max(range[1], range[1] + base)};
        }
        ranges.add(range);
        
        StringBuilder sb = new StringBuilder("");
        for(int i = ranges.size() - 1; i > 0; --i){
            int[] bigRange = ranges.get(i);
            int[] smallRange = ranges.get(i - 1);
            if((N >= bigRange[0] && N < smallRange[0]) || (N <= bigRange[1] && N > smallRange[1])){
                sb.append('1');
                N -= base;
            }else{
                sb.append('0');
            }
            base /= -2;
        }
        sb.append((N == 1)? '1': '0');
        return sb.toString();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 2;
        System.out.println("N:" + N);
        System.out.println("encode:" + sol.baseNeg2(N));
    }
}
