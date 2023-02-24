/* Leetcode 242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.


Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */

var isAnagram = function(s, t) {
    if(s.length !== t.length){
        return false;
    }

    let map = {}; //hashmap
    //used hashmap to save the characters from string t and s, and count the freqence time
    for(let i = 0; i < s.length; i++){
        map[s[i]] = map[s[i]] ? map[s[i]] + 1 : 1;
        map[t[i]] = map[t[i]] ? map[t[i]] - 1 : -1;
    }

    for(let key in map){
        if(map[key] !== 0){
            return false;
        }
    }

    return true;
};
