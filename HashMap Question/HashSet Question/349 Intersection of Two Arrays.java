/* Leetcode 349. Intersection of Two Arrays
Given two integer arrays nums1 and nums2, return an array of their intersection. 
Each element in the result must be unique and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
 
Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
*/

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        //HashSet is an implementation of Set Interface which does not allow duplicate value.
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        //save and add the number from nums1 list into hashset set1
        for(int num : nums1){
            set1.add(num);
        }
        
        //save number from nums2 list into hashset set2
        for(int num2 : nums2){
            //check whether the number element was the same as nums1 list
            if(set1.contains(num2)){
                set2.add(num2); // only add the number element was same with nums1 list
            }
        }
        
        int[] res = new int[set2.size()]; // get a integer list and list size
        int r = 0;
        
        //add the Intersection of Two Arrays into the integer list
        for(int num3 : set2){
            res[r++] = num3;
        }
            
        return res;
    }
}
