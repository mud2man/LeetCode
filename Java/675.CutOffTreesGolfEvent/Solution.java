/* Sort and BFS: Time:O(m^2 * n^2), Space:O(m * n)
 * 1. Sort tripples hightAndPosition = {forest.get(y).get(x), y, x} by forest.get(y).get(x)
 * 2. Get the distance between hightAndPosition.get(i) and hightAndPosition.get(i + 1)
 * 3. Accumulate all distance
 */

import java.util.*; // Stack

public class Solution {
    private int bfs(int[] start, int[] end, List<List<Integer>> forest){
        int depth = forest.size();
        int width = forest.get(0).size();
        Set<Integer> visited = new HashSet<>();
        Deque<int[]> queue = new LinkedList<>();
        visited.add(start[1] * width +  start[2]);
        queue.add(new int[]{start[1], start[2]});
        int distance = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] front = queue.pollFirst();
                if(front[0] == end[1] && front[1] == end[2]){
                    return distance;
                }
                int[][] directions = {{0 ,1}, {0, -1}, {1, 0}, {-1, 0}};
                for(int[] direction: directions){
                    int y = front[0] + direction[0];
                    int x = front[1] + direction[1];
                    int hash = y * width + x;
                    if(y >= 0 && y < depth && x >= 0 && x < width && !visited.contains(hash) && forest.get(y).get(x) > 0){
                        queue.add(new int[]{y, x});
                        visited.add(hash);
                    }
                }
            }
            distance++;
        }
        return -1;
    }
    
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> hightAndPositions = new ArrayList<>();
        for(int y = 0; y < forest.size(); y++){
            for(int x = 0; x < forest.get(y).size(); x++){
                if(forest.get(y).get(x) > 0){
                    hightAndPositions.add(new int[]{forest.get(y).get(x), y , x});
                }   
            }
        }
        Collections.sort(hightAndPositions, (x, y) -> (x[0] - y[0]));
        int totlalDistance = bfs(new int[]{0, 0, 0}, hightAndPositions.get(0), forest);
        if(totlalDistance == -1){
            return -1;
        }
        for(int i = 0; i < hightAndPositions.size() - 1; i++){
            int distance = bfs(hightAndPositions.get(i), hightAndPositions.get(i + 1), forest);
            if(distance == -1){
                return -1;
            }
            totlalDistance += distance;
        }
        return (totlalDistance == 0)? -1: totlalDistance;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] arr = {{1, 2, 3}, {0, 0, 4}, {7, 6, 5}};
        System.out.println("forest:");
        List<List<Integer>> forest = new ArrayList<>();
        for(int[] row: arr){
            System.out.println(Arrays.toString(row));
            List<Integer> forestRow = new ArrayList<>();
            for(int height: row){
                forestRow.add(height);
            }
            forest.add(forestRow);
        }
        System.out.println("min steps:" + sol.cutOffTree(forest));
    }
}
