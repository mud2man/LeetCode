/* Complexity: O(n)
 * 1. Traverse from left to right using two pointers to record head and tail
 * 2. If head==tail, and a single number, else add a range
 * 3. Need to take care of boundry 
 */

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    vector<string> summaryRanges(vector<int>& nums);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

vector<string> Solution::summaryRanges(vector<int>& nums) {
    vector<string> ranges;
    string range;
    int head;
    int tail;
    int size;
    
    size = nums.size();
    
    head = 0;
    while(head < size){
        tail = head + 1;
        
        while((tail < size) && (nums[tail] == (nums[tail - 1] + 1))){
            ++tail;
        }
        --tail;
        
        if(tail == head){
            ranges.push_back(to_string(nums[head]));
        }
        else{
            range = to_string(nums[head]);
            range.append("->");
            range.append(to_string(nums[tail]));
            ranges.push_back(range);
        }
        
        head = tail + 1;
    }
    
    return ranges;
}

int main()
{
    Solution sol;
    vector<int> nums;
	vector<string> ranges;
	int i;

	nums.push_back(0);
	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(4);
	nums.push_back(5);
	nums.push_back(7);

	ranges = sol.summaryRanges(nums);

	for(i = 0; i < ranges.size(); ++i){
		cout << ranges[i] << endl;
	}
	
	return 0;
}
