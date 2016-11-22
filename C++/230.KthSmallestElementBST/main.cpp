/* O(n)
 * 1. Clone the tree, and augment the information of #nodes in every node
 * 2. Caculate the information of #nodes
 * 3. Traverse the tree based on the information of #nodes
 */

#include <iostream>
#include <new>

using namespace std;

struct TreeNode{
	int	val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

struct cloneTreeNode {
	int val;
	int num;
	cloneTreeNode *left;
	cloneTreeNode *right;
	cloneTreeNode(int x, int y) : val(x), num(y), left(NULL), right(NULL) {}
 };

class Solution {

public:
	Solution();
	~Solution();
    cloneTreeNode* cloneTree(TreeNode *root);
    void deleteTree(cloneTreeNode *root);
    int caculate(cloneTreeNode* root);
    int findKth(cloneTreeNode *root, int k);
	int kthSmallest(TreeNode* root, int k);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

cloneTreeNode* Solution::cloneTree(TreeNode *root){
    cloneTreeNode *cloneRoot;
    
    if(root == NULL){
        return NULL;
    }
    
    cloneRoot = new cloneTreeNode(root->val, -1);
    cloneRoot->left = cloneTree(root->left);
    cloneRoot->right = cloneTree(root->right);
    
    return cloneRoot;
}

void Solution::deleteTree(cloneTreeNode *root){
    
    if(root == NULL){
        return ;
    }
    
    deleteTree(root->left);
    deleteTree(root->right);
    delete root;
}

int Solution::caculate(cloneTreeNode* root){
    int leftNum;
    int rightNum;
    
    if(root == NULL){
        return 0;
    }
    
    leftNum = caculate(root->left);
    rightNum = caculate(root->right);
    root->num = leftNum + rightNum + 1;
    
    return root->num;
}

int Solution::findKth(cloneTreeNode *root, int k){
    int leftNum;
    int rightNum;
    
    if(root->left != NULL){
        leftNum = root->left->num;
    }
    else{
        leftNum = 0;
    }
    
    if(leftNum == (k - 1)){
        return root->val;
    }
    
    if(leftNum >= k){
        return findKth(root->left, k);
    }
    else{
        return findKth(root->right, k - leftNum - 1);
    }
}

int Solution::kthSmallest(TreeNode* root, int k) {
    cloneTreeNode *cloneRoot;
    int num;
    
    cloneRoot = cloneTree(root);
    caculate(cloneRoot);
    num = findKth(cloneRoot, k);
    deleteTree(cloneRoot);
    
    return num;
}

int main(){
    
    TreeNode* root;
    Solution sol;
    int k;
    int num;
    
    /* Generate a input tree
	 *      3
	 *     / \
	 *    1   4
	 *     \
     *      2
	 */
	root = new TreeNode(3);
	root->left = new TreeNode(1);
	root->right = new TreeNode(4);
	root->left->right = new TreeNode(2);
   	
	k = 2; 
    num = sol.kthSmallest(root, k);
    
    cout << k << "-th smallest number: " << num << endl;

	return 0;
}
