/* 
 * 1. Use root as an input, and push all the elements along the left offspring to "nextList"
 * 2. If nextList is not empty, pop the top elemnet as the next smallest number
 * 3. After poping out, use the right child as an input, and push all the left childs again
 * 4. If the nextList is empty, there is no more next smallest number
 */

#include <iostream>
#include <vector>

using namespace std;

struct TreeNode 
{
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class BSTIterator
{

public:
	BSTIterator(TreeNode *);
	~BSTIterator(void);
	bool hasNext(void);
	int next(void);
	void dump();

private:
	/* Utilities function */
	void pushLefts(TreeNode*);

	/* Store the elements along left offsprings */
	vector<TreeNode*> nextList;

};/*End of class BSTIterator */

BSTIterator::BSTIterator(TreeNode *root)
{
	pushLefts(root);
}

BSTIterator::~BSTIterator(void)
{
	nextList.clear();
}

void BSTIterator::pushLefts(TreeNode *node)
{
	while(node != NULL)
	{
		nextList.push_back(node);
		node = node->left;
	}
}

bool BSTIterator::hasNext(void)
{
	if(!nextList.empty())
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

int BSTIterator::next(void)
{
	TreeNode *node;

	node = nextList.back();
	nextList.pop_back();

	if(node->right != NULL)
	{
		pushLefts(node->right);
	}

	return node->val;
}

void BSTIterator::dump(void)
{
	vector<TreeNode*>::iterator it;

	for(it = nextList.begin(); it != nextList.end(); it++)
	{
		cout << (*it)->val << endl;
	}

	cout << endl;
}

int main()
{
	TreeNode* root;
	BSTIterator *itr;
    
	/* Generate a input tree
	 *     8
	 *    / \
	 *   3   10
	 *  / \   \
	 * 1   6   14
	 *    / \  /
	 *   4   7 13 
	 */
	root = new TreeNode(8);
	root->left = new TreeNode(3);
	root->right = new TreeNode(10);
	root->left->left = new TreeNode(1);
	root->left->right = new TreeNode(6);
	root->right->right = new TreeNode(14);
	root->left->right->left = new TreeNode(4);
	root->left->right->right = new TreeNode(7);
	root->right->right->left = new TreeNode(13);

	itr = new BSTIterator(root);
	
	while (itr->hasNext())
	{
		cout << "Next smallest number: " << itr->next() << endl;
	}

	return 0;
}
