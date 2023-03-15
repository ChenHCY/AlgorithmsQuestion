/* 71. Simplify Path
Given a string path, which is an absolute path (starting with a slash '/') to a file or 
directory in a Unix-style file system, convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double 
period '..' refers to the directory up a level, and any multiple consecutive slashes 
(i.e. '//') are treated as a single slash '/'. For this problem, any other format of 
periods such as '...' are treated as file/directory names.

The canonical path should have the following format:
The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.

Example 1:
Input: path = "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
Input: path = "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 

Constraints:
1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
*/

//Solution one: Used Deque Method ==> It can either be used as a queue(first-in-first-out/FIFO) or as a stack(last-in-first-out/LIFO).
//Time Complexity: O(N)    Space Complexity: O(N)  
//ArrayDeque的运行速度比Stack更快，所以现在通常来说是用Deque的
class Solution {
    public String simplifyPath(String path) {
        //Used the Deque to save the path file
        //first in first out ==> 
        ArrayDeque<String> stack = new ArrayDeque<>();
        //Split the String path with "/" and save into a String array
        String[] file = path.split("/"); 
        
        //travser all the element from String[] array, one by one
        for(String str : file){
            // A no-op for a "." or an empty string
            if(str.length() == 0 || str.equals(".")){
                continue;
            } else if(str.equals("..")){ //if meet "..", it need remove previous one path
                if (!stack.isEmpty()) {
                    stack.pop(); //Deque first in last one, pop() can get the top element
                }
            } else{ //others solution: add the string element into deque
                stack.push(str);
            }
        }
        
        
        //Save the data element into StringBuilder
        StringBuilder res = new StringBuilder();
        //When using this for-each loop to get an element from the stack, 
        //it will start at the bottom of Stack(first in - first out)
        while(!stack.isEmpty()){
            res.append("/");
            res.append(stack.pollLast());
        }
        
        //if there is "/../" ==> it res length is 0, and will return "/"
        return res.length() > 0 ? res.toString() : "/"; //output the string format
    }
}


//Solution Two: Used Stack Method
//Time Complexity: O(N)    Space Complexity: O(N)
class Solution {
    public String simplifyPath(String path) {
        //Used the Deque to save the path file
        //first in first out ==> 
        Stack<String> stack = new Stack<String>();
        //Split the String path with "/" and save into a String array
        String[] file = path.split("/"); 
        
        //travser all the element from String[] array, one by one
        for(String str : file){
            // A no-op for a "." or an empty string
            if(str.length() == 0 || str.equals(".")){
                continue;
            } else if(str.equals("..")){ //if meet "..", it need remove previous one path
                if (!stack.isEmpty()) {
                    stack.pop(); //Deque first in last one, pop() can get the top element
                }
            } else{ //others solution: add the string element into deque
                stack.push(str);
            }
        }
        
        
        //Save the data element into StringBuilder
        StringBuilder res = new StringBuilder();
        //When using this for-each loop to get an element from the stack, 
        //it will start at the bottom of Stack(first in - first out)
        for(String s : stack){
            res.append("/");
            res.append(s);
        }
        
        //if there is "/../" ==> it res length is 0, and will return "/"
        return res.length() > 0 ? res.toString() : "/"; //output the string format
    }
}
 
