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

struct region
{
	int	begin;
	int	end;
	region(int x, int y) : begin(x), end(y){}
};

class Solution 
{
public:

TreeNode* subTree(region& preorder, region& inorder)
{
	TreeNode* subroot;
	int root_val;
	int interval;
	int idx;
	region rinorder(-1,-1);
	region rpreorder(-1, -1);
	region linorder(-1, -1);
	region lpreorder(-1, -1);

	if(preorder.begin == -1)
	{
		return NULL;
	}

	/* Pinpoint the position "idx" of root_val in inorder */
	root_val = preordertrack.at(preorder.begin);
	
	for(idx = inorder.begin; idx <= (int)inorder.end; idx++)
	{
		if(inordertrack[idx] == root_val)
			break;
	}
	
	interval = idx - inorder.begin;

	/* Obtain the right subtree */
	if(idx < inorder.end)
	{
		rinorder.begin = inorder.begin + interval + 1;
		rinorder.end = inorder.end;
		rpreorder.begin = preorder.begin + interval + 1;
		rpreorder.end = preorder.end;
	}
	
	/* Obtain the left subtree */
	if(idx > inorder.begin)
	{
		linorder.begin = inorder.begin; 
		linorder.end = inorder.begin + interval - 1; 
		lpreorder.begin = preorder.begin + 1; 
		lpreorder.end = preorder.begin + interval; 
	}

	/* Generate the left sub-tree and right sub-tree */
	subroot = new TreeNode(root_val);
	subroot->left = (lpreorder.begin == -1)? NULL : subTree(lpreorder, linorder);
	subroot->right = (rpreorder.begin == -1)? NULL : subTree(rpreorder, rinorder);

	return subroot;
}

/* Tree constructor */
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) 
{
	region inorderpair(0, inorder.size() - 1);
	region preorderpair(0, preorder.size() - 1);
	TreeNode* root;

	if(preorder.empty())
	{
		return NULL;
	}
	
	/* Set the private date */
	preordertrack = preorder;
	inordertrack = inorder;
	lastidx = preorder.size() - 1 ;

	root = subTree(preorderpair, inorderpair);

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
	vector<int> preordertrack;
	vector<int> inordertrack;
	int lastidx;
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

#define NODENUM 21

int main()
{
    TreeNode* root;
	int prearray[]={5,1,0,3,2,4,6,8,7,9,10,11,12,13,14,15,16,17,18,19,20};
	int inarray[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
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
