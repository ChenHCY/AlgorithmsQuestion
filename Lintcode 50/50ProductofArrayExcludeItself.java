/* 50 · Product of Array Exclude Itself
Description
Given an integers array A.
Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]B[i]=A[0]∗...∗A[i−1]∗A[i+1]∗...∗A[n−1], calculate B WITHOUT divide operation.Out put B

Example 1:
Input:
A = [1,2,3]
Output:
[6,3,2]
Explanation:
B[0] = A[1] * A[2] = 6; B[1] = A[0] * A[2] = 3; B[2] = A[0] * A[1] = 2

Example 2:
Input:
A = [2,4,6]
Output:
[24,12,8]
Explanation:
B[0] = A[1] * A[2] = 24; B[1] = A[0] * A[2] = 12; B[2] = A[0] * A[1] = 8
*/

public class Solution {
    public List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
        List<Long> result = new ArrayList<>(nums.size());
        for(int i = 0; i < nums.size(); i++){
            long number = 1;
            for(int j = 0; j < i; j++){
                number *= nums.get(j);
            }
            for(int j = i+1; j < nums.size(); j++){
                 number *= nums.get(j);
            }
            result.add(number);
        }
       return result;
    }
}
