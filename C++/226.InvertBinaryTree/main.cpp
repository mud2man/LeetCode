/* Use recursive method: O(n)
 * 1. Exchnage the children and use DFS to traversal
 */

#include <iostream>
#include <new>
#include <vector>

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
	TreeNode* invertTree(TreeNode* root);
	void dump(TreeNode* root);

private:
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

TreeNode* Solution::invertTree(TreeNode* root)
{
	TreeNode *ptrNode;
	
	if(root == NULL)
	{
		return NULL;
	}

	ptrNode = root->left;
	root->left = root->right;
	root->right = ptrNode;

	if(root->left != NULL)
	{
		invertTree(root->left);
	}

	if(root->right != NULL)
	{
		invertTree(root->right);
	}

	return root;
}

void Solution::dump(TreeNode* root)
{
	TreeNode *ptrNode;

	ptrNode = root;

	cout << ptrNode->val << ",";

	if(ptrNode->left != NULL)
	{
		dump(ptrNode->left);
	}

	if(ptrNode->right != NULL)
	{
		dump(ptrNode->right);
	}
}

int main()
{
	TreeNode* root;
	Solution* sol;
    
	/* Generate a input tree
	 *      1
	 *     / \
	 *    2   6
	 *   / \  /
     *  4   7 5  
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(6);
	root->left->left = new TreeNode(4);
	root->left->right = new TreeNode(7);
	root->right->left = new TreeNode(5);
	sol = new Solution();
	
	sol->dump(root);
	cout << endl;

	root = sol->invertTree(root);
	
	sol->dump(root);
	cout << endl;
	
	return 0;
}
