/* Iterator: Time:O(1), Space:O(1)
 * 1. Have two pointer y and x
 * 2. The next value is v[y][x]
 * 3. Determine if has next by (y < v.length)
 */

import java.util.*;

public class Solution{
    int y;
    int x;
    int[][] v;
    public Solution(int[][] v) {
        this.y = 0;
        this.x = 0;
        this.v = v;
        while(y < v.length){
            if(x < v[y].length){
                break;
            }
            ++y;
        }
    }
    
    public int next() {
        int next = v[y][x];
        ++x;
        while(y < v.length){
            if(x < v[y].length){
                break;
            }else{
                x = 0;
            }
            ++y;
        }
        return next;
    }
    
    public boolean hasNext() {
        return (y < v.length);
    }
 
    public static void main(String[] args){
        int[][] vec2d = {{1, 2}, {3}, {4}};
        Solution sol = new Solution(vec2d);    
        System.out.println("vec2d: ");
        for(int[] row: vec2d){
            System.out.println(Arrays.toString(row));
        }
        while(sol.hasNext()){
            System.out.print(sol.next() + ",");
        }
        System.out.println("");
    }
}
