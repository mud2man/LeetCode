/* Use DFS: O(n)
 * 1. Use DFS search to locate any one the targets, and keep trace via stack
 * 2. If hitted, use DFS again to check if the other target B in the subtree with root A
 * 3. If not, return and pop the stack, in the same time, search the other side
 */

#include <iostream>
#include <new>
#include <stack>

using namespace std;

struct TreeNode
{
	int	val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution 
{
public:
	Solution();
	~Solution();
	TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q);
	bool dfsSearch(TreeNode* root, TreeNode* node, bool isRecord);
	void dump(TreeNode* root);

private:
	stack<TreeNode *> dfsHistory;

};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

TreeNode* Solution::lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q)
{
	TreeNode* predecessor;
	TreeNode* visited;

	dfsSearch(root, p , 1);
	
	if(dfsSearch(p, q, 0))
	{
		return p;
	}
	
	visited = p;
	predecessor = dfsHistory.top();
	dfsHistory.pop();

	do
	{
		if(predecessor == q)
		{
			return q;
		}
		else
		{
			if(predecessor->left == visited)
			{
				if(dfsSearch(predecessor->right, q , 0))
				{
					return predecessor;
				}
			}
			else
			{
				if(dfsSearch(predecessor->left, q , 0))
				{
					return predecessor;
				}
			}
		}
		
		visited = predecessor;
		predecessor = dfsHistory.top();
		dfsHistory.pop();

	}while(predecessor != NULL);

	return NULL;
}

bool Solution::dfsSearch(TreeNode* root, TreeNode* node, bool isRecord)
{
	if(root == NULL)
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
	if(root->left != NULL)
	{
		if(dfsSearch(root->left, node, isRecord))
		{
			return true;
		}
	}
	
	/* Traverse rights latter*/
	if(root->right != NULL)
	{
		if(dfsSearch(root->right, node, isRecord))
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

void Solution::dump(TreeNode* root)
{
}

int main()
{
	TreeNode* root;
	TreeNode* lca;
	Solution* sol;
    
	/* Generate a input tree
	 *      1
	 *     / \
	 *    2   3
	 *     \
     *      4
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->left->right = new TreeNode(4);
	sol = new Solution();

	lca = sol->lowestCommonAncestor(root, root->left->right, root);

	cout << "LCA: "<< lca->val << endl;
	
	return 0;
}
