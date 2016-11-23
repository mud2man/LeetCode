/* Complexity: O(n)
 * 1. Generate product from right
 * 2. Generate product from left
 * 3. ans[i] = productFromRight[i + 1] * productFromLeft[i - 1];
 * ex: input: [1, 2, 3, 4]
 *     right: [24,24,12,4]
 *     left:  [1, 2, 6, 24]
 *     answer:[24,12,8, 6]
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    vector<int> productExceptSelf(vector<int>& nums);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

vector<int> Solution::productExceptSelf(vector<int>& nums) {
    vector<int> productFromRight;
    int productFromLeft;
    int size;
    int i;
    
    size = nums.size();
    productFromRight.assign(size, 0);
    
    productFromRight[size - 1] = nums[size - 1];
    for(i = 1; i < size; ++i){
        productFromRight[size - i - 1] = productFromRight[size - i] * nums[size - i - 1];
    }
    
    productFromRight[0] = productFromRight[1];
    productFromLeft = nums[0];
	for(i = 1; i < (size - 1); ++i){
        productFromRight[i] = productFromRight[i + 1] * productFromLeft;
		productFromLeft = productFromLeft * nums[i];
    }
    productFromRight[size - 1] = productFromLeft;
    
    return productFromRight;
}

int main()
{
    vector<int> nums;
	vector<int> prods;
    Solution sol;
	int i;
	
 	nums.push_back(1);
 	nums.push_back(2);
 	nums.push_back(3);
 	nums.push_back(4);
	prods = sol.productExceptSelf(nums);
	
	cout << "input: ";
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << "," ; 
	}
	cout << endl;

	cout << "prods: ";
	for(i = 0; i < prods.size(); ++i){
		cout << prods[i] << "," ; 
	}
	cout << endl;
	
	return 0;
}
