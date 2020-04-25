package com.timkanake.JavaPlayground;

public class DivideAndConquer {
	
	// O(n) running time
	public int[] findMaximumSubArrayIterative(int[] nums) {
		int maxSum = Integer.MIN_VALUE, curSum = 0, curSumStart = 1;
		int start = 0, end = 0;
		for(int i = 0; i < nums.length; i++) {
			curSum += nums[i];
			if(curSum > maxSum) {
				start = curSumStart;
				end = i;
				maxSum = curSum;
			}
			if(curSum < 0) {
				curSum = 0;
				curSumStart = i + 1;
			}
		}
		return new int[] {start, end, maxSum};
	}
	
	// O(n log n) running time
	public int[] findMaximumSubArrayRecursive(int[] nums, int low, int high) {
		if(high == low) {
			return new int[] {low, high, nums[low]};
		}
		int mid = (low + high) / 2;
		int[] left = findMaximumSubArrayRecursive(nums, low, mid);
		int[] right = findMaximumSubArrayRecursive(nums, mid + 1, high);
		int[] cross = findMaxCrossingSubArray(nums, low, mid, high);
		
		if(left[2] >= right[2] && left[2] >= cross[2]) {
			return left;
		}else if(right[2] >= left[2] && right[2] >= cross[2]) {
			return right;
		}
		return cross;
	}
	
	/*
	 * Given an array of length high-1, this method returns three indices
	 * that correspond to the indices for finding the maximum sum
	 * sub-array that crosses through the middle
	 */
	public int[] findMaxCrossingSubArray(int[] nums, int low, int mid, int high) {
		int leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE;
		int sum = 0, maxLeft = low, maxRight = high;
		
		for(int i = mid; i >= low; i--) {
			sum += nums[i];
			if(sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		
		sum = 0;
		for(int i = mid + 1; i <= high; i++) {
			sum += nums[i];
			if(sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		return new int[] {maxLeft, maxRight, leftSum + rightSum};
	}

}
