/* Map:Time:O(n + m), Space:O(n + m),
 * 1. For x/y/diagnal/antidiagnal direction, use lampsOnCol(x)/lampsOnRow(y)/lampsOnDiagnal(y - x)/lampsOnAntidiagnal(y + x) to record the lamps#
 * 2. isOn[i] = 1, if any lamps# in 4 direction > 0
 * 3. Otherwise, isOn[i] = 0
 * 4. Update "lampsMap" from the 9 positions
 */          

import java.util.*; 
public class Solution {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Set<Integer>> lampsMap = new HashMap<>();
        Map<Integer, Integer> lampsOnCol = new HashMap<>();
        Map<Integer, Integer> lampsOnRow = new HashMap<>();
        Map<Integer, Integer> lampsOnDiagnal = new HashMap<>();
        Map<Integer, Integer> lampsOnAntidiagnal = new HashMap<>();
        for(int[] lamp: lamps){
            int y = lamp[0];
            int x = lamp[1];
            lampsMap.computeIfAbsent(y, key -> new HashSet<>()).add(x);
            lampsOnCol.putIfAbsent(x, 0);
            lampsOnCol.put(x, lampsOnCol.get(x) + 1);
            lampsOnRow.putIfAbsent(y, 0);
            lampsOnRow.put(y, lampsOnRow.get(y) + 1);
            lampsOnDiagnal.putIfAbsent(y - x, 0);
            lampsOnDiagnal.put(y - x, lampsOnDiagnal.get(y - x) + 1);
            lampsOnAntidiagnal.putIfAbsent(y + x, 0);
            lampsOnAntidiagnal.put(y + x, lampsOnAntidiagnal.get(y + x) + 1);
        }
        
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int[] isOn = new int[queries.length];
        for(int i = 0; i < queries.length; ++i){
            int y = queries[i][0];
            int x = queries[i][1];
            if(lampsOnCol.getOrDefault(x, 0) > 0 ||
               lampsOnRow.getOrDefault(y, 0) > 0 ||
               lampsOnDiagnal.getOrDefault(y - x, 0) > 0 ||
               lampsOnAntidiagnal.getOrDefault(y + x, 0) > 0){
                isOn[i] = 1;
            }
            for(int[] offset: offsets){
                int currY = y + offset[0];
                int currX = x + offset[1];
                if(lampsMap.containsKey(currY) && lampsMap.get(currY).contains(currX)){
                    lampsMap.get(currY).remove(currX);
                    lampsOnCol.put(currX, lampsOnCol.get(currX) - 1);
                    lampsOnRow.put(currY, lampsOnRow.get(currY) - 1);
                    lampsOnDiagnal.put(currY - currX, lampsOnDiagnal.get(currY - currX) - 1);
                    lampsOnAntidiagnal.put(currY + currX, lampsOnAntidiagnal.get(currY + currX) - 1);
                }
            }
        }
        return isOn;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] lamps = {{0, 0}, {4, 4}};
        int[][] queries = {{1, 1}, {1, 0}};
        System.out.print("lamps: ");
        for(int[] lamp: lamps){
            System.out.print(Arrays.toString(lamp) + ",");
        }
        System.out.print("\nqueries: ");
        for(int[] query: queries){
            System.out.print(Arrays.toString(query) + ",");
        }
        System.out.println("\nisOn: " + Arrays.toString(sol.gridIllumination(5, lamps, queries)));
    }
}
