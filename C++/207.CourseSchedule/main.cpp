/* Use topological sort
 * 1. Construct the graph via 2-D array, the first element in every row is in-degree
 * 2. The following elements are its successor
 * 3. Iterate all the nodes, and put the node with in-degree=0 into the stack
 * 4. Go into a "for" loop with the iteration number = #nodes, 
 * 5. If the stack become empty in the middle of loop, cycle exist
 */

#include <iostream>
#include <vector>
#include <deque>
#include <new>
#include <utility> /* pair */
#include <stack> /* stack */

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	bool canFinish(int numCourses, vector< pair<int, int> >& prerequisites); 
	void graphGen(int numCourses, vector< pair<int, int> >& prerequisites);
	void dump(void);

private:
	/* The graph data structure */
	deque< deque<int> > graph;
	
	/* The stack storing the node with in-degree=0 */
	stack< int > zeroInDegrees;
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
	int idx;

	for(idx = 0; idx < (int)graph.size(); idx++)
	{
		graph[idx].clear();
	}
	graph.clear();

	while(!zeroInDegrees.empty())
	{
		zeroInDegrees.pop();
	}
}

bool Solution::canFinish(int numCourses, vector< pair<int, int> >& prerequisites)  
{
	int idx;
	int nodeId;
	deque<int>* ptrNode;
	int outerIdx;
	int successorId;
	
	graphGen(numCourses, prerequisites);
	
	/* Push all the nodes with indegree=0 to the stack */
	for(idx = 0; idx < (int)graph.size(); idx++)
	{
		if(graph[idx][0] == 0)
		{
			zeroInDegrees.push(idx);
		}
	}

	for(idx = 0; idx < (int)graph.size(); idx++)
	{
		if(zeroInDegrees.empty())
		{
			return false;
		}

		nodeId = zeroInDegrees.top();
		zeroInDegrees.pop();
		
		ptrNode = &(graph[nodeId]);
	
		/* Visit all the sucessors */
		for(outerIdx = 1; outerIdx < (int)(ptrNode->size()); outerIdx++)
		{
			successorId = (*ptrNode)[outerIdx];
			graph[successorId][0] --;

			if(graph[successorId][0] == 0)
			{
				zeroInDegrees.push(successorId);
			}
		}
	}

	return true;
}

void Solution::graphGen(int numCourses, vector< pair<int, int> >& prerequisites)
{
	int idx;
	int target;
	int condition;
	deque<int>* ptrNode;
	pair<int, int> prerequisite;
	
	for(idx = 0; idx < numCourses; idx++)
	{
		ptrNode = new deque<int>;
		ptrNode->push_back(0);
		graph.push_back(*ptrNode);
	}

	for(idx = 0; idx < (int)prerequisites.size(); idx++)
	{
		target = prerequisites[idx].first;
		condition = prerequisites[idx].second;

		/* Increase the indegree of target */
		ptrNode = &(graph[target]);
		(*ptrNode)[0]++;

		/* Add the sucessor of node condition */
		ptrNode = &(graph[condition]);
		ptrNode->push_back(target);
	}
}

void Solution::dump(void)
{
	int course;
	deque<int> node;
	deque< deque<int> >::iterator outerIt;
	deque<int>::iterator InnerIt;
	
	course = 0;

	for(outerIt = graph.begin(); outerIt != graph.end(); outerIt++)
	{
		node = *outerIt;
		
		cout << "Course " << course << ":" ; 
		for(InnerIt = node.begin(); InnerIt != node.end(); InnerIt++)
		{
			cout << *InnerIt << "," ;
		}
		cout << endl;

		course++;
	}
}

#define COURSE_NUM 5

int main()
{
	int numCourses;
	Solution sol;
	bool canFinish;
	pair<int, int> prerequisite;
	vector< pair<int, int> > prerequisites;

	/*	Relationship graph
	 *         0
	 *        / \
	 *       @   @
	 *      1     2
	 *     /@     /
	 *    @ |    /
	 *   3  |   /
	 *    \ |  /
	 *	   @| @
	 *	    4
	 */
	prerequisite = make_pair(1,0);
	prerequisites.push_back(prerequisite);
	prerequisite = make_pair(2,0);
	prerequisites.push_back(prerequisite);
	prerequisite = make_pair(3,1);
	prerequisites.push_back(prerequisite);
	prerequisite = make_pair(4,3);
	prerequisites.push_back(prerequisite);
	prerequisite = make_pair(4,2);
	prerequisites.push_back(prerequisite);
	prerequisite = make_pair(1,4);
	prerequisites.push_back(prerequisite);

	numCourses = COURSE_NUM;
	
	canFinish = sol.canFinish(numCourses, prerequisites);
	
	if(canFinish)
		cout << "Schedule successfully" << endl;
	else
		cout << "Schedule conflict" << endl;

	/* Dump the answer */
	sol.dump();

	return 0;
}
