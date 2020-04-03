/* Map:Time:O(n), Space:O(n)
 * 1. Have 2 hepers "check2"/"check1" to check if the row accommodate 2/1 family
 * 2. Scan row wich has reserved set, and we decrease sets#(from n * 2) based on the check result
 */          

import java.util.*; 
public class Solution {
    private boolean check2(int row, Map<Integer, Set<Integer>> row2ReservedSeats){
        Set<Integer> reservedSeats = row2ReservedSeats.getOrDefault(row, new HashSet<>());
        for(int col = 2; col <= 9; ++col){
            if(reservedSeats.contains(col)){
                return false;
            }
        }
        return true;
    }
    
    private boolean check1(int row, Map<Integer, Set<Integer>> row2ReservedSeats){
        Set<Integer> reservedSeats = row2ReservedSeats.getOrDefault(row, new HashSet<>());
        for(int start = 2; start <= 6; start += 2){
            boolean hit = false;
            for(int col = start; col < start + 4; ++col){
                if(reservedSeats.contains(col)){
                    hit = true;
                    break;
                }
            }  
            if(!hit){
                return true;
            }
        }
        return false;
    }
    
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> row2ReservedSeats = new HashMap<>();
        Set<Integer> reservedRows = new HashSet<>();
        for(int[] reservedSeat: reservedSeats){
            row2ReservedSeats.computeIfAbsent(reservedSeat[0], key -> new HashSet<>()).add(reservedSeat[1]);
            reservedRows.add(reservedSeat[0]);
        }
        int count = n * 2;
        for(int row: reservedRows){
            if(check2(row, row2ReservedSeats)){
                count -= 0;
            }else if(check1(row, row2ReservedSeats)){
                count -= 1;
            }else{
                count -= 2;
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] reservedSeats = {{1,2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        int n = 3;
        System.out.print("reserved seats:");
        for(int[] reservedSeat: reservedSeats){
            System.out.print(Arrays.toString(reservedSeat) + ",");
        }
        System.out.println("\nn:" + n);
        System.out.println("family#:" + sol.maxNumberOfFamilies(n, reservedSeats));
    }
}
