/* Leetcode 118. Pascal's Triangle
Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]
*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        //Use Arraylist inside
        List<List<Integer>> result = new ArrayList();
        
        List<Integer> row = new ArrayList();
        
        for(int i = 0; i <= numRows - 1; i++){
            row.add(0, 1); //every time add a 1 in the began 
            //start at third row to deal with "each number is the sum of the two numbers"
            for(int j = 1; j < row.size() - 1; j++){
                row.set(j, row.get(j) + row.get(j + 1));
                //use pre row number sum get the new row
            }
            
            result.add(new ArrayList(row)); //everytime save the row list into the result list
        }
        
        return result;
    }
}
