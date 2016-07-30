""" 
 * 1. Extract all the subarray without zero into a subarray list
 * 2. Obtain all the maximum product in the subarray list, there are only three cases 
 * 	2.1. the number of negative integer in the subarray is even, maximum = product them all
 *	2.2. the number of negative integer is odd, there are 4 products need be compared, ex
 *  2.2.1. ex: 2, -3, -2, 4, -3, 2, 8
 *  2.2.2. product0(product until reach a negative from left): 2
 *  2.2.3. product1(product of the remaining element except the negative): (-2)*4*(-3)*2*8
 *  2.2.4. product0(product until reach a negative from right): 2*8
 *  2.2.5. product1(product of the remaining element except the negative): 2*(-3)*(-2)*4
 * 3. Obtain the maximum from the maxlist 
"""

from inspect import currentframe, getframeinfo
import copy


ARRAYSIZE = 8

# :type nums: List[int]; rtype: int 

class Solution(object):
	
	def get_linenumber(self):
		cf = currentframe()
		return cf.f_back.f_lineno

	def __init__(self):
		self.subarrays = []
		self.maxList = []
	
	def maxProduct(self, nums):

		# Corner case 1: Only 1 element
		if len(nums) == 1:
			return nums[0]

		# Extract all the subarray without zero 
		self.extSubarray(nums)
	
		# Obtain all the local maximun 
		for subarray in self.subarrays:
			maxProd = self.caclMax(nums, subarray)
			self.maxList.append(maxProd)
		
		# Obtain the global maximun 
		maxProd = self.maxList[0]
		for idx in range(1 ,len(self.maxList)):
			maxProd = max(self.maxList[idx], maxProd)

		# Corner case 2: all the local maximum are negative, and zero exist
		if (maxProd < 0) and (len(self.subarrays) > 0):
			return 0
		
		return maxProd	

	def extSubarray(self, nums):
		
		head = 0

		for idx in range(0, len(nums)):
			tail = idx
			if nums[idx] == 0:
				tail = idx - 1 
				subarray = (head, tail)
				
				if tail >= 0:
					self.subarrays.append(subarray)

				head = idx + 1

			if (idx == (len(nums) - 1)) and (nums[idx] != 0):
				subarray = (head, tail)
				self.subarrays.append(subarray)

	def caclMax(self, nums, subarray):
		if subarray[0] == subarray[1]:
			return nums[subarray[0]]

		# Obtain the number of negative integer 
		count = 0
		for idx in range(subarray[0], subarray[1] + 1):
			if nums[idx] < 0:
				count += 1

		# If the number of negative integer is even
		if (count % 2) == 0: 
			maxProd = 1
			for idx in range(subarray[0], subarray[1] + 1):
				maxProd = maxProd * nums[idx]
		# If the number of negative integer is even """
		else:
			lprod0 = 1
			lprod1 = 1
			rprod0 = 1
			rprod1 = 1
			
			# Obtain the two local maxmums from left 
			for idx in range(subarray[0], subarray[1] + 1):
				if nums[idx] < 0:
					bound = idx
					idx += 1
					break
				lprod0 = lprod0 * nums[idx]
			
			for idx in range(idx, subarray[1] + 1):
				lprod1 = lprod1 * nums[idx]
		
			if (bound == subarray[0]): lprod0 = 0
			if (bound == subarray[1]): lprod1 = 0

			# Obtain the two local maxmums from right 
			for idx in range(subarray[1], subarray[0] - 1, -1):
				if nums[idx] < 0:
					bound = idx
					idx -= 1
					break
				rprod1 = rprod1 * nums[idx]
			
			for idx in range(idx, subarray[0] - 1, -1):
				rprod0 = rprod0 * nums[idx]
		
			if (bound == subarray[0]): rprod0 = 0
			if (bound == subarray[1]): rprod1 = 0
				
			# Obtain the maximum from the four local maximums */
			lprod0 = max(lprod0, lprod1);
			rprod0 = max(rprod0, rprod1);
			maxProd = max(lprod0, rprod0);
		
		return maxProd
	
	def dump(self, nums):
		# Input nums
		print "Input nums"
		for num in nums:
			print num, ", ",

		print "\nSubarrays: "
		for subarray in self.subarrays:
			print "(", subarray[0], ", ", subarray[1], ")"

# Main function
sol = Solution()
nums = [3, 0, -3]

# Obtain the solution 
maxProd = sol.maxProduct(nums)

# Dump the private data 
sol.dump(nums)

print "Maximum product:", maxProd

