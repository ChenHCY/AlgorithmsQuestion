/* Leetcode 17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]
 
Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        
        if(digits.length() == 0){
            return res;
        }
        //use the String list to store the character group corresponding to each number
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(mapping, digits, "", 0, res); //call the dfs function
            
        return res;
    }
    
    //the dfs helper function
    private void dfs(String[] mapping, String digits, String str, int index, List<String> res){
        //recursive exit of DFS
        if(index == digits.length()){ //when the index is equal to the digits.length, means all the number's character group finished added into the result list
            res.add(str);
            return;
        }
        
        //get the character group corresponding to first number(String digits)
        String character = mapping[digits.charAt(index) - '0'];
        //all the possioble case 
        for(int i = 0; i < character.length(); i++){
            // add the character group corresponding to the next number(String digits)
            dfs(mapping, digits, str + character.charAt(i), index + 1, res);  // recursive  to next number's character
        }
    }
}
