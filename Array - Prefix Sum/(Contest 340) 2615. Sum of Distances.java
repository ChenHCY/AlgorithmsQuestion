/* Leetcode  (Contest 340) 2615. Sum of Distances && 2121. Intervals Between Identical Elements

You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, 
where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.

Example 1:

Input: nums = [1,3,1,1,2]
Output: [5,0,3,4,0]
Explanation: 
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
When i = 4, arr[4] = 0 because there is no other index with value 2. 

Example 2:

Input: nums = [0,5,3]
Output: [0,0,0]
Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
*/

/* 总体思路： 因为nums.length <= 10^5， 数据长度很大 ==》 必须要进行前缀和处理来缩减运行时间*/

// Leetcode  (Contest 340) 2615. Sum of Distances
class Solution {
    public long[] distance(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        long[] res = new long[nums.length];
        
        //count the number and the number index from nums[] arry
        //then saved into hashmap
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<>()); //create a empty list if meet new number
            }
            map.get(nums[i]).add(i);
        }
        
        //traver and calculate the value
        //entrySet()中的方法返回HashMap映射中所有键值对的集合。
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
           List<Integer> list = entry.getValue(); // 提取是每个值对应的index list集合
           long sum = 0;
           for(int num : list){
               sum += num; //首先计算每个值对应整个index的集合的sum
           }
           long preSum = 0;
           int size = list.size();
           for(int j = 0; j < size; j++){
               int val = list.get(j); // 每个index的value
               preSum += val;
                /*System.out.println("1."+ val);
               System.out.println("2." + preSum);
                System.out.println("3." + sum);*/
               res[val] = sum + (2 * j + 2 - size) * (long)val - 2 * preSum;
           }
        }
        
        return res;
    }
}

// 2121. Intervals Between Identical Elements
class Solution {
    public long[] getDistances(int[] arr) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        long[] res = new long[arr.length];
        
        //count the number and the number index from nums[] arry
        //then saved into hashmap
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], new ArrayList<>()); //create a empty list if meet new number
            }
            map.get(arr[i]).add(i);
        }
        
        //traver and calculate the value
        //entrySet()中的方法返回HashMap映射中所有键值对的集合。
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
           List<Integer> list = entry.getValue(); // 提取是每个值对应的index list集合
           long sum = 0;
           for(int num : list){
               sum += num; //首先计算每个值对应整个index的集合的sum
           }
           long preSum = 0;
           int size = list.size();
           for(int j = 0; j < size; j++){
               int val = list.get(j); // 每个index的value
               preSum += val;
                /*System.out.println("1."+ val);
               System.out.println("2." + preSum);
                System.out.println("3." + sum);*/
               res[val] = sum + (2 * j + 2 - size) * (long)val - 2 * preSum;
           }
        }
        
        return res;        
    }
}
