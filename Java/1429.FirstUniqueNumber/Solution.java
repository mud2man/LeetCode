/* Hash: Time:O(1), Space:O(n)
 * 1. Use LinkedHashSet to remember the unique number
 * 2. Use Set to remember the duplicates
 * 3. Update LinkedHashSet and Set when add was called
 */     

import java.util.*; // Stack

public class Solution {
    LinkedHashSet<Integer> uniques;
    Set<Integer> duplicates;
    public Solution(int[] nums) {
        uniques = new LinkedHashSet<>();
        duplicates = new HashSet<>();
        for(int num : nums){
            add(num);
        }
    }
    
    public int showFirstUnique() {
        return (uniques.isEmpty())? -1: uniques.iterator().next();
    }
    
    public void add(int value) {
        if(duplicates.contains(value)){
            return;
        }else if(uniques.contains(value)){
            uniques.remove(value);
            duplicates.add(value);
        }else{
            uniques.add(value);  
        }
    }  
    public static void main(String[] args){
        int[] nums = {2, 3, 5};
        int n = 0;
        Solution sol = new Solution(nums);
        System.out.println("showFirstUnique:" + sol.showFirstUnique());
        n = 5;
        sol.add(n);
        System.out.println("add(" + n + ")");
        System.out.println("showFirstUnique:" + sol.showFirstUnique());
        n = 2;
        sol.add(n);
        System.out.println("add(" + n + ")");
        System.out.println("showFirstUnique:" + sol.showFirstUnique());
        n = 3;
        sol.add(n);
        System.out.println("add(" + n + ")");
        System.out.println("showFirstUnique:" + sol.showFirstUnique());
    }
}
