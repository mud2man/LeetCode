/* Dynamic programming + Maximum heap: O(n*k)
 * 1. Construct a 2-D array to represent the top-2 minimum cost with 2 colors
 * 2. Get the minium cost with color x on house y, based on dp[y - 1][0] and dp[y - 1][1], and put the node into maximum heap
 * 3. dp[i][0] = minimum cost on house i, dp[i][1] = second minimum cost on house i
 * 4. cost = costs[i][color] + ((dp[i][0].color != color)? dp[i][0].cost: dp[i][1].cost)
 */

import java.util.*;

public class Solution{
    private class Node{
        int color;
        int cost;
        Node(int cr, int ct){color = cr; cost = ct;}
    }
    
    public int minCostII(int[][] costs) {
        if(costs.length == 0){
            return 0;
        }
        int length = costs.length;
        int depth = costs[0].length;
        Node[][] dp = new Node[length + 1][2];
        dp[0][0] = new Node(-1, 0);
        dp[0][1] = new Node(-2, 0);
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((x, y) -> y.cost - x.cost);
        for(int i = 0; i < length; ++i){
            for(int color = 0; color < depth; ++color){
                int cost = costs[i][color];
                cost += (dp[i][0].color != color)? dp[i][0].cost: dp[i][1].cost;
                
                if(maxHeap.size() < 2){
                    maxHeap.add(new Node(color, cost));
                }else{
                    if(maxHeap.peek().cost > cost){
                        maxHeap.poll();
                        maxHeap.add(new Node(color, cost));
                    }
                }
            }
            
            if(maxHeap.size() == 2){
                dp[i + 1][1] = maxHeap.poll();
                dp[i + 1][0] = maxHeap.poll();
            }else{
                Node top = maxHeap.poll();
                dp[i + 1][1] = top;
                dp[i + 1][0] = top;
            }
        }
        return dp[length][0].cost; 
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int [][] costs = {{1, 2, 3},
                          {2, 1, 4},
                          {5, 7, 8}};

        System.out.println("costs: ");
        for(int[] cost: costs){
            System.out.println(Arrays.toString(cost));
        }
        System.out.println("");
        System.out.println("minCost: " + sol.minCostII(costs));
    }
}
