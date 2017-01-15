/* Sort: O(nlogn)
 * 1. Sort people groups by their "h".
 * 2. Sort people in the same group by their "k" 
 * 2. Insert to the new list by their k from the tallest people group
 * 3. Since the order of taller people groups will not be affected by shorter people 
 *
 * ex: input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *     sorted:[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]] 
 *    
 *    time[0]: people=[[7,0]]; remain=[[7,1], [6,1], [5,0], [5,2], [4,4]]
 *    time[1]: people=[[7,0], [7,1]]; remain=[[6,1], [5,0], [5,2], [4,4]]
 *    time[2]: people=[[7,0], [6,1], [7,1]]; remain=[[5,0], [5,2], [4,4]]
 *    time[3]: people=[[5,0], [7,0], [6,1], [7,1]]; remain=[[5,2], [4,4]]
 *    time[4]: people=[[5,0], [7,0], [5,2], [6,1], [7,1]]; remain=[[4,4]]
 *    time[5]: people=[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]; remain=[]
 */

import java.util.*;

class HeightComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] o1, int[] o2) {
        if(o1[0] != o2[0])
            return o2[0] - o1[0];
        else
            return o1[1] - o2[1];
    }
}

public class Solution{
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list;
        int[] person;
        int i;
        
        list = new LinkedList<int[]>();
        
        Arrays.sort(people, new HeightComparator());    
        
        for(int[] p: people){
            list.add(p[1], p);
        }
        
        for(i = 0; i < people.length; ++i){
            people[i] = list.get(i);
        }
        
        return people;
    }

    public static void main(String[] args){
        int i;
        Solution sol;
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};

        sol = new Solution();
        
        System.out.println("before: ");
        for(int[] p: people){
            System.out.print(Arrays.toString(p) + ", ");
        }
        System.out.println("");

        people = sol.reconstructQueue(people);

        System.out.println("after: ");
        for(int[] p: people){
            System.out.print(Arrays.toString(p) + ", ");
        }
        System.out.println("");
    }
}
