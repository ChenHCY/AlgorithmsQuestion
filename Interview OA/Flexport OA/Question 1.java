/* Flexport OA Question 3 Same withe Leetcode 532

Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

  0 <= i, j < nums.length
  i != j
  nums[i] - nums[j] == k
  Notice that |val| denotes the absolute value of val.

Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
 

Constraints:

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107
 
*/

class Result {

    /*
     * Complete the 'countPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER k
     */

    public static int countPairs(List<Integer> numbers, int k) {
        if(numbers.size() == 0){
            return 0;
        }
        int res = 0;
        
        //Used HashMap to save the frequency of every number element
        HashMap<Integer, Integer> map = new HashMap<>();
        //save all the frequency of every number of element
        for(int i = 0; i < numbers.size(); i++){
            map.put(numbers.get(i), map.getOrDefault(numbers.get(i), 0) + 1);
        }
        
        //for-loop to travser all the map(key-value)
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(k == 0){
                if(e.getValue() >= 2){
                    res += 1;
                } 
            } else{ // if find any valid pairs which a + k = b
                if(map.containsKey(e.getKey() + k)){
                    res += 1;
                }
            }
        }
        
        return res;
    }
}
