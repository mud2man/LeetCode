/* Disjoint sets: O(e + nlogn)
 * 1. Create a component array, which every node is its component
 * 2. Union: merge component1 to component0 by comps[compId1] = comps[compId0];
 * 3. Find: loop until nodeId = comps[nodeId];
 * 4. Path compression: comps[nodeId] = comps[comps[nodeId]];
 */

import java.util.*;

public class Solution{
	public int find(int[] comps, int nodeId){
        
        while(comps[nodeId] != nodeId){
            nodeId = comps[nodeId];
            
            //path compression
            comps[nodeId] = comps[comps[nodeId]];
        }
        
        return nodeId;
    }
    
    public int countComponents(int n, int[][] edges) {
        int[] comps;
        int i;
        int compId0;
        int compId1;
        int count;
        
        comps = new int[n];
        count = n;
        
        for(i = 0; i < n; ++i){
            comps[i] = i;
        }
        
        for(int[] edge: edges){
            compId0 =  find(comps, edge[0]);
            compId1 =  find(comps, edge[1]);
            
            //union
            if(compId0 != compId1){
                count--;
                comps[compId1] = comps[compId0];
            }
        }
    
        return count;
    }

	public static void main(String[] args){
		Solution sol;
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
		int n;
		int count;
		
		n = 5;

		System.out.println("nodes: 0" + " to " + (n -1));
		System.out.println("edges: ");
		
		for(int[] edge: edges){
			System.out.println(Arrays.toString(edge));
		}
		
		sol = new Solution();	
		count = sol.countComponents(n, edges);

		System.out.println("#components: " + count);
	}
}
