package com.timkanake.JavaPlayground;

import java.util.HashSet;
import java.util.Scanner;

public class PlayGround {

	public static void main(String[] args) {
//		CodingChallenges cc = new CodingChallenges();
//		System.out.println(cc.stringIsSubsequence("here", "h2341234er"));
		
		//System.out.println(Arrays.toString(nums1));
		System.out.println(ackermann(1,1));
	}
	
	
	public static long ackermann(long m, long n) {
	      if (m == 0) return n + 1;
	      if (n == 0) return ackermann(m - 1, 1);
	      return ackermann(m - 1, ackermann(m, n - 1));
   }

	
	public int customParseInteger(String s){
		if(s.length() == 0){
			return 0;
		}
		char[] digits = s.toCharArray();
		int i = 1, sum = 0;
		for(int j = digits.length - 1; j >= 0; j--){
			sum += (digits[j] - '0') * i;
			i *= 10;
		}
		return -sum;
	}
	
	
	public int removeDuplicatesFromSorted(int[] nums){
		if(nums.length == 0 || nums.length == 1){
			return nums.length;
		}
		int j = 0;
		
		for(int i = 0; i < nums.length - 1; i++){
			if(nums[i] != nums[i+1]){
				nums[j++] = nums[i];
			}
		}
		nums[j++] = nums[nums.length - 1];
		return j;
	}
	public int makeEven(){
		Scanner in = new Scanner(System.in);
		System.out.println("hi");
		char[] numStr = in.next().toCharArray();
		char lastDigit = numStr[numStr.length -1];
		int toSwap = -1;
		// find first even digit less than or equal to lastDigit
		for(int i = 0; i < numStr.length -1; i++){
		    if(Integer.parseInt(Character.toString(numStr[i])) % 2 == 0 && numStr[i] - lastDigit <= 0){
		    	toSwap = i;
		    	break;
		    }
		}
		if(toSwap == -1){
			for(int i = numStr.length - 2; i >= 0; i--){
				Character c = numStr[i];
				if(Character.isDigit(c) && Integer.parseInt(Character.toString(c)) % 2 == 0){
					toSwap = i;
					break;
				}
			}
		}
		if(toSwap != -1){
			char temp = numStr[numStr.length - 1];
			numStr[numStr.length - 1] = numStr[toSwap];
			numStr[toSwap] = temp;
		}
		in.close();
		return Integer.parseInt(new String(numStr));
	}
	
	/*
	 * Bubble Sort Algorithm Implementation
	 */
	public void bubbleSort(int[] nums){
		boolean swapped = true;
		while(swapped){
			swapped = false;
			for(int i = 1; i < nums.length; i++){
				if(nums[i] < nums[i-1]){
					swapped = true;
					int temp = nums[i];
					nums[i] = nums[i-1];
					nums[i-1] = temp;
				}
			}
		}
	}
	
	public boolean isSorted(int[] nums){
		for(int i = 1; i < nums.length; i++){
			if(nums[i] < nums[i-1]){
				return false;
			}
		}
		return true;
	}
	
	
	public String imcSolution(int N, String S, String T) {
        // write your code in Java SE 8
		if(T.length() == 0){
			return "0,0";
		}
		String[] hitCellStrings = T.trim().split(" ");
		HashSet<String> hitCells = new HashSet<String>();
		for(String s: hitCellStrings){
			hitCells.add(s);
		}
		
		String[] shipStrings = S.split(",");
		Ship[] ships = new Ship[shipStrings.length];
		for(int i = 0; i < shipStrings.length; i++){
			String[] points = shipStrings[i].trim().split(" ");
			ships[i] = new Ship(N, points[0], points[1]);
		}
		
		int hitShips = 0;
		int sunkShips = 0;
		
		for(Ship s: ships){
			String[] boxes = s.boxes;
			for(String box: boxes){
				if(hitCells.contains(box)){
					s.hitBoxes++;
					hitCells.remove(box);
				}
			}
			if(s.hitBoxes == s.boxes.length){
				sunkShips++;
			}else if(s.hitBoxes > 0){
				hitShips++;
			}
		}
		return Integer.toString(sunkShips) + "," + Integer.toString(hitShips);
    }
		
