/* Time:O(n), Space:O(1)
 * 1. Have an array "cache" to store the data readed from read4
 * 2. Have two pointers "startIndex" and "endIndex" to store start and end index in cache
 * 3. Upon enter "read", check if cache is non-empty. If so, put the data into "buf"
 * 4. If the size of buf is less than n, put the data into "cache" by invoking read4
 * 5. Fill in from "cache" to "buf"
 * 6. Repeat step4 and step5 until the size of buf reaching n or localCount == 0
 */

import java.util.*;
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] cache = new char[4];
    int endIndex = 0;
    int startIndex = 0;
    
    private boolean fillIn(char[] buf, int[] bufIndex, char[] cache, int n){
        for(int i = startIndex; i < endIndex; ++i){
            buf[bufIndex[0]++] = cache[i];
            startIndex++;
            if(bufIndex[0] == n){
                return true;
            }
        }
        return false;
    }
    
    public int read(char[] buf, int n) {
        int[] bufIndex = new int[1];
        
        if(endIndex > startIndex && n > 0){
            if(fillIn(buf, bufIndex, cache, n)){
                return bufIndex[0];
            }
        }
        
        int localCount = 1;
        while(bufIndex[0] < n && localCount > 0){
            localCount = read4(cache);
            startIndex = 0;
            endIndex = localCount;
            if(fillIn(buf, bufIndex, cache, n)){
                return bufIndex[0];
            }
        }
        
        return bufIndex[0];
    }
}
