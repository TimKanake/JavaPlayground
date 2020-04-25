package com.timkanake.JavaPlayground;

public class ProjectEuler {	
	public static int sumSquareDifference(){
		int sumOfSquares = 0, squareOfSum = 0;
		for(int i = 1; i <= 100; i++){
			sumOfSquares += (int) Math.pow(i,  2);
			squareOfSum += i;
		}
		return (int) Math.pow(squareOfSum, 2) - sumOfSquares;
	}

}
