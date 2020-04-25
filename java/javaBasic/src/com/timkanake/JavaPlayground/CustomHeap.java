package com.timkanake.JavaPlayground;

public class CustomHeap {
	
	int[] vals;
	int heapSize;
	int heapLen;
	public CustomHeap(int[] nums){
		vals = nums;
		heapSize = nums.length-1;
	}
	
	public void buildMaxHeap(){
		for(int i = (heapSize/2); i >= 0; i--){
			maxHeapify(i);
		}
	}
	
	// TODO
	public void buildMinHeap(){
	}
	
	public void heapSort(){
		buildMaxHeap();
		for(int i = vals.length-1; i >= 1; i--){
			int temp = vals[0];
			vals[0] = vals[i];
			vals[i] = temp;
			this.heapSize--;
			maxHeapify(0);
		}
	}
	public void maxHeapify(int i){
		int l = getLeft(i);
		int r = getRight(i);
		int largest = 0;
		if(l <= heapSize && vals[l] > vals[i]){
			largest = l;
		}else{
			largest = i;
		}
		
		if(r <= heapSize && vals[r] > vals[largest]){
			largest = r;
		}
		
		if(i != largest){
			int temp = vals[i];
			vals[i] = vals[largest];
			vals[largest] = temp;
			maxHeapify(largest);
		}
	}
	
	// TODO
	public void minHeapify(int i){
		
	}
	
	public int getLeft(int i){
		return (2*i) + 1;
	}
	
	public int getRight(int i){
		return (i*2) + 2;
	}
	
	public int getParent(int i){
		return (i-1)/2;
	}

}
