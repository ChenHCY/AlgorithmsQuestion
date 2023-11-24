/* Leetcode 1424. Diagonal Traverse II

Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 
Constraints:
1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= sum(nums[i].length) <= 10^5
1 <= nums[i][j] <= 10^5
*/

    // 因为要按照对角线顺序输出 matrix里面的元素，从左上到右下
    // 所以首先按照 i(横坐标) + j(纵坐标) 的和，对于所有number进行分类
    // 然后在把其放入结果array中输出

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        int maxSum = 0;
        // 使用邻接表，来按照 i(横坐标) + j(纵坐标) 的和，对于所有number进行分类
        List<Integer>[] map = new ArrayList[100001];

        // i(横坐标)   j(纵坐标)
        for(int i = 0; i < nums.size(); i++){
            len += nums.get(i).size(); // 统计总共列表的长度
            for(int j = 0; j < nums.get(i).size(); j++){
                int sum = i + j;
                // 如果不存在，则创造一个新的空间
                if(map[sum] == null){
                    map[sum] = new ArrayList<>();
                } 
                map[sum].add(nums.get(i).get(j));
                maxSum = Math.max(maxSum, sum); // 因为我无法知道按照对角线分类，存在多少类
            }
        }

        int[] res = new int[len];
        int index = 0; //用来添加值进入array的下标

        //从左上往右下，所以按照层级i+j 从小往大一层一层的提取 加入到最后的 res array
        for(int i = 0; i <= maxSum; i++){
            List<Integer> numList = map[i];
            //因为提取的方向是从左下往右上，所以添加数字到结果的时候，需要从后往前加
            for(int k = numList.size() - 1; k >= 0; k--){
                res[index] = numList.get(k);
                index++;
            }
        }
        return res;
    }
}
