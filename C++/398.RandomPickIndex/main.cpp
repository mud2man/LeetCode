/* Use Reservoir sampling
 * 1. Assign number list into this class
 * 2. Apply Reservoir sampling
 */

#include <vector>
#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

using namespace std;

class Solution {

public:
	Solution(vector<int>);
	~Solution();
	int pick(int);
	void dump();

private:
	vector<int> nums;
};/*End of class Solution */

Solution::Solution(vector<int> nums){
	this->nums = nums;
	srand(time(0));
}

Solution::~Solution(){
}

int Solution::pick(int target) {
	int pick;
	int i;
	int count;
	
	count = 1;
	for(i = 0; i < nums.size(); i++){
		if(target == nums[i]){
			/* Resevoir sample */
			if(rand()%count == 0 ){
				pick = i;
				count++;
			}
		}
	}

	if(count > 1){
		return pick;
	}
	else{
		return 0;
	}
}

void Solution::dump(){
}

int main(){
	int i;
	int pick;
	vector<int> nums;

	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(4);
	nums.push_back(3);
	pick = 3;
	Solution sol(nums);

	cout << "nums: ";
	for(i = 0; i < nums.size(); i++){
		cout << nums[i] << ",";
	}
	cout << endl;

	cout << "pick[" << pick << "] = " << sol.pick(pick) <<endl;
	cout << "pick[" << pick << "] = " << sol.pick(pick) <<endl;
	cout << "pick[" << pick << "] = " << sol.pick(pick) <<endl;
	cout << "pick[" << pick << "] = " << sol.pick(pick) <<endl;
	
	return 0;
}
