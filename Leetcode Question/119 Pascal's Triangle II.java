/* Leetcode 119. Pascal's Triangle II
Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]

Example 2:
Input: rowIndex = 0
Output: [1]

Example 3:
Input: rowIndex = 1
Output: [1,1]
*/
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> row = new ArrayList();
        
        for(int i = 0; i <= rowIndex; i++){
            row.add(0, 1);
            for(int j = 1; j < row.size() - 1; j++){
                row.set(j, row.get(j) + row.get(j + 1));
                //use pre row number sum get the new row
            }
            
            result.add(new ArrayList(row));
            
        }
        
        return result.get(rowIndex);
    }
}
