/* Use binary search: complexity O(lgn*lgn)
 * 1. Use a series of digit to record the traversal path; 0 is left, 1 is right
 * 2. Acquire the size of the bitset by traversing along the left of the tree
 * 3. Use binary search with lower bond = 2b000..., and upperbond start from 2b011...
 */

#include <iostream>
#include <new>
#include <vector>
#include <math.h>

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
	int countNodes(TreeNode* root);
	int getHeight(TreeNode* root);
	int marginHit(TreeNode* root, vector<bool>& lb, vector<bool>& ub, vector<bool>& director);
	vector<bool> directoradd(vector<bool>& director);
	bool isReachLastLevel(TreeNode* root, vector<bool>& director);
	void dump(void);

private:
	/* The number of levels of the tree */
	unsigned int height;

	/* The traversal indicator */
	vector<bool> director;
	
	/* The upper bond */
	vector<bool> ub;
	
	/* The lower bond */
	vector<bool> lb;
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

int Solution::countNodes(TreeNode* root)
{
	unsigned int idx;
	int total;
	int lastLevelSum;
	int ret;
	TreeNode* ptrNode;

	/* Corner case I: Fully complete tree */
	if(root == NULL)
	{
		return 0;
	}

	height = getHeight(root);
	director.resize(height - 1);
	lb.resize(height - 1);
	ub.resize(height - 1);

	/* Corner case II: Fully complete tree */
	ptrNode = root;
	for(idx = 0; idx < (height - 1); idx++)
	{
		if(ptrNode->right != NULL)
		{
			ptrNode = ptrNode->right;
		}
		else
		{
			break;
		}
	}

	if(idx == (height - 1))
	{
		return pow(2, height) - 1;
	}

	/* Set the upper bond and lower bond */
	for(idx = 0; idx < lb.size(); idx++)
	{
		lb[idx] = 0;
		ub[idx] = 1;
	}
	
	/* Binary search */
	do
	{
		ret = marginHit(root, lb, ub, director);
		
		if(ret == 2)
		{
			break;
		}
		else if (ret == 1)
		{
			lb = director;
		}
		else
		{
			ub = director;
		}
	}while(1);

	/* Caculate the result form director */
	total = pow(2, height - 1) - 1;

	lastLevelSum = 0;
	for(idx = 0; idx < director.size(); idx++)
	{
		lastLevelSum = lastLevelSum + pow(2, idx)* director[idx];
	}
	lastLevelSum = lastLevelSum + 1;

	total = total + lastLevelSum;
	
	return total;
}

int Solution::getHeight(TreeNode* root)
{
	int h;
	TreeNode* ptrNode;

	ptrNode = root;
	h = 0;

	while(ptrNode != NULL)
	{
		h++;
		ptrNode = ptrNode->left;
	}

	return h;
}

int Solution::marginHit(TreeNode* root, vector<bool>& lb, vector<bool>& ub, vector<bool>& director)
{	
	unsigned int idx;
	int sum;
	vector<bool> tmp;
	bool carry;
	int isBottomLeft;
	int isBottomRight;

	tmp.resize(height);
	
	/* tmp =  ub + lb */
	carry = 0;
	for(idx = 0; idx < lb.size(); idx++)
	{
		sum = (int)lb[idx] + (int)ub[idx] + (int)carry;
		tmp[idx] =(bool)(sum & 0x1);
		carry = (bool)(sum >> 1) ;
	}
	tmp[idx] = carry;

	/* director =  tmp / 2 */
	for(idx = 0; idx < lb.size(); idx++)
	{
		director[idx] = tmp[idx + 1];
	}
	
	tmp.resize(height - 1);
	tmp = directoradd(director);

	isBottomLeft = isReachLastLevel(root, director);
	isBottomRight = isReachLastLevel(root, tmp);

	if((isBottomLeft == 1) && (isBottomRight == 0))
	{
		return 2;
	}
	else if((isBottomLeft == 0) && (isBottomRight == 0))
	{
		return 0;
	}
	else
	{
		return 1;
	}
}

vector<bool> Solution::directoradd(vector<bool>& director)
{
	unsigned int idx;
	int sum;
	bool carry;
	vector<bool> ret;
	
	ret = director;

	carry = 1;
	for(idx = 0; idx < director.size(); idx++)
	{
		sum = (int)director[idx] + (int)carry;
		ret[idx] =(bool)(sum & 0x1);
		carry = (bool)(sum >> 1);

		if(carry == 0)
		{
			break;
		}
	}
	
	return ret;
}

bool Solution::isReachLastLevel(TreeNode* root, vector<bool>& director)
{
	int idx;
	TreeNode* ptrNode;
	
	ptrNode = root;

	for(idx = director.size() - 1; idx >= 0; idx--)
	{
		if((director[idx] == 0) && (ptrNode->left != NULL))
		{
			ptrNode = ptrNode->left;
		}
		else if((director[idx] == 1) && (ptrNode->right != NULL))
		{
			ptrNode = ptrNode->right;
		}
		else
		{
			return 0;
		}
	}

	return 1;
}

void Solution::dump(void)
{
	unsigned int idx;
	
	cout << "director: ";
	for(idx = 0; idx < (director.size() - 1); idx++)
	{
		cout << director[idx];
	}
	cout << endl;
	
}

int main()
{
	TreeNode* root;
	Solution* sol;
	int nodeCount;
    
	/* Generate a input tree
	 *      1
	 *     / \
	 *    2   6
	 *   / \  /
     *  3   7 5  
	 */
	root = new TreeNode(1);
	root->left = new TreeNode(2);
	root->right = new TreeNode(3);
	root->left->left = new TreeNode(4);
	root->left->right = new TreeNode(5);
	root->right->left = new TreeNode(6);
	root->right->right = new TreeNode(7);
	root->left->left->left = new TreeNode(8);

    /* Right side list generation */
	sol = new Solution();
	for(int idx = 0; idx < 100000; idx++)
	{
		nodeCount = sol->countNodes(root);
	}
	
	cout << "nodeCount: " << nodeCount << endl;

	return 0;
}
