/* Topological sort: Complexity = O(e + n) 
 * 1. Create indegree and outdegree matrix
 * 2. Use stack to store the courseId with indgree = 0
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    void parse(vector<pair<int, int>>& prerequisites, vector<int>& inDegree, vector<vector<int>>& outDegree);
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::parse(vector<pair<int, int>>& prerequisites, vector<int>& inDegree, vector<vector<int>>& outDegree){
    int i;
    pair<int, int> prerequisite;
    int first;
    int second;
    
    for(i = 0; i < prerequisites.size(); ++i){
        prerequisite = prerequisites[i];
        outDegree[prerequisite.second].push_back(prerequisite.first);
        inDegree[prerequisite.first] = inDegree[prerequisite.first] + 1;
    }
}

vector<int> Solution::findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
    vector<int> inDegree;
    vector<vector<int>> outDegree;
    vector<int> order;
    vector<int> stack;
    vector<int> empty;
    int i;
    int courseId;
    int visitId;
    
    if((numCourses > 0) && (prerequisites.size() == 0)){
        for(i = 0; i < numCourses; ++i){
            order.push_back(i);
        }
        return order;
    }
    
    outDegree.assign(numCourses, order);
    inDegree.assign(numCourses, 0);
    parse(prerequisites, inDegree, outDegree);
    
    for(i = 0; i < inDegree.size(); ++i){
        if(inDegree[i] == 0){
            stack.push_back(i);
        }
    }
    
    while(!stack.empty()){
        courseId = stack.back();
        stack.pop_back();
        for(i = 0; i < outDegree[courseId].size(); ++i){
            visitId =  outDegree[courseId][i];
            inDegree[visitId] = inDegree[visitId] - 1;
            
            if(inDegree[visitId] == 0){
                stack.push_back(visitId);
            }
        }
        order.push_back(courseId);
    }
    
    if(order.size() == numCourses){
        return order;
    }
    else{
        return empty;
    }
}

int main(){
    vector<pair<int, int>> prerequisites;
    Solution sol;
    int numCourses;
	vector<int> order;
	int i;
	
	numCourses = 4;
	prerequisites.push_back(make_pair(1,0));
	prerequisites.push_back(make_pair(2,0));
	prerequisites.push_back(make_pair(3,1));
	prerequisites.push_back(make_pair(3,2));
	
	order = sol.findOrder(numCourses, prerequisites);

	cout << "order: ";
	for(i = 0; i < order.size(); ++i){
		cout << order[i] << ", ";
	}
	cout << endl;

	return 0;
}
