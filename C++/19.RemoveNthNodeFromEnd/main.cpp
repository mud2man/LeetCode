/* Two pointers: O(n)
 * 1. Find the ending pointer given n
 * 2. Assign the starting pointer to the head, and move the ending pointer right until reaching NULL
 * 3. Remove the starting pointer
 *
 * ex: 1->2->3->4->5, n = 2
 * time[0]: dummy->1->2->3->4->5, tail = 3, prev = dummy
 * time[1]: dummy->1->2->3->4->5, tail = 4, prev = 1
 * time[2]: dummy->1->2->3->4->5, tail = 5, prev = 2
 * time[3]: dummy->1->2->3->4->5, tail = NULL, prev = 3
 * time[4]: dummy->1->2->3->5
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

};/*End of class Solution */

Solution::Solution(){
};

Solution::~Solution(){
};

ListNode* Solution::removeNthFromEnd(ListNode* head, int n) {
    ListNode *tail, *prev, *dummy;

    dummy = new ListNode(0);
    dummy->next = head;
    
    tail = head;
    for(int i = 0; i < n; ++i){
        tail = tail->next;
    }
    
    prev = dummy;
    while(tail != NULL){
        prev = prev->next;
        tail = tail->next;
    }
    
    prev->next = prev->next->next;
    head = dummy->next;
    delete dummy;

    return head;
}

int main(){
    Solution sol;
	ListNode* head;
	ListNode* pNode;;

	head = new ListNode(1);
	head->next = new ListNode(2);
	head->next->next = new ListNode(3);
	head->next->next->next = new ListNode(4);
	head->next->next->next->next = new ListNode(5);

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
