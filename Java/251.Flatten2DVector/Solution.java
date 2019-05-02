/* Iterator: Time:O(1), Space:O(1)
 * 1. Have two pointer y and x
 * 2. Have "cache" to store the next value
 * 3. Determine if has next by (cache != null)
 */

import java.util.*;

public class Solution{
    int y = 0;
    int x = 0;
    Integer cache;
    int[][] v;

    public Solution(int[][] v) {
        this.y = 0;
        this.x = 0;
        this.v = v;
        while(y < v.length && cache == null){
            while(x < v[y].length && cache == null){
                cache = v[y][x++];
                break;
            }
            x = (cache == null)? 0: x;
            y += (cache == null)? 1: 0;
        }
    }
    
    public int next() {
        Integer temp = this.cache;
        while(y < v.length){
            while(x < v[y].length){
                this.cache = this.v[y][x++];
                return temp;
            }
            x = 0;
            ++y;
        }
        this.cache = null;
        return temp;
    }
    
    public boolean hasNext() {
        return (cache != null);
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
