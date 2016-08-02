/* 
 * 1. Encode the Dna sequence via the denifination, A=0, C=1, G=2, T=3
 * 2. Caculate all the encoded number of every sub-sequence from right
 * 3. Store all the encoded number in "multiset", because it's based on rb-tree
 * 4. Any of the element in rb-tree is a pair = (encoded number, starting position)
 * 5. Iterate all the pairs, if the position difference > 10, repeated sub-sequence is found
 */

#include <iostream>
#include <map>
#include <vector>
#include <string>

#define BITMAP_SIZE 1024*1024

using namespace std;

class Solution
{

public:
	Solution(void);
	~Solution(void);
	vector<string> findRepeatedDnaSequences(string &);
	void dump();

private:
	/* Utilities function */
	unsigned long seq2num(unsigned int &, string &);
	string num2seq(unsigned long);

	/* Bit map to record the traversal */
	short bitmap[BITMAP_SIZE];
	
	/* Repeated sequences */
	vector<string> repeatedSeqs;

};/*End of class Solution */

Solution::Solution(void)
{
	unsigned int idx;

	for(idx = 0; idx < BITMAP_SIZE; idx++)
	{
		bitmap[idx] = 0;
	}
}

Solution::~Solution(void)
{
	repeatedSeqs.clear();
}

vector<string> Solution::findRepeatedDnaSequences(string &s)
{	
	string subSeq;
	unsigned long seqNum;
	unsigned int idx;
	unsigned int strLen;

	strLen = s.length();
	
	if(strLen <= 10)
	{
		return repeatedSeqs;
	}

	/* Store all the encoded number in multimap seqs */
	for(idx = 0; (idx + 9) < strLen; idx++)
	{
		seqNum = seq2num(idx, s);
		bitmap[seqNum]++;
		
		if(bitmap[seqNum] == 2)
		{
			subSeq = num2seq(seqNum);
			repeatedSeqs.push_back(subSeq);
		}
	}

	return repeatedSeqs;
}

unsigned long Solution::seq2num(unsigned int &idx, string &s)
{
	unsigned int offset;
	unsigned long num;

	num = 0;
	
	/*A=0, C=1, G=2, T=3 */
	for(offset = 0; offset < 10; offset++)
	{
		switch(s[idx + offset])
		{
			case 'A':
				num = num*4;
				break;
			
			case 'C':
				num = num*4 + 1;
				break;
			
			case 'G':
				num = num*4 + 2;
				break;
			
			case 'T':
				num = num*4 + 3;
				break;
		}
	}

	return num;
}

string Solution::num2seq(unsigned long num)
{
	unsigned long lsb;	
	unsigned int idx;
	string s = "AAAAAAAAAA" ;
    
	for(idx = 0; idx < 10; idx++)
	{
		lsb = num % 4;

		switch(lsb)
		{
			case 0:
				s[9 - idx] = 'A';
				break;
			
			case 1:
				s[9 - idx] = 'C';
				break;
			
			case 2:
				s[9 - idx] = 'G';
				break;
			
			case 3:
				s[9 - idx] = 'T';
				break;
		}
		num = num / 4;
	}

	return s;
}

void Solution::dump(void)
{
}

int main()
{
	string s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
	Solution sol;
	vector<string> repeatedSeqs;
	vector<string>::iterator vecit;
	
	cout << "string: " << s << endl;
	
	for (int i=0; i<300000; i++)
	{
		repeatedSeqs = sol.findRepeatedDnaSequences(s);
	}

#if 0	
	sol.dump();
	
	cout << "Repeated sub-sequences: " << endl;
	for(vecit = repeatedSeqs.begin(); vecit != repeatedSeqs.end(); vecit++)
	{
		cout <<(*vecit) << endl;
	}
#endif
}
