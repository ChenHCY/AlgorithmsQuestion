/*Leetcode 34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
 
Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
*/

//Solution 1: Binary Search Method: Used two small function that find the starting and ending position of a given target value.
//Time: O(n)  Space: O(1)
class Solution {
    //The main function
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int left = 0;
        int right = nums.length - 1;
        res[0] = checkFirst(nums, left, right, target); //the starting position
        res[1] = checkLast(nums, left, right, target); //the ending position
        
        return res;
    }
    
    //Small Function 1: Used to find the starting position of give target value
    public static int checkFirst(int[] nums, int l, int r, int target){
        int res = -1;
        while(l <= r){ //maybe have just one element in the nums list(For Example: nums = [1] target = 1)  ==> so left-pointer need can equals right-pointer
            int mid = l + (r - l) / 2;
            if(nums[mid] == target){
                res = mid; //save this mid postion value
                r = mid - 1; //so can delete mid postion
            } else if(nums[mid] < target){
                l = mid + 1;
            } else{
                r = mid - 1; 
            }
        }
        return res;
    }
    
    //Small Function 2: Used to find the ending position of give target value
    public static int checkLast(int[] nums, int l, int r, int target){
        int res = -1;  
        while(l <= r){ //maybe have just one element in the nums list(For Example: nums = [1] target = 1) ==> so left-pointer need can equals right-pointer
            int mid = l + (r - l) / 2;
            if(nums[mid] == target){
                res = mid;//save this mid postion value
                l = mid + 1; //so can delete mid postion
            } else if(nums[mid] < target){
                l = mid + 1;
            } else{
                r = mid - 1;
            }
        }
        return res;
    }
}

//Solution 2: use a little trick  Time: O(logn)  Space: O(1)
//Example: [5,7,7,8,8,10], target = 8
//               l   r
//              e s e s
//left-pointer to find 7.5 // right-pointer to find 8.5
//then get the area(target) through used the two start-pointer 
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0 || nums == null){
            return new int[]{-1, -1};
        }
        
        int[] res = new int[]{-1, -1};
        int left = BinarySearch(nums, target - 0.5);
        int right = BinarySearch(nums, target + 0.5);
        if(right - left == 0){
            return res;
        }
        res[0] = left;
        res[1] = right - 1;
        return res;
    }
    
    //Small function used Binary Search to find the starting and ending position of a given target value.
    public int BinarySearch(int[] nums, double target){
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end){
            int mid = (end - start) / 2 + start;
            //because target is Double format number, so only need find the start-pointer(left) position
            if(nums[mid] < target){
                start = mid + 1;
            } else{
                end = mid - 1;
            }
        }
        
        return start;
    }
}
