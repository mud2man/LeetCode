/* : Math: O(n)
 * 1. BigStep = 2 * numRows - 2
 * 2. Internal step = idx + BigStep - 2 * rowNum
 * 
 * ex: path: "abcdefghijklm", numRows = 4, BigStep = 2*4-2=6
 *
 * (0)a           (6)g             (12)m
 * (1)b      (5)f (7)h       (11)l
 * (2)c (4)e      (8)i (10)k 
 * (3)d           (9)j
 *
 */

import java.util.*;

public class Solution{
    public String convert(String s, int numRows) {
        StringBuilder sb;
        int step, rowNum, idx, interStep;
        
        if(numRows == 1){
            return s;
        }
        sb = new StringBuilder("");
        step = 2 * numRows - 2;
        for(rowNum = 0; rowNum < numRows; rowNum++){
            idx = rowNum;
            while(idx < s.length()){
                sb.append(s.charAt(idx));
                if(rowNum > 0 && rowNum < numRows - 1){
                    interStep = idx + step - 2 * rowNum;
                    if(interStep < s.length()){
                        sb.append(s.charAt(interStep));
                    }
                }
                idx += step;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String s;
        Solution sol;
        int numRows;
        
        numRows = 4; 
        s = "abcdefghijklm";
        sol = new Solution();

        System.out.println("before convert with row#=" + numRows + " : " + s);
        s = sol.convert(s, numRows);
        System.out.println("after convert with row#=" + numRows + " : " + s);
    }
}
