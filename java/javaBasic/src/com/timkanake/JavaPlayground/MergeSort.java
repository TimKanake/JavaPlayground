package com.timkanake.JavaPlayground;

public class MergeSort {
	
	public void sort(int[] nums){
		sortHelper(nums, 0, nums.length-1);
	}
	
	private void sortHelper(int[] nums, int start, int end){
		if(start < end){
			int mid = (start + end) / 2;
			sortHelper(nums, start, mid);
			sortHelper(nums, mid + 1, end);
			merge(nums, start, mid, end);
		}		
	}
	
	public void merge(int[] nums, int start, int mid, int end){
		int lSize = mid - start + 1;
		int rSize = end - mid;
		
		int left[] = new int[lSize];
		int right[] = new int[rSize];
		for(int i = 0; i < lSize; i++){
			left[i] = nums[start + i];
		}
		for(int i = 0; i < rSize; i++){
			right[i] = nums[mid + 1 + i];
		}
		
		int i = 0, j = 0;
		
		int k = start;
		
		while(i < lSize && j < rSize){
			if(left[i] <= right[j]){
				nums[k] = left[i];
				i++;
			}else{
				nums[k] = right[j];
				j++;
			}
			k++;
		}
		
		while(i < lSize){
			nums[k] = left[i];
			i++;
			k++;
		}
		while(j < rSize){
			nums[k] = right[j];
			j++;
			k++;
		}
	}
}
