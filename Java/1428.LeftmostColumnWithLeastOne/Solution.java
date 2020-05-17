/* Array: Time:O(n + m), Space:O(1)
 * 1.Set y = 0, x = width - 1 in the beginning
 * 2.Move x to left if binaryMatrix.get(y, x) == 1. Otherwise, move y to bottom
 * 3.Repeat step2 until (y, x) is out of range
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    private interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int depth = binaryMatrix.dimensions().get(0);
        int width = binaryMatrix.dimensions().get(1);
        int y = 0;
        int x = width - 1;
        while(y < depth && x >= 0){
            int val = binaryMatrix.get(y, x);
            if(val == 1){
                --x;
            }else{
                ++y;
            }
        }
        return (x == width - 1)? -1: x + 1;
    }
  
    public static void main(String[] args){
        System.out.println("no demo code");
    }
}
