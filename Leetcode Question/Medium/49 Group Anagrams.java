/* Leetcode 49. Group Anagrams
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 
Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

//Solution 1: Used Arrays.sort() function to sort the string and a HashMap save into same arrylist
//Time: O(m * n * logn)  Space: O(n) 
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
    
        //remove 0 and null
        if(strs == null || strs.length == 0){
            return res;
        }
        //Used hashmap to save the string element and times
        HashMap<String, List<String>> map = new HashMap<>(); 
        
        //travser all the string element from strs
        for(String str : strs){
            // Used Arrays.sort() function to sort every string
            char[] c = str.toCharArray(); // change every str from strs[] list to be a char array
            Arrays.sort(c); // Reorder each char Array in character order
            String characterOder = new String(c); //change back to string format ==> used to find same string
          
            //if there is same string in the HashMap
            if(map.containsKey(characterOder)){
                map.get(characterOder).add(str); //add into the list of same string
            } else{ //if did not have same
                List<String> list = new ArrayList<>();
                list.add(str); //add into list
                map.put(characterOder, list); //put into hashmap with a new list
            }
        }
        
        res.addAll(map.values()); //add the hashmap into res list
        return res;
    }
}


//Solution 2: Used bucket sort to sort every String from strs[] list
//Time: O(m * n)  Space: O(n) 
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
    
        //remove 0 and null
        if(strs == null || strs.length == 0){
            return res;
        }
        //Used hashmap to save the string element and times
        HashMap<String, List<String>> map = new HashMap<>(); 
        
        //travser all the string element from strs
        for(String str : strs){
            //Used bucket sort to sort every String from strs[] list
            char[] c = new char[26]; 
            for(int i = 0; i < str.length(); i++){
                c[str.charAt(i) - 'a']++;
            }
            //change back to string format ==> used to find same string
            String characterOder = new String(c);
            
            //if there is same string in the HashMap
            if(map.containsKey(characterOder)){
                map.get(characterOder).add(str); //add into the list of same string
            } else{ //if did not have same
                List<String> list = new ArrayList<>();
                list.add(str); //add into list
                map.put(characterOder, list); //put into hashmap with a new list
            }
        }
        
        res.addAll(map.values()); //add the hashmap into res list
        return res;
    }
}
