/* Use topological sort
 * 1. Construct the graph via 2-D array, the first element in every row is in-degree
 * 2. The following elements are its successor
 * 3. Iterate all the nodes, and put the node with in-degree=0 into the stack
 * 4. Go into a "for" loop with the iteration number = #nodes, 
 * 5. If the stack become empty in the middle of loop, cycle exist
 */

import java.util.*; // Stack

public class Solution 
{
	/* The graph data structure */
	private List<List<Integer>> graph;

	/* The stack storing the node with in-degree=0 */
	private Stack<Integer> zeroInDegrees;
	
    Solution() 
	{
		zeroInDegrees = new Stack<Integer>();
	}

	public void graphGen(int numCourses, int[][] prerequisites)
	{
		int edgenum;
		int idx;
		int source;
		int target;
		List<Integer> itr;

		edgenum = prerequisites.length;
		
		graph = new ArrayList<List<Integer>>(numCourses);

		for(idx = 0; idx < numCourses; idx++)
		{
			graph.add(new ArrayList<Integer>());	
			graph.get(idx).add(0);
		}
		
		for(idx = 0; idx < edgenum; idx++)
		{
			source = prerequisites[idx][1];		
			target = prerequisites[idx][0];
			
			itr = graph.get(target);
			itr.set(0, itr.get(0) + 1);
			itr = graph.get(source);
			itr.add(target);
		}
	}

	public boolean canFinish(int numCourses, int[][] prerequisites)
	{
		int idx;
		int nodeId;
		int outerIdx;
		int successor;

		/* Consruct graph */
		graphGen(numCourses, prerequisites);
		
		/* Push all the nodes with indegree=0 to the stack */
		for(idx = 0; idx < graph.size(); idx++)
		{
			if(graph.get(idx).get(0) == 0)
			{
				zeroInDegrees.push(idx);
			}
		}
	
		/* BFS traversal guarantee that there is must al leastone  node with indegree = 0 */
		for(idx = 0; idx < graph.size(); idx++)
		{
			if(zeroInDegrees.empty())
			{
				return false;
			}
			
			System.out.println(zeroInDegrees);
			nodeId = zeroInDegrees.pop();

			/* Visit all the sucessors */
			for(outerIdx = 1; outerIdx < graph.get(nodeId).size(); outerIdx++)
			{
				successor = graph.get(nodeId).get(outerIdx);
				graph.get(successor).set(0, graph.get(successor).get(0) - 1);

				if(graph.get(successor).get(0) == 0)
				{
					zeroInDegrees.push(successor);
				}
			}
		}

		return true;
	}
	
	public static void main(String[] args)
	{
		final int numCourses = 5;
		Solution sol;
		int[][] prerequisites;
		boolean canfinish; 

		/*	Relationship graph
		 *         0
		 *        / \
		 *       @   @
		 *      1     2
		 *     /@     /
		 *    @ |    /
		 *   3  |   /
		 *    \ |  /
		 *	   @| @
		 *	    4
		 */
		prerequisites = new int [][] 
		{
			{1, 0}, {2, 0}, {3, 1}, {4, 3}, {1, 4} 
		};

		sol = new Solution();
		canfinish = sol.canFinish(numCourses, prerequisites);
		
		if(canfinish)
		{
			System.out.println("No conflict!!!");
		}
		else
		{
			System.out.println("Conflict!!!");
		}
	}
}
