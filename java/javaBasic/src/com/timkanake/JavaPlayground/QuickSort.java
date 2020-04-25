package com.timkanake.JavaPlayground;


// Implements the QuickSort Algorithm using Lomuto Partition Scheme

public class QuickSort {
	
	public void sort(int[] nums){
		sortHelper(nums, 0, nums.length-1);
	}
	
	public void sortHelper(int[] nums, int start, int end){
		if(start < end){
			int pivot = partition(nums, start, end);
			sortHelper(nums, start, pivot - 1);
			sortHelper(nums, pivot + 1, end);
		}
	}
	
	public int partition(int[] nums, int start, int end){
		int pivot = nums[end];
		int i = start-1;
		for(int j = start; j < end; j++){
			if(nums[j] <= pivot){
				i++;
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}
		
		int temp = nums[i+1];
		nums[i+1] = nums[end];
		nums[end] = temp;
		return i+1;
	}
}
