/* (Contest 333)  Leetcode 2570. Merge Two 2D Arrays by Summing Values

You are given two 2D integer arrays nums1 and nums2.

nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
Each array contains unique ids and is sorted in ascending order by id.

Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:

Only ids that appear in at least one of the two arrays should be included in the resulting array.
Each id should be included only once and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
Return the resulting array. The returned array must be sorted in ascending order by id.

Example 1:

Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
Output: [[1,6],[2,3],[3,2],[4,6]]
Explanation: The resulting array contains the following:
- id = 1, the value of this id is 2 + 4 = 6.
- id = 2, the value of this id is 3.
- id = 3, the value of this id is 2.
- id = 4, the value of this id is 5 + 1 = 6.

Example 2:

Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
Explanation: There are no common ids, so we just include each id with its value in the resulting list.
 

Constraints:

1 <= nums1.length, nums2.length <= 200
nums1[i].length == nums2[j].length == 2
1 <= idi, vali <= 1000
Both arrays contain unique ids.
Both arrays are in strictly ascending order by id.
*/

//My Solution: Used hashmap to find the same id element, and then count their value
//Time:O(n^2)  Space:O(n)
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        //travser all the element from nums1
        for(int[] num : nums1){
            map.put(num[0], num[1]);
        }
        
        //then check whether nums2 have same id, and count the value with same id
        for(int[] num2 : nums2){
            int id = num2[0];
            int value = num2[1];
            //check whether have same id
            if(map.containsKey(id)){
                value += map.get(id);
            }
            map.put(id, value);
        }
        
        //then sort the hashmap with id increasing order and save into a int[] array
        int[][] res = new int[map.size()][2];
        int index = 0;
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
            res[index][0] = m.getKey();
            res[index][1] = m.getValue();
            index++;
        }
        
        Arrays.sort(res, (a,b) -> a[0] - b[0]);
        return res;
    }
}

//The best time solution: 

/* nums1[i] = [idi, vali] nums2[i] = [idi, vali]

题意是合并nums1[]和怒骂是nums2[]中所有具有相同id的元素，然后返回一个新的int[] array

思路： 两个移动的pointer i和j, i在nums1[]中滑动， j在nums2[]中滑动

用一个while-loop进行遍历，比较和查找是否有id一样的元素id, 然后计算对应的value

再加入到list中  ==》 List<int[]> list = new ArrayList<>();

最后把list改成一个int[] array ==> int[][] res = new int[list.size()][2];

*/

//Time:O(n)  Space:O(n)
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int m = nums1.length;
        int n = nums2.length;

        //travser all the element from nums1[] and nums2[]
        //and compare whether the element have same id, then count the value
      //用一个while-loop进行遍历，比较和查找是否有id一样的元素id, 然后计算对应的value
        while(i < m && j < n){
            if(nums1[i][0] == nums2[j][0]){ //if the two element id is same
                list.add(new int[] {nums1[i][0], nums1[i][1] + nums2[j][1]});
                i++;
                j++;
            } else if(nums1[i][0] < nums2[j][0]){
                list.add(nums1[i]);
                i++;
            } else{
                list.add(nums2[j]);
                j++;
            }
        }

        //如果nums1[]中还有剩余没有被添加进list中的数
        while(i < m){
            list.add(nums1[i]);
            i++; 
        }

        //如果nums2[]中还有剩余没有被添加进list中的数
        while(j < n){
            list.add(nums2[j]);
            j++; 
        }

        //change the array list format to be a int[] array
        int[][] res = new int[list.size()][2];
        for(int k = 0; k < list.size(); k++){
            res[k] = list.get(k);
        }
        
        return res;
    }
}
