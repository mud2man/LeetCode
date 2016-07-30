/* Use recursive method 
 * 1. Create a magic node "magic"
 * 2. Traverse every node in the intput list
 * 	2.1. Check if the node is a self-cycle, if yes, return the node
 *	2.2. Check if the double-next node is the magic node, if yes, return the next node
 */

#include <iostream>
#include <vector>
#include <new>

using namespace std;

struct ListNode 
{
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};

class Solution 
{
public:
	Solution(void);
	~Solution(void);
	ListNode* detectCycle(ListNode *);
	void dump(ListNode *);

private:
	/* A dummy magic node */
	ListNode *magicNode;

	/* A list to stor all the next node */
	vector<ListNode *> nextList;
	
	/* Restore input list */
	void restoreList(ListNode *);

};/*End of class Solution */

#define LISTSIZE 4

Solution::Solution(void)
{
	magicNode = new ListNode(-1);
}

Solution::~Solution(void)
{
	nextList.clear();
	delete magicNode;
}

void Solution::restoreList(ListNode *head)
{
	ListNode *it;
	unsigned int idx;

	it = head;

	for(idx = 0; idx < nextList.size(); idx++)
	{
		it->next = nextList[idx];
		it = it->next;
	}
}

ListNode* Solution::detectCycle(ListNode *head)
{
	ListNode *it;
	ListNode *tmp;

	if (head == NULL)
	{
		return NULL;
	}

	it = head;

	/* Traverse each node */
	while(1)
	{
		/* Detect if single cycle */
		if(it->next == it)
		{
			/* Restore the next pointer for all the traversed node */
			restoreList(head);

			return it;
		}

		if(it->next != NULL)
		{
			/* Detect if the grandson of the node is magicNode */
			if(it->next->next == magicNode)
			{
				/* Restore the next pointer for all the traversed node */
				restoreList(head);

				return it->next;
			}
			
			/* Marked as the node be traverseed, and jump to the next node */
			tmp = it->next;
			nextList.push_back(tmp);
			it->next = magicNode;
			it = tmp;
		}
		else
		{
			return NULL;
		}
	}
}

void Solution::dump(ListNode *head)
{
	ListNode *it;
	unsigned int idx;

	it = head;

	for(idx = 0; idx < LISTSIZE; idx++)
	{
		cout <<	"(" << it->val << ", " << it << "), ";
		it = it->next;
	}

	cout << endl;
}

int main()
{
	Solution sol;
	ListNode *root;
	ListNode *cycle;

	cycle = NULL;

	root = new ListNode(3);
	root->next = new ListNode(2);
	root->next->next = new ListNode(0);
	root->next->next->next = new ListNode(-4);
	root->next->next->next->next = root->next->next->next;

	/* Dump the list */
	sol.dump(root);

    /* Obtain the solution */
	cycle = sol.detectCycle(root);
	
	if(cycle != NULL)
	{
		cout << "cycle exist with val: " << cycle->val << "@" << cycle << endl;
	}
	else
	{
		cout << "No cycle exist" << endl;
	}

	/* Dump the list */
	sol.dump(root);
	
	return 0;
}
