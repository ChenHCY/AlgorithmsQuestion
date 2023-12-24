/* 2975. Maximum Square Area by Removing Fences From a Field

There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal 
and vertical fences given in arrays hFences and vFences respectively.

Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the 
coordinates (1, vFences[i]) to (m, vFences[i]).

Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 
if it is impossible to make a square field.

Since the answer may be large, return it modulo 109 + 7.

Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to (m, n) 
and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be removed.

Example 1:
Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
Output: 4
Explanation: Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.

Example 2:
Input: m = 6, n = 7, hFences = [2], vFences = [4]
Output: -1
Explanation: It can be proved that there is no way to create a square field by removing fences.
 

Constraints:
3 <= m, n <= 10^9
1 <= hFences.length, vFences.length <= 600
1 < hFences[i] < m
1 < vFences[i] < n
hFences and vFences are unique.
*/

// 计算水平，和垂直 中，每一个间隔的可能(也就是边长的可能)，全部储存进哈希表中
// 然后找到一个同时在水平和垂直哈希表都存在的最大值
// 这就是能得到的最大方型面积边，边 * 边 得到最大面积
//Time: O(h^2 + v^2) 其中 h 为 hFences 的长度，v 为 vFences 的长度。
//Space: O(h^2 + v^2)  开了两个HashSet, 储存所有可能的长度值
class Solution {
    // hFences: 水平方向上存在的切线，vFences: 垂直方向上存在的切线
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int MOD = 1000000007;
        //计算得到所有可能的长度
        Set<Integer> hLengthSet = countLen(m, hFences); 
        Set<Integer> vLengthSet = countLen(n, vFences);

        int maxlen = 0;
        //然后再当中，找到最大值
        for(int num : hLengthSet){
            if(vLengthSet.contains(num)){
                maxlen = Math.max(maxlen, num);
            }
        }
        //然后判断能不能找到符合要求的长度，可以输出面积，不行输出-1
        return maxlen == 0 ? -1 : (int)((long) maxlen * maxlen % MOD);
    }

    //计算水平和垂直部分一些切线后能得到的所有长度
    public static Set<Integer> countLen(int max, int[] nums){
        // 因为hFences 和 vFences 只包括square中存在的切线
        // 所以首先把他复原成一个含有所有边界的array
        int len = nums.length;
        nums = Arrays.copyOf(nums, len + 2); //把square的四周界限都加入进去，找到可能的最长部分
        nums[len] = 1;
        nums[len + 1] = max;
        Arrays.sort(nums); //排序，形成一个升序的序列 从 1 - max（square的最大界限）

        //遍历找到删除一些切线后，所有可能的长度 ==> Time: O(h^2) or O(v^2)
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                set.add(nums[j] - nums[i]);
            }
        }

        return set; //里面储存的是所有可能的长度值(不重复的)
    }

}
