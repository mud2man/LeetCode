/* BFS: Time:O(n^2 * w * d), Space:O(n^2 * w * d), where n = keys#, w = grid width, l = grid length
 * 1. Use BFS to find all the shortest distance for each pair "shortestDistance" with all combinations of visited keys
 * 2. Generate all the possible sequence to collect keys with starting point '@'
 * 3. For examle, if sequence = "@abc", distance = shortestDistance.get("@a").get("") + shortestDistance.get("ab").get("a") + shortestDistance.get("bc").get("ab")
 * 4. Get the minimum distance among all possible sequences
 */

import java.util.*;

public class Solution {
    private String getSorted(String str){
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        String sorted = "";
        for(char c: charArray){
            sorted = sorted + Character.toString(c);
        }
        return sorted;
    }
    
    private void getVisited(int i, int j, List<Character> keys, int ptr, List<String> visited, String path){
        if(ptr == keys.size()){
            visited.add(getSorted(path));
            return;
        }
        if(keys.get(ptr) != '@'){
            getVisited(i, j, keys, ptr + 1, visited, path + Character.toString(keys.get(ptr)));   
        }
        getVisited(i, j, keys, ptr + 1, visited, path);
    }
    
    private int getMinDistance(char start, char end, String[] grid, String visitedKeys, Map<Character, Integer> key2Position){
        Set<Integer> visited = new HashSet<>();
        visited.add(key2Position.get(start));
        Deque<Integer> queue = new LinkedList<>();
        queue.add(key2Position.get(start));
        int dis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int front = queue.pollFirst();
                int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                int y = front / grid[0].length();
                int x = front % grid[0].length();
                for(int[] dir: dirs){
                    int nextY = y + dir[0];
                    int nextX = x + dir[1];
                    int encodedPos = nextY * grid[0].length() + nextX;
                    if(nextY >= 0 && nextY < grid.length && nextX >= 0 && nextX < grid[0].length()){
                        char c = grid[nextY].charAt(nextX);
                        if(c == end){
                            return dis + 1;   
                        }else if(visited.contains(encodedPos) || c == '#'){
                            continue;
                        }else if(Character.isUpperCase(c)){
                            char keyOfLock = Character.toLowerCase(c);
                            if(visitedKeys.indexOf(keyOfLock) != -1){
                                visited.add(encodedPos);
                                queue.add(encodedPos);
                            }
                        }else{
                            visited.add(encodedPos);
                            queue.add(encodedPos);
                        }
                    }
                }
            }
            dis++;
        }
        return -1;
    }
    
    private void getSequences(String path, List<Character> keys, List<String> sequences){
        if(path.length() == keys.size()){
            sequences.add(path);
            return;
        }
        for(char key: keys){
            if(path.indexOf(key) == -1 && key != '@'){
                getSequences(path + Character.toString(key), keys, sequences);
            }
        }
    }
    
    public int shortestPathAllKeys(String[] grid) {
        List<Character> keys = new ArrayList<>();
        Map<Character, Integer> key2Position = new HashMap<>();
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length(); ++x){
                if(Character.isLowerCase(grid[y].charAt(x)) || grid[y].charAt(x) == '@'){
                    keys.add(grid[y].charAt(x));
                    key2Position.put(grid[y].charAt(x), y * grid[0].length() + x);
                }
            }
        }
        
        Map<String, Map<String, Integer>> shortestDistance = new HashMap<>();
        for(int i = 0; i < keys.size() - 1; ++i){
            for(int j = i + 1; j < keys.size() ; ++j){
                List<String> visited = new ArrayList<>();
                getVisited(i, j, keys, 0, visited, "");
                char start = keys.get(i);
                char end = keys.get(j);
                for(String visitedKeys: visited){
                    int minDistance = getMinDistance(start, end, grid, visitedKeys, key2Position);
                    if(minDistance != -1){
                        String start2End = Character.toString(start) + Character.toString(end);
                        shortestDistance.computeIfAbsent(start2End, key -> new HashMap<>()).put(visitedKeys, minDistance);
                        String end2Start = Character.toString(end) + Character.toString(start);
                        shortestDistance.computeIfAbsent(end2Start, key -> new HashMap<>()).put(visitedKeys, minDistance);
                    }
                }
            }
        }
        
        List<String> sequences = new ArrayList<>();
        getSequences("@", keys, sequences);
        int min = Integer.MAX_VALUE;
        for(String sequence: sequences){
            int distance = 0;
            for(int a = 0; a < sequence.length() - 1; ++a){
                String a2b = sequence.substring(a, a + 2);
                String acquiredKeys = sequence.substring(1, a + 1);
                String sortedKeys = getSorted(acquiredKeys);
                if(!shortestDistance.containsKey(a2b) || !shortestDistance.get(a2b).containsKey(sortedKeys)){
                    distance = Integer.MAX_VALUE;
                    break;
                }else{
                    distance += shortestDistance.get(a2b).get(sortedKeys);
                }
            }
            min = Math.min(min, distance);
        }
        return (min == Integer.MAX_VALUE)? -1: min;
    }
   
    public static void main(String[] args){
        String[] grid = {"@.a.#", "###.#","b.A.B"};
        Solution sol = new Solution();
        System.out.println("grid: " + Arrays.toString(grid));
        System.out.println("shortest path: " + sol.shortestPathAllKeys(grid));
    }
}
