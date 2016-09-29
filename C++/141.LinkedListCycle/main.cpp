/* Use recursive method 
 * 1. Create a check node "checkNode"
 * 2. Traverse every node in the intput list
 * 	2.1. Check if the node is a self-cycle, if yes, restore input list and return true
 *	2.2. Check if the next of the next node is the "checkNode", if yes, restore input list and return true
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
    bool hasCycle(ListNode *head);
    
private:
    vector<ListNode *> nodes;

};/*End of class Solution */

bool Solution::hasCycle(ListNode *head) {
    ListNode *checkNode = new ListNode(0);
    ListNode *ptr;
    ListNode *tmpPtr;
    int i;
    
    if(head == NULL){
        return false;
    }
    
    if(head->next == head){
        return true;
    }
    
    ptr = head;
    while(ptr->next != NULL){
        if((ptr->next->next != checkNode) && (ptr->next != ptr)){
            tmpPtr = ptr;
            ptr = ptr->next;
            nodes.push_back(ptr);
            tmpPtr->next = checkNode;
        }
        else{
            /* Restore input list */
            for(i = 0, ptr = head; i < nodes.size(); ++i){
                ptr->next = nodes[i];
            }
            
            return true;
        }
    }
    
    /* Restore input list */
    for(i = 0, ptr = head; i < nodes.size(); ++i){
        ptr->next = nodes[i];
    }
    
    return false;
}

Solution::Solution(void)
{
}

Solution::~Solution(void)
{
}

#define LIST_SIZE 4

int main()
{
	Solution sol;
	ListNode *head;
	ListNode *ptr;
	bool hasCycle;
	int i;

	head = new ListNode(3);
	head->next = new ListNode(2);
	head->next->next = new ListNode(0);
	head->next->next->next = new ListNode(-4);
	head->next->next->next->next = head->next->next->next;
	
	for(i = 0, ptr = head; i < LIST_SIZE;  ++i, ptr = ptr->next){
		cout << ptr->val << ",";
	}
	cout << endl;

	hasCycle = sol.hasCycle(head);

	cout << "haseCycle: " << hasCycle << endl;
	
	return 0;
}
