/* Binary Search: Time:O(nlogm)), Space:O(1)
 * 1.Use binary search to find the left most index. 
 * 2.Update end by the left most index found from previous row
 * 3.We check if binaryMatrix.get(y, min - 1) == 1 before we run binary search, since we don't waste effor on the worse row
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    private interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    private int binarySearch(BinaryMatrix binaryMatrix, int y, int end){
        int l = 0;
        int r = end - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(binaryMatrix.get(y, mid) == 0){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }
    
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int depth = binaryMatrix.dimensions().get(0);
        int width = binaryMatrix.dimensions().get(1);
        int min = width;
        int end = width;
        for(int y = 0; y < depth; ++y){
            if(min == width || (min > 0 && binaryMatrix.get(y, min - 1) == 1)){
                min = binarySearch(binaryMatrix, y, min);
            }else if(min == 0){
                return 0;
            }
        }
        return (min == width)? -1: min;
    }
  
    public static void main(String[] args){
        System.out.println("no demo code");
    }
}
