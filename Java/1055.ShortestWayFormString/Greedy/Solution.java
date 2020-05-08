/* Greedy: Time:O(m + n), Space:(m)
 * 1. Have a 2D array "closiestIdxAfter" to store the leftest index for each letter after index i
 * 2. Start index of source(idxSource) from 0
 * 3. Update idxSource closiestIdxAfter[0][targetChar - 'a'] + 1 in general case
 * 4. Accumulate count when reach the end of source
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int shortestWay(String source, String target) {
        int[][] closiestIdxAfter = new int[source.length()][26];
        Set<Character> sourceChars = new HashSet<>();
        for(int i = source.length() - 1; i >= 0; --i){
            Arrays.fill(closiestIdxAfter[i], -1);
            closiestIdxAfter[i] =(i < source.length() - 1)? Arrays.copyOf(closiestIdxAfter[i + 1], 26): closiestIdxAfter[i];
            closiestIdxAfter[i][source.charAt(i) - 'a'] = i;
            sourceChars.add(source.charAt(i));
        }
        
        int count = 1;
        int idxSource = 0;
        for(int i = 0; i < target.length(); ++i){
            char targetChar = target.charAt(i);
            if(!sourceChars.contains(targetChar)){
                return -1;
            }
            if(closiestIdxAfter[idxSource][targetChar - 'a'] == -1){
                count++;
                idxSource = closiestIdxAfter[0][targetChar - 'a'] + 1;
            }else if(closiestIdxAfter[idxSource][targetChar - 'a'] == source.length() - 1){
                count +=(i < target.length() - 1)? 1: 0 ;
                idxSource = 0;
            }else{
                idxSource = closiestIdxAfter[idxSource][targetChar - 'a'] + 1;
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String source = "abc";
        String target = "abcbc";
        System.out.println("source:" + source);
        System.out.println("target:" + target);
        System.out.println("minimum number of subsequences:" + sol.shortestWay(source, target));
    }
}
