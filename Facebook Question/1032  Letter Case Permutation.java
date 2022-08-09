/* Lintcode 1032 · Letter Case Permutation

Description
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string. 
Return a list of all possible strings we could create.

Example 1:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Example 2:
Input: S = "3z4"
Output: ["3z4", "3Z4"]

Example 3:
Input: S = "12345"
Output: ["12345"]
*/

public class Solution {
    /**
     * @param s: a string
     * @return: return a list of strings
     *          we will sort your return value in output
     */
    public List<String> letterCasePermutation(String s) {
        // write your code here
        List<String> res = new ArrayList<>();

        if(s.length() == 0 || s == null){
            res.add("");
            return res;
        }
        helper(s, res, 0);
        return res;
    }

    private void helper(String A, List<String> res, int index){
        res.add(A);
        for(int i = index; i < A.length(); i++){
            if(Character.isDigit(A.charAt(i))){
               continue;
            }

            if(A.charAt(i) - 'a' >= 0){
                //lowercase part 
                helper(A.substring(0, i) + (char)(A.charAt(i) - 'a' + 'A') + A.substring(i+1), res, i + 1);
            } else{ // Upper case
                helper(A.substring(0, i) + (char)(A.charAt(i) - 'A' + 'a') + A.substring(i+1), res, i + 1);
            }
        }
    }
}
