/* 8 · Rotate String
Description
Given a string of char array and an offset, rotate the string by offset in place. (from left to right).
In different languages, str will be given in different ways. For example, the string "abc" will be given in following ways:

Java: char[] str = {'a', 'b', 'c'};
Python：str = ['a', 'b', 'c']
C++：string str = "abc";

Example 1:
Input:
str = ""abcdefg"
offset = 3
Output:
"efgabcd"

Explanation:
Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".

Example 2:
Input:
str = ""abcdefg"
offset = 0
Output:
"abcdefg"

Explanation:
Note that it is rotated in place, that is, after str is rotated, it becomes "abcdefg".
*/

public class Solution {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        int right = str.length; //used for make the leangth of new list 
        char[] result = new char[right]; //creative a new list to save data 

        if(str.length <= 1){
            return;
        }

        offset = offset % right; // "%=" is set to remainder

        //first save the offset part(behind part) into the result list
        for(int i = 0; i < offset; i++){
            result[i] = str[right - offset + i];
        }

        //second save the front part into the result list
        int start = 0;
        for(int j = offset; j < right; j++){
            result[j] = str[start];
            start++;
        }

        //Finally save all the result list data instead of str list data
        for(int k = 0; k < right; k++){
            str[k] = result[k];
        }

    }
}
