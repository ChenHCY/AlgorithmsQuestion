/* 165. Compare Version Numbers

Given two version numbers, version1 and version2, compare them.

Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. 
Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, 
the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.

To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. 
This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, 
version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
 
Example 1:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 2:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".

Example 3:
Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 

Constraints:
1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
*/

// Time: O(n + m)  n is version1.length; m is version2.length,
// Space: O(n + m) n is version1.length; m is version2.length,
class Solution {
    // Integer.parseInt() 可以用来去除 “01” 中的前导0
    public int compareVersion(String version1, String version2) {
      
        // 使用 Split()正则表达式 对于 String version 1 和 version 2 进行分割
        // 因为在正则表达式中，"." 表示任意字符，但是在 Java 中，"." 是正则表达式中的特殊字符
        // ==> 所以我们需要使用 "\\." 来表示真正的点字符。
        String[] sArr1 = version1.split("\\.");
        String[] sArr2 = version2.split("\\.");

        // 这样我们就基于"."，对于两个version string进行了分割
        // while-loop 从左往右遍历，对比 Version1 和 version2 的每一位上的数字是否一样
        int i = 0;
        int j = 0;

        while(i < sArr1.length || j < sArr2.length){
            int num1 = 0;
            int num2 = 0;

            // 得到当前遍历位置 version1 和 version2 上的数值
            // Integer.parseInt() 可以用来去除前导0
            if(i < sArr1.length){
                num1 = Integer.parseInt(sArr1[i]);
                i++;
            }

            if(j < sArr2.length){
                num2 = Integer.parseInt(sArr2[j]);
                j++;
            }

            //判断当前位置上 两个version的值是否一样
            if(num1 < num2){ //If version1 < version2, return -1.
                return -1;
            } else if(num1 > num2){ //If version1 > version2, return 1.
                return 1;
            }
        }   

        return 0;//Otherwise, return 0.
    }
}
