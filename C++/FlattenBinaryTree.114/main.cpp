/* 
 * 1. Traverse the left offspring and push them into the "nodestack" and "prestack"
 * 2. Pop the "nodestack" and ckeck if it has right child, if yes go to step3, else go to step4
 * 3. Use its right child as the input parameter, and go back to step1
 * 4. Check if node stack empty, if yes, exit, else, go to step 2
 * 5. Reconstruct the tree using the nodes stored in "prestack"
 */

#include <iostream>
#include <vector>
#include <new>

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

	void flatten(TreeNode* root); 
	void preOrderRecursive(TreeNode *root);
	void reConstructTree(void);

private:
	/* The stack stored the Treenodes needed be traversed */
	vector<TreeNode*> nodestack;

	/* The stack stored the Treenodes pre-orderly */
	vector<TreeNode*> prestack;

};/*End of class Solution */

void Solution::flatten(TreeNode* root) 
{
	if(root != NULL)
	{
		/* Store the tree nodes in "prestack" pre-orderly*/
		preOrderRecursive(root);

		/* Reconstruct the tree in a flat form */
		reConstructTree();
	}
}

void Solution::preOrderRecursive(TreeNode *root)
{
	TreeNode *node;

	node = root;

	/* Push nodes along the left-child path */
	while(node != NULL)
	{
		nodestack.push_back(node);
		prestack.push_back(node);
		node = node->left;
	}

	/* Pop the nodes in nodestack until the stack empty or the node has right child */
	while(!nodestack.empty())
	{
		node = nodestack.back();
		nodestack.pop_back();
    
		if(node->right != NULL)
		{
			preOrderRecursive(node->right);
		}
	}
}

void Solution::reConstructTree()
{
	unsigned int idx;
	TreeNode *node;

	for(idx = 0; idx < (prestack.size() - 1); idx++)
	{
		node = prestack[idx];	
		node->left = NULL;
		node->right = prestack[idx + 1];
	}

	/* Assign NULL to the the offsprings of the last node */
	node = prestack[idx];	
	node->left = NULL;
	node->right = NULL;

	prestack.clear(); 
}

void dumpFlatTree(TreeNode *root)
{
	TreeNode *node;
	
	for(node = root; node != NULL; node = node->right)
	{
		cout << node->val << ",";
	}

	cout << endl;
}


int main()
{
	TreeNode* root;
	Solution sol;
    
	/* Generate a input tree
	 *     1
	 *    / \
	 *   2   5
	 *  / \   \
     * 3  4    6
	 *
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(5);
	root->left->left = new TreeNode(3);
	root->left->right = new TreeNode(4);
	root->right->right = new TreeNode(6);

	for(int i; i < 1000000; i++)
	{
    	/* Tree generation */
		sol.flatten(root);
	}

	/*Dump the answer */
	dumpFlatTree(root);

	return 0;
}
