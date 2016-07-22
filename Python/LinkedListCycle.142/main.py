""" Use recursive method 
  1. Create a magic node "magic"
  2. Traverse every node in the intput list
  	2.1. Check if the node is a self-cycle, if yes, return the node
 	2.2. Check if the double-next node is the magic node, if yes, return the next node
"""

from inspect import currentframe, getframeinfo
import copy

class ListNode(object):
	def __init__(self, x):
		self.val = x
		self.next = None

LISTSIZE = 4

# :type head: ListNode; rtype: ListNode 

class Solution(object):
	
	def get_linenumber(self):
		cf = currentframe()
		return cf.f_back.f_lineno

	def __init__(self):
		self.magic = ListNode(-1)
		self.nextList = []
	
	def restoreList(self, head):
		it = head
		for node in self.nextList:
			it.next = node
			it = it.next

	def detectCycle(self, head):
		if head == None:
			return None
		
		it = head

		""" Traverse each node """
		while (1):
			
			""" Detect if single cycle """
			if it == it.next:
				self.restoreList(head)
				return it
	
			if it.next != None:

				""" Detect if the grandson of the node is the magic node """
				if it.next.next == self.magic:
					self.restoreList(head)
					return it.next
			
			 	""" Marked as the node be traverseed, and jump to the next node """
				tmp = it.next
				self.nextList.append(tmp)
				it.next = self.magic
				it = tmp

			else:
				return None	
	
	def dump(self, head):
		it = head
		
		for idx in range(0, LISTSIZE):
			print "(", it.val, ", ", it, "), " 
			it = it.next
		
		print "\n"

""" main """
cycle = None
sol = Solution()
root = ListNode(3)
root.next = ListNode(2)
root.next.next = ListNode(0)
root.next.next.next = ListNode(-4)
root.next.next.next.next = root.next  

""" Dump the input """
sol.dump(root)

""" Obtain the solution """
cycle = sol.detectCycle(root)

if cycle != None:
	print "Cycle exist with val:", cycle.val, "@", cycle, "\n"
else:
	print "No cycle exist \n"

""" Dump the traversed input """
sol.dump(root)

