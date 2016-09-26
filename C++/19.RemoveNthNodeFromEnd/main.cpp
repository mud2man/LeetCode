/* Complexity: O(n)
 * 1. Tarverse list from right end and store nodes into a stack
 * 2. Pop the stack to fullfill back traverse, and delete the Nth node
 */

#include <iostream>
#include <vector>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution 
{
public:
	Solution();
	~Solution();
    ListNode* removeNthFromEnd(ListNode* head, int n);
	void dump();

private:
     vector<ListNode*> stack;

};/*End of class Solution */

Solution::Solution(){
};

Solution::~Solution(){
};

ListNode* Solution::removeNthFromEnd(ListNode* head, int n) {
    int i;
    ListNode* pNode;
    ListNode* pNextNode;
    ListNode* pPreviousNode;
    
    pNode = head;
    while(pNode != NULL){
        stack.push_back(pNode);
        pNode = pNode->next;
    }
    
    if(stack.size() <= 1){
        delete head;
        return NULL;
    }
    
    for(i = 0; i < n; i++){
        pNode = stack.back();
        pNextNode = pNode->next;
        stack.pop_back();
        if(!stack.empty()){
            pPreviousNode = stack.back();
        }
        else{
            pPreviousNode = NULL;
        }
    }
    
    if(pPreviousNode == NULL){
        head = pNextNode;
    }
    else{
        pPreviousNode->next = pNextNode;
    }
    
    delete pNode;
    
    return head;
}

void Solution::dump(){
};

int main(){
	int i;
   	int num;
    Solution sol;
	ListNode* head;
	ListNode* pNode;;

	head = new ListNode(1);
	head->next = new ListNode(2);
	head->next->next = new ListNode(3);

	for(pNode = head; pNode != NULL; pNode = pNode->next){
		cout << pNode->val << ",";
	}
	cout << endl;
	
	sol.removeNthFromEnd(head, 2);
	cout << "After delete 2" << endl;

	for(pNode = head; pNode != NULL; pNode = pNode->next){
		cout << pNode->val << ",";
	}
	cout << endl;

	return 0;
}
