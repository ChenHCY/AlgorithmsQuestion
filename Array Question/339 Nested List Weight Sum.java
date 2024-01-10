/* 339. Nested List Weight Sum

You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.

Return the sum of each integer in nestedList multiplied by its depth.

Example 1:
Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.

Example 3:
Input: nestedList = [0]
Output: 0
 
Constraints:
1 <= nestedList.length <= 50
The values of the integers in the nested list is in the range [-100, 100].
The maximum depth of any integer is less than or equal to 50.
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

//Solution 1: Used DFS
//Time: O(n)   Space: O(1)
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    //dfs function: 递归计算每个层级 数字的乘积和
    public int dfs(List<NestedInteger> nestedList, int depth){
        int sum = 0;

        //遍历整个nestedList
        for(NestedInteger currNested : nestedList){
            //判断是不是number
            if(currNested.isInteger()){
                sum += currNested.getInteger() * depth;
            } else{ //如果是队列，继续递归
                sum += dfs(currNested.getList(), depth + 1);
            }
        }

        return sum; //输出总和
    }
}


//Solution 2: Used BFS
// Time: O(N): 与 DFS 方法相似。每个嵌套元素只放入队列并从队列中移除一次。
// Space: O(n): 最坏情况发生在大部分元素都在同一层的情况下，例如，一个平面列表 [1, 2, 3, 4, 5]，因为所有的元素都必须同时放入队列。因此，这种方法的最坏情况的空间复杂度也为 O(N)
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList); //把nestedList里面所有的element都加入到队列中，进行计算层级深度

        int sum = 0;
        int depth = 1;

        while(!queue.isEmpty()){
            // 每一层 每一层的 计算number的层级值的结果
            int size = queue.size();
            for(int i = 0; i < size; i++){
                NestedInteger currNested = queue.poll();
                if(currNested.isInteger()){ //判断当前的element是不是数字
                    sum += currNested.getInteger() * depth; //计算这个element和depth的乘积值
                } else{
                    queue.addAll(currNested.getList()); //如果是list, 把list中的所有的element加入到队列中
                }
            }
            depth++;
        }
        return sum;
    }
}
