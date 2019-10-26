/* Backtrack: O(n!), where n is the length or tiles
 * 1. Have a queue to store the sorted characters of tiles
 * 2. In backtrack, pick the character whihc is not the same as the previous one. 
 * 3. Then restore the the current character into the tail of queue
 *
 */

import java.util.*;

public class Solution{
    private void backtrack(Deque<Character> queue, int[] count){
        if(queue.isEmpty()){
            return;
        }
        
        char prevChar = (char)((int)queue.peekFirst() - 1);
        int size = queue.size();
        for(int i = 0; i < size; ++i){
            char currChar = queue.pollFirst();
            if(currChar != prevChar){
                count[0]++;
                backtrack(queue, count);
            }
            queue.add(currChar);
            prevChar = currChar;
        }
    }
    
    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        Deque<Character> queue = new LinkedList<>();
        for(char c: chars){
            queue.add(c);
        }
        int[] count = {0};
        backtrack(queue, count);
        return count[0];
    }
     
    public static void main(String[] args){
        Solution sol = new Solution();
        String tiles = "AAB";
        System.out.println("tiles: " + tiles);
        System.out.println("possibilities: " + sol.numTilePossibilities(tiles));
    }
}
