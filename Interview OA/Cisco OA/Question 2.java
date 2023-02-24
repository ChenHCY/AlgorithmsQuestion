/*Leetcode 5 最长回文子字符串*/

public static void longestPalindrome(String inputStr)
	{
		// Write your code here
		int n = inputStr.length();

		//used dp method
		boolean[][] dp = new boolean[n][n];
		int maxLength = 1; //the max length of find palindromic sub-string
		int start = 0; // the substring range start position

		//1. consider the substring of length 1 
		for(int i = 0; i < n; i++){
			dp[i][i] = true;
		}

		//2. consider the substring of length is 2
		for(int j = 0; j < n-1; j++){
			if(inputStr.charAt(j) == inputStr.charAt(j+1)){
				dp[j][j+1] = true;
				maxLength = 2;
				start = j;
			}
		}

		//3.check for substring of length is greater than 2
		for(int k = 3; k <= n; k++){
			for(int z = 0; z < n - k + 1; z++){
				int curr = z + k - 1;
				if(inputStr.charAt(z) == inputStr.charAt(curr) && dp[z+1][curr-1]){
					dp[z][curr] = true;
					//we used "compareTo" to check whether have the curr sub-string is the lexicographically smallest one.
					if(k > maxLength || (k == maxLength && inputStr.substring(z, curr + 1).compareTo(inputStr.substring(start, start + maxLength)) < 0)){
						maxLength = k;
						start = z;
					}
				}
			}
		}

		System.out.print(inputStr.substring(start, start+maxLength));	
		
}
