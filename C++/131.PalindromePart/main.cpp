/* Use recursive method 
 * 1. Pick up the first palindrome from left to right, and call "PickPalin" again
 *	1.1. take a string ababc as an example ex: a|babc, ab|abc, aba|bc ...
 * 2. Check if the substring is equal to to string, if yes, terminate; else go to step1
 */

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution 
{
public:
	vector< vector<string> > partition(string);
	void PickPalin(vector<string> &);
	int isPalin(unsigned int &, string &);
	void dump(vector< vector<string> > );

private:
	/* A list of palindromes */
	vector< vector<string> > palins;

};/*End of class Solution */

vector< vector<string> > Solution::partition(string s)
{
	vector<string> strings;

	if(s.size() > 0)
	{
		strings.push_back(s);
		PickPalin(strings);
	}

	return palins;
}

void Solution::PickPalin(vector<string> &strings)
{
	string tailstr;
	string topstr;
	string botstr;
	unsigned int charnum;
	unsigned int strsize;
	vector<string> *newstrs;

	/* Pop the last string */
	tailstr = strings.back();
	strings.pop_back();
	
	strsize = tailstr.size();

	/* Check if the last string with char number = 1 ~ (strize - 1) is palindrome*/
	for(charnum = 1; charnum <= (strsize - 1); charnum++)
	{
		if(isPalin(charnum, tailstr))
		{
			topstr = tailstr.substr(0, charnum);
			botstr = tailstr.substr(charnum, strsize - charnum);
			newstrs = new vector<string>;
			*newstrs = strings;
			newstrs->push_back(topstr);
			newstrs->push_back(botstr);

			/* check if the last string is a character, if yes, it's a palindrome */
			if(botstr.size() == 1)
			{
				palins.push_back(*newstrs);
			}
			else
			{
				PickPalin(*newstrs);
			}
		}
	}

	/* Check if the last string with char number = strsize is a palindrome */
	if(isPalin(strsize, tailstr))
	{
		strings.push_back(tailstr);
		palins.push_back(strings);
	}
		
	/* Delete strings */
	strings.clear();
}

int Solution::isPalin(unsigned int & charnum, string & s)
{
	unsigned idx;
	unsigned end = charnum - 1;

	for(idx = 0; idx <= ((charnum - 1) / 2); idx++)
	{
		if(s[idx] != s[end-idx])
		{
			return false;
		}
	}

	return true;
}

void Solution::dump(vector< vector<string> > palinlist)
{
	vector< vector<string> >::iterator it0;
	vector<string>::iterator it1;

	for(it0 = palinlist.begin(); it0 != palinlist.end(); it0++)
	{
		for(it1 = it0->begin(); it1 != it0->end(); it1++)
		{
			cout << *it1 << ",";
		}
		cout << endl;
	}
}

int main()
{
	Solution sol;
	string s = "ababc";
	vector< vector<string> > palinlist;
    
    /* Obtain the solution */
	palinlist = sol.partition(s);

	/*Dump the solution */
	sol.dump(palinlist);

	return 0;
}
