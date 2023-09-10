/* (Contest 362) 2848 Points That Intersect With Cars

You are given a 0-indexed 2D integer array nums representing the coordinates of the cars parking on a number line. For any index i, 
nums[i] = [starti, endi] where starti is the starting point of the ith car and endi is the ending point of the ith car.

Return the number of integer points on the line that are covered with any part of a car.

Example 1:

Input: nums = [[3,6],[1,5],[4,7]]
Output: 7
Explanation: All the points from 1 to 7 intersect at least one car, therefore the answer would be 7.

Example 2:

Input: nums = [[1,3],[5,8]]
Output: 7
Explanation: Points intersecting at least one car are 1, 2, 3, 5, 6, 7, 8. There are a total of 7 points, therefore the answer would be 7.
 

Constraints:

1 <= nums.length <= 100
nums[i].length == 2
1 <= starti <= endi <= 100
*/

class Solution {
    //差分数组
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] res = new int[101]; // the resulet array
        int[] carNum = new int[101];
        
        int count = 0;
        
        //create the diff array => O(n) n is nums.length
        for(List<Integer> list : nums){
            int start = list.get(0);
            int end = list.get(1);
            
             //diff[start] += 1 意味着给 nums[i, ..] 所有的元素都加了 1
            //diff[end + 1] -= 1 意味着给 nums[j+1, ..] 所有的元素都减去了 1
            //结合起来， 就是对于 nums[start..end] 中的所有元素都加 1 了
            carNum[start] += 1;
            if(end + 1 < 101){
                carNum[end + 1] -= 1;
            }
        }
        
        
        //差分数组是前缀和数组的逆运算，所以对于一个差分数组求前缀和，就可以还原数组
        res[0] = carNum[0];
        for(int i = 1; i < carNum.length; i++){
            res[i] = carNum[i] + res[i-1];
        }
        
        for(int j = 0; j < res.length; j++){
            if(res[j] >= 1){
                count++;
            }
        }
        
        return count;
    }
}
