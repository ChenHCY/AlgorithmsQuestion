/*Leetcode 4. Median of Two Sorted Arrays
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

// Time complexity: O (m+n)   Space complexity: O (m+n)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = mergeSort(nums1, nums2);
        
        //check the res list length is even or odd
        if(res.length % 2 == 0){ //if the length is even, use res.length / 2 to find the two mid-range number
            int n = res.length / 2; 
            return (double) (res[n] + res[n-1]) / 2;
        } else{ // if the length is odd, just return the mid element(res.length / 2)
            return (double) (res[res.length / 2]);
        }
    }
    
    //Merge Sort Method, sort the nums1 and nums2 be a total list
    public int[] mergeSort(int[] n1, int[] n2){
        int i = 0; 
        int j = 0; 
        int z = 0;
        int m = n1.length;
        int n = n2.length;
        int[] total = new int[m + n];
        
        while(i < m && j < n){
            if(n1[i] <= n2[j]){
                total[z++] = n1[i++];
            } else{
                total[z++] = n2[j++];
            }
        }
        
        while(i < m){
            total[z++] = n1[i++];
        }
        
        while(j < n){
            total[z++] = n2[j++];
        }
        
        return total;
    }
}
