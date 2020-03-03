/* HashMap: Time:O(1), Space:O(B)
 * 1. Name the elenments in blacklist which is smaller than (N - blacklist.length) as leftBlack
 * 2. Name the elenments in blacklist which is equal to or bigger than (N - blacklist.length) as rightBlack
 * 3. Nmae the elenments NOT in blacklist which is equal to or bigger than (N - blacklist.length) as rightWhite
 * 4. Have a mapping "reMap" to map all the elements in leftBlack to rightWhite
 * 5. When doing picking, choose the random number between 0 and N - blacklist.length, and remap the number if reMap contains
 */

import java.util.*;

public class Solution {
    Map<Integer, Integer> reMap;
    Random random;
    int n;
    
    public Solution(int N, int[] blacklist) {
        random = new Random();
        reMap = new HashMap<>();
        n = N - blacklist.length; 
        Set<Integer> blackSet = new HashSet<Integer>();
        for(int b: blacklist){
            blackSet.add(b);
        }
        
        LinkedList<Integer> whiteList = new LinkedList<>();
        for(int i = N - blacklist.length; i < N; ++i){
            if(!blackSet.contains(i)){
                whiteList.add(i);
            }
        }
        
        for(int i = 0; i < blacklist.length; ++i){
            if(blacklist[i] >= N - blacklist.length){
                continue;
            }
            else{
                reMap.put(blacklist[i], whiteList.poll());
            }
        }
    }
    
    public int pick() {
        int num = random.nextInt(n);
        if(reMap.containsKey(num)){
            num = reMap.get(num);
        }
        return num;
    }
 
    public static void main(String[] args){
        int N = 3;
        int[] blacklist = {1};
        Solution sol = new Solution(N, blacklist);

        System.out.println("N: " + N);
        System.out.println("blacklist: " + Arrays.toString(blacklist));
        System.out.println("pick(): " + sol.pick());
        System.out.println("pick(): " + sol.pick());
        System.out.println("pick(): " + sol.pick());
    }
}
