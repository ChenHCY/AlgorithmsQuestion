/* Leetcode 11. Container With Most Water
You are given an integer array height of length n. 
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1
 
Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
*/

//Solution 1: 
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        
        while(right !=  left){
            if(height[left] > height[right]){
                result = Math.max(height[right] * (right - left), result);
                right--;
            } else{
                result = Math.max(height[left] * (right - left), result);
                left++;
            }
        }
        return result;
    }
}

//Solution 2:
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        
        while(left < right){
            //Compare every time to find the max area (the maximum amount of water a container can store)
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left)); // Rectangle area: width * height
            
            //Because we need find the maximum area, so need find the max height and width as possible as it can
            //so if the left-pointer height is smaller, move the left-pointer into the next one
            //if right-pointer height is smaller, move the right-pointer into the next one
            if(height[left] < height[right]){
                left++;
            } else{
                right--;
            }
        }
        return res;
    }
}
