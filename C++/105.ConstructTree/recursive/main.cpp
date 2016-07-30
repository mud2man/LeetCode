/* Use recursive method
 * 1. Select the first element "A" in preorder 
 * 2. Search the element "A', and pinpoint its position as i-th element in inorder
 * 3. Select the leftest i elements from inorder, and names it left_inorder
 * 4. Select the rightest elements after i-th elements, and names it right_inorder
 * 5. Select the next i elements in preorder, and name it left_preorder
 * 6. Select the residures in preorder, and name it right_preorder
 * 7. Call buildTree with left_inorder and left_inorder, and assign the returned 
 *    node to the left child of element "A"
 * 8. Call buildTree with right_inorder and right_inorder, and assign the returned 
 *    node to the right child of element "A"
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

/* Tree constructor */
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) 
{
	vector<int> left_inorder;
	vector<int> left_preorder;
	vector<int> right_inorder;
	vector<int> right_preorder;
	vector<int>::iterator it;
	int root_val;
	int idx;
	TreeNode* root;

	if(preorder.empty())
	{
		return NULL;
	}

	/* Pinpoint the position "idx" of root_val in inorder */
	root_val = preorder.at(0);

	for(idx = 0; idx < (int)inorder.size(); idx++)
	{
		if(inorder.at(idx) == root_val)
			break;
	}

	/* Select the leftest i elements from inorder as the left_inorder */
	it = inorder.begin();
	left_inorder.assign(it, it + idx);
	
	/* Select the rightest elements after i-th elements as the right_inorder */
	right_inorder.assign(it + idx + 1, inorder.end());
    
	/* Select the next i elements afte in preorder as the left_preorder */
	it = preorder.begin();
	left_preorder.assign(it + 1, it + 1 + idx);
	
	/* Select the residures in preorder as the right_preorder */
	right_preorder.assign(it + idx + 1, preorder.end());
	
	/* Destory the previous vector */
	preorder.clear();
	inorder.clear();

	/* Generate the left sub-tree and right sub-tree */
	root = new TreeNode(root_val);
	root->left = buildTree(left_preorder, left_inorder);
	root->right = buildTree(right_preorder, right_inorder);

	return root;
}

void preorderTrav(TreeNode* root)
{
	vector<TreeNode*> stack;
	TreeNode* ptrNode;
	
	cout << "Preorder traverse: ";
	stack.push_back(root);
	
	while(!stack.empty())
	{
		ptrNode = stack.back();
		cout << ptrNode->val << "," ;
		stack.pop_back();
		
		if(ptrNode->right != NULL)
		{
			stack.push_back(ptrNode->right);
		}
		
		if(ptrNode->left != NULL)
		{
			stack.push_back(ptrNode->left);
		}
	}
	cout << endl;
}

private:
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

#define NODENUM 9

int main()
{
    TreeNode* root;
	int prearray[]={5,1,0,3,2,4,6,8,7};
	int inarray[]={0,1,2,3,4,5,6,7,8};
	vector<int> preorder(&(prearray[0]), &prearray[NODENUM]);
	vector<int> inorder(&(inarray[0]), &inarray[NODENUM]);
    Solution sol;

	/* Dump vecotrs */
    dumpvec(preorder);
    dumpvec(inorder);

    /* Tree generation */
	root = sol.buildTree(preorder, inorder);

	/*Dump the answer */
	sol.preorderTrav(root);

	return 0;
}
