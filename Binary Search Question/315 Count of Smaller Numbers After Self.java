/* Leetcode 315. Count of Smaller Numbers After Self

Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Example 2:

Input: nums = [-1]
Output: [0]
Example 3:

Input: nums = [-1,-1]
Output: [0,0]
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
*/
/* 思路： 此题是统计一个nums[] array中的每个数字之后，有多少个数字小于它，然后储存进一个list

Solution 1: 蛮力解决，使用两个for-loop进行遍历，然后挨个统计 ==》 时间复杂度非常高 不符合要求

Solution 2: Binary Search的方法，

1. 首先复杂nums[] array所有数字，然后储存进一个arraylist，然后把这个list按照升序排序

这样可以通过每个number的index位置值来统计有多少个数字是小于它的

2. Bianry Search Function: 普通的二分法搜索，查找一个升序的有重复数字的数组中，特定target值的位置

3. 把找到的位置值（也表示有多少个数字小于当前数字）储存进res list，然后在clone list中删除

==》因为题目是要求找到每个数字身后有多少个数字小于它，所以我们按照nums[]的顺序挨个遍历，然后删除，可以避免统计curr number身前的数字

==》 因为list.remove() function 删除是根据坐标位置的。所以要用当前num的index值，也就是我们通过binary Search 找到的值

Example: nums[] array => { 5, 2, 6, 1 } /  clone list => [1, 2, 5, 6]
5 = { 1, 2, 5, 6 } index = 2;
2 = { 1, 2, 6 } index = 1;
6 = { 1, 6 } index = 1
1 = { 1 } index = 0
*/

//Solution 1: Time: O(n^2)  Space: O(1)   ==> 时间复杂度过于高 “超时”
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            int count = 0;
            for(int j = i; j < nums.length; j++){
                if(nums[j] < nums[i]){
                    count += 1;
                }
            }
            res.add(count);
        }

        return res;
    }
}

//Solution 2: Time: best: O(nlogn) wrost: O(n^2)  / Space: O(n)
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        List<Integer> clone = new ArrayList<>();

        //clone the nums[] array and save into list
        for(int num : nums){
            clone.add(num);
        }

        //then sort the clone list with increasing oreder
        Collections.sort(clone); //让clone list 按照升序排列，方便统计小于curr num的数

        //main travser and check there is how many number is less than curr element
        for(int i = 0; i < nums.length; i++){
            //这里的pos可以表示了nums[i]在clone list的坐标位置，也能统计有多少比它小的数
            int pos = BinarySearch(clone, nums[i]); //Count the Smaller Numbers of curr number
            res.add(pos); //then save into res list
            clone.remove(pos); //因为list.remove() function 删除是根据坐标位置的。所以要用当前num的index值
        }

        return res;
    }
    //Binary Search Function to count there is how many number less than the curr number
    //普通的二分法搜索，查找一个升序的有重复数字的数组中，特定target值的位置
    public int BinarySearch(List<Integer> clone, int target){
        int left = 0;
        int right = clone.size();

        while(left < right){
            int mid = left + (right - left) / 2;

            if(clone.get(mid) < target){
                left = mid + 1;
            } else if(clone.get(mid) >= target){
                right = mid;
            } 
        }

        return left;
    }
}
