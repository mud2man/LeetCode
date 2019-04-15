/* Math: Time:O(logN), Space:O(logN)
 * 1. We can prove the encode is unique and continuous by induction, range of msb=1 is (-2, 1), range of msb=3 is (-10, 5)
 * 2. Then, we create the table as following, which includes positive queue "maxQueue" ,negative queue "minQueue", and msb as well
 * 3. We find the returned string from msb, then set '1' if the remaining is within secondMax and max or secondMin and min. Otherwise set '0'
 *
 * Table:         _________   
 *       msb=1 <=| -2  , 1 |=> msb=0
 *       msb=3 <=| -10 , 5 |=> msb=2   
 *       msb=5 <=| -42 , 21|=> msb=4
 *       ..........................
 */

import java.util.*;


public class Solution{
    public String baseNeg2(int N) {
        Deque<Integer> maxQueue = new LinkedList<>();
        maxQueue.add(1);
        Deque<Integer> minQueue = new LinkedList<>();
        minQueue.add(-2);
        int msb = 1;
        while(maxQueue.peekLast() < N){
            maxQueue.add(maxQueue.peekLast() * 4 + 1);
            minQueue.add(minQueue.peekLast() * 4 - 2);
            msb = msb * 4;
        }
        minQueue.pollLast();
        
        int remain = N;
        StringBuilder ret = new StringBuilder("");
        while(!maxQueue.isEmpty()){
            int max = maxQueue.pollLast();
            int secondMax = maxQueue.isEmpty()? 0: maxQueue.peekLast();
            if(remain > 0 && remain <= max && remain > secondMax){
                ret.append('1');
                remain -= msb;
            }
            else{
                ret.append('0');
            }

            msb = msb / (-2);
            if(minQueue.isEmpty()){
                break;
            }
            int min = minQueue.pollLast();
            int secondMin = minQueue.isEmpty()? 0: minQueue.peekLast();
            if(remain < 0 && remain >= min && remain < secondMin){
                ret.append('1');
                remain -= msb;
            }
            else{
                ret.append('0');
            }
            msb = msb / (-2);
        }
        return ret.toString();
    }
    
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 2;
        
        System.out.println("N:" + N);
        System.out.println("encode:" + sol.baseNeg2(N));
    }
}
