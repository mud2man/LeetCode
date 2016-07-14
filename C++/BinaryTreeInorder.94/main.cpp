/* 
 * 1. Traverse the left offspring and push them into the stack
 * 2. Pop the stack and put it into the output vector
 * 3. Conduct the "inorderTraversal" and use its right child as the input parameter
 * 4. Repeat step 1 ~ 3 until the stack is empty 
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

vector<int> inorderTraversal(TreeNode* root) 
{
	if(root != NULL)
	{
		inorderRecursive(root);
	}
	
	return inorder;
}

void inorderRecursive(TreeNode *root)
{
	TreeNode *node;

	node = root;

	/* Push nodes along the left-child path */
	while(node != NULL)
	{
		nodestack.push_back(node);
		node = node->left;
	}

	/* Pop the nodes in nodestack until the stack empty or the node has right child */
	while(!nodestack.empty())
	{
		node = nodestack.back();
		nodestack.pop_back();
		inorder.push_back(node->val);
    
		if(node->right != NULL)
		{
			inorderRecursive(node->right);
		}
	}
}

private:
	/* The stack stored the Treenodes needed be traversed */
	vector<TreeNode*> nodestack;

	/* The inorder traversal */
	vector<int> inorder;

};/*End of class Solution */

void dumpvec(vector<int>& vec)
{
	vector<int>::iterator it;

	for(it = vec.begin(); it != vec.end(); it++)
	{
		cout << *it << ",";
	}

	cout << endl;
}


int main()
{
	TreeNode* root;
	Solution sol;
	vector<int> inorder;
    
	/* Generate a input tree
	 *     1
	 *    / \
	 *   2   3
	 *  / \  /\
	 * 4  5 6  7
	 *
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->left->left = new TreeNode(4);
	root->left->right = new TreeNode(5);
	root->right->left = new TreeNode(6);
	root->right->right = new TreeNode(7);


    /* Tree generation */
	inorder = sol.inorderTraversal(root);

	/*Dump the answer */
	dumpvec(inorder);

	return 0;
}
