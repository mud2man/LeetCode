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

struct pair_t
{
	int	begin;
	int	end;
	pair_t(int x, int y) : begin(x), end(y){}
};
	

class Solution 
{
public:

/* Tree constructor */
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) 
{
	pair_t *rrinorder, *rrpreorder, *rlinorder, *rlpreorder;
	pair_t *lrinorder, *lrpreorder, *llinorder, *llpreorder;
	pair_t *rinorder, *rpreorder, *linorder, *lpreorder;
	pair_t *pinorder, *ppreorder;
	TreeNode* root;
	TreeNode* subroot;

	if(preorder.empty())
		return NULL;

	preordertrack = preorder;
	inordertrack = inorder;
	lastidx = preorder.size() - 1 ;

	pinorder = new pair_t(0, lastidx);
	ppreorder = new pair_t(0, lastidx);
	treestack.push_back(*pinorder); 
	treestack.push_back(*ppreorder);

	root = subtree(rinorder, rpreorder, linorder, lpreorder);
	
	if(root != NULL)
	{
		nodestack.push_back(root);

		/* Push back the subtree */
		treestack.push_back(*rinorder); 
		treestack.push_back(*rpreorder); 
		treestack.push_back(*linorder); 
		treestack.push_back(*lpreorder);
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
			treestack.push_back(*rrinorder); 
			treestack.push_back(*rrpreorder); 
			treestack.push_back(*rlinorder); 
			treestack.push_back(*rlpreorder);
		}

		if(subroot->left != NULL)
		{
			nodestack.push_back(subroot->left);

			/* Push back the left subtree */
			treestack.push_back(*lrinorder); 
			treestack.push_back(*lrpreorder); 
			treestack.push_back(*llinorder); 
			treestack.push_back(*llpreorder);
		}
	}
	
	return root;
}
TreeNode* subtree(pair_t* &rinorder, pair_t* &rpreorder, 
			      pair_t* &linorder, pair_t* &lpreorder)
{
	pair_t inorder(-1, -1);
	pair_t preorder(-1, -1);
	TreeNode* root;
	int root_val;
	int idx;
	int interval;

	preorder = treestack.back();
	treestack.pop_back();
	inorder = treestack.back();
	treestack.pop_back();
		
	if(inorder.begin == -1)
	{
		return NULL;
	}

	/* Pinpoint the position "idx" of root_val in inorder */
	root_val = preordertrack.at(preorder.begin);
	for(idx = inorder.begin; idx <= inorder.end; idx++)
	{
		if(inordertrack.at(idx) == root_val)
			break;
	}
	interval = idx - inorder.begin;

	/* Obtain the right subtree */
	if(idx < inorder.end)
	{
		rinorder = new pair_t(inorder.begin + interval + 1, inorder.end);
		rpreorder = new pair_t(preorder.begin + interval + 1, preorder.end);
	}
	else
	{
		rinorder = new pair_t(-1,-1);
		rpreorder = new pair_t(-1, -1);
	}
	
	/* Obtain the left subtree */
	if(idx > inorder.begin)
	{
		linorder = new pair_t(inorder.begin, inorder.begin + interval - 1); 
		lpreorder = new pair_t(preorder.begin + 1, preorder.begin + interval); 
	}
	else
	{
		linorder = new pair_t(-1, -1);
		lpreorder = new pair_t(-1, -1);
	}

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
	vector<pair_t> treestack;

	/* The stack stored nodes waiting for connecting to thier children */
	vector<TreeNode*> nodestack;

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
