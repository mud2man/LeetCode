/* Use breadth-first traversal
 * 1. Use stack to store level1 elemnets via visiting left child firstly
 * 2. Pop the level1 stack and store level2 elements via visiting right child
 * 3. Pop the level2 stack and repeat the previous procedure
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

/* Report the next ID without overlap */
vector< vector<int> > zigzagLevelOrder(TreeNode* root) 
{
	TreeNode *itr;
	vector<int> *row;
	
	/* Null case */
	if(root == NULL)
	{
		return zigzag;
	}

	/*Init the left-child-first stack */
	lfirst.push_back(root);
	
	/* Fill all the element in a zigzag order */
	do
	{
		/* Pop the lfirst */
		if(!lfirst.empty())
		{
			row = new vector<int>;

			while(!lfirst.empty())
			{
				itr = lfirst.back();
				row->push_back(itr->val);
				
				/* Push the elemnts in level n+1 */
				if(itr->left != NULL)
				{
					rfirst.push_back(itr->left);
				}
				
				if(itr->right != NULL)
				{
					rfirst.push_back(itr->right);
				}

				lfirst.pop_back();
			}
			
			/* Push the element in level n */
			zigzag.push_back(*row);

		}
		/* Pop the rfirst */
		else if(!rfirst.empty())
		{
			row = new vector<int>;

			while(!rfirst.empty())
			{
				itr = rfirst.back();
				row->push_back(itr->val);
				
				/* Push the elemnts in level n+1 */
				if(itr->right != NULL)
				{
					lfirst.push_back(itr->right);
				}
				
				if(itr->left != NULL)
				{
					lfirst.push_back(itr->left);
				}

				rfirst.pop_back();
			}

			/* Push the element in level n */
			zigzag.push_back(*row);
		}
	}while((!lfirst.empty()) || (!rfirst.empty()));
	
	return zigzag;
}

void dumpZigzag(void)
{
    vector< vector<int> >::iterator row;
    vector<int>::iterator it;
	
	for(row = zigzag.begin(); row != zigzag.end(); row++)
	{
		for(it = (*row).begin(); it != (*row).end(); it++)
		{
			cout << *it << ",";
		}
		cout << endl;
	}
}

private:
    /* Traversal zigzag */
    vector< vector<int> > zigzag;

    /* Pick the left child first */
	vector<TreeNode*> lfirst;

    /* Pick the right child first */
	vector<TreeNode*> rfirst;
};/*End of class Solution */

TreeNode* InsertLeave(TreeNode* root, TreeNode* leave, int idx, int level)
{
	int window;
	TreeNode* itr;

	if(idx == 1)
	{
		return leave;
	}
	else
	{
		itr = root;

		for(window = 1 << (level - 1); window > 0; window = window / 2)
		{
			if(window > 1)
			{
				/* Go to left */
				if((idx & window) == 0)
				{
					itr = itr->left;
				}
				/* Go to right */
				else
				{
					itr = itr->right;
				}
			}
			else
			{
				/* Assign the left leave */
				if((idx & window) == 0)
				{
					if(leave->val == 0)
					{
						itr->left = NULL;
						free(leave);
					}
					else
					{
						itr->left = leave;
					}
				}
				/* Assign the right leave */
				else
				{
					if(leave->val == 0)
					{
						itr->right = NULL;
						free(leave);
					}
					else
					{
						itr->right = leave;
					}
				}
			}
		}
	}

	return root;
}

TreeNode* TreeGen(TreeNode* root, vector<int> list)
{
	TreeNode* leave;
	int size;
	int idx;
	int level;
	int i;
	int j;

	size = list.size();

	/* Obtain the number of level */
	for(level = 0; (1 << level) < size; level++);

	cout << "level: " << level << endl;
    
	idx = 0;

	for(i = 0; i < level; i++)
	{
		for(j=0; j < (1 << i); j++)
		{
			leave = new TreeNode(list[idx]);
			root = InsertLeave(root, leave, idx + 1, i);
			idx++;
		}
	}

	return root;
}

#define LISTSIZE 7
int main()
{
    TreeNode* root;
	int array[LISTSIZE]={3,9,20,0,0,15,7};
	vector<int> list(&(array[0]), &array[LISTSIZE]);
	vector< vector<int> > zigzag;
	Solution sol;

    /* Tree generation */
	root = NULL;
	root = TreeGen(root, list);
    
	cout << "root:" << root->val << endl;
	cout << "root->left:" << root->left->val << endl;
	cout << "root->right:" << root->right->val << endl;
	cout << "root->right->left:" << root->right->left->val << endl;
	cout << "root->right->right:" << root->right->right->val << endl;

	/* Zigzag the tree */
	zigzag = sol.zigzagLevelOrder(root); 
	
	/*Dump the answer */
	sol.dumpZigzag();

	return 0;
}
