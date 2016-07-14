/*
 * 1. ...
 */

#include <iostream>
#include <new>

using namespace std;
/* Definition for singly-linked list */
struct ListNode 
{
	int val;
    ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};

class Solution 
{
public:

ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) 
{
	struct ListNode *itrl1;
	struct ListNode *itrl2;
	struct ListNode *itrl3;
	struct ListNode *preitrl3;
	struct ListNode *sol;
	struct ListNode *newnode;
	int carry;
	int digit;
	int sum;

	itrl1 = l1;
	itrl2 = l2;
	carry = 0;
    
	/* Traverse l1 and l2 until any of them reach the end */
	do
	{
		if((itrl1 != NULL) && (itrl2 != NULL))
		{
			sum = carry + itrl1->val + itrl2->val;
			carry = sum / 10;
			digit = sum % 10;
			itrl1->val = digit;
			itrl2->val = digit;
            
			/* Consider for case1 */
			if( itrl1->next == NULL)
			{
            	preitrl3 = itrl2;
				sol = l2;
			}
			else
			{
            	preitrl3 = itrl1;
			}

			itrl1 = itrl1->next;
			itrl2 = itrl2->next;

		}
		else if (itrl1 == NULL)
		{
			itrl3 = itrl2;
			sol = l2;
			break;
		}
		else
		{
			itrl3 = itrl1;
			sol = l1;
			break;
		}
	}while(1);

    /* Case1: The length of l1 is the same at that of l2 */
	if((itrl1 == NULL) && (itrl2 == NULL))
	{
		if(carry == 1)
		{
			newnode = new ListNode(1);
			preitrl3->next = newnode;
		}
		
		/* Store the answer into l2 and return it */
		return sol;
	}

    /* Case2: The length of l1 is not the same at that of l2 */
	do
	{
		sum = carry + itrl3->val;
		digit = sum % 10;
		carry = sum / 10;
		itrl3->val = digit;
        
		if(carry == 0)
		{
			break;
		}
        preitrl3 = itrl3;
		itrl3 = itrl3->next;

	}while(itrl3 != NULL);

	if((itrl3 == NULL) && (carry == 1))
	{
		newnode = new ListNode(1);
		preitrl3->next = newnode;
	}

	return sol;
}

void dumplist(struct ListNode* list)
{
	struct ListNode* itr;

	for(itr = list; itr != NULL; itr = itr->next)
	{
		cout << itr->val <<",";
	}
}

private:

};/*End of class Solution */

#define LEN1	3
#define LEN2	3

int main()
{
	int i;
	int a[LEN1] = {2, 4, 3};
	int b[LEN2] = {5, 6, 4};
    struct ListNode* l1;
	struct ListNode* l2;
	struct ListNode* newnode;
	struct ListNode* itr;
	struct ListNode* sum;
	Solution sol;
	
	/* Convert "a" node list */
	for(i = 0; i < LEN1; i++)
	{
		newnode = new ListNode(a[i]);

		if(i == 0)
		{
			l1 = newnode;
			itr = l1;
		}
		else
		{
			itr->next = newnode;
			itr = itr->next;
		}
	}
	
	/* Convert "b" node list */
	for(i = 0; i < LEN2; i++)
	{
		newnode = new ListNode(b[i]);
		
		if(i == 0)
		{
			l2 = newnode;
			itr = l2;
		}
		else
		{
			itr->next = newnode;
			itr = itr->next;
		}
	}
    
	/*Dump the content of l1 */
	cout << "l1: ";
	sol.dumplist(l1);
	cout << endl;
	
	/*Dump the content of l2 */
	cout << "l2: ";
	sol.dumplist(l2);
	cout << endl;
	
	sum = sol.addTwoNumbers(l1, l2);

	/*Dump the content of sum */
	cout << "sum: ";
	sol.dumplist(sum);
	cout << endl;

	return 0;
}
