/* Use Devide and Counquer
 * case 1: robber didn't rob the root => money = rob(left) + rob(right)
 * case 2: robber robbe the root => money = root->val + rob(left->left) + rob(left->right) + 
 *          rob(right->left) + rob(right->right)
 * Money = max(case1, case2)
 * Create a variable in every node to store the robbed money to void re-rob
 */

#include <iostream>
#include <algorithm>

using namespace std;

struct TreeNode{
	int	val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

struct exTreeNode{
	int	val;
	int	max;
	exTreeNode *left;
	exTreeNode *right;
	exTreeNode(int x) : val(x), max(-1), left(NULL), right(NULL) {}
};


class Solution {

public:
	Solution();
	~Solution();
	int rob(TreeNode* root);
	int exrob(exTreeNode* exRoot);
	exTreeNode* copyTree(TreeNode* root);
	void preorder(exTreeNode* exRoot);
	void dump();

private:
	exTreeNode* exRoot;
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

exTreeNode* Solution::copyTree(TreeNode* root){
	exTreeNode* exNode;

	exNode = NULL;

	if(root != NULL){
		exNode = new exTreeNode(root->val);
		exNode->left = copyTree(root->left); 
		exNode->right = copyTree(root->right); 
	}

	return exNode; 
}

int Solution::exrob(exTreeNode* exRoot){
	int robRoot;
	int noRobRoot;
	exTreeNode* right = NULL;
	exTreeNode* left = NULL;
	exTreeNode* rightRight = NULL;
	exTreeNode* rightLeft = NULL;
	exTreeNode* leftRight = NULL;
	exTreeNode* leftLeft = NULL;
	
	if(exRoot == NULL){
		return 0;
	}

	if(exRoot->right != NULL){
		right = exRoot->right;
		if(right->right != NULL){
			rightRight = right->right;
		}
		if(right->left != NULL){
			rightLeft = right->left;
		}
	}

	if(exRoot->left != NULL){
		left = exRoot->left;
		if(left->right != NULL){
			leftRight = left->right;
		}
		if(left->left != NULL){
			leftLeft = left->left;
		}
	}

	/* The money if robber rob the root */
	robRoot = exRoot->val;
	if((rightRight != NULL)){
		if(rightRight->max == -1)
			rightRight->max = exrob(rightRight);
		robRoot += rightRight->max;
	}

	if(rightLeft != NULL){
		if(rightLeft->max == -1)
			rightLeft->max = exrob(rightLeft);
		robRoot += rightLeft->max;
	}

	if(leftRight != NULL){
		if(leftRight->max == -1)
			leftRight->max = exrob(leftRight);
		robRoot += leftRight->max;
	}

	if(leftLeft != NULL){
		if(leftLeft->max == -1)
			leftLeft->max = exrob(leftLeft);
		robRoot += leftLeft->max;
	}
	
	/* The money if the robber didn't rob the root */
	noRobRoot = 0;
	if(right != NULL){
		if(right->max == -1)
			right->max = exrob(right);
		noRobRoot += right->max;
	}

	if(left != NULL){
		if(left->max == -1)
			left->max = exrob(left);
		noRobRoot += left->max;
	}

	if(robRoot > noRobRoot){
		return robRoot;
	}
	else{
		return noRobRoot;
	}
}

int Solution::rob(TreeNode* root){
	exRoot = copyTree(root);

	return exrob(exRoot);
}

void Solution::preorder(exTreeNode* exRoot){

	if(exRoot != NULL){
		cout << exRoot->val << ", ";
		preorder(exRoot->left);
		preorder(exRoot->right);
	}
	else{
		return;
	}
}

void Solution::dump(){
	preorder(exRoot);	
}

int main(){
	int money;
	TreeNode* root;
	Solution sol;
    
	/* Generate a input tree
	 *      8
	 *     / \
	 *    2   3
	 *     \
     *      4
	 *	   / \
	 *    9   10
	 */
	root = new TreeNode(8);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->left->right = new TreeNode(4);
	root->left->right->right = new TreeNode(10);
	root->left->right->left = new TreeNode(9);

	money = sol.rob(root);

	cout << "Money: " << money << endl; 
	
	return 0;
}
