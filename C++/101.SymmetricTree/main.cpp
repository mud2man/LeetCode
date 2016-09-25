/* Colmplexity: O(n)
 * 1. Generate an invertted tree
 * 2. Check if the inorder and preorder are the same
 */

#include <iostream>
#include <new>
#include <vector>

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
private:
    vector<int> inorder;
    vector<int> preorder;
    vector<int> mirrorInorder;
    vector<int> mirroePreorder;
    
    TreeNode* mirrorRoot;
public:
	Solution();
	~Solution();
    void inorderTraverse(TreeNode* root, vector<int> *inorder);
    void preorderTraverse(TreeNode* root, vector<int> *preorder);
    TreeNode* copyMirror(TreeNode* root);
    bool isSymmetric(TreeNode* root);
	void dump();

private:
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

void Solution::inorderTraverse(TreeNode* root, vector<int> *inorder){
    if(root == NULL){
        return;
    }
    
    inorderTraverse(root->left, inorder);
    inorder->push_back(root->val);
    inorderTraverse(root->right, inorder);
}

void Solution::preorderTraverse(TreeNode* root, vector<int> *preorder){
    if(root == NULL){
        return;
    }
    
    preorder->push_back(root->val);
    preorderTraverse(root->left, preorder);
    preorderTraverse(root->right, preorder);
}

TreeNode* Solution::copyMirror(TreeNode* root){
    TreeNode *node;
    
    if(root == NULL){
        return NULL;
    }
    
    node = new TreeNode(root->val);
    node->right = copyMirror(root->left);
    node->left = copyMirror(root->right);
    
    return node;
}

void Solution::dump(void){
    int i;
    
    cout << "inorder: ";
    for(i = 0; i < this->inorder.size(); ++i){
        cout << inorder[i] << "," ;
    }
    cout << endl;
    
    cout << "preorder: ";
    for(i = 0; i < this->preorder.size(); ++i){
        cout << preorder[i] << "," ;
    }
    cout << endl;
    
    cout << "mirrorInorder: ";
    for(i = 0; i < this->mirrorInorder.size(); ++i){
        cout << mirrorInorder[i] << "," ;
    }
    cout << endl;
    
    cout << "mirroePreorder: ";
    for(i = 0; i < this->mirroePreorder.size(); ++i){
        cout << mirroePreorder[i] << "," ;
    }
    cout << endl;
}

bool Solution::isSymmetric(TreeNode* root) {
    int i;
    int size;
    
    /* Mirror the input tree */
    mirrorRoot = copyMirror(root);
    
    /* Store the record of inorder traversing */
    inorderTraverse(root, &inorder);
    preorderTraverse(root, &preorder);
    inorderTraverse(mirrorRoot, &mirrorInorder);
    preorderTraverse(mirrorRoot, &mirroePreorder);
    
    //dump();
    
    size = this->inorder.size();
    
    for(i = 0; i < size; ++i){
        if(inorder[i] != mirrorInorder[i]){
            return false;
        }
    }
    
    for(i = 0; i < size; ++i){
        if(preorder[i] != mirroePreorder[i]){
            return false;
        }
    }
    
    return true;
    
}

int main()
{
    TreeNode* root;
    Solution* sol;
    
    /* Generate a input tree
	 *      1
	 *     / \
	 *    2   2
	 *   /     \
     *  4       4
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(2);
	root->left->left = new TreeNode(4);
	root->right->right = new TreeNode(4);
	sol = new Solution();
	
	cout << "isSymmetric = " << sol->isSymmetric(root) << endl; 
	return 0;
}
