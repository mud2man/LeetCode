/* Use loop method
 * 1. Pick up the root node from preorder and inorder, and store it into node stack
 * 2. Store the right subtree first and left subtree later into subtree stack
 * 3. Enter the while loop until the node stack empty
 * 4. Pick up the left and right child from (0th,1st) and (2nd,3rd) subtree stack
 * 5. Build the link between child and root
 * 6. Store child nodes into node stack
 * 7. Store right and left subtrees into stack orderly
 * 8. Go to step3
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
	vector<int> *rrinorder, *rrpreorder, *rlinorder, *rlpreorder;
	vector<int> *lrinorder, *lrpreorder, *llinorder, *llpreorder;
	vector<int> *rinorder, *rpreorder, *linorder, *lpreorder;
	vector<int> *ptrinorder, *ptrpreorder;
	vector<int>::iterator it;
	vector< vector<int>* > itx;
	TreeNode* root;
	TreeNode* subroot;

	ptrpreorder = &preorder;
	ptrinorder = &inorder;

	treestack.push_back(ptrinorder); 
	treestack.push_back(ptrpreorder);

	root = subtree(rinorder, rpreorder, linorder, lpreorder);
	
	if(root != NULL)
	{
		nodestack.push_back(root);

		/* Push back the right subtree */
		treestack.push_back(rinorder); 
		treestack.push_back(rpreorder); 
		treestack.push_back(linorder); 
		treestack.push_back(lpreorder);
	}
	else
	{
		return NULL;
	}

	/* Looping until nodestack empty*/
	while(!nodestack.empty())
	{
		/* Prepare the (n+1)-th level */
		subroot = nodestack.back();
		nodestack.pop_back();
		subroot->left = subtree(lrinorder, lrpreorder, llinorder, llpreorder);
		subroot->right = subtree(rrinorder, rrpreorder, rlinorder, rlpreorder);
		
		/* Push back the (n+1)-th level into stack*/
		if(subroot->right != NULL)
		{
			nodestack.push_back(subroot->right);

			/* Push back the right subtree */
			treestack.push_back(rrinorder); 
			treestack.push_back(rrpreorder); 
			treestack.push_back(rlinorder); 
			treestack.push_back(rlpreorder);
		}

		if(subroot->left != NULL)
		{
			nodestack.push_back(subroot->left);

			/* Push back the left subtree */
			treestack.push_back(lrinorder); 
			treestack.push_back(lrpreorder); 
			treestack.push_back(llinorder); 
			treestack.push_back(llpreorder);
		}
	}
	
	return root;
}
TreeNode* subtree(vector<int>* &rinorder, vector<int>* &rpreorder, 
				vector<int>* &linorder, vector<int>* &lpreorder)
{
	vector<int> *inorder, *preorder;
	vector<int>::iterator it;
	TreeNode* root;
	int root_val;
	int idx;

	preorder = treestack.back();
	treestack.pop_back();
	inorder = treestack.back();
	treestack.pop_back();
	
	if(preorder->empty())
	{
		return NULL;
	}

	/* Pinpoint the position "idx" of root_val in inorder */
	root_val = preorder->at(0);
	for(idx = 0; idx < (int)inorder->size(); idx++)
	{
		if(inorder->at(idx) == root_val)
			break;
	}
	
	it = inorder->begin();
	rinorder = new vector<int>; 
	rinorder->assign(it + idx + 1, inorder->end());
	
	it = preorder->begin();
	rpreorder = new vector<int>; 
	rpreorder->assign(it + idx + 1, preorder->end());
    
	it = inorder->begin();
	linorder = new vector<int>; 
	linorder->assign(it, it + idx);
	
	it = preorder->begin();
	lpreorder = new vector<int>; 
	lpreorder->assign(it + 1, it + idx + 1);

	/* Free memory */
	preorder->clear();
	inorder->clear();
	root = new TreeNode(root_val);

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
	/* The stack stored the subtrees */
	vector< vector<int>* > treestack;

	/* The stack stored nodes waiting for connecting to thier children */
	vector<TreeNode*> nodestack;
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
