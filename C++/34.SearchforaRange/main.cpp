/* Use Binary search (O(logn))
 * 1. Binary search to locate the position of the target
 * 2. Based on the position, extend to right and and left end to get the range
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	int binarySearch(vector<int>& nums, int target, int l, int r);
	vector<int> searchRange(vector<int>& nums, int target);
	void dump();

private:

};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::binarySearch(vector<int>& nums, int target, int l, int r){
    int m;
    int result;
    
    if( l > r){
        return -1;
    }
    
    m = (l + r) / 2;
    
    if(nums[m] == target){
        return m;
    }
    
    if(nums[m] < target){
        return binarySearch(nums, target, m + 1, r);
    }
    else{
        return binarySearch(nums, target, l, m - 1);
    }
}

vector<int> Solution::searchRange(vector<int>& nums, int target) {
    int pos;
    vector<int> pair;
    int head;
    int tail;
    
    pos = binarySearch(nums, target, 0, nums.size() - 1);
    
    if(pos == -1){
        head = -1;
        tail = -1;
        pair.push_back(head);
        pair.push_back(tail);
        return pair;
    }
    else{
        head = pos;
        tail = pos;
        
        for(head = pos; (head >= 0) && (nums[head] == target); --head);
        for(tail = pos; (tail < nums.size()) && (nums[tail] == target); ++tail);
        pair.push_back(++head);
        pair.push_back(--tail);
        
        return pair;
    }
}

int main(){
	Solution sol;
	int i;
	vector<int> nums;
	vector<int> ans;
	int target;

	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(3);
	nums.push_back(6);
	nums.push_back(7);
	target = 3;
	
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << ",";
	}
	cout << endl;
	
	ans = sol.searchRange(nums, target);

	cout << "target: " << target << endl;
	cout << "range: (:" << ans[0] << ", " << ans[1] << ")" << endl; 

	return 0;
}
