/* 10037. Maximum Size of a Set After Removals

You are given two 0-indexed integer arrays nums1 and nums2 of even length n.

You must remove n / 2 elements from nums1 and n / 2 elements from nums2. After the removals, you insert the remaining elements of nums1 and nums2 into a set s.

Return the maximum possible size of the set s.

Example 1:
Input: nums1 = [1,2,1,2], nums2 = [1,1,1,1]
Output: 2
Explanation: We remove two occurences of 1 from nums1 and nums2. After the removals, the arrays become equal to nums1 = [2,2] and nums2 = [1,1]. Therefore, s = {1,2}.
It can be shown that 2 is the maximum possible size of the set s after the removals.

Example 2:
Input: nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
Output: 5
Explanation: We remove 2, 3, and 6 from nums1, as well as 2 and two occurrences of 3 from nums2. After the removals, the arrays become equal to nums1 = [1,4,5] and nums2 = [2,3,2]. Therefore, s = {1,2,3,4,5}.
It can be shown that 5 is the maximum possible size of the set s after the removals.

Example 3:
Input: nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
Output: 6
Explanation: We remove 1, 2, and 3 from nums1, as well as 4, 5, and 6 from nums2. After the removals, the arrays become equal to nums1 = [1,2,3] and nums2 = [4,5,6]. Therefore, s = {1,2,3,4,5,6}.
It can be shown that 6 is the maximum possible size of the set s after the removals.
 

Constraints:
n == nums1.length == nums2.length
1 <= n <= 2 * 10^4
n is even.
1 <= nums1[i], nums2[i] <= 10^9
*/
/*
    // 要得到nums1 和 nums2 合并之后，删除一半数字，的能有的 最多种类
    // 1. 统计nums1[]和nums2[] 删除重复数字之后，的最长长度
    // 2. 统计nums1[]和nums2[] 两个数组重复的数字
    // 3. 然后考虑怎么删除一半的数字 能保留的数字种类最多：
    // ==> 首先是删除nums1[]和nums2[] 各自重复的数字
    // ==> 其次是删除nums1[]和nums2[]相互重复的数字comman
    // ==> 最后如果还不够，删除nums1[]和nums2剩余数字里面的
*/
//Time: O(M + N) M is length of nums1[], n is the length of nums2[]
//Space: O(M + N) M is length of nums1[], n is the length of nums2[]
class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        int comman = 0; // nums1[]和nums2[] 的相同数字的数量

        //计算nums1里面不重复的数字数量
        for(int num : nums1){
            set1.add(num);
        }

        //计算nums2里面不重复的数字数量
        for(int num : nums2){
            if (set2.contains(num)) {
                continue;
            }
            set2.add(num);
            if(set1.contains(num)){
                comman += 1;
            }
        }

        int n1 = set1.size(); //nums1[]不重复的长度
        int n2 = set2.size(); //nums2[]不重复的长度
        int ans = n1 + n2 - comman; //不删除数字的情况下，nums1+nums2能得到的不重复数字[]的最长长度
        int m = nums1.length / 2; //需要删除的元素

        //判断nums1是不是要继续删除：重复的数字够不够需要删除的(n/2)
        if(n1 > m){
            //如果需要继续删
            //开始删除nums1[]和nums2[]相互重复的数字comman
            //n1-m 表示还有多少个需要删除的数字，comman表示nums1[]和nums2[]的公共数字
            int mn = Math.min(n1 - m, comman); //nums1能够删除的数字
            n1 -= mn; //nums1移除能够删除的数字
            comman -= mn; //从公共数字里减去这些数字

            //我们在前面已经在ans中减去了重复的数字，所以ans只需要考虑，抵消完单独重复，公共数字之后，num1还要删除多少元素
            ans -= n1 - m; //最后如果还不够，删除nums1[]剩余数字里面的 n1 - m个，表示还需要删除的数字数量
            //ans是不删除数字的情况下，nums1+nums2能得到的不重复数字[]的最长长度
        }

        //判断nums2是不是要继续删除：重复的数字够不够需要删除的(n/2)
        if(n2 > m){
            //如果需要继续删
            //开始删除nums1[]和nums2[]相互重复的数字comman
            //n2-m 表示还有多少个需要删除的数字，comman表示nums1[]和nums2[]的公共数字 
            int mn = Math.min(n2 - m, comman); //nums2能够删除的数字
            n2 -= mn; //nums2移除能够删除的数字

            //我们在前面已经在ans中减去了重复的数字，所以ans只需要考虑，抵消完单独重复，公共数字之后，num2还要删除多少元素
            ans -= n2 - m; //最后如果还不够，删除nums2[]剩余数字里面的 n2 - m个，表示还需要删除的数字数量
            //ans是不删除数字的情况下，nums1+nums2能得到的不重复数字[]的最长长度
        }

        return ans;
    }
}
