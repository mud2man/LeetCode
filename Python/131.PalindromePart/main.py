""" Use recursive method 
 1. Pick up the first palindrome from left to right, and call "PickPalin" again
 	1.1. take a string ababc as an example ex: a|babc, ab|abc, aba|bc ...
 2. Check if the substring is equal to to string, if yes, terminate; else go to step1
"""
from inspect import currentframe, getframeinfo
import copy

# :type s: str; rtype: List[List[str]] 

class Solution(object):
	palins = []

	def partition(self, s):
		strs = []
		self.palins = []

		if len(s) > 0:
			strs.append(s)
			self.pickPalin(strs)

		return self.palins

	def pickPalin(self, strs):

		""" Pop the last string """
		tailstr = str(strs.pop())
		strsize = len(tailstr)

		""" Check if the last string with char number = 1 ~ (strize - 1) is palindrome """
		for charnum in range(1, strsize):
			if self.isPalin(charnum, tailstr):
				topstr = tailstr[0: charnum] 
				botstr = tailstr[charnum:]
				newstrs = copy.copy(strs)
				newstrs.append(topstr)
				newstrs.append(botstr)

				""" check if the last string is a character, if yes, it's a palindrome """
				if len(botstr) == 1:
					self.palins.append(newstrs)
				else:
					self.pickPalin(newstrs)

		""" Check if the last string with char number = strsize is a palindrome """
		if self.isPalin(strsize, tailstr):
			strs.append(tailstr)
			self.palins.append(strs)

	def isPalin(self, charnum, tailstr):
		end = charnum - 1	
		for idx in range(0, (((charnum - 1) / 2) + 1)):
			if tailstr[idx] != tailstr[end-idx]:
				return False
		return True
	
	def get_linenumber(self):
		cf = currentframe()
		return cf.f_back.f_lineno

""" main """
s = "aab"

""" Obtain the solution """
sol = Solution();
palinlist = sol.partition(s)

""" Dump the solution """
for palins in palinlist:
	print palins

s = "a"
palinlist = sol.partition(s)
for palins in palinlist:
	print palins
