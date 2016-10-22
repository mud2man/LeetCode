/* Insertion sort, Complexity = O(n^2)
 */

#include <iostream>
#include <vector>

using namespace std;

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};

class Solution {

public:
	Solution();
	~Solution();
    ListNode* insertionSortList(ListNode* head);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

ListNode* Solution::insertionSortList(ListNode* head) {
    vector<int> list;
    ListNode* node;
    int i;
    int j;
    int num;
    
    for(node = head; node != NULL; node = node->next){
        list.push_back(node->val);
    }
    
    for(i = 1; i < list.size(); ++i){
        num = list[i];
        
        if(num > list[i - 1]){
            continue;
        }
        
        for(j = i - 1; j >= 0; --j){
            if(list[j] > num){
                list[j + 1] = list[j];
                if(j == 0){
                    list[0] = num;
                }
            }
            else{
                list[j + 1] = num;
                break;
            }
        }
    }
    
    for(node = head, i = 0; node != NULL; node = node->next, ++i){
        node->val = list[i];
    }
    
    return head;
}

int main(){
    Solution sol;
    ListNode *head;
	ListNode *node;

	head = new ListNode(1);
	head->next = new ListNode(4);
	head->next->next = new ListNode(-1);

	cout << "unsorted: ";
	for(node = head; node != NULL; node = node->next){
		cout << node->val << ",";
	}
	cout << endl;
    
    head = sol.insertionSortList(head);
    
	cout << "sorted: ";
	for(node = head; node != NULL; node = node->next){
		cout << node->val << ",";
	}
	cout << endl;

	return 0;
}
