/* Complexity: O(n)
 * 1. Create a saving account for every gas station
 * 2. Traverse backward to locate the maximum saving station
 * 3. Check if the amount of the saving of station 0 > 0
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost);

private:
    
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
    vector<int> saving;
    int maxSaving;
    int size;
    int i;
    int idx;
    
    size = gas.size();
    saving.resize(size);
    
    if(size == 1){
        if(gas[0] >= cost[0]){
            return 0;
        }
        else{
            return -1;
        }
    }
    
    /* Backward Traverse */
    saving[size - 1] = gas[size - 1] - cost[size - 1];
    maxSaving = saving[size - 1];
    idx = size - 1;
    for(i = size - 2; i >= 0; --i){
        saving[i] = saving[i + 1] + gas[i] - cost[i];
        if(saving[i] > maxSaving){
            idx = i;
            maxSaving = saving[i];
        }
    }
    
    if(saving[0] < 0){
        return -1;
    }
    else{
        return idx;
    }
}

int main(){
    Solution sol;
    vector<int> gas;
	vector<int> cost;
	int i;

	gas.push_back(4);	
	gas.push_back(3);	
	gas.push_back(2);	

	cost.push_back(5);	
	cost.push_back(2);	
	cost.push_back(2);

	cout << "Gas:" << endl;
	for(i = 0; i < gas.size(); ++i){
		cout << gas[i] << ",";
	}
	cout << endl;

	cout << "Cost:" << endl;
	for(i = 0; i < cost.size(); ++i){
		cout << cost[i] << ",";
	}
	cout << endl;

	cout << "Starting idx: " << sol.canCompleteCircuit(gas, cost) << endl;

	return 0;
}
