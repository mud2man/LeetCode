/* 
 * 1. Use root as an input, and push all the elements along the left offspring to "nextList"
 * 2. If nextList is not empty, pop the top elemnet as the next smallest number
 * 3. After poping out, use the right child as an input, and push all the left childs again
 * 4. If the nextList is empty, there is no more next smallest number
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class pair 
{
	TreeNode first;
	int second;
	pair(TreeNode node, int level) {first = node; second = level;}
}

public class Solution 
{
	/* The history of visited node */
	private Stack<TreeNode> dfsHistory;

    Solution() 
	{
		dfsHistory = new Stack<TreeNode>();
	}

	protected void finalize() throws Throwable 
	{
	}

	public boolean dfsSearch(TreeNode root, TreeNode node, boolean isRecord)
	{
		if(root == null)
		{
			return false;
		}
    
		if(root == node)
		{
			return true;
		}
		
		if(isRecord)
		{
			dfsHistory.push(root);
		}
    
		/* Traverse lefts first*/
		if(root.left != null)
		{
			if(dfsSearch(root.left, node, isRecord))
			{
				return true;
			}
		}
		
		/* Traverse rights latter*/
		if(root.right != null)
		{
			if(dfsSearch(root.right, node, isRecord))
			{
				return true;
			}
		}
    
		if(isRecord)
		{
			dfsHistory.pop();
		}
    
		return false;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		TreeNode predecessor;
		TreeNode visited;
    
		dfsSearch(root, p , true);
		
		if(dfsSearch(p, q, false))
		{
			return p;
		}
		
		visited = p;
		predecessor = dfsHistory.pop();
    
		do
		{
			if(predecessor == q)
			{
				return q;
			}
			else
			{
				if(predecessor.left == visited)
				{
					if(dfsSearch(predecessor.right, q , false))
					{
						return predecessor;
					}
				}
				else
				{
					if(dfsSearch(predecessor.left, q , false))
					{
						return predecessor;
					}
				}
			}
			
			visited = predecessor;
			predecessor = dfsHistory.pop();
    
		}while(predecessor != null);

		return null;
	}
	
	public static void main(String[] args)
	{

		TreeNode root;
		TreeNode lca;
		Solution sol;
        
		/* Generate a input tree
		 *     8
		 *    / \
		 *   3   10
		 *  / \   \
		 * 1   6   14
		 *    / \ 
		 *   4   7 
		 */
		root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.right.right = new TreeNode(14);
		root.left.right.left = new TreeNode(4);
		root.left.right.right = new TreeNode(7);

		sol = new Solution();
		lca = sol.lowestCommonAncestor(root, root.left.left, root.left.right.right);

		System.out.println(lca.val);
		
		sol = null;
	}
}
