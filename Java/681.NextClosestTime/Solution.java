/* Permutation: Time:O(1), Space:O(1)
 * 1. Parse time and translate it to minitues
 * 2. Get all the permutation, and keep the closest next time "nextClosetMin"
 * 3. In each loop, compare the difference, then update "nextClosetMin" if the current difference is smaller
 */

import java.util.*;

public class Solution{
    private boolean isValid(int[] nextTime){
        int hour = nextTime[0]*10 + nextTime[1];
        int min = nextTime[2]*10 + nextTime[3];
        return (hour < 24 && min < 60);
    }
    
    private int getDiff(int currentMin, int nextMin){
        return (nextMin == currentMin)? 1440: (nextMin > currentMin)? nextMin - currentMin: (1440 - (currentMin - nextMin));
    }
    
    public String nextClosestTime(String time) {
        String[] times = time.split(":");
        int[] currentTime = new int[4];
        currentTime[0] = (int)(times[0].charAt(0) - '0');
        currentTime[1] = (int)(times[0].charAt(1) - '0');
        currentTime[2] = (int)(times[1].charAt(0) - '0');
        currentTime[3] = (int)(times[1].charAt(1) - '0');
        
        int currentMin = currentTime[0]*10*60 + currentTime[1]*60 + currentTime[2]*10 + currentTime[3];
        int nextClosetMin = Integer.MAX_VALUE;
        int[] nextTime = new int[4];
        String nextClosestTime = "";
        for(int x = 0; x < 4; ++x){
            for(int y = 0; y < 4; ++y){
                for(int z = 0; z < 4; ++z){
                    for(int w = 0; w < 4; ++w){
                        nextTime[0] = currentTime[x];
                        nextTime[1] = currentTime[y];
                        nextTime[2] = currentTime[z];
                        nextTime[3] = currentTime[w];
                        if(isValid(nextTime)){
                            int nextMin = nextTime[0]*10*60 + nextTime[1]*60 + nextTime[2]*10 + nextTime[3];
                            int diff = getDiff(currentMin, nextMin);
                            int minDiff = getDiff(currentMin, nextClosetMin);
                            if(minDiff > diff){
                                nextClosetMin = nextMin;
                                nextClosestTime = Integer.toString(nextTime[0]) + Integer.toString(nextTime[1]);
                                nextClosestTime += ":";
                                nextClosestTime += Integer.toString(nextTime[2]) + Integer.toString(nextTime[3]);
                            }
                        }
                    }
                }
            }
        }
        return nextClosestTime;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String time = "19:34";
        
        System.out.println("current time: " + time);
        System.out.println("next time: " + sol.nextClosestTime(time));
    }
}
