/* Use Bunary search: O(n)
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution{

public:
	Solution(void);
	~Solution(void);
	int hIndex(vector<int>&);
	int binarySearch(vector<int>&, int, int, int);
	void dump();

private:

};/*End of class Solution */

Solution::Solution(void){
}

Solution::~Solution(void){
}

int Solution::hIndex(vector<int>& citations){

	int hIndex;

	hIndex = binarySearch(citations, 0, 0, (int)citations.size());

	return hIndex;
}

int Solution::binarySearch(vector<int>& targets, int found, int lb, int ub){

	unsigned idx;
	int key;
	int lrange;
	int hrange;
	vector<int> lows;
	vector<int> highs;

	lrange = 0;
	hrange = 0;
	key = (lb + ub) / 2;

	for(idx = 0; idx < targets.size(); idx++){

		if(targets[idx] > key){
			hrange++;
			lrange++;
			highs.push_back(targets[idx]);
		}
		else if(targets[idx] == key){
			lrange++;
			highs.push_back(targets[idx]);
		}
		else{
			lows.push_back(targets[idx]);
		}
	}
	
	if(((found + hrange) <= key) && ((found + lrange) >= key)){
		return key;
	}
	else if((found + hrange) > key){
		return binarySearch(highs, found, key + 1, ub);
	}
	else{
		found = lrange + found;
		return binarySearch(lows, found, lb, key - 1);
	}
}

void Solution::dump(void){
}

int main(){

	int hIndex;
	Solution sol;
	vector<int> citations;
	
	/* Create an input pattern */
	citations.push_back(1);
	citations.push_back(1);
	citations.push_back(1);
	citations.push_back(1);
	citations.push_back(1);
	citations.push_back(100);
	citations.push_back(2);

	hIndex = sol.hIndex(citations);

	cout << "hindex: " << hIndex << endl;

	return 0;
}
