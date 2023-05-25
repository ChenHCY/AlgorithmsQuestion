/* Leetcode 455. Assign Cookies
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; 
and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. 

Your goal is to maximize the number of your content children and output the maximum number.


Example 1:
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.

Example 2:
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
 

Constraints:
1 <= g.length <= 3 * 104
0 <= s.length <= 3 * 104
1 <= g[i], s[j] <= 231 - 1
*/

/*
因为此题是要找到饼干s[] 能满足最多的小孩g[] 数量，所以一定不要造成饼干尺寸的浪费。

==> 局部最优就是大饼干喂给胃口大的，充分利用饼干尺寸喂饱一个，

==> 全局最优就是喂饱尽可能多的小孩。

==> 可以尝试使用贪心策略，先将饼干数组和小孩数组排序。

*/

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int i = 0;
        int j = 0;
        int res = 0;
        
        Arrays.sort(g);
        Arrays.sort(s);
        
        while(i < g.length  && j < s.length){
            if(s[j] >= g[i]){
                res++;
                i++;
            } 
            j++;
        }
        
        return res;
    }
}
