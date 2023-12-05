/* Leetcode 666 Binary Tree Path Sum IV

If the depth of a tree is smaller than 5, this tree can be represented by a list of three-digits integers.

For each integer in this list:

  1. The hundreds digit represents the depth D of this node, 1 <= D <= 4.
  2. The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
  3. The units digit represents the value V of this node, 0 <= V <= 9.

Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:

Input: [113, 215, 221]
Output: 12
Explanation:
  The tree that the list represents is:
    3
   / \
  5   1
  The path sum is (3 + 5) + (3 + 1) = 12.

  
Example 2:

Input: [113, 221]
Output: 4
Explanation:
  The tree that the list represents is:
    3
     \
      1
  The path sum is 3 + 1 = 4.
*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: return an integer
     */
    
    int res;
    HashMap<Integer, Integer> map;

    public int pathSum(int[] nums) {
        // write your code here
        res = 0;
        map = new HashMap<>(); //储存每个节点值和 d p的对应关系

        for(int num : nums){
          int key = num / 10; // 把d 和 p 作为key进行储存
          int value = num % 10; //节点值作为value 储存进hashmap

          map.put(key, value);
        }

        dfs((nums[0] / 10), 0); //call helper function
        return res;
    }

    //helper small function: 用来递归和计算path总和
    public void dfs(int rootKey, int sum){
      //exit condition, 找不到对应node的key值(高度+位置)
      if(!map.containsKey(rootKey)){
        return;
      }

      //如果存在对应位置和高度的节点值，加入到sum
      sum += map.get(rootKey);
      System.out.println(sum);

      //根据公式：dnext = d + 1; / childLeftP = 2 * p - 1 and childRightP = 2 * p
      int currDepth = rootKey / 10;
      int currP = rootKey % 10;

      int childLeftKey = (currDepth + 1) * 10 + (currP * 2 - 1);
      int childRightKey = childLeftKey + 1; 

      //检查是否抵达最底层, 如果已经抵达，直接把当前路径的总和加入到总结果中
      if(!map.containsKey(childLeftKey) && !map.containsKey(childRightKey)){
        res += sum;
        return; //不用再执行后面的
      }

      dfs(childLeftKey, sum);
      dfs(childRightKey, sum);
    }
}
