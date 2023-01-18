/* Contest 328) Leetcode 2537. Count the Number of Good Subarrays 

Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.

Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i], k <= 109
*/

//Two pointer sliding window and HashMap
//Time: O(n) Space:O (n)
class Solution {
    public long countGood(int[] nums, int k) {
        long res = 0;
        long pair = 0;

        //Two pointer -  Sliding Window | HashMap
        int left = 0;
        int right = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        //while-loop to travser all the element from nums[] array
        while(right < nums.length){
            pair += map.getOrDefault(nums[right], 0); //count how many pairs in the [left, right] range
            //count every numbers frequency in the [left, right] range
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while(pair >= k){ //if the pairs more than K valuem, it's good subarray'
                //count every numbers frequency in the [left, right] range after remove left-pointer element
                map.put(nums[left], map.get(nums[left]) - 1);
                //count how many pairs in the [left, right] range after remove left-pointer element
                //beacuse every number in the nums[] can be pair unlimited times, so it need remove the hashmap value
                pair -= map.getOrDefault(nums[left], 0);
                //remove left-pointer
                left++;  // we shrink the window from left
            } 


            // The siding window we are maintaining here has the min num of elements in range [left,right] 
            // So ==> if [left, right] is valid that means [left-1,right] is also valid, [left-2, right] is also valid. 
            res += left; // count the good subarrays  
        /*Beacuse if k = 2, and [4,3,2,2,4] is a good subarray, then [3,1,4,3,2,2,4] [1,4,3,2,2,4] also is a good subarray, 
        so it should add the index position of left-pointer*/

        // Thus we just add the left pointer to ans as it will contains the range from [0,left-1] which is forming the valid pair with right

            right++;
        }

        return res;
    }
}
