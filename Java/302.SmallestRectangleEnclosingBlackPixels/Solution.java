/* Binary Search: Time:O(nlogm + mlogn), Space:O(1)However, leetcode has a shorter answer
 * 1. Apply binary search to find the left/right bound, and top/bottom bound
 * 2. For right bound, starts from the original point and traverse down to update the leftest point with decending slope
 * 3. Also, traverse up to update the leftest point with accending slope
 * 4. Tarverse from first row to update the leftest point with dencendding slope
 * 5. Tarverse from last row to update the leftest point with accending slope
 * 6. Do similar thing on right, top, and bottom edje
 */

import java.util.*;

public class Solution{
    private void updateRight(char[][] image, int y, int x, int[] right){
        if(image[y][x] == '0'){
            return;
        }
        int l = x;
        int r = image[0].length - 1;
        while(l <= r){
            int m = (l + r) / 2;
            if(image[y][m] == '1'){
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        right[0] = r;
    }
    
    private void updateLeft(char[][] image, int y, int x, int[] left){
        if(image[y][x] == '0'){
            return;
        }
        int l = 0;
        int r = x;
        while(l <= r){
            int m = (l + r) / 2;
            if(image[y][m] == '1'){
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        left[0] = l;
    }
    
    private void updateBottom(char[][] image, int y, int x, int[] bottom){
        if(image[y][x] == '0'){
            return;
        }
        int l = y;
        int r = image.length - 1;
        while(l <= r){
            int m = (l + r) / 2;
            if(image[m][x] == '1'){
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        bottom[0] = r;
    }
    
    private void updateTop(char[][] image, int y, int x, int[] top){
        if(image[y][x] == '0'){
            return;
        }
        int l = 0;
        int r = y;
        while(l <= r){
            int m = (l + r) / 2;
            if(image[m][x] == '1'){
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        top[0] = l;
    }
    
    public int minArea(char[][] image, int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
        
        int[] right = {x};
        int[] left = {x};
        for(int i = y; i < image.length; ++i){
            updateRight(image, i, right[0], right);
            updateLeft(image, i, left[0], left);
        }
        for(int i = y - 1; i >= 0; --i){
            updateRight(image, i, right[0], right);
            updateLeft(image, i, left[0], left);
        }
        for(int i = 0; i < image.length; ++i){
            updateRight(image, i, right[0], right);
            updateLeft(image, i, left[0], left);
        }
        for(int i = image.length - 1; i >= 0; --i){
            updateRight(image, i, right[0], right);
            updateLeft(image, i, left[0], left);
        }
        
        int[] top = {y};
        int[] bottom = {y};
        for(int j = x; j < image[0].length; ++j){
            updateTop(image, top[0], j, top);
            updateBottom(image, bottom[0], j, bottom);
        }
        for(int j = x - 1; j >= 0; --j){
            updateTop(image, top[0], j, top);
            updateBottom(image, bottom[0], j, bottom);
        }
        for(int j = 0; j < image[0].length; ++j){
            updateTop(image, top[0], j, top);
            updateBottom(image, bottom[0], j, bottom);
        }
        for(int j = image[0].length - 1; j >= 0; --j){
            updateTop(image, top[0], j, top);
            updateBottom(image, bottom[0], j, bottom);
        }
        
        return (right[0] - left[0] + 1) * (bottom[0] - top[0] + 1);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int x = 0;
        int y = 2;
        char[][] image = {{'0','0','1','0'},
                          {'0','1','1','0'},
                          {'0','1','0','0'}};
 
        System.out.println("y: " + y);
        System.out.println("x: " + x);
        System.out.println("image: ");
        for(char[] row: image){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("smallest rectangle: " + sol.minArea(image, y, x));
    }
}
