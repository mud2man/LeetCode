/* Math: O(1)
 * 1. get overlapped X and overlpaped Y
 * 2. return the sum of two rectangles - getXoverlap * getYoverlap
 */

import java.util.*;


public class Solution{
    private long getXoverlap(long A, long C, long E, long G){
        long rightestX = Math.max(C, G);
        long leftestX = Math.min(A, E);
        long mergeX = rightestX - leftestX;
        long overlapX = (G - E) - mergeX + (C - A);
        return (overlapX > 0)? overlapX: 0;
    }
    
    private long getYoverlap(long B, long D, long F, long H){
        long upY = Math.max(D, H);
        long bottomY = Math.min(B, F);
        long mergeY = upY - bottomY;
        long overlapY = (D - B) - mergeY + (H - F);
        return (overlapY > 0)? overlapY: 0;    
    }
    
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        long recangle0Area = ((long)C - (long)A) * ((long)D - (long)B);
        long recangle1Area = ((long)G - (long)E) * ((long)H - (long)F);
        long overlapArea = getXoverlap((long)A, (long)C, (long)E, (long)G) * getYoverlap((long)B, (long)D, (long)F, (long)H);
        long coverdArea = recangle0Area + recangle1Area - overlapArea;
        return (int)coverdArea;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int A = -3;
        int B = 0;
        int C = 3;
        int D = 4;
        int E = 0;
        int F = -1;
        int G = 9;
        int H = 2;
        int area;

        area = sol.computeArea(A, B, C, D, E, F, G, H);
        System.out.println("area: " + area);
    }
}
