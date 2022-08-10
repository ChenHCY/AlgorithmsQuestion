/* Leetcode 500. Keyboard Row
Given an array of strings words, return the words that can be typed using letters of the alphabet on 
only one row of American keyboard like the image below.

In the American keyboard:
the first row consists of the characters "qwertyuiop",
the second row consists of the characters "asdfghjkl", and
the third row consists of the characters "zxcvbnm".

Example 1:
Input: words = ["Hello","Alaska","Dad","Peace"]
Output: ["Alaska","Dad"]

Example 2:
Input: words = ["omk"]
Output: []

Example 3:
Input: words = ["adsdf","sfd"]
Output: ["adsdf","sfd"]

Constraints:
1 <= words.length <= 20
1 <= words[i].length <= 100
words[i] consists of English letters (both lowercase and uppercase). 
*/

class Solution {
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";
        
        //check every element from String list words whether can can be typed using letters of the alphabet on only one row of keyboards
        for(String word : words){
            if(check(word, row1) || check(word, row2) || check(word, row3)){
                res.add(word);
            }
        }
        
        //change arraylist to be string[] list
        /*int size = res.size();
        String[] list = new String[size];
        
        for(int i = 0; i < list.length; i++){
            list[i] = res.get(i);
        } */
        
        return res.toArray(new String[0]);      
    }
    
    //Small function: used for check whether can  typed using letters of the alphabet on only one row 
    public boolean check(String word, String row){
        for(int i = 0; i < word.length(); i++){
            //contains() method checks whether a string contains a sequence of characters
            if(!row.contains((word.charAt(i) + "").toLowerCase())){
                return false;
            }
        }
        return true;
    }
}
