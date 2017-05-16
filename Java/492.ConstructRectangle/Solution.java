/* O(n^0.5)
 * 1. Save length to rectangle[0], and width to rectangle[1]
 * 2. Set length with the init value of ceil(area^0.5)
 * 3. Set width with the init value of floor(area^0.5)
 * 5. If length * width > area, width = floor(area / length)
 * 6. Otherwise, length = ceil(area / width)
 * 7. Repeat step 5 and 6, until length * width == area
 *
 * ex: area = 7
 * time = 0, length = 3, width = 2
 * time = 1, length = 4, width = 2
 * time = 2, length = 4, width = 1
 * time = 3, length = 7, width = 1
 *
 */          

import java.util.*; // Stack

public class Solution {
    public int[] constructRectangle(int area) {
        int[] rectangle, reArea;
        
        rectangle = new int[2];
        
        rectangle[0] = (int)Math.ceil(Math.sqrt(area)); 
        rectangle[1] = (int)Math.floor(Math.sqrt(area)); 
        
        while(rectangle[0] * rectangle[1] != area){
            if(rectangle[0] * rectangle[1] > area){
                rectangle[1] = (int)Math.floor((double)area / (double)rectangle[0]);
            } 
            else{
                rectangle[0] = (int)Math.ceil((double)area / (double)rectangle[1]); 
            }
        }
        return rectangle;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] rectangle;
        int area = 7;

        sol = new Solution();

        System.out.println("area: " + area);
        rectangle = sol.constructRectangle(area);
        System.out.println("rectangle: " + Arrays.toString(rectangle));
    }
}
