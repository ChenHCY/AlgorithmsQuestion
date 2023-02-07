/* Leetcode 438. Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
*/

/*
思路： Bucket Sort[] + Sliding windows 滑动窗口

首先根据题意，我们要查找检查String S 中包含所有 任意排列形式 String P 的起始点 （都是小字母）

所以Bucket Sort就是最好的选择，Bucket Sort可以根据26个字母的顺序来存储字符串中字母的组成 ==》 每个字母出现了多少次

==》可以帮助我们来判断两个string是否组成一样

然后因为要查找到String S 中所有包含任意排列形式的 String P ==》 使用一个长度为P.length的滑动窗口

然后是一个For-loop 开始遍历： 两种情况：


***1. 当For-loop的i指针还处于 [0, P.length] 之内的时候，

==> bucketP[p.charAt(i) - 'a']++; //把String p中的字母储存到Bucket Sort中

==> 然后当其到达p.length的最后一个字母的时候， 使用checkFren function进行判断

 if(i == p.length() - 1 && checkFren(bucketS, bucketP)){）==》如果字母组成一样，记录起始点（0）

 
 ***2.  当For-loop的i指针处于 [P.length， S.length] 之内的时候，
 
 因为我们已经越过了String P的长度，所以我们只需要对于滑动窗口 进行移动，然后来找到相同的字母组成
 
 ==> 删除： bucketS[s.charAt(i - p.length()) - 'a']--; //改变和移动String S的滑动窗口，删除原有窗口的第一个字母
 
 ==》 增加： bucketS[s.charAt(i) - 'a']++; //把String s中的字母储存到Bucket Sort中
 
 然后再使用checkFren function进行判断，检查是否是相同的字母组成
 
 if(checkFren(bucketS, bucketP)){
     res.add(i - p.length() + 1); //找到当前窗口的第一个字母的位置，也就是原有窗口的第二个字母
 }
 
 ==》如果字母组成一样，记录起始点 ==》 res.add(i - p.length() + 1);
 
*/

class Solution {
    //Bucket Sort + Sliding windows
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        //Bucket Sort: Used to save all the character frenqucy from String s
        int[] bucketS = new int[26];
        int[] bucketP = new int[26];

        //检查和 String p 和 String s 中的滑动window 中的字母组成 是否一样
        //然后记录每个一样的起始点
        for(int i = 0; i < s.length(); i++){
            //每次把String S 中 i指针的字母存入bucket Sort
            bucketS[s.charAt(i) - 'a']++; //把String s中的字母储存到Bucket Sort中

            //每个滑动窗口都是P的长度
            if(i < p.length()){
                bucketP[p.charAt(i) - 'a']++; //把String p中的字母储存到Bucket Sort中

                //当遍历到String P的最后一个字母，检查String P是否和String S的第一个滑动窗口的字母组成一样
                if(i == p.length() - 1 && checkFren(bucketS, bucketP)){
                    res.add(0); //如果一样，添加起始点进入res ==> 第一个窗口 所以起始点为0
                }
            } else{ //当遍历范围越过string p的长度
                bucketS[s.charAt(i - p.length()) - 'a']--; //改变和移动String S的滑动窗口，删除原有窗口的第一个字母 

                if(checkFren(bucketS, bucketP)){
                    res.add(i - p.length() + 1); //找到当前窗口的第一个字母的位置，也就是原有窗口的第二个字母
                }
            }
        }
        return res;
    }

    //boolean function to check whether two range all characters has same frequency 
    //用bucket sort来表示字符串中字母的组成，所以这个子程序是来检查这两个字符串是否在不同的排列组合下都是一样的
    public static boolean checkFren(int[] dict1, int[] dict2){
        for(int i = 0; i < 26; i++){
            if(dict1[i] != dict2[i]){
                return false; //if find one character frequency is difference, return false
            }
        }
        return true;  //if all the characters frequency is same, return true
    }
}
