/* Leetcode 228. Summary Ranges 
You are given a sorted unique integer array nums.
A range [a,b] is the set of all integers from a to b (inclusive).
Return the smallest sorted list of ranges that cover all the numbers in the array exactly. 
That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:
"a->b" if a != b
"a" if a == b
 
Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"

Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"
 
Constraints:

0 <= nums.length <= 20
-231 <= nums[i] <= 231 - 1
All the values of nums are unique.
nums is sorted in ascending order.
*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        StringBuilder range = new StringBuilder(); // check the number range
        int start = 0; // start pointer // find the range start in whcih number
        int end; // end pointer => find the range end in which number
        
         if(nums.length == 0){
            return res;
        }
        
        //String Builder should start at 1, nums[] start at nums[0]
        for(int i = 1; i < nums.length; i++){
            //check and find the two numbers' difference is more than 1.
            if(nums[i] - nums[i - 1] != 1){
                end = i - 1; // move the end-pointer to this range last number
                
                //check whether is range or single number
                if(nums[start] != nums[end]){
                    range.append(nums[start] + "->" + nums[end]); //Add the Range
                } else {
                    range.append(nums[start]); // Single number
                }
            
                res.add(range.toString()); //And the range or single number into the Summary List
            
                start = i; // move start pointer into next range first number
            }
            range.setLength(0);//clear the StringBuilder
        }
        
        //check and add the last number range 
        if(nums[start] != nums[nums.length - 1]){ 
             range.append(nums[start] + "->" + nums[nums.length - 1]); //add the range
        }
        else{
            range.append(nums[start]);   // add the single number
        }
        
        res.add(range.toString()); //And the range into Summary List
        
        return res; //return the Summary List
    }
}