	public class Ship{
		int hitBoxes;
        String[] boxes;
        String topLeftRow, topLeftColumn, bottomRightRow, bottomRightColumn;
        
        public Ship(int N, String topLeft, String bottomRight){
//        	if(topLeft.length() <= 2 && bottomRight.length() <= 2){
//        		topLeftRow = Character.toString(topLeft.charAt(0));
//        		bottomRightRow = Character.toString(bottomRight.charAt(0));
//        	}else{
//        		topLeftRow = (topLeft.length() > 2 ? topLeft.substring(0, 2): topLeft.substring(0, 1));
//        		bottomRightRow = (bottomRight.length() > 2 ? bottomRight.substring(0, 2): bottomRight.substring(0, 1));
//        	}
        	topLeftRow = topLeft.substring(0, topLeft.length()-1);
        	bottomRightRow = bottomRight.substring(0, bottomRight.length()-1);
        	topLeftColumn = Character.toString(topLeft.charAt(topLeft.length()-1));
        	bottomRightColumn = Character.toString(bottomRight.charAt(bottomRight.length()-1));
        	boxes = getShipBoxes(topLeftRow, topLeftColumn, bottomRightRow, bottomRightColumn);
        }
        
        public String[] getShipBoxes(String topLeftRow, String topLeftColumn, String bottomRightRow, String bottomRightColumn){
        	if(topLeftRow.equals(bottomRightRow)){
        		return onSameRow(topLeftRow, topLeftColumn, bottomRightRow, bottomRightColumn);
        	}else if(topLeftColumn.equals(bottomRightColumn)){
        		return onSameColumn(topLeftRow, topLeftColumn, bottomRightRow, bottomRightColumn);
        	}else{
        		return squareBox(topLeftRow, topLeftColumn, bottomRightRow, bottomRightColumn);
        	}
        }
        
        public String[] onSameRow(String topLeftRow, String topLeftColumn, String bottomRightRow, String bottomRightColumn){
        	if(topLeftRow.equals(bottomRightRow) && topLeftColumn.equals(bottomRightColumn)){
        		return new String[]{topLeftRow + topLeftColumn};
        	}
        	int len = bottomRightColumn.charAt(0) - topLeftColumn.charAt(0) + 1;
        	String[] boxes = new String[len];
        	for(int i = 0; i < len; i++){
        		boxes[i] = topLeftRow + Character.toString((char) (topLeftColumn.charAt(0) + i));
        	}
        	return boxes;
        }

        public String[] onSameColumn(String topLeftRow, String topLeftColumn, String bottomRightRow, String bottomRightColumn){
        	if(topLeftColumn.equals(bottomRightColumn) && topLeftRow.equals(bottomRightRow)){
        		return new String[]{topLeftRow + topLeftColumn};
        	}
        	int len = Integer.valueOf(bottomRightRow) - Integer.valueOf(topLeftRow) + 1;
        	String[] boxes = new String[len];
        	for(int i = 0; i < len; i++){
        		boxes[i] = Integer.toString((Integer.valueOf(topLeftRow) + i)) + topLeftColumn;
        	}
        	return boxes;
        }
        
        public String[] squareBox(String topLeftRow, String topLeftColumn, String bottomRightRow, String bottomRightColumn){
        	String[] boxes = new String[4];
        	String[] topRow = onSameRow(topLeftRow, topLeftColumn, topLeftRow, bottomRightColumn);
        	String[] bottomRow = onSameRow(bottomRightRow, topLeftColumn, bottomRightRow, bottomRightColumn);
        	for(int i = 0; i < 2; i++){
        		boxes[i] = topRow[i];
        	}
        	
        	for(int i = 2; i < 4; i++){
        		boxes[i] = bottomRow[i-2];
        	}
        	return boxes;
        }
	}
}
