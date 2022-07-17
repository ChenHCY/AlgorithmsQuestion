/*Leetcode 350. Intersection of Two Arrays II
Given two integer arrays nums1 and nums2, return an array of their intersection. 
Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.


Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
 

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

//Solution 1: Used Two HashMap
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        //HashMap<Key, Value>
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        int size = 0;
        
        for(int num : nums1){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for(int num2 : nums2){
            if(map.containsKey(num2)){
                map2.put(num2, map2.getOrDefault(num2, 0) + 1);
                size++;
                map.put(num2, map.get(num2) - 1);
                if(map.get(num2) == 0){
                    map.remove(num2);
                }
            }
        }
        
        int[] res = new int[size];
        int i = 0;
        
        for(int key : map2.keySet()){
            for(int j = 0; j < map2.get(key); j++){
                res[i++] = key;
            }
        }
        
        return res;
    }
}

//Solution 2: Used one HashMap and one ArrayList
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        //HashMap<Key, Value>
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int size = 0;
        
        for(int num : nums1){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for(int num2 : nums2){
            if(map.containsKey(num2) && map.get(num2) > 0){
                list.add(num2);
                map.put(num2,  map.get(num2) - 1);
            }
        }
        
        int[] res = new int[list.size()];
        
        for(int i = 0; i <= list.size() - 1; i++){
            res[i] = list.get(i);
        }
        
        return res;
    }
}

//Solution 3: Used one ArrayList
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                res.add(nums1[i]);
                i++;
                j++;
            } else if(nums1[i] < nums2[j]){
                i++;
            } else {
                j++;
            }
        }
        
        return listToInteger(res);
    }
    
    public int[] listToInteger(List<Integer> list){
        int[] res = new int[list.size()];
        
        for(int i = 0; i <= list.size() - 1; i++){
            res[i] = list.get(i);
        }
        
        return res;
    }
}
