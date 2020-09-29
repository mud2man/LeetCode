/* BFS: Time:O(n), Space:O(n), where n is blocked.size()
 * 1. Call canEscape from "start" and "end". Return true only if both start and end can escape
 * 2. Use BFS to circulate the boundary in "canEscape"
 * 3. If the boundary.size() > 4 * blocked.size(), it means there start can reach any nodes which not circulated by blocked
 * 4. There are 2 conditions can make "start" reaches "end"
 * 5. Condition 1: both "start" and "end" can reach any nodes which not circulated by blocked
 * 6. Condition 2: start and end are reachable in a boundary
 */     

import java.util.*; // Stack

public class Solution {
    private boolean canEscape(long start, long end, Set<Long> blocked){
        Set<Long> visited = new HashSet<>();
        Deque<Long> boundary = new LinkedList<>();
        visited.add(start);
        boundary.add(start);
        long width = 1_000_000;
        while(!boundary.isEmpty()){
            int size = boundary.size();
            for(int i = 0; i < size; ++i){
                long front = boundary.pollFirst();
                if(front == end){
                    return true;
                }
                long y = front / width;
                long x = front % width;
                long[][] shifts = new long[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for(long[] shift: shifts){
                    long nextY = y + shift[0];
                    long nextX = x + shift[1];
                    long next = nextY * width + nextX;
                    if(nextY >= 0 && nextY < width && nextX >= 0 && nextX < width && 
                       !visited.contains(next) && !blocked.contains(next)){
                        visited.add(next);
                        boundary.add(next);
                    }
                }
            }
            if(boundary.size() > 4 * blocked.size()){
                return true;
            }
        }
        return false;
    }
    
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedHashes = new HashSet<>();
        for(int[] pos: blocked){
            blockedHashes.add((long)pos[0] * 1_000_000 + (long)pos[1]);
        }
        long sourceHash = (long)source[0] * 1_000_000 + (long)source[1];
        long targetHash = (long)target[0] * 1_000_000 + (long)target[1];
        return (canEscape(sourceHash, targetHash, blockedHashes) && canEscape(targetHash, sourceHash, blockedHashes));
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("maximum sum divisible by 3:" + sol.maxSumDivThree(nums));
    }
}
