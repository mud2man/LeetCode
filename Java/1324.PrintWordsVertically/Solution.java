/* String: Time:O(n), Space:O(1)
 * 1. Split string and store them in rows  
 * 2. Pick up the "vertical" from x-th char of rows, and trim the trailing space
 * 3. Repeat step 2 until vertical is an empty string
 */

import java.util.*;


public class Solution{
    public List<String> printVertically(String s) {
        String[] rows = s.split(" ");
        List<String> verticals = new ArrayList<>();
        String vertical = "NULL";
        int x = 0;
        while(vertical.length() > 0){
            vertical = "";
            for(int y = 0; y < rows.length; ++y){
                String row = rows[y];
                vertical +=(x < row.length())? Character.toString(row.charAt(x)): " "; 
            }
            int endIdx = vertical.length() - 1;
            while(endIdx >= 0 && vertical.charAt(endIdx) == ' '){
                endIdx--;
            }
            vertical = vertical.substring(0, endIdx + 1);
            if(vertical.length() > 0){
                verticals.add(vertical);
            }
            ++x;
        }
        return verticals;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "CONTEST IS COMING";
        System.out.println("s:" + s);
        System.out.println("verticals:" + sol.printVertically(s));
    }
}
