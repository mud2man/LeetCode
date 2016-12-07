/* DFS: O(#nodes) 
 * 1. Keep the current consecutive number
 * 2. Visit left and right child, if the value of child = the value of parent + 1.
 * 3. Pass the parameter with current length + 1. Otherwise, pass with 1
 */

#include <iostream>
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
	Solution();
	~Solution();
    void traverse(TreeNode* root, int &maxLen, int currLen);
    int longestConsecutive(TreeNode* root);

private:
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

void Solution::traverse(TreeNode* root, int &maxLen, int currLen){
    
    if(currLen > maxLen){
        maxLen = currLen;
    }
    
    if(root->left != NULL){
        if(root->left->val == (root->val + 1)){
            traverse(root->left, maxLen, currLen + 1);
        }
        else{
            traverse(root->left, maxLen, 1);
        }
    }
    
    if(root->right != NULL){
        if(root->right->val == (root->val + 1)){
            traverse(root->right, maxLen, currLen + 1);
        }
        else{
            traverse(root->right, maxLen, 1);
        }
    }
}

int Solution::longestConsecutive(TreeNode* root) {
    int maxLen;
    
    maxLen = 0;
    
    if(root != NULL){
        traverse(root, maxLen, 1);
    }
    
    return maxLen;
}

int main()
{
    TreeNode* root;
    Solution sol;
	int maxLen;
    
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

	maxLen = sol.longestConsecutive(root);

	cout << "maxLen: " << maxLen << endl;

	return 0;
}
