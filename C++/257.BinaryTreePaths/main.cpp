/* Use DFS: O(n)
 * 1. Use DFS to traverse every node
 * 2. If a leave is hitted, push the traversal history into dfshistory
 */

#include <iostream>
#include <new>
#include <vector>
#include <string>
#include <sstream>

using namespace std;

struct TreeNode{
	int	val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {

public:
	Solution();
	~Solution();
	vector<string> binaryTreePaths(TreeNode*);
	void dfs(TreeNode*);
	void addPath(vector<string>&, string&);
	void stringPush(string&, int&);
	void stringPop(string&);
	string NumberToString(int);
	void dump();

private:
	/* Traversal history */
	string stringStack;

	/* The set of all possible paths */
	vector<string> allPaths;

};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

vector<string> Solution::binaryTreePaths(TreeNode* root){
	
	if(root == NULL){
		return allPaths;
	}
	else{
		dfs(root);
	}

	return allPaths;
}

void Solution::dfs(TreeNode* node){
	
	stringPush(stringStack, node->val);

	if((node->left == NULL) && (node->right == NULL)){
		addPath(allPaths, stringStack);
		stringPop(stringStack);

		return;
	}
	else{
		if(node->left != NULL)
			binaryTreePaths(node->left);
		
		if(node->right != NULL)
			binaryTreePaths(node->right);
	}
	
	/* Erase the arrow */
	stringStack.erase(stringStack.length() - 2, 2);
	stringPop(stringStack);

	return;
}

void Solution::stringPush(string& stack, int& val){

	stack.append(NumberToString(val));
	stack.append("->");
}

void Solution::stringPop(string& stack){
	
	while(stack.back() != '>'){
		stack.pop_back();

		if(stack.length() == 0){
			break;
		}
	}
}

void Solution::addPath(vector<string>& allPaths, string& stack){
	
	/* Erase the arrow */
	stack.erase(stack.length() - 2, 2);
	
	allPaths.push_back(stack);
}

string Solution::NumberToString(int num )
{
	ostringstream convert;
	convert << num;
	return convert.str();
}

void Solution::dump(){
	
	vector<string>::iterator itr;
	
	cout << "All paths: " << endl;
	for(itr = allPaths.begin(); itr != allPaths.end(); itr++){
		cout << *itr << endl; 
	}
}

int main(){

	TreeNode* root;
	Solution sol;
	vector<string> paths;
    
	/* Generate a input tree
	 *      1
	 *     / \
	 *    2   3
	 *     \
     *      4
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->left->right = new TreeNode(4);

	paths = sol.binaryTreePaths(root);

	sol.dump();
	
	return 0;
}
