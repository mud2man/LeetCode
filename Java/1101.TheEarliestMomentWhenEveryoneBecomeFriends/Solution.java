/* Union Find: Time:O(nlogn), Space:O(n)
 * 1. Sort logs by log[0], and apply union and find to caculate the number of group
 * 2. Return timeStamp when groupCount == 1
 */

import java.util.*; // Stack

public class Solution {
    private int find(int[] root, int x){
        if(root[x] == x){
            return x;
        }
        root[x] = root[root[x]];
        return find(root, root[root[x]]);
    }
    
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (int[] x, int[] y) -> x[0] - y[0]);
        int[] root = new int[N];
        int[] rank = new int[N];
        for(int i = 0; i < N; ++i){
            root[i] = i;
        }
        
        int groupCount = N;
        for(int[] log: logs){
            int rootX = find(root, log[1]);
            int rootY = find(root, log[2]);
            if(rootX != rootY){
                if(rank[rootX] > rank[rootY]){
                    root[rootY] = rootX;
                }else if(rank[rootX] < rank[rootY]){
                    root[rootX] = rootY;
                }else{
                    root[rootY] = rootX;
                    rank[rootX]++;
                }
                groupCount--;
            }
            if(groupCount == 1){
                return log[0];
            }
        }
        return -1;
    }
  
    public static void main(String[] args){
        int N = 3;
        int[][] logs= {{20190101, 0, 1}, {20190104, 1, 2}, {20190100, 0, 2}};
        Solution sol = new Solution();
        System.out.println(String.format("N: %d", N));
        for(int[] log: logs){
            System.out.println(Arrays.toString(log));
        }
        System.out.println(String.format("earilest time: %d", sol.earliestAcq(logs, 3)));
    }
}
