/* Dynamic Programming Time:O(n^3), Space:O(n^2). LeetCode has a nlogn solution
 * 1. Add start {0, startFuel} and end {target, 0} to stations, and rename it to fullStations
 * 2. dp[y][x] = The maximum fuel can last with refuel y times
 * 3. Update dp[y][x] = max{dp[y - 1][z] - dis + fullStations.get(x)[1]}, where 0 <= z < x, dis = stations[x][0] - station[z][0]
 * 4. If dp[y - 1][z] >= dis, skip this time
 */

import java.util.*;

public class Solution{
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int length = stations.length;
        int[][] dp = new int[length + 1][length + 2];
        List<int[]> fullStations = new ArrayList<>();
        
        fullStations.add(new int[]{0, startFuel});
        for(int[] station: stations){
            fullStations.add(station);
        }
        fullStations.add(new int[]{target, 0});
        
        for(int y = 0; y < (length + 1); ++y){
            if(y == 0){
                int fuel = startFuel;
                for(int x = 0; x < dp[0].length; ++x){
                    dp[y][x] = fuel;
                    fuel -= (x < dp[0].length - 1)? fullStations.get(x + 1)[0] - fullStations.get(x)[0]: 0;
                }
            }
            else{
                for(int x = y; x < dp[0].length; ++x){
                    int max = dp[y][x - 1] - (fullStations.get(x)[0] - fullStations.get(x - 1)[0]);
                    for(int z = x - 1; z >= 0; --z){
                        int dis = fullStations.get(x)[0] - fullStations.get(z)[0]; 
                        if(dp[y - 1][z] >= dis){
                            max = Math.max(max, dp[y - 1][z] - dis + fullStations.get(x)[1]);   
                        }
                        else{
                            continue;
                        }
                    }
                    if(y == x && max < 0){
                        return -1;
                    }
                    dp[y][x] = max;
                }
            }
            
            if(dp[y][dp[0].length - 1] >= 0){
                return y;
            }
        }
        
        return -1;
    }

    public static void main(String[] args){
        int target = 100;
        int startFuel = 10;
        int[][] stations = {{10, 60}, {20, 30}, {30, 30},{60, 40}};
        Solution sol = new Solution();

        System.out.println("target:" + target + ", startFuel:" + startFuel);
        System.out.println("stations:");
        for(int[] station: stations){
            System.out.println(Arrays.toString(station));
        }
        System.out.println("minimum refuel: " + sol.minRefuelStops(target, startFuel, stations));
    }
}
