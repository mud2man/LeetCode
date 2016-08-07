/* 
 * 1. Insert the right child of root into the queue "leverOrderQue", and the left one latter
 * 2. Pop the node in levelOrderQue, and check whether the level > current level
 * 3. If Yes, push the node->val into return vector
 */

#include <iostream>
#include <vector>
#include <deque>
#include <new>
#include <utility> /* pair */

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
	vector<int> rightSideView(TreeNode* root); 
	void dump(void);

private:
	/* The level order queue */
	deque< pair<TreeNode*, int> > levelOrderQue;
	
	/* The list of right-side nodes */
	vector<int> rightSideList;
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
	levelOrderQue.clear();
	rightSideList.clear();
}

vector<int> Solution::rightSideView(TreeNode* root) 
{
	int currLev;
	int nodeLev;
	TreeNode* ptrNode;
	pair<TreeNode*, int> nodePair;

	if(root == NULL)
	{
		return rightSideList;
	}
	
	/* Init levelOrderQue */
	currLev = -1;
	nodePair = make_pair(root, 0);
	levelOrderQue.push_front(nodePair);

	while(!levelOrderQue.empty())
	{
		nodePair = levelOrderQue.back();
		levelOrderQue.pop_back();
		
		ptrNode = nodePair.first;
		nodeLev = nodePair.second;
		
		/* Right-side node found */
		if(nodeLev > currLev)
		{
			rightSideList.push_back(ptrNode->val);
			currLev++;
		}
		
		/* Push right first */
		if(ptrNode->right != NULL)
		{
			nodePair = make_pair(ptrNode->right, nodeLev + 1);
			levelOrderQue.push_front(nodePair);
		}
		
		/* Push left latter */
		if(ptrNode->left != NULL)
		{
			nodePair = make_pair(ptrNode->left, nodeLev + 1);
			levelOrderQue.push_front(nodePair);
		}
	}

	return rightSideList;
}

void Solution::dump(void)
{
	vector<int>::iterator it;

	for(it=rightSideList.begin(); it != rightSideList.end(); it++)
	{
		cout << *it << ",";
	}
}


int main()
{
	TreeNode* root;
	Solution* sol;
	vector<int> rightSideList;
    
	/* Generate a input tree
	 *     1
	 *    / \
	 *   2   6
	 *  / \   \
     * 3   4   7
	 *      \
	 *       5
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(6);
	root->left->left = new TreeNode(3);
	root->left->right = new TreeNode(4);
	root->right->right = new TreeNode(7);
	root->left->right->right = new TreeNode(5);

    /* Right side list generation */
	sol = new Solution();
	rightSideList = sol->rightSideView(root);

	/* Dump the answer */
	sol->dump();

	delete sol;

	return 0;
}
