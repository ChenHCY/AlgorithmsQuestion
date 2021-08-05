/* 25 · Print X
Description
Enter a positive integer 'N'. You need to return a list of strings as shown in the Example.

Example 1:
Input:
n = 1
Output:
["X"]
Explanation: The answer list can be seen as the following shape: X

Example 2:
Input: n = 2
Output: ["XX", "XX"]
Explanation: The answer list can be seen as the following shape:XX
                                                                XX
*/

public class Solution {
    /**
     * @param n: An integer.
     * @return: A string list.
     */
    public List<String> printX(int n) {
        // write your code here
        ArrayList<String> result = new ArrayList<>();
        char[] line = new char[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                line[j] = ' ';
            }
            line[i] = 'X';
            line[n - i - 1] = 'X';
            result.add(String.valueOf(line));
        }
         return result;
    }
}
