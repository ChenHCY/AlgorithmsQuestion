/*Leetcode 4. Median of Two Sorted Arrays

// Binary Search Method（The optimal complexity solution)  O(log(min(m+n)))

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) { 
        //Binary Search Question
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        if(m == 0){
            return ((double) nums2[(n - 1) / 2] + (double) nums2[n / 2]) / 2;
        }
        
        int size = m + n;
        
        //find the cut1 line in between the nums1 range [0, m]
        int left = 0;
        int right = m;
        
        //cut1 and cut2 start position
        int cut1 = 0;
        int cut2 = 0;
        
        //Binary Search Question: we need find the cut-line satisfy two rules: 
        //1. nums1L(nums1 left cut line element) < nums2R(nums2 right cut line element)
        //2. nums2L(nums2 left cut line element) < nums1R(nums1 right cut line element)
        while(cut1 <= m){
            cut1 = (right - left) / 2 + left; // find where is the cut line in the nums1
            cut2 = size / 2 - cut1; // find where is the cut line in the nums2
            
            double nums1L = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1]; //find the nums1 element left of cutline
            double nums2L = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1]; //find the nums2 element left of cutline
            double nums1R = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1]; //find the nums1 element right of cutline
            double nums2R = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];//find the nums2 element right of cutline
            
            if(nums1L > nums2R){
                right = cut1 - 1;
            } else if(nums2L > nums1R) {
                left = cut1 + 1;
            } else{ // if satisfy two rules, it means here is correct cut position
                //we need check whether size is odd or even
                if(size % 2 == 0){ // if size is even
                    nums1L = nums1L > nums2L ? nums1L : nums2L; // find the max value of left part;
                    nums1R = nums1R < nums2R ? nums1R : nums2R; // find the min value of right part;
                    return (nums1L + nums1R) / 2;
                } else{ // if size is odd
                    nums1R = nums1R < nums2R ? nums1R : nums2R; // find the min value of right part
                    return nums1R;
                }
            }
        }
        
        return -1;
    }
}
