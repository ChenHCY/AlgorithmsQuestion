/* 133 · Longest Word
Description
Given a dictionary, find all of the longest words in the dictionary.

Example 1:
	Input: {
		"dog",
		"google",
		"facebook",
		"internationalization",
		"blabla"
		}
	Output: ["internationalization"]


Example 2:
	Input:  {
		"like",
		"love",
		"hate",
		"yes"		
		}
	Output: ["like", "love", "hate"]
  */

public class Solution {
    public List<String> longestWords(String[] dictionary) {
        // write your code here
        List<String> result = new ArrayList<>();
        result.add(dictionary[0]);
        int currentL = 0;
        int maxL = 0;

        for(int i = 0; i < dictionary.length; i++){
            currentL = dictionary[i].length();
            if (currentL > maxL){
                result.clear();
                result.add(dictionary[i]);
                maxL = currentL;
            } else if(currentL == maxL){
                result.add(dictionary[i]);
            }
        }
        return result;
    }
}
	
