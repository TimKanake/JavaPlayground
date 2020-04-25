package com.timkanake.JavaPlayground;

public class DynamicProgramming {
	
	class LongestCommonSubsequenceArrays{
		int[][] B;
		int[][] C;
		
		public LongestCommonSubsequenceArrays(int alen, int blen){
			B = new int[alen][blen];
			C = new int[alen + 1][blen + 1];
		}
	}
	
//	public void buildLongestSubsequenceArrays(char[] x, char[] y){
//		int m = x.length;
//		int n = y.length;
//		LongestCommonSubsequenceArrays lcsA = new LongestCommonSubsequenceArrays(m, n);
//		
//		for(int i = 0; i < m; i++){
//			lcsA.C[i][0] = 0;
//		}
//		
//		for(int j = 0; j <= n; j++){
//			lcsA.C[0][j] = 0;
//		}
//		
//		for(int i = 0; i < m; i++){
//			for(int j = 1; j <= n; j++){
//				if(x[i] == y[j]){
//					lcsA.C[i][j] = lcsA.C[i-1][j-1] + 1;
//					
//				}
//			}
//		}
//	}
	/*
	 * Returns the n-th fibonacci number
	 * O(n) running time
	 */
	public int getNthFibonacci(int n) {
		int prev = 1, prevprev = 1;
		if(n <= 1) {
			return 1;
		}
		for(int i = 2; i <= n; i++) {
			int tmp = prev + prevprev;
			prevprev = prev;
			prev = tmp;
		}
		return prev;
	}
	// Bottom Up Approach: Build the solutions to the sub-problems
	public int buttomUpCutRod(int[] prices, int n) {
		int[] vals = new int[n+1];
		vals[0] = 0;
		
		for(int j = 1; j <= n; j++) {
			int q = Integer.MIN_VALUE;
			for(int i = 1; i <= j; i++) {
				q = Math.max(q, prices[i-1] + vals[j - i]);
			}
			vals[j] = q;
		}
		return vals[n];
	}
	
	public int topDownCutRod(int[] prices, int n) {
		int[] vals = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			vals[i] = Integer.MIN_VALUE;
		}
		return topDownCutRodHelper(prices, n, vals);
	}
	
	public int topDownCutRodHelper(int[] prices, int n, int[] vals) {
		if(vals[n] >= 0) {
			return vals[n];
		}
		
		int q = Integer.MIN_VALUE;
		if(n == 0) {
			q = 0;
		}else {
			for(int i = 1; i <= n; i++) {
				q = Math.max(q, prices[i - 1] + topDownCutRodHelper(prices, n - i, vals));
			}
		}
		vals[n] = q;
		return q;
	}
}
