/* Leetcode 1300. Sum of Mutated Array Closest to Target
Given an integer array arr and a target value target, return the integer value such that when 
we change all the integers larger than value in the given array to be equal to value, the sum 
of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.
Notice that the answer is not neccesarilly a number from arr.

Example 1:
Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.

Example 2:
Input: arr = [2,3,5], target = 10
Output: 5

Example 3:
Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361
 
Constraints:
1 <= arr.length <= 104
1 <= arr[i], target <= 105
*/

class Solution {
    public int findBestValue(int[] arr, int target) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        //find the max value of arr[]
        for(int num : arr){
            sum += num;
            max = Math.max(max, num);
        }
        
        //Used the Binary Search method ==> min and max
        int min = 0;
        int res = 0;
        int diff = Integer.MAX_VALUE; //used to store the difference with target
        
        while(min <= max){
            int mid = (max - min) / 2 + min;
            sum = getSum(arr, mid); //get the total sum after arr[] changed
            
            if(sum > target){
                max = mid - 1;
            } else{
                min = mid + 1;
            }
            
            //count and compare the difference ==> In case of a tie, return the minimum such integer.
            if(Math.abs(sum - target) < diff ||(Math.abs(sum - target) == diff && mid < res)){
                res = mid; //get the value result
                diff = Math.abs(sum - target); //renew the new diff value
            }
        }
        
        return res;
    }
    
    //get the total sum after arr[] changed
    private static int getSum(int[] array, int value){
        int sum = 0;
        for(int num : array){
            // change all the integers larger than value in the given array to be equal to value,
            sum += (num > value) ? value : num;
        }
        
        return sum;
    }
}
