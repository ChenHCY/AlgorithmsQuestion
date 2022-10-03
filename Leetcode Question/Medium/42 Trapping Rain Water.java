/*Leetcode 42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
*/

//Time: O(n)    Space: O(1)
//Two pointer method: left pointer travser from front to back
//                    right pointer travser from back to front
//Use others two indexes to save the left max height or right max height in the previous of every current pointer
//the left max heightest value or right heighest value minus current pointer height = total of trap water

class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        
        int res = 0; //the total of trap water
        
        //two pointer method
        int left = 0;
        int right = height.length - 1;
        
        //use two indexes to save the left max height or right max height in the previous of every current pointer
        int leftMax = height[left];
        int rightMax = height[right];
        
        //Use while-loop travser all the element from height
        while(left < right){
            if(height[left] < height[right]){
                //find the heighest in the previous of current pointer
                leftMax = Math.max(leftMax, height[left]);
                // Use the previous left max height minus current pointer height = total of trap water
                res += leftMax - height[left]; 
                left++;
            }else{
                //find the heighest in the previous of current pointer
                rightMax = Math.max(rightMax, height[right]);
                // Use the previous right max height minus current pointer height = total of trap water
                res += rightMax - height[right]; 
                right--;
            }
        }
        
        return res;
    }
}
