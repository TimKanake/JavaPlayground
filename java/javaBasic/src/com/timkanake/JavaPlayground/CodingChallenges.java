package com.timkanake.JavaPlayground;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class CodingChallenges {
	Queue<Integer> q = new LinkedList<Integer>();
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x){ 
			val = x;
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x){ 
			val = x;
		}
	}
	// s1 is word s2 is longer
	public boolean stringIsSubsequence(String s1, String s2){
		int m = s1.length(), n = s2.length();
		int j = 0;
		for(int i = 0; i < n && j < m; i++){
			if(s1.charAt(j) == s2.charAt(i))
				j++;
		}
		return j == m;
	}
	public int[] getInts(String s, int i){
		HashSet<Character> operators = new HashSet<Character>();
		operators.add('/');
		operators.add('*');
		operators.add('+');
		operators.add('-');
		int firstInt = 0, firstIntIndex = 0, secondInt = 0, secondIntIndex = s.length()-1;
		int icopy = i;
		i--;
		while(i > 0){
			if(operators.contains(s.charAt(i)) || i == 0){
				firstIntIndex = i;
				firstInt = Integer.parseInt(s.substring(firstIntIndex, icopy));
				break;
			}
			i--;
		}
		i = icopy;
		i++;
		while(i < s.length()){
			if(operators.contains(s.charAt(i)) || i == s.length()-1){
				secondIntIndex = i;
				secondInt = Integer.parseInt(s.substring(icopy+1, i+1));
			}
			i++;
		}
		
		return new int[]{firstInt, firstIntIndex, secondInt, secondIntIndex};
	}
	
	public int calculate(String s) {
        // Division first, Multiplication second, Addition third, Substraction last
        //division "4+3/6";
        s = s.replace(" ", "");
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '/'){
            	int[] usefulInts = getInts(s, i);
            	System.out.println(Arrays.toString(usefulInts));
                int result = usefulInts[0] / usefulInts[2];
                s = s.substring(0, usefulInts[1]+1) + Integer.toString(result) + s.substring(usefulInts[3]+1);
                System.out.println(s);
            }
        }
        
        //multiplication
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '*'){
            	int[] usefulInts = getInts(s, i);
            	System.out.println(Arrays.toString(usefulInts));
                int result = usefulInts[0] * usefulInts[2];
                s = s.substring(0, usefulInts[1]+1) + Integer.toString(result) + s.substring(usefulInts[3]+1);
                System.out.println(s);
            }
        }
        
        //addition
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '+'){
            	int[] usefulInts = getInts(s, i);
            	System.out.println(Arrays.toString(usefulInts));
                int result = usefulInts[0] + usefulInts[2];
                s = s.substring(0, usefulInts[1]+1) + Integer.toString(result) + s.substring(usefulInts[3]+1);
                System.out.println(s);
            }
        }
        
        //subtraction
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '/'){
            	int[] usefulInts = getInts(s, i);
            	System.out.println(Arrays.toString(usefulInts));
                int result = usefulInts[0] - usefulInts[2];
                s = s.substring(0, usefulInts[1]+1) + Integer.toString(result) + s.substring(usefulInts[3]+1);
                System.out.println(s);
            }
        }
        
        return Integer.parseInt(s);
    }
	
	public int longestPalindromicSubsequence(String str){
		int n = str.length();
		int i, j, cl;
		int[][] records = new int[n][n];
		
		for(i = 0; i < n; i++){
			records[i][i] = 1;
		}
		
		for(cl = 2; cl <= n; cl++){
			for(i = 0; i < n-cl+1; i++){
				j = i + cl - 1;
				if(str.charAt(i) == str.charAt(j) && cl == 2){
					records[i][j] = 2;
				}else if(str.charAt(i) == str.charAt(j)){
					records[i][j] = records[i+1][j-1] + 2;
				}else{
					records[i][j] = Math.max(records[i][j-1], records[i+1][j]);
				}
			}
		}
		return records[0][n-1];		
	}
	
	// Kadane's Algorithm for Max-Subarray
	public int maxSubarraySum(int[] nums){
		if(nums.length == 0){
			return 0;
		}
		int maxSoFar = nums[0], maxFinal = nums[0];
		
		for(int i = 1; i < nums.length; i++){
			maxSoFar = Math.max(maxSoFar, maxSoFar + nums[i]);
			maxFinal = Math.max(maxFinal, maxSoFar);
		}
		
		return maxFinal;
	}
	
	public ArrayList<String> appearsInAAndNotInB(String A, String B){
        Set<String> words = new HashSet<String>();
        ArrayList<String> wordsToRemove = new ArrayList<String>();
        for(String s: A.trim().split(" ")){
            if(words.contains(s)){
                wordsToRemove.add(s);
            }else{
                words.add(s);
            }
        }
        
        for(int i = 0; i < wordsToRemove.size(); i++){
            words.remove(wordsToRemove.get(i));
        }
        
        HashSet<String> bWords = new HashSet<String>();
        for(String s: B.trim().split(" ")){
                bWords.add(s);
        }
        
        ArrayList<String> uniqueWords = new ArrayList<String>();
        for(String s: words){
        	if(! bWords.contains(s)){
        		uniqueWords.add(s);
        	}
        }
        
        return uniqueWords;
        
    }
	
	public int minSubarray(int[] nums){
		if(nums.length == 0){
			return 0;
		}
		int minSoFar = Integer.MAX_VALUE, minFinal = Integer.MAX_VALUE;
		
		for(int i = 0; i < nums.length; i++){
			if(minSoFar > 0){
				minSoFar = nums[i];
			}else{
				minSoFar += nums[i];
			}
			minFinal = Math.min(minFinal, minSoFar);
		}
		
		return minFinal;
	}
	public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder strB = new StringBuilder();
        for(int i = words.length-1; i >= 0; i--){
        	if(!words[i].equals(" ") && words[i].length() != 0){
        		strB = strB.append(words[i]).append(" ");
        	}
        }
        
        return strB.toString().trim();
    }
	
	public int findMinimumInSortedRotatedArray(int[] nums){
		if(nums.length == 1){
			return nums[0];
		}
		int start = 0, end  = nums.length-1;
		if(nums[end] > nums[start])
			return nums[start];
		while(end > start){			
			int mid = start + (end - start) / 2;
			if(nums[mid] > nums[mid + 1]){
				return nums[mid+1];
			}
			if(nums[mid] < nums[mid - 1]){
				return nums[mid];
			}
			if(nums[mid] > nums[0]){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) return new ArrayList<ArrayList<String>>();
        HashMap<String, List<String>> ans = new HashMap<String, List<String>>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<String>());
            ans.get(key).add(s);
        }
        return new ArrayList<ArrayList<String>>((Collection<? extends ArrayList<String>>) ans.values());
    }
	
	public boolean areAnagrams(String s1, String s2){
		if(s1.length() != s2.length()){
			return false;
		}
		int[] chars = new int[256];
		for(char c: s1.toCharArray()){
			chars[c]++;
		}
		for(char c: s2.toCharArray()){
			chars[c]--;
		}
		
		for(int i = 0; i < chars.length; i++){
			if(chars[i] != 0){
				return false;
			}
		}
		return true;		
	}
	
	public String minimumWindowSubstring(String s1, String s2){
		int len1 = s1.length(), len2 = s2.length(), no_of_chars = 256;
		if(len2 > len1){
			System.out.println("No such window exists.");
			return "";
		}
		
		int[] s2pat = new int[no_of_chars];
		int[] s1pat = new int[no_of_chars];
		
		for(int i = 0; i < len2; i++){
			s2pat[s2.charAt(i)]++;
		}
		
		int start = 0, startIndex = -1, minLen = Integer.MAX_VALUE, count = 0;
		
		for(int j = 0; j < len1; j++){
			System.out.println(count);
			s1pat[s1.charAt(j)]++;
			// keep moving pointer until you find a window with all characters
			if(s2pat[s1.charAt(j)] != 0 && s1pat[s1.charAt(j)] <= s2pat[s1.charAt(j)]){
				count++;
			}
			
			if(count == len2){
				// while the window still exists
				while(s1pat[s1.charAt(start)] > s2pat[s1.charAt(start)] || s2pat[s1.charAt(start)] == 0){
					if(s1pat[s1.charAt(start)] > s2pat[s1.charAt(start)]){
						s1pat[s1.charAt(start)]--;
					}
					start++;
				}
				int windowLen = j - start + 1;
				System.out.println(s1.substring(start, start + windowLen));
				if(minLen > windowLen){
					minLen = windowLen;
					startIndex = start;
				}
			}
			
		}
		if(startIndex == -1){
			System.out.println("No such window exists.");
			return "";
		}
		return s1.substring(startIndex, startIndex + minLen);
	}
	
	public static class Contest{
		boolean importance;
		int luck;
		public Contest(boolean i, int l) {
			importance = i;
			luck = l;
		}
	}
	
	/*0
	 * Takes two list nodes pointing to two numbers in a list (reversed) and returns their sum
	 * 
	 * E.g. 2 -> 4 -> 3 + 5 -> 6 -> 4 = 7 -> 0 -> 8
	 */
	public int maxIncreaseKeepingSkyline1(int[][] grid) {
		int[] top = new int[grid.length];
		int[] side = new int[grid[0].length];
		
		for(int i = 0; i < top.length; i++) {
			int maxTemp = Integer.MIN_VALUE;
			for(int j = 0; j < side.length; j++) {
				if(grid[i][j] > maxTemp) {
					maxTemp = grid[i][j];
				}
			}
			side[i] = maxTemp;
		}
		
		for(int i = 0; i < side.length; i++) {
			int maxTemp = Integer.MIN_VALUE;
			for(int j = 0; j < top.length; j++) {
				if(grid[j][i] > maxTemp) {
					maxTemp = grid[j][i];
				}
			}
			top[i] = maxTemp;
		}
		System.out.println(Arrays.toString(top));
		System.out.println(Arrays.toString(side));
		int increasedAmount = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(Math.min(top[j], side[i]) - grid[i][j] > 0) {
					increasedAmount += Math.min(top[j], side[i]) - grid[i][j];
					grid[i][j] = Math.min(top[j], side[i]);
				}
			}
		}
		
		return increasedAmount;
	}

	public ListNode addTwoNumbers(ListNode firstNumber, ListNode secondNumber) {
		if(firstNumber == null) return secondNumber;
		if(secondNumber == null) return firstNumber;
		int sum = firstNumber.val + secondNumber.val;
		int rem = sum % 10;
		ListNode sumNode = new ListNode(rem);
		ListNode holder = sumNode;
		int carryOut = (sum >= 10 ? 1 : 0);
		firstNumber = firstNumber.next; 
		secondNumber = secondNumber.next;
		while(firstNumber != null && secondNumber!= null) {
			sum = firstNumber.val + secondNumber.val + carryOut;
			carryOut = (sum >= 10 ? 1 : 0);
			rem = sum % 10;
			sumNode.next = new ListNode(rem);
			sumNode = sumNode.next;
			firstNumber = firstNumber.next;
			secondNumber = secondNumber.next;
		}
		
		if(firstNumber == null && secondNumber != null) {
			sumNode.next = getRemainingSumNodes(secondNumber, carryOut);
		}else if (secondNumber == null && firstNumber != null) {
			sumNode.next = getRemainingSumNodes(firstNumber, carryOut);
		}else if(carryOut == 1){
			sumNode.next = new ListNode(1);
		}
		return holder;		
	}
	
	/*
	 * Given an integer array with no duplicates, this builds the maximum tree defined as below:
	 * 
	 * 1. The root is the maximum number in the array
	 * 2. The left subtree is the maximum tree constructed from left part sub-array divided by the maximum number.
	 * 3. The right subtree is the maximum tree constructed from right part sub-array divided by the maximum number.
	 */
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return constructMaximumBinaryTreeHelper(nums, 0, nums.length-1);
	}
	
	public TreeNode constructMaximumBinaryTreeHelper(int[] nums, int start, int end) {
		if(start < 0 || end < 0 || start > end) {
			return null;
		}
		if(start == end) {
			TreeNode temp = new TreeNode(nums[start]);
			return temp;
		}
		
		int maxIndex = maxInArray(nums, start, end);
		TreeNode head = new TreeNode(nums[maxIndex]);
		head.left = constructMaximumBinaryTreeHelper(nums, start, maxIndex-1);
		head.right = constructMaximumBinaryTreeHelper(nums, maxIndex+1, end);
		return head;
	}
	

	public int maxInArray(int[] nums, int start, int end) {
		for(int i = start+1; i <= end; i++) {
			if(nums[i] > nums[start]) {
				start = i;
			}
		}
		return start;
	}
	
	/*
	 * Returns the index of the first occurrence of the needle string in the full string
	 * If the needle string is an empty string we return 0
	 */
	public int customStrStr(String haystack, String needle) {
		if(needle.length() == 0) {
			return 0;
		}else if(needle.length() > haystack.length() || (needle.length() == haystack.length() && needle != haystack)) {
			return -1;
		}
		
		for(int i = 0; i <= haystack.length()-needle.length(); i++) {
			if(haystack.charAt(i) == needle.charAt(0)) {
				if(haystack.substring(i, i+needle.length()).equals(needle)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public ArrayList<Integer> concateArrays(int[] first, int[] second){
	    ArrayList<Integer> newar = new ArrayList<Integer>();
	    for(int i = 0; i < first.length; i++){
	        newar.add(first[i]);
	    }
	    for(int i = 0; i < second.length; i++){
	        newar.add(second[i]);
	    }
	    return newar;
	}
	
	public int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	}
	
	
	public TreeNode invertTree(TreeNode root) {
		if(root == null) {
			return null;
		}
		TreeNode leftHolder = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(leftHolder);
		return root;
	}
	
	/*
	 * Checks if a string is a palindrome
	 */
	public boolean isPalindrome(String str) {
		if(str.length() == 1) 
			return true;
		for(int i = 0; i < str.length()/2; i++) {
			if(str.charAt(i) != str.charAt(str.length()-(i+1))) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if a given integer reads the same front to back and back to front
	 */
	public boolean intIsPalindrome(int num) {
		if(num < 0) return false;
		
		String numAsString = Integer.toString((Integer) num);
		return isPalindrome(numAsString);
	}
	
	/*
	 * Given a string containing just the characters '(', ')', '{', '}',
	 *  '[' and ']', determines if the input string is valid.
	 */
	public boolean isValidParentheses(String s){
		Stack<Character> charsStack = new Stack<Character>();
		Set<Character> openingChars = new HashSet<Character>(Arrays.asList(new Character[]{'(','[','{'}));
		
		for(int i = 0; i < s.length(); i++){
			if(openingChars.contains((Character) s.charAt(i))){
				charsStack.push((Character) s.charAt(i));
			}else{
				if(charsStack.isEmpty())
					return false;
				char matchingChar = (char) charsStack.pop();
				if((matchingChar == '[' && s.charAt(i) != ']') || (matchingChar == '{' && s.charAt(i) != '}') ||
						(matchingChar == '(' && s.charAt(i) != ')')){
					return false;
				}
			}
		}
		return charsStack.isEmpty();
	}
	
	
	/*
	 * Given a string return the longest substring palindrome
	 */
	public String longestPalindrome(String str){
		int [] indices = longestPalindromeIndex(str);
		return str.substring(indices[0], indices[1]+1);	
	}
	
	
	/*
	 * Given a string return the indices of the longest string palindrome
	 */
	public int[] longestPalindromeIndex(String str){
		int[][] palindromLengthValues = new int[str.length()][str.length()];
		int maxValue = Integer.MIN_VALUE;
		int startIndex = 0;
		int endIndex = 0;
		for(int i = palindromLengthValues.length-1; i >= 0; i--){
			for(int j = palindromLengthValues.length-1; j >= 0; j--){
				if(i == j){
					// if it's one letter, then it's a palindrome
					palindromLengthValues[i][j] = 1;
				}else if(i > j){
					// invalid indices
					palindromLengthValues[i][j] = 0;
				}else{
					if(str.charAt(i) == str.charAt(j)){						
						if(j-i > 1 && palindromLengthValues[i+1][j-1] == 0){
							// if the enclosed string is not a palindrome then this string is not a palindrome
							palindromLengthValues[i][j] = 0;
						}else{
							palindromLengthValues[i][j] = 2 + palindromLengthValues[i+1][j-1];
						}
					}
				}
				if(palindromLengthValues[i][j] > maxValue){
					maxValue = palindromLengthValues[i][j];
					startIndex = i;
					endIndex = j;
				}
			}
		}
		int[] indices = {startIndex, endIndex};
		return indices;
	}
	
	
	/*
	 * Increases height of buildings in a 2D array while mantaining the skyline
	 */
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int[] top = new int[grid.length];
		int[] side = new int[grid[0].length];
		
		for(int i = 0; i < top.length; i++) {
			int maxTemp = Integer.MIN_VALUE;
			for(int j = 0; j < side.length; j++) {
				if(grid[i][j] > maxTemp) {
					maxTemp = grid[i][j];
				}
			}
			side[i] = maxTemp;
		}
		
		for(int i = 0; i < side.length; i++) {
			int maxTemp = Integer.MIN_VALUE;
			for(int j = 0; j < top.length; j++) {
				if(grid[j][i] > maxTemp) {
					maxTemp = grid[j][i];
				}
			}
			top[i] = maxTemp;
		}
		int increasedAmount = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(Math.min(top[j], side[i]) - grid[i][j] > 0) {
					increasedAmount += Math.min(top[j], side[i]) - grid[i][j];
					grid[i][j] = Math.min(top[j], side[i]);
				}
			}
		}
		
		return increasedAmount;
	}
	
	
	public ListNode getRemainingSumNodes(ListNode startNode, int carryOut) {
		ListNode prev = startNode, holder = startNode;
		if(carryOut == 0)
			return startNode;
		while(startNode != null && carryOut == 1) {
			int sum = startNode.val + carryOut;
			int rem  = sum % 10;
			startNode.val = rem;
			carryOut = (sum >= 10? 1 : 0);
			prev = startNode;
			startNode = startNode.next;
		}
		if(carryOut == 1) {
			prev.next = new ListNode(1);
		}
		return holder;		
	}
	
	public boolean[] getPrimeNumbers(int max){
		boolean[] flags = new boolean[max + 1];
		for(int i = 0; i < flags.length; i++){
			flags[i] = true;
		}
		flags[0] = false;
		flags[1] = false;
		// flags[2] = true;
		int prime = 2;
		
		while(prime <= (int) Math.sqrt(max)){
			crossOff(flags, prime);
			prime = getNextPrime(flags, prime);
		}
		return flags;
		
	}
	
	public void crossOff(boolean[] flags, int prime){
		for(int i = prime * prime; i < flags.length; i+= prime){
			flags[i] = false;
		}
	}
	
	public int getNextPrime(boolean[] flags, int prime){
		int next = prime + 1;
		while(next < flags.length && ! flags[next]){
			next++;
		}
		return next;
	}
	
	/*
	 * Removes duplicates from a sorted array
	 * 
	 * Returns length of array up to where there are no duplicates, values after that length don't matter
	 */
	public int removeDuplicates(int[] nums) {
		if(nums.length <= 1) {
			return nums.length;
		}
		
		int curIntIndex = 0;
		int runnerIndex = 1;
		
		while(runnerIndex < nums.length) {
			if(nums[runnerIndex] == nums[curIntIndex]) {
				runnerIndex++;
			}else {
				curIntIndex++;
				nums[curIntIndex] = nums[runnerIndex];
				runnerIndex++;
			}
		}
		return curIntIndex+1;
	}
	
	public int[] sortArrayAsWave(int[] ar) {
		for(int i = 0; i < ar.length; i+=2) {
			if(i > 0 && ar[i] < ar[i-1]) {
				int temp = ar[i];
				ar[i] = ar[i-1];
				ar[i-1] = temp;
			}
			if(i < ar.length-1 && ar[i] < ar[i+1]) {
				int temp = ar[i];
				ar[i] = ar[i+1];
				ar[i+1] = temp;
			}
		}
		return ar;
	}
	
	public int sumArraList(ArrayList<Integer> arL) {
		int sum = 0;
		for(Integer i: arL) {
			sum += i;
		}
		return sum;
	}	
	
}
