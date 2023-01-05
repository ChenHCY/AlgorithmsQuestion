/* Leetcode Contest 326 2521. Distinct Prime Factors of Product of Array

Given an array of positive integers nums, return the number of distinct prime factors in the product of the elements of nums.

Note that:
A number greater than 1 is called prime if it is divisible by only 1 and itself.
An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
 
Example 1:
Input: nums = [2,4,3,7,10,6]
Output: 4
Explanation:
The product of all the elements in nums is: 2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7.
There are 4 distinct prime factors so we return 4.

Example 2:
Input: nums = [2,4,8,16]
Output: 1
Explanation:
The product of all the elements in nums is: 2 * 4 * 8 * 16 = 1024 = 210.
There is 1 distinct prime factor so we return 1.
 

Constraints:

1 <= nums.length <= 104
2 <= nums[i] <= 1000
*/

//Solution 1: Used two for-loop and a HashSet to find all distinct prime numbers 
//==> HashSet can remove the duplicate number

//Time complexity: O(N * Prime Factors of each num)
//Space complexity: O(Distict Prime Factors of each num)

class Solution {
    public int distinctPrimeFactors(int[] nums) {
        //HashSet can remove the duplicate number 
        HashSet<Integer> res = new HashSet<>();

        //for-loop to travser all the element from nums[] array
        for(int num : nums){
            //check and find all distinct prime numbers in every element
            for(int i = 2; i <= num; i++){
                while(num % i == 0){ //Get the distinct prime numbers
                    res.add(i); // HashSet can remove the duplicate number 
                    num /= i; //move to find next distinct prime numbers
                }
            }
        }
        
        //return the size of res set (All the number of distinct prime factors) 
        return res.size();
    }
}
